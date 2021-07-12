package br.com.zupacademy.henio.ecommerce.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.dto.NovaCategoriaRequest;
import br.com.zupacademy.henio.ecommerce.model.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @PersistenceContext
	EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCategoria(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel(manager);
        manager.persist(categoria);
        return ResponseEntity.ok(request.toString());
    }
}
