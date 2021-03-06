
import java.util.ArrayList;
import java.util.Iterator;

public class Diccionario {

	private ArrayList<PalabrasEmpiezan> diccionario;

	public Diccionario() {
		this.diccionario = new ArrayList<>();
		anniadirLetras();
	}

	private void anniadirLetras() {
		for (int i = 65; i <= 90; i++) {
			PalabrasEmpiezan relleno = new PalabrasEmpiezan((char) i);
			this.diccionario.add(relleno);
		}
	}

	public boolean addDiccionario(String palabra, String significado) throws DiccionarioException {
		boolean encontrado = false;
		PalabrasEmpiezan aux = new PalabrasEmpiezan(palabra.charAt(0));
		if((!palabra.isEmpty() && palabra != null) && (!significado.isEmpty() && significado != null)) {
			this.diccionario.get(this.diccionario.indexOf(aux)).addPalabra(palabra, significado);
		}
		return encontrado;
	}
	
	public boolean borrarPalabra(String palabra) throws DiccionarioException {
		boolean encontrado = false;
		for (Iterator<PalabrasEmpiezan> iterator = diccionario.iterator(); iterator.hasNext() && !encontrado;) {
			PalabrasEmpiezan palabras = iterator.next();
			if (palabra.charAt(0) == palabras.getLetra()) {
				encontrado = palabras.borrarPalabra(palabra);
			}
		}
		return encontrado;
	}
	

	public String palabraEmpiezaPor(char letra) {
		StringBuilder resultado = new StringBuilder();
		
		for(PalabrasEmpiezan aux : this.diccionario) {
			if(aux.getLetra() == letra) {
				resultado.append(aux);
			}
		}
		return resultado.toString();
	}

	public String buscarSignificado(String palabra) {
		PalabrasEmpiezan aux = new PalabrasEmpiezan(palabra.charAt(0));
		
		return this.diccionario.get(this.diccionario.indexOf(aux)).getSignificadoPalabra(palabra);
	}

	@Override
	public String toString() {
		return "Diccionario:" + diccionario;
	}

}
