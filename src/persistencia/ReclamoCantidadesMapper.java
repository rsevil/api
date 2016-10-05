package persistencia;

import negocio.ReclamoCantidades;

public class ReclamoCantidadesMapper extends ReclamoMapper<ReclamoCantidades> {

	private static ReclamoCantidadesMapper instance;
	
	private ReclamoCantidadesMapper() {
	}
	
	public static ReclamoCantidadesMapper getInstancia()
	{
		if (instance == null) {
			instance = new ReclamoCantidadesMapper();
		}
		return instance;
	}
	
	@Override
	public void insert(ReclamoCantidades o) {
		super.insertReclamo(o);
		tryExecuteCommand("insert into dbo.ReclamoCantidades (?,?,?,?)", s -> {
			s.setInt(1, o.getNumReclamo());
			s.setString(2, o.getProducto().getCodigoPublicacion());
			s.setInt(3, o.getCantidad());
			s.setBoolean(4, o.getActivo());
		});
	}

	@Override
	public void update(ReclamoCantidades o) {
		super.updateReclamo(o);
		tryExecuteCommand("update dbo.ReclamoCantidades set codigoProducto=?, set cantidad=?, set activo=? where nroReclamo=?", s -> {
			s.setString(1, o.getProducto().getCodigoPublicacion());
			s.setInt(2, o.getCantidad());
			s.setBoolean(3, o.getActivo());
			s.setInt(4, o.getNumReclamo());
		});
	}

	@Override
	public void delete(ReclamoCantidades o) {
		super.deleteReclamo(o);
		tryExecuteCommand("update dbo.ReclamoCantidades set activo=0 where nroReclamo=?", s -> {
			s.setInt(1, o.getNumReclamo());
		});
	}

	@Override
	public ReclamoCantidades selectOne(Object id) {
		return tryExecuteQuery(
				"select "
				+ "r.nroReclamo, "
				+ "r.fecha, "
				+ "r.fechaCierre, "
				+ "r.descripcionReclamo, "
				+ "r.estado, "
				+ "r.activo, "
				+ "r.nroCliente, "
				+ "rc.codigoProducto, "
				+ "rc.cantidad "
				+ "from dbo.Reclamo r "
				+ "join dbo.ReclamoCantidades rc on rc.nroReclamo = r.nroReclamo "
				+ "where nroReclamo = ?", 
				s -> s.setInt(1, (int)id), 
				rs -> new ReclamoCantidades(
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
