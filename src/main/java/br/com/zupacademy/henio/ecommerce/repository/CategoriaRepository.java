package br.com.zupacademy.henio.ecommerce.repository;

import br.com.zupacademy.henio.ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
