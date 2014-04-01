package com.test.java;

import org.junit.Test;

public class TestEquals {
	
	@Test
	public void testMain() {
		System.out.println(getClass());
	}
	
	class Base{

		@Override
		public boolean equals(Object obj) {
			
			if (this == obj) return true;
			
			if (getClass() != obj.getClass())
				return false;
			else {
				return true;
			}
		}
		
	}
}
