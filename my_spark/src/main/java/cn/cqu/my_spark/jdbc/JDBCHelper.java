package cn.cqu.my_spark.jdbc;

import java.util.LinkedList;
import java.util.List;

import cn.cqu.my_spark.conf.ConfigurationManager;
import cn.cqu.my_spark.constant.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 * JDBC辅助组件
 * @author 一路向北
 *
 */

public class JDBCHelper {
	
	
	/**
	 * 项目，要尽量做成可配置的
	 * 就是说，我们的这个数据库驱动，更进一步，也不只是放在常量接口中就可以了
	 * 最好的方式，是放在外部的配置文件中，跟代码彻底分离
	 * 常量接口中，只是包含了这个值对应的key的名字
	 */
	
	static {
		
		try {
			
			String driver=ConfigurationManager.getProperty(Constants.JDBC_DRIVER);
			Class.forName(driver);
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * 第二步，实现JDBCHelper的单例化
	 * 为什么要实现单例化呢？因为它的内部要封装一个简单的内部的数据库连接池
	 * 为了保证数据库连接池有且仅有一份，所以要实现单例化
	 * 保证JDBCHelper只有一个实例，实例中只有一份数据库连接池
	 */
	
	private static JDBCHelper instance=null;
	
	/**
	 * 获取实例
	 * 
	 */
	public static JDBCHelper getInstance()
	{
		if(instance==null)
		{
			synchronized(JDBCHelper.class)
			{
				if (instance==null)
				{
					instance=new JDBCHelper();
				}
			}
		}
		return instance;
	}
	
	
	
	//数据库连接池
	private LinkedList<Connection> datasource=new LinkedList<Connection>();
	
	
	/**
	 * 第三步：实现单例的过程中，创建唯一的数据库连接池
	 * 
	 * 私有化构造方法
	 * 
	 * JDBCHelper在整个程序运行生命周期中，只会创建一次实例
	 * 在这一次创建实例的过程中，就会调用JDBCHelper()构造方法
	 * 此时，就可以在构造方法中，去创建自己唯一的一个数据库连接池
	 * 
	 */
	
	private JDBCHelper()
	{
		/**
		 * 首先第一步，获取数据库连接池的大小，就是说，数据库连接池中要放多少个数据库连接
		 * 这个，可以通过在配置文件中配置的方式，来灵活的配置
		 */
		
		int datasourceSize=ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);
		
		//然后创建指定数量的数据库连接，并放入数据库连接中
		for(int i=0;i<datasourceSize;i++)
		{
			boolean local=ConfigurationManager.getBoolean(Constants.SPARK_LOCAL);
			String url = null;
			String user = null;
			String password = null;
			
			if(local) {
				url = ConfigurationManager.getProperty(Constants.JDBC_URL);
				user = ConfigurationManager.getProperty(Constants.JDBC_USER);
				password = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD);
			} else {
				url = ConfigurationManager.getProperty(Constants.JDBC_URL_PROD);
				user = ConfigurationManager.getProperty(Constants.JDBC_USER_PROD);
				password = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD_PROD);
			}
			
			try {
				Connection conn = DriverManager.getConnection(url, user, password);
				datasource.push(conn);  
			} catch (Exception e) {
				e.printStackTrace(); 
			}	
		}
	}
	
	
	/**
	 * 第四步，提供获取数据库连接的方法
	 * 有可能，你去获取的时候，这个时候，连接都被用完了，你暂时获取不到数据库连接
	 * 所以我们要自己编码实现一个简单的等待机制，去等待获取到数据库连接
	 */
	
	public synchronized Connection getConnection(){
		while(datasource.size()==0){
			try {
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		return datasource.poll();
	}
	
	
	
	/**
	 * 第五步：开发增删改查的方法
	 * 1.执行增删改SQL语句的方法
	 * 2.执行查询SQL语句的方法
	 * 3.批量执行SQL语句的方法
	 */
	
	
	/**
	 * 执行增删改SQL语句
	 */
	
	public int executeUpdate(String sql,Object[] params){
		int rtn=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			
			pstmt=conn.prepareStatement(sql);
			
			if(params!=null && params.length>0)
			{
				for(int i=0;i<params.length;i++)
				{
					pstmt.setObject(i+1, params[i]);
				}
			}
			
			rtn = pstmt.executeUpdate();
			
			conn.commit();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			if(conn!=null){
				datasource.push(conn);
			}
		}
		return rtn;
	}
	
	
	/**
	 * 执行查询SQL语句
	 * 
	 */
	public void executeQuery(String sql, Object[] params, QueryCallback callback) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		conn=getConnection();
		pstmt=conn.prepareStatement(sql);
		
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
		}
		rs=pstmt.executeQuery();
		callback.process(rs);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
			{
				datasource.push(conn);
			}
		}
	}
	
	
	/**
	 * 批量执行SQL语句
	 * 
	 * 批量执行SQL语句，是JDBC中的一个高级功能
	 * 默认情况下，每次执行一条SQL语句，就会通过网络连接，向MySQL发送一次请求
	 * 
	 * 但是，如果在短时间内要执行多条结构完全一模一样的SQL，只是参数不同
	 * 虽然使用PreparedStatement这种方式，可以只编译一次SQL，提高性能，但是，还是对于每次SQL
	 * 都要向MySQL发送一次网络请求
	 * 
	 * 可以通过批量执行SQL语句的功能优化这个性能
	 * 一次性通过PreparedStatement发送多条SQL语句，比如100条、1000条，甚至上万条
	 * 执行的时候，也仅仅编译一次就可以
	 * 这种批量执行SQL语句的方式，可以大大提升性能
	 * 
	 * @param sql
	 * @param paramsList
	 * @return 每条SQL语句影响的行数
	 */
	public int[] executeBatch(String sql, List<Object[]> paramsList) {
		int[] rtn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 第一步：使用Connection对象，取消自动提交
			conn.setAutoCommit(false);  
			
			pstmt = conn.prepareStatement(sql);
			
			// 第二步：使用PreparedStatement.addBatch()方法加入批量的SQL参数
			if(paramsList != null && paramsList.size() > 0) {
				for(Object[] params : paramsList) {
					for(int i = 0; i < params.length; i++) {
						pstmt.setObject(i + 1, params[i]);  
					}
					pstmt.addBatch();
				}
			}
			
			// 第三步：使用PreparedStatement.executeBatch()方法，执行批量的SQL语句
			rtn = pstmt.executeBatch();
			
			// 最后一步：使用Connection对象，提交批量的SQL语句
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			if(conn != null) {
				datasource.push(conn);  
			}
		}
		
		return rtn;
	}
	
	
	
	/**
	 * 静态内部类：查询回调接口
	 * @author Administrator
	 *
	 */
	public static interface QueryCallback {
		
		/**
		 * 处理查询结果
		 * @param rs 
		 * @throws Exception
		 */
		void process(ResultSet rs) throws Exception;
		
	}
}
