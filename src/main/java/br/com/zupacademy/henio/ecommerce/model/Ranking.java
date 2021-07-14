package br.com.zupacademy.henio.ecommerce.model;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso{

	@Override
	public void processa(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(),"Compra nao processada "+compra);
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("--------------------------------------");
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idDonoProduto", compra.getDonoProduto().getId());

		restTemplate.postForEntity("http://localhost:8080/ranking",
				request, String.class);
	}
}
