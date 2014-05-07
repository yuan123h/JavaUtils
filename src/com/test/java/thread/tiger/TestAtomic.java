package com.test.java.thread.tiger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类型的使用
 * @author yuanhuan
 *
 */
public class TestAtomic {
	
	//依靠locking 来防止并发的访问
	int count1 = 0;
	public synchronized void add() {
		count1++;
	}
	
	//依靠atomic operation 来防止并发存取
	AtomicInteger count2 = new AtomicInteger(0);
	public int count2() {
		return count2.getAndIncrement();
	}
	
	//乐观锁定， 对比值以减少执行成本
	// 且只有在有需要时才做更正的动作
	AtomicInteger count3 = new AtomicInteger(0);
	public int count3() {
		int result;
		do {
			result = count3.get();
		}while (!count3.compareAndSet(result, result + 1));
		
		return result; 
	}
	
	
}
