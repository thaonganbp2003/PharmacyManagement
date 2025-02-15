package GUI;

import BLL.QLBanHangBLL;
import DAL.ThuocDAL;
import Utils.DBUtils;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import BLL.*;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.Document;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileOutputStream;
import java.text.DateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class QLNhapHang extends javax.swing.JFrame {

    /**
     * Creates new form EmpDashBoard
     */
    public QLNhapHang() {
        initComponents();
        setLocationRelativeTo(null);
        HienThiNgay();
        HienThiSOHD();
        HienThiThongtin();
        HienThiNhaCungCap(nccbll.dsNhaCungCap());
        txtpayment.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                cal();
            }

            public void cal() {
                if (!txttongtien.getText().equals("")) {
                    float tien = Float.parseFloat(txtpayment.getText());
                    float tien1 = Float.parseFloat(txttongtien.getText());
                    float result = tien - tien1;
                    txtchange.setText(String.valueOf(result));
                }
            }

        });

    }
    DefaultTableModel tblModel = new DefaultTableModel();
    ThuocBLL mbll = new ThuocBLL();
    NhanVienBLL ebll = new NhanVienBLL();
    NhaCungCapBLL nccbll = new NhaCungCapBLL();
    KhoThuocBLL ktbll = new KhoThuocBLL();
    QLNhapHangBLL bLL = new QLNhapHangBLL();
    private float tongtien = 0;
    private String VAITRO = DangNhap.frmInstance.txt1;

    public void HienThiHoaDonNhap() {
        try {
            DefaultTableModel tblModel1 = new DefaultTableModel();
            Connection conn = new DBUtils().createConn();
            String sql = """
                          select hd.SOHDN,count(distinct ct.MASP), hd.NGHDN,hd.MANV,hd.MANCC, hd.TRIGIA 
                         from HOADONNHAP hd, CTHDNHAP ct where hd.SOHDN=ct.SOHDN
                         group by hd.SOHDN, hd.NGHDN,hd.MANV,hd.MANCC,hd.TRIGIA
                         order by hd.SOHDN asc""";
            PreparedStatement pres = conn.prepareStatement(sql);
            ResultSet rs = pres.executeQuery();
            String title[] = {"Mã hóa đơn", "Số thuốc", "Ngày lập", "Mã nhân viên", "Mã nhà cung cấp", "Tổng tiền"};
            tblModel1.setColumnIdentifiers(title);
            while (rs.next()) {
                String SOHDN = rs.getString(1);
                String MASP = rs.getString(2);
                String NGHDN = rs.getString(3);
                String MANV = rs.getString(4);
                String MANCC = rs.getString(5);
                String TRIGIA = rs.getString(6);
                Date date1 = convertStringtoDate(NGHDN, "yyyy-MM-dd");
                String result1 = convertDatetoString(date1, "dd-MM-yyyy");
                Object row[] = {SOHDN, MASP, result1, MANV, MANCC, TRIGIA};
                tblModel1.addRow(row);
            }
            jTable3.setModel(tblModel1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TimKiemHoaDonNhap(String search, int i) {
        try {
            DefaultTableModel tblModel1 = new DefaultTableModel();
            String title[] = {"Mã hóa đơn", "Số thuốc", "Ngày lập", "Mã nhân viên", "Mã nhà cung cấp", "Tổng tiền"};
            tblModel1.setColumnIdentifiers(title);
            Connection conn = new DBUtils().createConn();
            String sql;
            if (i == 0) {
                sql = "select hd.SOHDN,count(distinct ct.MASP), hd.NGHDN,hd.MANV,hd.MANCC, hd.TRIGIA \n"
                        + "                         from HOADONNHAP hd, CTHDNHAP ct where hd.SOHDN=ct.SOHDN and ( hd.NGHDN like to_date('" + search + "','DD-MM-YYYY') )\n"
                        + "                         group by hd.SOHDN, hd.NGHDN,hd.MANV,hd.MANCC,hd.TRIGIA";
                PreparedStatement pres = conn.prepareStatement(sql);
                ResultSet rs = pres.executeQuery();
                while (rs.next()) {
                    String SOHDN = rs.getString(1);
                    String MASP = rs.getString(2);
                    String NGHDN = rs.getString(3);
                    String MANV = rs.getString(4);
                    String MANCC = rs.getString(5);
                    String TRIGIA = rs.getString(6);
                    Date date1 = convertStringtoDate(NGHDN, "yyyy-MM-dd");
                    String result1 = convertDatetoString(date1, "dd-MM-yyyy");
                    Object row[] = {SOHDN, MASP, result1, MANV, MANCC, TRIGIA};
                    tblModel1.addRow(row);
                }
            } else if (i == 1) {
                sql = "select hd.SOHDN,count(distinct ct.MASP), hd.NGHDN,hd.MANV,hd.MANCC, hd.TRIGIA \n"
                        + "                         from HOADONNHAP hd, CTHDNHAP ct where hd.SOHDN=ct.SOHDN and (hd.SOHDN like '%" + search + "%')\n"
                        + "                         group by hd.SOHDN, hd.NGHDN,hd.MANV,hd.MANCC,hd.TRIGIA";
                PreparedStatement pres = conn.prepareStatement(sql);
                ResultSet rs = pres.executeQuery();
                while (rs.next()) {
                    String SOHDN = rs.getString(1);
                    String MASP = rs.getString(2);
                    String NGHDN = rs.getString(3);
                    String MANV = rs.getString(4);
                    String MANCC = rs.getString(5);
                    String TRIGIA = rs.getString(6);
                    Date date1 = convertStringtoDate(NGHDN, "yyyy-MM-dd");
                    String result1 = convertDatetoString(date1, "dd-MM-yyyy");
                    Object row[] = {SOHDN, MASP, result1, MANV, MANCC, TRIGIA};
                    tblModel1.addRow(row);
                }
            }

            jTable3.setModel(tblModel1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HienThiNhaCungCap(ArrayList<NhaCungCap> dsnhacungcap) {
        NhaCungCap t = new NhaCungCap();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (int i = 0; i < dsnhacungcap.size(); i++) {
            t = dsnhacungcap.get(i);
            tenncc.addItem(t.getTenNCC());
        }
    }

    public void TimNhaCungCap(ArrayList<NhaCungCap> dsnhacungcap) {
        try {
            NhaCungCap a = new NhaCungCap();
            for (int i = 0; i < dsnhacungcap.size(); i++) {
                a = dsnhacungcap.get(i);
                int id = a.getMaNCC();
                MANCC.setText(String.valueOf(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HienThiNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
        ngaylapHD.setText(date);
    }

    public void HienThiThongtin() {
        Connection conn = new DBUtils().createConn();
        String sql = "select nv.MANV, nv.HOTEN from NHANVIEN nv, TAIKHOAN tk where nv.MANV=tk.MANV and tk.VAITRO=?";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, VAITRO);
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                txtMaNV.setText(rs.getString(1));
                txtName.setText(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void HienThiSOHD() {
        try {
            Connection con = new DBUtils().createConn();
            String sql = "select SOHDN from HOADONNHAP";
            PreparedStatement pres = con.prepareStatement(sql);
            ResultSet rs = pres.executeQuery();
            int i = 1;
            while (rs.next()) {
                if (i == rs.getInt(1)) {
                    i++;
                }
            }
            String sohd = String.valueOf(i);
            txtSoHD.setText(sohd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HienThiTENSP(ArrayList<Thuoc> dsthuoc) {
        try {
            Thuoc t = new Thuoc();
            TenSP.removeAllItems();
            for (int i = 0; i < dsthuoc.size(); i++) {
                t = dsthuoc.get(i);
                TenSP.addItem(t.getTENSP());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HienThiThuoc(ArrayList<Thuoc> dsthuoc) {
        try {
            DefaultTableModel tbl2 = new DefaultTableModel();
            String title[] = {"Mã thuốc", "Tên thuốc", "Số lượng còn lại", "Hạn sử dụng"};
            tbl2.setColumnIdentifiers(title);
            Thuoc t = new Thuoc();
            for (int i = 0; i < dsthuoc.size(); i++) {
                t = dsthuoc.get(i);
                String MASP = t.getMASP();
                String TENSP = t.getTENSP();
                int SOLUONG = t.getSOLUONG();
                String HSD = t.getHSD();
                Date date1 = convertStringtoDate(HSD, "yyyy-MM-dd");
                String result1 = convertDatetoString(date1, "dd-MM-yyyy");
                Object row[] = {MASP, TENSP, SOLUONG, result1};
                tbl2.addRow(row);
            }
            jTable2.setModel(tbl2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadMedicineForm(ArrayList<Thuoc> dsthuoc) {
        try {
            Thuoc t = new Thuoc();
            for (int i = 0; i < dsthuoc.size(); i++) {
                t = dsthuoc.get(i);
                String MASP = t.getMASP();
                float GIA = t.getGIA();

                ID.setText(MASP);
                DVT.setText(t.getDVT());
                NHASX.setText(t.getNHASX());
                if (t.getTRANGTHAI() == 0) {
                    trangthai.setSelectedItem("Ngừng bán");
                } else if (t.getTRANGTHAI() == 1) {
                    trangthai.setSelectedItem("Đang bán");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String convertDatetoString(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public Date convertStringtoDate(String dateinString, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Date startDate = null;
        try {
            startDate = df.parse(dateinString);
            String newDateString = df.format(startDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startDate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datecreatebill = new com.raven.datechooser.DateChooser();
        ngaysx = new com.raven.datechooser.DateChooser();
        hsd = new com.raven.datechooser.DateChooser();
        ngaysx1 = new com.raven.datechooser.DateChooser();
        hsd1 = new com.raven.datechooser.DateChooser();
        mainpanel = new javax.swing.JPanel();
        GradientPaneltop = new keeptoo.KGradientPanel();
        lbsohd = new javax.swing.JLabel();
        txtSoHD = new javax.swing.JTextField();
        lbtenNV = new javax.swing.JLabel();
        lbmaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lbDate = new javax.swing.JLabel();
        ngaylapHD = new javax.swing.JTextField();
        calendarchoose = new javax.swing.JButton();
        lbtenNV1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        tenncc = new javax.swing.JComboBox<>();
        MANCC = new javax.swing.JTextField();
        lbtenNV2 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnBack = new keeptoo.Mybutton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelCaculate = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        mybutton3Gradient1 = new keeptoo.Mybutton3Gradient();
        jLabel2 = new javax.swing.JLabel();
        mybutton3Gradient2 = new keeptoo.Mybutton3Gradient();
        jLabel6 = new javax.swing.JLabel();
        mybutton3Gradient3 = new keeptoo.Mybutton3Gradient();
        panelReset2 = new keeptoo.KGradientPanel();
        panelReset3 = new keeptoo.KGradientPanel();
        mybutton24 = new keeptoo.Mybutton2();
        btnsave3 = new keeptoo.Mybutton2();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelMedInform = new keeptoo.KGradientPanel();
        panelMedInform1 = new keeptoo.KGradientPanel();
        txtdelete = new javax.swing.JTextField();
        Delete = new keeptoo.Mybutton2();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelMedInform14 = new keeptoo.KGradientPanel();
        panelMedInform15 = new keeptoo.KGradientPanel();
        panelMedInform16 = new keeptoo.KGradientPanel();
        panelMedInform17 = new keeptoo.KGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        mybutton28 = new keeptoo.Mybutton2();
        jLabel25 = new javax.swing.JLabel();
        btnsave = new keeptoo.Mybutton2();
        panelMedInform18 = new keeptoo.KGradientPanel();
        panelMedInform19 = new keeptoo.KGradientPanel();
        panelMedInform20 = new keeptoo.KGradientPanel();
        panelMedInform21 = new keeptoo.KGradientPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        mybutton30 = new keeptoo.Mybutton2();
        jLabel29 = new javax.swing.JLabel();
        mybutton31 = new keeptoo.Mybutton2();
        panelMedInform2 = new keeptoo.KGradientPanel();
        panelMedInform3 = new keeptoo.KGradientPanel();
        panelMedInform4 = new keeptoo.KGradientPanel();
        panelMedInform5 = new keeptoo.KGradientPanel();
        txtchange = new javax.swing.JTextField();
        txtpayment = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Printbutton = new keeptoo.Mybutton2();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        availablemedicine = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        panelMedInform22 = new keeptoo.KGradientPanel();
        panelMedInform23 = new keeptoo.KGradientPanel();
        panelMedInform24 = new keeptoo.KGradientPanel();
        panelMedInform25 = new keeptoo.KGradientPanel();
        panelMedInform26 = new keeptoo.KGradientPanel();
        panelMedInform27 = new keeptoo.KGradientPanel();
        panelMedInform28 = new keeptoo.KGradientPanel();
        panelMedInform29 = new keeptoo.KGradientPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        mybutton32 = new keeptoo.Mybutton2();
        jLabel35 = new javax.swing.JLabel();
        mybutton33 = new keeptoo.Mybutton2();
        TenSP = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        GIAGOC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        DVT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        SOLUONG = new javax.swing.JTextField();
        NGAYSX = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        HSD = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        NHASX = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        SOLOSX = new javax.swing.JTextField();
        btnsave1 = new keeptoo.Mybutton2();
        jLabel23 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        btnHSD = new javax.swing.JButton();
        btnNgaysx = new javax.swing.JButton();
        trangthai = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        newmedicine = new javax.swing.JPanel();
        panelMedInform30 = new keeptoo.KGradientPanel();
        panelMedInform31 = new keeptoo.KGradientPanel();
        panelMedInform32 = new keeptoo.KGradientPanel();
        panelMedInform33 = new keeptoo.KGradientPanel();
        panelMedInform34 = new keeptoo.KGradientPanel();
        panelMedInform35 = new keeptoo.KGradientPanel();
        panelMedInform36 = new keeptoo.KGradientPanel();
        panelMedInform37 = new keeptoo.KGradientPanel();
        jLabel36 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        mybutton34 = new keeptoo.Mybutton2();
        jLabel39 = new javax.swing.JLabel();
        mybutton35 = new keeptoo.Mybutton2();
        jLabel30 = new javax.swing.JLabel();
        GIAGOC1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        DVT1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        SOLUONG1 = new javax.swing.JTextField();
        NGAYSX1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        HSD1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        NHASX1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        SOLOSX1 = new javax.swing.JTextField();
        btnsave2 = new keeptoo.Mybutton2();
        jLabel45 = new javax.swing.JLabel();
        TenSP1 = new javax.swing.JTextField();
        btnHSD1 = new javax.swing.JButton();
        btnNgaysx1 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jPanel5 = new javax.swing.JPanel();
        panelMedInform38 = new keeptoo.KGradientPanel();
        panelMedInform39 = new keeptoo.KGradientPanel();
        panelMedInform40 = new keeptoo.KGradientPanel();
        panelMedInform41 = new keeptoo.KGradientPanel();
        panelMedInform42 = new keeptoo.KGradientPanel();
        panelMedInform43 = new keeptoo.KGradientPanel();
        panelMedInform44 = new keeptoo.KGradientPanel();
        panelMedInform45 = new keeptoo.KGradientPanel();
        jLabel48 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        mybutton36 = new keeptoo.Mybutton2();
        jLabel51 = new javax.swing.JLabel();
        mybutton37 = new keeptoo.Mybutton2();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        btnsearch = new javax.swing.JButton();
        btnsearch1 = new javax.swing.JButton();
        txtsearch1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();

        datecreatebill.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        datecreatebill.setTextRefernce(ngaylapHD);

        ngaysx.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ngaysx.setTextRefernce(NGAYSX);

        hsd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        hsd.setTextRefernce(HSD);

        ngaysx1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ngaysx1.setTextRefernce(NGAYSX1);

        hsd1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        hsd1.setTextRefernce(HSD1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(9, 131, 149));
        setMinimumSize(new java.awt.Dimension(1100, 783));

        mainpanel.setBackground(new java.awt.Color(10, 77, 105));
        mainpanel.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 15, 0, new java.awt.Color(8, 131, 149)));
        mainpanel.setForeground(new java.awt.Color(10, 77, 105));
        mainpanel.setMaximumSize(new java.awt.Dimension(1200, 750));
        mainpanel.setMinimumSize(new java.awt.Dimension(1101, 750));
        mainpanel.setPreferredSize(new java.awt.Dimension(1101, 750));
        mainpanel.setLayout(null);

        GradientPaneltop.setBackground(new java.awt.Color(10, 77, 105));
        GradientPaneltop.setkEndColor(new java.awt.Color(1, 255, 202));
        GradientPaneltop.setkGradientFocus(875);
        GradientPaneltop.setkStartColor(new java.awt.Color(9, 131, 149));
        GradientPaneltop.setRoundBottomLeft(50);
        GradientPaneltop.setRoundBottomRight(50);
        GradientPaneltop.setRoundTopLeft(50);
        GradientPaneltop.setRoundTopRight(50);

        lbsohd.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbsohd.setForeground(new java.awt.Color(255, 255, 255));
        lbsohd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbsohd.setText("Số HĐ");

        txtSoHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoHD.setBorder(null);
        txtSoHD.setOpaque(true);
        txtSoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoHDActionPerformed(evt);
            }
        });

        lbtenNV.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbtenNV.setForeground(new java.awt.Color(255, 255, 255));
        lbtenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtenNV.setText("Tên nhân viên");
        lbtenNV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbmaNV.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbmaNV.setForeground(new java.awt.Color(255, 255, 255));
        lbmaNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbmaNV.setText("Mã NV");

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaNV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaNV.setBorder(null);

        lbDate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbDate.setForeground(new java.awt.Color(255, 255, 255));
        lbDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDate.setText("Ngày lập");

        ngaylapHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ngaylapHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ngaylapHD.setBorder(null);

        calendarchoose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        calendarchoose.setBorderPainted(false);
        calendarchoose.setContentAreaFilled(false);
        calendarchoose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        calendarchoose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        calendarchoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarchooseActionPerformed(evt);
            }
        });

        lbtenNV1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbtenNV1.setForeground(new java.awt.Color(255, 255, 255));
        lbtenNV1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtenNV1.setText("Tên nhà cung cấp");
        lbtenNV1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtName.setEditable(false);

        tenncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tennccActionPerformed(evt);
            }
        });

        lbtenNV2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbtenNV2.setForeground(new java.awt.Color(255, 255, 255));
        lbtenNV2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtenNV2.setText("Mã nhà cung cấp");
        lbtenNV2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout GradientPaneltopLayout = new javax.swing.GroupLayout(GradientPaneltop);
        GradientPaneltop.setLayout(GradientPaneltopLayout);
        GradientPaneltopLayout.setHorizontalGroup(
            GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GradientPaneltopLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbsohd)
                    .addComponent(lbtenNV)
                    .addComponent(lbmaNV))
                .addGap(28, 28, 28)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtenNV2)
                    .addComponent(lbtenNV1)
                    .addComponent(lbDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GradientPaneltopLayout.createSequentialGroup()
                        .addComponent(ngaylapHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calendarchoose))
                    .addComponent(tenncc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MANCC, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        GradientPaneltopLayout.setVerticalGroup(
            GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GradientPaneltopLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbsohd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDate)
                        .addComponent(ngaylapHD, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calendarchoose, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtenNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MANCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtenNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        mainpanel.add(GradientPaneltop);
        GradientPaneltop.setBounds(30, 40, 750, 110);

        kGradientPanel4.setBackground(new java.awt.Color(8, 131, 149));
        kGradientPanel4.setForeground(new java.awt.Color(8, 131, 149));
        kGradientPanel4.setkEndColor(new java.awt.Color(5, 191, 219));
        kGradientPanel4.setkGradientFocus(50);
        kGradientPanel4.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel4.setMinimumSize(new java.awt.Dimension(20, 690));
        kGradientPanel4.setPreferredSize(new java.awt.Dimension(20, 690));

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );

        mainpanel.add(kGradientPanel4);
        kGradientPanel4.setBounds(810, 0, 20, 710);

        jPanel6.setBackground(new java.awt.Color(8, 131, 149));
        jPanel6.setPreferredSize(new java.awt.Dimension(290, 95));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        mainpanel.add(jPanel6);
        jPanel6.setBounds(830, 290, 250, 140);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-arrow.png"))); // NOI18N
        mainpanel.add(jLabel9);
        jLabel9.setBounds(890, 622, 45, 45);

        btnBack.setBorder(null);
        btnBack.setText("      Quay lại");
        btnBack.setColor1(new java.awt.Color(216, 171, 255));
        btnBack.setColor2(new java.awt.Color(3, 137, 255));
        btnBack.setFocusable(false);
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBack.setkGradientFocus(225);
        btnBack.setPreferredSize(new java.awt.Dimension(170, 65));
        btnBack.setRadius(39);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        mainpanel.add(btnBack);
        btnBack.setBounds(890, 620, 130, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine3 (1).png"))); // NOI18N
        mainpanel.add(jLabel3);
        jLabel3.setBounds(940, 250, 41, 41);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        mainpanel.add(jLabel7);
        jLabel7.setBounds(870, 40, 171, 120);

        panelCaculate.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelCaculate.setkEndColor(new java.awt.Color(16, 123, 162));
        panelCaculate.setkGradientFocus(550);
        panelCaculate.setkStartColor(new java.awt.Color(16, 123, 162));
        panelCaculate.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compose.png"))); // NOI18N
        panelCaculate.add(jLabel1);
        jLabel1.setBounds(500, 20, 60, 60);

        mybutton3Gradient1.setForeground(new java.awt.Color(255, 255, 255));
        mybutton3Gradient1.setText("Thêm thuốc mới");
        mybutton3Gradient1.setColor2(new java.awt.Color(102, 204, 255));
        mybutton3Gradient1.setFocusable(false);
        mybutton3Gradient1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mybutton3Gradient1.sethoverColor1(new java.awt.Color(102, 204, 255));
        mybutton3Gradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton3Gradient1ActionPerformed(evt);
            }
        });
        panelCaculate.add(mybutton3Gradient1);
        mybutton3Gradient1.setBounds(520, 20, 210, 60);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list.png"))); // NOI18N
        panelCaculate.add(jLabel2);
        jLabel2.setBounds(20, 20, 60, 60);

        mybutton3Gradient2.setForeground(new java.awt.Color(255, 255, 255));
        mybutton3Gradient2.setText("Danh sách");
        mybutton3Gradient2.setColor2(new java.awt.Color(102, 204, 255));
        mybutton3Gradient2.setFocusable(false);
        mybutton3Gradient2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mybutton3Gradient2.sethoverColor1(new java.awt.Color(51, 51, 255));
        mybutton3Gradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton3Gradient2ActionPerformed(evt);
            }
        });
        panelCaculate.add(mybutton3Gradient2);
        mybutton3Gradient2.setBounds(40, 20, 210, 60);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checklist.png"))); // NOI18N
        panelCaculate.add(jLabel6);
        jLabel6.setBounds(260, 20, 60, 60);

        mybutton3Gradient3.setForeground(new java.awt.Color(255, 255, 255));
        mybutton3Gradient3.setText("   Thêm thuốc đã có ");
        mybutton3Gradient3.setColor2(new java.awt.Color(102, 204, 255));
        mybutton3Gradient3.setFocusable(false);
        mybutton3Gradient3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mybutton3Gradient3.sethoverColor1(new java.awt.Color(51, 51, 255));
        mybutton3Gradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton3Gradient3ActionPerformed(evt);
            }
        });
        panelCaculate.add(mybutton3Gradient3);
        mybutton3Gradient3.setBounds(280, 20, 210, 60);

        mainpanel.add(panelCaculate);
        panelCaculate.setBounds(30, 160, 750, 100);

        panelReset2.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelReset2.setkEndColor(new java.awt.Color(16, 123, 162));
        panelReset2.setkGradientFocus(550);
        panelReset2.setkStartColor(new java.awt.Color(16, 123, 162));
        panelReset2.setLayout(null);

        panelReset3.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelReset3.setkEndColor(new java.awt.Color(16, 123, 162));
        panelReset3.setkGradientFocus(550);
        panelReset3.setkStartColor(new java.awt.Color(16, 123, 162));
        panelReset3.setLayout(null);
        panelReset2.add(panelReset3);
        panelReset3.setBounds(830, 410, 250, 100);

        mybutton24.setBorder(null);
        mybutton24.setText("Làm mới");
        mybutton24.setFocusable(false);
        mybutton24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton24.setRadius(35);
        mybutton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton24ActionPerformed(evt);
            }
        });
        panelReset2.add(mybutton24);
        mybutton24.setBounds(50, 30, 170, 40);

        btnsave3.setBorder(null);
        btnsave3.setText("Danh sách hóa đơn");
        btnsave3.setFocusable(false);
        btnsave3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave3.setRadius(35);
        btnsave3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsave3ActionPerformed(evt);
            }
        });
        panelReset2.add(btnsave3);
        btnsave3.setBounds(50, 90, 170, 40);

        mainpanel.add(panelReset2);
        panelReset2.setBounds(830, 450, 250, 150);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 6, 0, new java.awt.Color(5, 191, 219)));
        jPanel1.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quản lý nhập thuốc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(854, 854, 854))
        );

        mainpanel.add(jPanel1);
        jPanel1.setBounds(830, 180, 250, 60);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine3 (2).png"))); // NOI18N
        mainpanel.add(jLabel16);
        jLabel16.setBounds(870, 250, 40, 41);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(10, 77, 104));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        panelMedInform.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 0, new java.awt.Color(5, 191, 219)));
        panelMedInform.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform.setkGradientFocus(550);
        panelMedInform.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform.setLayout(null);

        panelMedInform1.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform1.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform1.setkGradientFocus(550);
        panelMedInform1.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform1.setLayout(null);
        panelMedInform.add(panelMedInform1);
        panelMedInform1.setBounds(450, 440, 330, 170);

        txtdelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdelete.setForeground(new java.awt.Color(153, 153, 153));
        panelMedInform.add(txtdelete);
        txtdelete.setBounds(60, 30, 130, 23);

        Delete.setBorder(null);
        Delete.setText("Xóa");
        Delete.setFocusable(false);
        Delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Delete.setRadius(35);
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        panelMedInform.add(Delete);
        Delete.setBounds(220, 30, 100, 30);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (5).png"))); // NOI18N
        panelMedInform.add(jLabel5);
        jLabel5.setBounds(10, 32, 41, 41);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Mã thuốc");
        panelMedInform.add(jLabel17);
        jLabel17.setBounds(60, 10, 70, 16);

        panelMedInform14.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform14.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform14.setkGradientFocus(550);
        panelMedInform14.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform14.setLayout(null);

        panelMedInform15.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform15.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform15.setkGradientFocus(550);
        panelMedInform15.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform15.setLayout(null);
        panelMedInform14.add(panelMedInform15);
        panelMedInform15.setBounds(450, 440, 330, 170);

        panelMedInform16.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform16.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform16.setkGradientFocus(550);
        panelMedInform16.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform16.setLayout(null);

        panelMedInform17.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform17.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform17.setkGradientFocus(550);
        panelMedInform17.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform17.setLayout(null);
        panelMedInform16.add(panelMedInform17);
        panelMedInform17.setBounds(450, 440, 330, 170);

        panelMedInform14.add(panelMedInform16);
        panelMedInform16.setBounds(470, 480, 330, 170);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tổng tiền");
        jLabel24.setToolTipText("");
        panelMedInform14.add(jLabel24);
        jLabel24.setBounds(80, 50, 110, 16);

        txttongtien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform14.add(txttongtien);
        txttongtien.setBounds(210, 40, 150, 30);

        mybutton28.setBorder(null);
        mybutton28.setText("Tính toán");
        mybutton28.setFocusable(false);
        mybutton28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton28.setRadius(35);
        mybutton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton28ActionPerformed(evt);
            }
        });
        panelMedInform14.add(mybutton28);
        mybutton28.setBounds(80, 120, 110, 40);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform14.add(jLabel25);
        jLabel25.setBounds(10, 130, 41, 41);

        btnsave.setBorder(null);
        btnsave.setText("Lưu");
        btnsave.setFocusable(false);
        btnsave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave.setRadius(35);
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        panelMedInform14.add(btnsave);
        btnsave.setBounds(230, 120, 110, 40);

        panelMedInform18.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform18.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform18.setkGradientFocus(550);
        panelMedInform18.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform18.setLayout(null);

        panelMedInform19.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform19.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform19.setkGradientFocus(550);
        panelMedInform19.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform19.setLayout(null);
        panelMedInform18.add(panelMedInform19);
        panelMedInform19.setBounds(450, 440, 330, 170);

        panelMedInform20.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform20.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform20.setkGradientFocus(550);
        panelMedInform20.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform20.setLayout(null);

        panelMedInform21.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform21.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform21.setkGradientFocus(550);
        panelMedInform21.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform21.setLayout(null);
        panelMedInform20.add(panelMedInform21);
        panelMedInform21.setBounds(450, 440, 330, 170);

        panelMedInform18.add(panelMedInform20);
        panelMedInform20.setBounds(470, 480, 330, 170);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Giảm giá");
        panelMedInform18.add(jLabel26);
        jLabel26.setBounds(60, 60, 80, 20);

        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField18.setText("0");
        panelMedInform18.add(jTextField18);
        jTextField18.setBounds(200, 60, 100, 23);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Khách cần trả");
        jLabel27.setToolTipText("");
        panelMedInform18.add(jLabel27);
        jLabel27.setBounds(60, 100, 100, 16);

        jTextField19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform18.add(jTextField19);
        jTextField19.setBounds(200, 100, 100, 22);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Tổng tiền");
        jLabel28.setToolTipText("");
        panelMedInform18.add(jLabel28);
        jLabel28.setBounds(60, 20, 70, 16);

        jTextField20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform18.add(jTextField20);
        jTextField20.setBounds(200, 20, 100, 22);

        mybutton30.setBorder(null);
        mybutton30.setText("Tính");
        mybutton30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton30.setRadius(35);
        panelMedInform18.add(mybutton30);
        mybutton30.setBounds(80, 140, 100, 30);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform18.add(jLabel29);
        jLabel29.setBounds(10, 130, 41, 41);

        mybutton31.setBorder(null);
        mybutton31.setText("Lưu");
        mybutton31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton31.setRadius(35);
        panelMedInform18.add(mybutton31);
        mybutton31.setBounds(240, 140, 100, 30);

        panelMedInform14.add(panelMedInform18);
        panelMedInform18.setBounds(390, 450, 390, 180);

        panelMedInform2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 6, 0, new java.awt.Color(5, 191, 219)));
        panelMedInform2.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform2.setkGradientFocus(550);
        panelMedInform2.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform2.setLayout(null);

        panelMedInform3.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform3.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform3.setkGradientFocus(550);
        panelMedInform3.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform3.setLayout(null);
        panelMedInform2.add(panelMedInform3);
        panelMedInform3.setBounds(450, 440, 330, 170);

        panelMedInform4.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform4.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform4.setkGradientFocus(550);
        panelMedInform4.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform4.setLayout(null);

        panelMedInform5.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform5.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform5.setkGradientFocus(550);
        panelMedInform5.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform5.setLayout(null);
        panelMedInform4.add(panelMedInform5);
        panelMedInform5.setBounds(450, 440, 330, 170);

        panelMedInform2.add(panelMedInform4);
        panelMedInform4.setBounds(470, 480, 330, 170);

        txtchange.setEditable(false);
        txtchange.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtchange.setForeground(new java.awt.Color(153, 153, 153));
        txtchange.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panelMedInform2.add(txtchange);
        txtchange.setBounds(200, 53, 130, 30);

        txtpayment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpayment.setForeground(new java.awt.Color(153, 153, 153));
        txtpayment.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panelMedInform2.add(txtpayment);
        txtpayment.setBounds(200, 13, 130, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Tiền cần trả ");
        panelMedInform2.add(jLabel12);
        jLabel12.setBounds(30, 10, 140, 17);

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tiền mặt");
        panelMedInform2.add(jLabel13);
        jLabel13.setBounds(70, 30, 70, 16);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tiền thừa nhận lại ");
        panelMedInform2.add(jLabel14);
        jLabel14.setBounds(40, 60, 140, 17);

        Printbutton.setBorder(null);
        Printbutton.setText("In");
        Printbutton.setFocusable(false);
        Printbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Printbutton.setRadius(35);
        Printbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintbuttonActionPerformed(evt);
            }
        });

        kGradientPanel5.setBackground(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setForeground(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setkEndColor(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setkGradientFocus(50);
        kGradientPanel5.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setMinimumSize(new java.awt.Dimension(20, 690));

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panelMedInform, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelMedInform2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(panelMedInform14, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addComponent(kGradientPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelMedInform, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelMedInform2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelMedInform14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        availablemedicine.setBackground(new java.awt.Color(10, 77, 104));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã thuốc", "Tên thuốc", "Số lượng còn", "Hạn sử dụng"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        panelMedInform22.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform22.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform22.setkGradientFocus(550);
        panelMedInform22.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform22.setLayout(null);

        panelMedInform23.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform23.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform23.setkGradientFocus(550);
        panelMedInform23.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform23.setLayout(null);
        panelMedInform22.add(panelMedInform23);
        panelMedInform23.setBounds(450, 440, 330, 170);

        panelMedInform24.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform24.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform24.setkGradientFocus(550);
        panelMedInform24.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform24.setLayout(null);

        panelMedInform25.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform25.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform25.setkGradientFocus(550);
        panelMedInform25.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform25.setLayout(null);
        panelMedInform24.add(panelMedInform25);
        panelMedInform25.setBounds(450, 440, 330, 170);

        panelMedInform22.add(panelMedInform24);
        panelMedInform24.setBounds(470, 480, 330, 170);

        panelMedInform26.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform26.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform26.setkGradientFocus(550);
        panelMedInform26.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform26.setLayout(null);

        panelMedInform27.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform27.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform27.setkGradientFocus(550);
        panelMedInform27.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform27.setLayout(null);
        panelMedInform26.add(panelMedInform27);
        panelMedInform27.setBounds(450, 440, 330, 170);

        panelMedInform28.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform28.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform28.setkGradientFocus(550);
        panelMedInform28.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform28.setLayout(null);

        panelMedInform29.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform29.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform29.setkGradientFocus(550);
        panelMedInform29.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform29.setLayout(null);
        panelMedInform28.add(panelMedInform29);
        panelMedInform29.setBounds(450, 440, 330, 170);

        panelMedInform26.add(panelMedInform28);
        panelMedInform28.setBounds(470, 480, 330, 170);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Giảm giá");
        panelMedInform26.add(jLabel32);
        jLabel32.setBounds(60, 60, 80, 20);

        jTextField21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField21.setText("0");
        panelMedInform26.add(jTextField21);
        jTextField21.setBounds(200, 60, 100, 23);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Khách cần trả");
        jLabel33.setToolTipText("");
        panelMedInform26.add(jLabel33);
        jLabel33.setBounds(60, 100, 100, 16);

        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform26.add(jTextField22);
        jTextField22.setBounds(200, 100, 100, 22);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Tổng tiền");
        jLabel34.setToolTipText("");
        panelMedInform26.add(jLabel34);
        jLabel34.setBounds(60, 20, 70, 16);

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform26.add(jTextField23);
        jTextField23.setBounds(200, 20, 100, 22);

        mybutton32.setBorder(null);
        mybutton32.setText("Tính");
        mybutton32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton32.setRadius(35);
        panelMedInform26.add(mybutton32);
        mybutton32.setBounds(80, 140, 100, 30);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform26.add(jLabel35);
        jLabel35.setBounds(10, 130, 41, 41);

        mybutton33.setBorder(null);
        mybutton33.setText("Lưu");
        mybutton33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton33.setRadius(35);
        panelMedInform26.add(mybutton33);
        mybutton33.setBounds(240, 140, 100, 30);

        panelMedInform22.add(panelMedInform26);
        panelMedInform26.setBounds(390, 450, 390, 180);

        TenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenSPActionPerformed(evt);
            }
        });
        panelMedInform22.add(TenSP);
        TenSP.setBounds(130, 20, 210, 22);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Giá Nhập");
        panelMedInform22.add(jLabel8);
        jLabel8.setBounds(10, 110, 73, 20);
        panelMedInform22.add(GIAGOC);
        GIAGOC.setBounds(130, 110, 120, 22);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Đơn Vị");
        panelMedInform22.add(jLabel11);
        jLabel11.setBounds(10, 80, 73, 20);
        panelMedInform22.add(DVT);
        DVT.setBounds(130, 80, 120, 22);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Số lượng");
        panelMedInform22.add(jLabel18);
        jLabel18.setBounds(10, 140, 73, 20);
        panelMedInform22.add(SOLUONG);
        SOLUONG.setBounds(130, 140, 120, 22);
        panelMedInform22.add(NGAYSX);
        NGAYSX.setBounds(130, 230, 120, 22);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Ngày Sản xuất");
        panelMedInform22.add(jLabel19);
        jLabel19.setBounds(10, 230, 110, 20);
        panelMedInform22.add(HSD);
        HSD.setBounds(130, 260, 120, 22);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Hạn sử dụng");
        panelMedInform22.add(jLabel20);
        jLabel20.setBounds(10, 260, 90, 20);
        panelMedInform22.add(NHASX);
        NHASX.setBounds(130, 170, 200, 22);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nhà Sản Xuất");
        panelMedInform22.add(jLabel21);
        jLabel21.setBounds(10, 170, 100, 20);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Số Lô Sản Xuất");
        panelMedInform22.add(jLabel22);
        jLabel22.setBounds(10, 200, 110, 20);

        SOLOSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SOLOSXActionPerformed(evt);
            }
        });
        panelMedInform22.add(SOLOSX);
        SOLOSX.setBounds(130, 200, 200, 22);

        btnsave1.setBorder(null);
        btnsave1.setText("Thêm vào danh sách");
        btnsave1.setFocusable(false);
        btnsave1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave1.setRadius(35);
        btnsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsave1ActionPerformed(evt);
            }
        });
        panelMedInform22.add(btnsave1);
        btnsave1.setBounds(100, 300, 160, 40);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Mã thuốc");
        panelMedInform22.add(jLabel23);
        jLabel23.setBounds(10, 50, 73, 20);

        ID.setEditable(false);
        panelMedInform22.add(ID);
        ID.setBounds(130, 50, 64, 22);

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Tên Thuốc");
        panelMedInform22.add(jLabel47);
        jLabel47.setBounds(10, 20, 73, 20);

        btnHSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnHSD.setBorder(null);
        btnHSD.setContentAreaFilled(false);
        btnHSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHSDActionPerformed(evt);
            }
        });
        panelMedInform22.add(btnHSD);
        btnHSD.setBounds(260, 260, 30, 24);

        btnNgaysx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgaysx.setBorder(null);
        btnNgaysx.setContentAreaFilled(false);
        btnNgaysx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaysxActionPerformed(evt);
            }
        });
        panelMedInform22.add(btnNgaysx);
        btnNgaysx.setBounds(260, 230, 30, 24);

        panelMedInform22.add(trangthai);
        trangthai.setBounds(220, 50, 120, 22);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nhập thuốc đã có");

        kGradientPanel6.setBackground(new java.awt.Color(8, 131, 149));
        kGradientPanel6.setForeground(new java.awt.Color(8, 131, 149));
        kGradientPanel6.setkEndColor(new java.awt.Color(8, 131, 149));
        kGradientPanel6.setkGradientFocus(50);
        kGradientPanel6.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel6.setMinimumSize(new java.awt.Dimension(20, 690));

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout availablemedicineLayout = new javax.swing.GroupLayout(availablemedicine);
        availablemedicine.setLayout(availablemedicineLayout);
        availablemedicineLayout.setHorizontalGroup(
            availablemedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availablemedicineLayout.createSequentialGroup()
                .addGroup(availablemedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(availablemedicineLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, availablemedicineLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelMedInform22, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, availablemedicineLayout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        availablemedicineLayout.setVerticalGroup(
            availablemedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availablemedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(availablemedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(availablemedicineLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMedInform22, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", availablemedicine);

        newmedicine.setBackground(new java.awt.Color(10, 77, 104));

        panelMedInform30.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform30.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform30.setkGradientFocus(550);
        panelMedInform30.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform30.setLayout(null);

        panelMedInform31.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform31.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform31.setkGradientFocus(550);
        panelMedInform31.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform31.setLayout(null);
        panelMedInform30.add(panelMedInform31);
        panelMedInform31.setBounds(450, 440, 330, 170);

        panelMedInform32.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform32.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform32.setkGradientFocus(550);
        panelMedInform32.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform32.setLayout(null);

        panelMedInform33.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform33.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform33.setkGradientFocus(550);
        panelMedInform33.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform33.setLayout(null);
        panelMedInform32.add(panelMedInform33);
        panelMedInform33.setBounds(450, 440, 330, 170);

        panelMedInform30.add(panelMedInform32);
        panelMedInform32.setBounds(470, 480, 330, 170);

        panelMedInform34.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform34.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform34.setkGradientFocus(550);
        panelMedInform34.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform34.setLayout(null);

        panelMedInform35.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform35.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform35.setkGradientFocus(550);
        panelMedInform35.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform35.setLayout(null);
        panelMedInform34.add(panelMedInform35);
        panelMedInform35.setBounds(450, 440, 330, 170);

        panelMedInform36.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform36.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform36.setkGradientFocus(550);
        panelMedInform36.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform36.setLayout(null);

        panelMedInform37.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform37.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform37.setkGradientFocus(550);
        panelMedInform37.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform37.setLayout(null);
        panelMedInform36.add(panelMedInform37);
        panelMedInform37.setBounds(450, 440, 330, 170);

        panelMedInform34.add(panelMedInform36);
        panelMedInform36.setBounds(470, 480, 330, 170);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Giảm giá");
        panelMedInform34.add(jLabel36);
        jLabel36.setBounds(60, 60, 80, 20);

        jTextField24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField24.setText("0");
        panelMedInform34.add(jTextField24);
        jTextField24.setBounds(200, 60, 100, 23);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Khách cần trả");
        jLabel37.setToolTipText("");
        panelMedInform34.add(jLabel37);
        jLabel37.setBounds(60, 100, 100, 16);

        jTextField25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform34.add(jTextField25);
        jTextField25.setBounds(200, 100, 100, 22);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Tổng tiền");
        jLabel38.setToolTipText("");
        panelMedInform34.add(jLabel38);
        jLabel38.setBounds(60, 20, 70, 16);

        jTextField26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform34.add(jTextField26);
        jTextField26.setBounds(200, 20, 100, 22);

        mybutton34.setBorder(null);
        mybutton34.setText("Tính");
        mybutton34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton34.setRadius(35);
        panelMedInform34.add(mybutton34);
        mybutton34.setBounds(80, 140, 100, 30);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform34.add(jLabel39);
        jLabel39.setBounds(10, 130, 41, 41);

        mybutton35.setBorder(null);
        mybutton35.setText("Lưu");
        mybutton35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton35.setRadius(35);
        panelMedInform34.add(mybutton35);
        mybutton35.setBounds(240, 140, 100, 30);

        panelMedInform30.add(panelMedInform34);
        panelMedInform34.setBounds(390, 450, 390, 180);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Giá Nhập");
        panelMedInform30.add(jLabel30);
        jLabel30.setBounds(80, 90, 73, 20);
        panelMedInform30.add(GIAGOC1);
        GIAGOC1.setBounds(220, 90, 120, 22);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Đơn Vị");
        panelMedInform30.add(jLabel31);
        jLabel31.setBounds(80, 60, 73, 20);
        panelMedInform30.add(DVT1);
        DVT1.setBounds(220, 60, 120, 22);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Số lượng");
        panelMedInform30.add(jLabel40);
        jLabel40.setBounds(80, 120, 73, 20);
        panelMedInform30.add(SOLUONG1);
        SOLUONG1.setBounds(220, 120, 120, 22);
        panelMedInform30.add(NGAYSX1);
        NGAYSX1.setBounds(220, 210, 120, 22);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Ngày Sản xuất");
        panelMedInform30.add(jLabel41);
        jLabel41.setBounds(80, 210, 110, 20);
        panelMedInform30.add(HSD1);
        HSD1.setBounds(220, 240, 120, 22);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Hạn sử dụng");
        panelMedInform30.add(jLabel42);
        jLabel42.setBounds(80, 240, 90, 20);
        panelMedInform30.add(NHASX1);
        NHASX1.setBounds(220, 150, 200, 22);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Nhà Sản Xuất");
        panelMedInform30.add(jLabel43);
        jLabel43.setBounds(80, 150, 100, 20);

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Số Lô Sản Xuất");
        panelMedInform30.add(jLabel44);
        jLabel44.setBounds(80, 180, 110, 20);
        panelMedInform30.add(SOLOSX1);
        SOLOSX1.setBounds(220, 180, 200, 22);

        btnsave2.setBorder(null);
        btnsave2.setText("Thêm vào danh sách");
        btnsave2.setFocusable(false);
        btnsave2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave2.setRadius(35);
        btnsave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsave2ActionPerformed(evt);
            }
        });
        panelMedInform30.add(btnsave2);
        btnsave2.setBounds(180, 290, 160, 40);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Tên Thuốc");
        panelMedInform30.add(jLabel45);
        jLabel45.setBounds(80, 30, 73, 20);
        panelMedInform30.add(TenSP1);
        TenSP1.setBounds(220, 30, 200, 22);

        btnHSD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnHSD1.setBorder(null);
        btnHSD1.setContentAreaFilled(false);
        btnHSD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHSD1ActionPerformed(evt);
            }
        });
        panelMedInform30.add(btnHSD1);
        btnHSD1.setBounds(350, 240, 30, 24);

        btnNgaysx1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgaysx1.setBorder(null);
        btnNgaysx1.setContentAreaFilled(false);
        btnNgaysx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaysx1ActionPerformed(evt);
            }
        });
        panelMedInform30.add(btnNgaysx1);
        btnNgaysx1.setBounds(350, 210, 30, 24);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Nhập thuốc mới");

        kGradientPanel7.setBackground(new java.awt.Color(8, 131, 149));
        kGradientPanel7.setForeground(new java.awt.Color(8, 131, 149));
        kGradientPanel7.setkEndColor(new java.awt.Color(8, 131, 149));
        kGradientPanel7.setkGradientFocus(50);
        kGradientPanel7.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel7.setMinimumSize(new java.awt.Dimension(20, 690));

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout newmedicineLayout = new javax.swing.GroupLayout(newmedicine);
        newmedicine.setLayout(newmedicineLayout);
        newmedicineLayout.setHorizontalGroup(
            newmedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newmedicineLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(newmedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newmedicineLayout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(324, 324, 324))
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newmedicineLayout.createSequentialGroup()
                        .addComponent(panelMedInform30, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))))
        );
        newmedicineLayout.setVerticalGroup(
            newmedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newmedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMedInform30, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", newmedicine);

        jPanel5.setBackground(new java.awt.Color(10, 77, 104));

        panelMedInform38.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform38.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform38.setkGradientFocus(550);
        panelMedInform38.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform38.setLayout(null);

        panelMedInform39.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform39.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform39.setkGradientFocus(550);
        panelMedInform39.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform39.setLayout(null);
        panelMedInform38.add(panelMedInform39);
        panelMedInform39.setBounds(450, 440, 330, 170);

        panelMedInform40.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform40.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform40.setkGradientFocus(550);
        panelMedInform40.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform40.setLayout(null);

        panelMedInform41.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform41.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform41.setkGradientFocus(550);
        panelMedInform41.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform41.setLayout(null);
        panelMedInform40.add(panelMedInform41);
        panelMedInform41.setBounds(450, 440, 330, 170);

        panelMedInform38.add(panelMedInform40);
        panelMedInform40.setBounds(470, 480, 330, 170);

        panelMedInform42.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform42.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform42.setkGradientFocus(550);
        panelMedInform42.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform42.setLayout(null);

        panelMedInform43.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform43.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform43.setkGradientFocus(550);
        panelMedInform43.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform43.setLayout(null);
        panelMedInform42.add(panelMedInform43);
        panelMedInform43.setBounds(450, 440, 330, 170);

        panelMedInform44.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform44.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform44.setkGradientFocus(550);
        panelMedInform44.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform44.setLayout(null);

        panelMedInform45.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelMedInform45.setkEndColor(new java.awt.Color(16, 123, 162));
        panelMedInform45.setkGradientFocus(550);
        panelMedInform45.setkStartColor(new java.awt.Color(16, 123, 162));
        panelMedInform45.setLayout(null);
        panelMedInform44.add(panelMedInform45);
        panelMedInform45.setBounds(450, 440, 330, 170);

        panelMedInform42.add(panelMedInform44);
        panelMedInform44.setBounds(470, 480, 330, 170);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Giảm giá");
        panelMedInform42.add(jLabel48);
        jLabel48.setBounds(60, 60, 80, 20);

        jTextField27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField27.setText("0");
        panelMedInform42.add(jTextField27);
        jTextField27.setBounds(200, 60, 100, 23);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Khách cần trả");
        jLabel49.setToolTipText("");
        panelMedInform42.add(jLabel49);
        jLabel49.setBounds(60, 100, 100, 16);

        jTextField28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform42.add(jTextField28);
        jTextField28.setBounds(200, 100, 100, 22);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Tổng tiền");
        jLabel50.setToolTipText("");
        panelMedInform42.add(jLabel50);
        jLabel50.setBounds(60, 20, 70, 16);

        jTextField29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform42.add(jTextField29);
        jTextField29.setBounds(200, 20, 100, 22);

        mybutton36.setBorder(null);
        mybutton36.setText("Tính");
        mybutton36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton36.setRadius(35);
        panelMedInform42.add(mybutton36);
        mybutton36.setBounds(80, 140, 100, 30);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform42.add(jLabel51);
        jLabel51.setBounds(10, 130, 41, 41);

        mybutton37.setBorder(null);
        mybutton37.setText("Lưu");
        mybutton37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton37.setRadius(35);
        panelMedInform42.add(mybutton37);
        mybutton37.setBounds(240, 140, 100, 30);

        panelMedInform38.add(panelMedInform42);
        panelMedInform42.setBounds(390, 450, 390, 180);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        panelMedInform38.add(jScrollPane3);
        jScrollPane3.setBounds(10, 10, 720, 330);

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Danh sách hóa đơn nhập thuốc");

        kGradientPanel8.setBackground(new java.awt.Color(8, 131, 149));
        kGradientPanel8.setForeground(new java.awt.Color(8, 131, 149));
        kGradientPanel8.setkEndColor(new java.awt.Color(8, 131, 149));
        kGradientPanel8.setkGradientFocus(50);
        kGradientPanel8.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel8.setMinimumSize(new java.awt.Dimension(20, 690));

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search1.png"))); // NOI18N
        btnsearch.setContentAreaFilled(false);
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        btnsearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search1.png"))); // NOI18N
        btnsearch1.setContentAreaFilled(false);
        btnsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch1ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Tìm theo ngày");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Tìm mã hóa đơn");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(panelMedInform38, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsearch1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(btnsearch)
                        .addGap(35, 35, 35))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addComponent(kGradientPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnsearch)
                                .addComponent(txtsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtsearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMedInform38, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(kGradientPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", jPanel5);

        mainpanel.add(jTabbedPane1);
        jTabbedPane1.setBounds(-50, 260, 860, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (VAITRO.equals("0")) {
            this.dispose();
            ManagerDashBoard m = new ManagerDashBoard();
            m.setVisible(true);
        } else if (VAITRO.equals("1")) {
            this.dispose();
            EmpDashBoard m = new EmpDashBoard();
            m.setVisible(true);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void calendarchooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarchooseActionPerformed
        // TODO add your handling code here:
        datecreatebill.showPopup();
    }//GEN-LAST:event_calendarchooseActionPerformed

    private void txtSoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoHDActionPerformed

    private void PrintbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintbuttonActionPerformed
        // TODO add your handling code here:
        String SOHD = txtSoHD.getText();
        String NGHD = ngaylapHD.getText();
        String MANV = txtMaNV.getText();
        String TEN =txtName.getText();
        String TENNCC = String.valueOf(tenncc.getSelectedItem().toString());
        Invoice1 m = new Invoice1(SOHD, NGHD, MANV, TENNCC, TEN);
        m.setVisible(true);
    }//GEN-LAST:event_PrintbuttonActionPerformed

    private void mybutton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton24ActionPerformed
        // TODO add your handling code here:
        tblModel.setRowCount(0);
        jTable1.setModel(tblModel);
        HienThiHoaDonNhap();
    }//GEN-LAST:event_mybutton24ActionPerformed

    private void mybutton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton28ActionPerformed
        // TODO add your handling code here:
        float tien = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            tien = tien + Float.parseFloat(jTable1.getValueAt(i, 10).toString());
        }
        txttongtien.setText(String.valueOf(tien));
    }//GEN-LAST:event_mybutton28ActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        String Name;
        String ID1;
        String Unit;
        float Price = 0;
        int Quantity;
        String MFG;
        String EXP;
        String Producer;
        String Pronumber;
        float OriginPrice;
        String mancc=MANCC.getText();

        if (txttongtien.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn phải tính tổng tiền trước khi lưu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            bLL.Insert_HOADON(txtSoHD.getText(), ngaylapHD.getText(), txtMaNV.getText(), MANCC.getText());
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int a = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                if (a == 0) {
                    mancc = MANCC.getText();
                    ID1 = jTable1.getValueAt(i, 1).toString();
                    Name = jTable1.getValueAt(i, 2).toString();
                    Unit = jTable1.getValueAt(i, 3).toString();
                    Quantity = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
                    Producer = jTable1.getValueAt(i, 5).toString();
                    Pronumber = jTable1.getValueAt(i, 6).toString();
                    MFG = jTable1.getValueAt(i, 7).toString();
                    EXP = jTable1.getValueAt(i, 8).toString();
                    OriginPrice = Float.parseFloat(jTable1.getValueAt(i, 9).toString());
                    float result;
                    result = OriginPrice * Quantity;
                    String status = jTable1.getValueAt(i, 11).toString();
                    if (status.equals("Đang bán")) {
                        if (mbll.Check(ID1) == 1) {
                            boolean check =ktbll.Insert(ID1, Name, Unit, Price, Quantity, Producer, Pronumber, MFG, EXP, OriginPrice, mancc);
                            if (check == true) {
                                bLL.Insert_CTHD(txtSoHD.getText(), ID1, Quantity, result, 0, OriginPrice);
                            }
                        } else if (mbll.Check(ID1) == 0) {
                            boolean check = mbll.Update(ID1, Name, Unit, Price, Quantity, Producer, Pronumber, MFG, EXP, OriginPrice, mancc, 1);
                            if (check == true) {
                                bLL.Insert_CTHD(txtSoHD.getText(), ID1, Quantity, result, 0, OriginPrice);
                            }
                        }
                    }

                } else if (a == 1) {
                    mancc = MANCC.getText();
                    ID1 = jTable1.getValueAt(i, 1).toString();
                    Name = jTable1.getValueAt(i, 2).toString();
                    Unit = jTable1.getValueAt(i, 3).toString();
                    Quantity = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
                    Producer = jTable1.getValueAt(i, 5).toString();
                    Pronumber = jTable1.getValueAt(i, 6).toString();
                    MFG = jTable1.getValueAt(i, 7).toString();
                    EXP = jTable1.getValueAt(i, 8).toString();
                    OriginPrice = Float.parseFloat(jTable1.getValueAt(i, 9).toString());
                    float result;
                    result = OriginPrice * Quantity;
                    boolean check = mbll.Insert(Name, Unit, Price, Quantity, Producer, Pronumber, MFG, EXP, OriginPrice, mancc, 1);
                    if (check == true) {
                        bLL.Insert_CTHD(txtSoHD.getText(), ID1, Quantity, result, Price, OriginPrice);
                    }
                }
            }
            bLL.Update_HOADON(txtSoHD.getText(), ngaylapHD.getText(), txtMaNV.getText(), MANCC.getText(), Float.parseFloat(txttongtien.getText()));
            JOptionPane.showMessageDialog(null, "Lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String a = tblModel.getValueAt(i, 1).toString();
            float b = Float.parseFloat(tblModel.getValueAt(i, 10).toString());
            if (txtdelete.getText().equals(a)) {
                tblModel.removeRow(i);
                float result;
                result = Float.parseFloat(txttongtien.getText()) - b;
                txttongtien.setText(String.valueOf(result));
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void mybutton3Gradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton3Gradient2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_mybutton3Gradient2ActionPerformed

    private void mybutton3Gradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton3Gradient3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        trangthai.addItem("Đang bán");
        trangthai.addItem("Ngừng bán");
        HienThiTENSP(mbll.Load_BLL());
        HienThiThuoc(mbll.Load_BLL());
    }//GEN-LAST:event_mybutton3Gradient3ActionPerformed

    private void btnsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave1ActionPerformed
        // TODO add your handling code here:
        String title[] = {"Loại", "Mã thuốc", "Tên thuốc", "Đơn vị", " Số lượng", "Nhà SX", "Số lô SX", "Ngày SX", " Hạn dùng", "Giá Nhập", "Thành tiền", "Trạng thái"};
        tblModel.setColumnIdentifiers(title);
        int Loai = 0;
        String Name = String.valueOf(TenSP.getSelectedItem());
        String ID1 = ID.getText();
        String Unit = DVT.getText();
        String Price = GIAGOC.getText();
        String Quantity = SOLUONG.getText();
        String MFG = NGAYSX.getText();
        String EXP = HSD.getText();
        String Producer = NHASX.getText();
        String Pronumber = SOLOSX.getText();

        float result;
        result = Float.parseFloat(Price) * Float.parseFloat(Quantity);
        String TRANGTHAI = String.valueOf(trangthai.getSelectedItem().toString());
        Object row[] = {Loai, ID1, Name, Unit, Quantity, Producer, Pronumber, MFG, EXP, Price, result, TRANGTHAI};
        tblModel.addRow(row);
        jTable1.setModel(tblModel);
    }//GEN-LAST:event_btnsave1ActionPerformed

    private void btnsave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave2ActionPerformed
        // TODO add your handling code here:
        String title[] = {"Loại", "Mã thuốc", "Tên thuốc", "Đơn vị", " Số lượng", "Nhà SX", "Số lô SX", "Ngày SX", " Hạn dùng", "Giá Nhập", "Thành tiền", "Trạng thái"};
        tblModel.setColumnIdentifiers(title);
        int ID1 = 1;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int a = Integer.parseInt((String) jTable1.getValueAt(i, 1));
            if (ID1 == a) {
                ID1++;
            }
        }
        int Loai = 1;
        String Name = TenSP1.getText();
        String Unit = DVT1.getText();
        String Price = GIAGOC1.getText();
        String Quantity = SOLUONG1.getText();
        String MFG = NGAYSX1.getText();
        String EXP = HSD1.getText();
        String Producer = NHASX1.getText();
        String Pronumber = SOLOSX1.getText();
        float result;
        result = Float.parseFloat(Price) * Float.parseFloat(Quantity);
        Object row[] = {Loai, ID1, Name, Unit, Quantity, Producer, Pronumber, MFG, EXP, Price, result};
        tblModel.addRow(row);
        jTable1.setModel(tblModel);
    }//GEN-LAST:event_btnsave2ActionPerformed

    private void mybutton3Gradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton3Gradient1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_mybutton3Gradient1ActionPerformed

    private void SOLOSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SOLOSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SOLOSXActionPerformed

    private void TenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TenSPActionPerformed
        // TODO add your handling code here:
        LoadMedicineForm(mbll.Search_BLL(String.valueOf(TenSP.getSelectedItem())));
    }//GEN-LAST:event_TenSPActionPerformed

    private void tennccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tennccActionPerformed
        // TODO add your handling code here:
        TimNhaCungCap(nccbll.TimKiemNCC(String.valueOf(tenncc.getSelectedItem())));
    }//GEN-LAST:event_tennccActionPerformed

    private void btnsave3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        HienThiHoaDonNhap();
    }//GEN-LAST:event_btnsave3ActionPerformed

    private void btnsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch1ActionPerformed
        // TODO add your handling code here:
        TimKiemHoaDonNhap(txtsearch.getText(), 0);
    }//GEN-LAST:event_btnsearch1ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        TimKiemHoaDonNhap(txtsearch1.getText(), 1);
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnNgaysxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaysxActionPerformed
        // TODO add your handling code here:
        ngaysx.showPopup();
    }//GEN-LAST:event_btnNgaysxActionPerformed

    private void btnHSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHSDActionPerformed
        // TODO add your handling code here:
        hsd.showPopup();
    }//GEN-LAST:event_btnHSDActionPerformed

    private void btnHSD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHSD1ActionPerformed
        // TODO add your handling code here:
        hsd1.showPopup();
    }//GEN-LAST:event_btnHSD1ActionPerformed

    private void btnNgaysx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaysx1ActionPerformed
        // TODO add your handling code here:
        ngaysx1.showPopup();
    }//GEN-LAST:event_btnNgaysx1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhapHang().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DVT;
    private javax.swing.JTextField DVT1;
    private keeptoo.Mybutton2 Delete;
    private javax.swing.JTextField GIAGOC;
    private javax.swing.JTextField GIAGOC1;
    private keeptoo.KGradientPanel GradientPaneltop;
    private javax.swing.JTextField HSD;
    private javax.swing.JTextField HSD1;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField MANCC;
    private javax.swing.JTextField NGAYSX;
    private javax.swing.JTextField NGAYSX1;
    private javax.swing.JTextField NHASX;
    private javax.swing.JTextField NHASX1;
    private keeptoo.Mybutton2 Printbutton;
    private javax.swing.JTextField SOLOSX;
    private javax.swing.JTextField SOLOSX1;
    private javax.swing.JTextField SOLUONG;
    private javax.swing.JTextField SOLUONG1;
    private javax.swing.JComboBox<String> TenSP;
    private javax.swing.JTextField TenSP1;
    private javax.swing.JPanel availablemedicine;
    private keeptoo.Mybutton btnBack;
    private javax.swing.JButton btnHSD;
    private javax.swing.JButton btnHSD1;
    private javax.swing.JButton btnNgaysx;
    private javax.swing.JButton btnNgaysx1;
    private keeptoo.Mybutton2 btnsave;
    private keeptoo.Mybutton2 btnsave1;
    private keeptoo.Mybutton2 btnsave2;
    private keeptoo.Mybutton2 btnsave3;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsearch1;
    private javax.swing.JButton calendarchoose;
    private com.raven.datechooser.DateChooser datecreatebill;
    private com.raven.datechooser.DateChooser hsd;
    private com.raven.datechooser.DateChooser hsd1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbmaNV;
    private javax.swing.JLabel lbsohd;
    private javax.swing.JLabel lbtenNV;
    private javax.swing.JLabel lbtenNV1;
    private javax.swing.JLabel lbtenNV2;
    private javax.swing.JPanel mainpanel;
    private keeptoo.Mybutton2 mybutton24;
    private keeptoo.Mybutton2 mybutton28;
    private keeptoo.Mybutton2 mybutton30;
    private keeptoo.Mybutton2 mybutton31;
    private keeptoo.Mybutton2 mybutton32;
    private keeptoo.Mybutton2 mybutton33;
    private keeptoo.Mybutton2 mybutton34;
    private keeptoo.Mybutton2 mybutton35;
    private keeptoo.Mybutton2 mybutton36;
    private keeptoo.Mybutton2 mybutton37;
    private keeptoo.Mybutton3Gradient mybutton3Gradient1;
    private keeptoo.Mybutton3Gradient mybutton3Gradient2;
    private keeptoo.Mybutton3Gradient mybutton3Gradient3;
    private javax.swing.JPanel newmedicine;
    private javax.swing.JTextField ngaylapHD;
    private com.raven.datechooser.DateChooser ngaysx;
    private com.raven.datechooser.DateChooser ngaysx1;
    private keeptoo.KGradientPanel panelCaculate;
    private keeptoo.KGradientPanel panelMedInform;
    private keeptoo.KGradientPanel panelMedInform1;
    private keeptoo.KGradientPanel panelMedInform14;
    private keeptoo.KGradientPanel panelMedInform15;
    private keeptoo.KGradientPanel panelMedInform16;
    private keeptoo.KGradientPanel panelMedInform17;
    private keeptoo.KGradientPanel panelMedInform18;
    private keeptoo.KGradientPanel panelMedInform19;
    private keeptoo.KGradientPanel panelMedInform2;
    private keeptoo.KGradientPanel panelMedInform20;
    private keeptoo.KGradientPanel panelMedInform21;
    private keeptoo.KGradientPanel panelMedInform22;
    private keeptoo.KGradientPanel panelMedInform23;
    private keeptoo.KGradientPanel panelMedInform24;
    private keeptoo.KGradientPanel panelMedInform25;
    private keeptoo.KGradientPanel panelMedInform26;
    private keeptoo.KGradientPanel panelMedInform27;
    private keeptoo.KGradientPanel panelMedInform28;
    private keeptoo.KGradientPanel panelMedInform29;
    private keeptoo.KGradientPanel panelMedInform3;
    private keeptoo.KGradientPanel panelMedInform30;
    private keeptoo.KGradientPanel panelMedInform31;
    private keeptoo.KGradientPanel panelMedInform32;
    private keeptoo.KGradientPanel panelMedInform33;
    private keeptoo.KGradientPanel panelMedInform34;
    private keeptoo.KGradientPanel panelMedInform35;
    private keeptoo.KGradientPanel panelMedInform36;
    private keeptoo.KGradientPanel panelMedInform37;
    private keeptoo.KGradientPanel panelMedInform38;
    private keeptoo.KGradientPanel panelMedInform39;
    private keeptoo.KGradientPanel panelMedInform4;
    private keeptoo.KGradientPanel panelMedInform40;
    private keeptoo.KGradientPanel panelMedInform41;
    private keeptoo.KGradientPanel panelMedInform42;
    private keeptoo.KGradientPanel panelMedInform43;
    private keeptoo.KGradientPanel panelMedInform44;
    private keeptoo.KGradientPanel panelMedInform45;
    private keeptoo.KGradientPanel panelMedInform5;
    private keeptoo.KGradientPanel panelReset2;
    private keeptoo.KGradientPanel panelReset3;
    private javax.swing.JComboBox<String> tenncc;
    private javax.swing.JComboBox<String> trangthai;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSoHD;
    private javax.swing.JTextField txtchange;
    private javax.swing.JTextField txtdelete;
    private javax.swing.JTextField txtpayment;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearch1;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
