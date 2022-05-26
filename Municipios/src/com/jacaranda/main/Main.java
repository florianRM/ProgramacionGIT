package com.jacaranda.main;

import java.util.Scanner;

import com.jacaranda.municipios.Comunidad;
import com.jacaranda.municipios.ComunidadException;
import com.jacaranda.municipios.TamanoMunicipioComunidad;

public class Main {

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws ComunidadException {
		int opc;
		int dato;
		String descripcion;
		String url;
		char caracter;
		TamanoMunicipioComunidad municipio = new TamanoMunicipioComunidad();

		do {
			menu();
			opc = pedirEntero();

			switch (opc) {
			case 1:
				System.out.println(municipio.buscarPorAnnio(pedirAnnio()));
				break;
			case 2:
				System.out.println(municipio.buscarComunidad(pedirNombre(), pedirAnnio()));
			case 3:
				String nombre = pedirNombre();
				System.out.print("Introduzca la descripción del municipio:");
				descripcion = pedirString();
				int annio = pedirAnnio();
				System.out.print("Introduzca el nuevo dato:");
				dato = pedirEntero();

				municipio.addDato(nombre, descripcion, annio, dato);
				break;
			case 4:
				System.out.println(municipio.calcularTotal(pedirNombre(), pedirAnnio()));
				break;
			case 5:
				System.out.println("¿Quieres guardar los datos en un nuevo fichero(S/N)?");
				caracter = teclado.nextLine().charAt(0);
				if(caracter == 'S' || caracter == 's') {
					System.out.print("Introduzca la url donde quiere guardar el fichero:");
					url = pedirString();
					municipio.escribirFichero(url);
					System.out.println("Fichero creado con exito en la dirección:" + url);
				} else {
					System.out.println("Saliendo...");
				}
				break;
			default:
				System.out.println("La opción introducida no existe.");
				break;
			}
		} while (opc != 5);
	}

	private static void menu() {
		System.out.print("1. Mostrar todos los datos de las comunidades y de España de un año.\n"
				+ "2. Mostrar los datos de una comunidad y un año.\n" + "3. Añadir un dato.\n"
				+ "4. Comprobar valor total.\n" + "5. Salir\n" + "Introduzca una opcion:");
	}
	
	private static String pedirNombre() {
		System.out.print("Introduzca el nombre de la comunidad:");
		return pedirString().toUpperCase();
	}
	
	private static int pedirAnnio() {
		System.out.print("Introduzca el año:");
		return pedirEntero();
	}

	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}

	private static String pedirString() {
		return teclado.nextLine();
	}

}
