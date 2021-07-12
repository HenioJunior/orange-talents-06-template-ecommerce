package br.com.zupacademy.henio.ecommerce.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.henio.ecommerce.model.Opiniao;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

public class NovaOpiniaoRequest {
	
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;
		
	public NovaOpiniaoRequest(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Opiniões [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}


	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid UsuarioRepository usuarioRepository) {
		Usuario consumidor = usuarioRepository.findByEmail("alex@gmail.com").get();
		return new Opiniao(nota, titulo, descricao, produto, consumidor);
	}
}
