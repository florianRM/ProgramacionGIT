package vehiculos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Microbus extends Vehiculo {
	private int numAsientos;
	private static final int PRECIONUMASIENTOS = 5;

	public Microbus(String matricula, String gama, LocalDate fechaSalida, int numAsientos) throws VehiculoException {
		super(matricula, gama, fechaSalida);
		if (numAsientos < 1) {
			throw new VehiculoException("El número de asientos debe ser mayor a 0.");
		}
		this.numAsientos = numAsientos;
	}

	public int getNumAsientos() {
		return numAsientos;
	}

	public void setNumAsientos(int numAsientos) throws VehiculoException {
		if (numAsientos < 1) {
			throw new VehiculoException("El número de asientos debe ser mayor a 0.");
		}
		this.numAsientos = numAsientos;
	}
	
	@Override
	public double getPrecio() {
		return (super.getPrecio() + (Microbus.PRECIONUMASIENTOS * numAsientos)) * this.getFechaEntrada().until(this.getFechaSalida(), ChronoUnit.DAYS);
	}

	@Override
	public String toString() {
		return "Microbus [numAsientos=" + numAsientos + ", toString()=" + super.toString() + "]";
	}
	
}
