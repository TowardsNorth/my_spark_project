package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.AdStat;

/**
 * 
 * 广告实时统计DAO接口
 * @author 一路向北
 *
 */

public interface IAdStatDAO {
	
	/**
	 * 
	 * 更新广告实时统计信息
	 * @param adStats
	 */
	
	void updateBatch(List<AdStat> adStats);
	

}
