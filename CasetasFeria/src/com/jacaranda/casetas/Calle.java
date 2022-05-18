package com.jacaranda.casetas;

import java.util.ArrayList;
import java.util.Objects;

public class Calle {
	private String calle;
	private ArrayList<Caseta> casetas;
	
	public Calle(String calle) {
		this.calle = calle;
		this.casetas = new ArrayList<>();
	}
	
	public void addCaseta(String nombre, String numero, String modulos, String clase, String id, String idCalle) {
		Caseta nuevaCaseta = new Caseta(nombre, numero, modulos, clase, id, idCalle);
		this.casetas.add(nuevaCaseta);
	}
	
	public String getCalle() {
		return this.calle;
	}
	
	public String casetasTipo(String tipo) {
		StringBuilder resultado = new StringBuilder();
		for(Caseta aux : this.casetas) {
			if(aux.getClase().equals(tipo)) {
				resultado.append(aux + "\n");
			}
		}
		return resultado.toString();
	}
	
	public String casetasTipoDistinto() {
		StringBuilder resultado = new StringBuilder();
		for(Caseta aux : this.casetas) {
			if(!aux.getClase().equals("FAMILIAR") && !aux.getClase().equals("DISTRITO")) {
				resultado.append(aux + "\n");
			}
		}
		return resultado.toString();
	}
	
	public int numCasetas(String tipo) {
		int resultado = 0;
		for(Caseta aux : this.casetas) {
			if(aux.getClase().equals(tipo)) {
				resultado ++;
			}
		}
		return resultado;
	}
	
	public void borrarCaseta(String id) {
		Caseta aux = new Caseta(null, null, null, null, id, null);
		this.casetas.remove(aux);
	}
	
	@Override
	public String toString() {
		return "Calle [calle=" + calle + ", casetas=" + casetas + "]\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(calle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calle other = (Calle) obj;
		return Objects.equals(calle, other.calle);
	}
	
}
