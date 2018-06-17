package br.edu.usj.ondepagomenos.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "produto_categoria")
@NamedQueries({ @NamedQuery(name = Categoria.BUSCA_GERAL, query = "select c from Categoria c order by c.nome"),
		@NamedQuery(name = Categoria.BUSCA_POR_NOME, query = "select c from Categoria c where c.nome like :nome order by c.nome") })
public class Categoria implements Entidade {

	public static final String BUSCA_GERAL = "buscaGeral";
	public static final String BUSCA_POR_NOME = "buscaPorNome";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Integer idCategoria;
	@Column(name = "nome")
	private String nome;
	

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
