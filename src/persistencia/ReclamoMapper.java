package persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import enums.TiposReclamo;
import negocio.NovedadReclamo;
import negocio.Reclamo;
import negocio.Reporte;

public class ReclamoMapper extends BaseReclamoMapper<Reclamo> {

	private static ReclamoMapper instance;

	private static final String INSERT_NOVEDAD = "INSERT INTO dbo.NovedadReclamo (nroReclamo, fecha, novedad) VALUES (?, ?, ?)";

	private static final String DELETE_RECLAMO = "UPDATE dbo.Reclamo SET activo = 0 WHERE nroReclamo = ?";

	private static final String CERRAR_RECLAMO = "UPDATE dbo.Reclamo SET estado = ?, fechaCierre = ? WHERE nroReclamo = ?";
	
	private static final String UPDATE_ESTADO = "UPDATE dbo.Reclamo SET estado = ? WHERE nroReclamo = ?";
	
	private static final String REPORTE_RANKING_CLIENTES = "select " + "c.nombre as nombreCliente, "
			+ "count(*) as cantidad " + "from Reclamo r " + "inner join Cliente c on c.nroCliente = r.nroCliente "
			+ "group by c.nombre " + "order by count(*) desc";

	private static final String REPORTE_RECLAMOS_TRATADOS_MES_ANIO = "select " + "datepart(mm, fecha) as mes, "
			+ "datepart(yy, fecha) as anio, " + "count(*) as cantidad " + "from Reclamo "
			+ "group by datepart(mm, fecha), datepart(yy, fecha)";

	/*private static final String REPORTE_RANKING_TRATAMIENTO_RECLAMOS = "select " + "case "
			+ "when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion' "
			+ "when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoProducto' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoZona' then 'zona' " + "else '' " + "end as nombreUsuario, "
			+ "count(*) as cantidad " + "from Reclamo r "
			+ "where r.fecha between ? and ? and r.tipoReclamo <> 'ReclamoCompuesto' " + "group by r.tipoReclamo "
			+ "order by count(*) desc";*/

	private static final String REPORTE_RANKING_TRATAMIENTO_RECLAMOS = "select t.nombreUsuario, sum(t.cantidad) as cantidad "
			+ "from "
			+ "( "
			+ "select " 
			+ "case "
			+ "when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion' "
			+ "when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoProducto' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoZona' then 'zona' "
			+ "else ''  "
			+ "end as nombreUsuario, "
			+ "count(*) as cantidad "
			+ "from Reclamo r "
			+ "where r.fecha between ? and ? and r.tipoReclamo <> 'ReclamoCompuesto' "
			+ "group by r.tipoReclamo "
			+ ") as t "
			+ "group by t.nombreUsuario "
			+ "order by cantidad desc ";

	private static final String REPORTE_TIEMPO_PROMEDIO_RESPUESTA = "select " + "case "
			+ "when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion' "
			+ "when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoProducto' then 'distribucion' "
			+ "when r.tipoReclamo = 'ReclamoZona' then 'zona' " + "else '' " + "end as nombreUsuario, "
			+ "avg(datediff(hh, r.fecha, r.fechaCierre)) as tiempoRespuestaPromedio " + "from Reclamo r "
			+ "where r.fecha between ? and ? " + "and r.tipoReclamo <> 'ReclamoCompuesto' "
			+ "and r.estado = 'Cerrado' " + "and r.fechaCierre is not null " + "group by r.tipoReclamo";

	private ReclamoMapper() {
		super(Reclamo.class);
	}

	public static ReclamoMapper getInstancia() {
		if (instance == null)
			instance = new ReclamoMapper();

		return instance;
	}

	public Reclamo selectOne(int nroReclamo) {
		return tryQuery("SELECT " + "tipoReclamo " + "FROM dbo.Reclamo " + "WHERE nroReclamo = ? " + "AND activo = 1",
				s -> s.setInt(1, nroReclamo), rs -> buildReclamo(nroReclamo, rs.getString("tipoReclamo")));
	}

	public Reclamo buildReclamo(int nroReclamo, String tipoReclamo) {

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
		 * if (tipoReclamo.equals(ReclamoCantidades.class.getSimpleName()))
		 * return ReclamoCantidadesMapper.getInstancia().selectOne(nroReclamo);
		 * else if (tipoReclamo.equals(ReclamoCompuesto.class.getSimpleName()))
		 * return ReclamoCompuestoMapper.getInstancia().selectOne(nroReclamo);
		 * else if
		 * (tipoReclamo.equals(ReclamoFacturacion.class.getSimpleName())) return
		 * ReclamoFacturacionMapper.getInstancia().selectOne(nroReclamo); else
		 * if (tipoReclamo.equals(ReclamoFaltantes.class.getSimpleName()))
		 * return ReclamoFaltanteMapper.getInstancia().selectOne(nroReclamo);
		 * else if (tipoReclamo.equals(ReclamoProducto.class.getSimpleName()))
		 * return ReclamoProductoMapper.getInstancia().selectOne(nroReclamo);
		 * else { System.out.println("Tipo de reclamo no reconocido: '" +
		 * tipoReclamo + "' id: '" + nroReclamo + "'"); return null; }
		 */

	}

	/*
	 * public void updateReclamo(Reclamo reclamo, String tipoReclamo) { switch
	 * (tipoReclamo) { case TiposReclamo.RECLAMO_CANTIDADES:
	 * ReclamoCantidadesMapper.getInstancia().update((ReclamoCantidades)reclamo)
	 * ; case TiposReclamo.RECLAMO_COMPUESTO:
	 * ReclamoCompuestoMapper.getInstancia().update((ReclamoCompuesto)reclamo);
	 * case TiposReclamo.RECLAMO_FACTURACION:
	 * ReclamoFacturacionMapper.getInstancia().update((ReclamoFacturacion)
	 * reclamo); case TiposReclamo.RECLAMO_FALTANTES:
	 * ReclamoFaltanteMapper.getInstancia().update((ReclamoFaltantes)reclamo);
	 * case TiposReclamo.RECLAMO_PRODUCTO:
	 * ReclamoProductoMapper.getInstancia().update((ReclamoProducto)reclamo);
	 * case TiposReclamo.RECLAMO_ZONA:
	 * ReclamoZonaMapper.getInstancia().update((ReclamoZona)reclamo); default:
	 * System.out.println("Tipo de reclamo no reconocido: '" + tipoReclamo +
	 * "' id: '" + reclamo.getNroReclamo() + "'"); } }
	 */

	public Vector<Reporte> getReporteRankingClientes() {
		return tryQueryMany(ReclamoMapper.REPORTE_RANKING_CLIENTES, null, rs -> {
			String nombre = rs.getString("nombreCliente");
			int cantidad = rs.getInt("cantidad");
			int mes = 0;
			int anio = 0;
			return new Reporte(nombre, cantidad, mes, anio);
		});
	}

	public Vector<Reporte> getReporteReclamosTratadosMesAnio() {
		return tryQueryMany(ReclamoMapper.REPORTE_RECLAMOS_TRATADOS_MES_ANIO, null, rs -> {
			String nombre = null;
			int mes = rs.getInt("mes");
			int anio = rs.getInt("anio");
			int cantidad = rs.getInt("cantidad");
			return new Reporte(nombre, cantidad, mes, anio);
		});
	}

	public Vector<Reporte> getReporteRankingTratamientoReclamos(Date fechaDesde, Date fechaHasta) {
		return tryQueryMany(ReclamoMapper.REPORTE_RANKING_TRATAMIENTO_RECLAMOS, s -> {
			s.setDate(1, new java.sql.Date(fechaDesde.getTime()));
			s.setDate(2, new java.sql.Date(fechaHasta.getTime()));
		} , rs -> {
			String nombre = rs.getString("nombreUsuario");
			int cantidad = rs.getInt("cantidad");
			int mes = 0;
			int anio = 0;
			return new Reporte(nombre, cantidad, mes, anio);
		});
	}

	public Vector<Reporte> getReporteTiempoPromedioRespuesta(Date fechaDesde, Date fechaHasta) {
		return tryQueryMany(ReclamoMapper.REPORTE_TIEMPO_PROMEDIO_RESPUESTA, s -> {
			s.setDate(1, new java.sql.Date(fechaDesde.getTime()));
			s.setDate(2, new java.sql.Date(fechaHasta.getTime()));
		} , rs -> {
			String nombre = rs.getString("nombreUsuario");
			int cantidad = rs.getInt("tiempoRespuestaPromedio");
			int mes = 0;
			int anio = 0;
			return new Reporte(nombre, cantidad, mes, anio);
		});
	}

	public void addNovedad(NovedadReclamo novedad) {
		tryCommand(INSERT_NOVEDAD, s -> {
			s.setInt(1, novedad.getNroReclamo());
			s.setDate(2, novedad.getFecha());
			s.setString(3, novedad.getNovedad());
		});

	}

	public void borrarReclamos(ArrayList<Integer> lista) {
		for (Integer integer : lista) {
			tryCommand(DELETE_RECLAMO, s -> {
				s.setInt(1, integer);
			});
		}
	}

	public void cerrarReclamo(int nroReclamo, String estado, java.sql.Date fecha) {
		tryCommand(CERRAR_RECLAMO, s -> {
			s.setString(1, estado);
			s.setDate(2, fecha);
			s.setInt(3, nroReclamo);
		});
	}
	
	public void updateEstado(final int nroReclamo, final String estado){
		tryCommand(UPDATE_ESTADO, s -> {
			s.setString(1, estado);
			s.setInt(2, nroReclamo);
		});
	}
}
