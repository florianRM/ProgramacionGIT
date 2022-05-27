package Principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import PlataformaOnline.jacaranda.com.SerieException;
import PlataformaOnline.jacaranda.com.Series;
import PlataformaOnline.jacaranda.com.Tema;

public class Main {

	static Series series = new Series();
	
	public static void main(String[] args) {
		
		try {
			series.annadirSerie("This is us", 2015, Tema.DRAMA);
			series.annadirSerie("Friends", 1990, Tema.COMEDIA);
			series.annadirSerie("Dallas", 1970, Tema.INTRIGA);
			series.annadirTemporada("This is us", "Empezamos");
			series.annadirTemporada("This is us", "Seguimos");
			series.annadirTemporada("This is us", "Finalizamos");
			series.annadirCapituloTemporada("This is us", "Empezamos", "La familia");
			series.annadirCapituloTemporada("This is us", "Empezamos", "El problema");
			series.annadirCapituloTemporada("This is us", "Empezamos", "Los niños");
			series.annadirCapituloTemporada("This is us", "Empezamos", "CAsi el final");
			series.annadirCapituloTemporada("This is us", "Empezamos", "El final");
			series.valorarTemporada("This is us", "Empezamos", 5);
			series.valorarTemporada("This is us", "Seguimos", 8);
			
			
			System.out.println(series.toString());
			System.out.println(series.listarPorNota());
			System.out.println(series.listarPorNumCapitulos());
			
			escribirFicheroSeries("fichero//Fichero Series.csv");
			escribirFicheroTemporada("fichero//Fichero Temporada.csv");
			escribirFicheroCapitulos("fichero//Fichero Capitulos.csv");
		} catch (SerieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void escribirFicheroSeries(String nombre) {
		try {
			FileWriter flujoDeEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoDeEscritura);
			filtroEscritura.println(series.getSeries());
			filtroEscritura.close();
			flujoDeEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void escribirFicheroTemporada(String nombre) {
		try {
			FileWriter flujoDeEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoDeEscritura);
			filtroEscritura.println(series.getTemporada());
			filtroEscritura.close();
			flujoDeEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void escribirFicheroCapitulos(String nombre) {
		try {
			FileWriter flujoDeEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoDeEscritura);
			filtroEscritura.println(series.getCapitulos());
			filtroEscritura.close();
			flujoDeEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
