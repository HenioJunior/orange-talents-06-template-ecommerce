package br.com.zupacademy.henio.ecommerce.controller;

import br.com.zupacademy.henio.ecommerce.dto.request.CategoriaRequest;
import br.com.zupacademy.henio.ecommerce.model.Categoria;
import br.com.zupacademy.henio.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCategoria(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = request.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().body("Categoria cadastrada");
    }
}
