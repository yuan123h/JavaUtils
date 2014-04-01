package com.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Sample {
	static final int size = 82145730;

	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.out.println("<usage> : <file> <number>");
			System.exit(1);
		}

		File file = new File(args[0]);
		int num = Integer.parseInt(args[1]);
		int step = size / (num - 1);
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		int tag = 0;
		while (tmp != null) {
			tag++;
			if (tag == step) {
				System.out.println(tmp);
				tag = 0;
			}
			tmp = br.readLine();
		}
	}
}
