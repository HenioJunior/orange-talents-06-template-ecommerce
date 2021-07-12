package br.com.zupacademy.henio.ecommerce.dto;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.henio.ecommerce.model.Categoria;
import br.com.zupacademy.henio.ecommerce.validation.ExistsId;
import br.com.zupacademy.henio.ecommerce.validation.UniqueValue;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    @NotNull
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private long idCategoriaMae;

    public NovaCategoriaRequest(@NotBlank String nome, @NotNull @Positive long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
    
    public String getNome() {
		return nome;
	}
	
    public long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public Categoria toModel(@NotNull @Valid EntityManager manager) {
        Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
        return new Categoria(nome, categoriaMae);
    }

	@Override
	public String toString() {
		return "Categoria criada [nome=" + nome + ", idCategoria=" + idCategoriaMae + "]";
	}
	   
}
