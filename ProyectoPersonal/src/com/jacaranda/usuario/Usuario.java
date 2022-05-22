package com.jacaranda.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import com.jacaranda.billete.Billete;
import com.jacaranda.billete.BilleteException;

public abstract class Usuario {
	private String login;
	private String contrasenna;
	protected double saldo;
	protected ArrayList<Billete> listaBilletes;
	protected boolean premium;

	protected Usuario(String login, String contrasenna) {
		this.login = login;
		this.contrasenna = contrasenna;
	}
	
	protected Usuario(String login, String contrasenna, double saldo) throws UsuarioException {
		this.login = login;
		this.contrasenna = contrasenna;
		setSaldo(saldo);
		this.listaBilletes = new ArrayList<>();
	}

	public void comprarBillete(String nombrePasajero, LocalDateTime fechaSalida, String nombreAerolinia)
			throws UsuarioException {
		Billete nuevoBillete;
		try {
			nuevoBillete = new Billete(nombrePasajero, fechaSalida, nombreAerolinia);
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	public void comprarBillete(String nombrePasajero, LocalDateTime fechaSalida, LocalDateTime fechaVuelta,
			String nombreAerolinia) throws UsuarioException {
		try {
			this.listaBilletes.add(new Billete(nombrePasajero, fechaSalida, fechaVuelta, nombreAerolinia));
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	public abstract void cancelarBillete(String nombrePasajero, LocalDateTime fecha) throws UsuarioException;
	
	public void setSaldo(double saldo) throws UsuarioException {
		if(saldo < 0) {
			throw new UsuarioException("El saldo no puede ser menor a 0");
		}
		this.saldo = saldo;
	}
	
	public void anniadirSaldo(double saldo) throws UsuarioException {
		if(saldo < 0) {
			throw new UsuarioException("El saldo no puede ser menor a 0");
		}
		this.saldo += saldo;
	}
	
	public String listadoBilletes() {
		StringBuilder resultado = new StringBuilder();
		for(Billete aux : this.listaBilletes) {
			resultado.append(aux.toString() + "\n");
		}
		return resultado.toString();
	}
	
	public String listadoBilletesPorNombre(String nombre) {
		StringBuilder resultado = new StringBuilder();
		for(Billete aux : this.listaBilletes) {
			if(aux.getNombrePasajero().equals(nombre)) {
				resultado.append(aux.toString() + "\n");
			}
		}
		return resultado.toString();
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", contrasenna=" + contrasenna + ", saldo=" + saldo + ", listaBilletes="
				+ listaBilletes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(login, other.login);
	}
	
}
