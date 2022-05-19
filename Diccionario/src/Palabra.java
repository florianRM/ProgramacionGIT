import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Palabra {
	private String palabra;
	private Set<String> significados;
	
	public Palabra(String palabra) throws DiccionarioException {
		if (palabra == null || palabra.isEmpty()) {
			throw new DiccionarioException("La palabra no puede ser nula.");
		}
		this.palabra = palabra;
		this.significados = new HashSet<>();
	}
	
	public Palabra(String palabra, String significado) throws DiccionarioException {
		if (palabra == null || palabra.isEmpty()) {
			throw new DiccionarioException("La palabra no puede ser nula.");
		}
		if (significado == null || significado.isEmpty()) {
			throw new DiccionarioException("El significado no puede ser nulo o estar vacío.");
		}
		this.palabra = palabra;
		this.significados = new HashSet<>();
		this.significados.add(significado);
	}
	
	public void addSignificado(String significado) throws DiccionarioException {
		if (significado == null || significado.isEmpty()) {
			throw new DiccionarioException("El significado no puede ser nulo o estar vacío.");
		}
		this.significados.add(significado);
	}
	
	public String getSignificado() {
		String resultado = "";
		for(String aux : this.significados) {
			resultado += aux + "\n";
		}
		return resultado;
	}
	
	public String getPalabra() {
		return palabra;
	}
	
	public char getInicial() {
		return this.palabra.toUpperCase().charAt(0);
	}

	@Override
	public String toString() {
		return "\nPalabra=" + palabra + ", significados=" + significados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(palabra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palabra other = (Palabra) obj;
		return Objects.equals(palabra, other.palabra);
	}

}
