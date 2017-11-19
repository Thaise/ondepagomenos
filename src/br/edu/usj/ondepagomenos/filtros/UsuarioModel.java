package br.edu.usj.ondepagomenos.filtros;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.entidades.Supermercado;
import br.edu.usj.ondepagomenos.entidades.Usuario;
import br.edu.usj.ondepagomenos.util.MD5Utils;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UsuarioModel extends AbstractModel<Usuario> {
	private EntityManager em;

	public Usuario buscaUsuario(Usuario usuario) throws Exception {
		Usuario usuarioEncontrado = null;
		try {
			usuarioEncontrado = (Usuario) getEm().createNamedQuery(Usuario.BUSCA_LOGIN)
					.setParameter("login", usuario.getLogin()).setParameter("senha", usuario.getSenha())
					.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Usuário não encontrado: " + e.getMessage());
			throw new NoResultException();
		}catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return usuarioEncontrado;
	}
	
	@Override
	public void insere(Usuario c) throws Exception {
		c.setSenha(MD5Utils.converteMD5(c.getSenha()));
		super.insere(c);
	}

	@Override
	public EntityManager getEm() {
		if (em == null)
			em = new ProdutorEntityManager().criaEntityManager();
		return em;
	}

	@Override
	public Class<Usuario> getClasseGenerica() {
		return Usuario.class;
	}

	public List<Usuario> buscaUsuarios(String nome, int flInativo) throws Exception {
		List<Usuario> usu = null;
		try {
			if (nome == null || nome.isEmpty()) {
				usu = getEm().createNamedQuery(Usuario.BUSCA_GERAL).setParameter("fl", flInativo).getResultList();
			} else {
				usu = getEm().createNamedQuery(Usuario.BUSCA_POR_NOME).setParameter("fl", flInativo)
						.setParameter("nome", "%" + nome + "%").getResultList();
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return usu;
	}

}
