package com.data.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("<usage>: filename # file to convert");
			System.exit(0);
		}

		String fileName = args[0];
		File file = new File(fileName);
		BufferedReader reader = null;
		BufferedWriter writer = null;
		FileWriter fw = null;
		fw = new FileWriter("./out" + System.currentTimeMillis());
		writer = new BufferedWriter(fw);
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			long line = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				line++;
				writer.write(line + '\t' + tempString.split("\t", 2)[1]);
				writer.newLine();
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
