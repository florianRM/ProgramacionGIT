package com.jacaranda.principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.jacaranda.country.Country;

public class Principal {
	static ArrayList<Country> listCountry = new ArrayList<>();
	
	public static void main(String[] args) {
		leerFicheroCountry("ficheros/country.txt");
		System.out.println(listCountry.toString());
	}
	
	private static void leerFicheroCountry(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(",");
				Country country = new Country(Integer.parseInt(campos[0]), campos[1]);
				listCountry.add(country);
				linea = filtroLectura.readLine();
			}
			filtroLectura.close();
			flujoLectura.close();
		} catch (FileNotFoundException e) {
			System.out.println("No existe el fichero " + nombreFichero);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
