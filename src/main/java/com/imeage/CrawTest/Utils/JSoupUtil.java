package com.imeage.CrawTest.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupUtil {

	public static void parse(String html){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(".select-box");
		for (Element element : elements) {
			if (!"0".equals(element.val())) {
				System.out.println(element.text()+"\r\n");
			}
		}
	}
}
