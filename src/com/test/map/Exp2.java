package com.test.map;

import java.util.*;

/**
 * hashmap 存储对象时，对象应该重写 equals，hashCode方法
 * <p>
 * <ul>
 * <li>不必对每个不同的对象都产生一个唯一的hashcode，只要你的HashCode方法使get()能够得到
 * put()放进去的内容就可以了。即"不为一原则"。<br/>
 * </li>
 * <li>
 * 生成hashcode的算法尽量使hashcode的值分散一些，不要很多hashcode都集中在一个范围内，这样
 * 有利于提高HashMap的性能。即 * "分散原则"。</li>
 * </ul>
 * 
 * @author yuanhuan
 * 
 */
public class Exp2 {
	public static void main(String[] args) {
		HashMap h2 = new HashMap();
		for (int i = 0; i < 10; i++)
			h2.put(new ElementE(i), new Figureout());
		System.out.println("h2:");
		System.out.println("Get the result for Element:");
		ElementE test = new ElementE(5);
		if (h2.containsKey(test))
			System.out.println((Figureout) h2.get(test));
		else
			System.out.println("Not found");
	}
}

class Element {
	int number;

	public Element(int n) {
		number = n;
	}
}

/**
 * 重写了hashCode equals 方法
 * 
 * @author yuanhuan
 * 
 */
class ElementE {
	int number;

	public ElementE(int n) {
		number = n;
	}

	public int hashCode() {
		return number;
	}

	public boolean equals(Object o) {
		return (o instanceof ElementE) && (number == ((ElementE) o).number);
	}
}

class Figureout {
	Random r = new Random();
	boolean possible = r.nextDouble() > 0.5;

	public String toString() {
		if (possible)
			return "OK!";
		else
			return "Impossible!";
	}
}