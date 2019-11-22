var app =app||{}
app=(()=>{
	let _,js,img;

	let run=x=>{
		$.when(
				$.getScript(x+'/resources/js/router.js',()=>{
					$.extend(new Session(x))
				}))
				$.getScript(x+'/resources/js/pop.js')
		.done(()=>{

			onCreate()
		})
		.fail(()=>{
			
		})


	}
	let init=()=>{
		_ =sessionStorage.getItem('ctx')
		js = sessionStorage.getItem('js')
		img = sessionStorage.getItem('img')		
		
	}
	let onCreate=()=>{
		init()
		$(pop.view()).appendTo('#a')
		pop.open()
		setContentView()
	}
	
	let setContentView=()=>{

	
		$('<table id = "tab"><tr></tr></table>')
		.css({
			width :'80%',
			height :'800px',
			border:'1px solid black',
			margin :'0 auto',
			'background-image':'url('+img+'/up.jpg)',
			'background-repeat':'no-repeat',
			'background-size':'cover'
		})
		.appendTo('#a')
		
		$('<td/>',{id:'left'})
		.css({
			width :'20%',
			height :'100%',
			border:'1px solid black',
			
			'vertical-align':'top'
			
			
		})
		.appendTo('tr')
		
		$('<td/>',{id:'right'})
		.css({
			width :'80%',
			height :'100%',
			border:'1px solid black',
			
			'vertical-align':'top'
			
			
		})
		.appendTo('tr')
		
		$.each(['naver','cgv','bugs'],(i,j)=>{
			$('<div/>')
			.text(j)
			.css({
				width:'100%',
				height:'50px',
				border:'1px solid black'
			})
			.appendTo('#left')
			.click(function(){
				$(this).css({'background-color':'skyblue'})
				$(this).siblings().css({'background-color':'white'})
			switch ($(this).text()) {
			case 'naver':
				$.getJSON(sessionStorage.getItem('ctx')+'/context/naver',d=>{
					$('#right').empty()
					$.each(d,(i,j)=>{
						$('<div/>')
						.html('<h1>'+j.origin+'<h1><h4>'+j.trans+'<h4>')
						.css({	
							width:'40%',
							height:'40%',
							border:'1px solid black',
							float: 'left',
							color :'green',
							'font-size':'2.0em'						
								})
						.appendTo('#right')
					})
				
				})
				break;
			case 'cgv':
				$.getJSON(sessionStorage.getItem('ctx')+'/context/cgv',d=>{
					$('#right').empty()
					$.each(d,(i,j)=>{
						$('<div/>')
						.html('<h1>'+j.title+'<h1><h4>'+j.content+'<h4><img src ="'+j.img+'">')
						.css({	

							border:'1px solid black',
							float: 'left'					
								})
						.appendTo('#right')
					})
					
				})
				break;
			case 'bugs':
				bugs({pageNo:0,size: 5})

				break;

			default:
				break;
			}
		})
			
		})
		let bugs=x=>{
			alert('벅스'+x.pageNo+x.size)
			$.getJSON(sessionStorage.getItem('ctx')+'/context/bugs/'+x.pageNo+'/'+x.size,d=>{
				$('#right').empty()
				alert('벅스')
				let pager = d.pager
				let list = d.list
				$('<table id = "content" ><tr id = "header"></tr></table>')
				.css({
					'margin-left': '50px',
					'margin-top': '50px'
					})
				
				.appendTo('#right')
				$.each(['순위','앨범','가수','제목'],(i,j)=>{
					$('<th>'+j+'</th>')
					.appendTo('#header')
				})
				$.each(list,(i,j)=>{
					$('<tr><td>'+j.rank+'</td><td><img src = "'+j.img+'"></td><td>'+j.artist+'</td><td>'+j.title+'</td></tr>')
					.appendTo('#content tbody')
				})
				$('<div id ="pac"></div>')
				.css({
					height:'100px',
					margin:'auto 0',
					'margin-left': '50px'})
				.appendTo('#right')
				if(pager.existprev){
					$('<div><a>이전</a></div>')
					.css({
						width :'80px',
						height : '80px',
						float: 'left',
						border:'1px solid pink'
				
					})
					.click(e=>{
						e.preventDefault()
						bugs({pageNo:pager.prevblock, size: x.size})

					})
					.appendTo('#pac')
				}
				for(let i = pager.startpage; i<=pager.endpage;i++){
					
					if(pager.nowpage ==1){
						$('<div><a>'+(parseInt(i)+1)+'</a></div>')
						.css({
							width :'80px',
							height : '80px',
							float: 'left',
							border:'1px solid pink',
							'background-color':'skyblue'
					
						})
						.click(function(e){
							e.preventDefault()

	
							bugs({pageNo:i, size: x.size})

						})
						.appendTo('#pac')
						
					}else{
						$('<div><a>'+(parseInt(i)+1)+'</a></div>')
						.css({
							width :'80px',
							height : '80px',
							float: 'left',
							border:'1px solid pink'
					
						})
						.click(function(e){
							e.preventDefault()

							bugs({pageNo:i, size: x.size})

						})
						.appendTo('#pac')
						
					}
					

				}
				if(pager.existnext){
					$('<div><a>다음</a></div>')
					.css({
						width :'80px',
						height : '80px',
						float: 'left',
						border:'1px solid pink'
				
					})
					.click(e=>{
						e.preventDefault()
						bugs({pageNo:pager.nextblock, size:x.size})

					})
					.appendTo('#pac')
				}
				$('<form id="paging_form" action="">'+
			               '  <select name="site" size="1" >'+    //    multiple
			               '  </select>'+
			               '</form>')
			           .appendTo('#pac')
				$.each([{sub:'5',val:'5'}, {sub:'10',val:'10'}, {sub:'15',val:'15'}], (i, j)=>{
		               $('<option value="'+ j.val +'">'+ j.sub +'개 보기</option>')
		               .appendTo('#paging_form select')
		           })
		           $('#paging_form option[value="'+list.pagesize+'"]').attr('selected','true')
			       $('#paging_form').change(()=>{
			        	   alert('선택한 보기:'+$('#paging_form option:selected').text())
			        	   bugs({pageNo: 0 , size: $('#paging_form option:selected').val()})
			           })
		        
				

			})
			
		}
		
		


	}
	return {run}
})()