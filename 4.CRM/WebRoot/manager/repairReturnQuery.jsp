<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
		<link rel="stylesheet" href="../css/pintuer.css">
	<link rel="stylesheet" href="../css/admin.css">
	</head>
	<body>
		<form name = "f">
			<field title="查询">
			<legend>
				<span width="12%" height="25" class="STYLE1" style="color: black;">查询条件</span>
			</legend>
			维修回执单名称：
			<input type="text" name="reptName" class="input" style="width:250px; line-height:17px;display:inline-block" placeholder="请输入搜索关键字"/>
			报修单名称：
			<input type="text" name="repairName" class="input" style="width:250px; line-height:17px;display:inline-block" placeholder="请输入搜索关键字"/>
			<input type="button" value="查询" onclick="query(0)" class="button border-main icon-search"/>
			</field>
		</form>
		<hr />
		<div id="showTable"></div>
		<div align="right">
			<input type="button" id="first" value=" |< " onclick="query(1)" />
			<input type="button" id="up" value=" < " onclick="query(2)" />
			<input type="button" id="next" value=">" onclick="query(3)" />
			<input type="button" id="end" value=">|" onclick="query(4)" />
			<select id="selectPageCurrent" onchange="query(5)">
				<option value="3" >
					显示3笔
				</option>
				<option value="5" selected="selected">
					显示5笔
				</option>
				<option value="10">
					显示10笔
				</option>
			</select>
			<span id="showPageMessage"></span>
		</div>
	</body>
	<script type="text/javascript">
		var pageSize = 5;//一页显示的数据笔数
		var pageCurrent = 1;//显示的页数
		var allCount = 0;//总共的数据笔数
		var allPage = 0;//总共数据页数
		query(0);
		function query(num){
			var reptName = f.reptName.value;
			var repairName = f.repairName.value;
			if(num ==1){
				pageCurrent = 1;
			}else if(num ==2){
				pageCurrent = pageCurrent -1;		
			}else if(num ==3){
				pageCurrent = pageCurrent +1;
			}else if(num ==4){
				pageCurrent = allPage;
			}else if(num ==5){
				pageCurrent = 1;
				pageSize = $("#selectPageCurrent").val();//取得每页显示的数据笔数
			}
			$(document).ready(function(){//设置提交的路径，和参数
				$.post("<%=path %>/RepairReturnQuery",{"reptName":reptName,"pageSize":pageSize,
					"pageCurrent":pageCurrent,"repairName":repairName},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
				$("#showTable").html(data);//显示Servlet返回的内容
				controlButton();
				});
			});
		}
		function controlButton(){
			allCount = $("#count").val();
			if(allCount%pageSize == 0){
				allPage = allCount/pageSize;
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
			$("#showPageMessage").html("总共" +allCount +"笔数据，当前显示" +pageCurrent+ "页，共" +allPage+ "页");
		}
		function goUpdate(reptId){
			var width = window.screen.width;
			var height = window.screen.height;
			window.open("<%=path%>/RepairReturnFindByReptId?reptId="+reptId,"修改维修回执单",'height=400,width=300,top='+(height-450)/2+',left='+(width-300)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no' );
		}
		function goDelete(reptId){
			if(confirm("确认删除?")){
				 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path %>/RepairReturnDel",{"reptId":reptId},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
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

</script>
</html>