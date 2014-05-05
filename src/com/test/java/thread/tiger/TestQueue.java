package com.test.java.thread.tiger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestQueue {
	
	/**
	 * 并发队列的使用
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue queue = new LinkedBlockingDeque(10);
		Producer producer = new Producer(queue, System.out);
		Consumer c1 = new Consumer("Consumer1", queue, System.out);
		Consumer c2 = new Consumer("Consumer2", queue, System.out);
		Consumer c3 = new Consumer("Consumer3", queue, System.out);
		Consumer c4 = new Consumer("Consumer4", queue, System.out);
		producer.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		while(true){
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
