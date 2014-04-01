package com.test.java;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.derby.iapi.services.io.ArrayUtil;
import org.junit.Test;

public class TestList {

	@Test
	public void testList(){
		List<String> list = new LinkedList<String>();
		list.add("hello");
		list.set(0, "nihao");
		
		System.out.println(list.size());
		System.out.println(list.toString());
		
	}
	
	@Test
	public void testOrder() {
		List<String> list = new LinkedList<String>();
		list.add("hello5");
		list.add("hello1");
		list.add("hello3");
		for (int i=0; i<3; i++) {
			System.out.println(list.get(i));
		}
		
		
	}
}
 