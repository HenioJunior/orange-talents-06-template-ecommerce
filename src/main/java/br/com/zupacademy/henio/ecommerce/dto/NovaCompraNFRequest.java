package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {
	
	@NotNull
	private long idCompra;
	@NotNull
	private long idComprador;
	
	public NovaCompraNFRequest(@NotNull long idCompra, @NotNull long idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	@Override
	public String toString() {
		return "Nota fiscal: [idCompra=" + idCompra + ", idComprador=" + idComprador + "]";
	}
}
