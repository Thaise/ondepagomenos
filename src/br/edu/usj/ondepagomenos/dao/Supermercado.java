package br.edu.usj.ondepagomenos.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="supermercado")
@NamedQuery(name = Supermercado.BUSCA_GERAL, query = "select s from Supermercado s where s.flExcluido = 0 order by s.nome")
public class Supermercado implements Entidade{

	public static final String BUSCA_GERAL = "buscaGeralSuper";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_supermercado")
	private Integer idSupermercado;
	@Column(name="nome")
	private String nome;
	@Column(name="site")
	private String site;
	@Column(name = "fl_excluido")
	private int flExcluido;
	@OneToOne
	@JoinColumn(name="id_estado_super")
	private Estado estado;
	@OneToOne
	@JoinColumn(name="id_regiao_super")
	private Regiao regiao;
	public Integer getIdSupermercado() {
		return idSupermercado;
	}
	public void setIdSupermercado(Integer idSupermercado) {
		this.idSupermercado = idSupermercado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public int getFlExcluido() {
		return flExcluido;
	}
	public void setFlExcluido(int flExcluido) {
		this.flExcluido = flExcluido;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + flExcluido;
		result = prime * result + ((idSupermercado == null) ? 0 : idSupermercado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((regiao == null) ? 0 : regiao.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
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
		Supermercado other = (Supermercado) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (flExcluido != other.flExcluido)
			return false;
		if (idSupermercado == null) {
			if (other.idSupermercado != null)
				return false;
		} else if (!idSupermercado.equals(other.idSupermercado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (regiao == null) {
			if (other.regiao != null)
				return false;
		} else if (!regiao.equals(other.regiao))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		return true;
	}
	
	

	
	
	
}
