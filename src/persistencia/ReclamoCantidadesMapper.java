package persistencia;

import negocio.ReclamoCantidades;

public class ReclamoCantidadesMapper extends ReclamoMapper {

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
	
	public void insert(ReclamoCantidades o) {
		super.insertReclamo(o);
		tryCommand("insert into dbo.ReclamoCantidades (?,?,?)", s -> {
			s.setInt(1, o.getNumReclamo());
			s.setString(2, o.getProducto().getCodigoPublicacion());
			s.setInt(3, o.getCantidad());
		});
	}

	public void update(ReclamoCantidades o) {
		super.updateReclamo(o);
		tryCommand("update dbo.ReclamoCantidades set codigoProducto=?, set cantidad=? where nroReclamo=?", s -> {
			s.setString(1, o.getProducto().getCodigoPublicacion());
			s.setInt(2, o.getCantidad());
			s.setInt(3, o.getNumReclamo());
		});
	}

	public void delete(ReclamoCantidades o) {
		super.deleteReclamo(o);
	}

	public ReclamoCantidades selectOne(int id) {
		return tryQuery(
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
				s -> s.setInt(1, id), 
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
