package com.jacaranda.principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.jacaranda.country.Address;
import com.jacaranda.country.City;
import com.jacaranda.country.Country;

public class Principal {
	static ArrayList<Country> listCountry = new ArrayList<>();

	public static void main(String[] args) {
		leerFicheroCountry("ficheros//country.txt");
		leerFicheroCity("ficheros//city.txt");
		leerFicheroAddress("ficheros//address2.txt");
		
		escribirFichero("ficheros//nuevoFichero.txt");
		escribirFicheroCiudades("ficheros//ficheroCiudades.txt");

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

	private static void leerFicheroCity(String nombreFichero) {
		String linea;
		int idPais;
		int posicion;

		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(",");
				idPais = Integer.parseInt(campos[2]);

				Country aux = new Country(idPais, null);
				posicion = listCountry.indexOf(aux);
				
				int idCity = Integer.parseInt(campos[0]);
				City city = new City(idCity, campos[1]);

				if (posicion != -1) {
					listCountry.get(posicion).addCity(city);
				}

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

	private static void leerFicheroAddress(String nombreFichero) {
		String linea;
		int idCity;
		int idAddress;

		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				boolean found = false;
				String[] campos = linea.split(",");

				idCity = Integer.parseInt(campos[4]);
				City aux = new City(idCity, null);

				idAddress = Integer.parseInt(campos[0]);
				Address address = new Address(idAddress, campos[1]);

				Iterator<Country> iterator = listCountry.iterator();
				while (iterator.hasNext() && !found) {
					Country country = iterator.next();
					if(country.searchIdCity(aux) != null) {
						country.addAddress(aux, address);
						found = true;
					}
				}

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

	private static void escribirFichero(String nombre) {
		try {
			FileWriter flujoEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);
			
			for(Country aux : listCountry) {
				filtroEscritura.println(aux.writeFile());
			}
			
			filtroEscritura.close();
			flujoEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void escribirFicheroCiudades(String nombre) {
		try {
			FileWriter flujoEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);
			
			for(Country aux : listCountry) {
				filtroEscritura.println(aux.writeCityFile());
			}
			
			filtroEscritura.close();
			flujoEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
