package br.com.zupacademy.henio.ecommerce.sistemasexternos;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henio.ecommerce.sistemasexternos.NovaCompraNFRequest;
import br.com.zupacademy.henio.ecommerce.sistemasexternos.NovaCompraRankingRequest;

@RestController
public class SistemasExternos {
	
	@PostMapping(value = "/notas-fiscais")
	public void criaNota(@Valid @RequestBody NovaCompraNFRequest request) throws InterruptedException {
		System.out.println("criando nota "+request);
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(@Valid @RequestBody NovaCompraRankingRequest request) throws InterruptedException {
		System.out.println("criando ranking"+request);
		Thread.sleep(150);
	}
}
