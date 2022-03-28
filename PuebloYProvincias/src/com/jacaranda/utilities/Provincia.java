package com.jacaranda.utilities;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Provincia {
	private String nombre;
	private String codigo;
	private int numeroHabitantes;
	private double rentaPerCapita;
	private double superficie;
	private Set<Pueblo> pueblos;
	
	public Provincia(String provincia, String codigo) throws ProvinciaException {
		if (provincia == null || provincia.isEmpty()) {
			throw new ProvinciaException("El nombre no puede ser nulo o estar vacío.");
		}
		this.nombre = provincia;
		setCodigo(codigo);
		this.numeroHabitantes = 0;
		this.rentaPerCapita = 0;
		this.superficie = 0;
		this.pueblos = new HashSet<>();
	}
	
	private void setCodigo(String codigo) throws ProvinciaException {
		if (codigo == null) {
			throw new ProvinciaException("El código no puede ser nulo.");
		}
		int longitud = codigo.length();
		int contador = 0;
		for (int i=0; i < longitud; i++) {
			if(codigo.codePointAt(i) >= 48 && codigo.codePointAt(i) <= 57) {
				contador ++;
			}
		}
		if (longitud != 2 || contador != longitud) {
			throw new ProvinciaException("El código no es correcto. Debe tener 2 carácteres numéricos.");
		}
		this.codigo = codigo;
	}
	
	private boolean existePueblo(String nombre) {
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		boolean encontrado = false;
		
		while(resultado.hasNext() && !encontrado) {
			Pueblo p = resultado.next();
			if(p.getNombre().equalsIgnoreCase(nombre)) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean addPueblo(String nombre, String codigo, int numeroHabitantes, double rentaPerCapita, double superfice) throws ProvinciaException {
		boolean resultado = false;
		Pueblo pueblo;
		
		if(existePueblo(nombre)) {
			throw new ProvinciaException("El nombre ya existe.");
		}
		
		try {
			pueblo = new Pueblo(nombre, this.codigo + codigo, numeroHabitantes, rentaPerCapita, superfice);
		} catch (PuebloException e) {
			throw new ProvinciaException(e.getMessage() + " El pueblo no se ha podido crear.");
		}
		
		if (!this.pueblos.add(pueblo)) {
			throw new ProvinciaException("El código ya existe.");
		}
		resultado = true;
//Vamos acumulando los habitantes y la superficie de los pueblos para ver el total de la provincia.
		this.numeroHabitantes += numeroHabitantes;
		this.superficie += superfice;
		return resultado;
	}
	
	public String listadoNombresPueblos() {
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		StringBuilder listado = new StringBuilder();
		
		while(resultado.hasNext()) {
			Pueblo p = resultado.next();
			listado.append(p.getNombre() + "\n");
		}
		return "Nombre pueblos:\n"  + listado.toString();
	}
	
	public boolean delPueblo(String pueblo) {
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		boolean encontrado = false;
		
		while(resultado.hasNext() && !encontrado) {
			Pueblo p = resultado.next();
			if (p.getNombre().equalsIgnoreCase(pueblo)) {
				this.pueblos.remove(p);
				this.numeroHabitantes -= p.getNumeroHabitantes();
				this.superficie -= p.getSuperficie();
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public String listadoPueblos() {
		StringBuilder listado = new StringBuilder();
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		
		while(resultado.hasNext()) {
			Pueblo p = resultado.next();
			listado.append(p + "\n");
		}
		return "Listado de pueblos:\n" + listado.toString();
	}
	
	public void setSuperficie(String pueblo, double superficie) throws ProvinciaException {
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		boolean encontrado = false;
		
		while(resultado.hasNext() && !encontrado) {
			Pueblo p = resultado.next();
			if (p.getNombre().equalsIgnoreCase(pueblo)) {
				try {
					p.setSuperficie(superficie);
				} catch (PuebloException e) {
					throw new ProvinciaException(e.getMessage());
				}
				encontrado = true;
			}
		}
		this.superficie += superficie;
	}

	public void setNumeroHabitantes(String pueblo, int numeroHabitantes) throws ProvinciaException {
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		boolean encontrado = false;
		
		while(resultado.hasNext() && !encontrado) {
			Pueblo p = resultado.next();
			if (p.getNombre().equalsIgnoreCase(pueblo)) {
				try {
					p.setNumeroHabitantes(numeroHabitantes);
				} catch (PuebloException e) {
					throw new ProvinciaException(e.getMessage());
				}
				encontrado = true;
			}
		}
		this.numeroHabitantes += numeroHabitantes; 
	}
	
	public int numPueblos() {
		int contador = 0;
		for (Iterator<Pueblo> iterator = pueblos.iterator(); iterator.hasNext();) {
			Pueblo pueblo = iterator.next();
			contador++;
		}
		return contador;
	}
	
	public double getRentaPerCapita() {
		return rentaPerCapita;
	}
	
	public void setRentaPerCapita(double rentaPerCapita) throws ProvinciaException {
		if (rentaPerCapita < 0) {
			throw new ProvinciaException("La renta percapita debe ser mayor a 0");
		}
		this.rentaPerCapita = rentaPerCapita;
	}

	public String getProvincia() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public int getNumeroHabitantes() {
		return numeroHabitantes;
	}
	
	public double getSuperficie() {
		return superficie;
	}

	@Override
	public String toString() {
		return "Provincia [nombre=" + nombre + ", codigo=" + codigo + ", numeroHabitantes=" + numeroHabitantes
				+ ", rentaPerCapita=" + rentaPerCapita + ", superficie=" + superficie + ", pueblos=" + pueblos + "]";
	}
	
	public String getInformacionPueblo(String pueblo) {
		Iterator<Pueblo> resultado = this.pueblos.iterator();
		boolean encontrado = false;
		String informacion = null;
		
		while (resultado.hasNext() && !encontrado) {
			Pueblo p = resultado.next();
			if (p.getNombre().equalsIgnoreCase(pueblo)) {
				informacion = p.toString();
				encontrado = true;
			}
		}
		return informacion;
	}
	
}
