<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新增产品</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
	<center>
		<form name = "pro"><br/>
			产品名称：<input type="text" name="pname"  class="input"/>
			<br/>
			产品描述：<input type="text" name="pdescribe" class="input"/>
			<br/>
			产品类别：<input type="text" name="pclass" class="input"/>
			<br/>
			产品库存：<input type="text" name="pinventory" class="input"/>
			<br/>
			产品单价：<input type="text" name="price" class="input"/>
			<br/>
			<input type="button" value="确认" onclick="add()"class="button border-main icon-search"/>
			<input type="reset" value="重置" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()"class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function add(){
			var pname=pro.pname.value;
			var pdescribe=pro.pdescribe.value;
			var pclass=pro.pclass.value;
			var pinventory=pro.pinventory.value;
			var price=pro.price.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/AddProduct",
				{"pname":pname,"pdescribe":pdescribe,"pclass":pclass,"pinventory":pinventory,"price":price},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("产品新增成功");
	 					back();
	 				}else{
	 					alert("产品新增失败，请联系系统管理员");
	 				}
	 			});
			});
		}
			
		function back(){
			opener.location.reload(); 
			//window.dialogArguments.query(0);//刷新之前页面 
			window.close();//关闭当前页面
		}
	</script>
</html>
