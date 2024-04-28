<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

   <table id="menu">
	  <c:forEach var="menu" items="${ menuList }">
	    <td>
	      <a href="/BoardPaging/List?menu_id=${ menu.menu_id }&nowpage=${ nowpage }">
	       ${ menu.menu_name }
	      </a> 
	     </td>
	  </c:forEach>
   </table>	  
   
   
   
   
   
   
   
     