package libros;

public class LibroDigital extends Libro {

	private double precio;
	private EnumFormato formato;
	
	public LibroDigital(String titulo, String autor, double precio, String formato) throws LibroException {
		super(titulo, autor);
		if (precio < 0) {
			throw new LibroException("El precio debe ser mayor a 0.");
		}
		this.precio = precio;
		try {
			this.formato = EnumFormato.valueOf(formato.toUpperCase());
		} catch (Exception e) {
			throw new LibroException("El formato introducido no es correcto.");
		}
	}

	public LibroDigital(String titulo, String autor, String editorial, double precio, String formato) throws LibroException {
		super(titulo, autor, editorial);
		if (precio < 0) {
			throw new LibroException("El precio debe ser mayor a 0.");
		}
		this.precio = precio;
		try {
			this.formato = EnumFormato.valueOf(formato.toUpperCase());
		} catch (Exception e) {
			throw new LibroException("El formato introducido no es correcto.");
		}
	}
	
	public boolean masCaroLibroDigital(LibroFisico other) {
		boolean resultado = false;
		if (this.precio > other.getPrecio()) {
			resultado = true;
		}
		return resultado;
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

	public EnumFormato getFormato() {
		return formato;
	}
	
	@Override
	public String toString() {
		return "Libro " + super.getTitulo() + ". Autor:" + super.getAutor() + ". ISBN:" + super.getIsbn() + ". Código:" + super.getCodigo() + ". Precio:" + this.precio + ". Formato:" + this.formato;
	}
}
