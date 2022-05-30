package com.jacaranda.usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

import com.jacaranda.billete.Billete;
import com.jacaranda.billete.BilleteException;

public abstract class Usuario {
	private String login;
	private String contrasenna;
	protected double saldo;
	protected LinkedList<Billete> listaBilletes;
	protected int premium;

	protected Usuario(String login, String contrasenna) {
		this.login = login;
		this.contrasenna = contrasenna;
		this.listaBilletes = new LinkedList<>();
	}

	protected Usuario(String login, String contrasenna, double saldo) throws UsuarioException {
		this.login = login;
		this.contrasenna = contrasenna;
		setSaldo(saldo);
		this.listaBilletes = new LinkedList<>();
	}

	public void comprarBillete(String dni, String tipoBillete, LocalDateTime fechaSalida, String nombreAerolinia)
			throws UsuarioException {
		try {
			this.listaBilletes.add(new Billete(dni, tipoBillete, fechaSalida, nombreAerolinia));
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	public void comprarBillete(String dni, String tipoBillete, LocalDateTime fechaSalida, LocalDateTime fechaVuelta,
			String nombreAerolinia) throws UsuarioException {
		try {
			this.listaBilletes.add(new Billete(dni, tipoBillete, fechaSalida, fechaVuelta, nombreAerolinia));
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	public void borrarDelCarrito(String dni, LocalDateTime fechaIda) throws UsuarioException {
		try {
			Billete aux = new Billete(dni, "Economy", fechaIda, null);
			this.listaBilletes.remove(aux);
		} catch (BilleteException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	protected abstract String calcularPrecio() throws UsuarioException;

	public void setSaldo(double saldo) throws UsuarioException {
		if (saldo < 0) {
			throw new UsuarioException("El saldo no puede ser menor a 0");
		}
		this.saldo = saldo;
	}

	public void anniadirSaldo(double saldo) throws UsuarioException {
		if (saldo < 0) {
			throw new UsuarioException("El saldo no puede ser menor a 0");
		}
		this.saldo += saldo;
	}

	public void guardarBilletes() throws UsuarioException, SQLException {
		Connection conexion;
		Statement instruccion;
		try {
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLCDB.localdomain", "dummy",
					"dummy");
			instruccion = conexion.createStatement();
		} catch (SQLException e) {
			throw new UsuarioException(e.getMessage());
		}
		Date fechaCom;
		LocalDateTime fechaCompra;
		Time horaCom;
		Date fechaId;
		LocalDateTime fechaIda;
		Time horaIda;
		Date fechaVuelt;
		LocalDateTime fechaVuelta;
		Time horaVuelta;
		String fechaVueltaString = null;
		for (Billete aux : this.listaBilletes) {
			fechaCompra = aux.getFechaCompra();
			fechaCom = Date.valueOf(fechaCompra.getYear() + "-" + fechaCompra.getMonthValue()+  "-" + fechaCompra.getDayOfMonth());
			horaCom = Time.valueOf(fechaCompra.getHour() + ":" + fechaCompra.getMinute() + ":" + fechaCompra.getSecond());
			String fechaCompraString = fechaCom + " " + horaCom;
			
			fechaIda = aux.getFechaSalida();
			fechaId = Date.valueOf(fechaIda.getYear() + "-" + fechaIda.getMonthValue()+  "-" + fechaIda.getDayOfMonth());
			horaIda = Time.valueOf(fechaIda.getHour() + ":" + fechaIda.getMinute() + ":" + fechaIda.getSecond());
			String fechaIdaString = fechaId + " " + horaIda;
			
			if(aux.getFechaVuelta() != null) {
				fechaVuelta = aux.getFechaVuelta();
				fechaVuelt = Date.valueOf(fechaVuelta.getYear() + "-" + fechaVuelta.getMonthValue()+  "-" + fechaVuelta.getDayOfMonth());
				horaVuelta = Time.valueOf(fechaVuelta.getHour() + ":" + fechaVuelta.getMinute() + ":" + fechaVuelta.getSecond());
				fechaVueltaString = fechaVuelt + " " + horaVuelta;
				
				instruccion.executeQuery("INSERT INTO BILLETES VALUES('" + this.login + "', '" + aux.getDni()
				+ "', TO_DATE('"+ fechaCompraString +"', 'yyyy-MM-dd hh24:mi:ss'), TO_DATE('"
				+ fechaIdaString + "', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('" + fechaVueltaString
				+ "', 'YYYY-MM-DD HH24:MI:SS'), '" + aux.getNombreAerolinia() + "', '" + aux.getTipoBillete() + "')");
			} else {
				instruccion.executeQuery("INSERT INTO BILLETES VALUES('" + this.login + "', '" + aux.getDni()
				+ "', TO_DATE('"+ fechaCompraString +"', 'yyyy-MM-dd hh24:mi:ss'), TO_DATE('"
				+ fechaIdaString + "', 'YYYY-MM-DD HH24:MI:SS'), null, '" + aux.getNombreAerolinia() + "', '" + aux.getTipoBillete() + "')");
			}
			
			
			
		}

		conexion.close();
		instruccion.close();
	}
	
	public String listadoBilletes() {
		StringBuilder resultado = new StringBuilder();
		for (Billete aux : this.listaBilletes) {
			resultado.append(aux.toString() + "\n");
		}
		return resultado.toString();
	}

	public String getLogin() {
		return login;
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
