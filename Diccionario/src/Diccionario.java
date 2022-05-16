
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
		for (Iterator<PalabrasEmpiezan> iterator = diccionario.iterator(); iterator.hasNext() && !encontrado;) {
			PalabrasEmpiezan palabras = iterator.next();
			if (palabra.charAt(0) == palabras.getLetra()) {
				palabras.addPalabra(palabra, significado);
				encontrado = true;
			}
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
	
	public String buscarSignificado(String palabra) {
		return this.diccionario.get(0).toString();
	}

	@Override
	public String toString() {
		return "Diccionario:" + diccionario;
	}

}
