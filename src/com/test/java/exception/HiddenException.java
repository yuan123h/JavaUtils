package com.test.java.exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//为了防范对原始异常的丢失，可以将所有异常保存在一个list中，在函数最后做检验，如果有则抛出异常
//这样就不会遗漏异常
public class HiddenException {
	public static void main(String[] args) {
		HiddenException hidden = new HiddenException();
		try {
			hidden.readFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readFile() throws FileNotFoundException, IOException{
		File f = new File("1.txt");
		BufferedReader reader = null;
		try{
		reader = new BufferedReader(new FileReader(f));
		}finally{
			if (reader != null)
				reader.close();
		}
	}
}
