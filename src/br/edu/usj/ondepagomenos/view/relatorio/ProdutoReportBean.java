package br.edu.usj.ondepagomenos.view.relatorio;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.usj.ondepagomenos.dao.Categoria;
import br.edu.usj.ondepagomenos.dao.Estado;
import br.edu.usj.ondepagomenos.dao.Produto;
import br.edu.usj.ondepagomenos.dao.Regiao;
import br.edu.usj.ondepagomenos.dao.Supermercado;
import br.edu.usj.ondepagomenos.model.CategoriaModel;
import br.edu.usj.ondepagomenos.model.ProdutoModel;
import br.edu.usj.ondepagomenos.model.SupermercadoModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class ProdutoReportBean implements Serializable {

	private Produto produtoFiltro;
	private List<Categoria> categorias;
	private List<Estado> estados;
	private List<Regiao> regioes;
	@EJB
	private ProdutoModel produtoModel;
	@EJB
	private CategoriaModel categoriaModel;
	@EJB
	private SupermercadoModel superModel;
	private Produto paraDeletar;
	private LazyProdutoDataModel lazyModel;

	@PostConstruct
	public void init() {
		try {
			categorias = categoriaModel.buscaCategorias(null);
			estados = superModel.buscaEstados();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao carregar filtros", true);
		}
		lazyModel = new LazyProdutoDataModel(produtoModel, produtoFiltro);
		limpar();
	}

	public String atualiza(Integer id) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro_produto.jsf?id=" + id);
		return null;
	}

	public void selecionaParaDeletar(Produto c) {
		setParaDeletar(c);
	}

	public void excluir() {
		try {
			paraDeletar.setFlExcluido(1);
			produtoModel.atualiza(paraDeletar);
			MensagemUtils.mostraMensagem("Produto excluido com sucesso", false);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir Produto", true);
		}
	}

	public void buscaRegiaoEstado() {
		try {
			setRegioes(superModel.buscaRegioes(produtoFiltro.getSupermercado().getEstado().getIdEstado()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		produtoFiltro = new Produto();
		produtoFiltro.setSupermercado(new Supermercado());
		produtoFiltro.getSupermercado().setEstado(estados.get(23));//SC
		buscaRegiaoEstado();

		lazyModel.setProdutoFiltro(produtoFiltro);
	}

	public void setParaDeletar(Produto paraDeletar) {
		this.paraDeletar = paraDeletar;
	}

	public Produto getParaDeletar() {
		return paraDeletar;
	}

	public Produto getProdutoFiltro() {
		return produtoFiltro;
	}

	public void setProdutoFiltro(Produto produtoFiltro) {
		this.produtoFiltro = produtoFiltro;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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

	public LazyProdutoDataModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyProdutoDataModel lazyModel) {
		this.lazyModel = lazyModel;
	}

}
