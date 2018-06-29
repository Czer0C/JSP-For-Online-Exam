/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.ThiTuLuan;

/**
 *
 * @author CzeroC
 */
@WebServlet(name = "validation", urlPatterns = {"/validation"})
public class validation extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if(request.getParameter("username") != null){
                String _username = request.getParameter("username");
                String _password = request.getParameter("password");
                System.out.println(_password);
                if (_username != null && _password != null) {
                    if (ThiTuLuan.KiemTraDangNhap(_username, _password)) {
                         session.setAttribute("ISLOGIN", true);
                         session.setAttribute("USERNAME", _username);
                         session.setAttribute("PASSWORD", _password);
                        if(_username.startsWith("gv")){
                            response.sendRedirect("view/teacher.jsp?u=" + _username);
                        }
                        else {
                            if(!ThiTuLuan.KiemTraTonTaiTraLoi(_username))
                                ThiTuLuan.KhoiTaoCauTraLoi(_username);
                        }
                    }
                    else {
                        out.println("Invalid username or password");
                    }
                }
                else {
                    out.println("Must not leave these 2 fields empty");
                }
            }
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if(request.getParameter("username") != null){
                String _username = request.getParameter("username");
                String _password = request.getParameter("password");
                if (_username != null && _password != null) {
                    if (ThiTuLuan.KiemTraDangNhap(_username, _password)) {
                         session.setAttribute("ISLOGIN", true);
                         session.setAttribute("USERNAME", _username);
                         session.setAttribute("PASSWORD", _password);
                        if(_username.startsWith("gv")){
                            response.sendRedirect("view/teacher.jsp?u=" + _username);
                        }
                        else{
                          response.sendRedirect("view/student.jsp?u=" + _username);
                          if(!ThiTuLuan.KiemTraTonTaiTraLoi(_username)){
                                ThiTuLuan.KhoiTaoCauTraLoi(_username);
                          }
                        }
                    }
                    else {
                        out.println("Invalid username or password");
                        response.sendRedirect("index.jsp?re=1");
                    }
                }
                else {
                    out.println("Must not leave these 2 fields empty");
                }
            }
        }
    }
}
