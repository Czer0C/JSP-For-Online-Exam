<%-- 
    Document   : welcome
    Created on : May 12, 2018, 8:15:18 PM
    Author     : CzeroC
--%>

<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.*"%>
<%@page import="modal.*"%>
<%@page import="modal.ThiTuLuan"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
        <link href="css/student.css" type="text/css" rel="stylesheet">
        <script src="jquery/jquery.min.js"></script>
        <script src="javascript/student.js"></script>
        <title>Trang thi tự luận</title>
    </head>
    <% 
        if (session.getAttribute("USERNAME") == null) {
            response.sendRedirect("http://localhost:8084/exam/index.jsp");
        }   
        ArrayList<CauHoi> lch = ThiTuLuan.LayDanhSachTatCaCauHoi();
    %>

    <body>
        <h1 id="hello">Chào, <%=request.getParameter("u")%></h1>
        <h1><a href = "../logout">Đăng xuất</a></h1>
        <div class="mainSection">
            <div class="answerBox">
                <p>
                    <h3 id="qid">Câu hỏi thứ <%=lch.get(0).stt%>:</h3>
                    <h3><div class="question"><%=lch.get(0).noiDungCauHoi%></div></h3>
                </p>
                <ul class="noBullet">
                  <li>
                    <hr>
                    <h3><b>Câu trả lời:</b></h3>
                    <br>
                    <input type="text" class="inputFields" id="answer" name="answer" placeholder="Câu trả lời" value="" required/>
                  </li>
                  <li id="center-btn">
                    <input type="submit" class="btnSpecial" id="btnBack" value="Quay lại">
                    <input type="submit" class="btnSpecial" id="btnSubmit" value="Gửi">
                    <input type="submit" class="btnSpecial" id="btnNext" value="Tiếp tục">
                  </li>
                  <h5 id="status">Gửi</h5>
                </ul>                
                <p id="page">1/10</p>
            </div>
        </div>
    <jsp:include page="footer.html"/>
    </body>
</html>
