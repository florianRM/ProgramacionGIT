package vehiculos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Furgoneta extends Vehiculo {
	private double peso;
	private static final double PRECIOSUMAR = 0.5;

	public Furgoneta(String matricula, String gama, LocalDate fechaSalida, double peso) throws VehiculoException {
		super(matricula, gama, fechaSalida);
		if (peso < 1000) {
			throw new VehiculoException("El peso no puede ser menor a 1000 kg.");
		}
		this.peso = peso;
	}
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) throws VehiculoException {
		if (peso < 1000) {
			throw new VehiculoException("El peso no puede ser menor a 1000 kg.");
		}
		this.peso = peso;
	}
	
	@Override
	public double getPrecio() {
		return super.getPrecio() + (Furgoneta.PRECIOSUMAR)* this.getFechaEntrada().until(this.getFechaSalida(), ChronoUnit.DAYS);
	}

	@Override
	public String toString() {
		return "Furgoneta [peso=" + peso + ", toString()=" + super.toString() + "]";
	}

}
