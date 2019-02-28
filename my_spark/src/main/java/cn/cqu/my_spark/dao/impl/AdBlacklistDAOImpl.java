package cn.cqu.my_spark.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.cqu.my_spark.dao.IAdBlacklistDAO;
import cn.cqu.my_spark.domain.AdBlacklist;
import cn.cqu.my_spark.jdbc.JDBCHelper;

/**
 * 
 * 
 * 广告黑名单的实现类
 * @author 一路向北
 *
 */

public class AdBlacklistDAOImpl implements IAdBlacklistDAO{
	
	
	/**
	 * 批量插入广告黑名单用户
	 */

	public void insertBatch(List<AdBlacklist> adBlacklists) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO ad_blacklist VALUES(?)";
		
		List<Object[]> paramsList=new ArrayList<Object[]>();
		
		for(AdBlacklist adBlacklist : adBlacklists)
		{
			Object[] params=new Object[] {adBlacklist.getUserid()};
			paramsList.add(params);
		}
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeBatch(sql, paramsList);
	}

	
	/**
	 * 
	 * 查询所有广告黑名单
	 */
	public List<AdBlacklist> findAll() {
		String sql="SELECT * FROM ad_blacklist";
		
		final List<AdBlacklist> adBlacklists=new ArrayList<AdBlacklist>();
		
		JDBCHelper jdbcHelper=JDBCHelper.getInstance();
		
		jdbcHelper.executeQuery(sql,null,new JDBCHelper.QueryCallback() {
			
			public void process(ResultSet rs) throws Exception {
				// TODO Auto-generated method stub
				
				while(rs.next())
				{
					long userid=Long.valueOf(String.valueOf(rs.getInt(1)));
					
					AdBlacklist adBlacklist=new AdBlacklist();
					adBlacklists.add(adBlacklist);
				}
				
			}
		});
		return adBlacklists;
	}

}
