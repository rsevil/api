package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public abstract class BaseMapper
{
//	public abstract void insert (T o);
//	public abstract void update (T o);
//	public abstract void delete (T o);
	//public abstract Vector<T> selectAll ();
//	public abstract T selectOne (Object o);
	
	protected void tryCommand(String command, Action<PreparedStatement> config) {
		try {
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement(command);
			config.apply(s);
			s.execute();
			PoolConnection.getPoolConnection().releaseConnection(c);
		}
		catch (Exception e)	{
			//System.out.println(e.getClass());
			//System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
	
	protected <TReturn> TReturn tryQuery(String query, Action<PreparedStatement> config, Func<ResultSet, TReturn> fn){
		try{
			return tryQueryMany(query,config,fn).firstElement();
		}catch (Exception e){
			//System.out.println(e.getClass());
			//System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return null;
	}
	
	protected <TReturn> Vector<TReturn> tryQueryMany(String query, Action<PreparedStatement> config, Func<ResultSet, TReturn> fn){
		try {
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement(query);
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
			//System.out.println(e.getClass());
			//System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return null;
	}
}

interface Action<T>{
	void apply(T t) throws Exception;
}

interface Func<T,T2>{
	T2 apply(T t) throws Exception;
}