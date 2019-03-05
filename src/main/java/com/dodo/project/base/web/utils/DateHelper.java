package com.dodo.project.base.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * <b>DateHelper</b></br>
 *
 * <pre>
 * 操作日期辅助类
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/20 14:32
 * @Since JDK 1.8
 */
public class DateHelper {
	private final static Logger logger = LoggerFactory.getLogger(DateHelper.class);

	/*
	 * @Description: 格式化日期
	 * @Author: walk_code walk_code@163.com
	 * @Param: [pattern]
	 * @return: java.text.SimpleDateFormat
	 * @Date: 2018/10/20 18:49
	 */
	private static SimpleDateFormat getDateFormat(String pattern) {
		if (null == pattern) {
			pattern = DateStyle.YYYY_MM_DD_HH_MM_SS.getValue();
		}

		return new SimpleDateFormat(pattern);
	}


	/*
	 * @Description: 字符串转日期
	 * @Author: walk_code walk_code@163.com
	 * @Param: [dateStr, patter]
	 * @return: java.util.Date
	 * @Date: 2018/10/20 18:44
	 */
	public static Date strToDate(String dateStr, String patter) {
		try {
			return getDateFormat(patter).parse(dateStr);
		} catch (ParseException e) {
			logger.error("日期格式转换错误：{}", e);
		}

		return null;
	}

	/*
	 * @Description: 字符串转日期
	 * @Author: walk_code walk_code@163.com
	 * @Param: [dateStr]
	 * @return: java.util.Date
	 * @Date: 2018/10/20 19:10
	 */
	public static Date strToDate(String dateStr) {
		if (dateStr.length() == 10) {
			dateStr += " 00:00:00";
		}

		return strToDate(dateStr, DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
	}

	/*
	 * @Description: 日期转字符串
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 * @Date: 2018/11/21 17:45
	 */
	public static String getCurrentDateStr(String pattern) {
		DateFormat dateFormat = getDateFormat(pattern);

		Date   today      = Calendar.getInstance().getTime();
		String reportDate = dateFormat.format(today);

		return reportDate;
	}

	/*
	 * @Description: 日期转字符串
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.lang.String
	 *
	 * @Date: 2018/11/21 17:51
	 */
	public static String getCurrentDateStr() {
		return getCurrentDateStr(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
	}

	/*
	 * @Description: 日期转字符串
	 * @Author: walk_code walk_code@163.com
	 * @Param: [date]
	 * @return: java.lang.String
	 * @Date: 2019/1/17 16:44
	 */
	public static String dateToStr(Date date) {
		return dateToStr(date, DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
	}

	/*
	 * @Description: 日期转字符串
	 * @Author: walk_code walk_code@163.com
	 * @Param: [date, pattern]
	 * @return: java.lang.String
	 * @Date: 2019/1/17 16:45
	 */
	public static String dateToStr(Date date, String pattern) {
		DateFormat dateFormat = getDateFormat(pattern);
		String     reportDate = dateFormat.format(date);

		return reportDate;
	}

}
