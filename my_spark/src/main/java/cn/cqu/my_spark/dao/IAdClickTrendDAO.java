package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.AdClickTrend;

/**
 * 
 * 广告点击趋势DAO接口
 * @author 一路向北
 *
 */

public interface IAdClickTrendDAO {
	
	
	
	/**
	 * 
	 * 更新广告点击趋势
	 * @param adClickTrends
	 */
	
	void updateBatch(List<AdClickTrend> adClickTrends);
	

}
