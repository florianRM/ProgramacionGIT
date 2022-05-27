package com.jacaranda.usuario;

import java.time.LocalDateTime;

import com.jacaranda.billete.Billete;
import com.jacaranda.billete.BilleteException;

public class Premium extends Usuario {
	
	public Premium(String login, String contrasenna, double saldo) throws UsuarioException {
		super(login, contrasenna, saldo);
		super.premium = 1;
		pagarPremium();
	}
	
	private void pagarPremium() throws UsuarioException {
		if(super.saldo < 15) {
			throw new UsuarioException("El saldo es insuficiente.");
		}
		super.saldo -= 15;
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
			super.saldo += listaBilletes.get(posicion).getPrecioBillete();
			listaBilletes.remove(posicion);
		}
	}
}
