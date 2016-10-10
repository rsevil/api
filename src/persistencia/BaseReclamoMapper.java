package persistencia;

import java.sql.PreparedStatement;

import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoCompuesto;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;

public class BaseReclamoMapper<TReclamo extends Reclamo> extends BaseMapper {
	
	protected final Class<TReclamo> tipoReclamo;
	
	protected BaseReclamoMapper(Class<TReclamo> tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}
	
	protected int configureInsert(PreparedStatement s, TReclamo o) throws Exception {
		int i = 1;
		s.setInt(i++, o.getNroReclamo());
		s.setString(i++, tipoReclamo.getSimpleName());
		s.setDate(i++, o.getFecha());
		s.setDate(i++, o.getFechaCierre());
		s.setString(i++, o.getDescripcionReclamo());
		s.setString(i++, o.getEstado());
		s.setBoolean(i++, true);
		s.setInt(i++, o.getCliente().getNroCliente());
		return i++;
	}
	
	protected int configureUpdate(PreparedStatement s, Reclamo o) throws Exception {
		int i = 1;
		s.setDate(i++, o.getFecha());
		s.setDate(i++, o.getFechaCierre());
		s.setString(i++, o.getDescripcionReclamo());
		s.setString(i++, o.getEstado());
		s.setInt(i++, o.getCliente().getNroCliente());
		return i;
	}

	protected void deleteReclamo(Reclamo r) {
		tryCommand("update dbo.Reclamo set activo=0 where codigoProducto=?", s -> {
			s.setInt(1, r.getNroReclamo());
		});
	}
	
	private static int ultimoId = -1;
	
	public int getUltimoId(){
		if (ultimoId < 0)
			ultimoId = tryQuery("SELECT ISNULL(MAX(nroReclamo),0) AS nroReclamo FROM dbo.Reclamo", rs -> rs.getInt("nroReclamo"));
		
		ultimoId++;
		return ultimoId;
	}
}
