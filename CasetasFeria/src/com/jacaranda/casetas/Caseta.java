package com.jacaranda.casetas;

import java.util.Objects;

public class Caseta {
	private String nombre;
	private String numero;
	private String modulos;
	private String clase;
	private String id;
	private String idCalle;
	
	public Caseta(String nombre, String numero, String modulos, String clase, String id, String idCalle) {
		super();
		this.nombre = nombre;
		this.numero = numero;
		this.modulos = modulos;
		this.clase = clase;
		this.id = id;
		this.idCalle = idCalle;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getNumero() {
		return numero;
	}

	public String getModulos() {
		return modulos;
	}

	public String getClase() {
		return clase;
	}

	public String getId() {
		return id;
	}

	public String getIdCalle() {
		return idCalle;
	}

	@Override
	public String toString() {
		return "Caseta [nombre=" + nombre + ", numero=" + numero + ", modulos=" + modulos
				+ ", clase=" + clase + ", id=" + id + ", idCalle=" + idCalle + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caseta other = (Caseta) obj;
		return Objects.equals(id, other.id);
	}
	
}
