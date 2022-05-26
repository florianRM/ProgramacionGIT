package com.jacaranda.municipios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Municipio {
	private String descrip;
	private List<Datos> datos;
	
	public Municipio() {
		this.datos = new ArrayList<>();
	}
	
	public Municipio(String descrip) {
		this.descrip = descrip;
	}
	
	public String buscarPorAnnio(int ano) {
		StringBuilder resultado = new StringBuilder();
		for(Datos aux : this.datos) {
			if(aux.getAno() == ano) {
				resultado.append("Municipio [descrip=" + descrip + ", datos=" + aux.toString() + "\n");
			}
		}
		return resultado.toString();
	}
	
	public int getDatos(int ano) {
		Datos aux = new Datos(ano, 0);
		return this.datos.get(datos.indexOf(aux)).getDato();
	}
	
	public void addDatos(int annio, int dato) {
		Datos nuevoDato = new Datos(annio, dato);
		
		if(this.datos.contains(nuevoDato)) {
			this.datos.get(datos.indexOf(nuevoDato)).setDato(dato);
		} else {
			this.datos.add(nuevoDato);
		}
		
		Collections.sort(this.datos);
	}
	
	public String getDescrip() {
		return descrip;
	}

	@Override
	public String toString() {
		return "Municipio [descrip=" + descrip + ", datos=" + datos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descrip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		return Objects.equals(descrip, other.descrip);
	}

}
