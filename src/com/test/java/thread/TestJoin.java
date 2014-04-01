package com.test.java.thread;

public class TestJoin {
	static 	class CustomThread1 extends Thread {   
		     public CustomThread1() {   
		         super("[CustomThread1] Thread");   
    };   
    public void run() {   
        String threadName = Thread.currentThread().getName();   
        System.out.println(threadName + " start.");   
        try {   
            for (int i = 0; i < 5; i++) {   
                System.out.println(threadName + " loop at " + i);   
                Thread.sleep(1000);   
            }   
            System.out.println(threadName + " end.");   
        } catch (Exception e) {   
            System.out.println("Exception from " + threadName + ".run");   
        }   
    }   
}   
	static class CustomThread extends Thread {   
    CustomThread1 t1;   
    public CustomThread(CustomThread1 t1) {   
        super("[CustomThread] Thread");   
        this.t1 = t1;   
    }   
    public void run() {   
        String threadName = Thread.currentThread().getName();   
        System.out.println(threadName + " start.");   
        try {   
            t1.join();   
            System.out.println(threadName + " end.");   
        } catch (Exception e) {   
            System.out.println("Exception from " + threadName + ".run");   
        }   
    }   
}   
    public static void main(String[] args) {   
        String threadName = Thread.currentThread().getName();   
        System.out.println(threadName + " start.");   
        CustomThread1 t1 = new CustomThread1();   
        CustomThread t = new CustomThread(t1);   
        try {   
            t1.start();   
            Thread.sleep(2000);   
            t.start();   
            //t.join();//在代碼2里，將此處注釋掉   
            	//这句话 有什么用呢？？
        } catch (Exception e) {   
            System.out.println("Exception from main");   
        }   
        System.out.println(threadName + " end!");   
      //线程CustomThread结束，此线程在t.join();阻塞处起动，向下继续执行的结果。
    } 
    
    
    //虽然在线程CustomThread执行了t1.join();，但这并不会影响到其他线程(这里main方法所在的线程)。
    //主线程结束，子线程 不会结束，执行完才会。
    
    
    
    
    
}
