package com.jacaranda.billete;

public enum PrecioBilletes {
	ECONOMY(300), BUSINNES(800), FIRST(1200);
	private double precio;
	
	PrecioBilletes(int precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return this.precio;
	}
}
