package com.danieldev.mvc.controller;

import com.danieldev.mvc.dto.ClienteDTO;
import com.danieldev.mvc.dto.response.ClienteResponseDTO;
import com.danieldev.mvc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvarCliente(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        return ResponseEntity.ok(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}