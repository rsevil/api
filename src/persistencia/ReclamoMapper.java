package persistencia;

import java.util.Vector;

import negocio.Reclamo;

public class ReclamoMapper extends BaseMapper {
	
	private static ReclamoMapper instance;
	
	protected ReclamoMapper() {
	}
	
	public static ReclamoMapper getInstancia()
	{
		if (instance == null) 
			instance = new ReclamoMapper();
		
		return instance;
	}
	
	protected void insertReclamo(Reclamo o) {
		tryCommand("insert into dbo.Reclamo (?,?,?,?,?,?,?)", s -> {
			s.setInt(1, o.getNumReclamo());
			s.setDate(2, o.getFecha());
			s.setDate(3, o.getFechaCierre());
			s.setString(4, o.getDescripcionReclamo());
			s.setString(5, o.getEstado());
			s.setBoolean(6, o.getActivo());
			s.setInt(7, o.getCliente().getNroCliente());
		});
	}
	
	protected void updateReclamo(Reclamo o) {
		tryCommand("update dbo.Reclamo set fecha=?, set fechaCierre=?, set descripcionReclamo=?, set estado=?, set activo=?, set nroCliente=? where nroReclamo=?", s -> {
			s.setDate(1, o.getFecha());
			s.setDate(2, o.getFechaCierre());
			s.setString(3, o.getDescripcionReclamo());
			s.setString(4, o.getEstado());
			s.setBoolean(5, o.getActivo());
			s.setInt(6, o.getCliente().getNroCliente());
			s.setInt(7, o.getNumReclamo());
		});
	}

	protected void deleteReclamo(Reclamo r) {
		tryCommand("update dbo.Reclamo set activo=0 where codigoProducto=?", s -> {
			s.setInt(1, r.getNumReclamo());
		});
	}
	
//	public Vector<Reclamo> selectAll(Vector<Integer> ids){
//		tryQuery("select "
//				+ "dbo.Reclamo r "
//				+ "left join ReclamoCantidades rc on rc.nroReclamo = r.nroReclamo "
//				+ "left join ")
//	}
}
