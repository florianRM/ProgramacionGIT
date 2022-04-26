import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class PalabrasEmpiezan {
	private char letra;
	private Queue<Palabra> palabras;

	public PalabrasEmpiezan(char letra) {
		this.letra = letra;
		this.palabras = new LinkedList<>();
	}

	public void addPalabra(String palabra, String significado) throws DiccionarioException  {
		boolean encontrado = false;
		char inicialPalabra = palabra.toUpperCase().charAt(0);
		Palabra nueva;
		nueva = new Palabra(palabra, significado);
		if (!this.palabras.contains(nueva)) {
			this.palabras.add(nueva);
		}
		for (Iterator<Palabra> iterator = palabras.iterator(); iterator.hasNext() && !encontrado;) {
			Palabra palabra2 = iterator.next();
			if (palabra2.getInicial() != inicialPalabra) {
				throw new DiccionarioException("La incial de la palabra no coincide con la letra.");
			}
			else if (palabra2.getPalabra().equals(palabra)) {
				palabra2.addSignificado(significado);
				encontrado = true;
			}
		}
	}
	
	public boolean borrarPalabra(String palabra) throws DiccionarioException {
		boolean encontrado = false;
		for (Iterator<Palabra> iterator = palabras.iterator(); iterator.hasNext() && !encontrado;) {
			Palabra palabra2 = iterator.next();
			if (palabra2.getPalabra().equalsIgnoreCase(palabra)) {
				this.palabras.remove(palabra2);
				encontrado = true;
			}
		}
		if (!encontrado) {
			throw new DiccionarioException("La palabra que busca no existe.");
		}
		return encontrado;
	}

	public char getLetra() {
		return letra;
	}

	@Override
	public String toString() {
		return "\nLetra=" + letra + "\n"+ palabras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(letra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PalabrasEmpiezan other = (PalabrasEmpiezan) obj;
		return letra == other.letra;
	}
	
}
