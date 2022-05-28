package com.jacaranda.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import com.jacaranda.gestion.*;

public class Main {

	public static Scanner teclado = new Scanner(System.in);
	private static LinkedList<Alumnado> listaAlumnos = new LinkedList<>();
	private static HashSet<Modulo> listaModulos = new HashSet<>();
	private static ArrayList<Nota> listaNotas = new ArrayList<>();
	
	public static void main(String[] args) {
		int opc;
		String nombre;
		String dni;
		String correo;
		int horas;
		int creditos;
		double nota;
		LocalDate fecha;
		leerFicheroAlumno("ficheros//alumnado.txt");
		leerFicheroModulo("ficheros//modulo.txt");
		leerFicheroNota("ficheros//nota.txt");
		
		do {
			menu();
			opc = pedirEntero();
			
			switch (opc) {
			case 1:
				System.out.println("Introduzca el nombre del alumno:");
				nombre = pedirCadena();
				System.out.println("Introduzca el dni:");
				dni = pedirCadena();
				System.out.println("Introduza un correo:");
				correo = pedirCadena();
				
				listaAlumnos.add(new Alumnado(nombre, dni, correo));
				break;
			case 2:
				System.out.println("Introduzca el nombre del modulo:");
				nombre = pedirCadena();
				System.out.println("Introduzca el número de horas:");
				horas = pedirEntero();
				System.out.println("Introduza el número de creditos:");
				creditos = pedirEntero();
				
				listaModulos.add(new Modulo(nombre, horas, creditos));
				break;
			case 3:
				System.out.println("Introduzca el dni del alumno:");
				dni = pedirCadena();
				System.out.println("Introduzca el nombre del modulo:");
				nombre = pedirCadena();
				System.out.println("Introduza la nota:");
				nota = pedirDouble();
				System.out.println("Introduzca la fecha(yyyy-mm-dd):");
				String cadenaFecha = pedirCadena();
				fecha = LocalDate.parse(cadenaFecha);
				
				Alumnado aux = new Alumnado(null, dni);
				int posicion = listaAlumnos.indexOf(aux);
				if(posicion != -1) {
					boolean encontrado = false;
					Iterator<Modulo> iterator = listaModulos.iterator();
					while(iterator.hasNext() && !encontrado) {
						Modulo modulo = iterator.next();
						if(modulo.getNombre().equals(nombre)) {
							listaNotas.add(new Nota(nota, fecha, listaAlumnos.get(posicion), modulo));
							encontrado = true;
						}
					}
				} else {
					System.out.println("El modulo no existe");
				}
				break;
			case 4:
				for(Nota not : listaNotas) {
					System.out.println(not);
				}
				break;
			case 5:
				for(Alumnado alum : listaAlumnos) {
					System.out.println(alum);
				}
				break;
			case 6:
				for(Modulo mod : listaModulos) {
					System.out.println(mod);
				}
				break;
			case 7:
				escribirFicheroAlumnado("ficheros//alumnado.txt");
				escribirFicheroModulo("ficheros//modulo.txt");
				escribirFicheroNota("ficheros//nota.txt");
			default:
				break;
			}
		} while (opc != 7);
	}
	
	private static void menu() {
		System.out.print("1.Dar de alta alumno.\n"
				+ "2.Dar de alta un modulo.\n"
				+ "3.Introducir nota.\n"
				+ "4.Listar las notas de todos los alumnos.\n"
				+ "5.Listar todos los alumnos.\n"
				+ "6.Listar todos los modulos.\n"
				+ "7.Salir\n"
				+ "Introduzca una opción:");
	}
	
	private static void leerFicheroAlumno(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);

			linea = filtroLectura.readLine();
			while (linea != null) {

				String[] campos = linea.split(",");
				Alumnado alumno = new Alumnado(campos[0], campos[1], campos[2]);
				listaAlumnos.add(alumno);

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
	
	private static void leerFicheroModulo(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);

			linea = filtroLectura.readLine();
			while (linea != null) {

				String[] campos = linea.split(",");
				Modulo modulo = new Modulo(campos[0], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]));
				
				listaModulos.add(modulo);

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
	
	private static void leerFicheroNota(String nombreFichero) {
		String linea;
		try {
			FileReader flujoLectura = new FileReader(nombreFichero);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);

			linea = filtroLectura.readLine();
			while (linea != null) {

				String[] campos = linea.split(",");
				Alumnado aux = new Alumnado(null, campos[2]);
				int posicion = listaAlumnos.indexOf(aux);
				
				if(posicion != -1) {
					aux = listaAlumnos.get(posicion);
				}
				
				Modulo modulo = new Modulo(campos[3]);
				
				Nota nota = new Nota(Double.parseDouble(campos[0]), LocalDate.parse(campos[1]), aux, modulo);
				
				listaNotas.add(nota);

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
	
	private static void escribirFicheroNota(String nombre) {
		try {
			FileWriter flujoEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);

			for (Nota aux : listaNotas) {
				filtroEscritura.println(aux.escribirFicheroNota());
			}

			filtroEscritura.close();
			flujoEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void escribirFicheroModulo(String nombre) {
		try {
			FileWriter flujoEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);

			for (Modulo aux : listaModulos) {
				filtroEscritura.println(aux.escribirFicheroModulo());
			}

			filtroEscritura.close();
			flujoEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void escribirFicheroAlumnado(String nombre) {
		try {
			FileWriter flujoEscritura = new FileWriter(nombre);
			PrintWriter filtroEscritura = new PrintWriter(flujoEscritura);

			for (Alumnado aux : listaAlumnos) {
				filtroEscritura.println(aux.escribirFicheroAlumno());
			}

			filtroEscritura.close();
			flujoEscritura.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}
	
	private static String pedirCadena() {
		return teclado.nextLine();
	}
	
	private static double pedirDouble() {
		return Double.parseDouble(teclado.nextLine());
	}

}
