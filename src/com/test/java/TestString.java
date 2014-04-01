package com.test.java;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.hbase.util.Strings;
import org.junit.Test;

public class TestString {
	@Test
	public void isNumeric() {
		String str = "9";
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				System.out.println(false);
		}
		System.out.println(true);
		int m = Integer.parseInt(str);
		System.out.println(m);
	}

	@Test
	public void testList() {
		List<Integer> list = new LinkedList<Integer>();
		list.add(8);
		list.add(10);
		list.add(10);
		list.add(2);

		System.out.println(list.get(3));
		for (int i : list) {
			System.out.println(i);
		}
		/*
		 * Collections.sort(list); System.out.println(list); HashSet h = new
		 * HashSet(list); list.clear(); list.addAll(h);
		 * System.out.println(list);
		 */
	}

	@Test
	public void testSort() {
		List<String> list = new ArrayList<String>();
		list.add("1376556504996_2661_699.0_5_");
		list.add("1376453167377_2651_699.0_5_");
		list.add("1376548036045_2661_699.0_5_");
		list.add("1376627804862_2670_699.0_5_");
		list.add("1376678520218_2672_699.0_5_");
		list.add("1376453167189_2651_699.0_5_");
		list.add("1376675400826_2672_699.0_5_");
		list.add("1376449463772_2651_699.0_5_");
		list.add("1376635379444_2670_699.0_5_");
		Collections.sort(list);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void test() {
		System.out.println(System.getProperty("user.dir"));
	}

	@Test
	public void testSplit() {
		String test = "http://item.taobao.com/item.htm?id=10000172238	http://img01.taobaocdn.com/bao/uploaded/i5/T1T3hHXeVMXXXHfGrc_095712.jpg	NGK白金 铂金火花塞 东南富利卡2.02.4 菱帅1.6 菱利1.6 蓝瑟1.6	40.0	0	40.0	NULL	TB10000172238	1374347223242	1	NULL	NULL	NULL	2013-07-21";
		String test2 = "	运动,运动服,夹克	1374685598114	29	http://www.yougou.com/c-lining/sku-aqcg029-99851986.html#ref=list&po=list	LI-NING 李宁 	其他服装 	艳红色/艳红色AQCG029	449.0	http://i2.ygimg.cn/pics/lining/2012/99851986/99851986_01_mb.jpg	99851986	29$99851986	\\N	 	 	 	 	 	http://www.yougou.com/s-1LAI2LAV3LBQ1.html	夹克新款正品折扣|价格-优购网时尚商城";
		System.out.println(test2.split("\t", -1).length);
		for (String s : test2.split("\t", -1)) {
			System.out.println(s);
		}
	}

	@Test
	public void testShort() {
		System.out.println(Integer.MAX_VALUE);
	}

	@Test
	public void testSplit1() {
		String test = "http://item.taobao.com/item.htm?id=10000172238	http://img01.taobaocdn.com/bao/uploaded/i5/T1T3hHXeVMXXXHfGrc_095712.jpg	NGK白金 铂金火花塞 东南富利卡2.02.4 菱帅1.6 菱利1.6 蓝瑟1.6	40.0	0	40.0	NULL	TB10000172238	1374347223242	1	NULL	NULL	NULL	2013-07-21";

		System.out.println(test.split("\t", 2)[1]);
	}

	@Test
	public void testPrint() {
		String s = "\\N";
		String m = "naem";
		System.out.println(m.split("\t", 5).length);

	}

	@Test
	public void testString() {
		String key = "13749867-1512-1022008-787608239-1-2013-08-15";
		System.out.println(key.substring(0,
				key.length() - "2013-00-00".length() - 1));

	}

	@Test
	public void testUUID() {
		String key = "40f00209-cbbb-4d61-b040-238b3f7760d0";
		UUID uu = UUID.fromString(key);
	}

	@Test
	public void testUniq() {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("aaa");
		list.add("ccc");
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		System.out.println(list);
	}

	@Test
	public void testSQL() {
		for (int i = 0; i < 8; i++) {
			String sql = "Create table goodsAdapter_1_"
					+ i
					+ " (goodsoid varchar(255) comment '商品标识', website int(11) COMMENT '电商', data_state int(11) COMMENT '数据状态', cat_name varchar(255) comment '类目名称', name varchar(255) comment '商品名称',url varchar(512) comment '商品url',primary key(goodsoid));";
			String sql1 = "Create table goodsAdapter_2_"
					+ i
					+ " (goodsoid varchar(255) comment '商品标识',cat_id int(11) COMMENT '类目ID',user_id int(11) COMMENT '用户ID', website int(11) COMMENT '电商',data_state int(11) COMMENT '数据状态',cat_name varchar(255) comment '类目名称',brand_name varchar(255) comment '品牌名称',type_name varchar(255) comment '型号名称', name varchar(255) comment '商品名称',url varchar(512) comment '商品url',primary key(goodsoid));";

			String sql2 = "Create table goodsAdapter_3_"
					+ i
					+ " (goodsoid varchar(255) comment '商品标识',cat_id int(11) COMMENT '类目ID',brand_id int(11) COMMENT '品牌ID',type_id int(11) COMMENT '型号ID',user_id int(11) COMMENT '用户ID', website int(11) COMMENT '电商',data_state int(11) COMMENT '数据状态',cat_name varchar(255) comment '类目名称',brand_name varchar(255) comment '品牌名称',type_name varchar(255) comment '型号名称', name varchar(255) comment '商品名称',url varchar(512) comment '商品url',primary key(goodsoid));";

			System.out.println(sql2);
		}
	}
	
	@Test
	public void testHost() throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost());
	}
	
	@Test
	public void testContains() {
		System.out.println("31953907-1_ejpg".contains("."));
	}
	
	@Test
	public void testNull(){
		String s = null;
		if (s==null)
			System.out.println("true");
	}
	
	/**
	 * return compressionRatio=0.7500, compressionRatio1=0.7500
	 */
	@Test
	public void testStringBuilder() {
		StringBuilder sb = new StringBuilder();
        sb = Strings.appendKeyValue(sb, "compressionRatio",
                String.format("%.4f", (float)15/
                    (float)20));
        sb = Strings.appendKeyValue(sb, "compressionRatio1",
                String.format("%.4f", (float)15/
                    (float)20));
        System.out.println(sb.toString());
	}
}
