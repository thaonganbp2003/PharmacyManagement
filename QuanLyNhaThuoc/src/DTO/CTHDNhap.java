package DTO;

public class CTHDNhap {
    private String SOHDN;
    private String MASP;
    private float SOLUONG;
    private float DONGIA;
    private float THUE;
    private float GIAGOC;

    public String getSOHDN() {
        return SOHDN;
    }

    public void setSOHDN(String SOHDN) {
        this.SOHDN = SOHDN;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public float getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(float SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public float getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(float DONGIA) {
        this.DONGIA = DONGIA;
    }

    public float getTHUE() {
        return THUE;
    }

    public void setTHUE(float THUE) {
        this.THUE = THUE;
    }

    public CTHDNhap() {
    }

    public float getGIAGOC() {
        return GIAGOC;
    }

    public void setGIAGOC(float GIAGOC) {
        this.GIAGOC = GIAGOC;
    }

    public CTHDNhap(String SOHDN, String MASP, float SOLUONG, float DONGIA, float THUE, float GIAGOC) {
        this.SOHDN = SOHDN;
        this.MASP = MASP;
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
        this.THUE = THUE;
        this.GIAGOC = GIAGOC;
    }

    
}
