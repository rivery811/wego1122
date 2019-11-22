package com.wego.web.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wego.web.pxy.Box;
import com.wego.web.pxy.CrawlingProxy;
import com.wego.web.pxy.PageProxy;



@RestController
@RequestMapping("context")
public class CrawlerController {
	@Autowired CrawlingProxy crawler;
	@Autowired PageProxy pageproxy;
	@Autowired Box<Object> box;
	
	@GetMapping("/naver")
	public ArrayList<HashMap<String,String>> naver() {
		return crawler.engCrawling("https://endic.naver.com/?sLn=kr");

		
	}
	@GetMapping("/cgv")
	public ArrayList<HashMap<String,String>> cgv()  {
		return crawler.cgvCrawl();
		
		
	}
	@GetMapping("/bugs/{nowpage}/{size}")
	public Map<?,?> bugs(@PathVariable String nowpage,@PathVariable String size, String url) {
	
		//ArrayList<HashMap<String,String>>
		ArrayList<HashMap<String,String>> list = crawler.bugs(url);
		pageproxy.setTotalCount(list.size());
		pageproxy.setBlocksize(5);
		pageproxy.setPagesize(Integer.parseInt(size));
		pageproxy.setNowpage(Integer.parseInt(nowpage));
		pageproxy.paging();
		System.out.println("컨트롤러"+list.size());
		 ArrayList<HashMap<String,String>> temp = new ArrayList<>();
		 for(int i=0; i<list.size();i++) {
			 if(i>= pageproxy.getStartrow()&& i<= pageproxy.getEndrow()) {
				 temp .add(list.get(i));
				 
			 }
			 if(i>pageproxy.getEndrow()) {break;}
		 }
		box.put("pager", pageproxy);
		box.put("list", temp);
		System.out.println("페이저"+box.get("pager")
		.toString());
		System.out.println("리스트"+box.get("list"));

		return box.get();
	}

}
