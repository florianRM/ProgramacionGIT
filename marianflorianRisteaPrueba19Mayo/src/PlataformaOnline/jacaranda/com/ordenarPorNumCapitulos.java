package PlataformaOnline.jacaranda.com;

import java.util.Comparator;

public class ordenarPorNumCapitulos implements Comparator<Temporada> {

	@Override
	public int compare(Temporada o1, Temporada o2) {
		int resultado = -1;
		if(o1.getNumeroCapitulos() > o2.getNumeroCapitulos()) {
			resultado = 1;
		}
		return resultado;
	}

}
