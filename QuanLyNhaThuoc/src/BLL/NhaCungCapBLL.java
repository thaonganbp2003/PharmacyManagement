/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author THAONGAN
 */
import DTO.*;
import DAL.*;
import java.util.ArrayList;

public class NhaCungCapBLL {
    
    NhaCungCapDAL NCC = new NhaCungCapDAL();

    public ArrayList<NhaCungCap> dsNhaCungCap() {
        return NCC.dsNhaCungCap();
    }

    public int insertNCC(NhaCungCap ncc) {
        int result = NCC.insertNCC(ncc);
        return result;
    }

    public int updateNCC(NhaCungCap ncc) {
        int result = NCC.updateNCC(ncc);
        return result;
    }

    public int deleteNCC(NhaCungCap ncc) {
        int result = NCC.deleteNCC(ncc);
        return result;
    }
    
    public ArrayList<NhaCungCap> TimKiemNCC (String info){
        ArrayList <NhaCungCap> dsNhaCungCap = new ArrayList<NhaCungCap>();
        dsNhaCungCap = NCC.TimKiemNCC(info);
        return dsNhaCungCap;
    }
    
}
