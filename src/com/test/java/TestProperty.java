package com.test.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class TestProperty {
	private Map<String, Object> attributeMap = new HashMap<String, Object>();

	/**
	 * In your case, you are loading the class from an Application Server, so
	 * your should use
	 * Thread.currentThread().getContextClassLoader().getResourceAsStream
	 * (fileName) instead of
	 * this.getClass().getClassLoader().getResourceAsStream(fileName).
	 * this.getClass().getResourceAsStream() will also work.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMain() throws IOException {
		String fileName = "my.properties";

		/**
		 * String fileName = "my.properties"; 此时放在同一个包下 String fileName =
		 * "/my.properties"; 此时在类路径下
		 */
		// InputStream is = this.getClass().getResourceAsStream(fileName);

		/**
		 * String fileName = "my.properties"; 在类路径下 String fileName =
		 * "./my.properties"; 在类路径下
		 */
		// InputStream is =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		/**
		 * String fileName = "my.properties"; 在类路径下 String fileName =
		 * "./my.properties"; 在类路径下
		 */
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(fileName);

		if (is != null) {
			try {
				Properties properties = new Properties();
				properties.load(is);
				// for (Object propertyNameObj : properties.keySet()) {
				Iterator it = properties.keySet().iterator();
				while (it.hasNext()) {
					String propertyName = (String) it.next();
					String propertyValue = properties.getProperty(propertyName);
					setAttribute(propertyName, propertyValue);
				}
			} finally {
				is.close();
			}
		}
		System.out.println(attributeMap.get("a"));
	}

	public void setAttribute(String attributeName, Object value) {
		attributeMap.put(attributeName, value);
	}

}
