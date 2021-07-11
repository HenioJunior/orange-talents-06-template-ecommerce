package br.com.zupacademy.henio.ecommerce.dto.request;

import br.com.zupacademy.henio.ecommerce.exceptions.EntityNotFoundException;
import br.com.zupacademy.henio.ecommerce.model.Categoria;
import br.com.zupacademy.henio.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.henio.ecommerce.validation.UniqueValue;

import javax.validation.Valid;
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

    public CategoriaRequest(@NotBlank String nome, @NotNull @Positive long idCategoriaMae) {
		super();
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
    
    public String getNome() {
		return nome;
	}

	public long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public Categoria toModel(@NotNull @Valid CategoriaRepository repository) {
        Optional<Categoria> obj = repository.findById(idCategoriaMae);
        Categoria categoriaMae = obj.orElseThrow(() -> new EntityNotFoundException("Id da categoria não encontrado."));
        return new Categoria(nome, categoriaMae);
    }

	@Override
	public String toString() {
		return "Categoria criada [nome=" + nome + ", idCategoria=" + idCategoriaMae + "]";
	}
	   
}
