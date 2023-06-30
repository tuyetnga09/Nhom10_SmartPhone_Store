CREATE DATABASE SMART_PHONE_STORE_N7
GO
USE SMART_PHONE_STORE_N7
GO 
--Capacity 
CREATE TABLE Capacity(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
--color 
SELECT * from ProductDetail
select * from Color
CREATE TABLE Color(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
-- Manufacture
CREATE TABLE Manufacture(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
-- Category
CREATE TABLE Category(
     Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
--Battery
CREATE TABLE Battery(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
-- Chip
CREATE TABLE Chip(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
-- Ram
CREATE TABLE Ram(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
--Screen
CREATE TABLE Screen(
  Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)

-- Size
CREATE TABLE Size(
     Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)
-- Images
CREATE TABLE Images(
     Id bigint PRIMARY KEY IDENTITY(1,1),
     LinkImage NVARCHAR(250) DEFAULT NULL,
     NameImage NVARCHAR(100) DEFAULT NULL,
     Describe NVARCHAR(250) DEFAULT NULL,
      DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)

--Product 
CREATE TABLE Product(
 Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    ImportPrice FLOAT DEFAULT NULL,
    Price FLOAT DEFAULT NULL,
    Quantity INT DEFAULT NULL, 
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL,
    Id_Images BIGINT DEFAULT NULL,
     FOREIGN KEY ( Id_Images) REFERENCES Images(Id)
)
-- ProductDetail 
IF OBJECT_ID('ProductDetail') IS NOT NULL
DROP TABLE ProductDetail 
GO
CREATE TABLE ProductDetail (
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Describe NVARCHAR(250) DEFAULT NULL,
    Status INT DEFAULT NULL,
    Id_Capacity BIGINT DEFAULT NULL,
    Id_Color BIGINT DEFAULT NULL,
    Id_Manufacture BIGINT DEFAULT NULL,
    Id_Category BIGINT DEFAULT NULL,
    Id_Battery BIGINT DEFAULT NULL,
    Id_Chip BIGINT DEFAULT NULL,
    Id_Ram BIGINT DEFAULT NULL,
    Id_Screen BIGINT DEFAULT NULL,
    Id_Size BIGINT DEFAULT NULL,
    Id_Product BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Capacity) REFERENCES Capacity(Id),
    FOREIGN KEY (  Id_Color) REFERENCES Color(Id),
     FOREIGN KEY (Id_Category) REFERENCES Category(Id),
    FOREIGN KEY (Id_Battery) REFERENCES Battery(Id),
    FOREIGN KEY(Id_Chip) REFERENCES Chip(Id),
    FOREIGN KEY(Id_Ram) REFERENCES Ram(Id),
     FOREIGN KEY(Id_Screen) REFERENCES Screen(Id),
    FOREIGN KEY (Id_Size) REFERENCES Size(Id),
    FOREIGN KEY (Id_Product) REFERENCES Product(Id),
     FOREIGN KEY (Id_Manufacture) REFERENCES Manufacture(Id)
)
-- Imei 
CREATE TABLE Imei(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    DateAddded DATE DEFAULT NULL,
    SaleDate  DATE DEFAULT NULL,
    PersonSale NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL,
    Id_ProductDetail BIGINT DEFAULT NULL, 
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail(Id)
)
SELECT * from Product 
select * from ProductDetail JOIN Product ON ProductDetail.Id = Product.Id

SELECT Id, Code, DateAddded, SaleDate, PersonSale, PersonUpdate, Status, Id_ProductDetail FROM Imei
select * from ProductDetail
select * from Imei
INSERT INTO SMART_PHONE_STORE_N7.dbo.Capacity
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('CP1', 'Capacity 1', GETDATE(), GETDATE(), 'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Capacity
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('CP2', 'Capacity 2', GETDATE(), GETDATE(), 'Hoang Van Hieu', 'Dieu Thuy', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Capacity
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('CP3', 'Capacity 3', GETDATE(), GETDATE(), 'Tran Cong Minh', 'Nguyen  Trung Hieu', 0);


INSERT INTO SMART_PHONE_STORE_N7.dbo.Battery
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('BT1', 'Battery',GETDATE(), GETDATE(), 'Tran Cong Minh', 'Nguyen  Trung Hieu', 0);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Battery
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('BT2', 'Battery 2',GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Battery
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('BT3', 'Battery 3',GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);


INSERT INTO SMART_PHONE_STORE_N7.dbo.Images
(LinkImage, NameImage, [Describe], DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('image1', 'anh_dt1.img', 'Iphone 11 - Mau xanh', GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);


INSERT INTO SMART_PHONE_STORE_N7.dbo.Product
(Code, Name, ImportPrice, Price, Quantity, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status, Id_Images)
VALUES('Product1', 'Iphone 12', 1500000, 2500000, 30, GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1, 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Product
(Code, Name, ImportPrice, Price, Quantity, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status, Id_Images)
VALUES('Product2', 'Iphone 13', 2500000, 3500000, 35, GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0, 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Product
(Code, Name, ImportPrice, Price, Quantity, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status, Id_Images)
VALUES('Product3', 'Iphone 14', 3000000, 3500000, 35, GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1, 1);

INSERT INTO SMART_PHONE_STORE_N7.dbo.Category
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('CT1', 'Iphone Japan', GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Category
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('CT2', 'Iphone China', GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Category
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('CT3', 'Iphone US', GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1);

INSERT INTO SMART_PHONE_STORE_N7.dbo.Chip
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Chip 1', 'Chip M1',GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1)
INSERT INTO SMART_PHONE_STORE_N7.dbo.Chip
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Chip 2', 'Chip Intel',GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1)
INSERT INTO SMART_PHONE_STORE_N7.dbo.Chip
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Chip 1', 'Chip Pro',GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0)

INSERT INTO SMART_PHONE_STORE_N7.dbo.Color
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('color1', 'Green',GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0)
INSERT INTO SMART_PHONE_STORE_N7.dbo.Color
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('color2', 'PINK',GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1)
INSERT INTO SMART_PHONE_STORE_N7.dbo.Color
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('color3', 'Black',GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1)

INSERT INTO SMART_PHONE_STORE_N7.dbo.Ram
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Ram 1', '64G', GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Ram
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Ram 2', '120G', GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Ram
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Ram 3', '250G', GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);


INSERT INTO SMART_PHONE_STORE_N7.dbo.Manufacture
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('MN1', 'Manufacture 1', GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Manufacture
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('MN2', 'Manufacture 2', GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0)
INSERT INTO SMART_PHONE_STORE_N7.dbo.Manufacture
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('MN3', 'Manufacture 3', GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1);


INSERT INTO SMART_PHONE_STORE_N7.dbo.Screen
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Screen 1', '1220 HZ',GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Screen
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Screen 2', '1420 HZ',GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0)
INSERT INTO SMART_PHONE_STORE_N7.dbo.Screen
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Screen 2', '1440 HZ',GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1);

INSERT INTO SMART_PHONE_STORE_N7.dbo.[Size]
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Size 1', '15 cm',GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.[Size]
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Size 2', '20 cm',GETDATE(), GETDATE(),'Pham Tuyet Nga', 'Nguyen Hong Phong', 1);
INSERT INTO SMART_PHONE_STORE_N7.dbo.[Size]
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status)
VALUES('Size 3', '25 cm',GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu', 0);

INSERT INTO SMART_PHONE_STORE_N7.dbo.ProductDetail
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, [Describe], Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Size, Id_Product)
VALUES('ProductDetail 1', 'Iphone 11', GETDATE(), GETDATE(),'Tran Cong Minh', 'Nguyen  Trung Hieu' , 'Dien thoai ban chay nhaT apple smart', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO SMART_PHONE_STORE_N7.dbo.ProductDetail
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, [Describe], Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Size, Id_Product)
VALUES('ProductDetail 2', 'Iphone 12', GETDATE(), GETDATE(),'Tran Xuan Vu', 'Pham Tuyet Nga' , 'Dien thoai dung top 1 T3', 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO SMART_PHONE_STORE_N7.dbo.ProductDetail
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, [Describe], Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Size, Id_Product)
VALUES('ProductDetail 3', 'Iphone 10', GETDATE(), GETDATE(),'Hoang Van Hieu', 'Dieu Thuy' , 'Dien thoai dung top 1 T4', 1, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO SMART_PHONE_STORE_N7.dbo.ProductDetail
(Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, [Describe], Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Size, Id_Product)
VALUES('ProductDetail 4', 'Iphone 13', GETDATE(),  GETDATE(),'Tran Cong Minh', 'Nguyen  Hong Phong' , 'Dien thoai dung top 1 T5', 1, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
select * from ProductDetail
SELECT Id, Code,Name,  DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status, (select Name from Capacity where Id = 1) , Id_Color from [ProductDetail]
INSERT INTO SMART_PHONE_STORE_N7.dbo.Imei
(Code, DateAddded, SaleDate, PersonSale, PersonUpdate, Status, Id_ProductDetail)
VALUES('8KexzpY8s',  GETDATE(),  GETDATE(), 'Tran Xuan Vu', 'Pham Tuyet Nga', 1, NULL);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Imei
(Code, DateAddded, SaleDate, PersonSale, PersonUpdate, Status, Id_ProductDetail)
VALUES('1ybP!pmJm',  GETDATE(),  GETDATE(), 'Pham Huu Thanh', 'Pham Tuyet Nga', 0, NULL);
INSERT INTO SMART_PHONE_STORE_N7.dbo.Imei
(Code, DateAddded, SaleDate, PersonSale, PersonUpdate, Status, Id_ProductDetail)
VALUES('8KexzpY8s',  GETDATE(),  GETDATE(), 'Tran Xuan Vu', 'Nguyen The Quy', 1, NULL);

-- DB bán hàng GD 2
CREATE TABLE Position(
      Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL
)

CREATE TABLE Staff(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    Gender INT DEFAULT NULL, 
    DateOfBirth DATE DEFAULT NULL,
    Address NVARCHAR(MAX) DEFAULT NULL,
    Email  NVARCHAR(MAX) DEFAULT NULL,
    PassWord NVARCHAR(50) DEFAULT NULL,
    Status INT DEFAULT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL, 
    DateStart  DATE DEFAULT NULL,
    Note  NVARCHAR(MAX) DEFAULT NULL,
    Id_Position BIGINT DEFAULT NULL,
      FOREIGN KEY (Id_Position) REFERENCES Position(Id)
)

CREATE TABLE RankCustomer(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    Describe  NVARCHAR(MAX) DEFAULT NULL,
    MiniumScore INT DEFAULT NULL,
     DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
     Status INT DEFAULT NULL
)

CREATE TABLE Customer(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Name NVARCHAR(200) NOT NULL,
    Date DATE DEFAULT NULL,
    Address NVARCHAR(MAX) DEFAULT NULL,
    PhoneNumber NVARCHAR(10) DEFAULT NULL,
    Email NVARCHAR(150) DEFAULT NULL,
    Gender INT DEFAULT null,
    Avatar NVARCHAR(MAX) DEFAULT NULL,
    AccumulatedPoints FLOAT DEFAULT NULL,
     DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
      Id_Rank BIGINT DEFAULT NULL,
      FOREIGN KEY (Id_Rank) REFERENCES RankCustomer(Id),
       Status INT DEFAULT NULL
)

CREATE TABLE Bill(
    Id bigint PRIMARY KEY IDENTITY(1,1),
     Code NVARCHAR(20) NOT NULL, 
     DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Quantity INT DEFAULT NULL,
    DateOfPayment DATE DEFAULT NULL,
    DateShipping DATE DEFAULT NULL,
    DeceivedDate  DATE DEFAULT NULL,
    NameCustomer NVARCHAR(250) DEFAULT NULL,
     Address NVARCHAR(MAX) DEFAULT NULL,
     MoneyReduced FLOAT DEFAULT NULL,
     TotalMoney FLOAT DEFAULT NULL,
     Status INT DEFAULT NULL,
     ConsigneePhoneNumber NVARCHAR(10) DEFAULT NULL,
     DeliveryPhoneNumber NVARCHAR(10) DEFAULT NULL,
     NameShipper NVARCHAR(MAX) DEFAULT NULL,
     Discount NVARCHAR(MAX) DEFAULT NULL,
     Id_Customer BIGINT DEFAULT NULL,
     Id_Staff BIGINT DEFAULT NULL,
       FOREIGN KEY (Id_Customer) REFERENCES Customer(Id),
         FOREIGN KEY (Id_Staff) REFERENCES Staff(Id)
 )
 CREATE TABLE BillDetail(
    Id bigint PRIMARY KEY IDENTITY(1,1),
      Code NVARCHAR(20) NOT NULL,
    Quantity INT DEFAULT NULL,
    UnitPrice FLOAT DEFAULT NULL,
    DiscountMoney  FLOAT DEFAULT NULL,
     DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
     Status INT DEFAULT NULL,
     CodeImei  NVARCHAR(20) NOT NULL,
     Id_ProductDetail BIGINT DEFAULT NULL,
     Id_Bill BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail(Id),
    FOREIGN KEY (Id_Bill) REFERENCES Bill(Id)
 )

 CREATE TABLE MembershipCard(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    Color  NVARCHAR(20) NOT NULL,
    Type NVARCHAR(100) DEFAULT NULL,
    ReleaseDate DATE DEFAULT NULL,
    CancellationDate  DATE DEFAULT NULL,
     DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Status INT DEFAULT NULL,
    Id_Customer BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer(Id)
 )
 CREATE TABLE Address(
      Id bigint PRIMARY KEY IDENTITY(1,1),
      Code NVARCHAR(20) NOT NULL,
      DescribeSpecific NVARCHAR(MAX) DEFAULT NULL,
      City NVARCHAR(30) DEFAULT NULL,
      District NVARCHAR(30) DEFAULT NULL,
      Commune  NVARCHAR(30) DEFAULT NULL,
      Street  NVARCHAR(30) DEFAULT NULL,
       DateCreate DATE DEFAULT NULL,
        DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
     Status INT DEFAULT NULL,
      Id_Customer BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer(Id)
 )
 CREATE TABLE Cart(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Code NVARCHAR(20) NOT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    TimeUpdate DATETIME  DEFAULT NULL,
    Status INT DEFAULT NULL,
    Note NVARCHAR(MAX) DEFAULT NULL,
     Id_Customer BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer(Id)
 )

 CREATE TABLE CartDetailShooping(
    Id bigint PRIMARY KEY IDENTITY(1,1),
    Quantity INT DEFAULT NULL,
    DateCreate DATE DEFAULT NULL,
    DateUpdate  DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate  NVARCHAR(200) DEFAULT NULL,
    Price FLOAT DEFAULT NULL,
    TotalMoney Float DEFAULT NULL,
    Status INT DEFAULT NULL,
    Id_Cart BIGINT DEFAULT NULL,
    Id_ProductDetail  BIGINT DEFAULT NULL,
    Id_Bill  BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Cart) REFERENCES Cart(Id),
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail(Id),
    FOREIGN KEY (Id_Bill) REFERENCES Bill(Id)
 )