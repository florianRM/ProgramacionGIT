package com.jacaranda.principal;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jacaranda.casetas.Calle;
import com.jacaranda.casetas.Caseta;

public class Main {
	public static final List<Calle> listaCalles = new ArrayList<>();
	public static final Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		int opc;
		String calle;
		final String TIPO_FAMILIAR = "FAMILIAR";
		final String TIPO_DISTRITO = "DISTRITO";
		crearCasetas();
		Calle aux;

		do {
			menu();
			opc = pedirEntero();

			switch (opc) {
			case 1:
				System.out.print("Introduzca la calle que quiere buscar:");
				calle = pedirCadena();
				aux = new Calle(calle.toUpperCase());
				String casetas = listaCalles.get(listaCalles.indexOf(aux)).toString();
				System.out.println(casetas);
				break;
			case 2:
				for(Calle casetasFam : listaCalles) {
					System.out.println(casetasFam.casetasTipo(TIPO_FAMILIAR));
				}
			case 3:
				for(Calle casetasFam : listaCalles) {
					System.out.println(casetasFam.casetasTipo(TIPO_DISTRITO));
				}
				break;
			case 4:
				for(Calle casetasFam : listaCalles) {
					System.out.println(casetasFam.casetasTipoDistinto());
				}
				break;
			case 5:
				for(Calle calles : listaCalles) {
					System.out.println("En la calle " + calles.getCalle() + " hay " + calles.numCasetas(TIPO_FAMILIAR) + " casetas de tipo familiar.");
				}
			break;
			case 6:
				for(Calle calles : listaCalles) {
					System.out.println("En la calle " + calles.getCalle() + " hay " + calles.numCasetas(TIPO_DISTRITO) + " casetas de tipo distrito.");
				}
			case 7:
				System.out.print("Introduzca la calle de la caseta:");
				calle = pedirCadena();
				System.out.print("Introduzca el id de la caseta que quiere borrar:");
				String id = pedirCadena();
				aux = new Calle(calle);
				
				listaCalles.get(listaCalles.indexOf(aux)).borrarCaseta(id);
				break;
			case 8:
				System.out.println("Saliendo");
				break;
			default:
				System.out.println("La opci�n introducida es err�nea.");
				break;
			}
		} while (opc != 8);
	}

	private static void menu() {
		System.out.print("1.Mostrar todas las casetas de una calle.\n"
				+ "2.Mostrar todas las casetas de tipo familiar.\n" + "3.Mostrar todas las casetas de tipo distrito.\n"
				+ "4.Mostrar todas las casetas que no sean ni familiares ni distritos.\n"
				+ "5.Mostrar numero de casetas de tipo familiar en cada calle.\n"
				+ "6.Mostrar numero de casetas de tipo distrito en cada calle.\n" + "7.Eliminar una caseta.\n"
				+ "8.Salir\n" + "Introduzca una opcion:");
	}

	private static int pedirEntero() {
		return Integer.parseInt(teclado.nextLine());
	}

	private static String pedirCadena() {
		return teclado.nextLine();
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

			for (int i = 0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);

				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;
					nombre = element.getElementsByTagName("TITULO").item(0).getTextContent();
					calle = element.getElementsByTagName("CALLE").item(0).getTextContent();
					numero = element.getElementsByTagName("NUMERO").item(0).getTextContent();
					modulos = element.getElementsByTagName("MODULOS").item(0).getTextContent();
					clase = element.getElementsByTagName("CLASE").item(0).getTextContent();
					id = element.getElementsByTagName("ID").item(0).getTextContent();
					idCalle = element.getElementsByTagName("ID_CALLE").item(0).getTextContent();

					Calle aux = new Calle(calle);
					if (listaCalles.contains(aux)) {
						listaCalles.get(listaCalles.indexOf(aux)).addCaseta(nombre, numero, modulos, clase, id,
								idCalle);
					} else {
						aux.addCaseta(nombre, numero, modulos, clase, id, idCalle);
						listaCalles.add(aux);
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
