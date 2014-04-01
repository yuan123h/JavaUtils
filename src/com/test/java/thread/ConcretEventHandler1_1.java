package com.test.java.thread;


import java.io.IOException;
 
 
public class ConcretEventHandler1_1 extends EventHandler{
	String tag;
 
    public ConcretEventHandler1_1(EventType eventType) {
        super(eventType);
    }
    
    public ConcretEventHandler1_1(EventType eventType, String tag) {
    	super(eventType);
    	this.tag =tag;
    }
 
    @Override
    public void process() throws IOException {
    	int i=0;
        while(i<5){
            //System.out.println("this is ConcretEventHandler 1.1");
//          try {
//              Thread.sleep(2000);
//          } catch (InterruptedException e) {
//              e.printStackTrace();
//          }
        	i++;
        	System.out.println(ConcretEventHandler1_1.class.getSimpleName() + tag);
        }
         
    }
     
 
}
 