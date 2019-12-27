<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>上传合同照片</title>
    <link rel="stylesheet" href="../css/pintuer.css">
	<link rel="stylesheet" href="../css/admin.css">
  </head>
  
  <body>
	<%
		int pid=Integer.parseInt(request.getParameter("pid"));
	%>
	<form method="post" enctype="multipart/form-data" action="<%=path%>/UpServlet">
		<input type="hidden" name="pid" value="<%=pid %>"/>
		<label for="file" class="button border-main icon-plus-square-o">选择图片</label>
		<input type="file" name="photo" id="file" style="display: none" />
		<input type="submit" value="确认上传" onclick="add()" class="button border-main icon-plus-square-o"/>
		<input type="button" value="返回" onclick="back()" class="button border-main icon-plus-square-o"/>
	</form>
  </body>
 <script>
    document.getElementById('file').onchange = function() {
        var imgFile = this.files[0];
        var fr = new FileReader();
        fr.onload = function() {
            document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
        };
        fr.readAsDataURL(imgFile);
    };
</script>
</html>
