package br.com.zupacademy.henio.ecommerce.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.henio.ecommerce.model.Categoria;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;
import br.com.zupacademy.henio.ecommerce.validation.ExistsId;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;

	@Positive
	@NotNull
	private BigDecimal preco;

	@PositiveOrZero
	@NotNull
	private int quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private long idCategoria;

	@Size(min = 3)
	@Valid
	private List<NovoProdutoCaracteristicaRequest> caracteristicas = new ArrayList<>();

	public NovoProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal preco,
			@PositiveOrZero @NotNull int quantidade, @NotBlank @Size(max = 1000) String descricao,
			@NotNull long idCategoria, @Size(min = 3) @Valid List<NovoProdutoCaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}
		
	public List<NovoProdutoCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}
		
	public Produto toModel(@NotNull @Valid EntityManager manager, @NotNull @Valid UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.findByEmail("alex@gmail.com").get();
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		return new Produto(nome, preco, quantidade, descricao, categoria, usuario, caracteristicas);
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (NovoProdutoCaracteristicaRequest c : caracteristicas) {
			String nome = c.getNome();
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

	@Override
	public String toString() {
		return "ProdutoRequest [nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}
}
