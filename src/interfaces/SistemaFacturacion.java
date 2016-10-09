package interfaces;

import java.util.Vector;
import java.util.function.Function;

import controlador.SistemaAdministracionReclamos;
import negocio.Cliente;
import negocio.Producto;
import persistencia.ClienteMapper;
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
	
	private Factura buscarFactura(int nroCliente) {
		return buscar(
				facturas,
				c -> c.sosFactura(nroCliente),
				() -> FacturaMapper.getInstancia().selectOne(nroCliente));
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
