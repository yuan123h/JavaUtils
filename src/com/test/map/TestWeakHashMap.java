package com.test.map;

import java.util.*;
import java.lang.ref.*;

public class TestWeakHashMap {

	static class Key {
		String id;

		public Key(String id) {
			this.id = id;
		}

		public String toString() {
			return id;
		}

		public int hashCode() {
			return id.hashCode();
		}

		public boolean equals(Object r) {
			return (r instanceof Key) && id.equals(((Key) r).id);
		}

		public void finalize() {
			System.out.println("Finalizing Key " + id);
		}
	}

	static class Value {
		String id;

		public Value(String id) {
			this.id = id;
		}

		public String toString() {
			return id;
		}

		public void finalize() {
			System.out.println("Finalizing Value " + id);
		}
	}

	/**
	 * 弱引用队列：不管用没有用，都会回收。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int size = 10;
		// 或者从命令行获得size的大小
		if (args.length > 0)
			size = Integer.parseInt(args[0]);

		Key[] keys = new Key[size]; // 存放键对象的强引用
		WeakHashMap<Key, Value> whm = new WeakHashMap<Key, Value>();
		for (int i = 0; i < size; i++) {
			Key k = new Key(Integer.toString(i));
			Value v = new Value(Integer.toString(i));
			if (i % 3 == 0)
				keys[i] = k; // 使Key对象持有强引用
			whm.put(k, v); // 使Key对象持有弱引用
		}
		// 催促垃圾回收器工作
		System.gc();
		System.out.println(whm.get( new Key(Integer.toString(3))));
		// 把CPU让给垃圾回收器线程
		Thread.sleep(8000);
	}
}
