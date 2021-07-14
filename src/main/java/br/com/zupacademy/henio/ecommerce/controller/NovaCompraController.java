package br.com.zupacademy.henio.ecommerce.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.henio.ecommerce.dto.NovaCompraRequest;
import br.com.zupacademy.henio.ecommerce.enums.GatewayPagamento;
import br.com.zupacademy.henio.ecommerce.infrastructure.service.EmailService;
import br.com.zupacademy.henio.ecommerce.model.Compra;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/compras")
public class NovaCompraController {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmailService emailService;

	@PostMapping
	@Transactional
	public String novaCompra(@Valid @RequestBody NovaCompraRequest request) throws BindException {

		Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());

		int quantidade = request.getQuantidade();
		boolean baixou = produtoASerComprado.baixarDoEstoque(quantidade);

		if (baixou) {
			Usuario comprador = usuarioRepository.findByEmail("alex@gmail.com").get();
			GatewayPagamento gateway = request.getGateway();
			Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, gateway);
			manager.persist(novaCompra);
			emailService.envioDeConfirmacaoDeCompra(novaCompra);

			if (gateway.equals(GatewayPagamento.PAGSEGURO)) {
				String urlRetornoPagseguro = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
				return "pagseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagseguro;
			} else {
				String urlRetornoPaypal = ServletUriComponentsBuilder.fromCurrentRequest().path("/retorno-paypal/{id}")
						.buildAndExpand(novaCompra.getId()).toString();
				return "paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPaypal;
			}
		}
		BindException problemasComEstoque = new BindException(request, "novaCompraRequest");
		problemasComEstoque.reject(null, "Não foi possivel realizar a compra por conta do estoque");
		throw problemasComEstoque;
		
	}
}
