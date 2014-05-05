package com.test.java.thread.tiger;

import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class TestTigerThread {
	
	int[] posArray = new int[]{-11,3,5,4,10};
	int[] negArray = new int[]{-1,-3,5,4,10};
	
	
	public void testBubbleSort(PrintStream out) throws IOException{
		Thread t1 = new BubbleSortThread(posArray);
		t1.start();
		out.println("Test with positive numbers!");
		
		try {
			t1.join();
			printArray(posArray,out);
		} catch (Exception ignored){};
		
		Thread t2 = new BubbleSortThread(negArray);
		t2.start();
		out.println("Test with negative numbers!");
		try {
			t2.join();
			printArray(posArray,out);
		} catch (Exception ignored){};
		
	}


	private void printArray(int[] posArray2, PrintStream out) {
		for (int a : posArray2) {
			out.println(a);
		}
	}
	
	/**
	 * 测试线程中异常的捕捉
	 */
	@Test
	public void test1() {
		TestTigerThread test = new TestTigerThread();
		try {
		test.testBubbleSort(System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
