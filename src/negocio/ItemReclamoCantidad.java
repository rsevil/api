package negocio;

public class ItemReclamoCantidad {
	
	private int cantidad;
	private Producto producto;
	
	public ItemReclamoCantidad( 
			int cantidad,
			Producto producto){
		this.cantidad = cantidad;
		this.producto = producto;
	}
	
	public int getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void agregarCantidad(int cantidad) {
		this.cantidad = this.cantidad + cantidad;
	} 
}
