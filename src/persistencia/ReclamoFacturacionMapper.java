package persistencia;

import java.util.Vector;

import negocio.DetalleReclamoFacturacion;
import negocio.ReclamoFacturacion;

public class ReclamoFacturacionMapper extends ReclamoMapper {

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
	
	public void insert(ReclamoFacturacion o) {
		super.insertReclamo(o);
		tryCommand("insert into dbo.ReclamoFacturacion (?)", s -> {
			s.setInt(1, o.getNumReclamo());
		});
		insertDetalles(o);
	}

	public void update(ReclamoFacturacion o) {
		super.updateReclamo(o);
		// TODO Que pasa con los detalles?
		
	}

	public void delete(ReclamoFacturacion o) {
		super.deleteReclamo(o);
		// TODO Que pasa con los detalles?
	}

	public ReclamoFacturacion selectOne(int id) {
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
				s -> s.setInt(1, id), 
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
	
	private void insertDetalles(ReclamoFacturacion o){
		// TODO PORQUE LOS DETALLES TIENEN SU PROPIO ID??
//		for (DetalleReclamoFacturacion d : o.getDetalles()) {
//			tryCommand("insert dbo.DetalleReclamoFacturacion values (", null);
//		}
	}
}
