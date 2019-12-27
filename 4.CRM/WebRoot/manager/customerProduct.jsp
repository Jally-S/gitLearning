<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jvsun.dao.factory.*"%>
<%@page import="org.jvsun.pojo.*"%>
<%
String path = request.getContextPath();
%>
<%
List<ProductPOJO> list = (List<ProductPOJO>) request.getAttribute("list");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户与产品</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
    <link type="text/css" href="<%=path %>/css/css.css" rel="stylesheet"/>
  </head>
  
  <body>
	<form name = "com">
	<h3>产品名：</h3>
		<%  List<String> strList = new ArrayList<String>();
			String pn = null;
			String cid = request.getParameter("cid");
		%>
		<%
			for (ProductPOJO pojo : list) {
				pn = pojo.getProductName();
				strList.add(pn);
			}
		%>

		<%
			List<ProductPOJO> listp = ProductDAOFactory.getDAOInstance()
					.findAllByNamePriceClass("", 0.00, 0, 100, 1);
			for (ProductPOJO pojo : listp) {
		
				if (strList.indexOf(pojo.getProductName())!=-1) {
		%>
		<%=pojo.getProductName()%>
		<input type="checkbox" name="pro" id="<%=pojo.getProductId()%>" checked="checked" onchange="goUpd('<%=pojo.getProductId()%>','<%=cid%>')">

		<%
			} else {
		%>
		<%=pojo.getProductName()%>
		<input type="checkbox" name="pro" id="<%=pojo.getProductId()%>" onchange="goUpd('<%=pojo.getProductId()%>','<%=cid%>')">
		<%
			}
			}
		%>




	</form>
		</body>
		<script type="text/javascript">
		
			function goUpd(a,b){
				if(document.getElementById(a).checked==true){
					 $(document).ready(function(){
						 	//设置提交的路径，和参数
							$.post("<%=path%>/CusProAdd",{"pid":a,"cid":b},
							function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
				 				$("#showTable").html(data);//显示Servlet返回的内容
				 			});
						});
					alert("选中");
				}else if(document.getElementById(a).checked==false){
					$(document).ready(function(){
					 	//设置提交的路径，和参数
						$.post("<%=path%>/CusProDel",{"pid":a,"cid":b},
						function(data){//Servlet执行完之后执行方法，data表示的servlet返回数据内容
			 				$("#showTable").html(data);//显示Servlet返回的内容
			 			});
					});
					alert("未选中");
					
				}
				
			}
		</script>
</html>
