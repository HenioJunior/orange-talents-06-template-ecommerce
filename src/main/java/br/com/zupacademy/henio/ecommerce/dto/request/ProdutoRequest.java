package br.com.zupacademy.henio.ecommerce.dto.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Categoria;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.henio.ecommerce.repository.UsuarioRepository;
import br.com.zupacademy.henio.ecommerce.validation.ExistsId;

public class ProdutoRequest {

	@NotBlank
	private String nome;

	@Positive
	@NotNull
	private BigDecimal valor;

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
	private List<CaracteristicaProdutoRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
			@PositiveOrZero @NotNull int quantidade, @NotBlank @Size(max = 1000) String descricao,
			@NotNull long idCategoria, @Size(min = 3) @Valid List<CaracteristicaProdutoRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}
		
	public List<CaracteristicaProdutoRequest> getCaracteristicas() {
		return caracteristicas;
	}
		
	public Produto toModel(CategoriaRepository repository, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.findByEmail("alex@gmail.com").get();
		Optional<Categoria> obj = repository.findById(idCategoria);
		Categoria categoria = obj.orElseThrow(() -> new EntityNotFoundException("Id da categoria não encontrado."));
		return new Produto(nome, valor, quantidade, descricao, categoria, usuario, caracteristicas);
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (CaracteristicaProdutoRequest c : caracteristicas) {
			String nome = c.getNome();
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

	@Override
	public String toString() {
		return "ProdutoRequest [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}
}
