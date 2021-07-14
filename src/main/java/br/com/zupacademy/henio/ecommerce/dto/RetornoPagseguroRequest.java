package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.henio.ecommerce.enums.StatusRetornoPagSeguro;
import br.com.zupacademy.henio.ecommerce.model.Compra;
import br.com.zupacademy.henio.ecommerce.model.Transacao;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento{
	
	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagSeguro status;
	
	public RetornoPagseguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	public String getIdTransacao() {
		return idTransacao;
	}
		
	public StatusRetornoPagSeguro getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RetornoPagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
}
