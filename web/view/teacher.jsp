<%-- 
    Document   : teacher
    Created on : May 22, 2018, 9:46:44 AM
    Author     : CzeroC
--%>

<%@page import="modal.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <%
    if (session.getAttribute("USERNAME") == null) {
      response.sendRedirect("http://localhost:8084/exam/index.jsp");
    }
  %>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang web của giáo viên</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/normalize.min.css">
    <link rel="stylesheet" href="css/teacher.css">
    <script src="jquery/jquery.min.js"></script>
    <script src="javascript/teacher.js"></script>
  </head>  
  <body>
    <h2><b>Chào, <%=request.getParameter("u")%></b></h2>
    <h2><b><a href="../logout" id="logout">Đăng xuất</a></b></h2>
    <div class="mainBox">
      <h1><center><b>DANH SÁCH CÁC THI SINH ĐÃ LÀM BÀI</b></center></h1>
      <main>
        <table>
          <thead>
            <tr>
              <th><center>Tài khoản</center></th>
              <th><center>Chi tiết</center></th>
            </tr>
          </thead>
          <tbody>
            <%
              ArrayList<TaiKhoan> dssv = ThiTuLuan.LayDanhSachNguoiDaThamGiaTraLoi();
              for (TaiKhoan i : dssv) {%>
                  <tr>
                    <td width="50%" data-title='Provider Name'><%= i.username %></td>
                    <td width="50%" class='select'><button class='btn btnPrimary' id="<%=i.username%>">Xem</a></td>
                  </tr>
              <%}
            %>
          </tbody>
          <tfoot><tr><th colspan='2'>Năm: 2018</th></tr></tfoot>
        </table>
        <div class='detail'>
          <div class='detail-container'>
            <div class="container">
              <center><b><h1 id="username"></h1></b></center>
              <b><h3 id="outputQuestionID"></h3></b>
              <div><span id="outputQuestionDetail"></span></div>
              <hr>
              <h3><b>Câu trả lời của SV:</b></h3>
              <div><span id="outputStudentAnswer" ></span></div>
              <hr>
              <h3><b>Đáp án đúng:</b></h3>
              <div><span id="outputValidAnswer"></span></div>
              <div class="clearfix">
                <button type="button" id="btnBack">Câu trước đó</button>
                <button type="button" id="btnNext">Câu kế tiếp</button>
              </div>
            </div>
          </div>
          <div class='detail-nav'>
            <button id="off" class='close'>Đóng</button>
          </div>
        </div>
      </main> 
    </div>
    
    <jsp:include page="footer.html"/>
  </body>
</html>