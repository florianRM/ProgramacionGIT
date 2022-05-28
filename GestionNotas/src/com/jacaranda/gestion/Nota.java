package com.jacaranda.gestion;

import java.time.LocalDate;
import java.util.Objects;

public class Nota {
	private double notaAlumno;
	private LocalDate fecha;
	private Alumnado alumno;
	private Modulo modulo;
	
	public Nota(double nota, LocalDate fecha, Alumnado alumno, Modulo modulo) {
		super();
		this.notaAlumno = nota;
		this.fecha = fecha;
		this.alumno = alumno;
		this.modulo = modulo;
	}

	public double getNotaAlumno() {
		return notaAlumno;
	}

	public void setNotaAlumno(double nota) {
		this.notaAlumno = nota;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Alumnado getAlumno() {
		return alumno;
	}

	public Modulo getModulo() {
		return modulo;
	}
	
	public String escribirFicheroNota() {
		return this.notaAlumno + ", " + this.fecha + ", " + alumno.getDni() + ", " + this.modulo.getNombre();
	}

	@Override
	public String toString() {
		return "Nota [nota=" + notaAlumno + ", fecha=" + fecha + ", alumno=" + alumno + ", modulo=" + modulo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, fecha, modulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(modulo, other.modulo);
	}
}
