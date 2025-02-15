package DTO;

public class HoaDonNhap {
    private String SOHDN;
    private String NGHDN;
    private String MANV;
    private String MANCC;
    private float TRIGIA;

    public HoaDonNhap(String SOHDN, String NGHDN, String MANV, String MANCC, float TRIGIA) {
        this.SOHDN = SOHDN;
        this.NGHDN = NGHDN;
        this.MANV = MANV;
        this.MANCC = MANCC;
        this.TRIGIA = TRIGIA;
    }

    public HoaDonNhap() {
    }

    public String getSOHDN() {
        return SOHDN;
    }

    public void setSOHDN(String SOHDN) {
        this.SOHDN = SOHDN;
    }

    public String getNGHDN() {
        return NGHDN;
    }

    public void setNGHDN(String NGHDN) {
        this.NGHDN = NGHDN;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMANCC() {
        return MANCC;
    }

    public void setMANCC(String MANCC) {
        this.MANCC = MANCC;
    }

    public float getTRIGIA() {
        return TRIGIA;
    }

    public void setTRIGIA(float TRIGIA) {
        this.TRIGIA = TRIGIA;
    }
    
}
