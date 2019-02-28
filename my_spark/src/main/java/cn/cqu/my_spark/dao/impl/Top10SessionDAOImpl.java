package cn.cqu.my_spark.dao.impl;
import cn.cqu.my_spark.dao.ITop10SessionDAO;
import cn.cqu.my_spark.domain.Top10Session;
import cn.cqu.my_spark.jdbc.JDBCHelper;

/**
 * 
 * top10活跃session的DAO实现
 * @author 一路向北
 *
 */

public class Top10SessionDAOImpl implements ITop10SessionDAO{

	public void insert(Top10Session top10Session) {
		
		String sql = "insert into top10_session values(?,?,?,?)"; 
		
		Object[] params = new Object[]{top10Session.getTaskid(),
				top10Session.getCategoryid(),
				top10Session.getSessionid(),
				top10Session.getClickCount()};
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeUpdate(sql, params);
		
	}
}
