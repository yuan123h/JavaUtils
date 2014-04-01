package com.test.java.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务 多线程
 * @author yuanhuan
 *
 */
public class ScheduledExecutorSample {
	public static ScheduledThreadPoolExecutor se = new ScheduledThreadPoolExecutor(
			10);

	public static void main(String[] args) {

		ScheduledExecutorSample ss = new ScheduledExecutorSample();
		ss.schedule("begin");
		ss.fixedPeriodSchedule();
		ss.schedule("end");

	}

	class Schedule implements Runnable {
		private String name;

		public Schedule(String name) {
			this.name = name;
		}

		public void run() {
			System.out.println("Schedule" + name);
		}
	}

	class FixedSchedule implements Runnable {
		public void run() {
			System.out.println("Fixed period schedule");
		}
	}

	public void schedule(String name) {
		// 设定值执行一次的runnable
		se.schedule(new Schedule(name), 0, TimeUnit.SECONDS);
	}

	public void fixedPeriodSchedule() {
		// 设定可以循环执行的runnable,任务的间隔为1秒
		se.scheduleAtFixedRate(new FixedSchedule(), 0, 1, TimeUnit.SECONDS);
	}
}
