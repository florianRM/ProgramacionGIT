package PlataformaOnline.jacaranda.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Series {

	private HashMap<String, Serie> mapSeries;

	/**
	 * Crea el objeto que nos servirá para tener las series
	 */
	public Series() {
		mapSeries = new HashMap<String, Serie>();
	}

	/**
	 * Añade una serie a la lista de series. Si existe una serie con el mismo nombre
	 * lanza una excpetion
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void annadirSerie(String nombreSerie, int anno, Tema tema) throws SerieException {
		if (mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("Ya existe esa serie");
		}
		Serie serie = new Serie(nombreSerie, anno, tema);
		mapSeries.put(serie.getNombreSerie(), serie);
	}

	/**
	 * Añade una temporada a la Serie cuyo nombre se le pasa por argumento, si no
	 * existe la Serie lanza una exception
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void annadirTemporada(String nombreSerie, String temporada) throws SerieException {
		if (!mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("No existe esa serie");
		}
		Serie serie = mapSeries.get(nombreSerie);
		serie.annadirTemporada(temporada);
	}

	/**
	 * Añade un capítulo a la temporada de la Serie cuyo nombre se le pasa por
	 * argumento, si no existe la Serie o la temporada lanza una exception
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void annadirCapituloTemporada(String nombreSerie, String temporada, String capitulo) throws SerieException {
		if (!mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("No existe esa serie");
		}
		Serie serie = mapSeries.get(nombreSerie);
		serie.annadirCapituloTemporada(temporada, capitulo);

	}

	/**
	 * Valorar una temporada de la Serie cuyo nombre se le pasa por argumento, si no
	 * existe la Serie o la temporada lanza una exception
	 * 
	 * @param serie
	 * @throws SerieException
	 */
	public void valorarTemporada(String nombreSerie, String temporada, int valoracion) throws SerieException {
		if (!mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("No existe esa serie");
		}
		Serie serie = mapSeries.get(nombreSerie);
		serie.valorarTemporada(temporada, valoracion);
	}

	/**
	 * Devuelve el número de temporadas que tiene la serie que se pasa por parámetro
	 * Si no existe la serie saltará la excepción.
	 * 
	 * @param nombreSerie
	 * @return
	 * @throws SerieException
	 */

	public int numeroDeTemporadasDeUnaSerie(String nombreSerie) throws SerieException {
		// He usado el método de si contiene esa clave ya que antés lo hize con los
		// valores
		if (!this.mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("La serie no existe.");
		}
		return this.mapSeries.get(nombreSerie).numeroTemporadas();
	}

	/**
	 * Modifica el tema de una serie. Si no se encuentra la serie o ya tenía ese
	 * tema saltará la excepción.
	 * 
	 * @param nombreSerie
	 * @param nuevoTema
	 * @throws SerieException
	 */
	public void modificarTema(String nombreSerie, Tema nuevoTema) throws SerieException {
		if (!this.mapSeries.containsKey(nombreSerie)) {
			throw new SerieException("La serie no existe.");
		}
		if (this.mapSeries.get(nombreSerie).getTema().equals(nuevoTema)) {
			throw new SerieException("La serie ya tiene este tema");
		}
		this.mapSeries.get(nombreSerie).setTema(nuevoTema);
	}

	/**
	 * Devolverá un listado ordenado de forma creciente por el año de la serie. Para
	 * cada serie se mostrará su nombre, año y número de temporadas. Si no hay
	 * ninguna serie de ese tema saltará la excepción.
	 * 
	 * @param tema
	 * @return
	 * @throws SerieException
	 */
	public String listadoOrdenadoSeriesDeUnTema(Tema tema) throws SerieException {
		StringBuilder resultado = new StringBuilder();
		ArrayList<String> aux = new ArrayList<>(this.mapSeries.keySet());
		Collections.sort(aux);
		for (String string : aux) {
			if (this.mapSeries.get(string).equals(tema)) {
				resultado.append(this.mapSeries.get(string) + ", " + this.mapSeries.get(string).getAnno() + ", "
						+ this.mapSeries.get(string).numeroTemporadas());
			}
		}
		return resultado.toString();
	}

	public String listarPorNota() {
		String resultado = "";
		for (Serie aux : this.mapSeries.values()) {
			resultado += aux.listadoTemporadasPorNotaMedia() + "\n";
		}
		return resultado;
	}

	public String listarPorNumCapitulos() {
		String resultado = "";
		for (Serie aux : this.mapSeries.values()) {
			resultado += aux.listadoTemporadasPorNumeroDeCapitulos() + "\n";
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "Series [mapSeries=" + mapSeries + "]";
	}

	public String getSeries() {
		String resultado = "";
		for (Serie aux : this.mapSeries.values()) {
			resultado += aux.escribirFichero() + "\n";
		}
		return resultado;
	}

	public String getTemporada() {
		String resultado = "";
		for (Serie aux : this.mapSeries.values()) {
			resultado += aux.getTemporada() + "\n";
		}
		return resultado;
	}

	public String getCapitulos() {
		String resultado = "";
		for (Serie aux : this.mapSeries.values()) {
			resultado += aux.getCapitulos() + "\n";
		}
		return resultado;
	}

}
