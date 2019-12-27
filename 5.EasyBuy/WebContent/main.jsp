<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.11.1.min_044d0927.js"></script>
<script type="text/javascript" src="js/jquery.bxslider_e88acd1b.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
</head>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="searchBar.jsp"%>

	<div class="menu_bg">
		<div class="menu">
			<!--Begin 商品分类详情 Begin-->
			<div class="nav">
				<div class="nav_t">全部商品分类</div>
				<div class="leftNav">
					<ul>

						<c:forEach items="${list }" var="products">
							<li>
								<div class="fj">
									<span class="n_img"><span></span> <img src="" /></span> <span
										class="fl">${products.name }</span>
								</div>
								<div class="zj">
									<div class="zj_l">

										<c:forEach items="${list2 }" var="products2">
											<c:if test="${products.id == products2.parentId }">
												<div class="zj_l_c">
													<h2>
														<a
															href="goodsList_queryGoodsBylevel?level=2&id=${products2.id }&page=1">${products2.name
															}</a>
													</h2>
													<c:forEach items="${list3}" var="products3">
														<c:if test="${products2.id == products3.parentId }">
															<a href="goodsList_queryGoodsBylevel?level=3&id=${products3.id }&page=1">${products3.name }</a>|							
														</c:if>
													</c:forEach>
												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<ul class="menu_r">
				<li><a href="easybuy_queryAll">首页</a></li>
				<c:forEach items="${list }" var="goods">
					<li><a
						href="goodsList_queryGoodsBylevel?level=1&id=${goods.id }&page=1">${goods.name
							}</a></li>
				</c:forEach>
			</ul>
			<div class="m_ad">中秋送好礼！</div>
			<!--End 商品分类详情 End-->
		</div>
	</div>
	<!--End Menu End-->
	<div class="i_bg bg_color">
		<div class="i_ban_bg">
			<!--Begin Banner Begin-->
			<div class="banner">
				<div class="top_slide_wrap">
					<ul class="slide_box bxslider">
						<li><img src="images/ban1.jpg" width="740" height="401" /></li>
						<li><img src="images/de2.jpg" width="740" height="401" /></li>
						<li><img src="images/de3.jpg" width="740" height="401" /></li>
					</ul>
					<div class="op_btns clearfix">
						<a href="#" class="op_btn op_prev"><span></span></a> <a href="#"
							class="op_btn op_next"><span></span></a>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				var jq2 = jQuery.noConflict();
				(function() {
					$(".bxslider").bxSlider({
						auto : true,
						prevSelector : jq2(".top_slide_wrap .op_prev")[0],
						nextSelector : jq2(".top_slide_wrap .op_next")[0]
					});
				})();
			</script>
			<!--End Banner End-->
			<div class="inews">
				<div class="news_t">
					<span class="fr"><a
						href="goodsList_getAllNews?page=1">更多 ></a></span>新闻资讯
				</div>
				<ul>
					<c:forEach items="${list5 }" var="news">
						<li><span>[ 公告 ]</span> <a
							href="javascript:void(0);">${news.title }</a></li>
					</c:forEach>
				</ul>
				<div class="charge_t">
					话费充值
					<div class="ch_t_icon"></div>
				</div>
				<form>
					<table border="0" style="width: 205px; margin-top: 10px;"
						cellspacing="0" cellpadding="0">
						<tr height="35">
							<td width="33">号码</td>
							<td><input type="text" value="" class="c_ipt" /></td>
						</tr>
						<tr height="35">
							<td>面值</td>
							<td><select class="jj" name="city">
									<option value="0" selected="selected">100元</option>
									<option value="1">50元</option>
									<option value="2">30元</option>
									<option value="3">20元</option>
									<option value="4">10元</option>
							</select> <span style="color: #ff4e00; font-size: 14px;">￥99.5</span></td>
						</tr>
						<tr height="35">
							<td colspan="2"><input type="submit" value="立即充值"
								class="c_btn" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>


	<c:forEach items="${list }" var="products">
		<div class="content mar_20">
			<img src="images/mban_1.jpg" width="1200" height="110" />
		</div>
		<div class="i_t mar_10">
			<span class="floor_num">${products.name }</span> <span class="fl">${products.name
				}</span>
		</div>

		<div class="content">
			<div class="fresh_left">
				<div class="fre_ban">
					<div id="imgPlay1" class="imgPlay1">
						<ul class="imgs" id="actor1">
							<li><a href="#"><img src="images/fre_r.jpg" width="211"
									height="286" /></a></li>
						</ul>
					</div>
				</div>
				<div class="fresh_txt">
					<div class="fresh_txt_c">
						<c:forEach items="${list2 }" var="products2">
							<c:if test="${products.id == products2.parentId }">
								<a
									href="goodsList_queryGoodsBylevel?level=2&id=${products2.id }&page=1">${products2.name
									}</a>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="fresh_mid">
				<ul>
					<c:forEach items="${list4 }" var="produuct">
						<c:if test="${products.id == produuct.categoryLevel1Id }">
							<li>
								<div class="name">
									<a href="#">${produuct.name }</a>
								</div>
								<div class="price">
									<font>￥<span>${produuct.price }</span></font> &nbsp;
								</div>
								<div class="img">
									<a
										href="goodsList_getProductById?id=${produuct.id}">
										<img src="images/${produuct.fileName } " width="185"
										height="155" />
									</a>
								</div>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="fresh_right">
				<ul>
					<li><a href="#"><img src="images/fre_b1.jpg" width="260"
							height="220" /></a></li>
					<li><a href="#"><img src="images/fre_b2.jpg" width="260"
							height="220" /></a></li>
				</ul>
			</div>
		</div>
	</c:forEach>


	<%@ include file="footer.jsp"%>
</body>
</html>