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
