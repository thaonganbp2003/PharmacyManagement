package DAL;

import DTO.*;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QLNhapHangDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public ArrayList<HoaDonNhap> LoadInvoice() {
        String sql = "select * from HOADONNHAP order by SOHDN asc";
        ArrayList<HoaDonNhap> h = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                HoaDonNhap hd = new HoaDonNhap();
                hd.setSOHDN(rs.getString(1));
                hd.setNGHDN(rs.getString(2));
                hd.setMANV(rs.getString(3));
                hd.setMANCC(rs.getString(4));
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
    public boolean InsertHoaDon(ArrayList<HoaDonNhap> hoaDons) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into HOADONNHAP values(?,?,?,?,?)";
        try {
            for (HoaDonNhap hd : hoaDons) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(hd.getNGHDN());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());

                pres.setString(1, hd.getSOHDN());
                pres.setDate(2, sqlDate);
                pres.setString(3, hd.getMANV());
                pres.setString(4, hd.getMANCC());
                pres.setFloat(5, hd.getTRIGIA());
                
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean InsertCTHD(ArrayList<CTHDNhap> cthd) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into CTHDNHAP values(?,?,?,?,?,?)";
        try {
            for (CTHDNhap hd : cthd) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, hd.getSOHDN());
                pres.setString(2, hd.getMASP());
                pres.setFloat(3, hd.getSOLUONG());
                pres.setFloat(4, hd.getDONGIA());
                pres.setFloat(5, hd.getTHUE());
                pres.setFloat(6, hd.getGIAGOC());
                
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean UpdateHoaDon(ArrayList<HoaDonNhap> hoaDons) {
        String sql = "Update HOADONNHAP set TRIGIA=? where SOHDN =?";
        try {
            for (HoaDonNhap hd : hoaDons) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setFloat(1, hd.getTRIGIA());
                pres.setString(2, hd.getSOHDN());
                
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
