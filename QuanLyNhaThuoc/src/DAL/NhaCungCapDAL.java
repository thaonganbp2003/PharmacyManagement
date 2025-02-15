package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;

public class NhaCungCapDAL {

    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public ArrayList<NhaCungCap> dsNhaCungCap() {
        String sql = "select * from NHACUNGCAP order by MANCC asc";
        ArrayList<NhaCungCap> dsNhaCungCap = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSoDT(rs.getString(3));
                ncc.setEmail(rs.getString(4));
                ncc.setDiachi(rs.getString(5));
                
                dsNhaCungCap.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in NhaCungCapDAL");
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsNhaCungCap;
    }

    public int insertNCC(NhaCungCap ncc) {
        int result = 0;
        String sqlInsert = "INSERT INTO NHACUNGCAP VALUES (?, ?, ?, ?, ?)";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlInsert);
            pres.setInt(1, ncc.getMaNCC());
            pres.setString(2,ncc.getTenNCC());
            pres.setString(3, ncc.getSoDT());
            pres.setString(4, ncc.getEmail());
            pres.setString(5, ncc.getDiachi());
       
            result = pres.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int updateNCC(NhaCungCap ncc) {
        int result = 0;
        String sqlUpdate = "UPDATE NHACUNGCAP SET TENNCC = ?, SODT = ?,EMAIL = ?,DIACHI = ? WHERE MANCC = ?";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlUpdate);

            pres.setString(1, ncc.getTenNCC());
            pres.setString(2, ncc.getSoDT());
            pres.setString(3, ncc.getEmail());
            pres.setString(4, ncc.getDiachi());
            pres.setInt(6, ncc.getMaNCC());
            
            result = pres.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int deleteNCC(NhaCungCap ncc) {
        int result = 0;
        String sqlDelete = "DELETE FROM NHACUNGCAP WHERE MANCC = ?";
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlDelete);
            pres.setInt(1, ncc.getMaNCC());
            result = pres.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
     public ArrayList<NhaCungCap> TimKiemNCC(String info) {
        String sql = "select * from NHACUNGCAP where MANCC like '%"+info+"%' or TENNCC like '%"+info+"%'";
        ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSoDT(rs.getString(3));
                ncc.setEmail(rs.getString(4));
                ncc.setDiachi(rs.getString(5));
                
                dsNCC.add(ncc);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                pres.close();
                rs.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return dsNCC;
    }

}
