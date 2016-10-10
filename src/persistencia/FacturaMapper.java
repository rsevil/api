package persistencia;
import java.util.Vector;

import interfaces.Factura;
import interfaces.ItemFactura;

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
	
	public Factura selectOneByIdAndCliente(int idFactura, int nroCliente) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Factura "
				+ "WHERE idFactura = ?"
				+ "AND nroCliente = ? "
				+ "AND activo = 1", 
				s -> { s.setInt(1, idFactura); s.setInt(1, nroCliente); }, 
				rs -> {
					return new Factura(
						rs.getInt("idFactura"),
						rs.getInt("nroCliente"),
						rs.getDate("fecha"),
						getItems(idFactura));
				});
	}
}
