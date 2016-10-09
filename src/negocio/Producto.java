package negocio;

import persistencia.ProductoMapper;

public class Producto {
	
	private int codigoProducto;
	private String codigoPublicacion;
	private String titulo;
	private String descripcion;
	private float precio;
	
	public Producto(
			int idProducto,
			String codigoPublicacion, 
			String titulo,
			String descripcion, 
			float precio, 
			boolean persistir) {
		super();
		this.codigoProducto = idProducto;
		this.codigoPublicacion = codigoPublicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		
		if (persistir)
			ProductoMapper.getInstancia().insert(this);
	}
	
	public Producto(
			int idProducto,
			String codigoPublicacion, 
			String titulo,
			String descripcion, 
			float precio) {
		this(idProducto,codigoPublicacion,titulo,descripcion,precio,false);
	}
	
	public Producto(
			String codigoPublicacion, 
			String titulo,
			String descripcion, 
			float precio){
		this(ProductoMapper.getInstancia().getUltimoId(), codigoPublicacion, titulo, descripcion, precio,true);
	}
	
	public int getCodigoProducto(){
		return this.codigoProducto;
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

	public boolean sosProducto(int codigoProducto) {
		return this.codigoProducto == codigoProducto;
	}
}
