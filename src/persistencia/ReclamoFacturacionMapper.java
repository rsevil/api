package persistencia;

import java.util.Vector;

import enums.EstadosReclamo;
import negocio.DetalleReclamoFacturacion;
import negocio.ReclamoCantidades;
import negocio.ReclamoFacturacion;

public class ReclamoFacturacionMapper extends ReclamoMapper<ReclamoFacturacion> {

	private static ReclamoFacturacionMapper instance;
	
	private ReclamoFacturacionMapper() {
		super(ReclamoFacturacion.class);
	}
	
	public static ReclamoFacturacionMapper getInstancia()
	{
		if (instance == null) {
			instance = new ReclamoFacturacionMapper();
		}
		return instance;
	}
	
	public void insert(ReclamoFacturacion o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> super.configureInsert(s, o));
		insertDetalles(o);
	}

	public void update(ReclamoFacturacion o) {
		tryCommand("UPDATE dbo.Reclamo "
				+ "SET fecha = ?, "
				+ "SET fechaCierre = ?, "
				+ "SET descripcionReclamo = ?, "
				+ "SET estado = ?, "
				+ "SET nroCliente = ?, "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? "
				+ "AND activo = 1 ", 
				s -> {
					int i = super.configureUpdate(s, o);
					s.setInt(i++, o.getNroReclamo());
					s.setString(i++, tipoReclamo.getSimpleName());
				});
	}

	public void delete(ReclamoFacturacion o) {
		deleteDetalles(o);
		super.deleteReclamo(o);
	}

	public ReclamoFacturacion selectOne(int id) {
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
				rs -> {
					int nroReclamo = rs.getInt("nroReclamo");
					return new ReclamoFacturacion(
						nroReclamo, 
						rs.getDate("fecha"), 
						rs.getDate("fechaCierre"), 
						rs.getString("descripcionReclamo"), 
						EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						this.getDetalles(nroReclamo));
				});
	}
	
	private Vector<DetalleReclamoFacturacion> getDetalles(int nroReclamo){
		return tryQueryMany(
				"SELECT * "
				+ "FROM dbo.DetalleReclamoFacturacion "
				+ "WHERE nroReclamo = ?",
				s -> s.setInt(1, nroReclamo),
				rs -> new DetalleReclamoFacturacion(
						rs.getString("detalle"),
						rs.getInt("idFactura")));
	}
	
	private void insertDetalles(ReclamoFacturacion o){
		for(DetalleReclamoFacturacion detalle : o.getDetalles())
			insertDetalle(o,detalle);
	}
	
	public void insertDetalle(ReclamoFacturacion o, DetalleReclamoFacturacion detalle){
		tryCommand("INSERT INTO dbo.DetalleReclamoFacturacion (detalle, activo, nroReclamo, idFactura) VALUES (?,?,?,?)", 
				s -> {
					s.setString(1, detalle.getDetalle());
					s.setBoolean(2, true);
					s.setInt(3, o.getNroReclamo());
					s.setInt(4, detalle.getNumFactura());
				});
	}
	
	private void deleteDetalles(ReclamoFacturacion o){
		tryCommand("UPDATE dbo.DetalleReclamoFacturacion "
				+ "SET activo = 0 "
				+ "WHERE nroReclamo = ?",
				s -> s.setInt(1, o.getNroReclamo()));
	}
}