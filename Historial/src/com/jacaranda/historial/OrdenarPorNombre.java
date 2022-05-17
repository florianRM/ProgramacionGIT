package com.jacaranda.historial;

import java.util.Comparator;

public class OrdenarPorNombre implements Comparator<PaginaWeb> {

	@Override
	public int compare(PaginaWeb o1, PaginaWeb o2) {
		int resultado = o1.getUrl().compareTo(o2.getUrl());
		if(resultado == 0) {
			resultado = o1.getFecha().compareTo(o2.getFecha());
		}
		return resultado;
	}

}
