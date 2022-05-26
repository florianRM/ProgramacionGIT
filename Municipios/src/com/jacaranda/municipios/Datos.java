package com.jacaranda.municipios;

import java.util.Objects;

public class Datos implements Comparable<Datos> {
	int ano;
	int dato;
	
	public Datos(int ano, int dato) {
		this.ano = ano;
		this.dato = dato;
	}
	
	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getAno() {
		return this.ano;
	}
	
	@Override
	public String toString() {
		return "Datos [ano=" + ano + ", dato=" + dato + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Datos other = (Datos) obj;
		return ano == other.ano;
	}

	@Override
	public int compareTo(Datos o) {
		int resultado = -1;
		if(this.ano == o.ano) {
			resultado = 0;
		} else if(this.ano < o.ano) {
			resultado = 1;
		}
		return resultado;
	}
	
}
