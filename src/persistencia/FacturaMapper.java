package persistencia;
import java.util.Vector;

import interfaces.Factura;
import interfaces.ItemFactura;
import negocio.Usuario;

public class FacturaMapper extends BaseMapper {

	private static FacturaMapper instance;
	
	private FacturaMapper() {
		
	}
	
	public static FacturaMapper getInstancia()
	{
		if (instance == null)
			instance = new FacturaMapper();
		return instance;
	}

	public Factura selectOne(int idFactura) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Factura "
				+ "WHERE idFactura = ? "
				+ "AND activo = 1", 
				s -> s.setInt(1, idFactura), 
				rs -> {
					return new Factura(
						idFactura,
						rs.getInt("nroCliente"),
						rs.getDate("fecha"),
						getItems(idFactura));
				});
	}
	
	public Vector<ItemFactura> getItems(int idFactura) {
		return tryQueryMany(
				"SELECT * "
				+ "FROM dbo.ItemFactura "
				+ "WHERE idFactura = ? "
				+ "AND activo = 1", 
				s -> s.setInt(1, idFactura), 
				rs -> new ItemFactura(
						rs.getInt("codigoProducto"),
						rs.getInt("cantidad"),
						rs.getFloat("precio")));
	}
}
