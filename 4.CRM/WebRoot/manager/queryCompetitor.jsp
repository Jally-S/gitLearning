<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询对手</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/pintuer.css">
	<link rel="stylesheet" href="../css/admin.css">
  </head>
  
  <body>
	<form name = "com">
		<fieldset title="查询">
			<legend>
				<span width="12%" height="25" class="STYLE1"
								style="color: black;">查询条件</span>
			</legend>
			对手名称：<input type="text" name="cname" class="input" style="width:250px; line-height:17px;display:inline-block" placeholder="请输入搜索关键字"/>
			对手级别：<select name="clas"  class="input" style="width:70px; line-height:17px; display:inline-block">
						<option value="0" selected="selected">全部</option>
						<option value="1">一级竞争</option>
						<option value="2">二级竞争</option>
						<option value="3">三级竞争</option>
						<option value="4">四级竞争</option>
					</select>
			<input type="button" value="查询" onclick="query(0)" class="button border-main icon-search"/>
            <a class="button border-main icon-plus-square-o" onclick="goAdd()">新增</a>
		</fieldset>
			
		</form>
		<div id="showTable"></div>
		<div align="right">
			<input type="button" id="first" value="|<" onclick="query(1)"/>
			<input type="button" id="up" value="<" onclick="query(2)"/>
			<input type="button" id="next" value=">" onclick="query(3)"/>
			<input type="button" id="end" value=">|" onclick="query(4)"/>
			<span id="showPageMessage"></span>
		</div>
		</body>
		<script type="text/javascript">
		var pageSize = 10;//一页显示的数据笔数
		var pageCurrent = 1;//显示的页数
		var allCount = 0;//总共的数据笔数
		var allPage = 0;//总共数据页数
		query(0);
		function query(num){
			var cname = com.cname.value;
			var clas = com.clas.value;
			if(num == 1){//第一页
				pageCurrent = 1;
			}else if(num == 2){//上一页
				pageCurrent = pageCurrent -1;
			}else if(num == 3){//下一页
				pageCurrent = pageCurrent + 1;
			}else if(num == 4){//最后一页
				pageCurrent = allPage;
			}
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/CompetitorQuery",{"cname":cname,"clas":clas,"pageSize":pageSize,"pageCurrent":pageCurrent},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				$("#showTable").html(data);//显示Servlet返回的内容
	 				controlButton();
	 			});
			});
		}
		function controlButton(){//设置按钮可见与否，停在第一页时不能点击上一页。停在最后一页时，不能点击下一页
			allCount = $("#count").val();
			if(allCount%pageSize == 0){
				allPage = allCount/pageSize
			}else{
				allPage = Math.floor(allCount/pageSize) +1;
			}
			document.getElementById("first").disabled = false;
			document.getElementById("up").disabled = false;
			document.getElementById("next").disabled = false;
			document.getElementById("end").disabled = false;
			if(allPage == 1){
				document.getElementById("first").disabled = true;
				document.getElementById("up").disabled = true;
				document.getElementById("next").disabled = true;
				document.getElementById("end").disabled = true;
			}else if(pageCurrent == 1){
				document.getElementById("first").disabled = true;
				document.getElementById("up").disabled = true;
			}else if(pageCurrent == allPage){
				document.getElementById("next").disabled = true;
				document.getElementById("end").disabled = true;
			}
			$("#showPageMessage").html("总共"+allCount+"笔数据，当前显示"+pageCurrent+"页，共"+ allPage+"页");
			
		}
			function goAdd(){
				var width = window.screen.width ;
				var height = window.screen.height ;
				window.open("<%=path%>/manager/addCompetitor.jsp","新增对手",'height=400,width=300,top='+(height-450)/2+',left='+(width-300)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			}
			function goUpdate(cid){
				var width = window.screen.width ;
				var height = window.screen.height ;
				window.open("<%=path%>/FindCompetitorById?cid="+cid,"修改对手",'height=400,width=300,top='+(height-450)/2+',left='+(width-300)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			}
			function goDelete(cid){
				if(confirm("确认删除？")){
					 $(document).ready(function(){
				 	//设置提交的路径，和参数
					$.post("<%=path%>/DelCompetitor",{"cid":cid},
					function(data){
						//Servlet执行完之后执行方法，data表示的servlet返回数据内容
		 				if(data == "true"){
		 					alert("删除成功");
		 					query(0);
		 				}else{
		 					alert("删除失败，请联系系统管理员");
		 				}
		 			});
				});
				}
			}
			function goAddProduct(pid){
				var width = window.screen.width ;
				var height = window.screen.height ;
				window.open("<%=path%>/manager/addCompetitorProduct.jsp?pid="+pid,"新增对手产品",'height=400,width=300,top='+(height-450)/2+',left='+(width-300)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			}
		</script>
</html>
