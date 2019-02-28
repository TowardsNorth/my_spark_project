package cn.cqu.my_spark.dao;

import cn.cqu.my_spark.domain.SessionAggrStat;

/**
 * 
 * session聚合统计模块DAO接口
 * @author 一路向北
 *
 */

public interface ISessionAggrStatDAO {
	
	/**
	 * 
	 * 插入session聚合统计接口
	 */
	
	void insert(SessionAggrStat sessionAggrStat);
	

}
