<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/cart.js"></script>
<script type="text/javascript">
    	$(function(){
    		$(".nav").hover(function(){						   
				$(this).find(".leftNav").show();
			},function(){
				$(this).find(".leftNav").hide();
			});
			$(".leftNav ul li").hover(
				function(){
					$(this).find(".fj").addClass("nuw");
					$(this).find(".zj").show();
				}
				,
				function(){
					$(this).find(".fj").removeClass("nuw");
					$(this).find(".zj").hide();
				}
			);
    	})
    </script>
<title>易买网</title>
</head>
<body>
	<!--Begin Header Begin-->
	<div id="searchBar">
		<%@ include file="searchBar.jsp"%>
	</div>
	<!--End Header End-->
	<!--Begin Menu Begin-->
	<div class="menu_bg">
		<div class="menu">
			<!--Begin 商品分类详情 Begin-->
			<%@ include file="categoryBar.jsp"%>
			<!--End 商品分类详情 End-->
		</div>
	</div>
	<!--End Menu End-->
	<div class="i_bg">
		<div id="settlement">

			<div class="content mar_20">
				<img src="images/img1.jpg" />
			</div>
			<!--Begin 第一步：查看购物车 Begin -->
			<div class="content mar_20">
				<table border="0" class="car_tab"
					style="width: 1200px; margin-bottom: 50px;" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="car_th" width="200">商品名称</td>
						<td class="car_th" width="150">单价</td>
						<td class="car_th" width="150">购买数量</td>
						<td class="car_th" width="130">小计</td>
						<td class="car_th" width="150">操作</td>
					</tr>

					<c:forEach items="${sessionScope.cart }" var="product">
						<tr class="goods">
							<td>
								<div class="c_s_img">
									<a href="goodsList_getProductById?id=${product.value.id}"><img
										src="images/${product.value.fileName }" width="73" height="73" /></a>
								</div> ${product.value.name }
							</td>
							<td align="center" style="color: #ff4e00;" id="price">￥${product.value.price
								}</td>
							<td align="center">
								<div class="c_num">
									<input type="button" value="" onclick="subQuantity(this,${product.value.price},${product.value.id});" class="car_btn_1" /> 
									<input type="text" value="${product.value.quantity }" name="quantit" class="car_ipt" readonly="readonly" /> 
									<input type="button" value="" onclick="addQuantity(this,${product.value.price},${product.value.id},${product.value.stock });" class="car_btn_2" />
								</div>
							</td>
							<td align="center" style="color: #ff4e00;" class="qprice">￥${product.value.price*product.value.quantity}</td>
							<td align="center"><a href="javascript:void(0);"
								onclick="removeCart(this,${product.value.id});" class="delete">删除</a></td>
						</tr>
					</c:forEach>
					<tr height="70">
						<td colspan="6"
							style="font-family: 'Microsoft YaHei'; border-bottom: 0;"><span
							class="fr">商品总价：<b
								style="font-size: 22px; color: #ff4e00;" id="totleprice">￥${count }</b></span></td>
					</tr>
					<tr valign="top" height="150">
						<td colspan="6" align="right">
						<!-- 继续购物 -->
						<a href="main.jsp"><img src="images/buy1.gif" /></a> 
						<!-- 确认结算 -->
						<a href="javascript:void(0);" onclick="settlement2()"><img src="images/buy2.gif" /></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<script>
        $(function(){
           // settlement1();
        });
    </script>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>
