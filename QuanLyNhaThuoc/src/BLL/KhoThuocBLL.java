package BLL;

import DTO.*;
import DAL.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class KhoThuocBLL {
    KhoThuocDAL ktdal=new KhoThuocDAL();
    public ArrayList<KhoThuoc> Load_BLL(){
        return ktdal.loadMedicine();
    }
    public ArrayList<KhoThuoc> Search_BLL(String search){
        ArrayList<KhoThuoc> t = new ArrayList<>();
        t=ktdal.SearchMedicine(search);
        return t;
    }
    public boolean Insert(String MASP2,String TENSP,String DVT,float GIA,int SOLUONG,String NHASX,String SOLOSX,String NGAYSX,String HSD,float GIAGOC, String MANCC){
        ArrayList<KhoThuoc> t = new ArrayList<>();
        t=ktdal.loadMedicine();
        int MASP=1;
        for(KhoThuoc thuoc: t){
            if(Integer.parseInt(thuoc.getMALO())==MASP){
                MASP++;
            }
        }
        String MASP1=String.valueOf(MASP);
        ArrayList<KhoThuoc> tnew = new ArrayList<>();
        tnew.add( new KhoThuoc(MASP1,MASP2, TENSP, DVT, GIA, SOLUONG, NHASX, SOLOSX, NGAYSX, HSD,GIAGOC, MANCC));
        return ktdal.InsertMedicine(tnew);
    }
    public boolean Delete(String MASP2,String TENSP,String DVT,float GIA,int SOLUONG,String NHASX,String SOLOSX,String NGAYSX,String HSD,float GIAGOC, String MANCC){
        ArrayList<KhoThuoc> t = new ArrayList<>();
        ArrayList<KhoThuoc> tnew = new ArrayList<>();
        tnew.add( new KhoThuoc(MASP2,MASP2, TENSP, DVT, GIA, SOLUONG, NHASX, SOLOSX, NGAYSX, HSD,GIAGOC, MANCC));
        return ktdal.DeleteMedicine(MASP2,SOLOSX);
    }
}
