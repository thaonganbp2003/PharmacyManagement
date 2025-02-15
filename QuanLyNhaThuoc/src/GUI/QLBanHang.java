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

public class QLBanHang extends javax.swing.JFrame {

    /**
     * Creates new form EmpDashBoard
     */
    public QLBanHang() {
        initComponents();
        setLocationRelativeTo(null);
        HienThiNgay();
        HienThiSOHD();
        HienThiTENSP(mbll.Load_BLL());
        HienThiThongtin();

        txtquantity.getDocument().addDocumentListener(new DocumentListener() {
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
                int quantity, tax;
                float result, price;
                if (!txtquantity.getText().equals("") && !txttax.getText().equals("")) {
                    quantity = Integer.parseInt(txtquantity.getText());
                    tax = Integer.parseInt(txttax.getText());
                    price = Float.parseFloat(txtprice.getText());
                    result = quantity * price + tax;
                    txtresult.setText(String.valueOf(result));
                }
            }

        });

        txttax.getDocument().addDocumentListener(new DocumentListener() {
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
                int quantity, tax;
                float result, price;
                if (!txttax.getText().equals("") && !txtquantity.getText().equals("")) {
                    quantity = Integer.parseInt(txtquantity.getText());
                    tax = Integer.parseInt(txttax.getText());
                    price = Float.parseFloat(txtprice.getText());
                    result = quantity * price + tax;
                    txtresult.setText(String.valueOf(result));
                }
            }

        });

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
                if (!txttotal.getText().equals("") && !txtpayment.getText().equals("")) {
                    float tien = Float.parseFloat(txtpayment.getText());
                    float tien1 = Float.parseFloat(txttotal.getText());
                    float result = tien - tien1;
                    txtchange.setText(String.valueOf(result));
                }
            }

        });

    }
    DefaultTableModel tblModel = new DefaultTableModel();
    QLBanHangBLL sbll = new QLBanHangBLL();
    ThuocBLL mbll = new ThuocBLL();
    NhanVienBLL ebll = new NhanVienBLL();
    private float tongtien = 0;
    private String VAITRO = DangNhap.frmInstance.txt1;

    public void HienThiNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
        ngaylapHD.setText(date);
    }

    public void HienThiHoaDonXuat() {
        try {
            DefaultTableModel tblModel1 = new DefaultTableModel();
            Connection conn = new DBUtils().createConn();
            String sql = """
                          select hd.SOHD,count(distinct ct.MASP), hd.NGHD,hd.MANV,hd.MAKH, hd.TRIGIA 
                         from HOADON hd, CTHD ct where hd.SOHD=ct.SOHD
                         group by hd.SOHD, hd.NGHD,hd.MANV,hd.MAKH,hd.TRIGIA
                         order by hd.SOHD asc""";
            PreparedStatement pres = conn.prepareStatement(sql);
            ResultSet rs = pres.executeQuery();
            String title[] = {"Mã hóa đơn", "Số thuốc", "Ngày lập", "Mã nhân viên", "Mã nhà khách hàng", "Tổng tiền"};
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
            jTable2.setModel(tblModel1);
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
                sql = "select hd.SOHD,count(distinct ct.MASP), hd.NGHD,hd.MANV,hd.MAKH, hd.TRIGIA \n" +
"                         from HOADON hd, CTHD ct where hd.SOHD=ct.SOHD and ( hd.NGHD like to_date('"+ search+"','DD-MM-YYYY') )\n" +
"                         group by hd.SOHD, hd.NGHD,hd.MANV,hd.MAKH,hd.TRIGIA";
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
                sql = "select hd.SOHD,count(distinct ct.MASP), hd.NGHD,hd.MANV,hd.MAKH, hd.TRIGIA \n"
                        + "                         from HOADON hd, CTHD ct where hd.SOHD=ct.SOHD and (hd.SOHD ='" + search + "')\n"
                        + "                         group by hd.SOHD, hd.NGHD,hd.MANV,hd.MAKH,hd.TRIGIA";
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

            jTable2.setModel(tblModel1);
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
            String sql = "select SOHD from HOADON";
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
            Name.removeAllItems();
            for (int i = 0; i < dsthuoc.size(); i++) {
                t = dsthuoc.get(i);
                Name.addItem(t.getTENSP());
            }
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

                txtid.setText(MASP);
                txtprice.setText(String.valueOf(GIA));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        lbtenNV2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        jLabel9 = new javax.swing.JLabel();
        btnBack = new keeptoo.Mybutton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelReset2 = new keeptoo.KGradientPanel();
        panelReset3 = new keeptoo.KGradientPanel();
        mybutton24 = new keeptoo.Mybutton2();
        mybutton25 = new keeptoo.Mybutton2();
        mybutton26 = new keeptoo.Mybutton2();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelMedInform2 = new keeptoo.KGradientPanel();
        panelMedInform3 = new keeptoo.KGradientPanel();
        panelMedInform4 = new keeptoo.KGradientPanel();
        panelMedInform5 = new keeptoo.KGradientPanel();
        txtchange = new javax.swing.JTextField();
        txtpayment = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelMedInform = new keeptoo.KGradientPanel();
        panelMedInform1 = new keeptoo.KGradientPanel();
        txtdelete = new javax.swing.JTextField();
        btndelete = new keeptoo.Mybutton2();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Printbutton = new keeptoo.Mybutton2();
        panelMedInform14 = new keeptoo.KGradientPanel();
        panelMedInform15 = new keeptoo.KGradientPanel();
        panelMedInform16 = new keeptoo.KGradientPanel();
        panelMedInform17 = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        txtdiscount = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
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
        kGradientPanel6 = new keeptoo.KGradientPanel();
        panelCaculate = new keeptoo.KGradientPanel();
        txtid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Name = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtresult = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        txtquantity = new javax.swing.JTextField();
        txttax = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelMedInform22 = new keeptoo.KGradientPanel();
        panelMedInform23 = new keeptoo.KGradientPanel();
        panelMedInform24 = new keeptoo.KGradientPanel();
        panelMedInform25 = new keeptoo.KGradientPanel();
        panelMedInform26 = new keeptoo.KGradientPanel();
        panelMedInform27 = new keeptoo.KGradientPanel();
        panelMedInform28 = new keeptoo.KGradientPanel();
        panelMedInform29 = new keeptoo.KGradientPanel();
        jLabel35 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        mybutton32 = new keeptoo.Mybutton2();
        jLabel38 = new javax.swing.JLabel();
        mybutton33 = new keeptoo.Mybutton2();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        txtsearch = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        btnsearch1 = new javax.swing.JButton();
        btnsearch2 = new javax.swing.JButton();
        txtsearch2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        datecreatebill.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        datecreatebill.setTextRefernce(ngaylapHD);

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
        lbtenNV1.setText("Tên khách hàng");
        lbtenNV1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbtenNV2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbtenNV2.setForeground(new java.awt.Color(255, 255, 255));
        lbtenNV2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtenNV2.setText("SĐT khách hàng");
        lbtenNV2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtTenKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenKH.setBorder(null);

        txtSoDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoDT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoDT.setBorder(null);

        txtName.setEditable(false);

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
                    .addGroup(GradientPaneltopLayout.createSequentialGroup()
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175)
                        .addComponent(lbtenNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(GradientPaneltopLayout.createSequentialGroup()
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbtenNV1)
                            .addComponent(lbDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GradientPaneltopLayout.createSequentialGroup()
                                .addComponent(ngaylapHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(calendarchoose))
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))))
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
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtenNV2)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        mainpanel.add(GradientPaneltop);
        GradientPaneltop.setBounds(30, 40, 750, 120);

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

        kGradientPanel5.setBackground(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setForeground(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setkEndColor(new java.awt.Color(5, 191, 219));
        kGradientPanel5.setkGradientFocus(50);
        kGradientPanel5.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel5.setMinimumSize(new java.awt.Dimension(20, 690));

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainpanel.add(jPanel6);
        jPanel6.setBounds(830, 290, 250, 120);

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
        mybutton24.setBounds(40, 20, 170, 40);

        mybutton25.setBorder(null);
        mybutton25.setText("Danh sách hóa đơn");
        mybutton25.setFocusable(false);
        mybutton25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton25.setRadius(35);
        mybutton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton25ActionPerformed(evt);
            }
        });
        panelReset2.add(mybutton25);
        mybutton25.setBounds(40, 120, 170, 40);

        mybutton26.setBorder(null);
        mybutton26.setText("Bán Thuốc");
        mybutton26.setFocusable(false);
        mybutton26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton26.setRadius(35);
        mybutton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton26ActionPerformed(evt);
            }
        });
        panelReset2.add(mybutton26);
        mybutton26.setBounds(40, 70, 170, 40);

        mainpanel.add(panelReset2);
        panelReset2.setBounds(830, 410, 250, 170);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 6, 0, new java.awt.Color(5, 191, 219)));
        jPanel1.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quản lý bán thuốc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(14, 14, 14))
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Thuốc", "Tên Thuốc", "Giá ", "Số lượng", "Thuế", "Thành Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

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
        jLabel12.setText("Khách thanh toán");
        panelMedInform2.add(jLabel12);
        jLabel12.setBounds(30, 10, 140, 17);

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tiền mặt");
        panelMedInform2.add(jLabel13);
        jLabel13.setBounds(70, 30, 70, 16);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tiền thừa trả khách");
        panelMedInform2.add(jLabel14);
        jLabel14.setBounds(30, 60, 160, 17);

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

        btndelete.setBorder(null);
        btndelete.setText("Xóa");
        btndelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndelete.setRadius(35);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        panelMedInform.add(btndelete);
        btndelete.setBounds(220, 30, 100, 30);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (5).png"))); // NOI18N
        panelMedInform.add(jLabel5);
        jLabel5.setBounds(10, 32, 41, 41);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Mã thuốc");
        panelMedInform.add(jLabel17);
        jLabel17.setBounds(60, 10, 70, 16);

        Printbutton.setBorder(null);
        Printbutton.setText("In");
        Printbutton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Printbutton.setRadius(35);
        Printbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintbuttonActionPerformed(evt);
            }
        });

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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Giảm giá");
        panelMedInform14.add(jLabel22);
        jLabel22.setBounds(60, 60, 80, 20);

        txtdiscount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtdiscount.setText("0");
        panelMedInform14.add(txtdiscount);
        txtdiscount.setBounds(200, 60, 150, 30);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Khách cần trả");
        jLabel23.setToolTipText("");
        panelMedInform14.add(jLabel23);
        jLabel23.setBounds(60, 100, 120, 16);

        txttotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform14.add(txttotal);
        txttotal.setBounds(200, 100, 150, 30);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tổng tiền");
        jLabel24.setToolTipText("");
        panelMedInform14.add(jLabel24);
        jLabel24.setBounds(60, 20, 70, 16);

        txttongtien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtienActionPerformed(evt);
            }
        });
        panelMedInform14.add(txttongtien);
        txttongtien.setBounds(200, 20, 150, 30);

        mybutton28.setBorder(null);
        mybutton28.setText("Tính toán");
        mybutton28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton28.setRadius(35);
        mybutton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybutton28ActionPerformed(evt);
            }
        });
        panelMedInform14.add(mybutton28);
        mybutton28.setBounds(80, 140, 100, 30);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform14.add(jLabel25);
        jLabel25.setBounds(10, 130, 41, 41);

        btnsave.setBorder(null);
        btnsave.setText("Lưu");
        btnsave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave.setRadius(35);
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        panelMedInform14.add(btnsave);
        btnsave.setBounds(240, 140, 100, 30);

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCaculate.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelCaculate.setkEndColor(new java.awt.Color(16, 123, 162));
        panelCaculate.setkGradientFocus(550);
        panelCaculate.setkStartColor(new java.awt.Color(16, 123, 162));
        panelCaculate.setLayout(null);

        txtid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtid.setBorder(null);
        panelCaculate.add(txtid);
        txtid.setBounds(20, 40, 71, 23);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thành tiền");
        panelCaculate.add(jLabel1);
        jLabel1.setBounds(610, 14, 80, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mã thuốc");
        panelCaculate.add(jLabel2);
        jLabel2.setBounds(10, 14, 80, 17);

        Name.setEditable(true);
        Name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Name.setBorder(null);
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        panelCaculate.add(Name);
        Name.setBounds(110, 40, 160, 23);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tên thuốc");
        panelCaculate.add(jLabel6);
        jLabel6.setBounds(150, 14, 80, 17);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Giá(VNĐ)");
        panelCaculate.add(jLabel8);
        jLabel8.setBounds(296, 14, 80, 17);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Số lượng");
        panelCaculate.add(jLabel10);
        jLabel10.setBounds(392, 14, 80, 17);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Thuế");
        panelCaculate.add(jLabel11);
        jLabel11.setBounds(480, 14, 80, 17);

        txtresult.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtresult.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtresult.setBorder(null);
        panelCaculate.add(txtresult);
        txtresult.setBounds(600, 40, 100, 23);

        txtprice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtprice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprice.setBorder(null);
        panelCaculate.add(txtprice);
        txtprice.setBounds(290, 40, 90, 23);

        txtquantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtquantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtquantity.setBorder(null);
        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });
        panelCaculate.add(txtquantity);
        txtquantity.setBounds(400, 40, 60, 23);

        txttax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttax.setBorder(null);
        panelCaculate.add(txttax);
        txttax.setBounds(490, 40, 80, 23);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnThem.setBorderPainted(false);
        btnThem.setContentAreaFilled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panelCaculate.add(btnThem);
        btnThem.setBounds(710, 30, 36, 37);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCaculate, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelMedInform, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelMedInform2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(panelMedInform14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(panelCaculate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(panelMedInform, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelMedInform2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelMedInform14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(10, 77, 104));

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

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Giảm giá");
        panelMedInform26.add(jLabel35);
        jLabel35.setBounds(60, 60, 80, 20);

        jTextField21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField21.setText("0");
        panelMedInform26.add(jTextField21);
        jTextField21.setBounds(200, 60, 100, 23);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Khách cần trả");
        jLabel36.setToolTipText("");
        panelMedInform26.add(jLabel36);
        jLabel36.setBounds(60, 100, 100, 16);

        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform26.add(jTextField22);
        jTextField22.setBounds(200, 100, 100, 22);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Tổng tiền");
        jLabel37.setToolTipText("");
        panelMedInform26.add(jLabel37);
        jLabel37.setBounds(60, 20, 70, 16);

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelMedInform26.add(jTextField23);
        jTextField23.setBounds(200, 20, 100, 22);

        mybutton32.setBorder(null);
        mybutton32.setText("Tính");
        mybutton32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton32.setRadius(35);
        panelMedInform26.add(mybutton32);
        mybutton32.setBounds(80, 140, 100, 30);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelMedInform26.add(jLabel38);
        jLabel38.setBounds(10, 130, 41, 41);

        mybutton33.setBorder(null);
        mybutton33.setText("Lưu");
        mybutton33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mybutton33.setRadius(35);
        panelMedInform26.add(mybutton33);
        mybutton33.setBounds(240, 140, 100, 30);

        panelMedInform22.add(panelMedInform26);
        panelMedInform26.setBounds(390, 450, 390, 180);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        panelMedInform22.add(jScrollPane2);
        jScrollPane2.setBounds(10, 10, 730, 390);

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Danh sách hóa đơn bán thuốc");
        jLabel33.setToolTipText("");

        btnsearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search1.png"))); // NOI18N
        btnsearch1.setBorder(null);
        btnsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch1ActionPerformed(evt);
            }
        });

        btnsearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search1.png"))); // NOI18N
        btnsearch2.setBorder(null);
        btnsearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch2ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("TÌm theo ngày");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("TÌm mã hóa đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(panelMedInform22, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnsearch2))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(btnsearch1)
                        .addGap(14, 14, 14)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(kGradientPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMedInform22, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab1", jPanel3);

        mainpanel.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 180, 810, 560);

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

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
        LoadMedicineForm(mbll.Search_BLL(String.valueOf(Name.getSelectedItem())));
        int quantity, tax;
        float result, price;
        if (!txttax.getText().equals("") && !txtquantity.getText().equals("")) {
            quantity = Integer.parseInt(txtquantity.getText());
            tax = Integer.parseInt(txttax.getText());
            price = Float.parseFloat(txtprice.getText());
            result = quantity * price + tax;
            txtresult.setText(String.valueOf(result));
        }

    }//GEN-LAST:event_NameActionPerformed

    private void PrintbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintbuttonActionPerformed
        // TODO add your handling code here:
        String SOHD = txtSoHD.getText();
        String NGHD = ngaylapHD.getText();
        String MANV = txtMaNV.getText();
        String phone = txtSoDT.getText();
        String HOTEN = txtTenKH.getText();
        String TEN=txtName.getText();
        Invoice i = new Invoice(SOHD, NGHD, MANV, phone, HOTEN,TEN);
//        i.setVisible(true);
    }//GEN-LAST:event_PrintbuttonActionPerformed

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtquantity.getText().equals("") || txttax.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số lượng và thuế không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String title[] = {"Mã thuốc", "Tên thuốc", "Giá", "Số lượng", "Thuế", "Thành tiền"};
            tblModel.setColumnIdentifiers(title);
            String MASP = txtid.getText();
            String TENSP = String.valueOf(Name.getSelectedItem());
            String GIA = txtprice.getText();
            String SOLUONG = txtquantity.getText();
            String THUE = txttax.getText();
            String TONG = txtresult.getText();
            Object row[] = {MASP, TENSP, GIA, SOLUONG, THUE, TONG};
            tblModel.addRow(row);
            jTable1.setModel(tblModel);
            tongtien = tongtien + Float.parseFloat(TONG);
            txttongtien.setText(String.valueOf(tongtien));
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void mybutton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton24ActionPerformed
        // TODO add your handling code here:
        tblModel.setRowCount(0);
        jTable1.setModel(tblModel);
        tongtien = 0;
        txttongtien.setText(String.valueOf(tongtien));
        HienThiHoaDonXuat();
    }//GEN-LAST:event_mybutton24ActionPerformed

    private void mybutton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton28ActionPerformed
        // TODO add your handling code here:
        float tien = Float.parseFloat(txttongtien.getText());
        tien = tien - Float.parseFloat(txtdiscount.getText());
        txttotal.setText(String.valueOf(tien));
    }//GEN-LAST:event_mybutton28ActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        String SOHD = txtSoHD.getText();
        String NGHD = ngaylapHD.getText();
        String MANV = txtMaNV.getText();
        String MAKH = "1", phone = txtSoDT.getText();
        String HOTEN = txtTenKH.getText();
        if (MANV.equals("")) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không được bỏ trống");
        } else if (HOTEN.equals("")) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được bỏ trống");
        } else if (phone.equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được bỏ trống");
        } else {
            float TRIGIA = 0;
            boolean check = sbll.Insert_HOADON(SOHD, NGHD, MANV, MAKH, TRIGIA, phone, HOTEN);
            if (check == true) {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    String MASP = jTable1.getValueAt(i, 0).toString();
                    String SOLUONG = jTable1.getValueAt(i, 3).toString();
                    String THUE = jTable1.getValueAt(i, 4).toString();
                    String DONGIA = jTable1.getValueAt(i, 5).toString();
                    sbll.Insert_CTHD(SOHD, MASP, Float.parseFloat(SOLUONG), Float.parseFloat(DONGIA), Float.parseFloat(THUE));
                }
                boolean check1 = sbll.Update_HOADON(SOHD, NGHD, MANV, MAKH, Float.parseFloat(txttotal.getText()), phone);
                if (check1 == true) {
                    JOptionPane.showMessageDialog(null, "Lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu không thành công");

                }

            }
        }


    }//GEN-LAST:event_btnsaveActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String a = tblModel.getValueAt(i, 0).toString();
            float b = Float.parseFloat(tblModel.getValueAt(i, 5).toString());
            if (txtdelete.getText().equals(a)) {
                tblModel.removeRow(i);
                float result;
                result = Float.parseFloat(txttongtien.getText()) - b;
                txttongtien.setText(String.valueOf(result));
            }
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void txttongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtienActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void mybutton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton25ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        HienThiHoaDonXuat();
    }//GEN-LAST:event_mybutton25ActionPerformed

    private void mybutton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybutton26ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_mybutton26ActionPerformed

    private void btnsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch1ActionPerformed
        // TODO add your handling code here:
        TimKiemHoaDonNhap(txtsearch.getText(),1);
    }//GEN-LAST:event_btnsearch1ActionPerformed

    private void btnsearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch2ActionPerformed
        // TODO add your handling code here:
        TimKiemHoaDonNhap(txtsearch2.getText(),0);
    }//GEN-LAST:event_btnsearch2ActionPerformed

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
                new QLBanHang().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel GradientPaneltop;
    private javax.swing.JComboBox<String> Name;
    private keeptoo.Mybutton2 Printbutton;
    private keeptoo.Mybutton btnBack;
    private javax.swing.JButton btnThem;
    private keeptoo.Mybutton2 btndelete;
    private keeptoo.Mybutton2 btnsave;
    private javax.swing.JButton btnsearch1;
    private javax.swing.JButton btnsearch2;
    private javax.swing.JButton calendarchoose;
    private com.raven.datechooser.DateChooser datecreatebill;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbmaNV;
    private javax.swing.JLabel lbsohd;
    private javax.swing.JLabel lbtenNV;
    private javax.swing.JLabel lbtenNV1;
    private javax.swing.JLabel lbtenNV2;
    private javax.swing.JPanel mainpanel;
    private keeptoo.Mybutton2 mybutton24;
    private keeptoo.Mybutton2 mybutton25;
    private keeptoo.Mybutton2 mybutton26;
    private keeptoo.Mybutton2 mybutton28;
    private keeptoo.Mybutton2 mybutton30;
    private keeptoo.Mybutton2 mybutton31;
    private keeptoo.Mybutton2 mybutton32;
    private keeptoo.Mybutton2 mybutton33;
    private javax.swing.JTextField ngaylapHD;
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
    private keeptoo.KGradientPanel panelMedInform4;
    private keeptoo.KGradientPanel panelMedInform5;
    private keeptoo.KGradientPanel panelReset2;
    private keeptoo.KGradientPanel panelReset3;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoHD;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtchange;
    private javax.swing.JTextField txtdelete;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtpayment;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txtresult;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearch2;
    private javax.swing.JTextField txttax;
    private javax.swing.JTextField txttongtien;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
