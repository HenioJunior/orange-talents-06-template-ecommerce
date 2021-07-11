package br.com.zupacademy.henio.ecommerce.dto.request;

import static br.com.zupacademy.henio.ecommerce.controller.util.UsuarioAutenticado.authenticated;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.henio.ecommerce.controller.exceptions.AuthorizationException;
import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Pergunta;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;
import br.com.zupacademy.henio.ecommerce.validation.ExistsId;

public class PerguntaRequest {

	@NotBlank
	private String titulo;
	@NotBlank
	@Email
	private String email;
	@ExistsId(domainClass = Produto.class, fieldName = "id")
	@NotNull
	private long idProduto;

	public PerguntaRequest(@NotBlank String titulo, @NotBlank @Email String email, @NotNull long idProduto) {
		this.titulo = titulo;
		this.email = email;
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "Nova Pergunta [titulo=" + titulo + ", email=" + email + ", idProduto=" + idProduto + "]";
	}

	public Pergunta toModel(EntityManager manager, UsuarioRepository repository) {
		Usuario usuarioAutenticado = authenticated();
		Optional<Usuario> obj = repository.findByEmail(email);
		Usuario usuario = obj.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
		
		if (!(usuarioAutenticado.equals(usuario))) {
			throw new AuthorizationException("Acesso negado");
		}

		Produto produto = manager.find(Produto.class, idProduto);
		return new Pergunta(titulo, usuario, produto);
	}
}
