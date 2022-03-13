package vehiculos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Vehiculo {
	private String matricula;
	private GamaEnum gama;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	
	
	public Vehiculo(String matricula, String gama, LocalDate fechaSalida) throws VehiculoException {
		super();
		this.matricula = matricula;
		this.gama = GamaEnum.valueOf(gama.toUpperCase());
		this.fechaEntrada = LocalDate.now();
		if (fechaSalida != null) {
			if (fechaSalida.isBefore(fechaEntrada)) {
				throw new VehiculoException("Error, no se ha podido crear el alquiler. La fecha de salida debe ser mayor a la de entrada.");
			}
			this.fechaSalida = fechaSalida;
		}
		else {
			this.fechaSalida = null;
		}
	}

	public String getMatricula() throws VehiculoException {
		return matricula;
	}
	
	public String getGama() {
		return gama.toString();
	}
	
	public void setGama(String gama) {
		this.gama = GamaEnum.valueOf(gama.toUpperCase());
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public double getPrecio() {
		if (this.fechaSalida == null) {
			this.fechaSalida = LocalDate.now();
		}
		double resultado = gama.getPrecio() * (int) fechaEntrada.until(fechaSalida, ChronoUnit.DAYS);
		return resultado;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", gama=" + gama + ", fechaEntrada=" + fechaEntrada
				+ ", fechaSalida=" + fechaSalida + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

}
