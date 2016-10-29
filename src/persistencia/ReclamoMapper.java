package persistencia;

import negocio.*;
import enums.EstadosReclamo;
import enums.TiposReclamo;

import java.util.Date;
import java.util.Vector;

public class ReclamoMapper extends BaseReclamoMapper<Reclamo> {

private static ReclamoMapper instance;
	

/*private static final String RECLAMO_CANTIDADES = "ReclamoCantidades";
private static final String RECLAMO_COMPUESTO = "ReclamoCompuesto";
private static final String RECLAMO_FACTURACION = "ReclamoFacturacion";
private static final String RECLAMO_FALTANTES = "ReclamoFaltantes";
private static final String RECLAMO_PRODUCTO = "ReclamoProducto";*/

private static final String REPORTE_RANKING_CLIENTES = "select " +
			"c.nombre as nombreCliente, " +
			"count(*) as cantidad " +
			"from Reclamo r " +
			"inner join Cliente c on c.nroCliente = r.nroCliente " +
			"group by c.nombre " +
			"order by count(*) desc";

private static final String REPORTE_RECLAMOS_TRATADOS_MES_ANIO = "select " + 
			"datepart(mm, fecha) as mes, " +
			"datepart(yy, fecha) as anio, " +
			"count(*) as cantidad " +
			"from Reclamo " +
			"group by datepart(mm, fecha), datepart(yy, fecha)";

private static final String REPORTE_RANKING_TRATAMIENTO_RECLAMOS = "select " +
			"case " +
				"when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion' " +
				"when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion' " +
				"when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion' " +
				"when r.tipoReclamo = 'ReclamoProducto' then 'distribucion' " +
				"when r.tipoReclamo = 'ReclamoZona' then 'zona' " +
				"else '' " +
			"end as nombreUsuario, " +
			"count(*) as cantidad " +
	"from Reclamo r " +
	"where r.fecha between ? and ? and r.tipoReclamo <> 'ReclamoCompuesto' " +
	"group by r.tipoReclamo " +
	"order by count(*) desc";

private static final String REPORTE_TIEMPO_PROMEDIO_RESPUESTA = "select " +
			"case " +
				"when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion' " +
				"when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion' " +
				"when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion' " +
				"when r.tipoReclamo = 'ReclamoProducto' then 'distribucion' " +
				"when r.tipoReclamo = 'ReclamoZona' then 'zona' " +
				"else '' " +
			"end as nombreUsuario, " +
			"avg(datediff(hh, r.fecha, r.fechaCierre)) as tiempoRespuestaPromedio " +
	"from Reclamo r " +
	"where r.fecha between ? and ? " +
		"and r.tipoReclamo <> 'ReclamoCompuesto' " +
		"and r.estado = 'Cerrado' " +
		"and r.fechaCierre is not null " +
	"group by r.tipoReclamo";


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
        case TiposReclamo.RECLAMO_CANTIDADES:
        	return ReclamoCantidadesMapper.getInstancia().selectOne(nroReclamo);
        case TiposReclamo.RECLAMO_COMPUESTO:
        	return ReclamoCompuestoMapper.getInstancia().selectOne(nroReclamo);
        case TiposReclamo.RECLAMO_FACTURACION:
        	return ReclamoFacturacionMapper.getInstancia().selectOne(nroReclamo);
        case TiposReclamo.RECLAMO_FALTANTES:
        	return ReclamoFaltanteMapper.getInstancia().selectOne(nroReclamo);
        case TiposReclamo.RECLAMO_PRODUCTO:
        	return ReclamoProductoMapper.getInstancia().selectOne(nroReclamo);
        case TiposReclamo.RECLAMO_ZONA:
        	return ReclamoZonaMapper.getInstancia().selectOne(nroReclamo);
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
	
	/*public void updateReclamo(Reclamo reclamo, String tipoReclamo) {
		switch (tipoReclamo) {
        case TiposReclamo.RECLAMO_CANTIDADES:
        	ReclamoCantidadesMapper.getInstancia().update((ReclamoCantidades)reclamo);
        case TiposReclamo.RECLAMO_COMPUESTO:
        	ReclamoCompuestoMapper.getInstancia().update((ReclamoCompuesto)reclamo);
        case TiposReclamo.RECLAMO_FACTURACION:
        	ReclamoFacturacionMapper.getInstancia().update((ReclamoFacturacion)reclamo);
        case TiposReclamo.RECLAMO_FALTANTES:
        	ReclamoFaltanteMapper.getInstancia().update((ReclamoFaltantes)reclamo);
        case TiposReclamo.RECLAMO_PRODUCTO:
        	ReclamoProductoMapper.getInstancia().update((ReclamoProducto)reclamo);
        case TiposReclamo.RECLAMO_ZONA:
        	ReclamoZonaMapper.getInstancia().update((ReclamoZona)reclamo);
        default:
        	System.out.println("Tipo de reclamo no reconocido: '" + tipoReclamo + "' id: '" + reclamo.getNroReclamo() + "'");
		}
	}*/
	
	public Vector<Reporte> getReporteRankingClientes() {
		return tryQueryMany(ReclamoMapper.REPORTE_RANKING_CLIENTES,
				null, rs -> {
					String nombre = rs.getString("nombreCliente");
					int cantidad = rs.getInt("cantidad");
					int mes = 0;
					int anio = 0;		
					return new Reporte(
						nombre,
						cantidad, 
						mes, 
						anio);
				});
	}
	
	public Vector<Reporte> getReporteReclamosTratadosMesAnio(int paramMes, int paramAnio) {
		return tryQueryMany(ReclamoMapper.REPORTE_RECLAMOS_TRATADOS_MES_ANIO,
				s -> { s.setInt(1, paramMes); s.setInt(2, paramAnio); }, 
				rs -> {
					String nombre = null;
					int cantidad = rs.getInt("cantidad");
					int mes = rs.getInt("mes");
					int anio = rs.getInt("anio");		
					return new Reporte(
						nombre,
						cantidad, 
						mes, 
						anio);
				});
	}
	
	public Vector<Reporte> getReporteRankingTratamientoReclamos(Date fechaDesde, Date fechaHasta) {
		return tryQueryMany(ReclamoMapper.REPORTE_RANKING_TRATAMIENTO_RECLAMOS,
				s -> { s.setDate(1, new java.sql.Date(fechaDesde.getTime())); s.setDate(2, new java.sql.Date(fechaHasta.getTime())); }, 
				rs -> {
					String nombre = rs.getString("nombreUsuario");
					int cantidad = rs.getInt("cantidad");
					int mes = 0;
					int anio = 0;		
					return new Reporte(
						nombre,
						cantidad, 
						mes, 
						anio);
				});
	}
	
	public Vector<Reporte> getReporteTiempoPromedioRespuesta(Date fechaDesde, Date fechaHasta) {
		return tryQueryMany(ReclamoMapper.REPORTE_TIEMPO_PROMEDIO_RESPUESTA,
				null, rs -> {
					String nombre = rs.getString("nombreCliente");
					int cantidad = rs.getInt("tiempoRespuestaPromedio");
					int mes = 0;
					int anio = 0;		
					return new Reporte(
						nombre,
						cantidad, 
						mes, 
						anio);
				});
	}
}
