package br.edu.usj.ondepagomenos.view.relatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.usj.ondepagomenos.entidades.Produto;
import br.edu.usj.ondepagomenos.model.ProdutoModel;

public class LazyProdutoDataModel extends LazyDataModel<Produto> {

	private List<Produto> datasource;
	private Produto produtoFiltro;
	private ProdutoModel produtoModel;

	public LazyProdutoDataModel(ProdutoModel produtoModel, Produto produtoFiltro) {
		this.produtoModel = produtoModel;
		this.produtoFiltro = produtoFiltro;
	}

	@Override
	public Produto getRowData(String rowKey) {
		for (Produto car : datasource) {
			if (car.getIdProduto().equals(rowKey))
				return car;
		}

		return null;
	}

	@Override
	public Object getRowKey(Produto car) {
		return car.getIdProduto();
	}

	@Override
	public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		try {
			List<Produto> data = produtoModel.buscaProdutos(produtoFiltro);
			
			// rowCount
			int dataSize = data.size();
			this.setRowCount(dataSize);

			// paginate
			if (dataSize > pageSize) {
				try {
					return data.subList(first, first + pageSize);
				} catch (IndexOutOfBoundsException e) {
					return data.subList(first, first + (dataSize % pageSize));
				}
			} else {
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setProdutoFiltro(Produto produtoFiltro) {
		this.produtoFiltro = produtoFiltro;
	}
}