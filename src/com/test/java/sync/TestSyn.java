package com.test.java.sync;

public class TestSyn {
	
	
	//静态方法与非静态方法的 同步控制
	
	//M1 M2 为线程安全的
	static class Foo implements Runnable {
		private byte[] lock = new byte[0];

		@Override
		public void run() {
			//synchronized (lock) {
				//
		}
		
	
		void printM1(){
			synchronized (lock) {
				//
			}
		}
		
		public static void printM2(Foo f){
			synchronized (f.lock) {
				//
			}
		}
		
	}
}
