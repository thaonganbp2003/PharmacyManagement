/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class CTHD {
    private String SOHD;
    private String MASP;
    private float SOLUONG;
    private float DONGIA;
    private float THUE;

    public String getSOHD() {
        return SOHD;
    }

    public void setSOHD(String SOHD) {
        this.SOHD = SOHD;
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

    public CTHD() {
    }

    public CTHD(String SOHD, String MASP, float SOLUONG, float DONGIA, float THUE) {
        this.SOHD = SOHD;
        this.MASP = MASP;
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
        this.THUE = THUE;
    }
    
}
