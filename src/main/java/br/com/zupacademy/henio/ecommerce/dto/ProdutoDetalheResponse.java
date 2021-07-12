package br.com.zupacademy.henio.ecommerce.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.henio.ecommerce.model.Produto;

public class ProdutoDetalheResponse {

	private String nome;
	private BigDecimal preco;
	private String descricao;
	private Set<ProdutoDetalheCaracteristicaResponse> caracteristicas = new HashSet<>();
	private Set<ProdutoDetalheImagemResponse> linkImagens = new HashSet<>();
	private Set<ProdutoDetalhePerguntaResponse> perguntas = new HashSet<>();
	private Set<Map<String, String>> opinioes;
	private double mediaNotas;
	private Long  totalDeNotas;

	public ProdutoDetalheResponse(Produto produto) {
		nome = produto.getNome();
		preco = produto.getValor();
		descricao = produto.getDescricao();
		caracteristicas = produto.getCaracteristicas().stream()
				.map(caract -> new ProdutoDetalheCaracteristicaResponse(caract)).collect(Collectors.toSet());
		linkImagens = produto.getImagens().stream().map(img -> new ProdutoDetalheImagemResponse(img))
				.collect(Collectors.toSet());
		perguntas = produto.getPerguntas().stream().map(perg -> new ProdutoDetalhePerguntaResponse(perg))
				.collect(Collectors.toSet());
		opinioes = produto.mapeiaOpinioes(opiniao -> {return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());});
		Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble media = notas.stream().mapToInt(nota -> nota).average();
		if(media.isPresent()){
			this.mediaNotas = media.getAsDouble();
		}
		totalDeNotas = notas.stream().mapToInt(nota -> nota).count();
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<ProdutoDetalheImagemResponse> getLinkImagens() {
		return linkImagens;
	}

	public Set<ProdutoDetalheCaracteristicaResponse> getCaracteristicasDTO() {
		return caracteristicas;
	}

	public Set<ProdutoDetalhePerguntaResponse> getPerguntas() {
		return perguntas;
	}
	
	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

	public Long getTotalDeNotas() {
		return totalDeNotas;
	}
			
}
