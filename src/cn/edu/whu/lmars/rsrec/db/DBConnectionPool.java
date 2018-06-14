package cn.edu.whu.lmars.rsrec.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接池通用类,使用的是配置文件里的设置，全局保存，静态方法获取
 *
 */
public class DBConnectionPool {
	
	private static ConcurrentMap<String, ComboPooledDataSource> dsMap = new ConcurrentHashMap<String, ComboPooledDataSource>();
	
	/**
	 * 获取Connection
	 * @param dsName
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String dsName) throws Exception{
		DataSource ds = getDataSource(dsName);
		return ds.getConnection();
	}
	/**
	 * 获取数据库连接池
	 * @param dsName	c3p0配置文件中的数据库名
	 * @return	
	 * @throws Exception
	 */
	public static DataSource getDataSource(String dsName) throws Exception{
		if(!dsMap.containsKey(dsName)){
			iniJdbcPool(dsName);
		}
		return dsMap.get(dsName);
	}
	public static void close(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}
	
	private static void iniJdbcPool(String dbName) throws Exception{
		if(dbName==null||dbName.equals("")){
			throw new Exception("poolName is null,please initialize it before calling the method getConnection()!");
		}
		ComboPooledDataSource ds =getPool(dbName);
		dsMap.putIfAbsent(dbName, ds);
	}
	
	private static ComboPooledDataSource getPool(String db){
		ComboPooledDataSource pool=new ComboPooledDataSource(db);
		return pool;
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(System.getProperty("user.dir"));
		System.setProperty("com.mchange.v2.c3p0.cfg.xml","F:/KingWang/公司项目/MuyunCore/src/c3p0-config.xml");//采用绝对路径
//		System.setProperty("com.mchange.v2.c3p0.cfg.xml","classloader:/config/c3p0-config.xml");//怎么在jar里面读取配置文件（也就是打包之后）
		System.out.println(System.getProperty("com.mchange.v2.c3p0.cfg.xml"));//null
		Connection conn=getConnection("db58");
		conn.setAutoCommit(false);
		System.out.println("----");
		close(conn);
	}
}