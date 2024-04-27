<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table id="menus">
    <c:forEach var="menu" items="${ menuList }"><!-- items=menuList 를 var=menu 에 넣어서 그 menu 안에 있는 id, name 등을 가져오는 것 -->
      <td>
        <a href="/Board/List?menu_id=${ menu.menu_id }">
        ${ menu.menu_name }
        </a>
      </td>
    </c:forEach>
    </table>

</body>
</html>