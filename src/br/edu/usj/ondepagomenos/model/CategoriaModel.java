package br.edu.usj.ondepagomenos.model;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import br.edu.usj.ondepagomenos.dao.Categoria;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CategoriaModel extends AbstractModel<Categoria> {

	private EntityManager em;

	public List<Categoria> buscaCategorias(String nome) throws Exception {
		List<Categoria> cats = null;
		try {
			if (nome == null || nome.isEmpty()) {
				cats = getEm().createNamedQuery(Categoria.BUSCA_GERAL).getResultList();
			} else {
				cats = getEm().createNamedQuery(Categoria.BUSCA_POR_NOME).setParameter("nome", "%" + nome + "%")
						.getResultList();
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return cats;
	}

	@Override
	public EntityManager getEm() {
		if (em == null)
			em = new ProdutorEntityManager().criaEntityManager();
		return em;
	}

	@Override
	public Class<Categoria> getClasseGenerica() {
		return Categoria.class;
	}
}
