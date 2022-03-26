package com.jacaranda.publicacion;

import com.jacarnda.usuario.Usuario;

public class Recomendacion extends Publicacion {

	private int numEstrellas;
	
	public Recomendacion(String texto, Usuario cuenta, int numeroEstrellas) throws PublicacionException {
		super(texto, cuenta);
		if (numeroEstrellas <= 0 || numeroEstrellas > 5) {
			throw new PublicacionException("El valor de la puntuación debe estar entre 1 y 5.");
		}
		this.numEstrellas = numeroEstrellas;
	}

	@Override
	protected void setTexto(String texto) throws PublicacionException {
		if (texto.length() < 100 || texto.length() > 200) {
			throw new PublicacionException("La recomendación debe tener entre 100 y 200 carácteres.");
		}
		super.texto = texto;
	}

	@Override
	public String toString() {
		return "Recomendacion.\n" + super.toString() + "\nNúmero de estrellas:" + this.numEstrellas;
	}
	
	

}
