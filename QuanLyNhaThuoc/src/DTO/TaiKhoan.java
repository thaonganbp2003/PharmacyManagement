/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author THAONGAN
 */
public class TaiKhoan {
    private String TenTaiKhoan;
    private String MatKhau;
    private int VaiTro;
    private int MaNV;

    

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String TenTaiKhoan) {
        this.TenTaiKhoan = TenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(int VaiTro) {
        this.VaiTro = VaiTro;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
   

    public TaiKhoan() {
//        this.TenTaiKhoan = null;
//        this.MatKhau = null;
//        this.VaiTro = 0;
//        this.MaNV = 0;
    }

    public TaiKhoan(String TenTaiKhoan, String MatKhau, int VaiTro, int MaNV) {
        this.TenTaiKhoan = TenTaiKhoan;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.MaNV = MaNV;
    }

    public TaiKhoan(String TenTaiKhoan, String MatKhau) {
        this.TenTaiKhoan = TenTaiKhoan;
        this.MatKhau = MatKhau;
    }

    

    
}
