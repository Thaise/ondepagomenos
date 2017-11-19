package br.edu.usj.ondepagomenos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.usj.ondepagomenos.entidades.Estado;
import br.edu.usj.ondepagomenos.entidades.Regiao;
import br.edu.usj.ondepagomenos.entidades.Supermercado;
import br.edu.usj.ondepagomenos.filtros.AbstractModel;
import br.edu.usj.ondepagomenos.filtros.SupermercadoModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class SupermercadoBean extends AbstractBean<Supermercado> {

	private List<Estado> estados;
	private List<Regiao> regioes;
	private Supermercado supermercado;
	@EJB
	private SupermercadoModel superModel;

	@PostConstruct
	public void init() {
		limpar();
		try {
			estados = superModel.buscaEstados();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao carregar dados", true);
			e.printStackTrace();
		}

		getIdAtualizacao();
	}

	@Override
	public String excluir() {
		try {
			supermercado.setFlExcluido(1);
			superModel.atualiza(supermercado);
			MensagemUtils.mostraMensagem("Supermercado excluído com sucesso", false);
			return cancelar();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir Supermercado", true);
		}
		return null;
	}

	public void buscaRegiaoEstado() {
		try {
			if (supermercado.getEstado() != null)
				setRegioes(superModel.buscaRegioes(supermercado.getEstado().getIdEstado()));
			else
				setRegioes(new ArrayList<Regiao>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String insereOuAtualiza() {
		try{
		if (supermercado.getIdSupermercado() == null) {
			insere();
		} else {
			atualiza();
		}
		return cancelar();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void limpar() {
		supermercado = new Supermercado();
		buscaRegiaoEstado();

	}

	@Override
	public String cancelar() {
		return "/admin/supermercado/pesquisa_supermercado.jsf";
	}

	@Override
	protected AbstractModel<Supermercado> getModel() {
		return superModel;
	}

	@Override
	protected Supermercado getEntidade() {
		return supermercado;
	}

	@Override
	protected void setEntidade(Supermercado entidade) {
		setSupermercado(entidade);
		buscaRegiaoEstado();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Regiao> getRegioes() {
		return regioes;
	}

	public void setRegioes(List<Regiao> regioes) {
		this.regioes = regioes;
	}

	public Supermercado getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}

}
