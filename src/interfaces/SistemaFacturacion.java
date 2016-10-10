package interfaces;

import java.util.Vector;
import java.util.function.Function;

import negocio.Producto;
import persistencia.FacturaMapper;
import utils.Func;

public class SistemaFacturacion {
	
	private Vector<Factura> facturas = new Vector<Factura>();
	
	private static SistemaFacturacion instance;
	public static SistemaFacturacion getInstancia()
	{
		if (instance == null) {
			instance = new SistemaFacturacion();
		}
		return instance;
	}
	
	public boolean Facture(int nroFactura, Producto p, int cantidad){
		Factura f = buscarFactura(nroFactura);
		return f != null && f.facturasteEstaCantidad(p, cantidad);
	}
	
	public boolean facturaEsDeEsteCliente(int nroFactura, int nroCliente) {
		Factura f = buscarFacturaCliente(nroFactura, nroCliente);
		return f != null;
	}
	
	private Factura buscarFactura(int nroFactura) {
		return buscar(
				facturas,
				c -> c.sosFactura(nroFactura),
				() -> FacturaMapper.getInstancia().selectOne(nroFactura));
	}
	
	private Factura buscarFacturaCliente(int nroFactura, int nroCliente) {
		return buscar(
				facturas,
				c -> c.sosDeEsteCliente(nroCliente),
				() -> FacturaMapper.getInstancia().selectOneByIdAndCliente(nroFactura, nroCliente));
	}
	
	private <T> T buscar(Vector<T> cache, Function<T, Boolean> predicate, Func<T> getter) {
		for (T c : cache){
			if (predicate.apply(c)){
				return c;
			}
		}
		
		T o = null;
		
		try{
			o = getter.apply();
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		if(o != null){
			cache.add(o);
		}
		
		return o;
	}
}
