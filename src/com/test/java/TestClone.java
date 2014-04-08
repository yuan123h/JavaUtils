package com.test.java;

import java.util.ArrayList;


public class TestClone {
	
	// 浅层 克隆
	static class User implements Cloneable {
		int name;
		@Override
		protected Object clone(){
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				throw new InternalError(e.getMessage());
			}
		}
		
	}
	
	public static void main(String[] args) {
		User u = new User();
		User u1 = (User) u.clone();
		System.out.println(u1.name);
		
	}
	
	/*
	 * 深层克隆
	 */
	static class ShareSet extends ArrayList{

		@Override
		public Object clone() {
			ShareSet set = (ShareSet) super.clone();
			int size = size();
			for ( int i=0; i< size; i++) {
				User u = (User)this.get(i);
				set.add((User)u.clone());
			}
			return set;
		}
		
	}
	
	///////////////////不可变接口/////////////////////////////////////////////
	
	interface ImmutableCircle{
		public double radius();
	}
	
	static class MutableCircle implements ImmutableCircle{

		@Override
		public double radius() {
			return 0;
		}
		
	}
	
	// 返回接口，防止改动
	// 但可以通过转型来突破限制，更改对象
	ImmutableCircle test() {
		return new MutableCircle();
	}
	
	// 转型，突破限制
	void test2() {
		ImmutableCircle circle = this.test();
		MutableCircle circle2 = (MutableCircle)circle;
	}
//////////////////////////////////////////////////////////////
}
