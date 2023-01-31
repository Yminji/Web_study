<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<script>
	var arry_index=0;
	var SEVER_URL="${contextPath}/thumnails.do";
	//빠른 메뉴의 다음 클릭 시 <hidden>태그에 저장된 상품 정보를 가져와 이미지를 표시
	function fn_show_next_goods(){
		var img_sticky=document.getElementById("img_sticky"); //이미지
		var cur_goods_num = document.getElementById("cur_goods_num"); //현재 상품 번호
		var _h_goods_id=document.frm_sticky.h_goods_id; //이미지 번호 반환
		var _h_goods_fileName=document.frm_sticky.h_goods_fileName; //이미지 파일 이름 반환
		if(array_index < h_goods_id.length-1) 
			array_index++; //다음 클릭 시 배열의 인덱스를 1증가
			
		//증가된 인덱스에 대한 배열 요소의 상품 번호와 이미지 파일 이름을 가져와 표시
		//사용자가 다음을 클릭하면 <hidden>태그의 상품 정볼르 자바스크립트 함수로 전달하여 이미지 표시
		var goods_id = h_goods_id[arry_index].value;
		var fileName = h_goods_fileName[arry_index].value;
		img_sticky.src = SEVER_URL+"?goods_id="+goods_id+"&fileName="+fileName;
		cur_goods_num.innerHTML=array_index+1; 
	}
	
	//빠른 메뉴의 이전 클릭 시 <hidden> 태그의 저장도니 상품 정보를 가져와 이미지 표시
	function fn_show_previous_goods(){
		var img_sticky=document.getElementById("img_sticky");
		var cur_goods_num=document.getElementById("cur_goods_num");
		var _h_goods_id=document.frm_sticky.h_goods_id;
		var _h_goods_fileName=document.frm_sticky.h_goods_fileName;
		
		if(array_index>0)
			array_index--;
		
		var goods_id=h_goods_id[array_index].value;
		var fileName=h_goods_fileName[array.index].value;
		img_sticky.src=SEVER_URL+"?goods_id="+goods_id+"&fileName="+fileName;
		cur_goods_num.innerHTML=array_index+1;
	}
	
	function goodsDetail(){
		var cur_goods_num = document.getElementById("cur_goods_num");
		arrIdx=cur_goods_num.innerHTML-1;
		
		var img_sticky=document.getElementById("img_sticky");
		var h_goods_id=document.frm_sticky.h_goods_id;
		var len=h_goods_id.length;
		
		if(len>1){ //상품의 개수가 한 개 초과라면
			goods_id=h_goods_id[arrIdx].value;
		}else{
			goods_id=h_goods_id.value;
		}
		
		var formObj=document.createElement("form");
		var i_goods_id = doucument.createElement("input");
		
		i_goods_id.name="goods_id";
		i_goods_id.value="goods_id";
		
		formObj.appendChild(i_goods_id); 
		documentn.body.appendChild(formObj);
		formObj.method="get";
		formObj.action="${contextPath}/goods/goodsDetail.do?goods_id="+goods_id;
		formObj.submit();
		
	}
</script>

<html>
<body>
	<div id="sticky">
		<ul>
			<li><a href="#">
				<img width="24" height="24" src="${contextPath}/resources/image/facebook_icon.png">
				페이스북
			</a></li>
			<li><a href="#">
				<img width="24" height="24" src="${contextPath}/resources/image/twitter_icon.png">
				트위터
			</a></li>
			<li><a href="#">
				<img width="24" height="24" src="${contextPath}/resources/image/rss_icon.png">
				RSS 피드
			</a></li>
		</ul>
	<div class="recent">
		<h3>최근 본 상품</h3>
			<ul>
				<c:choose>
					<c:when test="${empty quickGoodsList}">
						<strong>상품이 없습니다.</strong>
					</c:when>
					<c:otherwise>
						<form name="frm_sticky">
							<!-- 세션에 저장된 빠른 메뉴 목록의 이미지 정보를 hidden 태그에 차례대로 저장 -->
							<c:forEach var="item" items="${quickGoodsList}" varStatus="itemNum">
								<c:choose>
									<c:when test="${itemNum.count==1}">
										<a href="javascript:goodsDetail();">
											<img width="75" height="95" id="img_sticky"
												src="${contextPath}/thumnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
										</a>
											<!-- 동일한 hidden 태그에 연속해서 저장하면 배열로 저장됨 -->
											<input type="hidden" name="h_goods_id" value="${item.goods_id}"/>
											<input type="hidden" name="h_goods_fileName" value="${item.goods_fileName}"/>
										<br>
									</c:when>
								</c:choose>
							</c:forEach>
						</form>
					</c:otherwise>
				</c:choose>
			</ul>
	</div>
	</div>
	<c:choose>
		<c:when test="${empty quickyGoodsList}">
			<h5> &nbsp; &nbsp; &nbsp; &nbsp; 0/0 &nbsp;</h5> 
		</c:when>
		<c:otherwise>
			<h5><a href='javascript:fn_show_previous_goods();'>이전</a> 
			&nbsp; <span id="cur_goods_num">1</span>/${quickGoodsListNum} &nbsp; 
			<a href='javascript:fn_show_newxt_goods();'>다음</a></h5>
		</c:otherwise>
	</c:choose>
</body>
</html>