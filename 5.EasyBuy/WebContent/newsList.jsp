<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="header.jsp"%>
</head>
<body>
	<%@ include file="searchBar.jsp"%>
	<div class="i_bg bg_color">
		<!--Begin 用户中心 Begin -->
		<div class="m_content">
			<%@ include file="leftBar.jsp"%>
			<div class="m_right">
				<p></p>
				<div class="mem_tit">资讯列表</div>
				<table border="0" class="order_tab"
					style="width: 930px; text-align: center; margin-bottom: 30px;"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="20%">文章标题</td>
							<td width="25%">创建时间</td>
						</tr>
						<c:forEach items="${list7 }" var="news">
							<tr>
								<td><a
									href="javascript:void(0);">${news.title }</a></td>
								<td>${news.createTime }</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<%@ include file="pagerBar2.jsp"%>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>

</html>
