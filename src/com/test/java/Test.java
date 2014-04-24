package com.test.java;

public class Test {
	class T1 {
		static final int a = 5;
		static final int b = 6;
		int c = a + b;		
	}

	class T2 {
		final int a = 5;
		final int b = 6;
		int c = a + b;
	}
	
	@org.junit.Test
	public void testBasic() {
		Integer i = 2;
		System.out.println(i);
		i++;
		
		System.out.println(i);
		
		Integer j = 2;
		System.out.println(i == j);
			//有些jvm 下可能是相等的
		
		Integer i1 = 100;
		Integer i2 = 100;
		System.out.println(i1 == i2);
			// true 
	}
	
}
