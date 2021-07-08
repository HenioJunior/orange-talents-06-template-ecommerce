package br.com.zupacademy.henio.ecommerce.dto.request;

import br.com.zupacademy.henio.ecommerce.model.Usuario;
import br.com.zupacademy.henio.ecommerce.validation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

   @NotBlank
    private String nome;
   @Email
   @NotBlank
   @UniqueValue(fieldName = "email", domainClass = Usuario.class)
   private String email;
   @NotBlank
   @Size(min = 6)
   private String senha;

    public UsuarioRequest(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.nome, this.email, this.senha);
    }
}
