$(function(){
	var currentPage = $("#currentPage").text();
	var pages = $("#pages").text();
	//如果当前是最后一页
	if(currentPage == pages){
		$(".pageControl_item").eq(1).addClass("pageControl_item_disabled");
		$(".pageControl_item").eq(2).addClass("pageControl_item_disabled");
	}
	if(currentPage == 1){
		$(".pageControl_item").eq(4).addClass("pageControl_item_disabled");
		$(".pageControl_item").eq(5).addClass("pageControl_item_disabled");
	}
	$(".pageControl_item").click(function(){
		var index = $(".pageControl_item").index(this);
		//屏蔽加灰按钮的点击事件
		if($(".pageControl_item").eq(index).hasClass("pageControl_item_disabled")){
			return false;
		}
		var currentPage = $("#currentPage").text();
		var dataPrePage = $("#dataPrePage").text();
		var pages = $("#pages").text();
		switch(index){
			case 5:
				currentPage = 1;
				break;
			case 4:
				currentPage--;
				break;
			case 2:
				currentPage++;
				break;
			case 1:
				currentPage = pages;
				break;
		}
		window.location.href="servlet/BookServlet?currentPage="+currentPage+"&dataPrePage="+dataPrePage+"&method=getAll";
	});
})