package com.jacaranda.bloc;

import java.time.LocalDateTime;
import java.util.Objects;

import com.jacaranda.notas.Nota;
import com.jacaranda.notas.NotaAlarma;
import com.jacaranda.notas.NotaAlarmaException;

public class Bloc {
	private static final int NUMERONOTASMAXIMA = 20;
	private int numNotas;
	private String nombre;
	private Nota[] listaDeNotas = new Nota[NUMERONOTASMAXIMA];
	
	public Bloc(String nombre) {
		this.nombre = nombre;
	}
	
	public void addNota(String texto) throws BlocException {
		if (numNotas == Bloc.NUMERONOTASMAXIMA) {
			throw new BlocException("El bloc está completo.");
		}
		listaDeNotas[numNotas] = new Nota(texto);
		this.numNotas ++;
	}
	
	public void addNota(String texto, LocalDateTime horaAlarma, boolean estado) throws NotaAlarmaException, BlocException {
		if (numNotas == Bloc.NUMERONOTASMAXIMA) {
			throw new BlocException("El bloc está completo.");
		}
		listaDeNotas[numNotas] = new NotaAlarma(texto, horaAlarma, estado);
		this.numNotas ++;
	}
	
	public void addNota(String texto, LocalDateTime horaAlarma, int numMinutos) throws NotaAlarmaException, BlocException {
		if (numNotas == Bloc.NUMERONOTASMAXIMA) {
			throw new BlocException("El bloc está completo.");
		}
		listaDeNotas[numNotas] = new NotaAlarma(texto, horaAlarma, numMinutos);
		this.numNotas++;
	}
	
	public void borrarNota(int posicion) throws BlocException {
		if (posicion > Bloc.NUMERONOTASMAXIMA || listaDeNotas[posicion] == null) {
			throw new BlocException("Error. La posicion que quiere borrar no existe.");
		}
		if (posicion == Bloc.NUMERONOTASMAXIMA) {
			listaDeNotas[posicion] = null;
		}
		else {
			for (int i=posicion; i < numNotas; i++) {
				listaDeNotas[i] = listaDeNotas[i+1];
			}
		}
		this.numNotas --;
	}
	
	public String getNota(int posicion) throws BlocException {
		if (posicion > Bloc.NUMERONOTASMAXIMA || listaDeNotas[posicion] == null) {
			throw new BlocException("La posición que buscas no existe");
		}
		return listaDeNotas[posicion].toString();
	}
	
	public void updateNota(int posicion, String nuevoTexto) throws BlocException {
		if (posicion > Bloc.NUMERONOTASMAXIMA || listaDeNotas[posicion] == null) {
			throw new BlocException("La posición que buscas no existe");
		}
		listaDeNotas[posicion].setTexto(nuevoTexto);
	}
	
	public void activa(int posicion) throws BlocException {
		if (posicion > Bloc.NUMERONOTASMAXIMA || listaDeNotas[posicion] == null) {
			throw new BlocException("Error. La posicion que busca no existe.");
		}
		if (listaDeNotas[posicion] instanceof NotaAlarma) {
			((NotaAlarma) listaDeNotas[posicion]).activar();
		}
	}
	
	public void desactiva(int posicion) throws BlocException {
		if (posicion > Bloc.NUMERONOTASMAXIMA || listaDeNotas[posicion] == null) {
			throw new BlocException("Error. La posicion que busca no existe.");
		}
		if (listaDeNotas[posicion] instanceof NotaAlarma) {
			((NotaAlarma) listaDeNotas[posicion]).desactivar();
		}
	}
	
	public int getNumeroNotasMaxima() {
		return Bloc.NUMERONOTASMAXIMA;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i<numNotas; i++) {
			resultado.append(listaDeNotas[i] + "\n");
		}
		return "Nombre Bloc:" + this.nombre + ". Tiene " + this.numNotas + " notas.\n" + resultado.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bloc other = (Bloc) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}
