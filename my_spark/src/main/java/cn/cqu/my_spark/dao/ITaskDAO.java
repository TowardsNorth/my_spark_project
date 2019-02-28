package cn.cqu.my_spark.dao;

import cn.cqu.my_spark.domain.Task;

/**
 * 
 * 任务管理DAO会接口
 */
public interface ITaskDAO {
	
	/**
	 * 
	 * 根据主键查询任务
	 * @param taskid
	 * @return
	 */
	
	
	Task findById(long taskid);

}
