package com.test.java;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class TestSet {

	@Test
	public void testSortedSet() {
		SortedSet<String> allSet = new TreeSet<String>();
		allSet.add("A");
		allSet.add("B");
		allSet.add("B");
		allSet.add("B");
		allSet.add("C");
		allSet.add("D");
		allSet.add("E");
		System.out.println("第一个元素：" + allSet.first());
		System.out.println("最后一个元素：" + allSet.last());
		System.out.println("headSet元素：" + allSet.headSet("C"));// 返回从第一个元素到指定元素的集合
		System.out.println("tailSet元素：" + allSet.tailSet("C")); // 返回从指定元素到最后
		System.out.println("subSet元素：" + allSet.subSet("B", "D"));// 指定区间元素
	}
	
	@Test
	public void testSortedSet1() {
		SortedSet<Integer> allSet = new TreeSet<Integer>(new MyComparator());
		allSet.add(50);
		allSet.add(40);
		allSet.add(60);
		
		System.out.println(allSet);
	}
	
	static class MyComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}


		
	}
	
	
	
}
