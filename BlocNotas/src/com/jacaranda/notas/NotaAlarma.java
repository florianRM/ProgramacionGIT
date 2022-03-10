package com.jacaranda.notas;

import java.time.LocalDateTime;

public class NotaAlarma extends Nota implements Activable {
	private LocalDateTime fechaAlarma;
	private static final int MINUTOSREPETIRPORDEFECTO = 5;
	private int minutosRepetir;
	private boolean activado;
	
	public NotaAlarma(String texto, LocalDateTime fechaAlarma, boolean estado) throws NotaAlarmaException {
		super(texto);
		setFechaAlarma(fechaAlarma);
		this.activado = estado;
		this.minutosRepetir = NotaAlarma.MINUTOSREPETIRPORDEFECTO;
	}
	
	public NotaAlarma(String texto, LocalDateTime fechaAlarma, int numMinutos) throws NotaAlarmaException {
		super(texto);
		if (numMinutos < 0) {
			throw new NotaAlarmaException("Error. El número de minutos debe ser mayor a 0.");
		}
		setFechaAlarma(fechaAlarma);
		this.minutosRepetir = numMinutos;
	}
	
	private void setFechaAlarma(LocalDateTime fechaAlarma) throws NotaAlarmaException {
		if (fechaAlarma.isBefore(LocalDateTime.now())) {
			throw new NotaAlarmaException("Error. La fecha de la alarma no puede ponerse anterior a la fecha actual.");
		}
		else {
			this.fechaAlarma = fechaAlarma;
		}
	}
	
	public void activar() {
		this.activado = true;
	}
	
	public void desactivar() {
		this.activado = false;
	}
	
	public boolean isActivado() {
		return this.activado;
	}

	@Override
	public String toString() {
		return "NotaAlarma [fechaAlarma=" + fechaAlarma + ", minutosRepetir=" + minutosRepetir + ", activado="
				+ activado + "]";
	}
	
}
