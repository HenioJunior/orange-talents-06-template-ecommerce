package br.com.zupacademy.henio.ecommerce.exceptions;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String msg) {
        super(msg);
    }

}
