package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zupacademy.henio.ecommerce.model.ProdutoCaracteristica;
import br.com.zupacademy.henio.ecommerce.model.Produto;

public class NovoProdutoCaracteristicaRequest {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public NovoProdutoCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public NovoProdutoCaracteristicaRequest(@NotNull @Valid ProdutoCaracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}
		
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "Caracteristicas [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public ProdutoCaracteristica toModel(@NotNull @Valid Produto produto) {
		return new ProdutoCaracteristica(nome, descricao, produto);
	}
}
