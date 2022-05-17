package com.jacaranda.historial;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Historial {
	private List<PaginaWeb> paginas;

	public Historial() {
		this.paginas = new LinkedList<>();
	}
	
	public void addPagina(String url) {
		PaginaWeb nuevaPagina = new PaginaWeb(url);
		this.paginas.add(nuevaPagina);
	}
	
	public void addPagina(String url, LocalDateTime fecha) throws HistorialException {
		PaginaWeb nuevaPagina = new PaginaWeb(url, fecha);
		this.paginas.add(nuevaPagina);
	}
	
	public void borrarHistorial() {
		this.paginas.removeAll(paginas);
	}

	public String consultarHistorial(LocalDateTime dia) {
		boolean resultado = false;
		StringBuilder cadena = new StringBuilder();
		for (Iterator<PaginaWeb> iterator = paginas.iterator(); iterator.hasNext() && !resultado;) {
			PaginaWeb paginaWeb = iterator.next();
			if (paginaWeb.getFecha().getYear() == dia.getYear()
					&& paginaWeb.getFecha().getMonth() == dia.getMonth()
					&& paginaWeb.getFecha().getDayOfMonth() == dia.getDayOfYear()) {
				cadena.append(paginaWeb);
				resultado = true;
			}
		}
		return cadena.toString();
	}
	
	public void ordenarPorNombre() {
		Collections.sort(this.paginas, new OrdenarPorNombre());
	}
	
	public void ordenarPoFechaDesc() {
		Collections.sort(this.paginas, new FechaDesc());
	}

	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		for(PaginaWeb aux : paginas) {
			resultado.append(aux.toString() + "\n");
		}
		return "Historial\n" + resultado.toString();
	}

	@Override
	public Historial clone() throws CloneNotSupportedException {
		Historial resultado = new Historial();
		
		for(PaginaWeb aux : paginas) {
			resultado.addPagina(aux.getUrl());
		}
		return resultado;
	}
	
}
