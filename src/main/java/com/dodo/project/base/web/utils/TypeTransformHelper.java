package com.dodo.project.base.web.utils;

import java.util.Date;

/*
 * <b>TypeTransformHelper</b></br>
 *
 * <pre>
 * 类型转换
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/20 14:09
 * @Since JDK 1.8
 */
public class TypeTransformHelper {

	/*
	 * @Description: Object转Integer, 默认值：defaultValue
	 * @Author: walk_code walk_code@163.com
	 * @Param: [object, defaultValue]
	 * @return: java.lang.Integer
	 * @Date: 2018/10/20 14:16
	 */
	public static Integer objToInt(Object object, Integer defaultValue) {
		if (null == object) {
			return defaultValue;
		}

		String value = String.valueOf(object).trim();
		if ("null".equals(value) || value.length() == 0) {
			return defaultValue;
		}

		return Integer.parseInt(value);
	}

	/*
	 * @Description: Object转Long, 默认值：defaultValue
	 * @Author: walk_code walk_code@163.com
	 * @Param: [object, defaultValue]
	 * @return: java.lang.Long
	 * @Date: 2018/10/20 14:19
	 */
	public static Long objToLong(Object object, Long defaultValue) {
		if (null == object) {
			return defaultValue;
		}

		String value = String.valueOf(object).trim();
		if ("null".equals(value) || value.length() == 0) {
			return defaultValue;
		}

		return Long.valueOf(value);
	}

	/*
	 * @Description: Object转Boolean, 默认值：defaultValue
	 * @Author: walk_code walk_code@163.com
	 * @Param: [object, defaultValue]
	 * @return: boolean
	 * @Date: 2018/10/20 14:21
	 */
	public static boolean objToBoolean(Object object, Boolean defaultValue) {
		if (null == object) {
			return defaultValue;
		}

		String value = String.valueOf(object).trim();
		if ("null".equals(value) || value.length() == 0) {
			return defaultValue;
		}

		if ("1".equals(value) || "true".equals(value)) {
			return Boolean.TRUE;
		} else if ("0".equals(value) || "false".equals(value)) {
			return Boolean.FALSE;
		}

		return Boolean.FALSE;
	}

	/*
	* @Description: Object转Date, 默认值：defaulteValue
	* @Author: walk_code walk_code@163.com
	* @Param: [object, devalutValue]
	* @return: java.util.Date
	* @Date: 2018/10/23 14:56
	*/
	public static Date objToDate(Object object, Date defaultValue) {
		if (null == object) {
			return defaultValue;
		}

		String value = String.valueOf(object).trim();
		if ("null".equals(value) || value.length() == 0) {
			return defaultValue;
		}

		return DateHelper.strToDate(value);
	}
	
	/* 
	* @Description: Object转double, 默认值：defaultValue
	* @Author: walk_code walk_code@163.com
	* @Param: [obj, defaultValue] 
	* @return: double  
	* @Date: 2018/10/23 14:57
	*/ 
	public static double objToDouble(Object obj, double defaultValue) {
		if (null == obj) {
			return defaultValue;
		}

		String value = String.valueOf(obj).trim();
		if ("null".equals(value)) {
			return defaultValue;
		}

		return Double.valueOf(value);

	}
}
