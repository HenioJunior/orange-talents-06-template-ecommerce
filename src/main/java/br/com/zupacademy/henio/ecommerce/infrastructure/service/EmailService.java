package br.com.zupacademy.henio.ecommerce.infrastructure.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.zupacademy.henio.ecommerce.model.Compra;

public interface EmailService {

	void envioDeConfirmacaoDeCompra(Compra obj);
	
	void envioDeConfirmacaoDeProcessamentoDaCompra(Compra obj);
	
	void envioDeCompraNaoProcessadaPayPal(Compra obj);
	
	void envioDeCompraNaoProcessadaPagseguro(Compra obj);

	void sendEmail(SimpleMailMessage msg);

	
}