package br.com.zupacademy.henio.ecommerce.dto;

import br.com.zupacademy.henio.ecommerce.model.Opiniao;

public class ProdutoDetalheOpiniaoResponse {
	
	private int nota;
	private String titulo;
	private String descricao;
	
	
	public ProdutoDetalheOpiniaoResponse(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}


	public int getNota() {
		return nota;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getDescricao() {
		return descricao;
	}
	
}
