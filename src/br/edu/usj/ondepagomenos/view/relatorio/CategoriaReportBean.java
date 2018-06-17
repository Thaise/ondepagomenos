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
import br.edu.usj.ondepagomenos.model.CategoriaModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class CategoriaReportBean implements Serializable {

	private String nome;
	private List<Categoria> categorias;
	@EJB
	private CategoriaModel categoriaModel;
	private Categoria paraDeletar;

	@PostConstruct
	public void init() {
		buscaCategoria();
	}

	public String atualiza(Integer id) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro_categoria.jsf?id=" + id);
		return null;
	}

	public void buscaCategoria() {
		try {
			categorias = categoriaModel.buscaCategorias(nome);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao realizar pesquisa", true);
		}
	}

	public void selecionaParaDeletar(Categoria c) {
		setParaDeletar(c);
	}

	public void excluir() {
		try {
			categoriaModel.excluir(paraDeletar);
			buscaCategoria();
			MensagemUtils.mostraMensagem("Categoria excluida com sucesso", false);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir Categoria", true);
		}
	}

	public void limpar() {
		nome = null;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setParaDeletar(Categoria paraDeletar) {
		this.paraDeletar = paraDeletar;
	}

	public Categoria getParaDeletar() {
		return paraDeletar;
	}

}
