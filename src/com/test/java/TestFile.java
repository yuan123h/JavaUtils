package com.test.java;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestFile {
	@Test
	public void testWrite() throws IOException {
		File data = new File("./data.txt");
		List<String> list = new ArrayList<String>();
		list.add("test" + '\r');
		list.add("dfsdfsf" + '\r');
		FileWriter writer = new FileWriter(data);
		for (String s : list) {
			writer.write(s );
		}
		writer.flush();
	}
	
	/**
	 * linux 写文件 \n
	 * @throws IOException
	 */
	@Test
	public void testRead() throws IOException {
		File data = new File("./data.txt");
		List<String> list = new ArrayList<String>();
		String fileName = data.getName();
		fileName = fileName.substring(0,fileName.indexOf('.'));
		System.out.println(fileName);
		
		DataInputStream dis = new DataInputStream(new FileInputStream(data));
		dis.readInt();
		list.add("test" + '\r');
		list.add("dfsdfsf" + '\r');
		FileWriter writer = new FileWriter(data);
		for (String s : list) {
			writer.write(s );
		}
		writer.flush();
	}
}
