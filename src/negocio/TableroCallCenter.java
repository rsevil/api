package negocio;

public class TableroCallCenter {
	
	private static TableroCallCenter instance;
	
	private TableroCallCenter() {
		
	}
	
	public static TableroCallCenter getInstance() {
		return instance;	
	}	
	
}
