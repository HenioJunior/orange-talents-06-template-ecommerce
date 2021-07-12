package br.com.zupacademy.henio.ecommerce.controller;

import static br.com.zupacademy.henio.ecommerce.controller.util.UsuarioAutenticado.authenticated;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.controller.exceptions.AuthorizationException;
import br.com.zupacademy.henio.ecommerce.controller.util.UploaderFake;
import br.com.zupacademy.henio.ecommerce.dto.NovoProdutoImagemRequest;
import br.com.zupacademy.henio.ecommerce.dto.NovoProdutoRequest;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;
import br.com.zupacademy.henio.ecommerce.validation.ProibeCaracteristicaComNomeIgualValidator;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
      
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @PersistenceContext
    EntityManager manager;

    @Autowired
    UploaderFake uploaderFake;
    
    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
    	webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProduto(@RequestBody @Valid NovoProdutoRequest request) {
        Produto produto = request.toModel(manager, usuarioRepository);
        manager.persist(produto);
        return ResponseEntity.ok(request.toString());
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id, @Valid NovoProdutoImagemRequest request) {
    	Usuario usuarioAutenticado = authenticated();
		Usuario usuario = usuarioRepository.findByEmail("alex@gmail.com").get();
				
		if (!(usuarioAutenticado.equals(usuario))) {
			throw new AuthorizationException("Acesso negado");
		}

        Set<String> links = uploaderFake.envia(request.getImagens());
		Produto produto = manager.find(Produto.class, id);
		produto.associaImagens(links);

		manager.merge(produto);
						
		return ResponseEntity.ok().body("Link criado: " + links);
    }
}
