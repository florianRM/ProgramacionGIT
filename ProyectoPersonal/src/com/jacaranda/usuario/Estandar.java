package com.jacaranda.usuario;

import java.time.LocalDateTime;
import java.util.Iterator;

import com.jacaranda.billete.Billete;
import com.jacaranda.billete.BilleteException;

public class Estandar extends Usuario {

	public Estandar(String login, String contrasenna) {
		super(login, contrasenna);
		super.premium = 0;
	}
	
	@Override
	public String calcularPrecio() throws UsuarioException {
		double total = 0;
		for(Billete aux : this.listaBilletes) {
			try {
				total += aux.calcularPrecio(aux.getTipoBillete());
			} catch (BilleteException e) {
				throw new UsuarioException(e.getMessage());
			}
		}
		if(total > this.saldo) {
			throw new UsuarioException("No tiene suficiente dinero.");
		}
		this.saldo -= total;
		return "El total de los billetes es de " + total + " euros";
	}
	
	public double getSaldo() {
		return super.saldo;
	}
	
	@Override
	public void cancelarBillete(String nombrePasajero, LocalDateTime fecha) throws UsuarioException {
		boolean encontrado = false;
		Iterator<Billete> iterator = super.listaBilletes.iterator();
		while(iterator.hasNext() && !encontrado) {
			Billete aux = iterator.next();
			if(aux.getDni().equalsIgnoreCase(nombrePasajero) && aux.getFechaSalida().equals(fecha)) {
				listaBilletes.remove(aux);
				encontrado = true;
			}
		}
		if(!encontrado) {
			throw new UsuarioException("El billete no existe.");
		}
	}
	
}
