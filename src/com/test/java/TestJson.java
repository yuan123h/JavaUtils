package com.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class TestJson {
	@Test
	public void testTab() throws IOException {
		File file = new File("data.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		while (tmp != null) {
			System.out.println(tmp);

			JSONObject pageJson = JSONObject.fromObject(tmp);
			JSONArray commonts = pageJson.getJSONArray("comments");

			for (int i = 0; i < commonts.size(); i++) {
				JSONObject object = (JSONObject) commonts.get(i);
				JSONObject idObject = object.getJSONObject("auction");
				String id = "TB" + idObject.getString("aucNumId");
				String comment = object.getString("content");
				long commentId = object.getLong("rateId");
				String nickname = "";
				String creationTime = "0";
				long buyTime = 0;
				JSONObject buyObject = object.getJSONObject("payTime");
				if (!buyObject.toString().equals("null")) {
					buyTime = buyObject.getLong("time");
				}
				creationTime = object.getString("date");
				JSONObject userObject = object.getJSONObject("user");
				if (!userObject.toString().equals("null")) {
					nickname = userObject.getString("nick");
				}
				System.out.println(id + '\t' + commentId + '\t' + comment
						+ '\t' + '\t' + nickname + '\t' + buyTime + '\t'
						+ creationTime + '\t' + 1);
			}
			tmp = br.readLine();
		}
	}

	@Test
	public void testNumber() throws IOException {
		File file = new File("metaq.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		while (tmp != null) {

			JSONObject json = JSONObject.fromObject(tmp);
			JSONArray josnArray = json.getJSONArray("items");
			System.out.println(josnArray.size());
			tmp = br.readLine();
		}
	}

	@Test
	public void testMap() throws IOException {
		File file = new File("promotion.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		Set oids = new HashSet<String>();
		int i = 0;
		String last = "";
		while (tmp != null) {
			String oid = tmp.split("\t", -1)[1];
			if (!last.equals(oid)) {
				i++;
				last = oid;
			}
			oids.add(oid);
			tmp = br.readLine();
		}
		System.out.println(i);
		System.out.println(oids.size());
		br.close();
		reader.close();
	}

	@Test
	public void testReplace() throws IOException {
		String s = "sdfsfs\n\n\ndfsfdsdfs	啊";
		java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(1024);
		for (byte b : s.getBytes()) {
			if (b == '\t' || b == '\n') {
				System.out.println("step");
				continue;
			}
			bb.put(b);
		}
		bb.flip();
		Charset cs = Charset.forName("UTF-8");
		String result = new String(cs.decode(bb).array());
		System.out.println(result);

		/*
		 * String ss = new String(bb.array()); System.out.println(ss);
		 */
	}

	@Test
	public void charCheck() {
		String s = "sdfsfs\n\n\ndfsfdsdfs	啊";
		List result = new ArrayList<CharSequence>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\t' || s.charAt(i) == '\n') {
				continue;
			}

		}
	}

	@Test
	public void replaceBlank() {
		String str = "sdfsfs\n\n\ndfsfdsdfs	啊";
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		System.out.println(dest);
	}
	
	@Test
	public void testJsonType() throws IOException {
		File file = new File("data/json.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		while (tmp != null) {
			JSONObject json = JSONObject.fromObject(tmp);
			// LOG.info("Tuples number in a next:  " + josnArray.size());
			String newGoodsOId = json.getString("newGoodsOId");
			int website = json.getInt("website");
			int sales = json.getInt("salesAmount");
			long date = json.getLong("createTime");
			System.out.println(newGoodsOId + website + sales + date);
			tmp = br.readLine();
		}
		br.close();
		reader.close();
	}

}
