package br.com.zupacademy.henio.ecommerce.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotBlank
    @Email
    private String email;
    @Size(min = 6)
    private String senha;

    public LoginForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
