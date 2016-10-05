package negocio;

public class Producto {
	private String codigoPublicacion;
	private String titulo;
	private String descripcion;
	private float precio;
	private boolean activo;
	
	public Producto(String codigoPublicacion, String titulo,
			String descripcion, float precio, boolean activo) {
		super();
		this.codigoPublicacion = codigoPublicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.activo = activo;
	}
	
	public String getCodigoPublicacion() {
		return codigoPublicacion;
	}
	
	public void setCodigoPublicacion(String codigoPublicacion) {
		this.codigoPublicacion = codigoPublicacion;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public boolean getActivo(){
		return this.activo;
	}
	
	public void setActivo(boolean activo){
		this.activo = activo;
	}
}
