package com.test.java.jvm;

import java.lang.management.ManagementFactory;

import org.junit.Test;

public class TestMain {
	@Test
	public void testMain() {
		//processID@hostName
		 String name = ManagementFactory.getRuntimeMXBean().getName();
		 System.out.println(name);
	}
}
