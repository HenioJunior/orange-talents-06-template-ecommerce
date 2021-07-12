package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.henio.ecommerce.model.ProdutoImagem;

public class ProdutoDetalheImagemResponse {
	
	private String linkImagem;
	
	public ProdutoDetalheImagemResponse() {
	}

	public ProdutoDetalheImagemResponse(@NotNull @Valid ProdutoImagem linkImagem) {
		this.linkImagem = linkImagem.getLink();
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	@Override
	public String toString() {
		return "Link: [linkImagem=" + linkImagem + "]";
	}
}
