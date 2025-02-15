package BLL;

import DTO.*;
import DAL.*;
import java.util.ArrayList;

public class NhanVienBLL {
    NhanVienDAL edal=new NhanVienDAL();
    TaiKhoanBLL tkbll=new TaiKhoanBLL();
    public ArrayList<NhanVien> Load_BLL(){
        return edal.LoadEmployee();
    }
    public ArrayList<NhanVien> Search_BLL(String search){
        ArrayList<NhanVien> n =new ArrayList<>();
        n=edal.SearchEmployee(search);
        return n;
    }
    
    public boolean Insert(String HOTEN,String GIOITINH,String NGAYSINH, String EMAIL,String SODT,String NGAYVAOLAM,String DIACHI, int TRANGTHAI, String username, String password,int vaitro){
        ArrayList<NhanVien> n =new ArrayList<>();
        n=edal.LoadEmployee();
        int MANV=1;
        for(NhanVien nhanvien : n){
            if(Integer.parseInt(nhanvien.getMaNV())== MANV){
                MANV++;
            }
            for(NhanVien k : n){
                if(Integer.parseInt(k.getMaNV())== MANV){
                MANV++;
            }
            } 
        }
        String MANV1=String.valueOf(4);
        ArrayList<NhanVien> nnew =new ArrayList<>();
        nnew.add(new NhanVien(MANV1,HOTEN,GIOITINH,NGAYSINH,EMAIL,SODT,NGAYVAOLAM,DIACHI,TRANGTHAI));
        edal.InsertEmployee(nnew);
        return tkbll.Insert(username, password, vaitro, MANV);
    }
    public boolean Update(String MANV,String HOTEN,String GIOITINH,String NGAYSINH, String EMAIL,String SODT,String NGAYVAOLAM,String DIACHI,int TRANGTHAI){
        ArrayList<NhanVien> nnew =new ArrayList<>();
        nnew.add(new NhanVien(MANV,HOTEN,GIOITINH,NGAYSINH,EMAIL,SODT,NGAYVAOLAM,DIACHI,TRANGTHAI));
        return edal.UpdateEmployee(nnew);
    }
    public boolean Delete(String MANV,String HOTEN,String GIOITINH,String NGAYSINH, String EMAIL,String SODT,String NGAYVAOLAM,String DIACHI, int TRANGTHAI){
        ArrayList<NhanVien> nnew =new ArrayList<>();
        nnew.add(new NhanVien(MANV,HOTEN,GIOITINH,NGAYSINH,EMAIL,SODT,NGAYVAOLAM,DIACHI,TRANGTHAI));
        return edal.DeleteEmployee(nnew);
    }
}
