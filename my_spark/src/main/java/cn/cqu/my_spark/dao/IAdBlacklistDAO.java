package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.AdBlacklist;

/**
 * 
 * 广告黑名单DAO接口
 * @author 一路向北
 *
 */

public interface IAdBlacklistDAO {
	
	/**
	 * 
	 * 批量插入广告黑名单
	 */
	
	void insertBatch(List<AdBlacklist> adBlacklists);
	
	
	/**
	 * 
	 * 查询所有广告黑名单
	 */
	
	List<AdBlacklist> findAll();
	
	

}
