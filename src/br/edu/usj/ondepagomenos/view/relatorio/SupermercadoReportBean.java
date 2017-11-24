package br.edu.usj.ondepagomenos.view.relatorio;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.entidades.Estado;
import br.edu.usj.ondepagomenos.entidades.Regiao;
import br.edu.usj.ondepagomenos.entidades.Supermercado;
import br.edu.usj.ondepagomenos.model.CategoriaModel;
import br.edu.usj.ondepagomenos.model.SupermercadoModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class SupermercadoReportBean implements Serializable {

	private Supermercado supermercadoFiltro;
	private List<Estado> estados;
	private List<Regiao> regioes;
	@EJB
	private SupermercadoModel superModel;
	private Supermercado paraDeletar;
	private LazySupermercadoDataModel lazyModel;

	@PostConstruct
	public void init() {
		try {
			estados = superModel.buscaEstados();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao carregar filtros", true);
		}
		lazyModel = new LazySupermercadoDataModel(superModel, supermercadoFiltro);
		limpar();
	}

	public String atualiza(Integer id) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro_supermercado.jsf?id=" + id);
		return null;
	}

	public void selecionaParaDeletar(Supermercado c) {
		setParaDeletar(c);
	}

	public void excluir() {
		try {
			paraDeletar.setFlExcluido(1);
			superModel.atualiza(paraDeletar);
			MensagemUtils.mostraMensagem("Supermercado excluido com sucesso", false);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir Supermercado", true);
		}
	}

	public void buscaRegiaoEstado() {
		try {
			if (supermercadoFiltro.getEstado() != null)
				setRegioes(superModel.buscaRegioes(supermercadoFiltro.getEstado().getIdEstado()));
			else
				setRegioes(new ArrayList<Regiao>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		supermercadoFiltro = new Supermercado();
		buscaRegiaoEstado();
		lazyModel.setSupermercadoFiltro(supermercadoFiltro);
	}

	public void setParaDeletar(Supermercado paraDeletar) {
		this.paraDeletar = paraDeletar;
	}

	public Supermercado getParaDeletar() {
		return paraDeletar;
	}

	public Supermercado getSupermercadoFiltro() {
		return supermercadoFiltro;
	}

	public void setSupermercadoFiltro(Supermercado superFiltro) {
		this.supermercadoFiltro = superFiltro;
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

	public LazySupermercadoDataModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazySupermercadoDataModel lazyModel) {
		this.lazyModel = lazyModel;
	}

}
