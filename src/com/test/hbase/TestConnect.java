package com.test.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class TestConnect {
	public static void main(String[] args) throws IOException {
		TestConnect.testConnect1();
	}

	public static void testConnect1() throws IOException {
		Configuration HBASE_CONFIG = HBaseConfiguration.create();
		HBASE_CONFIG.set("hbase.zookeeper.quorum",
				"10.124.44.4,10.124.44.3");
		HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
		HBaseAdmin admin = new HBaseAdmin(HBASE_CONFIG);
		System.out.println(HBASE_CONFIG.get("hbase.zookeeper.quorum"));
		for (HTableDescriptor des : admin.listTables()) {
			System.out.println(des.getNameAsString());
		}

	}
}
