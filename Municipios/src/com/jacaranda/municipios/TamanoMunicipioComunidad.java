package com.jacaranda.municipios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/*He usado en todas las clases la colección ArrayList ya que necesito encontrar los
 * datos de una manera rápida*/

public class TamanoMunicipioComunidad {

	private static final String MENSAJE_ERROR = "La comunidad no existe.";
	private ArrayList<Comunidad> listaComunidades;

	public TamanoMunicipioComunidad() {
		Gson gson;
		this.listaComunidades = new ArrayList<>();
		gson = new Gson();
		String fichero = leerFichero("ficheros//tamanoMunicipioComunidad.json");
		this.listaComunidades = gson.fromJson(fichero, new TypeToken<ArrayList<Comunidad>>() {
		}.getType());
	}

	public String buscarPorAnnio(int annio) {
		StringBuilder resultado = new StringBuilder();
		for (Comunidad aux : this.listaComunidades) {
			resultado.append(aux.buscarPorAnnio(annio));
		}
		return resultado.toString();
	}

	public void addDato(String nombre, String descrip, int annio, int dato) throws ComunidadException {
		Comunidad aux = new Comunidad(nombre);
		int posicion = this.listaComunidades.indexOf(aux);
		if (posicion == -1) {
			throw new ComunidadException(MENSAJE_ERROR);
		}

		this.listaComunidades.get(posicion).addDato(descrip, annio, dato);
	}

	public String buscarComunidad(String nombre, int annio) throws ComunidadException {
		Comunidad aux = new Comunidad(nombre);
		int posicion = this.listaComunidades.indexOf(aux);
		if (posicion == -1) {
			throw new ComunidadException(MENSAJE_ERROR);
		}

		return this.listaComunidades.get(posicion).buscarPorAnnio(annio);
	}

	public String calcularTotal(String nombre, int annio) throws ComunidadException {
		Comunidad aux = new Comunidad(nombre);
		int posicion = this.listaComunidades.indexOf(aux);
		if (posicion == -1) {
			throw new ComunidadException(MENSAJE_ERROR);
		}

		return this.listaComunidades.get(posicion).compararTotal(annio);
	}

	public String toString() {
		return this.listaComunidades.toString();
	}

	private String leerFichero(String nombreFichero) {
		String linea;
		StringBuilder resultado = new StringBuilder();
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			linea = filtroLectura.readLine();
			while (linea != null) {
				resultado.append(linea);
				linea = filtroLectura.readLine();
			}
			filtroLectura.close();
			flujoLectura.close();
		} catch (FileNotFoundException e) {
			System.out.println("No existe el fichero " + nombreFichero);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return resultado.toString();
	}

	public void escribirFichero(String nombre) {
		final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		final String representacionBonita = prettyGson.toJson(this.listaComunidades);

		try {
			FileWriter flujoEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);
			
			filtroEscritura.print(representacionBonita);
			
			filtroEscritura.close();
			flujoEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
