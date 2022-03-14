package main;

import java.time.LocalDate;
import java.util.Scanner;

import vehiculos.Coche;
import vehiculos.Furgoneta;
import vehiculos.Microbus;
import vehiculos.Vehiculo;
import vehiculos.VehiculoException;

public class Main {
	private static Scanner teclado = new Scanner(System.in);
	private static final int MAXVEHICULOS = 200;
	private static Vehiculo[] flota = new Vehiculo[MAXVEHICULOS];
	private static int numVehiculos = 0;
	private static int opc, plazas, dia, mes, annio;
	private static String tipoVehiculo, matricula, combustible, gama;
	private static double peso;

	public static void main(String[] args) throws VehiculoException {
		// TODO Auto-generated method stub
		
		do {
			menu();
			opc = pedirEntero();
			switch (opc) {
			case 1:
				System.out.print("¿Que tipo de vehículo es?(Coche, microbús o furgoneta): ");
				tipoVehiculo = pedirString().toLowerCase();
				switch (tipoVehiculo) {
				case "coche":
					pedirDatos();
					System.out.print("Introduzca el combustible: ");
					Main.combustible = pedirString();
					try {
						flota[numVehiculos] = new Coche(matricula, gama, LocalDate.of(annio, mes, dia) ,combustible);
						Main.numVehiculos ++;
					} catch (VehiculoException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "microbus":
					pedirDatos();
					System.out.print("Introduzca numero de asientos: ");
					Main.plazas = pedirEntero();
					try {
						flota[numVehiculos] = new Microbus(matricula, gama, LocalDate.of(annio, mes, dia), plazas);
						Main.numVehiculos ++;
					} catch (VehiculoException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				case "furgoneta":
					pedirDatos();
					System.out.print("Introduzca el peso: ");
					Main.peso = pedirReal();
					try {
						flota[numVehiculos] = new Furgoneta(matricula, gama, LocalDate.of(annio, mes, dia), peso);
						Main.numVehiculos ++;
					} catch (VehiculoException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("El tipo de vehículo no existe.");
					break;
				}
				break;
			case 2:
				boolean encontrado = false;
				System.out.print("Introduzca la matrícula: ");
				matricula = pedirString();
				for (int i=0; i < numVehiculos && encontrado != true; i++) {
					if (flota[i].getMatricula().equals(matricula) && flota[i] instanceof Coche) {
						System.out.println(flota[i].getPrecio());
					}
					else if (flota[i].getMatricula().equals(matricula) && flota[i] instanceof Furgoneta) {
						System.out.println(flota[i].getPrecio());
					}
					else if (flota[i].getMatricula().equals(matricula) && flota[i] instanceof Microbus) {
						System.out.println(flota[i].getPrecio());
					}
				}
				if (Main.numVehiculos == 0) {
					System.out.println("No existe ningún vehículo.");
				}
				break;
			case 3:
				System.out.println("Gracias por usar nuestros servicios.");
				break;
			default:
				System.out.println("La opción introducida es incorrecta");
				break;
			}
		} while (opc != 3);
	}
	
	private static void menu() {
		System.out.print("1. Alta de vehículo. \n"
				+ "2. Cálculo de precio de alquiler. \n"
				+ "3. Salir. \n"
				+ "Introduzca una opción: ");
	}
	
	private static void pedirDatos() {
		System.out.print("Introduzca la matrícula: ");
		Main.matricula = pedirString();
		System.out.print("Introduzca la gama: ");
		Main.gama = pedirString();
		System.out.println("Introduzca el día de la devolución: ");
		Main.dia = pedirEntero();
		System.out.println("Introduzca el mes de la devolución: ");
		Main.mes = pedirEntero();
		System.out.println("Introduzca el año de la devolución: ");
		Main.annio = pedirEntero();
	}
	
	private static String pedirString() {
		return teclado.nextLine().toString();
	}
	
	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}
	
	private static double pedirReal() {
		return Double.parseDouble(teclado.nextLine());
	}

}

