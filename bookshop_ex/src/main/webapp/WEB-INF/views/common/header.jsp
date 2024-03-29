<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	var lookSearch = true; //제시된 키워드를 클릭하면 keywordSerach()함수의 실행을 중시킴
	function keywordSearch(){
		if(loopSearch == false)
			return;
		
		var value= document.frmSearch.searchWord.value; //검색한 단어
			$.ajax({
				type:"get", 
				async:true, //비동기식
				url:"${contextPath}/goods/keywordSearch.do",
				data:{keyword:value},
				success:function(data, textStatus){
					var jsonInfo = JSON.parse(data);
					displayResult(jsonInfo);
				},
				error:function(data, textStatus){
					alert("에러가 발생했습니다."+data);
				},
				complate:function(data, textStatus){
					//alert("작업을 완료했습니다.");
				}
			});
		
		function displayResult(jsonInfo){
			var count = jsonInfo.keyword.length; //json으로 변환한 키워드 개수
			if(count > 0){
				var html = '';
				//JSON 데이터를 차례대로 <a> 태그를 이용해 키워드 목록 만듦
				for(var i in jsonInfo.keyword){
					html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
				}
				//<a>태그로 만든 키워드 목록을 <div>태그에 차례대로 표시
				var listView = document.getElementById("suggestList");
				listView.innerHTML = html;
				show('suggest');
			}else{
				hide('suggest');
			}
		}
		
		function select(selectKeyword){
			document.frmSearch.searchWord.value=selectedKeyword;
			loopSearch =false;
			hide('suggest');
		}
		
		function show(elementId){
			var element = document.getElementById(elementId);
			if(element){
				element.style.display = 'block';
			}
		}
		
		function hide(elementId){}
		var element = document.getElementById(elementId);
		if(element){
			element.style.display = 'none';
		}
	}
</script>
<html>
<body>
	<div id = "logo">
		<a href="${contextPath}/main/main.do">
			<img width="176" height="80" alt="booktopia" src="${contextPath}/resources/image/Booktopia_Logo.jpg">
		</a>
	</div>
	<div id = "head_link">
		<ul>
			<c:choose>
				<c:when test="${isLogOn==true and not empty memberInfo}">
					<li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
					<li><a href="${contextPath}/mypage/myPageMain.do">마이페이지</a></li>
					<li><a href="${contextPath}/cart/myCartList.do">장바구니</a></li>
					<li><a href="#">주문배송</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
					<li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="#">고객센터</a></li>
			<c:if test="${isLogOn==true and memberInfo.member_id == 'admin' }">
				<li class="no_line"><a href="${contextPath}/admin/goods/adminGoodsMain.do">관리자</a></li>
			</c:if>
		</ul>
	</div>
	<br>
	<div id="search">
		<form name="frmSearch" action="${contextPath}/goods/searchGoods.do">
			<input name="searchWord" class="main_input" type="text" onKeyUp="keywordSearch()">
			<input type="submit" name="search" class="btn1" value="검 색">
		</form>
	</div>
	<div id="suggest">
		<div id="suggestList"></div>
	</div>
</body>
</html>