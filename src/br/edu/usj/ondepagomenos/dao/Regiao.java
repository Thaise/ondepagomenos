package br.edu.usj.ondepagomenos.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "regiao")
@NamedQueries({ @NamedQuery(name = Regiao.BUSCA_GERAL, query = "select r from Regiao r"),
	@NamedQuery(name = Regiao.BUSCA_POR_ESTADO, query = "select r from Regiao r where r.estado.idEstado = :idEstado")
})
public class Regiao implements Entidade {

	public static final String BUSCA_GERAL = "buscaGeralRegiao";

	public static final String BUSCA_POR_ESTADO = "buscaRegiaoPorEstado";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_regiao")
	private Integer idRegiao;
	@Column(name = "nome")
	private String nome;
	@OneToOne
	@JoinColumn(name = "id_estado_regiao")
	private Estado estado;

	public Integer getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((idRegiao == null) ? 0 : idRegiao.hashCode());
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
		Regiao other = (Regiao) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idRegiao == null) {
			if (other.idRegiao != null)
				return false;
		} else if (!idRegiao.equals(other.idRegiao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
