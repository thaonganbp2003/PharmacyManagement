package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;
import java.text.SimpleDateFormat;

public class KhoThuocDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    public ArrayList<KhoThuoc> loadMedicine() {
//        String sql = "select * from KHOSANPHAM";
        String sql = "select * from KHOSANPHAM order by MALO asc";
        ArrayList<KhoThuoc> loadName = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                KhoThuoc t = new KhoThuoc();
                t.setMALO(rs.getString(1));
                t.setMASP(rs.getString(2));
                t.setTENSP(rs.getString(3));
                t.setDVT(rs.getString(4));
                t.setGIA(rs.getFloat(5));
                t.setSOLUONG(rs.getInt(6));
                t.setNHASX(rs.getString(7));
                t.setSOLOSX(rs.getString(8));
                t.setNGAYSX(rs.getString(9));
                t.setHSD(rs.getString(10));
                t.setGIAGOC(rs.getFloat(11));
                t.setMANCC(rs.getString(12));

                loadName.add(t);
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
        return loadName;
    }
    public ArrayList<KhoThuoc> SearchMedicine(String search) {
        String sql = "select * from KHOSANPHAM where MASP ='"+ search+"'";
        ArrayList<KhoThuoc> loadName = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                KhoThuoc t = new KhoThuoc();
                t.setMALO(rs.getString(1));
                t.setMASP(rs.getString(2));
                t.setTENSP(rs.getString(3));
                t.setDVT(rs.getString(4));
                t.setGIA(rs.getFloat(5));
                t.setSOLUONG(rs.getInt(6));
                t.setNHASX(rs.getString(7));
                t.setSOLOSX(rs.getString(8));
                t.setNGAYSX(rs.getString(9));
                t.setHSD(rs.getString(10));
                t.setGIAGOC(rs.getFloat(11));
                t.setMANCC(rs.getString(12));

                loadName.add(t);
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
        return loadName;
    }
    public boolean InsertMedicine(ArrayList<KhoThuoc> thuoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into KHOSANPHAM values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            for (KhoThuoc t : thuoc) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(t.getNGAYSX());
                java.util.Date utildate1 = formatter.parse(t.getHSD());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(utildate1.getTime());

                pres.setString(1, t.getMALO());
                pres.setString(2, t.getMASP());
                pres.setString(3, t.getTENSP());
                pres.setString(4, t.getDVT());
                pres.setFloat(5, t.getGIA());
                pres.setInt(6, t.getSOLUONG());
                pres.setString(7, t.getNHASX());
                pres.setString(8, t.getSOLOSX());
                pres.setDate(9, sqlDate);
                pres.setDate(10, sqlDate1);
                pres.setFloat(11, t.getGIAGOC());
                pres.setString(12,t.getMANCC());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteMedicine(String MASP, String SOLOSX) {
        String sql = "Delete from KHOSANPHAM where MASP=? and SOLOSX=?";
        try {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, MASP);
                pres.setString(2, SOLOSX);
                pres.executeUpdate();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
