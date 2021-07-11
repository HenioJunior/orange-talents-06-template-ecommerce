package br.com.zupacademy.henio.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.henio.ecommerce.dto.request.CaracteristicaProdutoRequest;
import io.jsonwebtoken.lang.Assert;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	@Valid
	@ManyToOne
	private Categoria categoria;
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@PastOrPresent
	private LocalDateTime instanteDaCriacao = LocalDateTime.now();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor, @PositiveOrZero @NotNull int quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotNull @Valid Categoria categoria, @NotNull @Valid Usuario usuario, @Size(min = 3) @Valid Collection<CaracteristicaProdutoRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas
				.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		Assert.isTrue(this.caracteristicas.size() >=3, "Todo produto precisa ter no mínimo 3 ou mais características.");
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	@Override
	public String toString() {
		return "Produto{" +
				"nome='" + nome + '\'' +
				", valor=" + valor +
				", quantidade=" + quantidade +
				", descricao='" + descricao + '\'' +
				", categoria=" + categoria +
				", usuario=" + usuario +
				", instanteDaCriacao=" + instanteDaCriacao +
				", caracteristicas=" + caracteristicas +
				", imagens=" + imagens +
				'}';
	}
}
