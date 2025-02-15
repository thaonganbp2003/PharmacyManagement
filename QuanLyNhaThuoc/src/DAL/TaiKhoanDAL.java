package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;

public class TaiKhoanDAL {

    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public ArrayList<TaiKhoan> dsTaiKhoan() {
        String sql = "select TENTK, MATKHAU from TAIKHOAN";
        ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTaiKhoan(rs.getString(1));
                tk.setMatKhau(rs.getString(2));

                dsTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in TaiKhoanDAL");
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsTaiKhoan;

    }

    public ArrayList<TaiKhoan> TimTaiKhoan(String MANV) {
        String sql = "select TENTK, MATKHAU from TAIKHOAN where MANV=?";
        ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            pres.setString(1, MANV);
            rs = pres.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setTenTaiKhoan(rs.getString(1));
                tk.setMatKhau(rs.getString(2));

                dsTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in TaiKhoanDAL");
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsTaiKhoan;

    }

    public TaiKhoan dangNhap(String userName, String passWord) {
        TaiKhoan tk = null;
        String sql = " SELECT * FROM TAIKHOAN WHERE TENTK = ? AND MATKHAU=?";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            if (conn != null) {
                pres = conn.prepareStatement(sql);
                pres.setString(1, userName);
                pres.setString(2, passWord);
                rs = pres.executeQuery();
                if (rs.next()) {
                    tk = new TaiKhoan();
                    tk.setTenTaiKhoan(rs.getString("TENTK"));
                    tk.setMatKhau(rs.getString("MATKHAU"));
                    tk.setVaiTro(rs.getInt("VAITRO"));
                    tk.setMaNV(rs.getInt("MANV"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tk;
    }

    public boolean Insert(ArrayList<TaiKhoan> dsTaiKhoans) {
        try {
            String sql = "Insert into TAIKHOAN values (?,?,?,?)";
            for(TaiKhoan tk :dsTaiKhoans){
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);
                
                pres.setString(1, tk.getTenTaiKhoan());
                pres.setString(2, tk.getMatKhau());
                pres.setInt(3, tk.getVaiTro());
                pres.setInt(4, tk.getMaNV());
                
                pres.executeUpdate();
            }
            
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
