package br.edu.usj.ondepagomenos.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.filtros.AbstractModel;
import br.edu.usj.ondepagomenos.filtros.CategoriaModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class CategoriaBean extends AbstractBean<Categoria> {

	private Categoria categoria;
	@EJB
	private CategoriaModel categoriaModel;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		getIdAtualizacao();
	}

	public String insereOuAtualiza() {
		try {
			if (categoria.getIdCategoria() == null) {
				insere();
			} else {
				atualiza();
			}
			return cancelar();
		} catch (Exception e) {
			return null;
		}
	}

	public void limpar() {
		categoria = new Categoria();
	}

	public String cancelar() {
		return "/admin/categoria/pesquisa_categoria.jsf";
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	protected AbstractModel<Categoria> getModel() {
		return categoriaModel;
	}

	@Override
	protected Categoria getEntidade() {
		return categoria;
	}

	@Override
	protected void setEntidade(Categoria entidade) {
		setCategoria(entidade);
	}

}
