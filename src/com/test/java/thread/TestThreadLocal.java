package com.test.java.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestThreadLocal {

	/**
	 * ThreadLocal 的模拟
	 * 
	 * @author yuanhuan
	 * 
	 */
	public class SimpleThreadLocal {
		private Map valueMap = Collections.synchronizedMap(new HashMap());

		public void set(Object newValue) {
			valueMap.put(Thread.currentThread(), newValue);// ①键为线程对象，值为本线程的变量副本
		}

		public Object get() {
			Thread currentThread = Thread.currentThread();
			Object o = valueMap.get(currentThread);// ②返回本线程对应的变量
			if (o == null && !valueMap.containsKey(currentThread)) {// ③如果在Map中不存在，放到Map中保存起来。
				o = initialValue();
				valueMap.put(currentThread, o);
			}
			return o;
		}

		public void remove() {
			valueMap.remove(Thread.currentThread());
		}

		public Object initialValue() {
			return null;
		}
	}
	
	public static void main(String[] args) {
		SequenceNumber sequenceNumber = new SequenceNumber();
		sequenceNumber.main(null);
	}

	//3个线程是互不干扰的，各有有各自的对象副本
	//当传入对象为复杂类型时候，或许就可以实现共享了
	public static class SequenceNumber {
		// ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
		private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
			public Integer initialValue() {
				return 0;
			}
		};
		
		public void put(){
			seqNum.set(1);
		}

		// ②获取下一个序列值
		public int getNextNum() {
			seqNum.set(seqNum.get() + 1);
			return seqNum.get();
		}

		public void main(String[] args) {
			SequenceNumber sn = new SequenceNumber();
			sn.put();
			// ③ 3个线程共享sn，各自产生序列号
			TestClient t1 = new TestClient(sn);
			TestClient t2 = new TestClient(sn);
			TestClient t3 = new TestClient(sn);
			t1.start();
			t2.start();
			t3.start();
		}

		private class TestClient extends Thread {
			private SequenceNumber sn;

			public TestClient(SequenceNumber sn) {
				this.sn = sn;
			}

			public void run() {
				for (int i = 0; i < 3; i++) {// ④每个线程打出3个序列值
					System.out.println("thread["
							+ Thread.currentThread().getName() + "] sn["
							+ sn.getNextNum() + "]");
				}
			}
		}
	}

	

}
