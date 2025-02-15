package DTO;

public class KhachHang {
    private String MAKH;
    private String HOTEN;
    private String SODT;
    private String DIACHI;
    private String DIEMTICHLUY;
    private String EMAIL;

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getSODT() {
        return SODT;
    }

    public void setSODT(String SODT) {
        this.SODT = SODT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getDIEMTICHLUY() {
        return DIEMTICHLUY;
    }

    public void setDIEMTICHLUY(String DIEMTICHLUY) {
        this.DIEMTICHLUY = DIEMTICHLUY;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public KhachHang(String MAKH, String HOTEN, String SODT, String DIACHI, String DIEMTICHLUY, String EMAIL) {
        this.MAKH = MAKH;
        this.HOTEN = HOTEN;
        this.SODT = SODT;
        this.DIACHI = DIACHI;
        this.DIEMTICHLUY = DIEMTICHLUY;
        this.EMAIL = EMAIL;
    }

    public KhachHang() {
    }
    
}
