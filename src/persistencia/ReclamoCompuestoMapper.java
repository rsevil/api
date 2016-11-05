package persistencia;

import java.util.Vector;

import enums.EstadosReclamo;
import negocio.Reclamo;
import negocio.ReclamoCompuesto;

public class ReclamoCompuestoMapper extends BaseReclamoMapper<ReclamoCompuesto> {

	private static ReclamoCompuestoMapper instance;
	
	private ReclamoCompuestoMapper() {
		super(ReclamoCompuesto.class);
	}
	
	public static ReclamoCompuestoMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoCompuestoMapper();
			
		return instance;
	}
	
	public void insert(ReclamoCompuesto o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> super.configureInsert(s, o));

		//ESTO TALVEZ HAY QUE SACARLO
		insertReclamos(o);
	}

	public void update(ReclamoCompuesto o) {
		tryCommand("UPDATE dbo.Reclamo "
				+ "SET fecha = ?, "
				+ "SET fechaCierre = ?, "
				+ "SET descripcionReclamo = ?, "
				+ "SET estado = ?, "
				+ "SET codigoProducto = ?, "
				+ "SET cantidad = ? "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? "
				+ "AND activo = 1 ", 
				s -> {
					int i = super.configureUpdate(s, o);
					s.setInt(i++, o.getNroReclamo());
					s.setString(i++, tipoReclamo.getSimpleName());
				});
	}

	public void delete(ReclamoCompuesto o) {
		deleteReclamos(o);
		super.deleteReclamo(o);
	}

	public ReclamoCompuesto selectOne(int id) {
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
					return new ReclamoCompuesto(
						nroReclamo, 
						rs.getDate("fecha"), 
						rs.getDate("fechaCierre"), 
						rs.getString("descripcionReclamo"), 
						EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						this.getReclamos(nroReclamo));
				});
	}
	
	private void insertReclamos(ReclamoCompuesto o){
		for(Reclamo reclamo : o.getReclamos())
			insertReclamo(o,reclamo);
	}
	
	public void insertReclamo(ReclamoCompuesto o, Reclamo reclamo){
		//LOS RECLAMOS HIJOS YA ESTAN INSERTADOS DESDE ANTES 
		//ASI QUE NO HAY QUE HACER MAS QUE RELACIONAR
		tryCommand("INSERT INTO dbo.ReclamoCompuestoReclamoSimple (nroReclamoCompuesto, nroReclamo, activo) VALUES (?,?,?)", 
				s -> {
					s.setInt(1, o.getNroReclamo());
					s.setInt(2, reclamo.getNroReclamo());
					s.setBoolean(3, true);
				});
	}
	
	private Vector<Reclamo> getReclamos(int nroReclamoCompuesto){
		return tryQueryMany(
				"SELECT "
				+ "r.nroReclamo, "
				+ "r.tipoReclamo "
				+ "FROM dbo.ReclamoCompuestoReclamoSimple rs"
				+ "JOIN dbo.Reclamo r ON r.nroReclamo = rs.nroReclamo"
				+ "WHERE rs.nroReclamoCompuesto = ?",
				s -> s.setInt(1, nroReclamoCompuesto),
				rs -> {
					return ReclamoMapper.getInstancia().buildReclamo(rs.getInt("nroReclamo"), rs.getString("tipoReclamo"));
				});
	}
	
	private void deleteReclamos(ReclamoCompuesto o){
		tryCommand("UPDATE dbo.ReclamoCompuestoReclamoSimple "
				+ "SET activo = 0 "
				+ "WHERE nroReclamoCompuesto = ?",
				s -> s.setInt(1, o.getNroReclamo()));
		
		//TODO: Falta eliminar los reclamos que esten asociados?
	}
}
