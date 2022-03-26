package com.jacaranda.publicacion;

import com.jacarnda.usuario.Usuario;

public class Post extends Publicacion {
	
	private int numeroLecturas;
	private String tema;
	
	public Post(String texto, Usuario cuenta, String tema) throws PublicacionException {
		super(texto, cuenta);
		if (tema == null || tema.isEmpty()) {
			throw new PublicacionException("El post no puede estar vacío.");
		}
		this.tema = tema;
	}
	
	@Override
	protected void setTexto(String texto) throws PublicacionException {
		if (texto.isEmpty()) {
			throw new PublicacionException("El post no puede estar vacío.");
		}
		super.texto = texto;
	}

	@Override
	public boolean Valorar(String valoracion) {
		boolean resultado = true;
		try {
			super.valoracion += Valoraciones.valueOf(valoracion.toUpperCase()).getValoracion();
			this.numeroLecturas ++;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "Post.\n" + super.toString() + "\nNúmero lecturas:" + this.numeroLecturas + "\nTema:" + this.tema;
	}
	
	

}
