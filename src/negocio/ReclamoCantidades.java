package negocio;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import enums.EstadosReclamo;
import persistencia.ReclamoCantidadesMapper;

public class ReclamoCantidades extends Reclamo {
	
	private Vector<ItemReclamoCantidad> items;
	
	private ReclamoCantidades(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente, 
			Vector<ItemReclamoCantidad> items,
			boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.items = items;
		
		if (persistir)
			ReclamoCantidadesMapper.getInstancia().insert(this);
	}
	
	public ReclamoCantidades(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente, 
			Vector<ItemReclamoCantidad> items) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, items, false);
	}
	
	public ReclamoCantidades(
			String descripcionReclamo,
			Cliente cliente){
		this(ReclamoCantidadesMapper.getInstancia().getUltimoId(),
				new Date(Calendar.getInstance().getTimeInMillis()),
				null,
				descripcionReclamo,
				EstadosReclamo.INGRESADO,
				cliente,
				new Vector<ItemReclamoCantidad>(),
				true); 
	}
	
	public void agregarProducto(Producto producto, int cantidad){
		if (cantidad < 1){
			// QUE HACEMOS??		
		}
		
		ItemReclamoCantidad item = null;
		for(ItemReclamoCantidad i : items)
			if (i.getProducto().sosProducto(producto.getCodigoProducto()))
				item = i;
		
		if (item == null){
			item = new ItemReclamoCantidad(cantidad, producto);
			this.items.add(item);
			ReclamoCantidadesMapper.getInstancia().insertItemReclamoCantidad(this, item);
		}else{ //si se llama a agregarProducto de nuevo, se agrega la cantidad al item
			item.agregarCantidad(cantidad);
			ReclamoCantidadesMapper.getInstancia().updateItemReclamoCantidad(this, item);
		}
				
	}
}
