package enums;

public enum EstadosReclamo {
	INGRESADO("Ingresado"),
	EN_TRATAMIENTO("En Tratamiento"),
	CERRADO("Cerrado"),
	SOLUCIONADO("Solucionado");
	
	private String texto;
	
	private EstadosReclamo(String texto){
		this.texto = texto;
	}
	
	public String getTexto(){
		return this.texto;
	}
	
	public static EstadosReclamo getEstadoReclamo(String texto) {
		for (EstadosReclamo r : EstadosReclamo.values()){
			if (r.getTexto().equals(texto)){
				return r;
			}
		}
		return null;
	}
}
