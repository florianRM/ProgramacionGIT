package com.jacaranda.billete;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Billete {
	private LocalDateTime fechaCompra;
	private String nombrePasajero;
	private LocalDateTime fechaSalida;
	private LocalDateTime fechaVuelta;
	private String nombreAerolinia;
	private PrecioBilletes tipoBillete;
	
	public Billete(String nombrePasajero, LocalDateTime fechaSalida, String nombreAerolinia) throws BilleteException {
		super();
		this.nombrePasajero = nombrePasajero;
		this.fechaSalida = introducirFecha(fechaSalida);
		this.nombreAerolinia = nombreAerolinia;
		this.fechaCompra = LocalDateTime.now();
	}

	public Billete(String nombrePasajero, LocalDateTime fechaSalida, LocalDateTime fechaVuelta,
			String nombreAerolinia) throws BilleteException {
		super();
		this.nombrePasajero = nombrePasajero;
		this.fechaSalida = introducirFecha(fechaSalida);
		this.fechaVuelta = introducirFecha(fechaVuelta);
		this.nombreAerolinia = nombreAerolinia;
		this.fechaCompra = LocalDateTime.now();
	}
	
	//Comprobamos que la fecha introducida no es anterior a la actual
	private LocalDateTime introducirFecha(LocalDateTime fecha) throws BilleteException {
		if(fecha.isBefore(LocalDateTime.now())) {
			throw new BilleteException("La fecha no puede ser anterior a la actual.");
		}
		return fecha;
	}
	
	//Devolvemos la diferencia de días que hay con la fecha de salida y la actual
	private int diferenciaFecha() {
		return (int) this.fechaCompra.until(fechaSalida, ChronoUnit.DAYS);
	}
	
	public double calcularPrecio(String tipoBillete, int numPersonas) throws BilleteException {
		PrecioBilletes tipo;
		int diasDiferencia = diferenciaFecha();
		double precio;
		//Si el tipo de billete no es correcto lanzamos una excepción.
		try {
			tipo = PrecioBilletes.valueOf(tipoBillete.toUpperCase());
			this.tipoBillete = tipo;
		} catch (Exception e) {
			throw new BilleteException("El tipo de billete es incorrecto.");
		}
		//Revisamos si hay 5 días o más de diferencia para aplicar el descuento
		if(diasDiferencia >= 5) {
			precio = (tipo.getPrecio() - (tipo.getPrecio() * 0.3)) * numPersonas;
		} else {
			precio = tipo.getPrecio() * numPersonas;
		}
		
		return precio;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public String getNombrePasajero() {
		return nombrePasajero;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public LocalDateTime getFechaVuelta() {
		return fechaVuelta;
	}

	public String getNombreAerolinia() {
		return nombreAerolinia;
	}
	
	public double getPrecioBillete() {
		return tipoBillete.getPrecio();
	}

	@Override
	public String toString() {
		return "Billete [fechaCompra=" + fechaCompra + ", nombrePasajero=" + nombrePasajero + ", fechaSalida="
				+ fechaSalida + ", fechaVuelta=" + fechaVuelta + ", nombreAerolinia=" + nombreAerolinia + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaSalida, nombrePasajero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Billete other = (Billete) obj;
		return Objects.equals(fechaSalida, other.fechaSalida) && Objects.equals(nombrePasajero, other.nombrePasajero);
	}
	
}
