package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.AreaTop3Product;

/**
 * 
 * 各区域top3热门商品DAO接口
 * @author 一路向北
 *
 */

public interface IAreaTop3ProductDAO {
	
	void insertBatch(List<AreaTop3Product> areaTopsProducts);

}
