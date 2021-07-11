package br.com.zupacademy.henio.ecommerce.controller.util;

import br.com.zupacademy.henio.ecommerce.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioAutenticado {

    public static Usuario authenticated() {
        try {
            return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}
