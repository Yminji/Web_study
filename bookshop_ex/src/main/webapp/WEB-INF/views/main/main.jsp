<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${paegContext.request.contextPath }"/>
<%
	request.setCharacterEncoding("utf-8");
%>

<div id="ad_main_banner">
	<ul class="bjqs">
		<li><img width="775" height="145" src="${contextPath}/resources/image/main_banner01.jpg">
		<li><img width="775" height="145" src="${contextPath}/resources/image/main_banner02.jpg"/>
		<li><img width="775" height="145" src="${contextPath}/resources/image/main_banner03.jpg"/>
 	</ul>
</div>

<div class="main_boodk">
	<c:set var="goods_count" value="0"/>
		<h3>베스트셀러</h3>
		<c:forEach var="item" items="${goodsMap.bestseller}">
			<c:set var="goods_count" value="${goods_count+1}"/>
			<div class="book">
				<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
					<img class="link" src="${contextPath}/resources/image/1px.gif"></a>
				<img width="121" height="154" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
				
				<div class="title">${item.goods_title}</div>
				<div class="price">
					<fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price"/>
					${goods_price}원
				</div>
			</div>
			<c:if test="${goods_count==15}">
				<div class="book">
					<font size=20><a href="#">more</a></font>
				</div>
			</c:if>
		</c:forEach>
</div>

<div class="clear"></div>

<div id="ad_sub_banner">
	<img width="770" height="117" src="${contextPath}/resources/image/suub_banner01.jpg">
</div>

<div class="main_book">
	<c:set var="goods_count" value="0"/>
		<h3>새로 출판된 책</h3>
		<c:forEach var="item" items="${goodsMap.newbook}">
			<c:set var="goods_count" value="${goods_count+1}"/>
				<div class="book">
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
						<img class="link" src="${contextPath}/resources/image/1px.gif"></a>
					<img width="121" height="154" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
					
					<div class="title">${item.goods_title}</div>
					<div class="price">
						<fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price"/>
						${goods_price}원
					</div>
				</div>
				<c:if test="${goods_count == 15}">
					<div class="book">
						<font size=20><a href="#">more</a></font>
					</div>
				</c:if>
		</c:forEach>
</div>

<div class="clear"></div>

<div class="ad_sub_banner">
	<img width="770" height="117" src="${contextPath}/resources/image/sub_banner02.jpg">
</div> 

<div class="maini_book">
	<c:set var="goods_count" value="0"/>
		<h3>스테디셀러</h3>
		<c:forEach var="item" items="${goods_steadyseller}">
			<c:set var="goods_count" value="${goods_count+1}"/>
				<div class="book">
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
					<img class="link" src="${contextPath}/resources/image/1px.gif"></a>
					<img width="121" height="154" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
					<div class="title">${item.goods_title }</div>
					<div class="price">
						<fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price"/>
						${goods_price }원
					</div>
					<c:if test="${item.goods_count==15}">>
						<div class="book">
							<font size =20><a href="#">more</a></font>
						</div>
					</c:if>
				</div>
		</c:forEach>
</div>
