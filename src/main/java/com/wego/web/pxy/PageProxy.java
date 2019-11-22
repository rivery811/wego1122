package com.wego.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("pageproxy")
@Lazy
@Data
public class PageProxy extends Proxy {
	@Autowired CrawlingProxy crawler;
	private int totalCount,startrow,endrow,
				pageCount,pagesize,startpage,endpage,nowpage, 
				bolckCount,blocksize,nowblock,prevblock,nextblock;
	private boolean existprev, existnext;
	

	
	
	public void paging() {
		pageCount = totalCount%pagesize==0?totalCount/pagesize:totalCount/pagesize+1;
		bolckCount= pageCount%blocksize==0?pageCount/blocksize:pageCount/blocksize+1;
		startrow = nowpage*pagesize;
		endrow=nowpage!=(pageCount-1)?startrow+pagesize-1:totalCount-1;
		nowblock = nowpage/blocksize;
		startpage = nowblock*blocksize;
		endpage= nowblock!=(bolckCount-1)?startpage+blocksize-1:pageCount-1;
		prevblock= startpage-blocksize;
		nextblock=startpage+blocksize;
		existprev= nowblock!=0;
		existnext= nowblock!=(bolckCount-1);
		
		pageCount = totalCount%pagesize==0?totalCount/pagesize:totalCount/pagesize+1;

	}
	

}
