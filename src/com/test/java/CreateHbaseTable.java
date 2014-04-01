package com.test.java;

import java.io.IOException;

public class CreateHbaseTable {
	public static void main(String[] args) throws IOException {
		new ConnHBase().createSalesCacheTable();
	}
}
