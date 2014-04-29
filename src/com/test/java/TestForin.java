package com.test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

public class TestForin {
	public class GuitarManufactureList extends LinkedList<String> {
		public GuitarManufactureList() {
			super();
		}

		public boolean add(String manufacture) {
			if (manufacture.indexOf("Guitars") == -1) {
				return false;
			} else {
				super.add(manufacture);
				return true;
			}
		}
	}

	///////////////////////////
	@Test
	public void testTextRead() {
		String fileName = "data.txt";
		for (String s : new TextFile(fileName)) {
			System.out.println(s);
		}
	}
	
	
	
	public class TextFile implements Iterable<String> {

		final String fileName;

		public TextFile(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public Iterator<String> iterator() {
			return new TextFileIterator();
		}

		class TextFileIterator implements Iterator<String> {
			BufferedReader in;
			String nextLine;

			public TextFileIterator() {
				try {
					in = new BufferedReader(new FileReader(fileName));
					nextLine = in.readLine();

				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}

			@Override
			public boolean hasNext() {
				return this.nextLine != null;
			}

			@Override
			public String next() {
				try {
					String result = nextLine;
					if (nextLine != null) {
						nextLine = in.readLine();
						if (nextLine == null)
							in.close();
					}
					//返回上次独到的 行
					return result;
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}

			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		}

	}
}
