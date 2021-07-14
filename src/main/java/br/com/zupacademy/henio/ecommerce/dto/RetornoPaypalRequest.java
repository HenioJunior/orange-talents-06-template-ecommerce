package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.henio.ecommerce.enums.StatusTransacao;
import br.com.zupacademy.henio.ecommerce.model.Compra;
import br.com.zupacademy.henio.ecommerce.model.Transacao;

public class RetornoPaypalRequest implements RetornoGatewayPagamento{
	
	@Min(0)
	@Max(1)
	private int status;
	
	@NotBlank
	private String idTransacao;

	public RetornoPaypalRequest(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
		super();
		this.status = status;
		this.idTransacao = idTransacao;
	}
			
	public int getStatus() {
		return status;
	}

	public Transacao toTransacao(Compra compra) {
		@NotNull
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.SUCESSO : StatusTransacao.ERRO;
		return new Transacao(statusCalculado, idTransacao, compra);
	}
	
}
