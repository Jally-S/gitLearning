$(function(){
	var currentPage = $("#currentPage").text();
	var pages = $("#pages").text();
	//�����ǰ�����һҳ
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
		//���μӻҰ�ť�ĵ���¼�
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