/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DTO.*;
import DAL.*;
import java.util.ArrayList;
/**
 *
 * @author THAONGAN
 */
public class TaiKhoanBLL {
     TaiKhoanDAL TK = new TaiKhoanDAL();
    
    public ArrayList<TaiKhoan> dsTaiKhoan(){
        return TK.dsTaiKhoan();
    }
    public ArrayList<TaiKhoan> TìmTaiKhoan(String MANV){
        return TK.TimTaiKhoan(MANV);
    }
    
    public TaiKhoan dangNhapBLL (String userName,String passWord){
        TaiKhoan tk = TK.dangNhap(userName, passWord);
        return tk;
    }
    
    public int GetVaiTro(TaiKhoan tk){
        return tk.getVaiTro();
    }
    public boolean Insert(String usernameString, String password, int VAITRO,int MANV){
        ArrayList<TaiKhoan> tk = new ArrayList<>();
        tk.add(new TaiKhoan(usernameString, password, VAITRO,MANV));
        return TK.Insert(tk);
    }
}
