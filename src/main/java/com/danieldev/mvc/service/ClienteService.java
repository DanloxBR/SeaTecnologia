package com.danieldev.mvc.service;

import com.danieldev.mvc.dto.ClienteDTO;
import com.danieldev.mvc.entity.Cliente;
import com.danieldev.mvc.entity.ClienteEmail;
import com.danieldev.mvc.entity.Endereco;
import com.danieldev.mvc.entity.Telefone;
import com.danieldev.mvc.integration.ViaCepResponse;
import com.danieldev.mvc.integration.ViaCepService;
import com.danieldev.mvc.repository.ClienteRepository;
import com.danieldev.mvc.util.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    public Cliente salvarCliente(ClienteDTO dto) {

        String cpf = dto.getCpf().replaceAll("\\D", "");

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

        String cep = dto.getEndereco().getCep().replaceAll("\\D", "");
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
            tel.setNumero(t.getNumero().replaceAll("\\D", ""));
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

        return repository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}