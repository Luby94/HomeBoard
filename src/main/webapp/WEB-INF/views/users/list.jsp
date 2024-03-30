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

<style>
  td { 
    padding     : 10px;  
    width       : 150px;
    text-align  : center; 
  }
  
  tr:first-child {
     background-color: black;
     color : white;
     font-weight: bold;
     /* SCSS 문법에 적용 
     https://www.jsdelivr.com/package/npm/browser-scss
      */
     td {
        border-color : white;
     }
  }
  
  /*
  tr:first-child > td {
     border-color : white;
  }
  */
    
  
  tr:nth-child(2) > td {
     text-align : right;
  }
  
</style>
</head>
<body>
  <main>
    <h2>사용자 목록</h2>
    <table>
      <tr>
        <td>아이디</td>
        <td>이름</td>
        <td>이메일</td>
        <td>회원등급</td>
        <td>가입일</td>
      </tr>
      <tr>
        <td colspan="5">[<a href="/Users/WriteForm">사용자 추가</a>]&nbsp;&nbsp;
        [<a href="/">Home</a>]
        </td>
      </tr>
      
      <c:forEach var="userVo" items="${ userList }">
      <tr>
        <td>${ userVo.userid }</td>
        <td>${ userVo.username }</td>
        <td>${ userVo.email }</td>
        <td>${ userVo.upoint }</td>
        <td>${ userVo.indate }</td>
      </tr>
      </c:forEach>
      
    </table>
  </main>
</body>
</html>