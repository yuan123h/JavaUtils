package com.test.java;

import org.junit.Test;

//Data Type	Default Value (for fields)
//byte					0
//short					0
//int					0
//long					0L
//float					0.0f
//double				0.0d
//char					'\u0000'		
		//这个是unicode编码，'\u0000'代表的是nul，不是空格，空格的unicode编码是'\u0020'
//String (or any object)  	null
//boolean	false

public class TestDefault {
	boolean boo;
	
	byte b;
	String s;
	char c;
	char t = '\u0020';
	
	@Test
	public void test() {
		System.out.println(b);
		System.out.println(s);
		System.out.println(c);
		System.out.println(t + "hh");
		System.out.println(boo);
	}
	
	@Test
	public void test1(){
		System.out.println((char)0x5A);
	}
}
