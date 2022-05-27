package com.jacaranda.usuario;

import java.time.LocalDateTime;

import com.jacaranda.billete.Billete;
import com.jacaranda.billete.BilleteException;

public class Estandar extends Usuario {

	public Estandar(String login, String contrasenna) {
		super(login, contrasenna);
		super.premium = 0;
	}
	
	@Override
	public void comprarBillete(String nombrePasajero, String tipoBillete, LocalDateTime fechaSalida, String nombreAerolinia)
			throws UsuarioException {
		super.comprarBillete(nombrePasajero, tipoBillete, fechaSalida, nombreAerolinia);
	}

	@Override
	public void comprarBillete(String nombrePasajero, String tipoBillete, LocalDateTime fechaSalida, LocalDateTime fechaVuelta,
			String nombreAerolinia) throws UsuarioException {
		super.comprarBillete(nombrePasajero, tipoBillete, fechaSalida, fechaVuelta, nombreAerolinia);
	}
	
	@Override
	public String calcularPrecio() throws UsuarioException {
		return super.calcularPrecio();
	}
	
	public double getSaldo() {
		return super.saldo;
	}
	
	@Override
	public void cancelarBillete(String nombrePasajero, LocalDateTime fecha) throws UsuarioException {
		Billete aux;
		try {
			aux = new Billete(nombrePasajero, null, fecha, null);
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
		int posicion = listaBilletes.indexOf(aux);
		if(posicion != -1) {
			listaBilletes.remove(posicion);
		}
	}
	
}
