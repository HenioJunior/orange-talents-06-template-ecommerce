package br.com.zupacademy.henio.ecommerce.model;

import br.com.zupacademy.henio.ecommerce.dto.enums.GatewayPagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_compra")
public class
Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private int quantidade;

   @ManyToOne
   @NotNull
   @Valid
    private Produto produto;

   @ManyToOne
   @NotNull
   @Valid
   private Usuario comprador;

   @Enumerated
   @NotNull
    private GatewayPagamento gateway;

    public Compra(@Valid @NotNull Produto produto, @Positive int quantidade, @Valid @NotNull Usuario comprador, @NotNull GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "quantidade=" + quantidade +
                ", produto=" + produto +
                ", comprador=" + comprador +
                '}';
    }
}
