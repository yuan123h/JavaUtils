package com.test.java.thread;

import org.apache.hadoop.hbase.util.Threads;
import org.junit.Test;

public class TestDaemon {

	@Test
	public void testMain() throws InterruptedException {
		Threads.setDaemonThreadRunning(new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.print(".");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}));

		Thread.sleep(1000 * 10);

	}
}
