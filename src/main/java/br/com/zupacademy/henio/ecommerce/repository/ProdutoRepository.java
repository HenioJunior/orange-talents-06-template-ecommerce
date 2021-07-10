package br.com.zupacademy.henio.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.henio.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
