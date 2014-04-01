package com.test.java.thread;

import java.util.Map;

import com.test.java.thread.ExecutorService.ExecutorStatus;
 
//自定义实现的多线程 例子


public class ExecutorServiceTest {
    static ExecutorService service = new ExecutorService("service test");
 
    static EventHandler.EventHandlerListener sleepListener = new EventHandler.EventHandlerListener() {
        public void beforeProcess(EventHandler event) {
            // try {
            // Thread.sleep(1000);
            // System.err.println(Thread.currentThread().getId() + "-pre");
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
            System.err.println(Thread.currentThread().getId() + "-pre");
        }
 
        public void afterProcess(EventHandler event) {
            // try {
            // Thread.sleep(500);
            // System.err.println(Thread.currentThread().getId() + "-post");
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
            System.err.println(Thread.currentThread().getId() + "-post");
        }
    };
 
    public static void main(String args[]) {
        EventHandler event11 = new ConcretEventHandler1_1(
                AppEventTypes.eventtype1_1, "tag1");
        event11.setListener(sleepListener);
        EventHandler event12 = new ConcretEventHandler1_1(
                AppEventTypes.eventtype1_2, "tag2");
        event12.setListener(sleepListener);
        EventHandler event21 = new ConcretEventHandler1_1(
                AppEventTypes.eventtype2_1, "tag3");
        event21.setListener(sleepListener);
        EventHandler event22 = new ConcretEventHandler1_1(
                AppEventTypes.eventtype2_2, "tag4");
        event22.setListener(sleepListener);
 
        service.registerEventToExecutor(AppEventTypes.eventtype1_1,
                AppExecutorTypes.executortype1);
        service.registerEventToExecutor(AppEventTypes.eventtype1_2,
                AppExecutorTypes.executortype1);
        service.registerEventToExecutor(AppEventTypes.eventtype2_1,
                AppExecutorTypes.executortype2);
        service.registerEventToExecutor(AppEventTypes.eventtype2_2,
                AppExecutorTypes.executortype2);
 
        System.out.println(service.getInnerExecutors());
 
        // open the pools
        service.startExecutorService(AppExecutorTypes.executortype1, 1);
        service.startExecutorService(AppExecutorTypes.executortype2, 1);
 
        // submit events to service (each pool)
        service.submit(event11);
        service.submit(event12);
        service.submit(event21);
        service.submit(event22);
 
        while (true) {
            Map<String, ExecutorStatus> status = service
                    .getAllExecutorStatuses();
            for (String k : status.keySet()) {
                System.err.println(k + " : " + status.get(k).toString());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                service.shutdown();
                e.printStackTrace();
            }
        }
 
    }
 
}