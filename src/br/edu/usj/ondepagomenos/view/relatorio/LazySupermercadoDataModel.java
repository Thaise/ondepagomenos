package br.edu.usj.ondepagomenos.view.relatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.usj.ondepagomenos.entidades.Supermercado;
import br.edu.usj.ondepagomenos.model.SupermercadoModel;

public class LazySupermercadoDataModel extends LazyDataModel<Supermercado> {

	private List<Supermercado> datasource;
	private Supermercado superFiltro;
	private SupermercadoModel superModel;

	public LazySupermercadoDataModel(SupermercadoModel superModel, Supermercado superFiltro) {
		this.superModel = superModel;
		this.superFiltro = superFiltro;
	}

	@Override
	public Supermercado getRowData(String rowKey) {
		for (Supermercado car : datasource) {
			if (car.getIdSupermercado().equals(rowKey))
				return car;
		}

		return null;
	}

	@Override
	public Object getRowKey(Supermercado car) {
		return car.getIdSupermercado();
	}

	@Override
	public List<Supermercado> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		try {
			List<Supermercado> data = superModel.buscaSupermercados(superFiltro);
			
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
	
	public void setSupermercadoFiltro(Supermercado superFiltro) {
		this.superFiltro = superFiltro;
	}
}