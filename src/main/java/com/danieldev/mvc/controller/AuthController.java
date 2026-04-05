package com.danieldev.mvc.controller;

import com.danieldev.mvc.entity.Usuario;
import com.danieldev.mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(repository.save(usuario));
    }
}