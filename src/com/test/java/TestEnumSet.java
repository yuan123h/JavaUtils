package com.test.java;

import java.util.EnumSet;

import org.junit.Test;

public class TestEnumSet {
	public enum GuitarFeatures implements Features{
		ROSEWOOD(0), CEDAR(3), SPRUCE(5);
		
		private float upCharge;
		GuitarFeatures(float upCharge) {
			this.upCharge = upCharge;
		}
		
		public float getUpCharge() {
			return this.upCharge;
		}
	}
	
	interface Features{
		public float getUpCharge();
		//public float getDescription();
	}

	EnumSet<GuitarFeatures> backSides = EnumSet.of(GuitarFeatures.CEDAR,
			GuitarFeatures.SPRUCE);
	
	@Test
	public void testBasic() {
		System.out.println(GuitarFeatures.CEDAR.getUpCharge());
	}
	
	
}
