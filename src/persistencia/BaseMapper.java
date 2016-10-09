package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import utils.Action;
import utils.Func2;

public abstract class BaseMapper
{	
	private boolean mostrarExcepciones = true;
	
	protected void tryCommand(String command, Action<PreparedStatement> config) {
		try {
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement(command);
			config.apply(s);
			s.execute();
			PoolConnection.getPoolConnection().releaseConnection(c);
		}
		catch (Exception e)	{
			if (mostrarExcepciones) {
				System.out.println(e.getClass());
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	protected <TReturn> TReturn tryQuery(String query, Action<PreparedStatement> config, Func2<ResultSet, TReturn> fn){
		try{
			return tryQueryMany(query,config,fn).firstElement();
		}catch (Exception e){
			if (mostrarExcepciones) {
				System.out.println(e.getClass());
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected <TReturn> TReturn tryQuery(String query, Func2<ResultSet, TReturn> fn){
		try{
			return tryQueryMany(query,null,fn).firstElement();
		}catch (Exception e){
			if (mostrarExcepciones) {
				System.out.println(e.getClass());
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected <TReturn> Vector<TReturn> tryQueryMany(String query, Action<PreparedStatement> config, Func2<ResultSet, TReturn> fn){
		try {
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement(query);
			if (config != null)
				config.apply(s);
			ResultSet rs = s.executeQuery();
			Vector<TReturn> o = new Vector<TReturn>();
			while (rs.next()){
				o.add(fn.apply(rs));				
			}
			PoolConnection.getPoolConnection().releaseConnection(c);
			return o;
		}
		catch (Exception e)	{
			if (mostrarExcepciones) {
				System.out.println(e.getClass());
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
}