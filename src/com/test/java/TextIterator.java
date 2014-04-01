package com.test.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class TextIterator {
	@Test
	public void test() {
		List<Text> list =new ArrayList<Text>();
		list.add(new Text("aaa"));
		list.add(new Text("bbb"));
		list.add(new Text("ccc"));
		list.add(new Text("ddd"));
		
		Iterator<Text> iter = list.iterator();
		System.out.println(iter.next());
		if (iter.hasNext()) {
			Text t = iter.next();
			//iter.remove();
			System.out.println(t);
		}
		System.out.println(iter.next());
	}
	
	@Test
	public void testCopy(){
		int m[] = {4,5,6,7};
		int s[] = new int[4];
		System.arraycopy(m, 0, s, 0, m.length);
		System.out.println(Arrays.toString(s));
	}
}
