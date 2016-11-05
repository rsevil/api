package persistencia;

import java.util.Vector;

import enums.EstadosReclamo;
import negocio.ItemReclamoCantidad;
import negocio.ReclamoCantidades;

public class ReclamoCantidadesMapper extends BaseReclamoMapper<ReclamoCantidades> {

	private static ReclamoCantidadesMapper instance;
	
	private ReclamoCantidadesMapper() {
		super(ReclamoCantidades.class);
	}
	
	public static ReclamoCantidadesMapper getInstancia()
	{
		if (instance == null)
			instance = new ReclamoCantidadesMapper();
		
		return instance;
	}
	
	public void insert(ReclamoCantidades o) {
		tryCommand("INSERT INTO dbo.Reclamo (nroReclamo, tipoReclamo, fecha, fechaCierre, descripcionReclamo, estado, activo, nroCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
				s -> super.configureInsert(s, o));
	}

	public void update(ReclamoCantidades o) {
		tryCommand("UPDATE dbo.Reclamo "
				+ "SET fecha = ?, "
				+ "SET fechaCierre = ?, "
				+ "SET descripcionReclamo = ?, "
				+ "SET estado = ?, "
				+ "SET nroCliente = ?, "
				+ "WHERE nroReclamo = ? "
				+ "AND tipoReclamo = ? "
				+ "AND activo = 1 ", 
				s -> {
					int i = super.configureUpdate(s, o);
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
						EstadosReclamo.getEstadoReclamo(rs.getString("estado")),
						ClienteMapper.getInstancia().selectOne(rs.getInt("nroCliente")),
						getItems(id)));
	}

	private Vector<ItemReclamoCantidad> getItems(int nroReclamo){
		return tryQueryMany(
				"SELECT * "
				+ "FROM dbo.ItemReclamoCantidad "
				+ "WHERE nroReclamo = ?",
				s -> s.setInt(1, nroReclamo),
				rs -> new ItemReclamoCantidad(
						rs.getInt("cantidad"),
						ProductoMapper.getInstancia().selectOne(rs.getInt("codigoProducto"))));
	}
	
	public void insertItemReclamoCantidad(ReclamoCantidades o, ItemReclamoCantidad item) {
		tryCommand("INSERT INTO dbo.ItemReclamoCantidad (cantidad, activo, nroReclamo, codigoProducto) VALUES (?,?,?,?)", 
				s -> {
					s.setInt(1, item.getCantidad());
					s.setBoolean(2, true);
					s.setInt(3, o.getNroReclamo());
					s.setInt(4, item.getProducto().getCodigoProducto());
				});
	}

	public void updateItemReclamoCantidad(ReclamoCantidades reclamoCantidades, ItemReclamoCantidad item) {
		tryCommand("UPDATE dbo.ItemReclamoCantidad "
				+ "SET cantidad = ? "
				+ "WHERE nroReclamo = ? "
				+ "AND codigoProducto = ? "
				+ "AND activo = 1", 
				s -> {
					s.setInt(1, item.getCantidad());
					s.setInt(2, reclamoCantidades.getNroReclamo());
					s.setInt(3, item.getProducto().getCodigoProducto());
				});
	}
}
