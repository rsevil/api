package interfaces;

import negocio.Producto;

public class ItemFactura {
	
	private int codigoProducto;
	private int cantidad;
	@SuppressWarnings("unused")
	private float precio;
	
	public ItemFactura(
			int codigoProducto,
			int cantidad,
			float precio){
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public boolean tengoElProducto(Producto p){
		return p.sosProducto(this.codigoProducto);
	}
	
	public boolean tengoLaCantidad(int cantidad){
		return this.cantidad == cantidad;
	}
}
