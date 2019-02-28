package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.AdProvinceTop3;

/**
 * 
 * 
 * 各省份top3热门广告DAO接口
 * @author 一路向北
 *
 */


public interface IAdProvinceTop3DAO {
	
	/**
	 * 
	 * 更新各省份top3热门广告
	 * @param adProvinceTop3s
	 */
	void updateBatch(List<AdProvinceTop3> adProvinceTop3s);

}
