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

import br.com.zupacademy.henio.ecommerce.dto.NovaPerguntaRequest;
import br.com.zupacademy.henio.ecommerce.model.Pergunta;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/produtos/{id}/pergunta")
public class PerguntaController {
	
	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	UsuarioRepository repository;
	
	@PostMapping
    @Transactional
    public ResponseEntity<?> criarPergunta(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		Pergunta novaPergunta = request.toModel(produto, repository);
		System.out.println(request.toString());
		manager.persist(novaPergunta);
        return ResponseEntity.ok(request.toString());
    }

}
