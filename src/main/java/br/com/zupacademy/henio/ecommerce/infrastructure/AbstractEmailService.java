package br.com.zupacademy.henio.ecommerce.infrastructure;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.zupacademy.henio.ecommerce.model.Compra;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void envioDeConfirmacaoDeCompra(Compra obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromNovaCompra(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromNovaCompra(Compra obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getComprador().getUsername());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		System.out.println(obj.toString());
		return sm;
	}
		
	@Override
	public void envioDeConfirmacaoDeProcessamentoDaCompra(Compra obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromProcessamentoDaCompra(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromProcessamentoDaCompra(Compra obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getComprador().getUsername());
		sm.setFrom(sender);
		sm.setSubject("A compra já foi processada!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	@Override
	public void envioDeCompraNaoProcessadaPayPal(Compra obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromCompraNaoProcessadaPaypal(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromCompraNaoProcessadaPaypal(Compra obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getComprador().getUsername());
		sm.setFrom(sender);
		sm.setSubject("A compra não pode ser processada - tente novamente: http://localhost:8080/compras/retorno-paypal/"+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	@Override
	public void envioDeCompraNaoProcessadaPagseguro(Compra obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromCompraNaoProcessadaPagseguro(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromCompraNaoProcessadaPagseguro(Compra obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getComprador().getUsername());
		sm.setFrom(sender);
		sm.setSubject("A compra não pode ser processada - tente novamente: http://localhost:8080/compras/retorno-pagseguro/"+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}