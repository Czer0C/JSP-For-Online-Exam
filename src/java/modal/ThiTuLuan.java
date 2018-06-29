/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ThiTuLuan {
    public static Boolean KiemTraTonTaiTraLoi(String user){
         try {
            Connection con = DataProvider.Connect();
            String sql = String.format("SELECT count(*) FROM traloi where username = '%s'", user);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            System.out.println();
            if(rs.getInt(1) != 0){
                con.close();
                return true;
            }
            else{
                con.close();
                return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static Boolean CapNhatCauTraLoi(int id, String username, String ans) {
     
        try{
            Connection con = DataProvider.Connect();
            String sql = String.format("update traloi set cautraloi = '%s' where sttcauhoi = %d and username = '%s'", ans, id, username);
            System.out.println(sql);
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            con.close();
            return true;
        }catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public static int KhoiTaoCauTraLoi(String user)
    {
        int k = 0;
        try{
            ArrayList dsch = ThiTuLuan.LayDanhSachTatCaCauHoi();
            Connection con = DataProvider.Connect();
            for(int i = 0; i < dsch.size(); i++){
                CauHoi ch = (CauHoi)dsch.get(i);
                String sql = String.format("insert into traloi(username, sttcauhoi, cautraloi) value('%s',%d, NULL)", user, ch.stt);
                System.out.println(sql);
                PreparedStatement st = con.prepareStatement(sql);
                st.executeUpdate();
                
            }
            con.close();
            return k;
        }catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }
    
    
    public static Boolean KiemTraDangNhap(String user, String pass){
        try {
            Connection con = DataProvider.Connect();
            System.out.println(pass);
            String sql = String.format("SELECT * FROM taikhoan where username = '%s' and password = '%s'", user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            if(rs.first() != false){
                con.close();
                return true;
            }
            else{
                con.close();
                return false;    
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static ArrayList LayDanhSachTatCaCauHoi() {
        try {
            ArrayList dsch = new ArrayList();
            Connection con = DataProvider.Connect();
            String sql = "SELECT * FROM cauhoi";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CauHoi ch = new CauHoi();
                ch.stt = rs.getInt("stt");
                ch.noiDungCauHoi = rs.getString("noidung");
                dsch.add(ch);
            }
            
            con.close();
            return dsch;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static CauHoi LayCauHoi(int i){
        try {
            Connection con = DataProvider.Connect();
            String sql = "SELECT * FROM cauhoi where stt = " + i;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            CauHoi ch = new CauHoi();
            rs.next();
            ch.stt = rs.getInt("stt");
            ch.noiDungCauHoi = rs.getString("noidung");
            con.close();
            return ch;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static TraLoi LayCauTraLoi(String username, int id) {
        try {
            TraLoi ctl = new TraLoi();
            Connection con = DataProvider.Connect();
            
            String sql = "select * from traloi, cauhoi where username = '" + username + "' and traloi.sttcauhoi = " + id + " and traloi.sttcauhoi = cauhoi.stt"; 
            
            System.out.println(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            ctl.noiDungCauHoi = rs.getString("noidung");
            ctl.cauTraLoi = rs.getString("cautraloi");
            ctl.cauTraLoiChinhXac = rs.getString("dapan");
            con.close();
            return ctl;
        } catch (SQLException ex) {
            return null;
        }
    }
    public static ArrayList LayDanhSachTatCaCauTraLoiCuaAiDo(String user) {
        try {
            ArrayList dstl = new ArrayList();
            Connection con = DataProvider.Connect();
            String sql = "SELECT * FROM traloi, cauhoi where traloi.STTCauHoi = cauhoi.STTCauHoi and Username='" + user +"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TraLoi tl = new TraLoi();
                tl.username = user;
                tl.stt = rs.getInt("STTCauHoi");
                tl.cauTraLoi = rs.getString("CauTraLoi");
                tl.noiDungCauHoi = rs.getString("CauHoi");
                tl.cauTraLoiChinhXac = rs.getString("CauTraLoiChinhXac");
            }
            con.close();
            return dstl;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList LayDanhSachNguoiDaThamGiaTraLoi(){
        try {
            ArrayList dsntl = new ArrayList();
            Connection con = DataProvider.Connect();
            String sql = "SELECT distinct Username FROM traloi WHERE cautraloi is not null";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.username = rs.getString("Username");
                dsntl.add(tk);
            }
            con.close();
            return dsntl;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static String LayTraLoi(int id, String user){
        try {
            Connection con = DataProvider.Connect();
            String sql = "SELECT cautraloi FROM traloi WHERE username = (?) and sttcauhoi = (?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            String ch = "";
            if(rs != null){
                rs.next();
                ch = rs.getString("cautraloi");
                con.close();
                return ch;
            }
            else{
                con.close();
                return null;
            }
            
        } catch (SQLException ex) {
            return null;
        }
    }
}
