package DTO;

public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String soDT;
    private String email;
    private String diachi;

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public NhaCungCap() {
    }

    public NhaCungCap(int maNCC, String tenNCC, String soDT, String email, String diachi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.soDT = soDT;
        this.email = email;
        this.diachi = diachi;
    }
    
    
    
}
