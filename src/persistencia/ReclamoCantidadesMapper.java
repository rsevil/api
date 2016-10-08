package persistencia;

import negocio.ReclamoCantidades;

public class ReclamoCantidadesMapper extends ReclamoMapper<ReclamoCantidades> {

	private static ReclamoCantidadesMapper instance;
	
	private ReclamoCantidadesMapper() {
		super(ReclamoCantidades.class);
	}
	
	public static ReclamoCantidadesMapper getInstancia()
	{
		if (instance == null) {
			instance = new ReclamoCantidadesMapper();
		}
		return instance;
	}
	
	public void insert(ReclamoCantidades o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente, codigoProducto, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> {
					int i = super.configureInsert(s, o);
					s.setString(i++, o.getProducto().getCodigoProducto());
					s.setInt(i++, o.getCantidad());					
				});
	}

	public void update(ReclamoCantidades o) {
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
					s.setString(i++, o.getProducto().getCodigoProducto());
					s.setInt(i++, o.getCantidad());
					s.setInt(i++, o.getNroReclamo());
					s.setString(i++, tipoReclamo.getSimpleName());
				});
	}

	public void delete(ReclamoCantidades o) {
		super.deleteReclamo(o);
	}

	public ReclamoCantidades selectOne(int id) {
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
				rs -> new ReclamoCantidades(
						rs.getInt("nroReclamo"), 
						rs.getDate("fecha"), 
						rs.getDate("fechaCierre"), 
						rs.getString("descripcionReclamo"), 
						rs.getString("estado"),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						rs.getInt("cantidad"),
						ProductoMapper.getInstancia().selectOne(rs.getString("codigoProducto"))));
	}
}
