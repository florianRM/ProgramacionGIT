package com.jacaranda.casetas;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Caseta {
	private String nombre;
	private String calle;
	private String numero;
	private String modulos;
	private String clase;
	private String id;
	private String idCalle;
	
	public Caseta(String nombre, String calle, String numero, String modulos, String clase, String id, String idCalle) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.modulos = modulos;
		this.clase = clase;
		this.id = id;
		this.idCalle = idCalle;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCalle() {
		return calle;
	}

	public String getNumero() {
		return numero;
	}

	public String getModulos() {
		return modulos;
	}

	public String getClase() {
		return clase;
	}

	public String getId() {
		return id;
	}

	public String getIdCalle() {
		return idCalle;
	}

	@Override
	public String toString() {
		return "Caseta [nombre=" + nombre + ", calle=" + calle + ", numero=" + numero + ", modulos=" + modulos
				+ ", clase=" + clase + ", id=" + id + ", idCalle=" + idCalle + "]";
	}
	
}
