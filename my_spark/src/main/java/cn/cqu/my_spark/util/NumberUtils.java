package cn.cqu.my_spark.util;

import java.math.BigDecimal;

/**
 * 
 * 数字工具类
 * @author 一路向北
 *
 */
public class NumberUtils {
	
	/**
	 * 格式化小数
	 * @param str 字符串
	 * @param scale 四舍五入的位数
	 * @return 格式化小数
	 */
	public static double formatDouble(double num, int scale) {
		BigDecimal bd = new BigDecimal(num);  
		return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	

}