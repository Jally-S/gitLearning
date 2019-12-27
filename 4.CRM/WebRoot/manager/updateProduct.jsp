<%@page import="org.jvsun.pojo.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新产品信息</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
	<link rel="stylesheet" href="<%=path %>/css/admin.css">
  </head>
  
  <body>
  <center>
		<form name = "pro">
			<input type="hidden" name="pid" value = "${pojo.productId}" class="input"/>
			<br/>
			产品名称：<input type="text" name="pname" value = "${pojo.productName}" class="input"/>
			<br/>
			产品描述：<input type="text" name="pdesc" value = "${pojo.productDescribe}" class="input"/>
			<br/>
			产品类别：<input type="text" name="pclass" value = "${pojo.productClass}" class="input"/>
			<br/>
			产品库存：<input type="text" name="pin" value = "${pojo.productInventory}" class="input"/>
			<br/>
			产品单价：<input type="text" name="price" value = "${pojo.productPrice}" class="input"/>
			<br/>
			<input type="button" value="确认" onclick="upd()" class="button border-main icon-search"/>
			<input type="button" value="返回" onclick="back()" class="button border-main icon-search"/>
		</form>
	</center>	
  </body>
  <script type="text/javascript">
		function upd(){
			var pid=pro.pid.value;
			var pname=pro.pname.value;
			var pdesc=pro.pdesc.value;
			var pclass=pro.pclass.value;
			var pin=pro.pin.value;
			var price=pro.price.value;
			 $(document).ready(function(){
			 	//设置提交的路径，和参数
				$.post("<%=path%>/ProductUpd",
				{"pid":pid,"pname":pname,"pdesc":pdesc,"pclass":pclass,"pin":pin,"price":price},
				function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
	 				if(data == "true"){
	 					alert("产品修改成功");
	 					back();
	 				}else{
	 					alert("产品修改失败，请联系系统管理员");
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
