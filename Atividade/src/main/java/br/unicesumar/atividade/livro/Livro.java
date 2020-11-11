package br.unicesumar.atividade.livro;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Livro")
public class Livro {
	public String id;
	private String titulo;
	private String autor;
	private BigDecimal preco;

    public static final String CACHE_NAME = "Livro";
	
	public Livro(String titulo, String autor, BigDecimal preco) {
		this();
		this.titulo = titulo;
		this.autor = autor;
		this.preco = preco;
	}

	public Livro() {
		id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
