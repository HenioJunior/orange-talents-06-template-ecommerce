package br.com.zupacademy.henio.ecommerce.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.dto.NovaOpiniaoRequest;
import br.com.zupacademy.henio.ecommerce.model.Opiniao;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/produtos/{id}/opiniao")

public class OpiniaoController {
			
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	EntityManager manager;
	
	@PostMapping
    @Transactional
    public ResponseEntity<?> criarOpiniao(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		Opiniao novaOpiniao = request.toModel(produto, usuarioRepository);
		manager.persist(novaOpiniao);
        return ResponseEntity.ok(request.toString());
    }

}
