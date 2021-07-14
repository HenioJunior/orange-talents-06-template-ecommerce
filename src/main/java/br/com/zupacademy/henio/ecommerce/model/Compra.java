package br.com.zupacademy.henio.ecommerce.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.henio.ecommerce.dto.RetornoGatewayPagamento;
import br.com.zupacademy.henio.ecommerce.enums.GatewayPagamento;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoEscolhido;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(@Valid @NotNull Produto produto, @Positive int quantidade, @Valid @NotNull Usuario comprador,
                  @NotNull GatewayPagamento gateway) {
        this.produtoEscolhido = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getDonoProduto() {
        return produtoEscolhido.getDono();
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Long getId() {
        return id;
    }
        
    public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public void adicionaTransacao(@Valid @NotNull RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
               
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
        		"Já existe uma transaco igual a essa processada "
        				+novaTransacao);
        
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),
        		"Essa compra já foi concluida com sucesso");

        this.transacoes.add(novaTransacao);
    }

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao :: concluidaComSucesso)
                .collect(Collectors.toSet());
		Assert.isTrue(transacoesConcluidasComSucesso.size() <=1, "Erro: Tem mais de uma transação concluida para a compra");
		return transacoesConcluidasComSucesso;
	}

 	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compra [Nº do pedido: ");
		builder.append(id);
		builder.append(", Quantidade: ");
		builder.append(quantidade);
		builder.append(", Produto Escolhido: ");
		builder.append(produtoEscolhido.getNome());
		builder.append(", Comprador: ");
		builder.append(comprador.getUsername());
		builder.append(", Forma de pagamento: ");
		builder.append(gateway);
		builder.append("]");
		return builder.toString();
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}
}
