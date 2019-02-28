package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.AdUserClickCount;

/**
 * 
 * 用户广告点击量DAO接口
 * @author 一路向北
 *
 */

public interface IAdUserClickCountDAO {
	
	/**
	 * 
	 * 批量更新用户广告点击量
	 */
	void updateBatch(List<AdUserClickCount> adUserClickCounts);
	
	
	/**
	 * 根据多个key查询用户广告点击量 
	 * 
	 */
	int findClickCountByMultiKey(String date, long userid, long adid);

}
