package PlataformaOnline.jacaranda.com;

import java.util.Comparator;

public class ordenadoPorNota implements Comparator<Temporada> {

	@Override
	public int compare(Temporada o1, Temporada o2) {
		int resultado = -1;
		if((o1.getNotaMedia() - o2.getNotaMedia()) < 0) {
			resultado = 1;
		}
		return resultado;
	}

}
