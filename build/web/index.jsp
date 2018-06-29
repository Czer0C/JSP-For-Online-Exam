<%-- 
    Document   : index
    Created on : May 12, 2018, 7:58:57 PM
    Author     : CzeroC
--%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
      <link href="view/css/bootstrap.css" type="text/css" rel="stylesheet">
      <link href="view/css/index.css" type="text/css" rel="stylesheet">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <script src="view/jquery/jquery.min.js"></script>
      <script src="view/javascript/index.js"></script>
      <title>Trang web thi tự luận</title>
  </head>
  <% 
      if(session.getAttribute("USERNAME") != null) {
          String url = String.format("validation?username=%s&password=%s",
          session.getAttribute("USERNAME"), session.getAttribute("PASSWORD"));
          response.sendRedirect(url);
      } 
  %>
  <body>
      <h1 id="title"><center><b>Trang Web thi tự luận</b></center></h1>
      <div class="signupSection">
        
        <div class="info">
        	<br>
          <h2>Chào mừng tới trang web</h2>
          <img src="view/images/logo2.png">
          <br>
          <p>Tương lai sáng lạng</p>
        </div>
        <form action="validation" method="POST" class="signupForm" name="signupform">
          <br><br><br>
          <h2>Đăng nhập</h2>
          <ul class="noBullet">
            <li>
              <label for="username"></label>
              <input type="text" class="inputFields" id="username" name="username" placeholder="Tên tài khoản" value="" oninvalid="this.setCustomValidity('Vui lòng nhập vào tên tài khoản')" oninput="setCustomValidity('')" onfocusout="userNameValidation()" required/>
            </li>
            <li>
              <label for="password"></label>
              <input type="password" class="inputFields" id="password" name="password" placeholder="Mật khẩu" value="" oninvalid="this.setCustomValidity('Vui lòng nhập vào mật khẩu')" oninput="setCustomValidity('')" onfocusout="passwordValidation()" required/>
            </li>
            <li id="center-btn">
              <input type="submit" id="login-btn" name="login" alt="Login" value="Vào">
            </li>
            <span id="warning">Đăng nhập thất bại</span>
          </ul>
        </form>
    </div>
    <jsp:include page="view/footer.html"/>
  </body>
</html>
