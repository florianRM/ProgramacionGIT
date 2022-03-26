package com.jacaranda.publicacion;

import java.time.LocalDateTime;
import java.util.Objects;

import com.jacarnda.usuario.Usuario;

public abstract class Publicacion implements Comparable<Publicacion>, Valorable{
	protected String texto;
	private LocalDateTime fechaCreacion;
	protected int valoracion;
	private int codigo;
	private static int codigoSiguiente = 1;
	private Usuario usuario;
	
	public Publicacion(String texto, Usuario cuenta) throws PublicacionException {
		this.codigo = codigoSiguiente ++;
		setTexto(texto);
		this.usuario = cuenta;
		this.fechaCreacion = LocalDateTime.now();
	}

	public String getTexto() {
		return texto;
	}

	protected abstract void setTexto(String texto) throws PublicacionException;

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
		Publicacion other = (Publicacion) obj;
		return Objects.equals(fechaCreacion, other.fechaCreacion) && Objects.equals(texto, other.texto);
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public int getValoracion() {
		return valoracion;
	}
	
	/*En este metodo le pasamos una valoracion que se encuentra en el enumerado Valoraciones y devuelve
	*true si la asignación ha sido correcta o false si es incorrecta*/
	public boolean Valorar(String valoracion) {
		boolean resultado = true;
		try {
			this.valoracion = Valoraciones.valueOf(valoracion).getValoracion();
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getLoginUsuario() {
		return this.usuario.getLogin();
	}

	@Override
	public String toString() {
		return "Publicacion:" + this.texto + "\nRealizado por:" + getLoginUsuario() + "\nValoración:" + this.valoracion + "\nFecha de publicación:" + this.fechaCreacion;
	}

	@Override
	public int compareTo(Publicacion other) {
		int resultado;
		if (this.valoracion == 0) {
			resultado = -1;
		}
		else {
			resultado = this.valoracion - other.valoracion;
			if (resultado == 0) {
				resultado = this.fechaCreacion.compareTo(other.fechaCreacion);
			}
		}
		return resultado;
	}
	
	public boolean isAnterior(Publicacion other) {
		boolean resultado = false;
		if (this.fechaCreacion.isBefore(other.fechaCreacion)) {
			resultado = true;
		}
		return resultado;
	}
	
}
