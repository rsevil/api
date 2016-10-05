package persistencia;

import java.util.Vector;

import negocio.DetalleReclamoFacturacion;
import negocio.ReclamoFacturacion;

public class ReclamoFacturacionMapper extends ReclamoMapper<ReclamoFacturacion> {

	private static ReclamoFacturacionMapper instance;
	
	private ReclamoFacturacionMapper() {
	}
	
	public static ReclamoFacturacionMapper getInstancia()
	{
		if (instance == null) {
			instance = new ReclamoFacturacionMapper();
		}
		return instance;
	}
	
	@Override
	public void insert(ReclamoFacturacion o) {
		super.insertReclamo(o);
		tryCommand("insert into dbo.ReclamoFacturacion (?)", s -> {
			s.setInt(1, o.getNumReclamo());
		});
	}

	@Override
	public void update(ReclamoFacturacion o) {
		super.updateReclamo(o);
	}

	@Override
	public void delete(ReclamoFacturacion o) {
		super.deleteReclamo(o);
	}

	@Override
	public ReclamoFacturacion selectOne(Object id) {
		return tryQuery(
				"select "
				+ "r.nroReclamo, "
				+ "r.fecha, "
				+ "r.fechaCierre, "
				+ "r.descripcionReclamo, "
				+ "r.estado, "
				+ "r.activo, "
				+ "r.nroCliente "
				+ "from dbo.Reclamo r "
				+ "join dbo.ReclamoFacturacion rp on rp.nroReclamo = r.nroReclamo "
				+ "where nroReclamo = ?", 
				s -> s.setInt(1, (int)id), 
				rs -> {
					int nroReclamo = rs.getInt("nroReclamo");
					return new ReclamoFacturacion(
						rs.getDate("fecha"), 
						nroReclamo, 
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getString("descripcionReclamo"), 
						rs.getString("estado"),
						rs.getDate("fechaCierre"),
						this.getDetalles(nroReclamo),
						rs.getBoolean("activo"));
				}
			);
	}
	
	private Vector<DetalleReclamoFacturacion> getDetalles(int nroReclamo){
		return tryQueryMany(
				"select * "
				+ "from dbo.DetalleReclamoFacturacion "
				+ "where nroReclamo = ?",
				s -> s.setInt(1, nroReclamo),
				rs -> new DetalleReclamoFacturacion(
						rs.getString("detalle"),
						rs.getString("idFactura")));
	}
}
