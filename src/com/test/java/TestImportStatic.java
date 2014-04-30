package com.test.java;
import static java.lang.System.out;
import static java.util.Arrays.sort;

import static java.lang.Character.Subset;

import static java.lang.Math.*;
import static com.test.java.TestEnum.Grade.*;

import java.util.Arrays;

import org.junit.Test;

public class TestImportStatic {
	
	@Test
	public void testBasic() {
		out.println("");
		
		String[] s = {"ba", "b"};
		sort(s);
		out.print(Arrays.toString(s));
		
		
	}
	
	
}
