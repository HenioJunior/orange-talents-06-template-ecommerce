package br.com.zupacademy.henio.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_caracteristica")
public class ProdutoCaracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	@Valid
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public ProdutoCaracteristica() {
	}

	public ProdutoCaracteristica(@NotBlank String nome, @NotBlank String descricao, @Valid Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
		
}
