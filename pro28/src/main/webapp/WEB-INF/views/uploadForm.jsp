<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일업로드 하기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	var cnt = 1; //파일 업로드 name값을 다르게 하는 변수
	function fn_addFile(){
		$("#d_file").append("<br>"+"<input  type='file' name='file"+cnt+"' />");
		cnt++;
	}//파일 추가를 클릭하면 동적으로 파일 업로드 추가, name 속성의 값으로 'file'+cnt를 설정함으로써 값을 다르게 해줌
</script>
</head>
<body>
	<h1>파일 업로드 하기</h1>
	<!-- 파일 업로드 시 enType은 반드시 multipart/form-data로 설정 -->
	<form method="post" action="${contextPath}/upload" enctype="multipart/form-data">
		<label>아이디 : </label>
		<input type="text" name="id"><br>
		<label>이름 : </label>
		<input type ="text" name="name"><br>
		<input type="button" value="파일 추가" onCLick="fn_addFile()"/><br>
		<!-- 자바스크립트를 이용해 <div> 태그 안에 파일 업로드를 추가 -->
		<div id="d_file"></div>
		<input type="submit" value="업로드"/>
	</form>
</body>
</html>