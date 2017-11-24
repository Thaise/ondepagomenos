package br.edu.usj.ondepagomenos.model;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.edu.usj.ondepagomenos.entidades.Entidade;
import br.edu.usj.ondepagomenos.entidades.Produto;
import br.edu.usj.ondepagomenos.entidades.Supermercado;
import br.edu.usj.ondepagomenos.model.filtros.ProdutoFiltro;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProdutoModel extends AbstractModel<Produto> {

	private EntityManager em;

	public List<Produto> buscaProdutos() throws Exception {
		List<Produto> cats = null;
		try {
			cats = getEm().createNamedQuery(Produto.BUSCA_GERAL).getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return cats;
	}

	public List<Produto> buscaProdutos(Produto produtoFiltro) throws Exception {
		List<Produto> cats = null;
		try {
			cats = getEm().createQuery(ProdutoFiltro.montaQuery(produtoFiltro)).getResultList();
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
	public Class<Produto> getClasseGenerica() {
		return Produto.class;
	}
}
