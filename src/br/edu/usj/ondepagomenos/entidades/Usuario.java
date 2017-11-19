package br.edu.usj.ondepagomenos.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = Usuario.BUSCA_LOGIN, query = "select u from Usuario u where u.flExcluido = 0 and u.login = :login and u.senha = :senha"),
		@NamedQuery(name = Usuario.BUSCA_GERAL, query = "select u from Usuario u where u.flExcluido = :fl"),
		@NamedQuery(name = Usuario.BUSCA_POR_NOME, query = "select u from Usuario u where u.flExcluido = :fl and u.nome like :nome") })

public class Usuario implements Entidade {

	public static final String BUSCA_LOGIN = "buscaLoginUsuario";
	public static final String BUSCA_GERAL = "buscaGeralUsuario";
	public static final String BUSCA_POR_NOME = "buscaUsuarioPorNome";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "login")
	private String login;
	@Column(name = "senha")
	private String senha;
	@Column(name = "fl_excluido")
	private int flExcluido;
	@Enumerated(EnumType.ORDINAL)
	private Perfil perfil;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getFlExcluido() {
		return flExcluido;
	}

	public void setFlExcluido(int flExcluido) {
		this.flExcluido = flExcluido;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
