package br.com.zupacademy.henio.ecommerce.repository;

import br.com.zupacademy.henio.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
