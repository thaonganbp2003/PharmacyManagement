package GUI;

import DAL.ThuocDAL;
import DTO.*;
import BLL.*;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import Utils.DBUtils;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import keeptoo.TableActionEvent;

public class Employee extends javax.swing.JFrame {

    /**
     * Creates new form EmpDashBoard
     */
    public Employee() {
        initComponents();
        setLocationRelativeTo(null);
        LoadEmployee(ebll.Load_BLL());

    }
    NhanVienBLL ebll = new NhanVienBLL();
    DefaultTableModel tableModelEmployee;
    TaiKhoanBLL tkbll = new TaiKhoanBLL();
    private String VAITRO = DangNhap.frmInstance.txt1;

    public void LoadEmployee(ArrayList<NhanVien> dsnhan) {
        tableModelEmployee = new DefaultTableModel();
        String title[] = {"Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "email", "Số điện thoại", "Ngày vào làm", "Địa chỉ", "Tên đăng nhập", "Mật khẩu"};
        tableModelEmployee.setColumnIdentifiers(title);
        NhanVien t = new NhanVien();
        TaiKhoan m = new TaiKhoan();
        for (int i = 0; i < dsnhan.size(); i++) {
            t = dsnhan.get(i);
            String MANV = t.getMaNV();
            String HOTEN = t.getHoten();
            String GIOITINH = t.getGioitinh();
            String NGAYSINH = t.getNgaysinh();
            String EMAIL = t.getEmail();
            String SODT = t.getSdt();
            String NGAYVAOLAM = t.getNgayvaolam();
            String DIACHI = t.getDiachi();
            int TRANGTHAI = t.getTRANGTHAI();

            Date date = convertStringtoDate(NGAYSINH, "yyyy-MM-dd");
            Date date1 = convertStringtoDate(NGAYVAOLAM, "yyyy-MM-dd");
            String result = convertDatetoString(date, "dd-MM-yyyy");
            String result1 = convertDatetoString(date1, "dd-MM-yyyy");
            ArrayList<TaiKhoan> tk = tkbll.TìmTaiKhoan(MANV);
            if (tk.isEmpty()) {

            } else {
                m = tk.get(0);
                String username = m.getTenTaiKhoan();
                String password = m.getMatKhau();
                if (TRANGTHAI == 1) {
                    Object row[] = {MANV, HOTEN, GIOITINH, result, EMAIL, SODT, result1, DIACHI, username, password};
                    tableModelEmployee.addRow(row);
                }
            }

        }
        jTable2.setModel(tableModelEmployee);
    }

    public void LoadEmployeeForm(ArrayList<NhanVien> dsnhan) {
        NhanVien t = new NhanVien();
        for (int i = 0; i < dsnhan.size(); i++) {
            t = dsnhan.get(i);
            String MANV = t.getMaNV();
            String HOTEN = t.getHoten();
            String GIOITINH = t.getGioitinh();
            String NGAYSINH = t.getNgaysinh();
            String EMAIL = t.getEmail();
            String SODT = t.getSdt();
            String NGAYVAOLAM = t.getNgayvaolam();
            String DIACHI = t.getDiachi();

            Date date = convertStringtoDate(NGAYSINH, "yyyy-MM-dd");
            Date date1 = convertStringtoDate(NGAYVAOLAM, "yyyy-MM-dd");
            String result = convertDatetoString(date, "dd-MM-yyyy");
            String result1 = convertDatetoString(date1, "dd-MM-yyyy");

            txtid.setText(MANV);
            txtname2.setText(HOTEN);
            txtgender2.setSelectedItem(GIOITINH);
            txtdateofbirth2.setText(result);
            txtemail2.setText(EMAIL);
            txtphone2.setText(SODT);
            txtdatestart2.setText(result1);
            txtaddress2.setText(DIACHI);

            txtid1.setText(MANV);
            txtname3.setText(HOTEN);
            txtgender3.setSelectedItem(GIOITINH);
            txtdateofbirth3.setText(result);
            txtemail3.setText(EMAIL);
            txtphone3.setText(SODT);
            txtdatestart3.setText(result1);
            txtaddress3.setText(DIACHI);
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

        dateofbirth = new com.raven.datechooser.DateChooser();
        startdate = new com.raven.datechooser.DateChooser();
        dateofbirth1 = new com.raven.datechooser.DateChooser();
        startdate1 = new com.raven.datechooser.DateChooser();
        mainpanel = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        txtTimKiem = new javax.swing.JTextField();
        imgSearch = new javax.swing.JLabel();
        btnTimKiem = new keeptoo.Mybutton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        insert = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        lbHoten = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        lbgt = new javax.swing.JLabel();
        lbngaysinh = new javax.swing.JLabel();
        txtdateofbirth = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtdatestart = new javax.swing.JTextField();
        txtphone = new javax.swing.JTextField();
        lbmail = new javax.swing.JLabel();
        lbsdt = new javax.swing.JLabel();
        lbngayvl = new javax.swing.JLabel();
        lbdiachi = new javax.swing.JLabel();
        txtgender = new javax.swing.JComboBox<>();
        btnNgayvaolam = new javax.swing.JButton();
        btnNgaysinh = new javax.swing.JButton();
        lbdiachi1 = new javax.swing.JLabel();
        lbdiachi4 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        lbHoten1 = new javax.swing.JLabel();
        vaitro = new javax.swing.JComboBox<>();
        btnsave = new keeptoo.Mybutton();
        update = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        lbHoten2 = new javax.swing.JLabel();
        txtname2 = new javax.swing.JTextField();
        lbgt2 = new javax.swing.JLabel();
        lbngaysinh2 = new javax.swing.JLabel();
        txtdateofbirth2 = new javax.swing.JTextField();
        txtemail2 = new javax.swing.JTextField();
        txtaddress2 = new javax.swing.JTextField();
        txtdatestart2 = new javax.swing.JTextField();
        txtphone2 = new javax.swing.JTextField();
        lbmail2 = new javax.swing.JLabel();
        lbsdt2 = new javax.swing.JLabel();
        lbngayvl2 = new javax.swing.JLabel();
        lbdiachi2 = new javax.swing.JLabel();
        txtgender2 = new javax.swing.JComboBox<>();
        btnNgayvaolam1 = new javax.swing.JButton();
        btnNgaysinh1 = new javax.swing.JButton();
        lbHoten3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        btnsave1 = new keeptoo.Mybutton();
        delete = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btnsave2 = new keeptoo.Mybutton();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        lbHoten4 = new javax.swing.JLabel();
        txtname3 = new javax.swing.JTextField();
        lbgt3 = new javax.swing.JLabel();
        lbngaysinh3 = new javax.swing.JLabel();
        txtdateofbirth3 = new javax.swing.JTextField();
        txtemail3 = new javax.swing.JTextField();
        txtaddress3 = new javax.swing.JTextField();
        txtdatestart3 = new javax.swing.JTextField();
        txtphone3 = new javax.swing.JTextField();
        lbmail3 = new javax.swing.JLabel();
        lbsdt3 = new javax.swing.JLabel();
        lbngayvl3 = new javax.swing.JLabel();
        lbdiachi3 = new javax.swing.JLabel();
        txtgender3 = new javax.swing.JComboBox<>();
        btnNgayvaolam2 = new javax.swing.JButton();
        btnNgaysinh2 = new javax.swing.JButton();
        lbHoten5 = new javax.swing.JLabel();
        txtid1 = new javax.swing.JTextField();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        lbThuoc = new javax.swing.JLabel();
        imgBack = new javax.swing.JLabel();
        btnBack = new keeptoo.Mybutton();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        imgInsert = new javax.swing.JLabel();
        imgList = new javax.swing.JLabel();
        btnDSThuoc = new keeptoo.Mybutton();
        btnadd = new keeptoo.Mybutton();
        jLabel14 = new javax.swing.JLabel();
        btndelete = new keeptoo.Mybutton();
        jLabel12 = new javax.swing.JLabel();
        btnupdate = new keeptoo.Mybutton();

        dateofbirth.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        dateofbirth.setTextRefernce(txtdateofbirth);

        startdate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        startdate.setTextRefernce(txtdatestart);

        dateofbirth1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        dateofbirth1.setTextRefernce(txtdateofbirth2);

        startdate1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        startdate1.setTextRefernce(txtdatestart2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(9, 131, 149));
        setMinimumSize(new java.awt.Dimension(1100, 783));

        mainpanel.setBackground(new java.awt.Color(10, 77, 105));
        mainpanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 15, 0, new java.awt.Color(8, 131, 149)));
        mainpanel.setForeground(new java.awt.Color(10, 77, 105));
        mainpanel.setMaximumSize(new java.awt.Dimension(1200, 750));
        mainpanel.setMinimumSize(new java.awt.Dimension(1101, 750));
        mainpanel.setPreferredSize(new java.awt.Dimension(1101, 750));
        mainpanel.setLayout(null);

        kGradientPanel1.setkEndColor(new java.awt.Color(8, 131, 149));
        kGradientPanel1.setkGradientFocus(550);
        kGradientPanel1.setkStartColor(new java.awt.Color(8, 131, 149));
        kGradientPanel1.setLayout(null);

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTimKiem.setToolTipText("");
        txtTimKiem.setBorder(null);
        txtTimKiem.setName(""); // NOI18N
        txtTimKiem.setOpaque(true);
        kGradientPanel1.add(txtTimKiem);
        txtTimKiem.setBounds(100, 10, 410, 30);

        imgSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        kGradientPanel1.add(imgSearch);
        imgSearch.setBounds(550, 10, 35, 35);

        btnTimKiem.setBorder(null);
        btnTimKiem.setText("    Tìm Kiếm");
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.setFocusable(false);
        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiem.setRadius(40);
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btnTimKiem);
        btnTimKiem.setBounds(540, 10, 140, 40);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel1.setBackground(new java.awt.Color(8, 131, 149));

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
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        insert.setBackground(new java.awt.Color(8, 131, 149));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Thêm Nhân Viên");

        kGradientPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(10, 77, 105)));
        kGradientPanel2.setkEndColor(new java.awt.Color(0, 255, 202));
        kGradientPanel2.setkGradientFocus(750);
        kGradientPanel2.setkStartColor(new java.awt.Color(5, 191, 219));
        kGradientPanel2.setPreferredSize(new java.awt.Dimension(715, 386));
        kGradientPanel2.setLayout(null);

        lbHoten.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbHoten.setForeground(new java.awt.Color(255, 255, 255));
        lbHoten.setText("Vai trò");
        kGradientPanel2.add(lbHoten);
        lbHoten.setBounds(460, 60, 60, 19);

        txtname.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        kGradientPanel2.add(txtname);
        txtname.setBounds(325, 23, 208, 25);

        lbgt.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbgt.setForeground(new java.awt.Color(255, 255, 255));
        lbgt.setText("Giới tính");
        kGradientPanel2.add(lbgt);
        lbgt.setBounds(180, 60, 84, 19);

        lbngaysinh.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbngaysinh.setForeground(new java.awt.Color(255, 255, 255));
        lbngaysinh.setText("Ngày sinh");
        kGradientPanel2.add(lbngaysinh);
        lbngaysinh.setBounds(180, 100, 84, 19);

        txtdateofbirth.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtdateofbirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdateofbirthActionPerformed(evt);
            }
        });
        kGradientPanel2.add(txtdateofbirth);
        txtdateofbirth.setBounds(330, 100, 206, 25);

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        kGradientPanel2.add(txtemail);
        txtemail.setBounds(330, 140, 206, 25);

        txtaddress.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        kGradientPanel2.add(txtaddress);
        txtaddress.setBounds(330, 260, 206, 25);

        txtdatestart.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        kGradientPanel2.add(txtdatestart);
        txtdatestart.setBounds(330, 220, 206, 25);

        txtphone.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        kGradientPanel2.add(txtphone);
        txtphone.setBounds(330, 180, 206, 25);

        lbmail.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbmail.setForeground(new java.awt.Color(255, 255, 255));
        lbmail.setText("Email");
        kGradientPanel2.add(lbmail);
        lbmail.setBounds(180, 140, 84, 19);

        lbsdt.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbsdt.setForeground(new java.awt.Color(255, 255, 255));
        lbsdt.setText("Số điện thoại");
        kGradientPanel2.add(lbsdt);
        lbsdt.setBounds(180, 180, 102, 19);

        lbngayvl.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbngayvl.setForeground(new java.awt.Color(255, 255, 255));
        lbngayvl.setText("Ngày vào làm");
        kGradientPanel2.add(lbngayvl);
        lbngayvl.setBounds(180, 220, 110, 19);

        lbdiachi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbdiachi.setForeground(new java.awt.Color(255, 255, 255));
        lbdiachi.setText("Mật khẩu");
        kGradientPanel2.add(lbdiachi);
        lbdiachi.setBounds(180, 340, 120, 19);

        txtgender.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        kGradientPanel2.add(txtgender);
        txtgender.setBounds(330, 60, 72, 25);
        txtgender.getAccessibleContext().setAccessibleDescription("");

        btnNgayvaolam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgayvaolam.setBorder(null);
        btnNgayvaolam.setContentAreaFilled(false);
        btnNgayvaolam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayvaolamActionPerformed(evt);
            }
        });
        kGradientPanel2.add(btnNgayvaolam);
        btnNgayvaolam.setBounds(540, 220, 34, 24);

        btnNgaysinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgaysinh.setBorder(null);
        btnNgaysinh.setContentAreaFilled(false);
        btnNgaysinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaysinhActionPerformed(evt);
            }
        });
        kGradientPanel2.add(btnNgaysinh);
        btnNgaysinh.setBounds(540, 100, 34, 24);

        lbdiachi1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbdiachi1.setForeground(new java.awt.Color(255, 255, 255));
        lbdiachi1.setText("Địa chỉ");
        kGradientPanel2.add(lbdiachi1);
        lbdiachi1.setBounds(180, 260, 84, 19);

        lbdiachi4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbdiachi4.setForeground(new java.awt.Color(255, 255, 255));
        lbdiachi4.setText("Tên đăng nhập");
        kGradientPanel2.add(lbdiachi4);
        lbdiachi4.setBounds(180, 300, 120, 19);
        kGradientPanel2.add(username);
        username.setBounds(330, 300, 160, 22);
        kGradientPanel2.add(password);
        password.setBounds(330, 340, 160, 22);

        lbHoten1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbHoten1.setForeground(new java.awt.Color(255, 255, 255));
        lbHoten1.setText("Họ tên");
        kGradientPanel2.add(lbHoten1);
        lbHoten1.setBounds(180, 20, 84, 19);

        kGradientPanel2.add(vaitro);
        vaitro.setBounds(530, 60, 100, 22);

        btnsave.setText("Lưu");
        btnsave.setBorderPainted(false);
        btnsave.setFocusable(false);
        btnsave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave.setRadius(39);
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout insertLayout = new javax.swing.GroupLayout(insert);
        insert.setLayout(insertLayout);
        insertLayout.setHorizontalGroup(
            insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertLayout.createSequentialGroup()
                .addGroup(insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertLayout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jLabel22))
                    .addGroup(insertLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(insertLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(829, 829, 829))
        );
        insertLayout.setVerticalGroup(
            insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
        );

        jTabbedPane1.addTab("tab2", insert);

        update.setBackground(new java.awt.Color(8, 131, 149));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Chỉnh Sửa Thông Tin");

        kGradientPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(10, 77, 105)));
        kGradientPanel6.setkEndColor(new java.awt.Color(0, 255, 202));
        kGradientPanel6.setkGradientFocus(750);
        kGradientPanel6.setkStartColor(new java.awt.Color(5, 191, 219));
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(715, 386));

        lbHoten2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbHoten2.setForeground(new java.awt.Color(255, 255, 255));
        lbHoten2.setText("Họ tên");

        txtname2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lbgt2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbgt2.setForeground(new java.awt.Color(255, 255, 255));
        lbgt2.setText("Giới tính");

        lbngaysinh2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbngaysinh2.setForeground(new java.awt.Color(255, 255, 255));
        lbngaysinh2.setText("Ngày sinh");

        txtdateofbirth2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtdateofbirth2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdateofbirth2ActionPerformed(evt);
            }
        });

        txtemail2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtaddress2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtdatestart2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtphone2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lbmail2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbmail2.setForeground(new java.awt.Color(255, 255, 255));
        lbmail2.setText("Email");

        lbsdt2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbsdt2.setForeground(new java.awt.Color(255, 255, 255));
        lbsdt2.setText("Số điện thoại");

        lbngayvl2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbngayvl2.setForeground(new java.awt.Color(255, 255, 255));
        lbngayvl2.setText("Ngày vào làm");

        lbdiachi2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbdiachi2.setForeground(new java.awt.Color(255, 255, 255));
        lbdiachi2.setText("Địa chỉ");

        txtgender2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtgender2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btnNgayvaolam1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgayvaolam1.setBorder(null);
        btnNgayvaolam1.setContentAreaFilled(false);
        btnNgayvaolam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayvaolam1ActionPerformed(evt);
            }
        });

        btnNgaysinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgaysinh1.setBorder(null);
        btnNgaysinh1.setContentAreaFilled(false);
        btnNgaysinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaysinh1ActionPerformed(evt);
            }
        });

        lbHoten3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbHoten3.setForeground(new java.awt.Color(255, 255, 255));
        lbHoten3.setText("Mã nhân viên");

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbHoten2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbgt2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbngayvl2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbdiachi2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbsdt2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbHoten3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addComponent(txtdateofbirth2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgaysinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtemail2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphone2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addComponent(txtdatestart2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgayvaolam1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtgender2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel6Layout.createSequentialGroup()
                    .addGap(174, 174, 174)
                    .addComponent(lbngaysinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(442, Short.MAX_VALUE)))
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoten3)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lbHoten2))
                    .addGroup(kGradientPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtname2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbgt2)
                    .addComponent(txtgender2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdateofbirth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNgaysinh1))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbmail2))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtphone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsdt2))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdatestart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbngayvl2))
                    .addComponent(btnNgayvaolam1))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtaddress2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbdiachi2))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel6Layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(lbngaysinh2)
                    .addContainerGap(219, Short.MAX_VALUE)))
        );

        btnsave1.setText("Lưu");
        btnsave1.setBorderPainted(false);
        btnsave1.setFocusable(false);
        btnsave1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave1.setRadius(39);
        btnsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update);
        update.setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLayout.createSequentialGroup()
                        .addComponent(btnsave1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(307, 307, 307))))
            .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(updateLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE)))
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(402, 402, 402)
                .addComponent(btnsave1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(updateLayout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab3", update);

        delete.setBackground(new java.awt.Color(8, 131, 149));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Xóa Nhân Viên");

        btnsave2.setText("Xóa dữ liệu");
        btnsave2.setBorderPainted(false);
        btnsave2.setFocusable(false);
        btnsave2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave2.setRadius(39);
        btnsave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsave2ActionPerformed(evt);
            }
        });

        kGradientPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(10, 77, 105)));
        kGradientPanel7.setkEndColor(new java.awt.Color(0, 255, 202));
        kGradientPanel7.setkGradientFocus(750);
        kGradientPanel7.setkStartColor(new java.awt.Color(5, 191, 219));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(715, 386));

        lbHoten4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbHoten4.setForeground(new java.awt.Color(255, 255, 255));
        lbHoten4.setText("Họ tên");

        txtname3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lbgt3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbgt3.setForeground(new java.awt.Color(255, 255, 255));
        lbgt3.setText("Giới tính");

        lbngaysinh3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbngaysinh3.setForeground(new java.awt.Color(255, 255, 255));
        lbngaysinh3.setText("Ngày sinh");

        txtdateofbirth3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtdateofbirth3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdateofbirth3ActionPerformed(evt);
            }
        });

        txtemail3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtaddress3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtdatestart3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtphone3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lbmail3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbmail3.setForeground(new java.awt.Color(255, 255, 255));
        lbmail3.setText("Email");

        lbsdt3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbsdt3.setForeground(new java.awt.Color(255, 255, 255));
        lbsdt3.setText("Số điện thoại");

        lbngayvl3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbngayvl3.setForeground(new java.awt.Color(255, 255, 255));
        lbngayvl3.setText("Ngày vào làm");

        lbdiachi3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbdiachi3.setForeground(new java.awt.Color(255, 255, 255));
        lbdiachi3.setText("Địa chỉ");

        txtgender3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtgender3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btnNgayvaolam2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgayvaolam2.setBorder(null);
        btnNgayvaolam2.setContentAreaFilled(false);
        btnNgayvaolam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayvaolam2ActionPerformed(evt);
            }
        });

        btnNgaysinh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnNgaysinh2.setBorder(null);
        btnNgaysinh2.setContentAreaFilled(false);
        btnNgaysinh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaysinh2ActionPerformed(evt);
            }
        });

        lbHoten5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbHoten5.setForeground(new java.awt.Color(255, 255, 255));
        lbHoten5.setText("Mã nhân viên");

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbHoten4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbgt3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbngayvl3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbdiachi3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbsdt3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbHoten5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addComponent(txtdateofbirth3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgaysinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtemail3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphone3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addComponent(txtdatestart3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNgayvaolam2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtgender3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaddress3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel7Layout.createSequentialGroup()
                    .addGap(174, 174, 174)
                    .addComponent(lbngaysinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(441, Short.MAX_VALUE)))
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoten5)
                    .addComponent(txtid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lbHoten4))
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtname3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbgt3)
                    .addComponent(txtgender3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdateofbirth3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNgaysinh2))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbmail3))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtphone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsdt3))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdatestart3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbngayvl3))
                    .addComponent(btnNgayvaolam2))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtaddress3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbdiachi3))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel7Layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(lbngaysinh3)
                    .addContainerGap(219, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout deleteLayout = new javax.swing.GroupLayout(delete);
        delete.setLayout(deleteLayout);
        deleteLayout.setHorizontalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addContainerGap(332, Short.MAX_VALUE)
                .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteLayout.createSequentialGroup()
                        .addComponent(btnsave2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(299, 299, 299))))
            .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(deleteLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE)))
        );
        deleteLayout.setVerticalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(416, 416, 416)
                .addComponent(btnsave2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(deleteLayout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab3", delete);

        kGradientPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(-50, 50, 840, 530);

        mainpanel.add(kGradientPanel1);
        kGradientPanel1.setBounds(0, 60, 790, 600);

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

        kGradientPanel5.setkEndColor(new java.awt.Color(5, 191, 219));
        kGradientPanel5.setkGradientFocus(175);
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 255, 255));

        lbThuoc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbThuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThuoc.setText("Nhân Viên");
        lbThuoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbThuoc.setInheritsPopupMenu(false);

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(lbThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lbThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lbThuoc.getAccessibleContext().setAccessibleName("");

        mainpanel.add(kGradientPanel5);
        kGradientPanel5.setBounds(830, 180, 250, 70);

        imgBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-arrow.png"))); // NOI18N
        mainpanel.add(imgBack);
        imgBack.setBounds(890, 600, 45, 45);

        btnBack.setBorder(null);
        btnBack.setText("      Quay lại");
        btnBack.setBorderPainted(false);
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
        btnBack.setBounds(880, 600, 160, 50);

        jPanel8.setBackground(new java.awt.Color(4, 191, 219));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        mainpanel.add(jPanel8);
        jPanel8.setBounds(0, 50, 810, 10);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine3 (1).png"))); // NOI18N
        mainpanel.add(jLabel3);
        jLabel3.setBounds(390, 10, 41, 41);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        mainpanel.add(jLabel4);
        jLabel4.setBounds(610, 10, 41, 41);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (5).png"))); // NOI18N
        mainpanel.add(jLabel5);
        jLabel5.setBounds(220, 10, 41, 41);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        mainpanel.add(jLabel7);
        jLabel7.setBounds(870, 30, 171, 120);

        imgInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert.png"))); // NOI18N
        mainpanel.add(imgInsert);
        imgInsert.setBounds(890, 360, 43, 43);

        imgList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder.png"))); // NOI18N
        mainpanel.add(imgList);
        imgList.setBounds(890, 290, 43, 43);

        btnDSThuoc.setBorder(null);
        btnDSThuoc.setText("        Danh sách");
        btnDSThuoc.setToolTipText("");
        btnDSThuoc.setBorderPainted(false);
        btnDSThuoc.setFocusable(false);
        btnDSThuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDSThuoc.setRadius(39);
        btnDSThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSThuocActionPerformed(evt);
            }
        });
        mainpanel.add(btnDSThuoc);
        btnDSThuoc.setBounds(880, 290, 160, 50);

        btnadd.setText("    Thêm");
        btnadd.setBorderPainted(false);
        btnadd.setFocusable(false);
        btnadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnadd.setRadius(39);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        mainpanel.add(btnadd);
        btnadd.setBounds(880, 360, 160, 50);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trash.png"))); // NOI18N
        mainpanel.add(jLabel14);
        jLabel14.setBounds(890, 500, 43, 40);

        btndelete.setText("    Xóa");
        btndelete.setBorderPainted(false);
        btndelete.setFocusable(false);
        btndelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndelete.setRadius(39);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        mainpanel.add(btndelete);
        btndelete.setBounds(880, 500, 160, 50);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shuffle.png"))); // NOI18N
        mainpanel.add(jLabel12);
        jLabel12.setBounds(890, 430, 43, 43);

        btnupdate.setText("      Chỉnh sửa");
        btnupdate.setBorderPainted(false);
        btnupdate.setFocusable(false);
        btnupdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnupdate.setRadius(39);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        mainpanel.add(btnupdate);
        btnupdate.setBounds(880, 430, 160, 50);

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

    private void btnDSThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSThuocActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
        LoadEmployee(ebll.Load_BLL());
    }//GEN-LAST:event_btnDSThuocActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        vaitro.addItem("Quản lý");
        vaitro.addItem("Nhân viên");
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        LoadEmployee(ebll.Search_BLL(txtTimKiem.getText()));
        LoadEmployeeForm(ebll.Search_BLL(txtTimKiem.getText()));
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        try {
            if (txtname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống");
            } else if (txtgender.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Giới tính không được bỏ trống");
            } else if (txtdateofbirth.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không được bỏ trống");
            } else if (txtemail.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Email không được bỏ trống");
            } else if (txtphone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không được bỏ trống");
            } else if (txtdatestart.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu làm không được bỏ trống");
            } else if (txtaddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống");
            }else if (username.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên đăng nhập không được bỏ trống");
            }else if (password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Mật khẩu không được bỏ trống");
            } else {
                int VAITRO;
                if(vaitro.getSelectedItem().toString().equals("Quản lý")){
                    VAITRO =0;
                }else{
                    VAITRO=1;
                }
                boolean check = ebll.Insert(txtname.getText(), String.valueOf(txtgender.getSelectedItem()), txtdateofbirth.getText(), txtemail.getText(), txtphone.getText(), txtdatestart.getText(), txtaddress.getText(), 1,username.getText(),password.getText(),VAITRO);
                if (check == true) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave1ActionPerformed
        // TODO add your handling code here:
        try {
            if (txtname2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên nhân viên không được bỏ trống");
            } else if (txtgender2.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Giới tính không được bỏ trống");
            } else if (txtdateofbirth2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không được bỏ trống");
            } else if (txtemail2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Email không được bỏ trống");
            } else if (txtphone2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không được bỏ trống");
            } else if (txtdatestart2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu làm không được bỏ trống");
            } else if (txtaddress2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống");
            } else {

                boolean check = ebll.Update(txtid.getText(), txtname2.getText(), String.valueOf(txtgender2.getSelectedItem()), txtdateofbirth2.getText(), txtemail2.getText(), txtphone2.getText(), txtdatestart2.getText(), txtaddress2.getText(), 1);
                if (check == true) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnsave1ActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnsave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave2ActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa dữ liệu!", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                boolean check = ebll.Delete(txtid1.getText(), txtname3.getText(), String.valueOf(txtgender3.getSelectedItem()), txtdateofbirth3.getText(), txtemail3.getText(), txtphone3.getText(), txtdatestart3.getText(), txtaddress3.getText(), 0);
                if (check == true) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa không thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnsave2ActionPerformed

    private void txtdateofbirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdateofbirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateofbirthActionPerformed

    private void btnNgaysinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaysinhActionPerformed
        // TODO add your handling code here:
        dateofbirth.showPopup();
    }//GEN-LAST:event_btnNgaysinhActionPerformed

    private void btnNgayvaolamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayvaolamActionPerformed
        // TODO add your handling code here:
        startdate.showPopup();
    }//GEN-LAST:event_btnNgayvaolamActionPerformed

    private void txtdateofbirth2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdateofbirth2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateofbirth2ActionPerformed

    private void btnNgayvaolam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayvaolam1ActionPerformed
        // TODO add your handling code here:
        startdate1.showPopup();
    }//GEN-LAST:event_btnNgayvaolam1ActionPerformed

    private void btnNgaysinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaysinh1ActionPerformed
        // TODO add your handling code here:
        dateofbirth1.showPopup();
    }//GEN-LAST:event_btnNgaysinh1ActionPerformed

    private void txtdateofbirth3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdateofbirth3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateofbirth3ActionPerformed

    private void btnNgayvaolam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayvaolam2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNgayvaolam2ActionPerformed

    private void btnNgaysinh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaysinh2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNgaysinh2ActionPerformed

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
                new Employee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.Mybutton btnBack;
    private keeptoo.Mybutton btnDSThuoc;
    private javax.swing.JButton btnNgaysinh;
    private javax.swing.JButton btnNgaysinh1;
    private javax.swing.JButton btnNgaysinh2;
    private javax.swing.JButton btnNgayvaolam;
    private javax.swing.JButton btnNgayvaolam1;
    private javax.swing.JButton btnNgayvaolam2;
    private keeptoo.Mybutton btnTimKiem;
    private keeptoo.Mybutton btnadd;
    private keeptoo.Mybutton btndelete;
    private keeptoo.Mybutton btnsave;
    private keeptoo.Mybutton btnsave1;
    private keeptoo.Mybutton btnsave2;
    private keeptoo.Mybutton btnupdate;
    private com.raven.datechooser.DateChooser dateofbirth;
    private com.raven.datechooser.DateChooser dateofbirth1;
    private javax.swing.JPanel delete;
    private javax.swing.JLabel imgBack;
    private javax.swing.JLabel imgInsert;
    private javax.swing.JLabel imgList;
    private javax.swing.JLabel imgSearch;
    private javax.swing.JPanel insert;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private javax.swing.JLabel lbHoten;
    private javax.swing.JLabel lbHoten1;
    private javax.swing.JLabel lbHoten2;
    private javax.swing.JLabel lbHoten3;
    private javax.swing.JLabel lbHoten4;
    private javax.swing.JLabel lbHoten5;
    private javax.swing.JLabel lbThuoc;
    private javax.swing.JLabel lbdiachi;
    private javax.swing.JLabel lbdiachi1;
    private javax.swing.JLabel lbdiachi2;
    private javax.swing.JLabel lbdiachi3;
    private javax.swing.JLabel lbdiachi4;
    private javax.swing.JLabel lbgt;
    private javax.swing.JLabel lbgt2;
    private javax.swing.JLabel lbgt3;
    private javax.swing.JLabel lbmail;
    private javax.swing.JLabel lbmail2;
    private javax.swing.JLabel lbmail3;
    private javax.swing.JLabel lbngaysinh;
    private javax.swing.JLabel lbngaysinh2;
    private javax.swing.JLabel lbngaysinh3;
    private javax.swing.JLabel lbngayvl;
    private javax.swing.JLabel lbngayvl2;
    private javax.swing.JLabel lbngayvl3;
    private javax.swing.JLabel lbsdt;
    private javax.swing.JLabel lbsdt2;
    private javax.swing.JLabel lbsdt3;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTextField password;
    private com.raven.datechooser.DateChooser startdate;
    private com.raven.datechooser.DateChooser startdate1;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtaddress2;
    private javax.swing.JTextField txtaddress3;
    private javax.swing.JTextField txtdateofbirth;
    private javax.swing.JTextField txtdateofbirth2;
    private javax.swing.JTextField txtdateofbirth3;
    private javax.swing.JTextField txtdatestart;
    private javax.swing.JTextField txtdatestart2;
    private javax.swing.JTextField txtdatestart3;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtemail2;
    private javax.swing.JTextField txtemail3;
    private javax.swing.JComboBox<String> txtgender;
    private javax.swing.JComboBox<String> txtgender2;
    private javax.swing.JComboBox<String> txtgender3;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid1;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtname2;
    private javax.swing.JTextField txtname3;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtphone2;
    private javax.swing.JTextField txtphone3;
    private javax.swing.JPanel update;
    private javax.swing.JTextField username;
    private javax.swing.JComboBox<String> vaitro;
    // End of variables declaration//GEN-END:variables
}
