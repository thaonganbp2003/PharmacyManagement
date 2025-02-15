package BLL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import DAL.*;
import Utils.*;
import java.text.SimpleDateFormat;

public class KhachHangBLL {
    KhachHangDAL maAL=new KhachHangDAL();
    public ArrayList<KhachHang> Load_BLL(){
        return maAL.loadCustomer();
    }
    public ArrayList<KhachHang> Search_BLL(String search){
        ArrayList<KhachHang> k =new ArrayList<>();
        k=maAL.SearchCustomer(search);
        return k;
    }
    public boolean Insert(String HOTEN,String SDT,String DIACHI,String DIEMTICHLUY,String EMAIL){
        ArrayList<KhachHang> k =new ArrayList<>();
        k=maAL.loadCustomer();
        int MAKH=1;
        for(KhachHang kh:k){
            if(Integer.parseInt(kh.getMAKH())==MAKH){
                MAKH++;
            }
        }
        String MAKH1=String.valueOf(MAKH);
        ArrayList<KhachHang> knew = new ArrayList<>();
        knew.add(new KhachHang(MAKH1,HOTEN,SDT,DIACHI,DIEMTICHLUY,EMAIL));
        return maAL.InsertCustomer(knew);
    }
    public boolean Update(String MAKH,String HOTEN,String SDT,String DIACHI,String DIEMTICHLUY,String EMAIL){
        String MAKH1=String.valueOf(MAKH);
        ArrayList<KhachHang> knew = new ArrayList<>();
        knew.add(new KhachHang(MAKH,HOTEN,SDT,DIACHI,DIEMTICHLUY,EMAIL));
        return maAL.UpdateCustomer(knew);
    }
    public boolean Delete(String MAKH,String HOTEN,String SDT,String DIACHI,String DIEMTICHLUY,String EMAIL){
        String MAKH1=String.valueOf(MAKH);
        ArrayList<KhachHang> knew = new ArrayList<>();
        knew.add(new KhachHang(MAKH,HOTEN,SDT,DIACHI,DIEMTICHLUY,EMAIL));
        return maAL.DeleteCustomer(knew);
    }
    public boolean Insert_1(String MAKH,String HOTEN,String SODT){
        ArrayList<KhachHang> knew = new ArrayList<>();
        knew.add(new KhachHang(MAKH,HOTEN,SODT,"","",""));
        return maAL.InsertCustomer_1(knew);
    }
}
