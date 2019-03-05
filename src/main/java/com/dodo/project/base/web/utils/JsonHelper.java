package com.dodo.project.base.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/*
 * <b>JsonHelper</b></br>
 *
 * <pre>
 * json操作辅助类
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/20 10:19
 * @Since JDK 1.8
 */
public class JsonHelper {

	/*
	 * @Description: 对象转Json
	 * @Author: walk_code walk_code@163.com
	 * @Param: [object]
	 * @return: java.lang.String
	 * @Date: 2018/10/20 10:34
	 */
	public static String toJson(Object object) {
		if (null == object) {
			return null;
		}

		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
	}

	/*
	 * @Description: json字符串转Map
	 * @Author: walk_code walk_code@163.com
	 * @Param: [jsonString]
	 * @return: java.util.Map<java.lang.Object                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.Object>
	 * @Date: 2018/10/20 10:35
	 */
	public static Map<Object, Object> parseToMap(String jsonString) {
		Map<Object, Object> mapJson = parse(jsonString, Map.class);

		return  mapJson;
	}

	/*
	 * @Description: json字符串转任意pojo
	 * @Author: walk_code walk_code@163.com
	 * @Param: [jsonString, type]
	 * @return: T
	 * @Date: 2018/10/20 10:35
	 */
	public static <T> T parse(String jsonString, Class<T> type) {
		return JSON.parseObject(jsonString, type);
	}

	/*
	 * @Description: json字符串转List
	 * @Author: walk_code walk_code@163.com
	 * @Param: [jsonString, type]
	 * @return: java.util.List<T>
	 * @Date: 2018/10/20 10:37
	 */
	public static <T> List<T> parseArray(String jsonString, Class<T> type) {
		return JSON.parseArray(jsonString, type);
	}


}
