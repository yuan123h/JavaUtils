package com.test.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestHTML {

	public static void main(String[] args) throws IOException {
		File file = new File("json.html");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		while (tmp != null) {

			String html = JSONObject.fromObject(tmp).getString("html");
			html = html.replace("\\\"", "\"").replace("\\/", "/");
			System.out.println(html);
			TestHTML.getMessage(html);
			tmp = br.readLine();
		}
	}

	public static void getMessage(String html) {
		Document doc = Jsoup.parse(html);

		int num = doc.select("tr").size() - 1;

		for (int i = 0; i < num; i++) {

			System.out.println(doc.select("span.tb-sellnick").get(i).text());
			// System.out.println(doc.select("span.tb-anonymous").get(i).text());
			System.out.println(doc.getElementsByClass("tb-rmb-num").get(i)
					.text());
			System.out.println(doc.select("td.tb-amount").get(i).text());

			System.out.println(doc.select("td.tb-time").get(i).text());

			System.out.println(doc.getElementsByClass("tb-sku").get(i).text());
			System.out.println("      -            -      ");
		}
	}

	public void main1(String[] args) throws IOException {
		File input = new File("html.txt");
		FileReader reader = new FileReader(input);
		BufferedReader br = new BufferedReader(reader);
		String tmp = br.readLine();
		while (tmp != null) {
			tmp = tmp.replace("\\\"", "\"").replace("\\/", "/");
			System.out.println(tmp);
			Document doc = Jsoup.parse(tmp);

			int num = doc.select("tr").size() - 1;

			for (int i = 0; i < num; i++) {

				System.out
						.println(doc.select("span.tb-sellnick").get(i).text());
				// System.out.println(doc.select("span.tb-anonymous").get(i).text());
				System.out.println(doc.getElementsByClass("tb-rmb-num").get(i)
						.text());
				System.out.println(doc.select("td.tb-amount").get(i).text());

				System.out.println(doc.select("td.tb-time").get(i).text());

				System.out.println(doc.getElementsByClass("tb-sku").get(i)
						.text());
				System.out.println("      -            -      ");
			}

			tmp = br.readLine();
			// Document doc = Jsoup.parse(input, "UTF-8",
			// "http://www.oschina.net/");

			// System.out.println(doc.select("td.tb-time").get(0).text());
			// System.out.println(doc.getElementsByClass("\"tb-time\"").get(0).text());

			/*
			 * Elements links = doc.select("a[href]"); // 具有 href 属性的链接 Elements
			 * pngs = doc.select("img[src$=.png]");// 所有引用 png 图片的元素
			 * 
			 * Element masthead = doc.select("div.masthead").first(); // 找出定义了
			 * class=masthead 的元素
			 * 
			 * Elements resultLinks = doc.select("h3.r > a"); // direct a after
			 * h3
			 */}
	}
}
