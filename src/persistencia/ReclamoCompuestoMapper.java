package persistencia;

import java.util.Vector;

import negocio.Reclamo;
import negocio.ReclamoCompuesto;

public class ReclamoCompuestoMapper extends ReclamoMapper {

	private static ReclamoCompuestoMapper instance;
	
	private ReclamoCompuestoMapper() {
	}
	
	public static ReclamoCompuestoMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoCompuestoMapper();
			
		return instance;
	}
	
	public void insert(ReclamoCompuesto o) {
		super.insertReclamo(o);
		tryCommand("insert into dbo.ReclamoCompuesto (?)", s -> {
			s.setInt(1, o.getNumReclamo());
		});
		// TODO Que pasa con los hijos? (aca podriamos usar un visitor para que segun el tipo de reclamo haga el upd que corresponda
	}

	public void update(ReclamoCompuesto o) {
		super.updateReclamo(o);
		// TODO Que pasa con los hijos? (aca podriamos usar un visitor para que segun el tipo de reclamo haga el upd que corresponda
	}

	public void delete(ReclamoCompuesto o) {
		super.deleteReclamo(o);
		// TODO Que pasa con los hijos? (aca podriamos usar un visitor para que segun el tipo de reclamo haga el upd que corresponda
	}

	public ReclamoCompuesto selectOne(int id) {
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
				+ "join dbo.ReclamoCompuesto rc on rc.nroReclamo = r.nroReclamo "
				+ "where nroReclamo = ?", 
				s -> s.setInt(1, id), 
				rs -> {
					int nroReclamo = rs.getInt("nroReclamo"); 
					return new ReclamoCompuesto(
						rs.getDate("fecha"), 
						nroReclamo, 
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getString("descripcionReclamo"), 
						rs.getString("estado"),
						rs.getDate("fechaCierre"),
						getReclamos(nroReclamo),
						rs.getBoolean("activo"));
				}
			);
	}
	
	private Vector<Reclamo> getReclamos(int nroReclamo){
		
		//TODO Agregar el tipo del reclamo en la tabla intermedia, asi se puede agrupar y llamar a los mappers pasandoles un filtro de ids
		Vector<Integer> ids = tryQueryMany(
				"select * "
				+ "from ReclamoCompuestoReclamoSimple rr "
				+ "where nroReclamoCompuesto = ?",
				s -> s.setInt(1, nroReclamo),
				rs ->  rs.getInt("nroReclamoSimple"));
		
		//return ReclamoMapper.getInstancia().selectAll(ids);
		return null;
	}
}
