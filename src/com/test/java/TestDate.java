package com.test.java;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class TestDate {
	public TestDate(){};

	@Test
	public void getTableName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		System.out.println("log" + sdf.format(new Date()));
	}

	@Test
	public void getLongDate() throws ParseException {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String str = null;
		str = "2013-05-21 10:50:36";
		date = format1.parse(str);
		System.out.println(date.getTime());
	}

	@Test
	public void getDateLong() {
		long second = 0;
		Date dat = new Date(second);
		dat = new Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy_MM_d"); 	
		System.out.println(format.format(dat));
	}
	
	@Test
	public void getDate() {
		long sd =1392012868008L;
		Date dat = new Date(sd);
		// dat = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-M-d HH:mm:ss");// "yyyy-MM-dd HH:mm:ss");
		String sb = format.format(gc.getTime());
		System.out.println(sb);
	}

	@Test
	public void getHostName() throws UnknownHostException {
		String name = InetAddress.getLocalHost().getHostName();
		System.out.println(name);
	}

	@Test
	public void getCurrentTime() throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sf.format(date);
		String ss[] = dateStr.split("-", -1);
		for (String s : ss) {
			System.out.println(s);
		}
		System.out.println(dateStr);
		date = sf.parse(sf.format(date));
		System.out.println(date.getTime());
	}

	@Test
	public void getYesterday() throws ParseException {
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -1); // 得到前一天
		System.out.println(calendar.getTimeInMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String yestedayDate = sf.format(calendar.getTime());
		System.out.println(yestedayDate);
		Date date = sf.parse(yestedayDate);
		System.out.println(date.getTime());
	}

	@Test
	public void getDay() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String yestedayDate = sf.format(cal.getTime());
		System.out.println(yestedayDate);

		cal = Calendar.getInstance();
		yestedayDate = sf.format(cal.getTime());
		System.out.println(yestedayDate);

	}
	
	@Test
	public void getDay1() {
		java.util.Calendar  c  =  java.util.Calendar.getInstance();  
        System.out.println( c.get(java.util.Calendar.DAY_OF_MONTH) );
	}

	@Test
	public void getday() throws ParseException {
		int index = -1;
		String date = "2013-08-01";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateD = null;
		dateD = format.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateD);
		calendar.add(Calendar.DATE, index);
		System.out.println(format.format(calendar.getTime()));
	}

	@Test
	public void getNextDay() throws ParseException {
		String date = "2013-07-1";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateD = null;
		dateD = format.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateD);
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		System.out.println(format.format(calendar.getTime()));
	}

	@Test
	public void compareDate() {
		String date1 = "2013-08-03";
		String date2 = "2013-08-05";
		int n = 0;
		String formatStyle = "yyyy-MM-dd";

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n--;
			c1.add(Calendar.DATE, 1); // 比较天数，日期+1
		}
		n = n + 2;

		System.out.println(date1 + " -- " + date2 + " 相差多少" + ":" + n);
	}
	
	@Test
	public void testRange() throws ParseException {
		System.out.println(TestDate.getDayRange("2013-08-30", "2013-07-30"));
	}

	private static int getDayRange(String currentDate, String destDate)
			throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sf.parse(currentDate);
		Date date2 = sf.parse(destDate);
		return (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
	}
	
	@Test
	public void getCurrentName() {
		Calendar calendar = Calendar.getInstance();
		//calendar.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
		System.out.println(sdf.format(calendar.getTime()));
	}
	
	@Test
	public void testLong() {
		System.out.println(DateUtils.addDays(new Date(), -1));
	}
	
	@Test
	public void getGetDate() {
		System.out.println(System.currentTimeMillis());
	}
	
}
