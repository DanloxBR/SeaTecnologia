package com.danieldev.mvc.service;

import com.danieldev.mvc.dto.*;
import com.danieldev.mvc.dto.response.ClienteResponseDTO;
import com.danieldev.mvc.dto.response.EnderecoResponseDTO;
import com.danieldev.mvc.dto.response.TelefoneResponseDTO;
import com.danieldev.mvc.entity.*;
import com.danieldev.mvc.integration.ViaCepResponse;
import com.danieldev.mvc.integration.ViaCepService;
import com.danieldev.mvc.repository.ClienteRepository;
import com.danieldev.mvc.util.CPFValidator;
import com.danieldev.mvc.util.MaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    public ClienteResponseDTO salvarCliente(ClienteDTO dto) {

        Cliente cliente = mapToEntity(dto);

        cliente = repository.save(cliente);

        return toResponse(cliente);
    }

    public List<ClienteResponseDTO> listarClientes() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return toResponse(cliente);
    }

    private Cliente mapToEntity(ClienteDTO dto) {

        String cpf = MaskUtil.remover(dto.getCpf());

        if (!CPFValidator.isValid(cpf)) {
            throw new RuntimeException("CPF inválido");
        }

        if (repository.findByCpf(cpf).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }

        if (dto.getTelefones() == null || dto.getTelefones().isEmpty() || dto.getTelefones().size() > 5) {
            throw new RuntimeException("Cliente deve ter entre 1 e 5 telefones");
        }

        if (dto.getEmails() == null || dto.getEmails().isEmpty()) {
            throw new RuntimeException("Cliente deve ter ao menos 1 email");
        }

        String cep = MaskUtil.remover(dto.getEndereco().getCep());

        ViaCepResponse viaCep = viaCepService.buscarCep(cep);

        if (viaCep == null || viaCep.getLogradouro() == null) {
            throw new RuntimeException("CEP inválido");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(cpf);

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());
        endereco.setComplemento(dto.getEndereco().getComplemento());

        cliente.setEndereco(endereco);

        List<Telefone> telefones = dto.getTelefones().stream().map(t -> {
            Telefone tel = new Telefone();
            tel.setNumero(MaskUtil.remover(t.getNumero()));
            tel.setTipo(t.getTipo());
            return tel;
        }).collect(Collectors.toList());

        cliente.setTelefones(telefones);

        List<ClienteEmail> emails = dto.getEmails().stream().map(e -> {
            ClienteEmail email = new ClienteEmail();
            email.setEmail(e);
            return email;
        }).collect(Collectors.toList());

        cliente.setEmails(emails);

        return cliente;
    }

    private ClienteResponseDTO toResponse(Cliente cliente) {

        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(MaskUtil.cpf(cliente.getCpf()))
                .endereco(EnderecoResponseDTO.builder()
                        .cep(MaskUtil.cep(cliente.getEndereco().getCep()))
                        .logradouro(cliente.getEndereco().getLogradouro())
                        .bairro(cliente.getEndereco().getBairro())
                        .cidade(cliente.getEndereco().getCidade())
                        .uf(cliente.getEndereco().getUf())
                        .complemento(cliente.getEndereco().getComplemento())
                        .build())
                .telefones(cliente.getTelefones().stream().map(t ->
                        TelefoneResponseDTO.builder()
                                .numero(MaskUtil.telefone(t.getNumero()))
                                .tipo(t.getTipo().name())
                                .build()
                ).collect(Collectors.toList()))
                .emails(cliente.getEmails().stream()
                        .map(ClienteEmail::getEmail)
                        .collect(Collectors.toList()))
                .build();
    }
}