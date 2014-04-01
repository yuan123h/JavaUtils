package com.test.java.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//BlockingQueue	阻塞队列	线程容器
//


public class QueueTest {
	public static void main(String[] args) {
		BlockingQueue q = new ArrayBlockingQueue(20);
		Producer p = new Producer(q);
		Consumer c1 = new Consumer(q);
		Consumer c2 = new Consumer(q);
		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}

	static class Producer implements Runnable {
		private final BlockingQueue queue;

		Producer(BlockingQueue q) {
			queue = q;
		}

		public void run() {
			try {
				int i = 5;
				while (i > 0) {
					queue.put(produce());
					i--;
				}
			} catch (InterruptedException ex) {
			}
		}

		Object produce() {
			System.out.println("produ");
			return "produ";
		}
	}

	static class Consumer implements Runnable {
		private final BlockingQueue queue;

		Consumer(BlockingQueue q) {
			queue = q;
		}

		public void run() {
			try {
				while (true) {
					consume(queue.take());
				}
			} catch (InterruptedException ex) {
			}
		}

		void consume(Object x) {
			System.out.println("consu : " + x);
		}
	}

}
