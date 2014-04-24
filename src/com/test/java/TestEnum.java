package com.test.java;

import java.util.EnumMap;

import org.junit.Test;

public class TestEnum {
	enum Grade {
		A, B, C, D
	}

	// 枚举类型的map
	EnumMap<Grade, String> antMessage = new EnumMap<TestEnum.Grade, String>(
			Grade.class);

	@Test
	public void testCompare() {
		int result = Grade.A.compareTo(Grade.D);

		System.out.println(result);
	}

	@Test
	public void testBasic() {
		System.out.println(Grade.A);
		Grade d = Grade.valueOf("B");
		System.out.println(d);

		System.out.println(Grade.C.ordinal());

		System.out.println("-----------------------");
		Grade[] gradeValues = Grade.values();
		for (int i = 0; i < gradeValues.length; i++) {
			System.out.println(gradeValues[i]);
		}

		switch (d) {
		case A:

		case B:
			break;
		}

	}

	@Test
	public void testEnumMap() {
		this.antMessage.put(Grade.A, "a");
	}
	
	/////////////////////////////////////////////////////////////
	/*
	 * 值定的Class 主体
	 */
	enum Opcode{
		PUSH(1) {

			@Override
			public void perform() {
				
			}
			
		}
		;
		int numOperands;
		private Opcode(int numOperands) {
			this.numOperands = numOperands;
		}
		
		public abstract void perform();
	}
	
	
	
}
