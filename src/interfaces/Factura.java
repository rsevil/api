package interfaces;

import java.sql.Date;
import java.util.Vector;

import negocio.Producto;

public class Factura {
	
	private int idFactura;
	private int nroCliente;
	@SuppressWarnings("unused")
	private Date fecha;
	
	private Vector<ItemFactura> items;
	
	public Factura(
			int idFactura,
			int nroCliente,
			Date fecha,
			Vector<ItemFactura> items){
		this.idFactura = idFactura;
		this.nroCliente = nroCliente;
		this.fecha = fecha;
		this.items = items;
	}
	
	public boolean sosFactura(int idFactura){
		return this.idFactura == idFactura;
	}
	
	public boolean facturasteEstaCantidad(Producto p, int cantidad){
		for (ItemFactura i : this.items) {
			if (i.tengoElProducto(p) && i.tengoLaCantidad(cantidad))
				return true;
		}
		
		return false;
	}
	
	public boolean sosDeEsteCliente(int nroCliente) {
		return this.nroCliente == nroCliente;
	}
}
