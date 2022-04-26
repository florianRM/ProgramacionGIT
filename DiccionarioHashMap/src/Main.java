
public class Main {

	public static void main(String[] args) throws Exception {
		
		Diccionario d = new Diccionario();
		
		d.addDiccionario("Avión", "Método de transporte");
		d.addDiccionario("Ave", "Animal que vuela");
		d.addDiccionario("Banco", "Grupo de peces");
		d.addDiccionario("Banco", "Lugar donde se presta dinero");
		d.addDiccionario("Barco", "Método de transporte por agua");
		d.removePalabra("Barco");
		d.removePalabra("Banco");
		
		System.out.println(d.toString());
		System.out.println(d.buscarSignificado("Avión"));
	}

}
