package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.henio.ecommerce.model.Pergunta;

public class ProdutoDetalhePerguntaResponse {
	
	private String titulo;
	private String usuario;
	
	public ProdutoDetalhePerguntaResponse(@NotNull @Valid Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.usuario = pergunta.getUsuario().getUsername();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUsuario() {
		return usuario;
	}
}
