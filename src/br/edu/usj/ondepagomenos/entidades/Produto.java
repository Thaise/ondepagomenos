package br.edu.usj.ondepagomenos.entidades;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "produto")
@NamedQuery(name = Produto.BUSCA_GERAL, query = "select p from Produto p where p.flExcluido = 0 order by p.dtFimVigencia")
public class Produto implements Entidade {

	public static final String BUSCA_GERAL = "buscaGeralProd";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer idProduto;
	@Column(name = "nome")
	private String nome;
	@Column(name = "marca")
	private String marca;
	@Column(name = "preco")
	private Double preco;
	@Column(name = "data_inicio_vigencia")
	private Date dtInicioVigencia;
	@Column(name = "data_fim_vigencia")
	private Date dtFimVigencia;
	@Column(name = "fl_excluido")
	private int flExcluido;
	@OneToOne
	@JoinColumn(name = "id_categoria_prod")
	private Categoria categoria;
	@OneToOne
	@JoinColumn(name = "id_super_prod")
	private Supermercado supermercado;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public int isFlExcluido() {
		return flExcluido;
	}

	public void setFlExcluido(int flExcluido) {
		this.flExcluido = flExcluido;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Supermercado getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dtFimVigencia == null) ? 0 : dtFimVigencia.hashCode());
		result = prime * result + ((dtInicioVigencia == null) ? 0 : dtInicioVigencia.hashCode());
		result = prime * result + flExcluido;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((supermercado == null) ? 0 : supermercado.hashCode());
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
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dtFimVigencia == null) {
			if (other.dtFimVigencia != null)
				return false;
		} else if (!dtFimVigencia.equals(other.dtFimVigencia))
			return false;
		if (dtInicioVigencia == null) {
			if (other.dtInicioVigencia != null)
				return false;
		} else if (!dtInicioVigencia.equals(other.dtInicioVigencia))
			return false;
		if (flExcluido != other.flExcluido)
			return false;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (supermercado == null) {
			if (other.supermercado != null)
				return false;
		} else if (!supermercado.equals(other.supermercado))
			return false;
		return true;
	}

}
