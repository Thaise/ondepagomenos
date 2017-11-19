package br.edu.usj.ondepagomenos.filtros;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.edu.usj.ondepagomenos.entidades.Estado;
import br.edu.usj.ondepagomenos.entidades.Produto;
import br.edu.usj.ondepagomenos.entidades.Regiao;
import br.edu.usj.ondepagomenos.entidades.Supermercado;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SupermercadoModel extends AbstractModel<Supermercado> {

	private EntityManager em;

	public List<Supermercado> buscaSupermercados() throws Exception {
		List<Supermercado> supers = null;
		try {
			supers = getEm().createNamedQuery(Supermercado.BUSCA_GERAL).getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return supers;
	}

	public List<Supermercado> buscaSupermercados(Supermercado superFiltro) throws Exception {
		List<Supermercado> cats = null;
		try {
			cats = getEm().createQuery(SupermercadoFiltro.montaQuery(superFiltro)).getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return cats;
	}

	public List<Estado> buscaEstados() throws Exception {
		List<Estado> estados = null;
		try {
			estados = getEm().createNamedQuery(Estado.BUSCA_GERAL).getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return estados;
	}

	public List<Regiao> buscaRegioes() throws Exception {
		List<Regiao> regioes = null;
		try {
			regioes = getEm().createNamedQuery(Regiao.BUSCA_GERAL).getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return regioes;
	}

	public List<Regiao> buscaRegioes(Integer idEstado) throws Exception {
		List<Regiao> regioes = null;
		try {
			regioes = getEm().createNamedQuery(Regiao.BUSCA_POR_ESTADO).setParameter("idEstado", idEstado)
					.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar dado: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return regioes;
	}

	@Override
	public EntityManager getEm() {
		if (em == null)
			em = new ProdutorEntityManager().criaEntityManager();
		return em;
	}

	@Override
	public Class<Supermercado> getClasseGenerica() {
		return Supermercado.class;
	}

}
