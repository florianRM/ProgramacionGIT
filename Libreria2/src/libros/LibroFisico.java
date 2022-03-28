package libros;

public class LibroFisico extends Libro  {

	private double peso;
	private double precio;
	
	public LibroFisico(String titulo, String autor, double peso, double precio) throws LibroException {
		super(titulo, autor);
		if (precio < 0) {
			throw new LibroException("El precio debe ser mayor a 0.");
		}
		if (peso < 0) {
			throw new LibroException("El peso debe ser mayor a 0.");
		}
		this.peso = peso;
		this.precio = precio;
	}
	
	public LibroFisico(String titulo, String autor, String editorial, double peso, double precio) throws LibroException {
		super(titulo, autor, editorial);
		if (precio < 0) {
			throw new LibroException("El precio debe ser mayor a 0.");
		}
		if (peso < 0) {
			throw new LibroException("El peso debe ser mayor a 0.");
		}
		this.peso = peso;
		this.precio = precio;
	}
	
	public boolean masCaroLibroFisico(LibroDigital other) {
		boolean resultado = false;
		if (this.precio > other.getPrecio()) {
			resultado = true;
		}
		return resultado;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) throws LibroException {
		if (peso < 0) {
			throw new LibroException("El peso debe ser mayor a 0.");
		}
		this.peso = peso;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) throws LibroException {
		if (precio < 0) {
			throw new LibroException("El precio debe ser mayor a 0.");
		}
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "Autor:" + super.getAutor() + ". ISBN:" + super.getIsbn() + ". Código:" + super.getCodigo() + ". Precio:" + this.precio + ". Peso:" + this.peso + ". Libro:" + super.getTitulo();
	}
	
}
