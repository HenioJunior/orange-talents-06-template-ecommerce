package br.com.zupacademy.henio.ecommerce.dto;

import static br.com.zupacademy.henio.ecommerce.controller.util.UsuarioAutenticado.authenticated;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.henio.ecommerce.controller.exceptions.AuthorizationException;
import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Pergunta;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;
	@NotBlank
	@Email
	private String email;
	
	public NovaPerguntaRequest(@NotBlank String titulo, @NotBlank @Email String email) {
		this.titulo = titulo;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Perguntas [titulo=" + titulo + ", email=" + email + "]";
	}

	public Pergunta toModel(@NotNull @Valid Produto produto, @NotNull @Valid UsuarioRepository repository) {
		Usuario usuarioAutenticado = authenticated();
		Optional<Usuario> obj = repository.findByEmail(email);
		Usuario usuario = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
		
		if (!(usuarioAutenticado.equals(usuario))) {
			throw new AuthorizationException("Acesso negado");
		}
		return new Pergunta(titulo, usuario, produto);
	}
}
