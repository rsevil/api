package negocio;

public class TableroDistribucion {
	
	private static TableroDistribucion instance;
	
	private TableroDistribucion() {
	
	}
	
	public static TableroDistribucion getInstance() {
		return instance;	
	}
}
