package com.test.java;

public class TestClass {

	/**
	 * 抽象类是使用
	 * @author yuanhuan
	 *
	 */
	abstract class TeamScores {
		public abstract int storeTeamScore( int score);
		
		public void process() {
			//this is my process 
		}
	}
	
	/**
	 * 不可变类 </br>
	 * 如果传入的为复杂对象，要克隆一份
	 * immutable classes
	 * 
	 */
	final class PinNumbers {
		private String owner;
		
		public String getOwner() {
			return owner;
		}

		PinNumbers (String owner) {
			this.owner = owner;
		}
		
	}
}
