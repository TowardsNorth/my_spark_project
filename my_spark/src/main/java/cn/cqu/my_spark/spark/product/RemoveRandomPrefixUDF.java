package cn.cqu.my_spark.spark.product;

import org.apache.spark.sql.api.java.UDF1;

/*
 * 自定义函数
 * RemoveRandomPrefixUDF
 * 
 */
public class RemoveRandomPrefixUDF implements UDF1<String, String> {

	private static final long serialVersionUID = 1L;

	@Override
	public String call(String val) throws Exception {
		String[] valSplited = val.split("_");
		return valSplited[1];
	}

}
