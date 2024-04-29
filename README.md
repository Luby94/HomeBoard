■ 로그인 세션<br>
&nbsp;• src/main/java<br>
&nbsp;&nbsp;- com/board/controller/HomeController.java<br>
&nbsp;&nbsp;&nbsp;-- @ReqMap("/loginForm, /login, /logout")<br>
&nbsp;&nbsp;- com/board/user/mapper/UserMapper.java<br>
&nbsp;&nbsp;- com/board/config/WebMvcConfig.java<br>
&nbsp;&nbsp;- com/board/interceptor/LoginCheckInterceptor.java<br>
<div>&nbsp;</div>
&nbsp;• src/main/resources<br>
&nbsp;&nbsp;- mapper/userMapper.xml<br>
&nbsp;&nbsp;&nbsp;-- select id="login"<br>
<div>&nbsp;</div>
&nbsp;• src/main/webapp<br>
&nbsp;&nbsp;- WEB-INF/views/home.jsp<br>
&nbsp;&nbsp;&nbsp;-- "c:if test="${ sessionScope.login == null || login == '' }" + /c:if"<br>
<hr>
■ 게시판 페이징 구현<br>
&nbsp;• src/main/java<br>
&nbsp;&nbsp;- com/board/controller/BoardPagingController.java<br>
&nbsp;&nbsp;- com/board/domain/BoardPagingVo.java<br>
&nbsp;&nbsp;- com/board/domain/PagingResponse.java<br>
&nbsp;&nbsp;- com/board/domain/Pagination.java<br>
&nbsp;&nbsp;- com/board/domain/SearchVo.java<br>
&nbsp;&nbsp;- com/board/mapper/BoardPagingMapper.java<br>
<div>&nbsp;</div>
&nbsp;• src/main/resources<br>
&nbsp;&nbsp;- mapper/boardPagingMapper.xml<br>
<div>&nbsp;</div>
&nbsp;• src/main/webapp<br>
&nbsp;&nbsp;- WEB-INF/include/paging.jsp<br>
&nbsp;&nbsp;- WEB-INF/include/pagingmenus.jsp<br>
&nbsp;&nbsp;- WEB-INF/views/boardpaging/list.jsp<br>
&nbsp;&nbsp;- WEB-INF/views/boardpaging/view.jsp<br>
&nbsp;&nbsp;- WEB-INF/views/boardpaging/write.jsp<br>
&nbsp;&nbsp;- WEB-INF/views/boardpaging/update.jsp<br>
&nbsp;&nbsp;- WEB-INF/views/home.jsp<br>
