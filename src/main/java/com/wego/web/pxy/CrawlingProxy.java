package com.wego.web.pxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("crawler")
@Lazy
public class CrawlingProxy extends Proxy {
	@Autowired Inventory<HashMap<String,String>> inventory;
	@Autowired Box<String> box;
	
	public ArrayList<HashMap<String,String>> engCrawling(String url) {
		url = "https://endic.naver.com/?sLn=kr";
		inventory.clear();
		try {
			Document rawData = Jsoup.connect(url).timeout(10 * 1000).get();
			Elements origin = rawData.select("div[class=txt_origin] a");
			Elements trans = rawData.select("div[class=txt_trans]");
			HashMap<String,String> map = null;
			for (int i = 0 ; i<origin.size();i++) {
				map = new HashMap<>();
				System.out.println("����"+origin.get(i).text());
				map.put("origin", origin.get(i).text());
				map.put("trans", trans.get(i).text());
				inventory.add(map);
			}
		
			System.out.println("박스 " + inventory.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---------");
		//System.out.println(inventory.toString());  ����Ʈ���� �ּҰ��� ���� 
		inventory.get().forEach(System.out::println);
		return inventory.get();
	}
	
	public ArrayList<HashMap<String,String>> cgvCrawl() {
		//cgvseq,title.content,img
		HashMap<String,String> map = null;
		inventory.clear();


		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String bugs = "http://www.cgv.co.kr/movies/?lt=3" ;
			Connection.Response homePage;
			homePage = Jsoup.connect(bugs)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = homePage.parse();
			Elements element = temp.select("div.sect-movie-chart");
			Elements tempforTitle = element.select("strong.title");
			Elements tempforPrecent = element.select("strong.percent");
			Elements tempforTextinfo= element.select("span.txt-info");
			Elements  tempforphoto = temp.select("span.thumb-image");
			System.out.println("����");
			for(int i = 0;i<tempforTitle.size();i++ ) {
				map = new HashMap<>();
				map.put("cgvseq", string(i+1));
				map.put("title", tempforTitle.get(i).text());
				map.put("content", tempforPrecent.get(i).text()+tempforTextinfo.get(i).text());
				map.put("img", tempforphoto.get(i).select("img").attr("src"));
				inventory.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Consumer<Cgv> c = t -> hrMapper.insertCgvRank(t);		
		/*for (Element tempTitle : tempforTitle) {
			tempCgvs.setCgvseq(String.valueOf(cgvseq+1));
			tempCgvs.setTitle(tempTitle.text());
			tempCgvs.setContent(tempforPrecent.get(cgvseq).text() + "/"+tempforTextinfo.get(cgvseq).text());
			tempCgvs.setImg(tempforphoto.get(cgvseq).select("img").attr("src"));
			tempList.add(tempCgvs);
			cgvseq++;
		}*/

		System.out.println("---------");
		inventory.get().forEach(System.out::println);
		return inventory.get();
	}
	
	public ArrayList<HashMap<String,String>> bugs(String url){
		url = "https://music.bugs.co.kr/chart";
		inventory.clear();
		try {
			Document rawData = Jsoup.connect(url).get();
			Elements img = rawData.select("a[class=thumbnail]");
			Elements rank = rawData.select("div[class=ranking] strong");
			Elements artist = rawData.select("p[class=artist] a");
			Elements title = rawData.select("p[class=title] a");
			
			HashMap<String,String> map = null;
			for(int i = 0; i <title.size();i++) {
				map = new HashMap<>();
				map.put("img", img.get(i).select("img").attr("src"));
				map.put("rank", rank.get(i).text());
				map.put("artist", artist.get(i).text());
				map.put("title", title.get(i).text());
				inventory.add(map);
				
			}
			System.out.println("박스: " + inventory.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(inventory.get());
		return inventory.get();
		
	}

}
