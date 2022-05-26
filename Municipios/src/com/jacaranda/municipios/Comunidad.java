package com.jacaranda.municipios;

import java.util.ArrayList;
import java.util.Objects;

public class Comunidad {
	private String nombre;
	private ArrayList<Municipio> listMunicipio;

	public Comunidad() {
		this.listMunicipio = new ArrayList<>();
	}

	public Comunidad(String nombre) {
		this.nombre = nombre;
	}

	public String buscarPorAnnio(int annio) {
		StringBuilder resultado = new StringBuilder();
		for (Municipio aux : this.listMunicipio) {
			resultado.append("Nombre comunidad:" + nombre + ", " + aux.buscarPorAnnio(annio));
		}
		return resultado.toString();
	}

	public void addDato(String descripcion, int annio, int dato) throws ComunidadException {
		Municipio aux = new Municipio(descripcion);
		int posicion = this.listMunicipio.indexOf(aux);

		if (posicion == -1) {
			throw new ComunidadException("La descripción no existe.");
		}

		this.listMunicipio.get(posicion).addDatos(annio, dato);
	}

	public String compararTotal(int annio) {
		int sumaDatos = 0;
		int total = 0;
		String resultado;

		for (Municipio municipios : this.listMunicipio) {
			if (!municipios.getDescrip().equals("Total")) {
				sumaDatos += municipios.getDatos(annio);
			} else {
				total = municipios.getDatos(annio);
			}
		}

		if (total == sumaDatos) {
			resultado = "El total es igual a la suma de datos.";
		} else {
			resultado = "No son iguales. El valor actual es " + sumaDatos + " y debía ser " + total;
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "Comunidad [nombre=" + nombre + ", listaMunicipios=" + listMunicipio + "]";
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
		Comunidad other = (Comunidad) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
