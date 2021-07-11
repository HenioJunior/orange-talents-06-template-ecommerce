package br.com.zupacademy.henio.ecommerce.dto.request;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Opiniao;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;

public class OpiniaoRequest {
	
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	@NotNull
	private long idProduto;
	
	public OpiniaoRequest(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull long idProduto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "Opiniao [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", produto="
				+ idProduto + "]";
	}

	
	public Opiniao toModel(@NotNull @Valid ProdutoRepository produtoRepository, @NotNull @Valid UsuarioRepository usuarioRepository) {
		Usuario consumidor = usuarioRepository.findByEmail("alex@gmail.com").get();
		Optional<Produto> obj = produtoRepository.findById(idProduto);
	      Produto produto = obj.orElseThrow(() -> new EntityNotFoundException("Id do produto não encontrado."));
		return new Opiniao(nota, titulo, descricao, produto, consumidor);
	}
		
}
