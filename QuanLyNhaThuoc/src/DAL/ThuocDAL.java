package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;
import java.text.SimpleDateFormat;

public class ThuocDAL {

    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public ArrayList<Thuoc> loadMedicineName() {
        String sql = "select * from SANPHAM order by MASP asc";
        ArrayList<Thuoc> loadName = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMASP(rs.getString(1));
                t.setTENSP(rs.getString(2));
                t.setDVT(rs.getString(3));
                t.setGIA(rs.getFloat(4));
                t.setSOLUONG(rs.getInt(5));
                t.setNHASX(rs.getString(6));
                t.setSOLOSX(rs.getString(7));
                t.setNGAYSX(rs.getString(8));
                t.setHSD(rs.getString(9));
                t.setGIAGOC(rs.getFloat(10));
                t.setMANCC(rs.getString(11));
                t.setTRANGTHAI(rs.getInt(12));

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

    public ArrayList<Thuoc> loadExpiryMedicine() {
        String sql = "select * from SANPHAM where round(months_between(sysdate,HSD)) >=-1 and TRANGTHAI=1";
        ArrayList<Thuoc> loadName = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMASP(rs.getString(1));
                t.setTENSP(rs.getString(2));
                t.setDVT(rs.getString(3));
                t.setGIA(rs.getFloat(4));
                t.setSOLUONG(rs.getInt(5));
                t.setNHASX(rs.getString(6));
                t.setSOLOSX(rs.getString(7));
                t.setNGAYSX(rs.getString(8));
                t.setHSD(rs.getString(9));
                t.setGIAGOC(rs.getFloat(10));
                t.setMANCC(rs.getString(11));
                t.setTRANGTHAI(rs.getInt(12));

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

    public ArrayList<Thuoc> loadExpiryMedicine2() {
        String sql = "select * from SANPHAM where round(months_between(sysdate,HSD)) >=0 or SOLUONG =0 and TRANGTHAI=1";
        ArrayList<Thuoc> loadName = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMASP(rs.getString(1));
                t.setTENSP(rs.getString(2));
                t.setDVT(rs.getString(3));
                t.setGIA(rs.getFloat(4));
                t.setSOLUONG(rs.getInt(5));
                t.setNHASX(rs.getString(6));
                t.setSOLOSX(rs.getString(7));
                t.setNGAYSX(rs.getString(8));
                t.setHSD(rs.getString(9));
                t.setGIAGOC(rs.getFloat(10));
                t.setMANCC(rs.getString(11));
                t.setTRANGTHAI(rs.getInt(12));

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

    public int loadExpiryMedicine1() {
        String sql = "select count(*) from SANPHAM where round(months_between(sysdate,HSD)) >=-1 and TRANGTHAI=1";
        int i = 0;
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();

            while (rs.next()) {
                i = rs.getInt(1);
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
        return i;
    }

    public ArrayList<Thuoc> SearchMedicine(String search) {
        String sql = "select * from SANPHAM where MASP like '%" + search + "%' or TENSP like '%" + search + "%'";
        ArrayList<Thuoc> loadName = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMASP(rs.getString(1));
                t.setTENSP(rs.getString(2));
                t.setDVT(rs.getString(3));
                t.setGIA(rs.getFloat(4));
                t.setSOLUONG(rs.getInt(5));
                t.setNHASX(rs.getString(6));
                t.setSOLOSX(rs.getString(7));
                t.setNGAYSX(rs.getString(8));
                t.setHSD(rs.getString(9));
                t.setGIAGOC(rs.getFloat(10));
                t.setMANCC(rs.getString(11));
                t.setTRANGTHAI(rs.getInt(12));
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

    public boolean InsertMedicine(ArrayList<Thuoc> thuoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into SANPHAM values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            for (Thuoc t : thuoc) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(t.getNGAYSX());
                java.util.Date utildate1 = formatter.parse(t.getHSD());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(utildate1.getTime());

                pres.setString(1, t.getMASP());
                pres.setString(2, t.getTENSP());
                pres.setString(3, t.getDVT());
                pres.setInt(5, t.getSOLUONG());
                pres.setFloat(4, t.getGIA());
                pres.setString(6, t.getNHASX());
                pres.setString(7, t.getSOLOSX());
                pres.setDate(8, sqlDate);
                pres.setDate(9, sqlDate1);
                pres.setFloat(10, t.getGIAGOC());
                pres.setString(11, t.getMANCC());
                t.setTRANGTHAI(rs.getInt(12));
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean UpdateMedicine(ArrayList<Thuoc> thuoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Update sanpham set TENSP=?, DVT= ?, GIA= ?, SOLUONG= ?, NHASX= ?, SOLOSX=? ,NGAYSX=? ,HSD=?,GIAGOC=?, MANCC=?, TRANGTHAI=? where MASP=?";

        try {
            for (Thuoc t : thuoc) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(t.getNGAYSX());
                java.util.Date utildate1 = formatter.parse(t.getHSD());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(utildate1.getTime());

                pres.setString(1, t.getTENSP());
                pres.setString(2, t.getDVT());
                pres.setFloat(3, t.getGIA());
                pres.setInt(4, t.getSOLUONG());
                pres.setString(5, t.getNHASX());
                pres.setString(6, t.getSOLOSX());
                pres.setDate(7, sqlDate);
                pres.setDate(8, sqlDate1);
                pres.setString(12, t.getMASP());
                pres.setFloat(9, t.getGIAGOC());
                pres.setString(10, t.getMANCC());
                pres.setInt(11, t.getTRANGTHAI());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteMedicine(ArrayList<Thuoc> thuoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Update sanpham set TRANGTHAI=? where MASP=?";

        try {
            for (Thuoc t : thuoc) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setInt(1, t.getTRANGTHAI());
                pres.setString(2, t.getMASP());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int CheckMedicine(String MASP) {
        String sql = "select TRANGTHAI from SANPHAM where MASP=?";
        int i = 0;
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            pres.setString(1, MASP);
            ResultSet rs=pres.executeQuery();
            while(rs.next()){
                i=rs.getInt(1);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

}
