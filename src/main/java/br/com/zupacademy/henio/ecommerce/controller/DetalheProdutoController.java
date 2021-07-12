package br.com.zupacademy.henio.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.dto.ProdutoDetalheResponse;
import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.repository.ProdutoRepository;

@RequestMapping(value = "/produtos")
@RestController
public class DetalheProdutoController {

	@Autowired
	ProdutoRepository repository;
	
	
	@GetMapping(value = "/{id}")
    public ProdutoDetalheResponse detalheProduto(@PathVariable Long id) {
		
    	Optional<Produto> obj = repository.findById(id);
    	Produto produtoEscolhido = obj.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
		
		return new ProdutoDetalheResponse(produtoEscolhido);
	}
}
