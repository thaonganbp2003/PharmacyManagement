
package BLL;

import DTO.*;
import DAL.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class QLNhapHangBLL {
    QLNhapHangDAL nhapHangDAL=new QLNhapHangDAL();
    public ArrayList<HoaDonNhap> Load_BLL() {
        return nhapHangDAL.LoadInvoice();
    }
    public boolean Insert_HOADON(String SOHDN, String NGHDN, String MANV, String MANCC) {
        ArrayList<HoaDonNhap> hnew = new ArrayList<>();
        hnew.add(new HoaDonNhap(SOHDN,NGHDN,MANV,MANCC,0));
        return nhapHangDAL.InsertHoaDon(hnew);
    }
    public boolean Insert_CTHD(String SOHDN, String MASP, float SOLUONG, float DONGIA, float THUE,float GIAGOC) {
        ArrayList<CTHDNhap> cnew = new ArrayList<>();
        cnew.add(new CTHDNhap(SOHDN, MASP, SOLUONG, DONGIA, 0,GIAGOC));
        return nhapHangDAL.InsertCTHD(cnew);
    }
    public boolean Update_HOADON(String SOHDN, String NGHDN, String MANV, String MANCC, float TRIGIA) {
        ArrayList<HoaDonNhap> hnew = new ArrayList<>();
        hnew.add(new HoaDonNhap(SOHDN, NGHDN, MANV, MANCC,TRIGIA));
        return nhapHangDAL.UpdateHoaDon(hnew);
    }
}
