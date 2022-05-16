package com.jacaranda.country;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class City {
	private int cityId;
	private String cityName;
	private ArrayList<Address> listAddress;
	
	public City(int cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.listAddress = new ArrayList<>();
		leerFicheroAddress("ficheros/address2.txt");
	}
	
	public int getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	@Override
	public String toString() {
		return "City [city_id=" + cityId + ", cityName=" + cityName + ", listAddress=" + listAddress + "]";
	}
	
	private void leerFicheroAddress(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(",");
				Address address = new Address(Integer.parseInt(campos[0]), campos[1]);
				this.listAddress.add(address);
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
