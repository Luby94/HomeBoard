<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/common.css" />
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" />
</head>
<body>
  <main>
	<h2>Home</h2>
	<div><a href="/Menus/WriteForm">새 메뉴 추가</a></div>
	<div><a href="/Menus/WriteForm2">새 메뉴 추가2</a></div>
	<div><a href="/Menus/List">메뉴 목록</a></div>
	<br />
	<div><a href="/Users/List">사용자 목록</a></div>
	<div><a href="/Users/WriteForm">사용자 추가</a></div>
	<div>&nbsp;</div>
	<div><a href="/Board/List?menu_id=MENU01">게시물 목록</a></div>
	<div><a href="/Board/WriteForm?menu_id=MENU01">게시물 등록</a></div>
	<div>&nbsp;</div>
	
	<c:if test="${ sessionScope.login == null || login == '' }">
	  <div><a href="/loginForm" class="btn btn-info btn-sm">로그인</a></div>
	</c:if>
	
	<!-- sessionScope 변수에 login 변수 있음 -->
	<!-- session 안에 있는 login 이 null 인 경우(= 로그인 안된 경우)는 /loginForm 으로 이동하는 로그인 a tag 가 보이고 -->
	<!-- login 이 null 이 아닌 경우(= 로그인 된 경우)는 로그인 안보임 -->
	
	<div>
	  ${ login.username } 님 환영합니다<br />
	  당신의 가입일은 ${ login.indate } 입니다
	</div>
	<div><a href="/logout" class="btn btn-info btn-sm" style="padding: 1px">로그아웃</a></div>
	
  </main>	
</body>
</html>