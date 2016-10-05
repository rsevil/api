package persistencia;
//import java.util.Vector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class BaseMapper<T>
{
	public abstract void insert (T o);
	public abstract void update (T o);
	public abstract void delete (T o);
	//public abstract Vector<T> selectAll ();
	public abstract T selectOne (Object o);
	
	protected void tryExecuteCommand(String command, Action<PreparedStatement> config) {
		try {
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement(command);
			config.apply(s);
			s.execute();
			PoolConnection.getPoolConnection().releaseConnection(c);
		}
		catch (Exception e)	{
			System.out.println();
		}
	}
	
	protected T tryExecuteQuery(String query, Action<PreparedStatement> config, Func<ResultSet, T> fn){
		T o = null;
		
		try {
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement(query);
			ResultSet rs = s.executeQuery();
			while (rs.next()){
				o = fn.apply(rs);				
			}
			PoolConnection.getPoolConnection().releaseConnection(c);
		}
		catch (Exception e)	{
			System.out.println();
		}
		return o;
	}
}

interface Action<T>{
	void apply(T t) throws Exception;
}

interface Func<T,T2>{
	T2 apply(T t) throws Exception;
}