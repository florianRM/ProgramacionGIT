package vehiculos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Microbus extends Vehiculo {
	private int numAsientos;
	private static final int PRECIONUMASIENTOS = 5;

	public Microbus(String matricula, String gama, LocalDate fechaSalida, int numAsientos) throws VehiculoException {
		super(matricula, gama, fechaSalida);
		this.numAsientos = numAsientos;
	}

	public int getNumAsientos() {
		return numAsientos;
	}

	public void setNumAsientos(int numAsientos) {
		this.numAsientos = numAsientos;
	}

	@Override
	public String toString() {
		return "Microbus [numAsientos=" + numAsientos + ", toString()=" + super.toString() + "]";
	}
	
	@Override
	public double getPrecio() {
		return (super.getPrecio() + (Microbus.PRECIONUMASIENTOS * numAsientos)) * this.getFechaEntrada().until(this.getFechaSalida(), ChronoUnit.DAYS);
	}
	
}
