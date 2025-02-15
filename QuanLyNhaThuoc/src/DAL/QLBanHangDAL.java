package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;
import java.text.SimpleDateFormat;

public class QLBanHangDAL {

    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public ArrayList<HoaDon> LoadInvoice() {
        String sql = "select * from HOADON order by SOHD asc";
        ArrayList<HoaDon> h = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setSOHD(rs.getString(1));
                hd.setNGHD(rs.getString(2));
                hd.setMANV(rs.getString(3));
                hd.setMAKH(rs.getString(4));
                hd.setTRIGIA(rs.getFloat(5));

                h.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return h;
    }

    public boolean InsertHoaDon(ArrayList<HoaDon> hoaDons) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into HOADON values(?,?,?,?,?)";
        try {
            for (HoaDon hd : hoaDons) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(hd.getNGHD());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());

                pres.setString(1, hd.getSOHD());
                pres.setDate(2, sqlDate);
                pres.setString(3, hd.getMANV());
                pres.setString(4, hd.getMAKH());
                pres.setFloat(5, hd.getTRIGIA());
                
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean InsertCTHD(ArrayList<CTHD> cthd) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into CTHD values(?,?,?,?,?)";
        float soluong=0;
        String sql1 ="Update sanpham set soluong=soluong-? where MASP=?";
        try {
            for (CTHD hd : cthd) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, hd.getSOHD());
                pres.setString(2, hd.getMASP());
                pres.setFloat(3, hd.getSOLUONG());
                pres.setFloat(4, hd.getDONGIA());
                pres.setFloat(5, hd.getTHUE());
                
                pres.executeUpdate();
                soluong=hd.getSOLUONG();
                PreparedStatement pres1 =conn.prepareStatement(sql1);
                pres1.setFloat(1, soluong);
                pres1.setString(2, hd.getMASP());
                pres1.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean UpdateHoaDon(ArrayList<HoaDon> hoaDons) {
        String sql = "Update HOADON set TRIGIA=? where SOHD =?";
        try {
            for (HoaDon hd : hoaDons) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setFloat(1, hd.getTRIGIA());
                pres.setString(2, hd.getSOHD());
                
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
