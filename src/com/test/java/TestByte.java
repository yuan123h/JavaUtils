package com.test.java;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.junit.Test;

public class TestByte {
	@Test
	public void testArrayOffset() throws UnsupportedEncodingException {
		byte[] s = "Chinese".getBytes("UTF-8");
		System.out.println(s.length);
	}
	
	@Test
	public void testByteBuffer() {
		ByteBuffer HEADER = ByteBuffer.wrap("hrpc".getBytes());
		System.out.println(new String (HEADER.array()));
	}
	
	@Test
	public void testByteBuffer1() {
		ByteBuffer dataLengthBuffer = ByteBuffer.allocate(4);
		System.out.println(dataLengthBuffer.remaining());
		
	}
	
	int m;
	
	@Test
	public void testInit(){
		System.out.println(m);
	}
	
	@Test
	public void test1() {
		System.out.println("~".getBytes()[0]);
		byte b = 126;
		System.out.println((char)b);
	}
}
