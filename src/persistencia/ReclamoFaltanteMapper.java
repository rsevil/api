package persistencia;

import java.util.Vector;

import enums.EstadosReclamo;
import negocio.ReclamoFaltantes;

public class ReclamoFaltanteMapper extends BaseReclamoMapper<ReclamoFaltantes> {

	private static ReclamoFaltanteMapper instance;
	
	private static final String SELECT_ALL = "SELECT * FROM dbo.Reclamo r WHERE r.tipoReclamo = ? AND activo = 1";
	
	private static final String SELECT_ALL_NOT_RESOLVED = SELECT_ALL + " AND (estado <> 'Solucionado' AND estado <> 'Cerrado')";
	
	private ReclamoFaltanteMapper() {
		super(ReclamoFaltantes.class);
	}
	
	public static ReclamoFaltanteMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoFaltanteMapper();
			
		return instance;
	}
	
	public void insert(ReclamoFaltantes o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente, codigoProducto, cantFaltante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> {
					int i = super.configureInsert(s, o);
					s.setInt(i++, o.getProducto().getCodigoProducto());
					s.setInt(i++, o.getCantFaltante());					
				});
	}

	public void update(ReclamoFaltantes o) {
		tryCommand("UPDATE dbo.Reclamo "
				+ "SET fecha = ?, "
				+ "SET fechaCierre = ?, "
				+ "SET descripcionReclamo = ?, "
				+ "SET estado = ?, "
				+ "SET nroCliente = ?, "
				+ "SET codigoProducto = ?, "
				+ "SET cantFaltante = ? "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? "
				+ "AND activo = 1 ", 
				s -> {
					int i = super.configureUpdate(s, o);
					s.setInt(i++, o.getProducto().getCodigoProducto());
					s.setInt(i++, o.getCantFaltante());
					s.setInt(i++, o.getNroReclamo());
					s.setString(i++, tipoReclamo.getSimpleName());
				});
	}

	public void delete(ReclamoFaltantes o) {
		super.deleteReclamo(o);
	}

	public ReclamoFaltantes selectOne(int id) {
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
				rs -> new ReclamoFaltantes(
						rs.getInt("nroReclamo"), 
						rs.getDate("fecha"), 
						rs.getDate("fechaCierre"), 
						rs.getString("descripcionReclamo"), 
						EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getInt("cantFaltante"),
						ProductoMapper.getInstancia().selectOne(rs.getInt("codigoProducto"))));
	}
	
	public Vector<ReclamoFaltantes> selectAll(boolean noSolucionados) {
		String query = SELECT_ALL;
		if(noSolucionados){
			query = SELECT_ALL_NOT_RESOLVED;
		}
		return tryQueryMany(query, s -> {
			s.setString(1, tipoReclamo.getSimpleName());
		} , rs -> new ReclamoFaltantes(rs.getInt("nroReclamo"), rs.getDate("fecha"), rs.getDate("fechaCierre"),
				rs.getString("descripcionReclamo"), EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
				ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")), rs.getInt("cantFaltante"),
				ProductoMapper.getInstancia().selectOne(rs.getInt("codigoProducto"))));
	}
}
