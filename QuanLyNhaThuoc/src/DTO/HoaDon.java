
package DTO;

public class HoaDon {
    private String SOHD;
    private String NGHD;
    private String MANV;
    private String MAKH;
    private float TRIGIA;


    public String getSOHD() {
        return SOHD;
    }

    public void setSOHD(String SOHD) {
        this.SOHD = SOHD;
    }

    public String getNGHD() {
        return NGHD;
    }

    public void setNGHD(String NGHD) {
        this.NGHD = NGHD;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public float getTRIGIA() {
        return TRIGIA;
    }

    public void setTRIGIA(float TRIGIA) {
        this.TRIGIA = TRIGIA;
    }


    public HoaDon(String SOHD, String NGHD, String MANV, String MAKH, float TRIGIA) {
        this.SOHD = SOHD;
        this.NGHD = NGHD;
        this.MANV = MANV;
        this.MAKH = MAKH;
        this.TRIGIA = TRIGIA;
    }

    public HoaDon() {
    }


    
    
    
}
