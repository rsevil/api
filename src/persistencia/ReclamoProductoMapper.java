package persistencia;

import enums.EstadosReclamo;
import negocio.ReclamoProducto;

public class ReclamoProductoMapper extends BaseReclamoMapper<ReclamoProducto> {

	private static ReclamoProductoMapper instance;
	
	private ReclamoProductoMapper() {
		super(ReclamoProducto.class);
	}
	
	public static ReclamoProductoMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoProductoMapper();
			
		return instance;
	}
	
	public void insert(ReclamoProducto o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente, codigoProducto, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> {
					int i = super.configureInsert(s, o);
					s.setInt(i++, o.getProducto().getCodigoProducto());
					s.setInt(i++, o.getCantidad());					
				});
	}

	public void update(ReclamoProducto o) {
		tryCommand("UPDATE dbo.Reclamo "
				+ "SET fecha = ?, "
				+ "SET fechaCierre = ?, "
				+ "SET descripcionReclamo = ?, "
				+ "SET estado = ?, "
				+ "SET nroCliente = ?, "
				+ "SET codigoProducto = ?, "
				+ "SET cantidad = ? "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? "
				+ "AND activo = 1 ", 
				s -> {
					int i = super.configureUpdate(s, o);
					s.setInt(i++, o.getProducto().getCodigoProducto());
					s.setInt(i++, o.getCantidad());
					s.setInt(i++, o.getNroReclamo());
					s.setString(i++, tipoReclamo.getSimpleName());
				});

	}

	public void delete(ReclamoProducto o) {
		super.deleteReclamo(o);
	}

	public ReclamoProducto selectOne(int id) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Reclamo r "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? " 
				+ "AND activo = 1 ",
				s -> { 
					s.setInt(1, id);
					s.setString(2, tipoReclamo.getSimpleName());
				},
				rs -> new ReclamoProducto(
						rs.getInt("nroReclamo"), 
						rs.getDate("fecha"), 
						rs.getDate("fechaCierre"), 
						rs.getString("descripcionReclamo"), 
						EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getInt("cantidad"),
						ProductoMapper.getInstancia().selectOne(rs.getInt("codigoProducto"))));
	}
}
