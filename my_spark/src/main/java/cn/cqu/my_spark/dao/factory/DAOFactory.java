package cn.cqu.my_spark.dao.factory;



import cn.cqu.my_spark.dao.IAdBlacklistDAO;
import cn.cqu.my_spark.dao.IAdClickTrendDAO;
import cn.cqu.my_spark.dao.IAdProvinceTop3DAO;
import cn.cqu.my_spark.dao.IAdStatDAO;
import cn.cqu.my_spark.dao.IAdUserClickCountDAO;
import cn.cqu.my_spark.dao.IAreaTop3ProductDAO;
import cn.cqu.my_spark.dao.IPageSplitConvertRateDAO;
import cn.cqu.my_spark.dao.ISessionAggrStatDAO;
import cn.cqu.my_spark.dao.ISessionDetailDAO;
import cn.cqu.my_spark.dao.ISessionRandomExtractDAO;
import cn.cqu.my_spark.dao.ITaskDAO;
import cn.cqu.my_spark.dao.ITop10CategoryDAO;
import cn.cqu.my_spark.dao.ITop10SessionDAO;
import cn.cqu.my_spark.dao.impl.AdBlacklistDAOImpl;
import cn.cqu.my_spark.dao.impl.AdClickTrendDAOImpl;
import cn.cqu.my_spark.dao.impl.AdProvinceTop3DAOImpl;
import cn.cqu.my_spark.dao.impl.AdStatDAOImpl;
import cn.cqu.my_spark.dao.impl.AdUserClickCountDAOImpl;
import cn.cqu.my_spark.dao.impl.AreaTop3ProductDAOImpl;
import cn.cqu.my_spark.dao.impl.PageSplitConvertRateDAOImpl;
import cn.cqu.my_spark.dao.impl.SessionAggrStatDAOImpl;
import cn.cqu.my_spark.dao.impl.SessionDetailDAOImpl;
import cn.cqu.my_spark.dao.impl.SessionRandomExtractDAOImpl;
import cn.cqu.my_spark.dao.impl.TaskDAOImpl;
import cn.cqu.my_spark.dao.impl.Top10CategoryDAOImpl;
import cn.cqu.my_spark.dao.impl.Top10SessionDAOImpl;

public class DAOFactory {
	
	public static ITaskDAO getTaskDAO(){
		return new TaskDAOImpl();
	}
	
	public static ISessionAggrStatDAO getSessionAggrStatDAO() {
		return new SessionAggrStatDAOImpl();
	}
	
	public static ISessionRandomExtractDAO getSessionRandomExtractDAO() {
		return new SessionRandomExtractDAOImpl();
	}
	
	public static ISessionDetailDAO getSessionDetailDAO() {
		return new SessionDetailDAOImpl();
	}
	
	public static ITop10CategoryDAO getTop10CategoryDAO() {
		return new Top10CategoryDAOImpl();
	}
	
	public static ITop10SessionDAO getTop10SessionDAO() {
		return new Top10SessionDAOImpl();
	}
	
	public static IPageSplitConvertRateDAO getPageSplitConvertRateDAO() {
		return new PageSplitConvertRateDAOImpl();
	}
	
	public static IAreaTop3ProductDAO getAreaTop3ProductDAO() {
		return new AreaTop3ProductDAOImpl();
	}
	
	public static IAdUserClickCountDAO getAdUserClickCountDAO() {
		return new AdUserClickCountDAOImpl();
	}
	
	public static IAdBlacklistDAO getAdBlacklistDAO() {
		return new AdBlacklistDAOImpl();
	}
	
	public static IAdStatDAO getAdStatDAO() {
		return new AdStatDAOImpl();
	}
	
	public static IAdProvinceTop3DAO getAdProvinceTop3DAO() {
		return new AdProvinceTop3DAOImpl();
	}
	
	public static IAdClickTrendDAO getAdClickTrendDAO() {
		return new AdClickTrendDAOImpl();
	}
}
