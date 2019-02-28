package cn.cqu.my_spark.dao;

import java.util.List;

import cn.cqu.my_spark.domain.SessionDetail;

/**
 * 
 * Session明细DAO接口
 * @author 一路向北
 *
 */


public interface ISessionDetailDAO {
	
	/**
	 * 
	 * 插入一条session明细数据
	 */
	void insert(SessionDetail sessionDetail);
	
	
	/**
	 * 
	 * 批量插入session明细数据
	 */
	void insertBatch(List<SessionDetail> sessionDetails);
	

}
