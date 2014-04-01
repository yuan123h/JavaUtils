package com.test.java;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestMap {

	/**
	 * 非覆盖插入
	 */
	@Test
	public void testConcurrentHashMap() {
		ConcurrentHashMap<String, Object> executorMap = new ConcurrentHashMap<String, Object>();
		executorMap.putIfAbsent("test", 6);
		Object o = executorMap.putIfAbsent("test", 5);
		System.out.println(o);
	}

	@Test
	public void testMapGet() {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("test", 0.5);

		if (map.get("t") == null)
			;
		System.out.println("not exit");
		/*
		 * double d = map.get("t"); System.out.println(d);
		 */
	}

	@Test
	public void testMap() {
		Map<Boolean, Double> result = new HashMap<Boolean, Double>();
		result.put(false, 0.6);
		double d = result.put(false, 0.5);
		System.out.println(d);

		if (result.keySet().contains(true))
			System.out.println(result.get(true));
	}

	@Test
	public void testNavigateMapRef() {
		NavigableMap nav = new TreeMap();
		nav.put("aaa", 111);
		nav.put("bbb", 222);
		nav.put("eee", 333);
		nav.put("ccc", 555);
		nav.put("ddd", 444);

		String[] s = new String[5];
		nav.keySet().toArray(s);
		System.out.println(s.length);

		NavigableMap nav1 = nav;
		nav = new TreeMap();
		System.out.println(nav1.size());
	}

	@Test
	public void testNavigateMap() {
		NavigableMap nav = new TreeMap();
		nav.put("aaa", 111);
		nav.put("bbb", 222);
		nav.put("eee", 333);
		nav.put("ccc", 555);
		nav.put("ddd", 444);
		System.out.printf("Whole list:%s%n", nav);

		System.out.printf("First key: %s/tFirst entry: %s%n", nav.firstKey(),
				nav.firstEntry());

		System.out.printf("Last key: %s/tLast entry: %s%n", nav.lastKey(),
				nav.lastEntry());

		System.out.printf("Key floor before bbb: %s%n", nav.floorKey("bbb"));

		System.out.printf("Key lower before bbb: %s%n", nav.lowerKey("bbb"));

		System.out.printf("Key ceiling after ccc: %s%n", nav.ceilingKey("ccc"));

		System.out.printf("Key higher after ccc: %s%n", nav.higherKey("ccc"));
	}

	@Test
	public void testNavigateSet() {
		NavigableSet set = new TreeSet();
	}

	/**
	 * -Xmx64m OOM
	 */
	@Test
	public void testWeakHashMap() {
		List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();

		for (int i = 0; i < 1000; i++) {
			WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
			d.put(new byte[1000][1000], new byte[1000][1000]);
			maps.add(d);
			System.gc();
			System.err.println(i);

			for (int j = 0; j < i; j++) {
				System.err.println(j + " size" + maps.get(j).size());
			}

		}
	}

	@Test
	public void testWeakHashMap1() {
		WeakHashMap<String, String> map = new WeakHashMap<String, String>();
		map.put(new String("nihao"), "heihei");
		System.gc();
		System.out.println(map.size());
		map.get("nihao");
		System.gc();
		System.out.println(map.size());
	}

	/**
	 * WeakHashMap并不是你啥也干他就能自动释放内部不用的对象的，而是在你访问它的内容的时候释放内部不用的对象。 这两句话看似区别不大，
	 * 但是有时候一个小小的区别就会要了命的。就是说你只put 了压根没有get过，这个值是永远都存在的
	 */
	@Test
	public void testWeakHashMap2() {
		Map wMap = new WeakHashMap();
		wMap.put("1", "ding");
		wMap.put("2", "job");

		System.out.println("first get:" + wMap.get("1"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.gc();
		System.out.println("next get:" + wMap.get("1"));
	}

	@Test
	public void testWeakHashMap3() {
		String str = new String("hello"); // ①
		ReferenceQueue<String> rq = new ReferenceQueue<String>(); // ②
		WeakReference<String> wf = new WeakReference<String>(str, rq); // ③
		str = null; // ④

		// 两次催促垃圾回收器工作，提高"hello"对象被回收的可能性
		System.gc(); // ⑤
		System.gc(); // ⑥
		String str1 = wf.get(); // ⑦ 假如"hello"对象被回收，str1为null
		System.out.println(str1);
		Reference<? extends String> ref = rq.poll(); // ⑧
		System.out.println(ref);

	}
}
