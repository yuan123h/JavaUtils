package com.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.RegionSplitter;

public class ConnHBase {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("<usage>: 2013 9  # year month");
			return;
		}
		int year = Integer.parseInt(args[0]);
		int month = Integer.parseInt(args[1]);
		TableInfo info = new TableInfo("tradelog", year, month);
		try {
			new ConnHBase().create(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main1(String[] args) {
		try {
			new ConnHBase().testConnect1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main2(String[] args) throws IOException {
		ConnHBase.updateSchema("tradelog_2013_07");
	}

	public static void testConnect() throws IOException {
		Configuration HBASE_CONFIG = HBaseConfiguration.create();
		HBASE_CONFIG.set("hbase.zookeeper.quorum",
				"10.200.17.55,10.160.13.90,10.160.13.96");
		HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
		HBaseAdmin admin = new HBaseAdmin(HBASE_CONFIG);
		for (HTableDescriptor des : admin.listTables()) {
			System.out.println(des.getNameAsString());
		}

	}

	public static void testConnect1() throws IOException {
		Configuration HBASE_CONFIG = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(HBASE_CONFIG);
		System.out.println(HBASE_CONFIG.get("hbase.zookeeper.quorum"));
		for (HTableDescriptor des : admin.listTables()) {
			System.out.println(des.getNameAsString());
		}

	}

	public boolean create(TableInfo tableInfo) throws IOException {
		Configuration HBASE_CONFIG = HBaseConfiguration.create();
		HBASE_CONFIG.set("hbase.zookeeper.quorum", "10.124.44.4");
		// "10.200.17.55,10.160.13.90,10.160.13.96");
		HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
		HBaseAdmin hBaseAdmin = new HBaseAdmin(HBASE_CONFIG);

		if (hBaseAdmin.tableExists(tableInfo.getTableName())) {
			System.out.println(tableInfo.getTableName() + " is exist!");
			return false;
		}
		HTableDescriptor tableDescriptor = new HTableDescriptor(
				tableInfo.getTableName());
		for (int i = 1; i <= tableInfo.getDayNum(); i++) {
			tableDescriptor.addFamily(new HColumnDescriptor(String.valueOf(i)));
		}

		// hBaseAdmin.createTable(tableDescriptor);
		hBaseAdmin.createTable(tableDescriptor, getSplits());
		return true;
	}

	/**
	 * 创建 销量缓存表
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean createSalesCacheTable() throws IOException {
		Configuration HBASE_CONFIG = HBaseConfiguration.create();
		HBASE_CONFIG.set("hbase.zookeeper.quorum",
				"hbs2,hbs4,hbs6,hbs8,hbs10,hbs12,hbs14,hbs16");
		// "10.200.17.55,10.160.13.90,10.160.13.96");
		HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
		HBaseAdmin hBaseAdmin = new HBaseAdmin(HBASE_CONFIG);

		if (hBaseAdmin.tableExists("Sales30")) {
			System.out.println("Sales30" + " is exist!");
			return false;
		}
		HTableDescriptor tableDescriptor = new HTableDescriptor("Sales30");
		tableDescriptor.addFamily(new HColumnDescriptor("info")
				.setMaxVersions(31));

		// hBaseAdmin.createTable(tableDescriptor);
		hBaseAdmin.createTable(tableDescriptor, getSplits());
		return true;
	}

	private static void updateSchema(String tableName) throws IOException {
		Configuration config = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(config);
		admin.disableTable(tableName);
		HTableDescriptor discriptor = admin.getTableDescriptor(Bytes
				.toBytes(tableName));
		// 添加一个column family
		discriptor.addFamily(new HColumnDescriptor("31"));
		discriptor.removeFamily(Bytes.toBytes("0"));
		admin.modifyTable(Bytes.toBytes(tableName), discriptor);
		admin.enableTable(tableName);
	}

	byte[][] getSplits() throws IOException {
		byte[][] keys = new byte[18][];
		File file = new File("sample.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		int i = 0;
		while (tmp != null) {
			keys[i++] = Bytes.toBytes(tmp);
			tmp = br.readLine();
		}
		return keys;
	}

	/**
	 * JD1032700928 TB12846963054 TB14530059129 TB15834967833 TB16853599870
	 * TB17719125936 TB18519522195 TB19188792628 TB19735895441 TB20196214828
	 * TB21404947494 TB22473980937 TB24895404518 TB26904196972 TB5546348676
	 * TB9847332436 TM19332743442 http://img38
	 * #.ddimg.cn/31/21/1137245008-1_e.jpg
	 */
}
