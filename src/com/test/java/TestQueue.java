package com.test.java;

import static org.junit.Assert.*;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

public class TestQueue {
	@Test
	public void referenceQueue() throws InterruptedException {
		Object referent = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		WeakReference<Object> weakReference = new WeakReference<Object>(
				referent, referenceQueue);

		assertFalse(weakReference.isEnqueued());
		Reference<? extends Object> polled = referenceQueue.poll();
		assertNull(polled);

		referent = null;
		System.gc();

		assertTrue(weakReference.isEnqueued());
		Reference<? extends Object> removed = referenceQueue.remove();
		assertNotNull(removed);
	}

	@Test
	public void basicQueue() {
		Queue queue = new LinkedList();
		System.out.println("Head of queue is: " + queue.poll());
		assertNull(queue.poll());
		queue.offer("One");
		queue.offer("Two");
		queue.offer("Three");
		queue.offer("Four");
		// Head of queue should be One
		System.out.println("Head of queue is: " + queue.poll());
		assertTrue(true);
	}

	public void testBlockQueue() {
		
	}
}
