package persistencia;

import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoCompuesto;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;

public class ReclamoMapper extends BaseReclamoMapper<Reclamo> {

private static ReclamoMapper instance;
	

private static final String RECLAMO_CANTIDADES = "ReclamoCantidades";
private static final String RECLAMO_COMPUESTO = "ReclamoCompuesto";
private static final String RECLAMO_FACTURACION = "ReclamoFacturacion";
private static final String RECLAMO_FALTANTES = "ReclamoFaltantes";
private static final String RECLAMO_PRODUCTO = "ReclamoProducto";



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
		
		switch (tipoReclamo) {
        case RECLAMO_CANTIDADES:
        	return ReclamoCantidadesMapper.getInstancia().selectOne(nroReclamo);
        case RECLAMO_COMPUESTO:
        	return ReclamoCompuestoMapper.getInstancia().selectOne(nroReclamo);
        case RECLAMO_FACTURACION:
        	return ReclamoFacturacionMapper.getInstancia().selectOne(nroReclamo);
        case RECLAMO_FALTANTES:
        	return ReclamoFaltanteMapper.getInstancia().selectOne(nroReclamo);
        case RECLAMO_PRODUCTO:
        	return ReclamoProductoMapper.getInstancia().selectOne(nroReclamo);
        default:
        	System.out.println("Tipo de reclamo no reconocido: '" + tipoReclamo + "' id: '" + nroReclamo + "'");
			return null;
		}
			
		/*
		if (tipoReclamo.equals(ReclamoCantidades.class.getSimpleName()))
			return ReclamoCantidadesMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo.equals(ReclamoCompuesto.class.getSimpleName()))
			return ReclamoCompuestoMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo.equals(ReclamoFacturacion.class.getSimpleName()))
			return ReclamoFacturacionMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo.equals(ReclamoFaltantes.class.getSimpleName()))
			return ReclamoFaltanteMapper.getInstancia().selectOne(nroReclamo);
		else if (tipoReclamo.equals(ReclamoProducto.class.getSimpleName()))
			return ReclamoProductoMapper.getInstancia().selectOne(nroReclamo);
		else {
			System.out.println("Tipo de reclamo no reconocido: '" + tipoReclamo + "' id: '" + nroReclamo + "'");
			return null;
		}
		*/
		
	}
	
}
