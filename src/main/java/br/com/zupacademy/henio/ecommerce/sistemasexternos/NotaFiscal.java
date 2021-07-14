package br.com.zupacademy.henio.ecommerce.sistemasexternos;

import java.util.Map;

import br.com.zupacademy.henio.ecommerce.model.Compra;
import br.com.zupacademy.henio.ecommerce.model.EventoCompraSucesso;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscal implements EventoCompraSucesso {

	@Override
	public void processa(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(),"Compra nao processada "+compra);
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("--------------------------------------");
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idComprador", compra.getComprador().getId());

		restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
				request, String.class);

	}
}
