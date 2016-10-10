package persistencia;

import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoCompuesto;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;

public class ReclamoMapper extends BaseReclamoMapper<Reclamo> {

private static ReclamoMapper instance;
	
	private ReclamoMapper() {
		super(Reclamo.class);
	}
	
	public static ReclamoMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoMapper();
			
		return instance;
	}
	
	public Reclamo selectOne(int nroReclamo){
		return tryQuery("SELECT "
				+ "tipoReclamo "
				+ "FROM dbo.Reclamo "
				+ "WHERE nroReclamo = ? "
				+ "AND activo = 1",
				s -> s.setInt(1, nroReclamo),
				rs -> buildReclamo(nroReclamo, rs.getString("tipoReclamo")));
	}
	
	public Reclamo buildReclamo(int nroReclamo, String tipoReclamo){
		if (tipoReclamo == ReclamoCantidades.class.getSimpleName())
			return ReclamoCantidadesMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo == ReclamoCompuesto.class.getSimpleName())
			return ReclamoCompuestoMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo == ReclamoFacturacion.class.getSimpleName())
			return ReclamoFacturacionMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo == ReclamoFaltantes.class.getSimpleName())
			return ReclamoFaltanteMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo == ReclamoProducto.class.getSimpleName())
			return ReclamoProductoMapper.getInstancia().selectOne(nroReclamo);
		else {
			System.out.println("Tipo de reclamo no reconocido: '" + tipoReclamo + "' id: '" + nroReclamo + "'");
			return null;
		}
	}
	
}
