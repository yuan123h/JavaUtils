package com.test.java.thread.tiger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkList<E> {
	// node的值
	E value;
	// 其余的List
	LinkList<E> rest;
	// node 的lock
	Lock lock;

	// node 值被改变的标记
	Condition valueChanged;

	// node 连接被改变的 标志
	Condition linkChanged;

	public LinkList(E value) {
		this.value = value;
		rest = null;
		lock = new ReentrantLock();
		valueChanged = lock.newCondition();
		linkChanged = lock.newCondition();
	}

	public void setValue(E value) {
		lock.lock();
		try {
			this.value = value;
			valueChanged.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public void executeOnValue(E desiredValue, Runnable task)
			throws InterruptedException {
		lock.lock();
		try {
			while (!value.equals(desiredValue)) {
				valueChanged.await();
			}
			task.run();
		} finally {
			lock.unlock();
		}

	}
	
	public void append(E value) {
		//从这个node 开始指下去
		LinkList<E> node = this;
		node.lock.lock();
		
		while(node.rest != null) {
			LinkList<E> next = node.rest;
			
			//这里就是hand over hand locking、
			try{
			next.lock.lock();
			} finally {
				node.lock.unlock();
			}
			
			//穿越到下一个
			node = next;
		}
		
		// we're at the final node, so append and then unlock
		try{
			node.rest = new LinkList<E>(value);
			//告知所有在等待的thread 这个node 的link 改变了
			node.linkChanged.signalAll();
		}finally {
			node.lock.unlock();
		}
	}
	
	public void printUnitInterpruted(String prefix) {
		//start the pointer at this node
		LinkList<E> node = this;
		node.lock.lock();
		
		while(true) {
			LinkList<E> next;
			try{
				System.out.println(prefix + ":" + node.value);
				
				while(node.rest == null) {
					node.linkChanged.await();
				}
				
				//取下个node
				next = node.rest;
				
				//另一个hand-to-hand locking 
				next.lock.lock();
			}catch (InterruptedException e){
				//重置中断状态
				Thread.currentThread().interrupt();
				return;
			}finally{
				node.lock.lock();
			}
			
			//穿越到下一个
			node = next;
		}
		
		
		
	}

}
