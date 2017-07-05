package com.cdrundle.rpc.util;

public class ObjectUtils {
	/**
	 * 
	* @Title: isBaseType
	* @Description: 判断value是否是基本数据类型
	 * @param value 需要转换的值
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	* @author Tjee
	* @date 2017年6月14日
	 */
	public static boolean isBaseType (Object value) {
		if (value instanceof Number || value instanceof Boolean || value instanceof String) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* @Title: isBaseType
	* @Description:判断value是否是基本数据类型
	 * @param value 需要转换的值
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	* @author Tjee
	* @date 2017年6月14日
	 */
	public static boolean isBaseType (Class<?> clzz) {
		if (clzz.equals(String.class) || clzz.equals(int.class) || clzz.equals(short.class) || clzz.equals(byte.class)
				|| clzz.equals(long.class) || clzz.equals(boolean.class) || clzz.equals(Boolean.class) || clzz.equals(float.class) 
				|| clzz.equals(Float.class) || clzz.equals(double.class) || clzz.equals(Double.class)) {
			return true;
				}
		return false;
	}
	
}
