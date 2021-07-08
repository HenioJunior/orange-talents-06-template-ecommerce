package br.com.zupacademy.henio.ecommerce.controller;

import br.com.zupacademy.henio.ecommerce.dto.request.UsuarioRequest;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> criarUsuario(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        repository.save(usuario);
        return ResponseEntity.ok().body("Usuário cadastrado");
    }
}
