<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.jvsun.dao.*"%>
<%@ page import="org.jvsun.pojo.*"%>
<%@ page import="org.jvsun.dao.factory.*" %>
<%@ page import="org.jvsun.dao.proxy.*" %>
<%@ page import="org.jvsun.dao.impl.*" %>
<%@ page import="java.math.*" %>
<%
	String path = request.getContextPath();
 %>
<%		
		VLoginPOJO pojologin = (VLoginPOJO)session.getAttribute("pojo");
		VLoginPOJO vpojo = UserLoginDAOFactory.getDAOInstance().findUserByLogin(pojologin.getAccount());
		List<MenuPOJO> list = UserLoginDAOFactory.getDAOInstance().findMenuByLoginId(vpojo.getLoginId());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
%>
<html>
	<head>
		<title>客户关系管理系统</title>
		<link rel="stylesheet" href="../css/pintuer.css">
		<link rel="stylesheet" href="../css/admin.css">
		<script src="../js/jquery.js">
</script>
	</head>
	<body style="background-color: #f2f9fd;">
		<div class="header bg-main">
			<div class="logo margin-big-left fadein-top">
					<h2 style="color:black;">
					<img src="../img/y.jpg" class="radius-circle rotate-hover"
						height="50" alt="" />
					客户关系管理系统
					</h2>
				
			</div>
			<div class="head-l" >
				<a class="button button-little bg-red" href="<%=path%>/Logout"><span
					class="icon-power-off"></span> 退出登录</a>
			</div>
		</div>
		<div class="leftnav" id="showFirstMenu">
				<span style="padding-left:30px;">欢迎您：${pojo.userName}</span>
			<% 
			for (MenuPOJO pojo:list) {
				
				String menuName = pojo.getMenuName();
				 BigDecimal menuId =pojo.getMenuId();
				 	
				List<MenuPOJO> listChild = UserLoginDAOFactory.getDAOInstance().findChildMenuByFaherId(menuId);
				%>
				<h2>
					<span class='icon-user'></span><%= menuName%></h2>
				<ul>
					<% 
					for (MenuPOJO pojoChild:listChild) {	
						String menuPath = pojoChild.getMenuPath();
						String childmenuName = pojoChild.getMenuName();%>
						<li>
							<a href="<%= menuPath%>" target='right'><span>
								</span><%= childmenuName %></a>
						</li>
					<% }%>
				</ul>
				
			<%}%>

		</div>
		<ul class="bread">
			<li>
				<a href="##" id="a_leader_txt">网站信息</a>
			</li>
		</ul>
		<div class="admin">
			<iframe scrolling="auto" rameborder="0" src="home.jsp" name="right"
				width="100%" height="100%"></iframe>
		</div>
	</body>
	<script type="text/javascript">

$(function() {
	$(".leftnav h2").click(function() {
		$(this).next().slideToggle(200);
		$(this).toggleClass("on");
	})
	$(".leftnav ul li a").click(function() {
		$("#a_leader_txt").text($(this).text());
		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
	})
});
</script>
</html>