package com.jacaranda.principal;

import java.time.LocalDateTime;

import com.jacaranda.historial.Historial;
import com.jacaranda.historial.HistorialException;

public class Principal {

	public static void main(String[] args) throws HistorialException {
		Historial h = new Historial();
		h.addPagina("www.google.es");
		h.addPagina("www.bbva.com");
		h.addPagina("www.marca.es");
		h.addPagina("www.google.es", LocalDateTime.now().minusDays(1));
		
		System.out.println(h.toString());
		h.ordenarPorNombre();
		System.out.println(h.toString());
		h.ordenarPoFechaDesc();
		System.out.println(h.toString());
	}

}
