package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import Utils.*;
import java.text.SimpleDateFormat;

public class NhanVienDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    public ArrayList<NhanVien> LoadEmployee(){
        String sql="select * from NHANVIEN order by MANV asc";
        ArrayList<NhanVien> load= new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                NhanVien t = new NhanVien();
                t.setMaNV(rs.getString(1));
                t.setHoten(rs.getString(2));
                t.setGioitinh(rs.getString(3));
                t.setNgaysinh(rs.getString(4));
                t.setEmail(rs.getString(5));
                t.setSdt(rs.getString(6));
                t.setNgayvaolam(rs.getString(7));
                t.setDiachi(rs.getString(8));
                t.setTRANGTHAI(rs.getInt(9));
                load.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return load;
    }
    public ArrayList<NhanVien> SearchEmployee(String search){
        String sql="select * from NHANVIEN where MANV like '%"+search+"%' or HOTEN like '%"+search+"%'";
        ArrayList<NhanVien> load= new ArrayList<>();
        try {
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while(rs.next()){
                NhanVien t = new NhanVien();
                t.setMaNV(rs.getString(1));
                t.setHoten(rs.getString(2));
                t.setGioitinh(rs.getString(3));
                t.setNgaysinh(rs.getString(4));
                t.setEmail(rs.getString(5));
                t.setSdt(rs.getString(6));
                t.setNgayvaolam(rs.getString(7));
                t.setDiachi(rs.getString(8));
                t.setTRANGTHAI(rs.getInt(9));
                load.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return load;
    }
    public boolean InsertEmployee(ArrayList<NhanVien> nhanvien) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Insert into NHANVIEN values(?,?,?,?,?,?,?,?,?)";

        try {
            for (NhanVien t : nhanvien) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(t.getNgaysinh());
                java.util.Date utildate1 = formatter.parse(t.getNgayvaolam());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(utildate1.getTime());

                pres.setString(1, t.getMaNV());
                pres.setString(2, t.getHoten());
                pres.setString(3, t.getGioitinh());
                pres.setDate(4, sqlDate);
                pres.setString(5, t.getEmail());
                pres.setString(6, t.getSdt());
                pres.setDate(7, sqlDate1);
                pres.setString(8, t.getDiachi());
                pres.setInt(9, 1);
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean UpdateEmployee(ArrayList<NhanVien> nhanvien) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Update NHANVIEN set HOTEN=?,GIOITINH=?,NGAYSINH=?,EMAIL=?,SODT=?,NGAYVAOLAM=?,DIACHI=? where MANV=?";
        try {
            for (NhanVien t : nhanvien) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(t.getNgaysinh());
                java.util.Date utildate1 = formatter.parse(t.getNgayvaolam());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(utildate1.getTime());

                pres.setString(8, t.getMaNV());
                pres.setString(1, t.getHoten());
                pres.setString(2, t.getGioitinh());
                pres.setDate(3, sqlDate);
                pres.setString(4, t.getEmail());
                pres.setString(5, t.getSdt());
                pres.setDate(6, sqlDate1);
                pres.setString(7, t.getDiachi());

                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean DeleteEmployee(ArrayList<NhanVien> nhanvien) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "Update NHANVIEN set HOTEN=?,GIOITINH=?,NGAYSINH=?,EMAIL=?,SODT=?,NGAYVAOLAM=?,DIACHI=?, TRANGTHAI=? where MANV=?";
        try {
            for (NhanVien t : nhanvien) {
                dbu = new DBUtils();
                conn = dbu.createConn();
                pres = conn.prepareStatement(sql);

                java.util.Date utildate = formatter.parse(t.getNgaysinh());
                java.util.Date utildate1 = formatter.parse(t.getNgayvaolam());
                java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(utildate1.getTime());

                pres.setString(9, t.getMaNV());
                pres.setString(1, t.getHoten());
                pres.setString(2, t.getGioitinh());
                pres.setDate(3, sqlDate);
                pres.setString(4, t.getEmail());
                pres.setString(5, t.getSdt());
                pres.setDate(6, sqlDate1);
                pres.setString(7, t.getDiachi());
                pres.setInt(8, 0);
                pres.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
