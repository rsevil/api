package negocio;

public class TableroConsulta {
	
	private static TableroConsulta instance;
	
	private TableroConsulta() {
	
	}
	
	public static TableroConsulta getInstance() {
		return instance;	
	}
}
