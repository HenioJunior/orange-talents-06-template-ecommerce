package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zupacademy.henio.ecommerce.model.ProdutoCaracteristica;

public class ProdutoDetalheCaracteristicaResponse {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
			
	public ProdutoDetalheCaracteristicaResponse(@NotNull @Valid ProdutoCaracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}
		
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
