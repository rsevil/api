package persistencia;

import negocio.ReclamoProducto;

public class ReclamoProductoMapper extends ReclamoMapper<ReclamoProducto> {

	private static ReclamoProductoMapper instance;
	
	private ReclamoProductoMapper() {
	}
	
	public static ReclamoProductoMapper getInstancia()
	{
		if (instance == null) {
			instance = new ReclamoProductoMapper();
		}
		return instance;
	}
	
	@Override
	public void insert(ReclamoProducto o) {
		super.insertReclamo(o);
		tryCommand("insert into dbo.ReclamoProducto (?,?,?)", s -> {
			s.setInt(1, o.getNumReclamo());
			s.setString(2, o.getProducto().getCodigoPublicacion());
			s.setInt(3, o.getCantidad());
		});
	}

	@Override
	public void update(ReclamoProducto o) {
		super.updateReclamo(o);
		tryCommand("update dbo.ReclamoProducto set codigoProducto=?, set cantidad=? where nroReclamo=?", s -> {
			s.setString(1, o.getProducto().getCodigoPublicacion());
			s.setInt(2, o.getCantidad());
			s.setInt(3, o.getNumReclamo());
		});
	}

	@Override
	public void delete(ReclamoProducto o) {
		super.deleteReclamo(o);
	}

	@Override
	public ReclamoProducto selectOne(Object id) {
		return tryQuery(
				"select "
				+ "r.nroReclamo, "
				+ "r.fecha, "
				+ "r.fechaCierre, "
				+ "r.descripcionReclamo, "
				+ "r.estado, "
				+ "r.activo, "
				+ "r.nroCliente, "
				+ "rp.codigoProducto, "
				+ "rp.cantidad "
				+ "from dbo.Reclamo r "
				+ "join dbo.ReclamoProducto rp on rp.nroReclamo = r.nroReclamo "
				+ "where nroReclamo = ?", 
				s -> s.setInt(1, (int)id), 
				rs -> new ReclamoProducto(
						rs.getDate("fecha"), 
						rs.getInt("nroReclamo"), 
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getString("descripcionReclamo"), 
						rs.getString("estado"),
						rs.getDate("fechaCierre"), 
						ProductoMapper.getInstancia().selectOne(rs.getString("codigoProducto")),
						rs.getInt("cantidad"),
						rs.getBoolean("activo"))
			);
	}
}
