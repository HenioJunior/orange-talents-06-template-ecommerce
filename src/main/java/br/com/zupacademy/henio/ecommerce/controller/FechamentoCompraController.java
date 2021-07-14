package br.com.zupacademy.henio.ecommerce.controller;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.controller.exceptions.DatabaseException;
import br.com.zupacademy.henio.ecommerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.henio.ecommerce.dto.RetornoPagseguroRequest;
import br.com.zupacademy.henio.ecommerce.dto.RetornoPaypalRequest;
import br.com.zupacademy.henio.ecommerce.enums.StatusRetornoPagSeguro;
import br.com.zupacademy.henio.ecommerce.infrastructure.EmailService;
import br.com.zupacademy.henio.ecommerce.model.Compra;
import br.com.zupacademy.henio.ecommerce.model.EventoCompraSucesso;
import br.com.zupacademy.henio.ecommerce.repository.CompraRepository;

@RestController
@RequestMapping(value = "/retorno")
public class FechamentoCompraController {

	@Autowired
	CompraRepository repository;

	@Autowired
	EmailService emailService;

	@Autowired
	private Set<EventoCompraSucesso> eventosCompraSucesso;

	@PostMapping(value = "/pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
		StatusRetornoPagSeguro status = request.getStatus();
		if(status.equals(StatusRetornoPagSeguro.ERRO)) {
			Compra compra = verificaCompra(idCompra);
			emailService.envioDeCompraNaoProcessadaPagseguro(compra);
			return "O pagamento não pode ser processado";
		}
		return processa(idCompra, request);
	}

	

	@PostMapping(value = "/paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
		int status = request.getStatus();
		if (status == 1) {
			Compra compra = verificaCompra(idCompra);
			emailService.envioDeCompraNaoProcessadaPayPal(compra);
			return "O pagamento não pode ser processado";
		}
		return processa(idCompra, request);
	}

	private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
		Optional<Compra> objCompra = repository.findById(idCompra);
		Compra compra = objCompra.orElseThrow(() -> new DatabaseException("Compra não localizada"));
		compra.adicionaTransacao(retornoGatewayPagamento);
		repository.save(compra);
		if (compra.processadaComSucesso()) {
			eventosCompraSucesso.forEach(evento -> evento.processa(compra));
			emailService.envioDeConfirmacaoDeProcessamentoDaCompra(compra);
		}

		return compra.toString();
	}
	
	private Compra verificaCompra(Long idCompra) {
		Optional<Compra> objCompra = repository.findById(idCompra);
		Compra compra = objCompra.orElseThrow(() -> new DatabaseException("Compra não localizada"));
		return compra;
	}
}
