package br.com.zupacademy.henio.ecommerce.dto;

import br.com.zupacademy.henio.ecommerce.model.Compra;
import br.com.zupacademy.henio.ecommerce.model.Transacao;

public interface RetornoGatewayPagamento {
	

	Transacao toTransacao(Compra compra);

}
