package br.com.zupacademy.henio.ecommerce.dto.request;

import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Categoria;
import br.com.zupacademy.henio.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.henio.ecommerce.validation.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    @NotNull
    @Positive
    private long idCategoriaMae;

    public CategoriaRequest(String nome, long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public void setIdCategoriaMae(long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toModel(CategoriaRepository repository) {
        Optional<Categoria> obj = repository.findById(idCategoriaMae);
        Categoria categoriaMae = obj.orElseThrow(() -> new EntityNotFoundException("Id da categoria não encontrado."));
        return new Categoria(nome, categoriaMae);
    }
}
