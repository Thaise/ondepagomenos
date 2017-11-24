package br.edu.usj.ondepagomenos.view;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.entidades.Entidade;
import br.edu.usj.ondepagomenos.entidades.Produto;
import br.edu.usj.ondepagomenos.model.AbstractModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

public abstract class AbstractBean<T extends Entidade> implements Serializable {

	protected boolean ehNovo = true;

	public void getIdAtualizacao() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			ehNovo = false;
			try {
				setEntidade(getModel().getPeloId(Integer.valueOf(id)));
			} catch (Exception e) {
				MensagemUtils.mostraMensagem("Erro ao buscar " + getNomeEntidade(), true);
			}
		}
	}

	public void insere() throws Exception {
		try {
			getModel().insere(getEntidade());
			limpar();
			MensagemUtils.mostraMensagem("Sucesso ao inserir " + getNomeEntidade(), false);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao inserir " + getNomeEntidade(), true);
			throw e;
		}
	}

	public void atualiza() throws Exception {
		try {
			getModel().atualiza(getEntidade());
			MensagemUtils.mostraMensagem("Sucesso ao atualizar " + getNomeEntidade(), false);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao atualizar " + getNomeEntidade(), true);
			throw e;
		}
	}

	public String excluir() {
		try {
			getModel().excluir(getEntidade());
			return cancelar();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir " + getNomeEntidade(), true);
		}
		return null;
	}

	private String getNomeEntidade() {
		return getEntidade().getClass().getSimpleName();
	}

	public boolean isEhNovo() {
		return ehNovo;
	}

	public void setEhNovo(boolean ehNovo) {
		this.ehNovo = ehNovo;
	}

	public abstract void limpar();

	public abstract String cancelar();

	protected abstract AbstractModel<T> getModel();

	protected abstract T getEntidade();

	protected abstract void setEntidade(T entidade);

}
