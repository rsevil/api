package persistencia;

import java.util.Vector;

import enums.EstadosReclamo;
import negocio.ReclamoZona;

public class ReclamoZonaMapper extends BaseReclamoMapper<ReclamoZona> {

	private static final String SELECT_ALL = "SELECT * FROM dbo.Reclamo r AND r.tipoReclamo = ? AND activo = 1";
	private static ReclamoZonaMapper instance;
	
	private ReclamoZonaMapper() {
		super(ReclamoZona.class);
	}
	
	public static ReclamoZonaMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoZonaMapper();
			
		return instance;
	}
	
	public void insert(ReclamoZona o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente, zonaAfectada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> {
					int i = super.configureInsert(s, o);
					s.setString(i++, o.getZonaAfectada());				
				});
	}

	public void update(ReclamoZona o) {
		tryCommand("UPDATE dbo.Reclamo "
				+ "SET fecha = ?, "
				+ "SET fechaCierre = ?, "
				+ "SET descripcionReclamo = ?, "
				+ "SET estado = ?, "
				+ "SET zonaAfectada = ?, "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? "
				+ "AND activo = 1 ", 
				s -> {
					int i = super.configureUpdate(s, o);
					s.setString(i++, o.getZonaAfectada());
					s.setInt(i++, o.getNroReclamo());
					s.setString(i++, tipoReclamo.getSimpleName());
				});
	}

	public void delete(ReclamoZona o) {
		super.deleteReclamo(o);
	}

	public ReclamoZona selectOne(int id) {
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
				rs -> new ReclamoZona(
						rs.getInt("nroReclamo"), 
						rs.getDate("fecha"), 
						rs.getDate("fechaCierre"), 
						rs.getString("descripcionReclamo"), 
						EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getString("zonaAfectada")));
	}
	
	public Vector<ReclamoZona> selectAll() {
		return tryQueryMany(SELECT_ALL, s -> {
			s.setString(1, tipoReclamo.getSimpleName());
		} , rs -> new ReclamoZona(rs.getInt("nroReclamo"), rs.getDate("fecha"), rs.getDate("fechaCierre"),
				rs.getString("descripcionReclamo"), EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
				ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")), rs.getString("zonaAfectada")));
	}
}
