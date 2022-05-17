package com.jacaranda.historial;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaginaWeb {
	private String url;
	private LocalDateTime fecha;
	
	public PaginaWeb(String url) {
		this.url = url;
		this.fecha = LocalDateTime.now();
	}
	
	public PaginaWeb(String url, LocalDateTime fecha) {
		this.url = url;
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDateTime fecha) throws HistorialException {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("Url:" + url + ", fecha:" + fecha);
		return resultado.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaginaWeb other = (PaginaWeb) obj;
		return Objects.equals(fecha, other.fecha);
	}
	
}
