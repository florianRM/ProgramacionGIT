
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Diccionario {
	
	private HashMap<String, HashSet<String>> listaDePalabras;
	
	public Diccionario() {
		this.listaDePalabras = new HashMap<>();
	}
	
	public boolean addDiccionario(String palabra, String significado) throws DiccionarioException {
		boolean resultado = true;
		HashSet<String> valor = this.listaDePalabras.get(palabra);
		
		if (this.listaDePalabras.get(palabra) == null) {
			HashSet<String> aux = new HashSet<>();
			aux.add(significado);
			if (this.listaDePalabras.put(palabra, aux) == null) {
				resultado = false;
			}
		}
		else {
			valor.add(significado);
		}
		return resultado;
	}
	
	public boolean removePalabra(String palabra) throws DiccionarioException {
		boolean encontrado = false;
		if(this.listaDePalabras.containsKey(palabra)) {
			this.listaDePalabras.remove(palabra);
			encontrado = true;
		} else {
			throw new DiccionarioException("La palabra buscada no existe.");
		}
		return encontrado;
	}
	
	public String buscarSignificado(String palabra) throws DiccionarioException {
		String resultado = "";
		if(this.listaDePalabras.containsKey(palabra)) {
			resultado = this.listaDePalabras.get(palabra).toString();
		} else {
			throw new DiccionarioException("La palabra buscada no existe.");
		}
		return resultado;
	}
	
	public String buscarPalabrasEmpiezen(String inicial) {
		StringBuilder resultado = new StringBuilder();
		for(String aux : this.listaDePalabras.keySet()) {
			if(inicial.charAt(0) == aux.charAt(0)) {
				resultado.append(aux + this.listaDePalabras.get(aux) + "\n");
			}
		}
		return resultado.toString();
	}
	
	@Override
	public String toString() {
		return "Diccionario" + listaDePalabras;
	}


}
