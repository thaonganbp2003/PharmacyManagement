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
import com.raven.chart.ModelChart;
import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import keeptoo.TableActionEvent;

public class Medicine extends javax.swing.JFrame {

    /**
     * Creates new form EmpDashBoard
     */
    public Medicine() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 250, 250));
        chart1.addLegend("Sắp hết hạn sử dụng", new Color(255, 99, 71));
        chart1.addLegend("Còn hạn sử dụng", new Color(0, 245, 255));
        LoadMedicine(mbll.Load_BLL());

    }
    ThuocBLL mbll = new ThuocBLL();
    KhoThuocBLL ktbll = new KhoThuocBLL();
    DefaultTableModel tableModelMedicine;
    private String VAITRO = DangNhap.frmInstance.txt1;

    public void LoadMedicine(ArrayList<Thuoc> dsthuoc) {
        tableModelMedicine = new DefaultTableModel();
        String title[] = {"Mã thuốc", "Tên thuốc", "DVT", "Giá", "Số lượng", "Nhà SX", "Số lô SX", "Ngày SX", "Hạn sử dụng", "Giá gốc", "Mã nhà cung cấp"};
        tableModelMedicine.setColumnIdentifiers(title);
        try {
            Thuoc t = new Thuoc();
            for (int i = 0; i < dsthuoc.size(); i++) {
                t = dsthuoc.get(i);
                String MASP = t.getMASP();
                String TENSP = t.getTENSP();
                String DVT = t.getDVT();
                float GIA = t.getGIA();
                int SOLUONG = t.getSOLUONG();
                String NHASX = t.getNHASX();
                String SOLOSX = t.getSOLOSX();
                String NGAYSX = t.getNGAYSX();
                String HSD = t.getHSD();
                Float GIAGOC = t.getGIAGOC();
                String MANCC = t.getMANCC();
                int TRANGTHAI = t.getTRANGTHAI();

                Date date = convertStringtoDate(NGAYSX, "yyyy-MM-dd");
                Date date1 = convertStringtoDate(HSD, "yyyy-MM-dd");
                String result = convertDatetoString(date, "dd-MM-yyyy");
                String result1 = convertDatetoString(date1, "dd-MM-yyyy");

                if (TRANGTHAI == 1) {
                    Object row[] = {MASP, TENSP, DVT, GIA, SOLUONG, NHASX, SOLOSX, result, result1, GIAGOC, MANCC};
                    tableModelMedicine.addRow(row);
                }

            }
            jTable2.setModel(tableModelMedicine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadExpiryMedicine(ArrayList<Thuoc> dsthuoc) {
        tableModelMedicine = new DefaultTableModel();
        String title[] = {"Mã thuốc", "Tên thuốc", "Số lượng", "Ngày SX", "Hạn sử dụng", "Giá gốc"};
        tableModelMedicine.setColumnIdentifiers(title);
        try {
            Thuoc t = new Thuoc();
            for (int i = 0; i < dsthuoc.size(); i++) {
                t = dsthuoc.get(i);
                String MASP = t.getMASP();
                String TENSP = t.getTENSP();
                float GIA = t.getGIA();
                int SOLUONG = t.getSOLUONG();
                String NGAYSX = t.getNGAYSX();
                String HSD = t.getHSD();
                Float GIAGOC = t.getGIAGOC();

                Date date = convertStringtoDate(NGAYSX, "yyyy-MM-dd");
                Date date1 = convertStringtoDate(HSD, "yyyy-MM-dd");
                String result = convertDatetoString(date, "dd-MM-yyyy");
                String result1 = convertDatetoString(date1, "dd-MM-yyyy");
                Object row[] = {MASP, TENSP, SOLUONG, result, result1, GIAGOC};
                tableModelMedicine.addRow(row);

            }
            jTable1.setModel(tableModelMedicine);
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
                String TENSP = t.getTENSP();
                String DVT = t.getDVT();
                float GIA = t.getGIA();
                int SOLUONG = t.getSOLUONG();
                String NHASX = t.getNHASX();
                String SOLOSX = t.getSOLOSX();
                String NGAYSX = t.getNGAYSX();
                String HSD = t.getHSD();

                Date date = convertStringtoDate(NGAYSX, "yyyy-MM-dd");
                Date date1 = convertStringtoDate(HSD, "yyyy-MM-dd");
                String result = convertDatetoString(date, "dd-MM-yyyy");
                String result1 = convertDatetoString(date1, "dd-MM-yyyy");

                txtid.setText(MASP);
                txtname1.setText(TENSP);
                txtunit1.setText(DVT);
                txtprice1.setText(String.valueOf(GIA));
                txtquantity1.setText(String.valueOf(SOLUONG));
                txtproducer1.setText(NHASX);
                txtpronumber1.setText(SOLOSX);
                txtmfg1.setText(result);
                txtexp1.setText(result1);
                txtoriginprice.setText(String.valueOf(t.getGIAGOC()));
                txtmancc.setText(String.valueOf(t.getMANCC()));

                txtid2.setText(MASP);
                txtname3.setText(TENSP);
                txtunit3.setText(DVT);
                txtprice3.setText(String.valueOf(GIA));
                txtquantity3.setText(String.valueOf(SOLUONG));
                txtproducer3.setText(NHASX);
                txtpronumber3.setText(SOLOSX);
                txtmfg3.setText(result);
                txtexp3.setText(result1);
                txtoriginprice1.setText(String.valueOf(t.getGIAGOC()));
                txtmancc1.setText(String.valueOf(t.getMANCC()));

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

        ngaysx = new com.raven.datechooser.DateChooser();
        hsd = new com.raven.datechooser.DateChooser();
        ngaysx1 = new com.raven.datechooser.DateChooser();
        hsd1 = new com.raven.datechooser.DateChooser();
        ngaysx2 = new com.raven.datechooser.DateChooser();
        hsd2 = new com.raven.datechooser.DateChooser();
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
        chart1 = new com.raven.chart.Chart();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        update = new javax.swing.JPanel();
        btnsave1 = new keeptoo.Mybutton();
        jLabel13 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel48 = new javax.swing.JLabel();
        txtname1 = new javax.swing.JTextField();
        txtunit1 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtprice1 = new javax.swing.JTextField();
        txtquantity1 = new javax.swing.JTextField();
        txtexp1 = new javax.swing.JTextField();
        txtmfg1 = new javax.swing.JTextField();
        txtpronumber1 = new javax.swing.JTextField();
        txtproducer1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        btnhsd1 = new javax.swing.JButton();
        btnngaysx1 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtoriginprice = new javax.swing.JTextField();
        txtmancc = new javax.swing.JTextField();
        delete = new javax.swing.JPanel();
        btnsave2 = new keeptoo.Mybutton();
        jLabel33 = new javax.swing.JLabel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jLabel68 = new javax.swing.JLabel();
        txtname3 = new javax.swing.JTextField();
        txtunit3 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtprice3 = new javax.swing.JTextField();
        txtquantity3 = new javax.swing.JTextField();
        txtexp3 = new javax.swing.JTextField();
        txtmfg3 = new javax.swing.JTextField();
        txtpronumber3 = new javax.swing.JTextField();
        txtproducer3 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        btnhsd3 = new javax.swing.JButton();
        btnngaysx3 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        txtid2 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txtoriginprice1 = new javax.swing.JTextField();
        txtmancc1 = new javax.swing.JTextField();
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
        jLabel34 = new javax.swing.JLabel();
        btnupdate = new keeptoo.Mybutton();
        jLabel35 = new javax.swing.JLabel();
        btndelete = new keeptoo.Mybutton();

        ngaysx.setDateFormat("dd/MM/yyyy");
        ngaysx.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        hsd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        ngaysx1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ngaysx1.setTextRefernce(txtmfg1);

        hsd1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        hsd1.setTextRefernce(txtexp1);

        ngaysx2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ngaysx2.setTextRefernce(txtmfg1);

        hsd2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        hsd2.setTextRefernce(txtexp1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(9, 131, 149));
        setMinimumSize(new java.awt.Dimension(1100, 783));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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
        imgSearch.setBounds(560, 10, 35, 35);

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
        btnTimKiem.setBounds(560, 10, 140, 40);

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
        jLabel22.setText("Kiểm tra danh sách thuốc");

        kGradientPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(10, 77, 105)));
        kGradientPanel2.setkEndColor(new java.awt.Color(0, 255, 202));
        kGradientPanel2.setkGradientFocus(750);
        kGradientPanel2.setkStartColor(new java.awt.Color(5, 191, 219));
        kGradientPanel2.setPreferredSize(new java.awt.Dimension(564, 452));
        kGradientPanel2.setLayout(null);
        kGradientPanel2.add(chart1);
        chart1.setBounds(430, 20, 300, 400);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel1.setText("Danh sách thuốc sắp hết hạn");
        kGradientPanel2.add(jLabel1);
        jLabel1.setBounds(100, 440, 200, 16);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setRowHeight(25);
        jScrollPane2.setViewportView(jTable1);

        kGradientPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(20, 20, 400, 400);

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel2.setText("Biểu đồ hiệu lực của thuốc");
        kGradientPanel2.add(jLabel2);
        jLabel2.setBounds(510, 440, 180, 16);

        javax.swing.GroupLayout insertLayout = new javax.swing.GroupLayout(insert);
        insert.setLayout(insertLayout);
        insertLayout.setHorizontalGroup(
            insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insertLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        insertLayout.setVerticalGroup(
            insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertLayout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(296, 296, 296))
        );

        jTabbedPane1.addTab("tab2", insert);

        update.setBackground(new java.awt.Color(8, 131, 149));

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

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Chỉnh Sửa Thuốc");

        kGradientPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(10, 77, 105)));
        kGradientPanel3.setkEndColor(new java.awt.Color(0, 255, 202));
        kGradientPanel3.setkGradientFocus(750);
        kGradientPanel3.setkStartColor(new java.awt.Color(5, 191, 219));
        kGradientPanel3.setPreferredSize(new java.awt.Dimension(564, 452));
        kGradientPanel3.setLayout(null);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Mã thuốc");
        kGradientPanel3.add(jLabel48);
        jLabel48.setBounds(60, 30, 84, 19);
        kGradientPanel3.add(txtname1);
        txtname1.setBounds(200, 60, 206, 22);
        kGradientPanel3.add(txtunit1);
        txtunit1.setBounds(200, 90, 206, 22);

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Đơn vị");
        kGradientPanel3.add(jLabel49);
        jLabel49.setBounds(60, 90, 84, 20);

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Giá");
        kGradientPanel3.add(jLabel50);
        jLabel50.setBounds(60, 120, 84, 20);
        kGradientPanel3.add(txtprice1);
        txtprice1.setBounds(200, 120, 206, 22);
        kGradientPanel3.add(txtquantity1);
        txtquantity1.setBounds(200, 150, 90, 22);
        kGradientPanel3.add(txtexp1);
        txtexp1.setBounds(200, 270, 210, 22);

        txtmfg1.setFocusCycleRoot(true);
        kGradientPanel3.add(txtmfg1);
        txtmfg1.setBounds(200, 240, 210, 22);
        kGradientPanel3.add(txtpronumber1);
        txtpronumber1.setBounds(200, 210, 210, 22);
        kGradientPanel3.add(txtproducer1);
        txtproducer1.setBounds(200, 180, 210, 22);

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Số lượng");
        kGradientPanel3.add(jLabel51);
        jLabel51.setBounds(60, 150, 84, 20);

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Nhà SX");
        kGradientPanel3.add(jLabel52);
        jLabel52.setBounds(60, 180, 80, 20);

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Số lô SX");
        kGradientPanel3.add(jLabel53);
        jLabel53.setBounds(60, 210, 84, 20);

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Ngày SX");
        kGradientPanel3.add(jLabel54);
        jLabel54.setBounds(60, 240, 84, 20);

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Mã nhà cung cấp");
        kGradientPanel3.add(jLabel55);
        jLabel55.setBounds(60, 330, 130, 20);

        btnhsd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnhsd1.setBorder(null);
        btnhsd1.setContentAreaFilled(false);
        btnhsd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhsd1ActionPerformed(evt);
            }
        });
        kGradientPanel3.add(btnhsd1);
        btnhsd1.setBounds(420, 270, 30, 24);

        btnngaysx1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnngaysx1.setBorder(null);
        btnngaysx1.setContentAreaFilled(false);
        btnngaysx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnngaysx1ActionPerformed(evt);
            }
        });
        kGradientPanel3.add(btnngaysx1);
        btnngaysx1.setBounds(420, 240, 30, 24);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Tên thuốc");
        kGradientPanel3.add(jLabel64);
        jLabel64.setBounds(60, 60, 84, 19);
        kGradientPanel3.add(txtid);
        txtid.setBounds(200, 30, 90, 22);

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Hạn sử dụng");
        kGradientPanel3.add(jLabel66);
        jLabel66.setBounds(60, 270, 90, 20);

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Giá gốc");
        kGradientPanel3.add(jLabel67);
        jLabel67.setBounds(60, 300, 90, 20);
        kGradientPanel3.add(txtoriginprice);
        txtoriginprice.setBounds(200, 300, 210, 22);
        kGradientPanel3.add(txtmancc);
        txtmancc.setBounds(200, 330, 90, 22);

        javax.swing.GroupLayout updateLayout = new javax.swing.GroupLayout(update);
        update.setLayout(updateLayout);
        updateLayout.setHorizontalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLayout.createSequentialGroup()
                        .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLayout.createSequentialGroup()
                        .addComponent(btnsave1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(313, 313, 313))))
        );
        updateLayout.setVerticalGroup(
            updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsave1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("tab3", update);

        delete.setBackground(new java.awt.Color(8, 131, 149));

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

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Xóa Thuốc");

        kGradientPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(10, 77, 105)));
        kGradientPanel7.setkEndColor(new java.awt.Color(0, 255, 202));
        kGradientPanel7.setkGradientFocus(750);
        kGradientPanel7.setkStartColor(new java.awt.Color(5, 191, 219));
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(564, 452));
        kGradientPanel7.setLayout(null);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Mã thuốc");
        kGradientPanel7.add(jLabel68);
        jLabel68.setBounds(60, 30, 84, 19);
        kGradientPanel7.add(txtname3);
        txtname3.setBounds(200, 60, 206, 22);
        kGradientPanel7.add(txtunit3);
        txtunit3.setBounds(200, 90, 206, 22);

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Đơn vị");
        kGradientPanel7.add(jLabel69);
        jLabel69.setBounds(60, 90, 84, 20);

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Giá");
        kGradientPanel7.add(jLabel70);
        jLabel70.setBounds(60, 120, 84, 20);
        kGradientPanel7.add(txtprice3);
        txtprice3.setBounds(200, 120, 206, 22);
        kGradientPanel7.add(txtquantity3);
        txtquantity3.setBounds(200, 150, 90, 22);
        kGradientPanel7.add(txtexp3);
        txtexp3.setBounds(200, 270, 210, 22);

        txtmfg3.setFocusCycleRoot(true);
        kGradientPanel7.add(txtmfg3);
        txtmfg3.setBounds(200, 240, 210, 22);
        kGradientPanel7.add(txtpronumber3);
        txtpronumber3.setBounds(200, 210, 210, 22);
        kGradientPanel7.add(txtproducer3);
        txtproducer3.setBounds(200, 180, 210, 22);

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Số lượng");
        kGradientPanel7.add(jLabel71);
        jLabel71.setBounds(60, 150, 84, 20);

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Nhà SX");
        kGradientPanel7.add(jLabel72);
        jLabel72.setBounds(60, 180, 80, 20);

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Số lô SX");
        kGradientPanel7.add(jLabel73);
        jLabel73.setBounds(60, 210, 84, 20);

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Ngày SX");
        kGradientPanel7.add(jLabel74);
        jLabel74.setBounds(60, 240, 84, 20);

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Mã nhà cung cấp");
        kGradientPanel7.add(jLabel75);
        jLabel75.setBounds(60, 330, 130, 20);

        btnhsd3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnhsd3.setBorder(null);
        btnhsd3.setContentAreaFilled(false);
        btnhsd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhsd3ActionPerformed(evt);
            }
        });
        kGradientPanel7.add(btnhsd3);
        btnhsd3.setBounds(420, 270, 30, 24);

        btnngaysx3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-24.png"))); // NOI18N
        btnngaysx3.setBorder(null);
        btnngaysx3.setContentAreaFilled(false);
        btnngaysx3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnngaysx3ActionPerformed(evt);
            }
        });
        kGradientPanel7.add(btnngaysx3);
        btnngaysx3.setBounds(420, 240, 30, 24);

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Tên thuốc");
        kGradientPanel7.add(jLabel76);
        jLabel76.setBounds(60, 60, 84, 19);
        kGradientPanel7.add(txtid2);
        txtid2.setBounds(200, 30, 90, 22);

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Hạn sử dụng");
        kGradientPanel7.add(jLabel77);
        jLabel77.setBounds(60, 270, 90, 20);

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Giá gốc");
        kGradientPanel7.add(jLabel78);
        jLabel78.setBounds(60, 300, 90, 20);
        kGradientPanel7.add(txtoriginprice1);
        txtoriginprice1.setBounds(200, 300, 210, 22);
        kGradientPanel7.add(txtmancc1);
        txtmancc1.setBounds(200, 330, 90, 22);

        javax.swing.GroupLayout deleteLayout = new javax.swing.GroupLayout(delete);
        delete.setLayout(deleteLayout);
        deleteLayout.setHorizontalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteLayout.createSequentialGroup()
                        .addComponent(btnsave2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310))))
            .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(deleteLayout.createSequentialGroup()
                    .addGap(117, 117, 117)
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );
        deleteLayout.setVerticalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(414, 414, 414)
                .addComponent(btnsave2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(deleteLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(109, Short.MAX_VALUE)))
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
        lbThuoc.setText("Thuốc");
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
        imgBack.setBounds(890, 620, 45, 45);

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
        btnBack.setBounds(880, 620, 160, 50);

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

        imgInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bar-graph.png"))); // NOI18N
        mainpanel.add(imgInsert);
        imgInsert.setBounds(880, 360, 40, 30);

        imgList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder.png"))); // NOI18N
        mainpanel.add(imgList);
        imgList.setBounds(890, 280, 43, 43);

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
        btnDSThuoc.setBounds(880, 280, 160, 50);

        btnadd.setText("    Kiểm tra thuốc");
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
        btnadd.setBounds(880, 350, 160, 50);

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shuffle.png"))); // NOI18N
        mainpanel.add(jLabel34);
        jLabel34.setBounds(890, 420, 43, 50);

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
        btnupdate.setBounds(880, 420, 160, 50);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trash.png"))); // NOI18N
        mainpanel.add(jLabel35);
        jLabel35.setBounds(890, 490, 40, 40);

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
        btndelete.setBounds(880, 490, 160, 50);

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
        LoadMedicine(mbll.Load_BLL());
    }//GEN-LAST:event_btnDSThuocActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        chart1.clear();
        Connection conn = new DBUtils().createConn();
        try {
            String sql = "Select count(MASP) from SANPHAM";
            PreparedStatement pres = conn.prepareStatement(sql);
            ResultSet rs = pres.executeQuery();
            double a = 0, b = 0;
            while (rs.next()) {
                a = rs.getFloat(1);
            }
            String sql1 = "select count(MASP) from SANPHAM where round(months_between(sysdate,HSD)) >=-1";
            PreparedStatement pres1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pres1.executeQuery();
            while (rs1.next()) {
                b = rs1.getFloat(1);
            }
            chart1.addData(new ModelChart("Thuốc", new double[]{b, a}));
            chart1.start();
            LoadExpiryMedicine(mbll.LoadExpiry_BLL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        LoadMedicine(mbll.Search_BLL(txtTimKiem.getText()));
        LoadMedicineForm(mbll.Search_BLL(txtTimKiem.getText()));
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave1ActionPerformed
        // TODO add your handling code here:
        try {
            if (txtname1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên thuốc không được bỏ trống");
            } else if (txtunit1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Đơn vị thuốc không được bỏ trống");
            } else if (txtprice1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Giá thuốc không được bỏ trống");
            } else if (txtquantity1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Số lượng thuốc không được bỏ trống");
            } else if (txtunit1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Đơn vị thuốc không được bỏ trống");
            } else if (txtproducer1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nhà sản xuất không được bỏ trống");
            } else if (txtpronumber1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Số lô sản xuất không được bỏ trống");
            } else if (txtmfg1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ngày sản xuất thuốc không được bỏ trống");
            } else if (txtexp1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Hạn sử dụng thuốc không được bỏ trống");
            } else if (Integer.parseInt(txtquantity1.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "Số lượng thuốc phải là số lương");
            } else if (Float.parseFloat(txtprice1.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "Giá thuốc phải là số dương");
            } else {
                int a = (int) Float.parseFloat(txtprice1.getText());
                float b = (float) a;
                boolean check = mbll.Update(txtid.getText(), txtname1.getText(), txtunit1.getText(), b, Integer.parseInt(txtquantity1.getText()), txtproducer1.getText(), txtpronumber1.getText(), txtmfg1.getText(), txtexp1.getText(), Float.parseFloat(txtoriginprice.getText()), txtmancc.getText(), 1);
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
                int a = (int) Float.parseFloat(txtprice3.getText());
                float b = (float) a;
                boolean check = mbll.Delete(txtid.getText(), txtname1.getText(), txtunit1.getText(), b, Integer.parseInt(txtquantity1.getText()), txtproducer1.getText(), txtpronumber1.getText(), txtmfg1.getText(), txtexp1.getText(), Float.parseFloat(txtoriginprice.getText()), txtmancc.getText(), 0);
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

    private void btnhsd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhsd1ActionPerformed
        // TODO add your handling code here:
        hsd1.showPopup();
    }//GEN-LAST:event_btnhsd1ActionPerformed

    private void btnngaysx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnngaysx1ActionPerformed
        // TODO add your handling code here:
        ngaysx1.showPopup();
    }//GEN-LAST:event_btnngaysx1ActionPerformed

    private void btnhsd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhsd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnhsd3ActionPerformed

    private void btnngaysx3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnngaysx3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnngaysx3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        chart1.start();
    }//GEN-LAST:event_formWindowOpened

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
                new Medicine().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.Mybutton btnBack;
    private keeptoo.Mybutton btnDSThuoc;
    private keeptoo.Mybutton btnTimKiem;
    private keeptoo.Mybutton btnadd;
    private keeptoo.Mybutton btndelete;
    private javax.swing.JButton btnhsd1;
    private javax.swing.JButton btnhsd3;
    private javax.swing.JButton btnngaysx1;
    private javax.swing.JButton btnngaysx3;
    private keeptoo.Mybutton btnsave1;
    private keeptoo.Mybutton btnsave2;
    private keeptoo.Mybutton btnupdate;
    private com.raven.chart.Chart chart1;
    private javax.swing.JPanel delete;
    private com.raven.datechooser.DateChooser hsd;
    private com.raven.datechooser.DateChooser hsd1;
    private com.raven.datechooser.DateChooser hsd2;
    private javax.swing.JLabel imgBack;
    private javax.swing.JLabel imgInsert;
    private javax.swing.JLabel imgList;
    private javax.swing.JLabel imgSearch;
    private javax.swing.JPanel insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel7;
    private javax.swing.JLabel lbThuoc;
    private javax.swing.JPanel mainpanel;
    private com.raven.datechooser.DateChooser ngaysx;
    private com.raven.datechooser.DateChooser ngaysx1;
    private com.raven.datechooser.DateChooser ngaysx2;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtexp1;
    private javax.swing.JTextField txtexp3;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid2;
    private javax.swing.JTextField txtmancc;
    private javax.swing.JTextField txtmancc1;
    private javax.swing.JTextField txtmfg1;
    private javax.swing.JTextField txtmfg3;
    private javax.swing.JTextField txtname1;
    private javax.swing.JTextField txtname3;
    private javax.swing.JTextField txtoriginprice;
    private javax.swing.JTextField txtoriginprice1;
    private javax.swing.JTextField txtprice1;
    private javax.swing.JTextField txtprice3;
    private javax.swing.JTextField txtproducer1;
    private javax.swing.JTextField txtproducer3;
    private javax.swing.JTextField txtpronumber1;
    private javax.swing.JTextField txtpronumber3;
    private javax.swing.JTextField txtquantity1;
    private javax.swing.JTextField txtquantity3;
    private javax.swing.JTextField txtunit1;
    private javax.swing.JTextField txtunit3;
    private javax.swing.JPanel update;
    // End of variables declaration//GEN-END:variables
}
