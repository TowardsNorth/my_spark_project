package cn.cqu.my_spark.test;

import cn.cqu.my_spark.dao.ITaskDAO;
import cn.cqu.my_spark.dao.factory.DAOFactory;
import cn.cqu.my_spark.domain.Task;

/**
 * 任务管理DAO测试类
 * @author Administrator
 *
 */
public class TaskDAOTest {
	
	public static void main(String[] args) {
		ITaskDAO taskDAO = DAOFactory.getTaskDAO();
		Task task = taskDAO.findById(2);
		System.out.println(task.getTaskName());  
	}
	
}
