package BLL;

import DTO.*;
import DAL.*;
import java.util.ArrayList;

public class ThuocBLL {
    ThuocDAL mdal=new ThuocDAL();
    public ArrayList<Thuoc> Load_BLL(){
        return mdal.loadMedicineName();
    }
    public ArrayList<Thuoc> Search_BLL(String search){
        ArrayList<Thuoc> t = new ArrayList<>();
        t=mdal.SearchMedicine(search);
        return t;
    }
    public ArrayList<Thuoc> LoadExpiry_BLL(){
        return mdal.loadExpiryMedicine();
    }
    public ArrayList<Thuoc> LoadExpiry2_BLL(){
        return mdal.loadExpiryMedicine2();
    }
    public int LOadExpiry1_BLL(){
        return mdal.loadExpiryMedicine1();
    }
    public boolean Insert(String TENSP,String DVT,float GIA,int SOLUONG,String NHASX,String SOLOSX,String NGAYSX,String HSD,float GIAGOC,String MANCC, int TRANGTHAI){
        ArrayList<Thuoc> t = new ArrayList<>();
        t=mdal.loadMedicineName();
        int MASP=1;
        for(Thuoc thuoc: t){
            if(Integer.parseInt(thuoc.getMASP())==MASP){
                MASP++;
            }
        }
        String MASP1=String.valueOf(MASP);
        ArrayList<Thuoc> tnew = new ArrayList<>();
        tnew.add( new Thuoc(MASP1, TENSP, DVT, GIA, SOLUONG, NHASX, SOLOSX, NGAYSX, HSD,GIAGOC,MANCC,TRANGTHAI));
        return mdal.InsertMedicine(tnew);
    }
    public boolean Update(String MASP,String TENSP,String DVT,float GIA,int SOLUONG,String NHASX,String SOLOSX,String NGAYSX,String HSD,float GIAGOC,String MANCC,int TRANGTHAI){
        ArrayList<Thuoc> tnew = new ArrayList<>();
        tnew.add( new Thuoc(MASP, TENSP, DVT, GIA, SOLUONG, NHASX, SOLOSX, NGAYSX, HSD,GIAGOC,MANCC,TRANGTHAI));
        return mdal.UpdateMedicine(tnew);
    }
    public boolean Delete(String MASP,String TENSP,String DVT,float GIA,int SOLUONG,String NHASX,String SOLOSX,String NGAYSX,String HSD,float GIAGOC,String MANCC,int TRANGTHAI){
        ArrayList<Thuoc> tnew = new ArrayList<>();
        tnew.add( new Thuoc(MASP, TENSP, DVT, GIA, SOLUONG, NHASX, SOLOSX, NGAYSX, HSD, GIAGOC,MANCC,TRANGTHAI));
        return mdal.DeleteMedicine(tnew);
    }
    public int Check(String MASP){
        return mdal.CheckMedicine(MASP);
    }
            
}
