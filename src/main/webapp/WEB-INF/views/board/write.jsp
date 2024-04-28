<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" />
<link rel="stylesheet"  href="/css/common.css" />

<style>
   input:not(input[type=submit]) { width:100%; }
   input[type=submit] { width : 100px; }
   #goList  { width : 80px; }
   
   td { 
      padding:10px;
      width: 700px;
      text-align: center;
   }
   td:nth-of-type(1) {
      width : 200px;
   }
   
   td:not([colspan]):first-child {
      background: black;
      color : white;
      font-weight: bold;
   }
   
   input[readonly] {
   	  background: #D0D0D0;
   }
   
   textarea {
   	height: 250px;
   	width: 100%;
   }

</style>

<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>

</head>
<body>
  <main>
  
    <%@include file="/WEB-INF/include/menus.jsp" %>
  
	<h2>게시글 등록</h2>
	<%-- <form action="/Board/Write?menu_id=${ menu_id }" method="POST"> --%>
	<form action="/BoardPaging/Write?menu_id=${ menu_id }&nowpage=${ nowpage }" method="POST">
	<table>
	 <tr>
	   <td>제목</td>
	   <td><input type="text" name="title" /></td>
	 </tr>
	 <tr>
	   <td>작성자</td>
	   <td><input type="text" name="writer" value="${ sessionScope.login.userid }"/></td>
	 </tr>
	 <tr>
	   <td>내용</td>
	   <td><textarea name="content"></textarea></td>
	 </tr>
	 <tr>
	   <td colspan="2">
	    <input type="submit" value="글 쓰기" />
	    <input type="button" value="목록" id="goList" />
	    <a href="/">Home</a>
	   </td>
	 </tr>
	
	</table>	
	</form>   
	
  </main>
  
  <script>
  	const  goListEl  = document.getElementById('goList');
  	goListEl.addEventListener('click', function(e) {
  		//location.href = '/Board/List?menu_id=${menu_id}';
  		location.href = '/BoardPaging/List?menu_id=${menu_id}&nowpage=${ nowpage }';
  	})
  
  </script>
  
</body>
</html>





