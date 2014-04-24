package com.java.reguex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestMain {
	@Test
	public void testBasic() {
		//String rexp = "[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}";
		String rexp = "[\\d]+\\.[\\d]+\\.[\\d]+\\.[\\d]+";
		String s = "255.255.23.3";
		
		//String rexp = "[1-9]+";
		//String s = "555a";
		 Pattern p = Pattern.compile(rexp);   
	        Matcher m = p.matcher(s);   
	        //boolean result = m.matches();
	        System.out.println(m.matches());
	        
	}
}
