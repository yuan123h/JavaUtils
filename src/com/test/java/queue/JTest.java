package com.test.java.queue;

import java.util.PriorityQueue;
import java.util.Comparator;

import org.junit.Test;

public class JTest {
	@Test
	public void testPriorityQueue() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(20);
		
		for (int i=0; i<20; i++){
			pq.offer(i);
		}
		
		for (int i=0; i<20; i++){
			System.out.println(pq.poll());
		}
	}
	
	class MyComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer i, Integer j) {
			int result = i%2 - j%2;
			if (result == 0) {
				return i -j;
			}
			return result;
		}
		
	}
	
	@Test
	public void testPriorityQueue1() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(20, new MyComparator());
		
		for (int i=0; i<20; i++){
			pq.offer(i);
		}
		
		for (int i=0; i<20; i++){
			System.out.println(pq.poll());
		}
	}
}
