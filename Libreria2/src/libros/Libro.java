package libros;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Libro implements Comparable<Libro> {
	
	private static int codigoSiguiente = 1;
	private int codigo;
	private String titulo;
	private String autor;
	private String editorial;
	private LocalDate fechaEdicion;
	private String isbn;
	
	public Libro(String titulo, String autor) {
		super();
		this.codigo = Libro.codigoSiguiente++;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaEdicion = LocalDate.now();
		generarIsbn();
	}
	
	public Libro(String titulo, String autor, String editorial) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.fechaEdicion = LocalDate.now();
		generarIsbn();
	}
	
	private void generarIsbn() {
		String cadenaAux = "", cadenaAutor = "";
		int longitudTitulo = this.titulo.length();
		int longitudAutor = this.autor.length();
	
	/*Recorremos la longitud del titulo hasta que la cadena auxiliar llegue a los 3 carácteres
	 * o hasta que i sea igual que el título*/
		for (int i=0; cadenaAux.length() != 3 && i < longitudTitulo; i++) {
			if (this.titulo.charAt(i) != ' ') {
				cadenaAux += this.titulo.charAt(i);
			}
		}
	//Le sumamos a la cadena auxiliar la longitud del titulo
		cadenaAux += longitudTitulo;
	//Primero le quitamos los espacios a la cadena
		for (int i=0; i < longitudAutor; i++) {
			if (this.autor.charAt(i) != ' ') {
				cadenaAutor += this.autor.charAt(i);
			}
		}
	/*Ahora que la cadena no tiene espacios sumamos los 3 últimos carácteres 
	y la longitud del autor*/
		cadenaAux += cadenaAutor.substring(cadenaAutor.length() - 3);
		cadenaAux += longitudAutor;
		this.isbn = cadenaAux;
	}
	
	public int diferenciaDias(Libro other) throws LibroException {
		return (int) this.fechaEdicion.until(other.fechaEdicion, ChronoUnit.DAYS);
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public LocalDate getFechaEdicion() {
		return fechaEdicion;
	}
	
	public void setFechaEdicion(LocalDate fechaEdicion) throws LibroException {
		if (fechaEdicion.isAfter(LocalDate.now())) {
			throw new LibroException("Error. La fecha de edición no puede ser superior a la fecha actual.");
		}
		this.fechaEdicion = fechaEdicion;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getIsbn() {
		return isbn;
	}

	@Override
	public String toString() {
		return "Libro " + titulo + ". Autor:" + autor + ". ISBN:" + isbn + ". Código:" + codigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public int compareTo(Libro o) {
		int resultado;
		if (o == null) {
			resultado = -1;
		}
		else {
			resultado = this.fechaEdicion.compareTo(o.fechaEdicion);
			if (resultado == 0) {
				resultado = this.isbn.compareTo(o.isbn);
			}
		}
		return resultado;
	}
	
}
