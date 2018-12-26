/*
 * @(#) Utility  2017-07-04 17:57:31
 *
 * Copyright 2003 by TiuWeb Corporation.
 * 51 zhangba six Road, xian, PRC 710065 // Replace with xian’s address
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * TiuWeb Corporation.  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with TiuWeb.
 */
package com.tiuweb.family.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tiuweb.climb.framework.commons.util.UserToken;

/**
 * 
 * <p>
 * Title: 家庭医生团队
 * </p>
 * <p>
 * Description: TODO 家庭医生团队工具类
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-04 17:57:31
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public final class Utility {
	private static final String CONTACT_NUMBER_PREFIX = "C";
	private static final String TEAM_NUMBER_PREFIX = "T";
	private static final String FAMILY_NUMBER_PREFIX = "F";
	private static final String HEALTH_NUMBER_PREFIX = "H";
	private static final String SERVICE_NUMBER_PREFIX = "S";
	private static final String RECORD_NUMBER_PREFIX = "R";

	/**
	 * 健康档案编号
	 * 
	 * @return
	 */
	public static String getServiceNumber() {
		return SERVICE_NUMBER_PREFIX.concat(String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * 家庭编号编号
	 * 
	 * @return
	 */
	public static String getFamilyNumber() {
		return FAMILY_NUMBER_PREFIX.concat(String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * 团队编号
	 * 
	 * @return
	 */
	public static String getTeamNumber() {
		return TEAM_NUMBER_PREFIX.concat(String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * 签约协议单号
	 * 
	 * @return
	 */
	public static String getContactNumber() {
		return CONTACT_NUMBER_PREFIX.concat(String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * 健康档案编号
	 * 
	 * @return
	 */
	public static String getHealthNumber() {
		return HEALTH_NUMBER_PREFIX.concat(String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * 转诊吧编号z
	 * 
	 * @return
	 */
	public static String getRecordNumber() {
		return RECORD_NUMBER_PREFIX.concat(String.valueOf(System.currentTimeMillis()));
	}

	// 获取当前时间所在年的周数
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取两个时间之间的月份总数
	 * 
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthBetween(Date minDate, Date maxDate) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(minDate);
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(maxDate);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		return result.size();
	}

	/**
	 * 生成32位uuid
	 * 
	 * @return
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid.toUpperCase();
	}

	/**
	 * 获取当前时间+1年的时间
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getYearAddedOne(Date currentDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(currentDate);
		// 把日期往后增加一年.整数往后推,负数往前移动
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}

	// public static void main(String[] args) throws ParseException {
	// System.out.println(getWeeksBeforeEndDate(getDateFormat("2017-7-31"),
	// getDateFormat("2018-7-31")));
	// }

	public static int getWeeksBeforeEndDate(Date beginDate, Date endDate) {
		Calendar c_begin = new GregorianCalendar();
		Calendar c_end = new GregorianCalendar();
		// DateFormatSymbols dfs = new DateFormatSymbols();
		// String[] weeks = dfs.getWeekdays();
		c_begin.setTime(beginDate); // Calendar的月从0-11，所以4月是3.
		c_end.setTime(endDate); // Calendar的月从0-11，所以5月是4.

		int count = 1;
		c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天

		while (c_begin.before(c_end)) {
			// System.out.println("第" + count + "周 日期：" + new
			// java.sql.Date(c_begin.getTime().getTime()) + ","
			// + weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);

			if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				count++;
			}
			c_begin.add(Calendar.DAY_OF_YEAR, 1);
		}
		return count;
	}

	/**
	 * 格式化日期 yyyy-MM-dd
	 * 
	 * @param parsedTime
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFormat(String parsedTime) throws ParseException {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return format.parse(parsedTime);
	}

	/**
	 * 计算日期天数差
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDateDiff(Date startDate, Date endDate) {
		long difference = startDate.getTime() - endDate.getTime();
		return difference / (3600 * 24 * 1000);
	}

	/**
	 * @param req
	 * @param url
	 * @param method
	 * @param params
	 *            maybe null
	 * @return
	 */
	public static ResponseEntity<String> request(RestTemplate restTemplate, UserToken token, String url,
			HttpMethod method, Map<String, ?> params) {
		// 获取header信息
		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("userid", token.getUserid());
		requestHeaders.add("usertoken", token.getUsertoken());

		HttpEntity<Object> requestEntity = new HttpEntity<Object>(params, requestHeaders);
		ResponseEntity<String> rss = restTemplate.exchange(url, method, requestEntity, String.class);
		return rss;
	}
}
