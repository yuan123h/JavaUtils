package com.test.java;

import java.util.Calendar;

public class TableInfo {
	public TableInfo(String prefix, int year, int month) {
		super();
		this.year = year;
		this.prefix = prefix;
		this.month = month;
	}

	private int year;
	private String prefix;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	private int month;

	public static void main(String[] args) {
		System.out.println(new TableInfo("hbase", 2013, 10).getTableName());
	}

	public int getDayNum() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
		return cal.getActualMaximum(Calendar.DATE);
	}

	public String getTableName() {
		if (month < 10)
			return prefix + "_" + year + "_0" + month;
		else
			return prefix + "_" + year + "_" + month;
	}

}
