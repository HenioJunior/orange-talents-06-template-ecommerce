package br.com.zupacademy.henio.ecommerce.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.dto.request.OpiniaoRequest;
import br.com.zupacademy.henio.ecommerce.model.Opiniao;
import br.com.zupacademy.henio.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/produtos/{id}/opiniao")

public class OpiniaoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	EntityManager manager;
	
	@PostMapping
    @Transactional
    public ResponseEntity<?> criarOpiniao(@RequestBody @Valid OpiniaoRequest request) {
		Opiniao novaOpiniao = request.toModel(produtoRepository, usuarioRepository);
		manager.persist(novaOpiniao);
        return ResponseEntity.ok(request.toString());
    }

}
