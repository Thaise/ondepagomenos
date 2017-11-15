package br.edu.usj.ondepagomenos.model;

import java.util.ArrayList;

import br.edu.usj.ondepagomenos.dao.Produto;

public class ProdutoFiltro {

	public static String montaQuery(Produto produtoFiltro) {
		String queryFinal = "select p from Produto p WHERE p.flExcluido = 0 ";
		ArrayList<String> query = new ArrayList<String>();

		if (!estaVazio(produtoFiltro.getNome())) {
			query.add("p.nome like '%" + produtoFiltro.getNome() + "%'");
		}

		if (produtoFiltro.getSupermercado().getEstado() != null) {
			query.add("p.supermercado.estado.idEstado = " + produtoFiltro.getSupermercado().getEstado().getIdEstado());
		}

		if (produtoFiltro.getSupermercado().getRegiao() != null) {
			query.add("p.supermercado.regiao.idRegiao = " + produtoFiltro.getSupermercado().getRegiao().getIdRegiao());
		}

		if (produtoFiltro.getCategoria() != null) {
			query.add("p.categoria.idCategoria = " + produtoFiltro.getCategoria().getIdCategoria());
		}

		if (!query.isEmpty()) {
			for (String s : query) {
				queryFinal += " AND " + s;
			}
		}

		return queryFinal;
	}

	private static boolean estaVazio(String valor) {
		return valor == null || valor.isEmpty();
	}

}
