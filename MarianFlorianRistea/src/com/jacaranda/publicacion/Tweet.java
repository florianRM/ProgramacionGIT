package com.jacaranda.publicacion;

import com.jacarnda.usuario.Usuario;

public class Tweet extends Publicacion {
	
	public Tweet(String texto, Usuario cuenta) throws PublicacionException {
		super(texto, cuenta);
	}
	
	@Override
	public void setTexto(String texto) throws PublicacionException {
		if (texto.length() > 50) {
			throw new PublicacionException("El mensaje ha superado el límite de carácteres.");
		}
		super.texto = texto;
	}
	
	@Override
	public boolean Valorar(String valoracion) {
		boolean resultado = true;
		try {
			super.valoracion += Valoraciones.valueOf(valoracion.toUpperCase()).getValoracion() * 2;
		} catch (Exception e) {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "Tweet.\n" + super.toString();
	}
	
	

}
