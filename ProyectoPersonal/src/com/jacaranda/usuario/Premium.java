package com.jacaranda.usuario;

import java.time.LocalDateTime;
import java.util.Iterator;

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
	public String calcularPrecio() throws UsuarioException {
		double total = 0;
		for(Billete aux : this.listaBilletes) {
			try {
				total += aux.calcularPrecioPremium(aux.getTipoBillete());
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

}
