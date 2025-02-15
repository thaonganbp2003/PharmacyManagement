package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;

public class KhachHangDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    public ArrayList<KhachHang> loadCustomer(){
        String sql="select * from KHACHHANG order by MAKH asc";
        ArrayList<KhachHang> load= new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                KhachHang t = new KhachHang();
                t.setMAKH(rs.getString(1));
                t.setHOTEN(rs.getString(2));
                t.setSODT(rs.getString(3));
                t.setDIACHI(rs.getString(4));
                t.setDIEMTICHLUY(rs.getString(5));
                t.setEMAIL(rs.getString(6));
                load.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return load;
    }
    public ArrayList<KhachHang> SearchCustomer(String search){
        String sql="select * from KHACHHANG where MAKH like '%"+search+"%' or HOTEN like '%"+search+"%'";
        ArrayList<KhachHang> load= new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                KhachHang t = new KhachHang();
                t.setMAKH(rs.getString(1));
                t.setHOTEN(rs.getString(2));
                t.setSODT(rs.getString(3));
                t.setDIACHI(rs.getString(4));
                t.setDIEMTICHLUY(rs.getString(5));
                t.setEMAIL(rs.getString(6));
                load.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return load;
    }
    public boolean InsertCustomer(ArrayList<KhachHang> khach) {
        String sql = "Insert into KHACHHANG values(?,?,?,?,?,?)";

        try {
            for (KhachHang t : khach) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, t.getMAKH());
                pres.setString(2, t.getHOTEN());
                pres.setString(3, t.getSODT());
                pres.setString(4, t.getDIACHI());
                pres.setString(5, t.getDIEMTICHLUY());
                pres.setString(6, t.getEMAIL());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean UpdateCustomer(ArrayList<KhachHang> khach) {
        String sql = "Update KHACHHANG set HOTEN= ?,SODT= ?,DIACHI= ?,DIEMTICHLUY= ?,EMAIL= ? where MAKH=?";

        try {
            for (KhachHang t : khach) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, t.getHOTEN());
                pres.setString(2, t.getSODT());
                pres.setString(3, t.getDIACHI());
                pres.setString(4, t.getDIEMTICHLUY());
                pres.setString(5, t.getEMAIL());
                pres.setString(6, t.getMAKH());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteCustomer(ArrayList<KhachHang> khach) {
        String sql ="Delete from KHACHHANG where MAKH = ?";

        try {
            for (KhachHang t : khach) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, t.getMAKH());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean InsertCustomer_1(ArrayList<KhachHang> khach) {
        String sql ="Insert into KHACHHANG(MAKH,HOTEN,SODT) values(?,?,?)";

        try {
            for (KhachHang t : khach) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                pres.setString(1, t.getMAKH());
                pres.setString(2, t.getHOTEN());
                pres.setString(3, t.getSODT());
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
