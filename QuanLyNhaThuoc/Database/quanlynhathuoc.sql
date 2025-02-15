--------------------------------------------------------
--  DDL for Table TAIKHOAN
--------------------------------------------------------

CREATE TABLE "TAIKHOAN" (
    tentk   VARCHAR2(20),
    matkhau VARCHAR2(20),
    vaitro  NUMBER(2),
    manv    NUMBER(10)
);
--------------------------------------------------------
--  DDL for Table KHACHHANG
--------------------------------------------------------
CREATE TABLE "KHACHHANG" (
    "MAKH"        NUMBER(10),
    "HOTEN"       NVARCHAR2(50),
    "SODT"        NVARCHAR2(50),
    "DIACHI"      NVARCHAR2(400),
    "DIEMTICHLUY" NUMBER(10, 0) DEFAULT 0,
    "EMAIL"       VARCHAR2(25)
);

--------------------------------------------------------
--  DDL for Table NHANVIEN
--------------------------------------------------------

CREATE TABLE "NHANVIEN" (
    "MANV"       NUMBER(10),
    "HOTEN"      NVARCHAR2(50),
    "GIOITINH"   NVARCHAR2(5),
    "NGAYSINH"   DATE,
    "EMAIL"      VARCHAR2(25 BYTE),
    "SODT"       NUMBER(10),
    "NGAYVAOLAM" DATE,
    "DIACHI"     NVARCHAR2(50),
    "TRANGTHAI" NUMBER(11,2) default 1
);
--------------------------------------------------------
--  DDL for Table SANPHAM
--------------------------------------------------------

CREATE TABLE "SANPHAM" (
    "MASP"    NUMBER(11),
    "TENSP"   NVARCHAR2(50),
    "DVT"     NVARCHAR2(10),
    "GIA"     NUMBER(11, 2),
    "SOLUONG" INT,
    "NHASX"   NVARCHAR2(40),
    "SOLOSX"  VARCHAR2(15),
    "NGAYSX"  DATE,
    "HSD"     DATE,
    "GIAGOC"  NUMBER(11, 2),
    "MANCC"   NUMBER(11),
    "TRANGTHAI" NUMBER(11,2) default 1
);
--------------------------------------------------------
--  DDL for Table HOADON
--------------------------------------------------------

CREATE TABLE "HOADON" (
    "SOHD"   INT,
    "NGHD"   DATE,
    "MANV"   NUMBER(10),
    "MAKH"   NUMBER(10),
    "TRIGIA" NUMBER(11, 2) DEFAULT 0
);
--------------------------------------------------------
--  DDL for Table CTHD
--------------------------------------------------------

CREATE TABLE "CTHD" (
    "SOHD"    INT,
    "MASP"    NUMBER(11),
    "SOLUONG" INT,
    "DONGIA"  NUMBER(11, 2),
    "THUE"    NUMBER(11, 2) DEFAULT 0
);

--------------------------------------------------------
--  DDL for Table HOADONNHAP
--------------------------------------------------------

CREATE TABLE "HOADONNHAP" (
    "SOHDN"  INT,
    "NGHDN"  DATE,
    "MANV"   NUMBER(10),
    "MANCC"  NUMBER(10),
    "TRIGIA" NUMBER(11, 2) DEFAULT 0
);
   
--------------------------------------------------------
--  DDL for Table CTHDNHAP
--------------------------------------------------------

CREATE TABLE "CTHDNHAP" (
    "SOHDN"   INT,
    "MASP"    NUMBER(11),
    "SOLUONG" INT,
    "DONGIA"  NUMBER(11, 2),
    "THUE"    NUMBER(11, 2) DEFAULT 0,
    "GIAGOC" NUMBER(11,2)
);

--------------------------------------------------------
--  DDL for Table NHACUNGCAP
--------------------------------------------------------

CREATE TABLE "NHACUNGCAP" (
    "MANCC"  INT,
    "TENNCC" NVARCHAR2(150),
    "SODT"   NVARCHAR2(50),
    "EMAIL"  NVARCHAR2(50),
    "DIACHI" NVARCHAR2(150)
);
--------------------------------------------------------
--  DDL for Index MANCC_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "MANCC_PK" ON
    "NHACUNGCAP" (
        "MANCC"
    );
--------------------------------------------------------
--  DDL for Index MAKH_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "MAKH_PK" ON
    "KHACHHANG" (
        "MAKH"
    );
--------------------------------------------------------
--  DDL for Index MANV_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "MANV_PK" ON
    "NHANVIEN" (
        "MANV"
    );
--------------------------------------------------------
--  DDL for Index MASP_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "MASP_PK" ON
    "SANPHAM" (
        "MASP"
    );
--------------------------------------------------------
--  DDL for Index SOHD_PK
--------------------------------------------------------
CREATE UNIQUE INDEX "SOHD_PK" ON
    "HOADON" (
        "SOHD"
    );
--------------------------------------------------------
--  DDL for Index SOHDN_PK
--------------------------------------------------------
CREATE UNIQUE INDEX "SOHDN_PK" ON
    "HOADONNHAP" (
        "SOHDN"
    );
--------------------------------------------------------
--  DDL for Index SOHD_MASP_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "SOHD_MASP_PK" ON
    "CTHD" (
        "SOHD",
        "MASP"
    );
--------------------------------------------------------
--  DDL for Index SOHDN_MASP_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "SOHDN_MASP_PK" ON
    "CTHDNHAP" (
        "SOHDN",
        "MASP"
    );
--------------------------------------------------------
--  Constraints for Table TAIKHOAN
--------------------------------------------------------
ALTER TABLE "TAIKHOAN" MODIFY (
    "TENTK"
        CONSTRAINT "TK_TENTK_NN" NOT NULL ENABLE
);

ALTER TABLE "TAIKHOAN" MODIFY (
    "MATKHAU"
        CONSTRAINT "TK_MK_NN" NOT NULL ENABLE
);

ALTER TABLE "TAIKHOAN" MODIFY (
    "VAITRO"
        CONSTRAINT "TK_VAITRO_NN" NOT NULL ENABLE
);

ALTER TABLE "TAIKHOAN" MODIFY (
    "MANV"
        CONSTRAINT "TK_MANV_NN" NOT NULL ENABLE
);

ALTER TABLE "TAIKHOAN" ADD CONSTRAINT "TK_TENTK_PK" PRIMARY KEY ( "TENTK" );

ALTER TABLE "TAIKHOAN"
    ADD CONSTRAINT "NV_VAITRO_CK" CHECK ( vaitro IN ( 0, 1 ) ) ENABLE;
--------------------------------------------------------
--  Constraints for Table KHACHHANG
--------------------------------------------------------

ALTER TABLE "KHACHHANG" MODIFY (
    "MAKH"
        CONSTRAINT "KH_MAKH_NN" NOT NULL ENABLE
);

ALTER TABLE "KHACHHANG" MODIFY (
    "HOTEN"
        CONSTRAINT "KH_TEN_NN" NOT NULL ENABLE
);

ALTER TABLE "KHACHHANG" MODIFY (
    "SODT"
        CONSTRAINT "KH_SODT_NN" NOT NULL ENABLE
);

ALTER TABLE "KHACHHANG"
    ADD CONSTRAINT "KH_MAKH_PK" PRIMARY KEY ( "MAKH" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table NHANVIEN
--------------------------------------------------------

ALTER TABLE "NHANVIEN" MODIFY (
    "MANV"
        CONSTRAINT "NV_MANV_NN" NOT NULL ENABLE
);

ALTER TABLE "NHANVIEN" MODIFY (
    "HOTEN"
        CONSTRAINT "NV_HOTEN_NN" NOT NULL ENABLE
);

ALTER TABLE "NHANVIEN"
    ADD CONSTRAINT "NV_MANV_PK" PRIMARY KEY ( "MANV" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table NHACUNGCAP
--------------------------------------------------------

ALTER TABLE "NHACUNGCAP" MODIFY (
    "MANCC"
        CONSTRAINT "NCC_MANCC_NN" NOT NULL ENABLE
);

ALTER TABLE "NHACUNGCAP" MODIFY (
    "TENNCC"
        CONSTRAINT "NCC_TENNCC_NN" NOT NULL ENABLE
);

ALTER TABLE "NHACUNGCAP"
    ADD CONSTRAINT "NCC_MANCC_PK" PRIMARY KEY ( "MANCC" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table SANPHAM
--------------------------------------------------------

ALTER TABLE "SANPHAM" MODIFY (
    "MASP"
        CONSTRAINT "SP_MASP_NN" NOT NULL ENABLE
);

ALTER TABLE "SANPHAM" MODIFY (
    "TENSP"
        CONSTRAINT "SP_TENSP_NN" NOT NULL ENABLE
);

ALTER TABLE "SANPHAM" MODIFY (
    "MANCC"
        CONSTRAINT "SP_MANCC_NN" NOT NULL ENABLE
);

ALTER TABLE "SANPHAM"
    ADD CONSTRAINT "SANPHAM_MASP_PK" PRIMARY KEY ( "MASP" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table HOADON
--------------------------------------------------------

ALTER TABLE "HOADON" MODIFY (
    "SOHD"
        CONSTRAINT "HOADON_SOHD_NN" NOT NULL ENABLE
);

ALTER TABLE "HOADON" MODIFY (
    "MANV"
        CONSTRAINT "HOADON_MANV_NN" NOT NULL ENABLE
);

ALTER TABLE "HOADON"
    ADD CONSTRAINT "HOADON_SOHD_PK" PRIMARY KEY ( "SOHD" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table HOADONNHAP
--------------------------------------------------------

ALTER TABLE "HOADONNHAP" MODIFY (
    "SOHDN"
        CONSTRAINT "HOADONNHAP_SOHDN_NN" NOT NULL ENABLE
);

ALTER TABLE "HOADONNHAP" MODIFY (
    "MANV"
        CONSTRAINT "HOADONNHAP_MANV_NN" NOT NULL ENABLE
);

ALTER TABLE "HOADONNHAP"
    ADD CONSTRAINT "HOADONNHAP_SOHDN_PK" PRIMARY KEY ( "SOHDN" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table CTHD
--------------------------------------------------------

ALTER TABLE "CTHD" MODIFY (
    "SOHD"
        CONSTRAINT "CTHD_SOHD_NN" NOT NULL ENABLE
);

ALTER TABLE "CTHD" MODIFY (
    "MASP"
        CONSTRAINT "CTHD_MASP_NN" NOT NULL ENABLE
);

ALTER TABLE "CTHD"
    ADD CONSTRAINT "CTHD_SOHD_MASP_PK" PRIMARY KEY ( "SOHD",
                                                     "MASP" )
        USING INDEX enable;
--------------------------------------------------------
--  Constraints for Table CTHDNHAP
--------------------------------------------------------

ALTER TABLE "CTHDNHAP" MODIFY (
    "SOHDN"
        CONSTRAINT "CTHDNHAP_SOHDN_NN" NOT NULL ENABLE
);

ALTER TABLE "CTHDNHAP" MODIFY (
    "MASP"
        CONSTRAINT "CTHDNHAP_MASP_NN" NOT NULL ENABLE
);

ALTER TABLE "CTHDNHAP"
    ADD CONSTRAINT "CTHDNHAP_SOHDN_MASP_PK" PRIMARY KEY ( "SOHDN",
                                                          "MASP" )
        USING INDEX enable;

REM INSERTING into TAIKHOAN
SET DEFINE OFF;

INSERT INTO taikhoan (
    tentk,
    matkhau,
    vaitro,
    manv
) VALUES (
    'admin01',
    'admin01@',
    0,
    01
);

INSERT INTO taikhoan (
    tentk,
    matkhau,
    vaitro,
    manv
) VALUES (
    'yenltp',
    'yen@1003',
    1,
    03
);

INSERT INTO taikhoan (
    tentk,
    matkhau,
    vaitro,
    manv
) VALUES (
    'tuannt',
    'tuan223',
    1,
    02
);
REM INSERTING into KHACHHANG
SET DEFINE OFF;

INSERT INTO khachhang (
    makh,
    hoten,
    sodt,
    diachi,
    email
) VALUES (
    05,
    N'Tr?n Ng?c Hân',
    0908256478,
    N'23/5 Nguy?n Trãi, Q5, TpHCM',
    'abc@gmail.com'
);

INSERT INTO khachhang (
    makh,
    hoten,
    sodt,
    diachi,
    email
) VALUES (
    110,
    N'Lê Hoài Th??ng',
    08631738,
    N'227 Nguy?n V?n C?, Q5, TpHCM',
    'xyz@gmail.com'
);
REM INSERTING into NHANVIEN
SET DEFINE OFF;

INSERT INTO nhanvien (
    manv,
    hoten,
    gioitinh,
    ngaysinh,
    email,
    sodt,
    ngayvaolam,
    diachi
) VALUES (
    01,
    N'Tr?n Ng?c Linh ',
    N'N?',
    TO_DATE('12-06-1980', 'DD-MM-YYYY'),
    NULL,
    0938776266,
    TO_DATE('05-08-2006', 'DD-MM-YYYY'),
    '4/7 Xa Xuan Thoi Thuong huyen Hoc Mon'
);

INSERT INTO nhanvien (
    manv,
    hoten,
    gioitinh,
    ngaysinh,
    email,
    sodt,
    ngayvaolam,
    diachi
) VALUES (
    03,
    N'Lê Th? Phi Y?n',
    N'N?',
    TO_DATE('10-03-1993', 'DD-MM-YYYY'),
    NULL,
    0927345678,
    TO_DATE('13-04-2019', 'DD-MM-YYYY'),
    '5/7 Xa Xuan Thoi Thuong huyen Hoc Mon'
);

INSERT INTO nhanvien (
    manv,
    hoten,
    gioitinh,
    ngaysinh,
    email,
    sodt,
    ngayvaolam,
    diachi
) VALUES (
    02,
    N'Ngô Thanh Tu?n',
    N'Nam',
    TO_DATE('22-10-1994', 'DD-MM-YYYY'),
    NULL,
    0913758498,
    TO_DATE('24-6-2019', 'DD-MM-YYYY'),
    '2/4 Xa Xuan Thoi Thuong huyen Hoc Mon'
);
REM INSERTING into SANPHAM
SET DEFINE OFF;

INSERT INTO sanpham (
    masp,
    tensp,
    dvt,
    gia,
    soluong,
    nhasx,
    solosx,
    ngaysx,
    hsd,
    giagoc,
    mancc
) VALUES (
    2197,
    'Derma-forte(Advanced)15g',
    N'Tuýp',
    96800,
    20,
    'GAM MA CHEMICALS PTE',
    'DMF.011022',
    TO_DATE('05-10-2022', 'DD-MM-YYYY'),
    TO_DATE('05-10-2025', 'DD-MM-YYYY'),
    76800,
    1
);

INSERT INTO sanpham (
    masp,
    tensp,
    dvt,
    gia,
    soluong,
    nhasx,
    solosx,
    ngaysx,
    hsd,
    giagoc,
    mancc
) VALUES (
    3559,
    'Growsel',
    N'H?p',
    113400,
    35,
    'ROUSSEL',
    0150522,
    TO_DATE('17-05-2022', 'DD-MM-YYYY'),
    TO_DATE('17-05-2024', 'DD-MM-YYYY'),
    93400,
    1
);

INSERT INTO sanpham (
    masp,
    tensp,
    dvt,
    gia,
    soluong,
    nhasx,
    solosx,
    ngaysx,
    hsd,
    giagoc,
    mancc
) VALUES (
    2759,
    'Canxi Vitamin D3 KingPhar',
    N'H?p',
    105000,
    47,
    N'CÔNG TY TNHH CÔNG NGH? HERBITECH',
    010223,
    TO_DATE('19-02-2023', 'DD-MM-YYYY'),
    TO_DATE('18-02-2026', 'DD-MM-YYYY'),
    85000,
    3
);
REM INSERTING into HOADON
SET DEFINE OFF;

INSERT INTO hoadon (
    sohd,
    nghd,
    manv,
    makh,
    trigia
) VALUES (
    1001,
    TO_DATE('13-03-2023', 'DD-MM-YYYY'),
    02,
    110,
    96800
);

INSERT INTO hoadon (
    sohd,
    nghd,
    manv,
    makh,
    trigia
) VALUES (
    1005,
    TO_DATE('08-04-2023', 'DD-MM-YYYY'),
    03,
    110,
    218400
);
REM INSERTING into CTHD
SET DEFINE OFF;

INSERT INTO cthd (
    sohd,
    masp,
    soluong,
    dongia
) VALUES (
    1001,
    2197,
    1,
    96800
);

INSERT INTO cthd (
    sohd,
    masp,
    soluong,
    dongia
) VALUES (
    1005,
    3559,
    1,
    113400
);

INSERT INTO cthd (
    sohd,
    masp,
    soluong,
    dongia
) VALUES (
    1005,
    2759,
    1,
    105000
);
REM INSERTING into NHACUNGCAP
SET DEFINE OFF;

INSERT INTO nhacungcap (
    mancc,
    tenncc,
    sodt,
    email,
    diachi
) VALUES (
    1,
    'Medicines and Healthcare PR.Agency',
    '02030806593',
    'matthew.g.larsen@gsk.com',
    '980 Great West Road, Brentford, TW8 9GS, United Kingdom'
);

INSERT INTO nhacungcap (
    mancc,
    tenncc,
    sodt,
    email,
    diachi
) VALUES (
    2,
    'Haleon UK Trading Services',
    '02030806593',
    'matthew1.g.larsen@gsk.com',
    'Building 5, First Floor, The Heights, Brooklands, Weybridge, KT13 0NY, United Kingdom'
);

INSERT INTO nhacungcap (
    mancc,
    tenncc,
    sodt,
    email,
    diachi
) VALUES (
    3,
    'Công Ty TNHH Lê Hào',
    '02437190219',
    'sales.lehaoco@gmail.com',
    'S? 26 Ngõ 36 ???ng Th??ng H?i, X. Tân H?i, H. ?an Ph??ng,'
);
REM INSERTING into HOADONNHAP
SET DEFINE OFF;

INSERT INTO hoadonnhap (
    sohdn,
    nghdn,
    manv,
    mancc,
    trigia
) VALUES (
    1,
    TO_DATE('13-03-2022', 'DD-MM-YYYY'),
    02,
    3,
    76800
);

INSERT INTO hoadonnhap (
    sohdn,
    nghdn,
    manv,
    mancc,
    trigia
) VALUES (
    2,
    TO_DATE('08-04-2022', 'DD-MM-YYYY'),
    03,
    1,
    178400
);
REM INSERTING into CTHD
SET DEFINE OFF;

INSERT INTO cthdnhap (
    sohdn,
    masp,
    soluong,
    dongia
) VALUES (
    1,
    2197,
    1,
    76800
);

INSERT INTO cthdnhap (
    sohdn,
    masp,
    soluong,
    dongia
) VALUES (
    2,
    3559,
    1,
    93400
);

INSERT INTO cthdnhap (
    sohdn,
    masp,
    soluong,
    dongia
) VALUES (
    2,
    2759,
    1,
    85000
);
--------------------------------------------------------
--  Ref Constraints for Table TAIKHOAN
--------------------------------------------------------
ALTER TABLE "TAIKHOAN"
    ADD CONSTRAINT "TK_MANV_FK" FOREIGN KEY ( "MANV" )
        REFERENCES "NHANVIEN" ( "MANV" )
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HOADON
--------------------------------------------------------

ALTER TABLE "HOADON"
    ADD CONSTRAINT "HD_MANV_FK" FOREIGN KEY ( "MANV" )
        REFERENCES "NHANVIEN" ( "MANV" )
    ENABLE;

ALTER TABLE "HOADON"
    ADD CONSTRAINT "HD_MAKH_FK" FOREIGN KEY ( "MAKH" )
        REFERENCES "KHACHHANG" ( "MAKH" )
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HOADONNHAP
--------------------------------------------------------

ALTER TABLE "HOADONNHAP"
    ADD CONSTRAINT "HDN_MANCC_FK" FOREIGN KEY ( "MANCC" )
        REFERENCES "NHACUNGCAP" ( "MANCC" )
    ENABLE;

ALTER TABLE "HOADONNHAP"
    ADD CONSTRAINT "HDN_MANV_FK" FOREIGN KEY ( "MANV" )
        REFERENCES "NHANVIEN" ( "MANV" )
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CTHD
--------------------------------------------------------

ALTER TABLE "CTHD"
    ADD CONSTRAINT "CTHD_SOHD_FK" FOREIGN KEY ( "SOHD" )
        REFERENCES "HOADON" ( "SOHD" )
    ENABLE;

ALTER TABLE "CTHD"
    ADD CONSTRAINT "CTHD_MASP_FK" FOREIGN KEY ( "MASP" )
        REFERENCES "SANPHAM" ( "MASP" )
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CTHDNHAP
--------------------------------------------------------

ALTER TABLE "CTHDNHAP"
    ADD CONSTRAINT "CTHDNHAP_SOHDN_FK" FOREIGN KEY ( "SOHDN" )
        REFERENCES "HOADONNHAP" ( "SOHDN" )
    ENABLE;

ALTER TABLE "CTHDNHAP"
    ADD CONSTRAINT "CTHDNHAP_MASP_FK" FOREIGN KEY ( "MASP" )
        REFERENCES "SANPHAM" ( "MASP" )
    ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SANPHAM
--------------------------------------------------------

ALTER TABLE "SANPHAM"
    ADD CONSTRAINT "SP_MANCC_FK" FOREIGN KEY ( "MANCC" )
        REFERENCES "NHACUNGCAP" ( "MANCC" )
    ENABLE;
---------------------------------------------------------
--UPDATE
---------------------------------------------------------
CREATE TABLE "KHOSANPHAM" (
    "MALO"    NUMBER(11),
    "MASP"    NUMBER(11),
    "TENSP"   NVARCHAR2(50),
    "DVT"     NVARCHAR2(10),
    "GIA"     NUMBER(11, 2) DEFAULT 0,
    "SOLUONG" INT,
    "NHASX"   NVARCHAR2(40),
    "SOLOSX"  VARCHAR2(15),
    "NGAYSX"  DATE,
    "HSD"     DATE,
    "GIAGOC"  NUMBER(11, 2),
    "MANCC"   NUMBER(11)
);

CREATE UNIQUE INDEX "MALO_PK" ON
    "KHOSANPHAM" (
        "MALO"
    );

ALTER TABLE "KHOSANPHAM"
    ADD CONSTRAINT "KHO_MA_FK" FOREIGN KEY ( "MASP" )
        REFERENCES "SANPHAM" ( "MASP" )
    ENABLE;
    insert into KHOSANPHAM values(1,3559,'Growsel','H?p',114000,25,'ROUSSEL','0150323',to_date('20-03-2023','DD-MM-YYYY'),to_date('20-03-2026','DD-MM-YYYY'),100000,1);
insert into KHOSANPHAM values(2,2197,'Derma-forte(Advanced) 15g','Tuýp',96800,15,'GAM MA CHEMICALS PTE','DMF.010623',to_date('05-06-2023','DD-MM-YYYY'),to_date('05-06-2026','DD-MM-YYYY'),80000,1);
insert into KHOSANPHAM values(3,2759,'Canxi Vitamin D3 KingPhar','H?p',105000,10,'CÔNG TY TNHH CÔNG NGH?  HERBITECH','020423',to_date('10-04-2023','DD-MM-YYYY'),to_date('10-04-2026','DD-MM-YYYY'),80000,1);
insert into KHOSANPHAM values(5,2759,'Canxi Vitamin D3 KingPhar','H?p',105000,30,'CÔNG TY TNHH CÔNG NGH?  HERBITECH','020823',to_date('10-05-2023','DD-MM-YYYY'),to_date('10-05-2026','DD-MM-YYYY'),80000,1);
