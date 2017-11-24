package br.edu.usj.ondepagomenos.model.filtros;

import java.util.ArrayList;

import br.edu.usj.ondepagomenos.entidades.Supermercado;

public class SupermercadoFiltro {

	public static String montaQuery(Supermercado superFiltro) {
		String queryFinal = "select s from Supermercado s WHERE s.flExcluido = 0 ";
		ArrayList<String> query = new ArrayList<String>();

		if (!estaVazio(superFiltro.getNome())) {
			query.add("s.nome like '%" + superFiltro.getNome() + "%'");
		}

		if (superFiltro.getEstado() != null) {
			query.add("s.estado.idEstado = " + superFiltro.getEstado().getIdEstado());
		}

		if (superFiltro.getRegiao() != null) {
			query.add("s.regiao.idRegiao = " + superFiltro.getRegiao().getIdRegiao());
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
