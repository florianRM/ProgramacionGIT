package com.jacaranda.principal;

import com.jacaranda.utilities.*;

public class Main {

	public static void main(String[] args) {
		Provincia p = null;
		Provincia p1 = null;
		Provincia p2 = null;
		Provincia p3 = null;
		Provincia p4 = null;
		
		//Creación de provincia
		try {
			p = new Provincia("Sevilla", "12");
			System.out.println("Provincia creada");
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			p1 = new Provincia(null, "12");
			System.out.println("Provincia creada");
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			p2 = new Provincia("Sevilla", null);
			System.out.println("Provincia creada");
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			p3 = new Provincia("Sevilla", "1");
			System.out.println("Provincia creada");
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			p4 = new Provincia("Sevilla", "123");
			System.out.println("Provincia creada");
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		//Creacion de pueblos
		try {
			p.addPueblo("Brenes", "345", 10000, 200000, 200);
			System.out.println("Pueblo creado");
			p.addPueblo("Brene", "215", 10000, 200000, 200);
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(p.numPueblos());
		
		//Exceptions
		//Renta per capita negativa
		try {
			p.setRentaPerCapita(-2);
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		//Superficie negativa
		try {
			p.setSuperficie("Brenes", -2);
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		//Habitantes en negativo
		try {
			p.setNumeroHabitantes("Brenes", -5);
		} catch (ProvinciaException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(p.listadoPueblos());
	}

}
