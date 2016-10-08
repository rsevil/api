package negocio;

public class Producto {
	
	private String codigoProducto;
	private String titulo;
	private String descripcion;
	private float precio;
	
	public Producto(
			String codigoPublicacion, 
			String titulo,
			String descripcion, 
			float precio) {
		super();
		this.codigoProducto = codigoPublicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public String getCodigoProducto() {
		return codigoProducto;
	}
	
	public void setCodigoProducto(String codigoPublicacion) {
		this.codigoProducto = codigoPublicacion;
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
}
