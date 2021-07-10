package br.com.zupacademy.henio.ecommerce.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zupacademy.henio.ecommerce.model.CaracteristicaProduto;
import br.com.zupacademy.henio.ecommerce.model.Produto;

public class CaracteristicaProdutoRequest {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public CaracteristicaProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
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

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
}
