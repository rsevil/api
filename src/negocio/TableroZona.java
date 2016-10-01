package negocio;

public class TableroZona {
	
	private static TableroZona instance;
	
	private  TableroZona() {
	
	}
	
	public static TableroZona getInstance() {
		return instance;
	}
}