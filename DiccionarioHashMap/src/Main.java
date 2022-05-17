
public class Main {

	public static void main(String[] args) throws Exception {
		
		Diccionario d = new Diccionario();
		
		d.addDiccionario("Avion", "Metodo de transporte");
		d.addDiccionario("Ave", "Animal que vuela");
		d.addDiccionario("Banco", "Grupo de peces");
		d.addDiccionario("Banco", "Lugar donde se presta dinero");
		d.addDiccionario("Barco", "Metodo de transporte por agua");
		
		System.out.println(d.toString());
		System.out.println(d.buscarPalabrasEmpiezen("B"));
	}

}
