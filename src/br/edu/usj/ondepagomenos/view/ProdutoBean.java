package br.edu.usj.ondepagomenos.view;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.entidades.Produto;
import br.edu.usj.ondepagomenos.entidades.Supermercado;
import br.edu.usj.ondepagomenos.model.AbstractModel;
import br.edu.usj.ondepagomenos.model.CategoriaModel;
import br.edu.usj.ondepagomenos.model.ProdutoModel;
import br.edu.usj.ondepagomenos.model.SupermercadoModel;
import br.edu.usj.ondepagomenos.util.MensagemUtils;

@ViewScoped
@ManagedBean
public class ProdutoBean extends AbstractBean<Produto> {

	private Produto produto;
	private List<Categoria> categorias;
	private List<Supermercado> supermercados;
	@EJB
	private CategoriaModel categoriaModel;
	@EJB
	private SupermercadoModel superModel;
	@EJB
	private ProdutoModel produtoModel;

	@PostConstruct
	public void init() {
		limpar();
		try {
			categorias = categoriaModel.buscaCategorias(null);
			supermercados = superModel.buscaSupermercados();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao carregar dados", true);
			e.printStackTrace();
		}

		getIdAtualizacao();
	}

	public String insereOuAtualiza() {
		try {
			if (!possuiErroDatas()) {
				if (produto.getIdProduto() == null) {
					insere();
				} else {
					atualiza();
				}
				return cancelar();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean possuiErroDatas() {
		boolean erro = false;

		if (produto.getPreco() <= 0) {
			MensagemUtils.mostraMensagem("'Preço' deve ser maior que zero", true);
			erro = true;
		}

		if (produto.getDtFimVigencia().before(new Date())) {
			MensagemUtils.mostraMensagem("'Data fim de vigência' deve ser posterior à data de hoje", true);
			erro = true;
		} else if (produto.getDtFimVigencia().before(produto.getDtInicioVigencia())) {
			MensagemUtils.mostraMensagem("'Data fim de vigência' deve ser posterior à 'Data início de vigência'", true);
			erro = true;
		}

		return erro;
	}

	@Override
	public String excluir() {
		try {
			produto.setFlExcluido(1);
			produtoModel.atualiza(produto);
			MensagemUtils.mostraMensagem("Produto excluído com sucesso", false);
			return cancelar();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem("Erro ao excluir Produto", true);
		}
		return null;
	}

	public void limpar() {
		produto = new Produto();
	}

	public String cancelar() {
		return "/admin/produto/pesquisa_produto.jsf";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Supermercado> getSupermercados() {
		return supermercados;
	}

	public void setSupermercados(List<Supermercado> supermercado) {
		this.supermercados = supermercado;
	}

	@Override
	protected AbstractModel<Produto> getModel() {
		return produtoModel;
	}

	@Override
	protected Produto getEntidade() {
		return produto;
	}

	@Override
	protected void setEntidade(Produto entidade) {
		setProduto(entidade);
	}

}
