<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet" href="/css/common.css" />
<!-- SCSS cdn url 복붙-->
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script> <!-- 복붙 시 한줄에 다 표현 -->

<style>
  td {
	padding: 10px;
	width: 150px;
	text-align: center;
}
  tr:first-child {
	background-color: black;
	color: white;
	font-weight: bold;
		/* SCSS 문법 적용 -> download -> 13줄 url 주소 복붙 */
	td {
		border-color: red;
	}
}
  tr:nth-child(2) > td:nth-child(1) {
	text-align: right;
}
</style>

</head>
<body>
  <main>
    <h2>메뉴 목록</h2>
    <table>
      <tr>
        <td>Menu_id</td>
        <td>Menu_name</td>
        <td>Menu_seq</td>
        <td>삭제</td>
        <td>수정</td>
      </tr>
      <tr>
        <!-- <td colspan="5">[<a href="/Menus/WriteForm">메뉴 등록</a>]</td> -->
        <td colspan="5">[<a href="/Menus/WriteForm">메뉴 등록</a>]&nbsp;&nbsp;&nbsp;
        [<a href="/Menus/WriteForm2">메뉴 등록2</a>]&nbsp;&nbsp;&nbsp;
        [<a href="/">Home</a>]
        </td>
      </tr>
      
      <c:forEach var="menu" items="${ menuList }">		<!-- items : Model 에 담겨있는 것 -->
        <tr>
	      <td>${ menu.menu_id }</td>
	      <td>${ menu.menu_name }</td>
	      <td>${ menu.menu_seq }</td>
	      <td><a href="/Menus/Delete?menu_id=${ menu.menu_id }">삭제</a></td>
	      <td><a href="/Menus/UpdateForm?menu_id=${ menu.menu_id }">수정</a></td>
        </tr>
      </c:forEach>
      
    </table>
  </main>
</body>
</html>