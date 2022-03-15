package libreria;

import java.time.LocalDate;
import java.util.Scanner;

import libros.*;

public class Main {
	
	private static final int MAXLIBROS = 10;
	private static Libro[] libros = new Libro[MAXLIBROS];
	private static int numLibros;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws LibroException {
		int opc, opcLibro;
		String titulo, autor, editorial, formato;
		double peso, precio;
		
		do {
			menu();
			opc = pedirEntero();
			switch (opc) {
			case 1:
				menuCreacionLibro();
				opcLibro = pedirEntero();
				System.out.println("Introduzca el título del libro: ");
				titulo = pedirString();
				System.out.println("Introduzca el autor: ");
				autor = pedirString();
				switch (opcLibro) {
				case 1:
					System.out.println("Introduzca el peso: ");
					peso = pedirReal();
					System.out.println("Introduzca el precio: ");
					precio = pedirReal();
					try {
						libros[numLibros] = new LibroFisico(titulo, autor, peso, precio);
						numLibros ++;
					} catch (LibroException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println("Introduzca la editorial: ");
					editorial = pedirString();
					System.out.println("Introduzca el peso: ");
					peso = pedirReal();
					System.out.println("Introduzca el precio: ");
					precio = pedirReal();
					try {
						libros[numLibros] = new LibroFisico(titulo, autor, editorial, peso, precio);
						numLibros ++;
					} catch (LibroException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Introduzca el formato del libro: ");
					formato = pedirString();
					System.out.println("Introduzca el precio: ");
					precio = pedirReal();
					try {
						libros[numLibros] = new LibroDigital(titulo, autor, precio, formato);
					} catch (LibroException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					System.out.println("Introduzca la editorial: ");
					editorial = pedirString();
					System.out.println("Introduzca el formato: ");
					formato = pedirString();
					System.out.println("Introduzca el precio: ");
					precio = pedirReal();
					try {
						libros[numLibros] = new LibroDigital(titulo, autor, editorial, precio, formato);
					} catch (LibroException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("La opción introducida no existe.");
					break;
				}
				break;
			case 2:
				boolean encontrado = false;
				int dia, mes, annio;
				System.out.println("Introduzca el título del libro que busca: ");
				titulo = pedirString();
				for (int i=0; i < numLibros && encontrado != true; i++) {
					if (libros[i].getTitulo().equals(titulo)) {
						System.out.println("Introduzca el día: ");
						dia = pedirEntero();
						System.out.println("Introduzca el mes: ");
						mes = pedirEntero();
						System.out.println("Introduzca el año: ");
						annio = pedirEntero();
						try {
							libros[i].setFechaEdicion(LocalDate.of(annio, mes, dia));
						} catch (LibroException e) {
							System.out.println(e.getMessage());
						}
						encontrado = true;
					}
				}
				break;
			case 3:
				int posicion1, posicion2;
				System.out.println("Introduzca la posición del primer libro: ");
				posicion1 = pedirEntero();
				System.out.println("Introduzca la posición del segundo libro: ");
				posicion2 = pedirEntero();
				
				if (libros[posicion1].compareTo(libros[posicion2]) > 0) {
					System.out.println("Hay una diferencia de " + libros[posicion2].diferenciaDias(libros[posicion1]) + " días.");
				}
				else {
					System.out.println("Hay una diferencia de " + libros[posicion1].diferenciaDias(libros[posicion2]) + " días.");
				}
				break;
			case 4:
				System.out.println("Introduzca la posición del primer libro: ");
				posicion1 = pedirEntero();
				System.out.println("Introduzca la posición del segundo libro: ");
				posicion2 = pedirEntero();
				if (libros[posicion1] instanceof LibroDigital && libros[posicion2] instanceof LibroFisico) {
					System.out.println(((LibroDigital) libros[posicion1]).masCaroLibroDigital((LibroFisico)libros[posicion2]));
				}
				else {
					System.out.println(((LibroFisico) libros[posicion1]).masCaroLibroFisico((LibroDigital)libros[posicion2]));
				}
				break;
			case 5:
				System.out.println("Saliendo");
			default:
				System.out.println("La opción introducida es incorrecta.");
				break;
			}
		} while (opc != 5);
	}
	
	private static void menu() {
		System.out.print("1.Crear libro.\n"
				+ "2.Fecha edición.\n"
				+ "3.Comparar fecha edición.\n"
				+ "4.Comparar precio libros.\n"
				+ "5.Salir.\n"
				+ "Introduzca una opción: ");
	}
	
	private static void menuCreacionLibro() {
		System.out.print("1.Libro físico sin editorial.\n"
				+ "2.Libro físico con editorial.\n"
				+ "3.Libro digital sin editorial.\n"
				+ "4.Libro digital con editorial.\n"
				+ "Introduzca una opción: ");
	}
	
	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}
	
	private static String pedirString() {
		return teclado.nextLine().toString();
	}
	
	private static double pedirReal() {
		return Double.parseDouble(teclado.nextLine());
	}

}
