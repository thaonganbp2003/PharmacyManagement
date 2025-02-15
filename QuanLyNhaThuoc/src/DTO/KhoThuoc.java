package DTO;

public class KhoThuoc {
    private String MALO;
    private String MASP;
    private String TENSP;
    private String DVT; 
    private float GIA;
    private int SOLUONG;
    private String NHASX;
    private String SOLOSX;
    private String NGAYSX;
    private String HSD;
    private float GIAGOC;
    private String MANCC;

    public String getMALO() {
        return MALO;
    }

    public void setMALO(String MALO) {
        this.MALO = MALO;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public float getGIA() {
        return GIA;
    }

    public void setGIA(float GIA) {
        this.GIA = GIA;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public String getNHASX() {
        return NHASX;
    }

    public void setNHASX(String NHASX) {
        this.NHASX = NHASX;
    }

    public String getSOLOSX() {
        return SOLOSX;
    }

    public void setSOLOSX(String SOLOSX) {
        this.SOLOSX = SOLOSX;
    }

    public String getNGAYSX() {
        return NGAYSX;
    }

    public void setNGAYSX(String NGAYSX) {
        this.NGAYSX = NGAYSX;
    }

    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }

    public float getGIAGOC() {
        return GIAGOC;
    }

    public void setGIAGOC(float GIAGOC) {
        this.GIAGOC = GIAGOC;
    }

    public String getMANCC() {
        return MANCC;
    }

    public void setMANCC(String MANCC) {
        this.MANCC = MANCC;
    }

    public KhoThuoc() {
    }

    public KhoThuoc(String MALO, String MASP, String TENSP, String DVT, float GIA, int SOLUONG, String NHASX, String SOLOSX, String NGAYSX, String HSD, float GIAGOC, String MANCC) {
        this.MALO = MALO;
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.DVT = DVT;
        this.GIA = GIA;
        this.SOLUONG = SOLUONG;
        this.NHASX = NHASX;
        this.SOLOSX = SOLOSX;
        this.NGAYSX = NGAYSX;
        this.HSD = HSD;
        this.GIAGOC = GIAGOC;
        this.MANCC = MANCC;
    }
    
}
