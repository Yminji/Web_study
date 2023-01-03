<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
	<h1>업로드가 완료되었습니다.</h1>
	<label>아이디 : </label>
	<input type="text" name="id" value="${map.id}" readonly><br>
	<label>이름 : </label>
	<input type="text" name="name" value="${map.name}" readonly><br>
	<div class="result-images">
		<!-- 업로드한 파일들을 forEach문으로 이용해 <img> 태그에 표시 -->
		<c:forEach var="imageFileName" items="${map.fileList}">
			<img src="${contextPath}/download?imageFileName=${iamgeFileName}">
			<br><br><br>
		</c:forEach>
	</div><p>
	
	<a href="${contextPath}/form">다시 업로드 하기</a>
</body>
</html>