package br.com.zupacademy.henio.ecommerce.sistemasexternos;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    public NovaCompraRankingRequest(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "NovaCompraRankingRequest{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }
}
