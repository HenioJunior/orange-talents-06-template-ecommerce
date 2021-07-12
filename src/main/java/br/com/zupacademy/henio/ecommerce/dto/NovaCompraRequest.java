package br.com.zupacademy.henio.ecommerce.dto;

import br.com.zupacademy.henio.ecommerce.dto.enums.GatewayPagamento;
import br.com.zupacademy.henio.ecommerce.model.Produto;
import br.com.zupacademy.henio.ecommerce.validation.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    private int quantidade;

    @NotNull
    @ExistsId(fieldName = "id", domainClass = Produto.class)
    private long idProduto;
    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequest(@Positive int quantidade, @NotNull long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
