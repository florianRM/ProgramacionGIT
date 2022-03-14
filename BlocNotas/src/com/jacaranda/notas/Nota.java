package com.jacaranda.notas;

import java.time.LocalDateTime;
import java.util.Objects;

public class Nota implements Comparable <Nota>{
	private static int codigoSiguiente = 1;
	private int codigo;
	private String texto;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaUltimaModificacion;
	
	public Nota(String texto) {
		this.texto = texto;
		this.codigo = Nota.codigoSiguiente++;
		this.fechaCreacion = LocalDateTime.now();
		this.fechaUltimaModificacion = this.fechaCreacion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		this.fechaUltimaModificacion = LocalDateTime.now();
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	
	public boolean isModificado() {
		return this.fechaCreacion.equals(fechaUltimaModificacion);
	}
	
	public boolean isEmpty() {
		return this.texto.isEmpty();
	}
	
	public boolean isCreadoAnterior(Nota nota) throws NotaException {
		if (nota == null) {
			throw new NotaException("Error. No se puede comparar con nulo.");
		}
		return this.fechaCreacion.isBefore(nota.getFechaCreacion());
	}
	
	public boolean isModificadoAnterio(Nota nota) throws NotaException {
		if (nota == null) {
			throw new NotaException("Error. No se puede comparar con nulo.");
		}
		return this.fechaUltimaModificacion.isBefore(nota.getFechaUltimaModificacion());
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
	public int compareTo(Nota otro) {
		int resultado;
		if (otro == null) {
			resultado = -1;
		}
		else {
			resultado = this.texto.compareTo(otro.texto);
			if (resultado == 0) {
				resultado = this.fechaCreacion.compareTo(otro.fechaCreacion);
			}
		}
		return resultado;
	}

	@Override
	public Nota clone() throws CloneNotSupportedException {
		Nota nueva = new Nota(this.texto);
		nueva.codigo = this.codigo;
		nueva.fechaCreacion = this.fechaCreacion;
		nueva.fechaUltimaModificacion = this.fechaUltimaModificacion;
		return nueva;
	}
	
}
