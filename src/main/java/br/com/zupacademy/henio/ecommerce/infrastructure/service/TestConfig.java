package br.com.zupacademy.henio.ecommerce.infrastructure.service;

import br.com.zupacademy.henio.ecommerce.infrastructure.EmailService;
import br.com.zupacademy.henio.ecommerce.infrastructure.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}

