package negocio;

public class TableroFacturacion {
	
	private static TableroFacturacion instance;
	
	private  TableroFacturacion() {
	
	}
	
	public static TableroFacturacion getInstance() {
		return instance;
	}
}
