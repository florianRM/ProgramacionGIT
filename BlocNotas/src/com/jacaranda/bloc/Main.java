package com.jacaranda.bloc;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.jacaranda.notas.NotaAlarmaException;

public class Main {
	private static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String texto;
		int posicion, opc, tipoBloc, min;
		LocalDateTime fecha;
		Bloc nuevoBloc;
		
		nuevoBloc = new Bloc(pedirNombreBloc());
		do {
			menu();
			opc = pedirInteger();
			switch (opc) {
			case 1:
				tipoBloc = tipoBloc();
				if (tipoBloc == 1) {
					texto = textoNota();
					nuevoBloc.addNota(texto);
				}
				else if (tipoBloc == 2) {
					texto = textoNota();
					try {
						nuevoBloc.addNota(texto, pedirFecha(), estadoAlarma());
					} catch (NotaAlarmaException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				else if (tipoBloc == 3){
					texto = textoNota();
					fecha = pedirFecha();
					System.out.println("Introduzca cada cuantos minutos quiere que se repita la alarma: ");
					min = pedirInteger();
					try {
						nuevoBloc.addNota(texto, fecha, min);
					} catch (NotaAlarmaException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				else {
					System.out.println("Error. Debe escoger una de las 3 opciones.");
				}
				break;
			case 2:
				posicion = pedirPosicion();
				texto = textoNota();
				try {
					nuevoBloc.updateNota(posicion, texto);
				} catch (BlocException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Introduzca la posición de la nota que quiere eliminar: ");
				posicion = pedirInteger();
				try {
					nuevoBloc.borrarNota(posicion);
				} catch (BlocException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				break;
			case 4:
				posicion = pedirPosicion();
				try {
					System.out.println(nuevoBloc.getNota(posicion));
				} catch (BlocException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				posicion = pedirPosicion();
				if (estadoAlarma() == true) {
					try {
						nuevoBloc.activa(posicion);
					} catch (BlocException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				else {
					try {
						nuevoBloc.desactiva(posicion);
					} catch (BlocException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				break;
			case 6:
				System.out.println(nuevoBloc.getNombre());
				break;
			case 7:
				System.out.println(nuevoBloc.toString());
				break;
			case 8:
				System.out.println("Saliendo");
				break;
			default:
				System.out.println("Error. La opción escogida no es correcta.");
				break;
			}
			
		} while (opc != 8);
		
	}
	
	private static String pedirNombreBloc() {
		String nombreGrupo;
		System.out.print("Introduzca el nombre del bloc: ");
		nombreGrupo = pedirString();
		return nombreGrupo;
	}
	
	private static int pedirPosicion() {
		int posicion;
		System.out.print("Introduzca la posición de la nota que quiere modificar: ");
		posicion = pedirInteger();
		return posicion;
	}
	
	private static String textoNota() {
		String texto;
		System.out.print("Introduzca el texto de la nota: ");
		texto = pedirString();
		return texto;
	}
	
	private static LocalDateTime pedirFecha() {
		int dia, mes, annio, hora, min;
		System.out.print("Introduzca la hora de la alarma: ");
		hora = pedirInteger();
		System.out.print("Introduzca los minutos de la alarma: ");
		min = pedirInteger();
		System.out.print("Introduzca el día de la alarma: ");
		dia = pedirInteger();
		System.out.print("Introduzca el mes de la alarma: ");
		mes = pedirInteger();
		System.out.print("Introduzca el año de la alarma: ");
		annio = pedirInteger();
		return LocalDateTime.of(annio, mes, dia, hora, min);
	}
	
	private static boolean estadoAlarma() {
		boolean resultado = false;
		int opc;
		System.out.print("1. Alarma activada.\n"
				+ "2. Alarma desactivada.\n"
				+ "Introduzca una opción: ");
		opc = pedirInteger();
		if (opc == 1) {
			resultado = true;
		}
		return resultado;
	}
	
	private static int tipoBloc() {
		int tipoBloc;
		System.out.print("1. Nota\n"
				+ "2. Nota con alarma\n"
				+ "3. Nota con alarma y minutos de repetición\n"
				+ "Introduzca una opción: ");
		tipoBloc = pedirInteger();
		return tipoBloc;
	}
	
	private static void menu() {
		System.out.print("1. Crear nota.\n"
				+ "2. Modificar nota.\n"
				+ "3. Borrar nota\n"
				+ "4. Buscar nota.\n"
				+ "5. Activar o desactivar alarma.\n"
				+ "6. Ver nombre del bloc.\n"
				+ "7. Ver todas las notas.\n"
				+ "8. Salir.\n"
				+ "Introduzca una opción: ");
	}
	
	private static String pedirString() {
		return teclado.nextLine().toString();
	}
	
	private static int pedirInteger() {
		return Integer.parseInt(teclado.nextLine());
	}

}
