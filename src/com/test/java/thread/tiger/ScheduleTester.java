package com.test.java.thread.tiger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 调度程序的 实现
 * @author yuanhuan
 *
 */
public class ScheduleTester {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors
				.newSingleThreadScheduledExecutor();

		final ScheduledFuture<?> timeHandle = scheduler.scheduleAtFixedRate(
				new TimePrinter(System.out), 0, 10, TimeUnit.SECONDS);

		scheduler.schedule(new Runnable() {

			@Override
			public void run() {
				timeHandle.cancel(false);
			}
		}, 60 * 60, TimeUnit.SECONDS);
	}
}
