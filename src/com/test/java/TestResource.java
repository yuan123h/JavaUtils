package com.test.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestResource {
	public static void main(String[] args) throws IOException {

		InputStream is = TestResource.class
				.getResourceAsStream("/data/json.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String tmp = br.readLine();
		while (null != tmp) {
			System.out.println(tmp);
			tmp = br.readLine();
		}
		br.close();
		is.close();
	}
}
