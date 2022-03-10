package com.jacaranda.notas;

import java.time.LocalDateTime;
import java.util.Objects;

public class Nota implements Comparable <Nota>{
	private static int codigoSiguiente = 1;
	private int codigo;
	private String texto;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaUltimaModificacion;
	private boolean modificado;
	
	public Nota(String texto) {
		this.texto = texto;
		this.codigo = Nota.codigoSiguiente++;
		this.fechaCreacion = LocalDateTime.now();
		this.fechaUltimaModificacion = LocalDateTime.now();
		this.modificado = false;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		this.fechaUltimaModificacion = LocalDateTime.now();
		this.modificado = true;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	
	public boolean isModificado() {
		return this.modificado;
	}
	
	public boolean isEmpty() {
		boolean empty = false;
		if (this.texto.equals("")) {
			empty = true;
		}
		return empty;
	}
	
	public boolean isCreadoAnterior(Nota nota) {
		boolean creado = false;
		if (this.fechaCreacion.isBefore(nota.getFechaCreacion())) {
			creado = true;
		}
		return creado;
	}
	
	public boolean isModificadoAnterio(Nota nota) {
		boolean modificadoAntes = false;
		if (this.fechaUltimaModificacion.isBefore(nota.getFechaUltimaModificacion())) {
			modificadoAntes = true;
		}
		return modificadoAntes;
	}
	
	@Override
	public String toString() {
		return "Nota\nCodigo=" + codigo + ", texto=" + texto + ", fechaCreacion=" + fechaCreacion
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaCreacion, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(fechaCreacion, other.fechaCreacion) && Objects.equals(texto, other.texto);
	}

	@Override
	public int compareTo(Nota o) {
		int resultado;
		resultado = this.texto.compareToIgnoreCase(o.getTexto());
		
		if (resultado == 0) {
			resultado = this.fechaCreacion.compareTo(o.getFechaCreacion());
		}
		return resultado;
	}
	
	
	
}
