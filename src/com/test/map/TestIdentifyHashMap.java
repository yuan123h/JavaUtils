package com.test.map;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.junit.Test;

public class TestIdentifyHashMap {
	
	/**
	 * IdentifyHashMap  只判断key 是否相等决定 是否进行key的插入，可以通过修改key对象的equals 方法
	 * 实现自己的 放入判断逻辑。
	 */
	@Test
	public void testBasic() {
		 Map<Pet, String> map = new IdentityHashMap<Pet, String>();
		//Map<String, String> map = new IdentityHashMap<String, String>();
		  Pet p = new Pet("eric");
		  map.put(p, "eric");
		  map.put(p, "hello");
		  System.out.println("size = " + map.size());
		  
		  Map<Pet, String> hashMap = new HashMap<Pet, String>();
		  hashMap.put(new Pet("eric"), "eric");
		  hashMap.put(new Pet("eric"), "hello");
		  System.out.println("hashMap size = " + hashMap.size());
		//Pet中的equals中只要name相等, 就算这两个pet对象相等. 执行结果为:
		//size = 2
		//hashMap size = 1

	}
	
	class Pet{
		String name;
		public Pet(String name) {
			this.name = name;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pet p = (Pet)obj;
			return this.name == p.name;
		}
		
	}
}
