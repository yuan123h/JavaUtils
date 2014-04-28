package com.test.java;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Arrays jdk 5.0 Tiger 中的功能
 * 
 * @author yuanhuan
 * 
 */
public class TestArray {
	int[] arr;

	@Test
	public void testArrays() {
		arr = new int[10];
		Arrays.fill(arr, 2, 5, new Double(Math.PI).intValue());
		System.out.println(Arrays.toString(arr));

		// 排序
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

		// 找出特定的index 地址
		arr[0] = 5;
		int index = Arrays.binarySearch(arr, 5); // -11 ???
		System.out.println(index);

		System.out.println("-------------------------------");

		// 深层显示
		String[][] ticTacToe = { { "X", "O", "O" }, { "O", "X", "X" },
				{ "X", "O", "X" } };
		System.out.println(Arrays.deepToString(ticTacToe));

		// 深层比较
		// Arrays.deepEquals(a1, a2)

	}

	@Test
	public void testToArray() {
		ArrayList<String> list = new ArrayList<String>();
/*		list.add("Liming");
		list.add("Lucy");*/

		String[] array = list.toArray(new String[0]);
		
		
		
		for (String s : array) {
			System.out.println(s);
		}
		Assert.assertTrue(true);
	}
}
