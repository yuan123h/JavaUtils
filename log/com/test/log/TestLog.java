package com.test.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TestLog {
	private static final Log log = LogFactory.getLog(TestLog.class);
	public static void main(String[] args) {
		log.info("hello");
		File f = new File("a");
		try {
			InputStream is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			log.error("",e);
		}
	}
}
