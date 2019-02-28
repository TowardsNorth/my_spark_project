package cn.cqu.my_spark.dao.impl;


import cn.cqu.my_spark.dao.ISessionRandomExtractDAO;
import cn.cqu.my_spark.domain.SessionRandomExtract;
import cn.cqu.my_spark.jdbc.JDBCHelper;

/**
 * 
 * 随机抽取session的DAO实现
 * @author 一路向北
 *
 */

public class SessionRandomExtractDAOImpl implements ISessionRandomExtractDAO{
	
	/**
	 * 
	 * 插入session随机抽取
	 */

	public void insert(SessionRandomExtract sessionRandomExtract) {
		
		String sql = "insert into session_random_extract values(?,?,?,?,?)";
		
		Object[] params = new Object[]{sessionRandomExtract.getTaskid(),
				sessionRandomExtract.getSessionid(),
				sessionRandomExtract.getStartTime(),
				sessionRandomExtract.getSearchKeywords(),
				sessionRandomExtract.getClickCategoryIds()};
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeUpdate(sql, params);
		
	}

}
