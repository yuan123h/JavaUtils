package com.test.java.reflect;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.hadoop.metrics.MetricsContext;
import org.junit.Test;

/**hbase 中的martic 中的反射用的类
 * hbase.class=org.apache.hadoop.metrics.ganglia.GangliaContext31
 */

/**
 * 反射</br> web应用，应当使用 线程 classloader
 * 
 * @author yuanhuan
 * 
 */
public class TestReflect {

	/**
	 * Cache of constructors for each class. Pins the classes so they can't be
	 * garbage collected until ReflectionUtils can be collected.
	 */
	private static final Map<Class<?>, Constructor<?>> CONSTRUCTOR_CACHE = new ConcurrentHashMap<Class<?>, Constructor<?>>();
	private static final Class<?>[] EMPTY_ARRAY = new Class[] {};

	/**
	 * getContextClassLoader 与 ?? 区别
	 * 
	 * @param <T>
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public <T> void testMain() throws ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();

		Class<T> theClass = (Class<T>) Class.forName(
				"com.test.java.reflect.ToBeLoaded", true, classLoader);

		T result;
		try {
			Constructor<T> meth = (Constructor<T>) CONSTRUCTOR_CACHE
					.get(theClass);
			if (meth == null) {
				meth = theClass.getDeclaredConstructor(EMPTY_ARRAY);
				meth.setAccessible(true);
				CONSTRUCTOR_CACHE.put(theClass, meth);
			}
			System.out.println("start init:");
			result = meth.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// result;
	}

	/**
	 * 简单的反射运用
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testReflect() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		String className = null;
		if (className == null) {
			className = "com.test.java.reflect.ToBeLoaded";
		}
		Class loadClass = Class.forName(className);
		//有下面一种写法
//	    if (implClass == null)
//			implClass = java.net.PlainDatagramSocketImpl.class;
	    
		Load load = (Load) loadClass.newInstance();
		load.method1();
	}

}
