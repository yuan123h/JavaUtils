package com.test.java.lock;

//以相同的顺序锁定，防止 产生死锁
public class SomeClass implements Runnable{

	public int sumArrays(ArrayWithLockOrder a1, ArrayWithLockOrder a2) {
		int value = 0;
		ArrayWithLockOrder first = a1;
		ArrayWithLockOrder last = a2;
		int size = a1.array().length;
		if (size == a2.array().length) {
			if (a1.lockOrder() > a2.lockOrder()){
				first = a2; 
				last = a1;
			}
			synchronized (first) {
				int[] arr1 = a1.array();
				int[] arr2 = a2.array();
				for (int i = 0; i< size; i++){
					value += arr1[i] + arr2[i];
				}
					
			}
		}
		return value;
	}
	
	@Override
	public void run() {
		
	}

}
