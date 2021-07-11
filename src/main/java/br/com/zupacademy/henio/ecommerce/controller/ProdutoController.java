package br.com.zupacademy.henio.ecommerce.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zupacademy.henio.ecommerce.controller.exceptions.AuthorizationException;
import br.com.zupacademy.henio.ecommerce.controller.util.UploaderFake;
import br.com.zupacademy.henio.ecommerce.controller.util.UsuarioAutenticado;
import br.com.zupacademy.henio.ecommerce.dto.request.ImagensRequest;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import br.com.zupacademy.henio.ecommerce.dto.request.ProdutoRequest;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.henio.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;
import br.com.zupacademy.henio.ecommerce.validation.ProibeCaracteristicaComNomeIgualValidator;

import java.util.Set;

import static br.com.zupacademy.henio.ecommerce.controller.util.UsuarioAutenticado.authenticated;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UploaderFake uploaderFake;
    
    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
    	webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProduto(@RequestBody @Valid ProdutoRequest request) {
        Produto produto = request.toModel(categoriaRepository, usuarioRepository);
        produtoRepository.save(produto);
        return ResponseEntity.ok(request.toString());
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<String> adicionaImagens(@PathVariable("id") Long id, @Valid ImagensRequest request) {
        Usuario usuario = authenticated();
        long donoId = 1;
        if(usuario == null || usuario.getId() != donoId) {
            throw new AuthorizationException("Acesso negado");
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
		Produto produto = produtoRepository.findById(id).get();
		produto.associaImagens(links);

		produtoRepository.save(produto);
       return ResponseEntity.ok().body(produto.toString());
    }
}
