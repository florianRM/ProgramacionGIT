package com.jacaranda.usuario;

import java.time.LocalDateTime;

import com.jacaranda.billete.Billete;
import com.jacaranda.billete.BilleteException;

public class Premium extends Usuario {
	
	public Premium(String login, String contrasenna) throws UsuarioException {
		super(login, contrasenna);
		super.premium = true;
		pagarPremium();
	}
	
	private void pagarPremium() throws UsuarioException {
		if(super.saldo < 15) {
			throw new UsuarioException("El saldo es insuficiente.");
		}
		super.saldo -= 15;
	}

	@Override
	public void cancelarBillete(String nombrePasajero, LocalDateTime fecha) throws UsuarioException {
		Billete aux;
		try {
			aux = new Billete(nombrePasajero, fecha, null);
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
		int posicion = listaBilletes.indexOf(aux);
		super.saldo += listaBilletes.get(posicion).getPrecioBillete();
		listaBilletes.remove(posicion);
	}
}
