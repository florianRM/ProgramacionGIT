package com.jacaranda.utilities;

import java.util.Objects;

public class Pueblo implements Comparable<Pueblo> {
	private String nombre;
	private String codigo;
	private int numeroHabitantes;
	private double rentaPerCapita;
	private double superficie;
	
	public Pueblo(String pueblo, String codigo, int numeroHabitantes, double rentaPerCapita, double superficie) throws PuebloException {
		super();
		this.nombre = pueblo.toUpperCase();
		setCodigo(codigo);
		setNumeroHabitantes(numeroHabitantes);
		setRentaPerCapita(rentaPerCapita);
		setSuperficie(superficie);
	}

	public Pueblo(String nombre, String codigo) throws PuebloException {
		super();
		this.nombre = nombre.toUpperCase();
		setCodigo(codigo);
	}
	
	private void setCodigo(String codigo) throws PuebloException {
		int longitud = codigo.length();
		int contador = 0;
		for (int i=0; i < longitud; i++) {
			if(codigo.codePointAt(i) >= 48 && codigo.codePointAt(i) <= 57) {
				contador ++;
			}
		}
		if (longitud != 5 || contador != longitud) {
			throw new PuebloException("El código no es correcto. Debe tener 3 carácteres numéricos.");
		}
		this.codigo = codigo;
	}

	public int getNumeroHabitantes() {
		return numeroHabitantes;
	}

	public void setNumeroHabitantes(int numeroHabitantes) throws PuebloException {
		if(numeroHabitantes < 0) {
			throw new PuebloException("Los habitantes debe ser mayor a 0.");
		}
		this.numeroHabitantes = numeroHabitantes;
	}

	public double getRentaPerCapita() {
		return rentaPerCapita;
	}

	public void setRentaPerCapita(double rentaPerCapita) throws PuebloException {
		if(rentaPerCapita < 0) {
			throw new PuebloException("La renta per capita debe ser mayor a 0.");
		}
		this.rentaPerCapita = rentaPerCapita;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) throws PuebloException {
		if(superficie < 0) {
			throw new PuebloException("La superficie debe ser mayor a 0.");
		}
		this.superficie = superficie;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pueblo other = (Pueblo) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public int compareTo(Pueblo other) {
		int resultado;
		if (other == null) {
			resultado = -1;
		}
		else {
			resultado = this.nombre.compareTo(other.nombre);
		}
		return resultado;
	}
	
	@Override
	public String toString() {
		return "Pueblo [nombre=" + nombre + ", codigo=" + codigo + ", numeroHabitantes=" + numeroHabitantes
				+ ", rentaPerCapita=" + rentaPerCapita + ", superficie=" + superficie + "]";
	}
}