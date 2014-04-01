package com.test.java;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

//在实际的情况中可能会需要有的线程是定期执行的,有的线程是只执行一次,如果通过Timer去触发,会涉及线程的切换以
//及线程安全问题,这对这种情况从jdk1.5开始添加了ScheduledThreadPoolExecutor 组件,这种组件主要就是
//用来解决前面出现的问题,通过这个组件即可以定期的去执行一个任务,也可以只执行一次,也可以把这两种情况一起使用

/**
 * timer 线程的使用。
 * 
 *  自JDK5之后，可以用ScheduledThreadPoolExecutor来替代Timer。
 * 
 * @author yuanhuan
 *
 */
public class TestTimer {
	
	private int period = 5;
	
	@Test
	public void testMain(){
		Timer timer = null;
		 if (timer == null) {
		      timer = new Timer("Timer thread for monitoring " + "my_timer_name", 
		                        true);
		      TimerTask task = new TimerTask() {
		          public void run() {
		            try {
		              timerEvent();
		            }
		            catch (IOException ioe) {
		              ioe.printStackTrace();
		            }
		          }

		        };
		      long millis = period * 1000;
		      timer.scheduleAtFixedRate(task, millis, millis);
		    }
		 try {
			Thread.sleep(100*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Timer callback.
	 */
	private void timerEvent() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("monitoring...");
	}
	
	
	  private synchronized void stopTimer(Timer timer) {
		    if (timer != null) {
		      timer.cancel();
		      timer = null;
		    }
		  }
}
