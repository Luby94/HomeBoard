<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/css/common.css" />

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

</style>
</head>
<body>
  <main>
	  <h2>사용자 상세 정보</h2>
	  <table>
	    <tr>
	      <td>아이디</td>
	      <td>${ vo.userid }</td>
	    </tr>
	    <tr>
	      <td>비밀번호</td>
	      <td>${ vo['passwd'] }</td>
	    </tr>
	    <tr>
	      <td>이름</td>
	      <td>${ vo.username }</td>
	    </tr>
	    <tr>
	      <td>이메일</td>
	      <td>${ vo.email }</td>
	    </tr>
	    <tr>
	      <td>포인트</td>
		  <td>
		  <%-- <c:if test="${ vo.upoint ne 0 }">	<!-- 3번줄 명령 필수  -->
		  ${ vo['upoint'] }
		  </c:if> --%>
		   
		  <c:choose>
		    <c:when test="${ vo.upoint ne 0 }">
		   	  ${ vo['upoint'] }
		   	</c:when>
			<c:otherwise>
			  <span style="color: red">없음</span>
			</c:otherwise>
		  </c:choose>
		  
		  </td>
		  <%-- <td>${ vo['upoint'] + 1000 }</td> --%>
	    </tr>
	    <tr>
	      <td>회원등급</td>
	      <td>${ vo.upt }</td>
	    </tr>
	    
	    <tr>
	      <td colspan="2">
	      <a class="btn btn-primary btn-sm" role="button" 
	     href="/Users/WriteForm">회원가입</a>
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/Users/UpdateForm?userid=${ vo.userid }">회원수정</a>
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/Users/Delete?userid=${ vo.userid }">회원삭제</a>
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/Users/List">회원목록</a>
	     <a class="btn btn-primary btn-sm" role="button" 
	     href="/">Home</a>
	      </td>
	    </tr>
	  </table>
	  
  </main>
  
</body>
</html>