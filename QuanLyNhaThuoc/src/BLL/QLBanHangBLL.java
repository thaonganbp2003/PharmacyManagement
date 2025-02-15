package BLL;

import DTO.*;
import DAL.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class QLBanHangBLL {

    QLBanHangDAL hangDAL = new QLBanHangDAL();
    KhachHangBLL cbll = new KhachHangBLL();

    public ArrayList<HoaDon> Load_BLL() {
        return hangDAL.LoadInvoice();
    }

    public boolean Insert_HOADON(String SOHD, String NGHD, String MANV, String MAKH, float TRIGIA, String phone, String HOTEN) {
        ArrayList<KhachHang> k = new ArrayList<>();
        ArrayList<HoaDon> hnew = new ArrayList<>();
        k = cbll.Load_BLL();
        int MAKH1 = 1;
        int check = 0;
        for (KhachHang kh : k) {
            if (kh.getSODT().equals(phone)) {
                hnew.add(new HoaDon(SOHD, NGHD, MANV, kh.getMAKH(), TRIGIA));
                check++;
                return hangDAL.InsertHoaDon(hnew);
            } 
        }
        for(KhachHang kh: k){
            if(Integer.parseInt(kh.getMAKH())==MAKH1){
                MAKH1++;
            }
        }
        if (check == 0) {
            cbll.Insert_1(String.valueOf(MAKH1), HOTEN, phone);
            hnew.add(new HoaDon(SOHD, NGHD, MANV, String.valueOf(MAKH1), TRIGIA));
            return hangDAL.InsertHoaDon(hnew);
        }
        return false;
    }

    public boolean Insert_CTHD(String SOHD, String MASP, float SOLUONG, float DONGIA, float THUE) {
        ArrayList<CTHD> cnew = new ArrayList<>();
        cnew.add(new CTHD(SOHD, MASP, SOLUONG, DONGIA, THUE));
        return hangDAL.InsertCTHD(cnew);
    }

    public boolean Update_HOADON(String SOHD, String NGHD, String MANV, String MAKH, float TRIGIA, String phone) {
        ArrayList<HoaDon> hnew = new ArrayList<>();
        hnew.add(new HoaDon(SOHD, NGHD, MANV, MAKH, TRIGIA));
        return hangDAL.UpdateHoaDon(hnew);
    }

}
