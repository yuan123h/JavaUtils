package com.test.java.lock;

public class ArrayWithLockOrder {
	private static long num_locks = 0;
	private long lock_order;
	private int[] arr;
	
	public ArrayWithLockOrder(int[] a) {
		arr = a;
		synchronized (ArrayWithLockOrder.class) {
			num_locks ++;
			lock_order = num_locks;
		}
	}
	
	public long lockOrder() {
		return lock_order;
	}
	
	public int[] array() {
		return arr;
	}
	
	
}
