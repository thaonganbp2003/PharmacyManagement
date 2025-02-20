package GUI;

import DTO.*;
import BLL.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.util.ArrayList;

public class QLNhaCungCap extends javax.swing.JFrame {

    /**
     * Creates new form EmpDashBoard
     */
    public QLNhaCungCap() {
        initComponents();
        setLocationRelativeTo(null);
        LoadTableNCC(NCC.dsNhaCungCap());

    }

    DefaultTableModel tblModelNCC;
    NhaCungCapBLL NCC = new NhaCungCapBLL();
    private String VAITRO = DangNhap.frmInstance.txt1;

    public void LoadTableNCC(ArrayList<NhaCungCap> list) {
        tblModelNCC = new DefaultTableModel();
        String title[] = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ"};
        tblModelNCC.setColumnIdentifiers(title);

        NhaCungCap ncc = new NhaCungCap();
        for (int i = 0; i < list.size(); i++) {
            ncc = list.get(i);
            int maNCC = ncc.getMaNCC();
            String tenNCC = ncc.getTenNCC();
            String SDT = ncc.getSoDT();
            String email = ncc.getEmail();
            String diachi = ncc.getDiachi();
            Object[] row = {maNCC, tenNCC, SDT, email, diachi};
            tblModelNCC.addRow(row);
        }
        tblDS.setModel(tblModelNCC);
        setVisible(true);

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
        lbmaNCC = new javax.swing.JLabel();
        txtmaNCC = new javax.swing.JTextField();
        lbtenNCC = new javax.swing.JLabel();
        lbsoDT = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        lbDiachi = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        imgBack = new javax.swing.JLabel();
        btnBack = new keeptoo.Mybutton();
        imglogo = new javax.swing.JLabel();
        imgpill = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDS = new javax.swing.JTable();
        panelReset = new keeptoo.KGradientPanel();
        btnLamMoi = new keeptoo.Mybutton2();
        pnlTenTrang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        imgpill1 = new javax.swing.JLabel();
        imgTK = new javax.swing.JLabel();
        btnTK = new keeptoo.Mybutton();
        panelBtn = new keeptoo.KGradientPanel();
        btnThem = new keeptoo.Mybutton2();
        btnCapNhat = new keeptoo.Mybutton2();
        btnXoa = new keeptoo.Mybutton2();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        datecreatebill.setDateFormat("dd/MM/yyyy");
        datecreatebill.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

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

        lbmaNCC.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbmaNCC.setForeground(new java.awt.Color(255, 255, 255));
        lbmaNCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbmaNCC.setText("Mã nhà cung cấp");

        txtmaNCC.setEditable(false);
        txtmaNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmaNCC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmaNCC.setBorder(null);
        txtmaNCC.setOpaque(true);

        lbtenNCC.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbtenNCC.setForeground(new java.awt.Color(255, 255, 255));
        lbtenNCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtenNCC.setText("Tên nhà cung cấp");
        lbtenNCC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbsoDT.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbsoDT.setForeground(new java.awt.Color(255, 255, 255));
        lbsoDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbsoDT.setText("SĐT");

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail.setBorder(null);

        lbEmail.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 255, 255));
        lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail.setText("Email");
        lbEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbDiachi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbDiachi.setForeground(new java.awt.Color(255, 255, 255));
        lbDiachi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDiachi.setText("Địa chỉ");
        lbDiachi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenNCC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTenNCC.setBorder(null);
        txtTenNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNCCActionPerformed(evt);
            }
        });

        txtDiachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiachi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiachi.setBorder(null);

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSDT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSDT.setBorder(null);

        javax.swing.GroupLayout GradientPaneltopLayout = new javax.swing.GroupLayout(GradientPaneltop);
        GradientPaneltop.setLayout(GradientPaneltopLayout);
        GradientPaneltopLayout.setHorizontalGroup(
            GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GradientPaneltopLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GradientPaneltopLayout.createSequentialGroup()
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbmaNCC)
                            .addComponent(lbtenNCC))
                        .addGap(28, 28, 28)
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(txtmaNCC))
                        .addGap(114, 114, 114)
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbsoDT)
                            .addComponent(lbEmail))
                        .addGap(29, 29, 29)
                        .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtSDT)))
                    .addGroup(GradientPaneltopLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lbDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        GradientPaneltopLayout.setVerticalGroup(
            GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GradientPaneltopLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbtenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(GradientPaneltopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDiachi)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        mainpanel.add(GradientPaneltop);
        GradientPaneltop.setBounds(30, 40, 750, 150);

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

        imgBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-arrow.png"))); // NOI18N
        mainpanel.add(imgBack);
        imgBack.setBounds(890, 622, 45, 45);

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

        imglogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imglogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        mainpanel.add(imglogo);
        imglogo.setBounds(870, 40, 171, 120);

        imgpill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine1 (4).png"))); // NOI18N
        mainpanel.add(imgpill);
        imgpill.setBounds(940, 250, 41, 41);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblDS.setAutoCreateRowSorter(true);
        tblDS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDS.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDS);

        mainpanel.add(jScrollPane1);
        jScrollPane1.setBounds(40, 360, 740, 310);

        panelReset.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelReset.setkEndColor(new java.awt.Color(16, 123, 162));
        panelReset.setkGradientFocus(550);
        panelReset.setkStartColor(new java.awt.Color(16, 123, 162));
        panelReset.setLayout(null);

        btnLamMoi.setBorder(null);
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFocusable(false);
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoi.setRadius(35);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        panelReset.add(btnLamMoi);
        btnLamMoi.setBounds(60, 20, 130, 40);

        mainpanel.add(panelReset);
        panelReset.setBounds(830, 460, 250, 80);

        pnlTenTrang.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 6, 0, new java.awt.Color(5, 191, 219)));
        pnlTenTrang.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quản lý nhà cung cấp");

        javax.swing.GroupLayout pnlTenTrangLayout = new javax.swing.GroupLayout(pnlTenTrang);
        pnlTenTrang.setLayout(pnlTenTrangLayout);
        pnlTenTrangLayout.setHorizontalGroup(
            pnlTenTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTenTrangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTenTrangLayout.setVerticalGroup(
            pnlTenTrangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTenTrangLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainpanel.add(pnlTenTrang);
        pnlTenTrang.setBounds(830, 180, 250, 60);

        imgpill1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine3 (2).png"))); // NOI18N
        mainpanel.add(imgpill1);
        imgpill1.setBounds(870, 250, 40, 41);

        imgTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        mainpanel.add(imgTK);
        imgTK.setBounds(590, 300, 35, 40);

        btnTK.setBorder(null);
        btnTK.setText("      Tìm kiếm");
        btnTK.setFocusable(false);
        btnTK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTK.setRadius(40);
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });
        mainpanel.add(btnTK);
        btnTK.setBounds(580, 300, 130, 40);

        panelBtn.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(5, 191, 219)));
        panelBtn.setkEndColor(new java.awt.Color(16, 123, 162));
        panelBtn.setkGradientFocus(550);
        panelBtn.setkStartColor(new java.awt.Color(16, 123, 162));
        panelBtn.setLayout(null);

        btnThem.setBorder(null);
        btnThem.setText("Thêm");
        btnThem.setFocusable(false);
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setRadius(35);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panelBtn.add(btnThem);
        btnThem.setBounds(110, 20, 110, 40);

        btnCapNhat.setBorder(null);
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.setFocusable(false);
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCapNhat.setRadius(35);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        panelBtn.add(btnCapNhat);
        btnCapNhat.setBounds(280, 20, 130, 40);

        btnXoa.setBorder(null);
        btnXoa.setText("Xóa");
        btnXoa.setFocusable(false);
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setRadius(35);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        panelBtn.add(btnXoa);
        btnXoa.setBounds(470, 20, 120, 40);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (5).png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelBtn.add(jLabel2);
        jLabel2.setBounds(30, 30, 41, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicine2 (3).png"))); // NOI18N
        panelBtn.add(jLabel1);
        jLabel1.setBounds(660, 26, 41, 50);

        mainpanel.add(panelBtn);
        panelBtn.setBounds(40, 200, 740, 80);

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mainpanel.add(txtTimKiem);
        txtTimKiem.setBounds(150, 302, 400, 30);

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

    private void txtTenNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNCCActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtTenNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được bỏ trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtDiachi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Email không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(Integer.parseInt(txtmaNCC.getText()));
            ncc.setTenNCC(txtTenNCC.getText());
            ncc.setSoDT(txtSDT.getText());
            ncc.setEmail(txtEmail.getText());
            ncc.setDiachi(txtDiachi.getText());
            try {
                int result = NCC.insertNCC(ncc);
                if (result != 0) {
                    JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                LoadTableNCC(NCC.dsNhaCungCap());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if (txtTenNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được bỏ trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtDiachi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Email không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được bỏ trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            NhaCungCap ncc = new NhaCungCap();
            ncc.setMaNCC(Integer.parseInt(txtmaNCC.getText()));
            ncc.setTenNCC(txtTenNCC.getText());
            ncc.setSoDT(txtSDT.getText());
            ncc.setEmail(txtEmail.getText());
            ncc.setDiachi(txtDiachi.getText());

            try {
                int result = NCC.updateNCC(ncc);
                if (result != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                LoadTableNCC(NCC.dsNhaCungCap());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(Integer.parseInt(txtmaNCC.getText()));
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa dữ liệu!", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                int result = NCC.insertNCC(ncc);
                if (result != 0) {
                    JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                LoadTableNCC(NCC.dsNhaCungCap());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
        LoadTableNCC(NCC.TimKiemNCC(txtTimKiem.getText()));

    }//GEN-LAST:event_btnTKActionPerformed

    private void tblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSMouseClicked
        // TODO add your handling code here:
        int indexTB = tblDS.getSelectedRow();

        if (indexTB < tblModelNCC.getRowCount() && indexTB >= 0) {
            txtmaNCC.setText(tblModelNCC.getValueAt(indexTB, 0).toString());
            txtTenNCC.setText(tblModelNCC.getValueAt(indexTB, 1).toString());
            txtSDT.setText(tblModelNCC.getValueAt(indexTB, 2).toString());
            txtEmail.setText(tblModelNCC.getValueAt(indexTB, 3).toString());
            txtDiachi.setText(tblModelNCC.getValueAt(indexTB, 4).toString());
        }
    }//GEN-LAST:event_tblDSMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        LoadTableNCC(NCC.dsNhaCungCap());
    }//GEN-LAST:event_btnLamMoiActionPerformed

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
                new QLNhaCungCap().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel GradientPaneltop;
    private keeptoo.Mybutton btnBack;
    private keeptoo.Mybutton2 btnCapNhat;
    private keeptoo.Mybutton2 btnLamMoi;
    private keeptoo.Mybutton btnTK;
    private keeptoo.Mybutton2 btnThem;
    private keeptoo.Mybutton2 btnXoa;
    private com.raven.datechooser.DateChooser datecreatebill;
    private javax.swing.JLabel imgBack;
    private javax.swing.JLabel imgTK;
    private javax.swing.JLabel imglogo;
    private javax.swing.JLabel imgpill;
    private javax.swing.JLabel imgpill1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel4;
    private javax.swing.JLabel lbDiachi;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbmaNCC;
    private javax.swing.JLabel lbsoDT;
    private javax.swing.JLabel lbtenNCC;
    private javax.swing.JPanel mainpanel;
    private keeptoo.KGradientPanel panelBtn;
    private keeptoo.KGradientPanel panelReset;
    private javax.swing.JPanel pnlTenTrang;
    private javax.swing.JTable tblDS;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtmaNCC;
    // End of variables declaration//GEN-END:variables
}
