package cn.cqu.my_spark.dao.impl;


import cn.cqu.my_spark.dao.IPageSplitConvertRateDAO;
import cn.cqu.my_spark.domain.PageSplitConvertRate;
import cn.cqu.my_spark.jdbc.JDBCHelper;

/**
 * 
 * 页面切片转化率DAO实现类
 * @author 一路向北
 *
 */

public class PageSplitConvertRateDAOImpl implements IPageSplitConvertRateDAO{

	public void insert(PageSplitConvertRate pageSplitConvertRate) {
		
		String sql = "insert into page_split_convert_rate values(?,?)";  
		Object[] params = new Object[]{pageSplitConvertRate.getTaskid(), 
				pageSplitConvertRate.getConvertRate()};
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeUpdate(sql, params);
	}

}
