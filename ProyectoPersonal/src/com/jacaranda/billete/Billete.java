package com.jacaranda.billete;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Billete {
	private LocalDateTime fechaCompra;
	private String dni;
	private LocalDateTime fechaSalida;
	private LocalDateTime fechaVuelta;
	private String nombreAerolinia;
	private PrecioBilletes tipoBillete;

	public Billete(String dni, String tipoBillete, LocalDateTime fechaSalida, String nombreAerolinia)
			throws BilleteException {
		super();
		this.dni = dni;
		this.fechaSalida = introducirFecha(fechaSalida);
		this.nombreAerolinia = nombreAerolinia;
		this.fechaCompra = LocalDateTime.now();
		validarTipoBillete(tipoBillete);
	}

	public Billete(String dni, String tipoBillete, LocalDateTime fechaSalida, LocalDateTime fechaVuelta,
			String nombreAerolinia) throws BilleteException {
		super();
		this.dni = dni;
		this.fechaSalida = introducirFecha(fechaSalida);
		this.fechaVuelta = introducirFecha(fechaVuelta);
		this.nombreAerolinia = nombreAerolinia;
		this.fechaCompra = LocalDateTime.now();
		validarTipoBillete(tipoBillete);
	}

	// Comprobamos que la fecha introducida no es anterior a la actual
	private LocalDateTime introducirFecha(LocalDateTime fecha) throws BilleteException {
		if (fecha.isBefore(LocalDateTime.now())) {
			throw new BilleteException("La fecha no puede ser anterior a la actual.");
		}
		return fecha;
	}

	private void validarTipoBillete(String tipoBillete) throws BilleteException {
		PrecioBilletes tipo = null;
		try {
			tipo = PrecioBilletes.valueOf(tipoBillete.toUpperCase());
		} catch (Exception e) {
			throw new BilleteException("El tipo de billete no existe.");
		}
		this.tipoBillete = tipo;
	}

	// Devolvemos la diferencia de d�as que hay con la fecha de salida y la actual
	private int diferenciaFecha() {
		return (int) this.fechaCompra.until(fechaSalida, ChronoUnit.DAYS);
	}

	public double calcularPrecio(PrecioBilletes tipoBillete) throws BilleteException {
		return this.getPrecioBillete();
	}

	public double calcularPrecioPremium(PrecioBilletes tipoBillete) throws BilleteException {
		int diasDiferencia = diferenciaFecha();

		double precio;
		// Revisamos si hay 5 d�as o m�s de diferencia para aplicar el descuento
		if (diasDiferencia >= 5) {
			precio = tipoBillete.getPrecio() - (tipoBillete.getPrecio() * 0.3);
		} else {
			precio = tipoBillete.getPrecio();
		}

		return precio;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public String getDni() {
		return dni;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public PrecioBilletes getTipoBillete() {
		return this.tipoBillete;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) throws BilleteException {
		this.fechaSalida = introducirFecha(fechaSalida);
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
		return "Billete [fechaCompra=" + fechaCompra + ", dni=" + dni + ", fechaSalida=" + fechaSalida
				+ ", fechaVuelta=" + fechaVuelta + ", nombreAerolinia=" + nombreAerolinia + ", tipoBillete="
				+ this.tipoBillete + ", precio=" + this.tipoBillete.getPrecio() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaSalida, dni);
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
		return Objects.equals(fechaSalida, other.fechaSalida) && Objects.equals(dni, other.dni);
	}

}
