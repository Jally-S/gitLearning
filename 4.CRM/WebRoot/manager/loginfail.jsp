<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>登录失败</title>
  </head>
  
  <body>
    <h1>登录失败，请重新<a href="login.jsp">登录</a></h1> <br>
  </body>
</html>
