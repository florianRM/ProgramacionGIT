package com.jacaranda.historial;

import java.util.Comparator;

public class FechaDesc implements Comparator<PaginaWeb> {

	@Override
	public int compare(PaginaWeb o1, PaginaWeb o2) {
		return -o1.getFecha().compareTo(o2.getFecha());
	}

}
