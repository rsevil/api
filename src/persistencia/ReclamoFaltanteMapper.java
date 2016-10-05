package persistencia;

import negocio.ReclamoFaltantes;

public class ReclamoFaltanteMapper extends ReclamoMapper {

	private static ReclamoFaltanteMapper instance;
	
	private ReclamoFaltanteMapper() {
	}
	
	public static ReclamoFaltanteMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoFaltanteMapper();
			
		return instance;
	}
	
	public void insert(ReclamoFaltantes o) {
		super.insertReclamo(o);
		tryCommand("insert into dbo.ReclamoFaltante (?,?,?)", s -> {
			s.setInt(1, o.getNumReclamo());
			s.setString(2, o.getProducto().getCodigoPublicacion());
			s.setInt(3, o.getCantFaltante());
		});
	}

	public void update(ReclamoFaltantes o) {
		super.updateReclamo(o);
		tryCommand("update dbo.ReclamoFaltante (?,?) where nroReclamo = ?", s -> {
			s.setString(1, o.getProducto().getCodigoPublicacion());
			s.setInt(2, o.getCantFaltante());
			s.setInt(3, o.getNumReclamo());
		});
	}

	public void delete(ReclamoFaltantes o) {
		super.deleteReclamo(o);
	}

	public ReclamoFaltantes selectOne(int id) {
		return tryQuery(
				"select "
				+ "r.nroReclamo, "
				+ "r.fecha, "
				+ "r.fechaCierre, "
				+ "r.descripcionReclamo, "
				+ "r.estado, "
				+ "r.activo, "
				+ "r.nroCliente, "
				+ "rf.codigoProducto, "
				+ "rf.cantFaltante "
				+ "from dbo.Reclamo r "
				+ "join dbo.ReclamoFaltante rf on rf.nroReclamo = r.nroReclamo "
				+ "where nroReclamo = ?", 
				s -> s.setInt(1, id), 
				rs -> {
					return new ReclamoFaltantes(
						rs.getDate("fecha"), 
						rs.getInt("nroReclamo"), 
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getString("descripcionReclamo"), 
						rs.getString("estado"),
						rs.getDate("fechaCierre"),
						rs.getInt("cantFaltante"),
						ProductoMapper.getInstancia().selectOne(rs.getString("codigoProducto")),
						rs.getBoolean("activo"));
				}
			);
	}
}
