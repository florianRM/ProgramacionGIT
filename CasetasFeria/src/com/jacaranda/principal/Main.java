package com.jacaranda.principal;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jacaranda.casetas.Caseta;

public class Main {
	public static ArrayList<Caseta> listaCasetas = new ArrayList<>();
	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		int opc;
		int contador = 0;
		crearCasetas();
		
		do {
			menu();
			opc = pedirEntero();
			
			switch (opc) {
			case 1:
				for (Caseta caseta : listaCasetas) {
					if(caseta.getCalle().equalsIgnoreCase("Juan Belmonte")) {
						System.out.println(caseta.toString());
					}
				}
				break;
			case 2:
				for (Caseta caseta : listaCasetas) {
					if(caseta.getClase().equals("FAMILIAR")) {
						System.out.println(caseta.toString());
					}
				}
				break;
			case 3:
				for (Caseta caseta : listaCasetas) {
					if(caseta.getClase().equals("DISTRITO")) {
						System.out.println(caseta.toString());
					}
				}
				break;
			case 4:
				for (Caseta caseta : listaCasetas) {
					if(!caseta.getClase().equals("FAMILIAR") && !caseta.getClase().equals("DISTRITO")) {
						System.out.println(caseta.toString());
					}
				}
				break;
			case 5:
				contador = 0;
				for (Caseta caseta : listaCasetas) {
					if(caseta.getClase().equals("FAMILIAR")) {
						contador ++;
					}
				}
				System.out.println(contador);
				break;
			case 6:
				contador = 0;
				for (Caseta caseta : listaCasetas) {
					if(caseta.getClase().equals("DISTRITO")) {
						contador ++;
					}
				}
				System.out.println(contador);
				break;
			case 7:
				boolean encontrado = false;
				
				System.out.print("Introduzca el id de la caseta que quiere borrar:");
				String id = pedirCadena();
				
				Iterator<Caseta> iterator;
				iterator = listaCasetas.iterator();
				while(iterator.hasNext() && !encontrado) {
					Caseta caseta = iterator.next();
					if(caseta.getId().equals(id)) {
						listaCasetas.remove(caseta);
						encontrado = true;
					}
				}
				break;
			case 8:
				System.out.println("Saliendo");
				break;
			default:
				System.out.println("La opción introducida es errónea.");
				break;
			}
		} while (opc != 8);
	}
	
	private static void menu() {
		System.out.print("1.Mostrar todas las casetas de una calle.\n"
				+ "2.Mostrar todas las casetas de tipo familiar.\n"
				+ "3.Mostrar todas las casetas de tipo distrito.\n"
				+ "4.Mostrar todas las casetas que no sean ni familiares ni distritos.\n"
				+ "5.Mostrar número de casetas de tipo familiar en cada calle.\n"
				+ "6.Mostrar número de casetas de tipo distrito en cada calle.\n"
				+ "7.Eliminar una caseta.\n"
				+ "8.Salir\n"
				+ "Introduzca una opción:");
	}
	
	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}
	
	private static String pedirCadena() {
		return teclado.nextLine().toString();
	}
	
	private static void crearCasetas() {
		String nombre;
		String calle;
		String numero;
		String modulos;
		String clase;
		String id;
		String idCalle;
		File file = new File("xml/casetasferia.xml");
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			 
			doc.getDocumentElement().normalize();
			 
			NodeList lista = doc.getElementsByTagName("DatosLeidos");
			 
			 
			for(int i=0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
				 
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;
					nombre = element.getElementsByTagName("TITULO").item(0).getTextContent();
					calle = element.getElementsByTagName("CALLE").item(0).getTextContent();
					numero = element.getElementsByTagName("NUMERO").item(0).getTextContent();
					modulos = element.getElementsByTagName("MODULOS").item(0).getTextContent();
					clase = element.getElementsByTagName("CLASE").item(0).getTextContent();
					id = element.getElementsByTagName("ID").item(0).getTextContent();
					idCalle = element.getElementsByTagName("ID_CALLE").item(0).getTextContent();
					
					listaCasetas.add(new Caseta(nombre, calle, numero, modulos, clase, id, idCalle));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
