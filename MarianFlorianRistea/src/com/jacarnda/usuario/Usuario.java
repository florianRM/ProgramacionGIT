package com.jacarnda.usuario;

import java.util.Objects;

public class Usuario {
	private String login;
	private String pass;
	
	public Usuario(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}
	//Nos devuelve solo el login del usuario
	public String getLogin() {
		return login;
	}

	public boolean setPass(String pass, String nuevoPass) {
		boolean esIgual = false;
		if (pass.equals(this.pass)) {
			this.pass = nuevoPass;
			esIgual = true;
		}
		return esIgual;
	}
	
	public boolean checkPass(String pass) {
		boolean passCorrecto = false;
		if (pass.equals(this.pass)) {
			passCorrecto = true;
		}
		return passCorrecto;
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
