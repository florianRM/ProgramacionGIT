
public class Main {

	public static void main(String[] args) throws Exception {
		
		Diccionario d = new Diccionario();
		
		d.addDiccionario("Avión", "Vuela");
		d.addDiccionario("Avión", "Vuela por aire");
		d.addDiccionario("Ave", "animal");
		d.addDiccionario("Barco", "Transporte");
		d.borrarPalabra("Barco");
		
		System.out.println(d.toString());
	}

}
