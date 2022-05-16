
public class Main {

	public static void main(String[] args) throws Exception {
		
		Diccionario d = new Diccionario();
		
		d.addDiccionario("Avion", "Vuela");
		d.addDiccionario("Avion", "Vuela por aire");
		d.addDiccionario("Ave", "animal");
		d.addDiccionario("Barco", "Transporte");
		d.borrarPalabra("Barco");
		System.out.println(d.buscarSignificado("Avion"));
	}

}
