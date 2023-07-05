DROP
DATABASE SMART_PHONE_STORE_N7
GO 
CREATE
DATABASE SMART_PHONE_STORE_N7
GO
USE SMART_PHONE_STORE_N7
GO 
--Capacity 
CREATE TABLE Capacity
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
--color 
CREATE TABLE Color
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
-- Manufacture
CREATE TABLE Manufacture
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
-- Category
CREATE TABLE Category
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
--Battery
CREATE TABLE Battery
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
-- Chip
CREATE TABLE Chip
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
-- Ram
CREATE TABLE Ram
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
--Screen
CREATE TABLE Screen
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)

-- Size
CREATE TABLE Size
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)
--Product 
CREATE TABLE Product
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    ImportPrice  FLOAT DEFAULT NULL,
    Price        FLOAT DEFAULT NULL,
    Quantity     INT   DEFAULT NULL,
    DateCreate   DATE  DEFAULT NULL,
    DateUpdate   DATE  DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT   DEFAULT NULL
)
-- Images
CREATE TABLE Images
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    LinkImage    NVARCHAR(250) DEFAULT NULL,
    NameImage    NVARCHAR(100) DEFAULT NULL,
    Describe     NVARCHAR(250) DEFAULT NULL,
    DateCreate   DATE   DEFAULT NULL,
    DateUpdate   DATE   DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT    DEFAULT NULL,
    Id_Product   BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Product) REFERENCES Product (Id)
)

-- ProductDetail 
CREATE TABLE ProductDetail
(
    Id             bigint PRIMARY KEY IDENTITY(1,1),
    Code           NVARCHAR(20) NOT NULL,
    Name           NVARCHAR(200) NOT NULL,
    DateCreate     DATE   DEFAULT NULL,
    DateUpdate     DATE   DEFAULT NULL,
    PersonCreate   NVARCHAR(200) DEFAULT NULL,
    PersonUpdate   NVARCHAR(200) DEFAULT NULL,
    Describe       NVARCHAR(250) DEFAULT NULL,
    Status         INT    DEFAULT NULL,
    Id_Capacity    BIGINT DEFAULT NULL,
    Id_Color       BIGINT DEFAULT NULL,
    Id_Manufacture BIGINT DEFAULT NULL,
    Id_Category    BIGINT DEFAULT NULL,
    Id_Battery     BIGINT DEFAULT NULL,
    Id_Chip        BIGINT DEFAULT NULL,
    Id_Ram         BIGINT DEFAULT NULL,
    Id_Screen      BIGINT DEFAULT NULL,
    Id_Size        BIGINT DEFAULT NULL,
    Id_Product     BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Capacity) REFERENCES Capacity (Id),
    FOREIGN KEY (Id_Color) REFERENCES Color (Id),
    FOREIGN KEY (Id_Category) REFERENCES Category (Id),
    FOREIGN KEY (Id_Battery) REFERENCES Battery (Id),
    FOREIGN KEY (Id_Chip) REFERENCES Chip (Id),
    FOREIGN KEY (Id_Ram) REFERENCES Ram (Id),
    FOREIGN KEY (Id_Screen) REFERENCES Screen (Id),
    FOREIGN KEY (Id_Size) REFERENCES Size (Id),
    FOREIGN KEY (Id_Product) REFERENCES Product (Id),
    FOREIGN KEY (Id_Manufacture) REFERENCES Manufacture (Id)
)
-- Thêm dữ liệu vào bảng Product Detail
ALTER TABLE ProductDetail
    ADD images NVARCHAR(MAX)
ALTER TABLE ProductDetail
    ADD Price FLOAT
ALTER TABLE ProductDetail
ADD Quantity INT
--Asset Product Detail 
CREATE TABLE Assets
(
    Id                bigint PRIMARY KEY IDENTITY(1,1),
    LinkImage         NVARCHAR(250) DEFAULT NULL,
    NameImage         NVARCHAR(100) DEFAULT NULL,
    Describe          NVARCHAR(250) DEFAULT NULL,
    DateCreate        DATE   DEFAULT NULL,
    DateUpdate        DATE   DEFAULT NULL,
    PersonCreate      NVARCHAR(200) DEFAULT NULL,
    PersonUpdate      NVARCHAR(200) DEFAULT NULL,
    Status            INT    DEFAULT NULL,
    Id_ProductDetails BIGINT DEFAULT NULL,
    Id_ProductDetail  BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail (Id)
)

-- Imei 
CREATE TABLE Imei
(
    Id               bigint PRIMARY KEY IDENTITY(1,1),
    Code             NVARCHAR(20) NOT NULL,
    DateAddded       DATE   DEFAULT NULL,
    SaleDate         DATE   DEFAULT NULL,
    PersonSale       NVARCHAR(200) DEFAULT NULL,
    PersonUpdate     NVARCHAR(200) DEFAULT NULL,
    Status           INT    DEFAULT NULL,
    Id_ProductDetail BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail (Id)
)
-- DB bán hàng GD 2
CREATE TABLE Position
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)

CREATE TABLE Staff
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    Gender       INT    DEFAULT NULL,
    DateOfBirth  DATE   DEFAULT NULL,
    Address      NVARCHAR( MAX) DEFAULT NULL,
    Email        NVARCHAR( MAX) DEFAULT NULL,
    PassWord     NVARCHAR(50) DEFAULT NULL,
    Status       INT    DEFAULT NULL,
    DateCreate   DATE   DEFAULT NULL,
    DateUpdate   DATE   DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    DateStart    DATE   DEFAULT NULL,
    Note         NVARCHAR( MAX) DEFAULT NULL,
    Id_Position  BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Position) REFERENCES Position (Id)
)

CREATE TABLE RankCustomer
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    Name         NVARCHAR(200) NOT NULL,
    Describe     NVARCHAR( MAX) DEFAULT NULL,
    MiniumScore  INT  DEFAULT NULL,
    DateCreate   DATE DEFAULT NULL,
    DateUpdate   DATE DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    Status       INT  DEFAULT NULL
)

CREATE TABLE Customer
(
    Id                bigint PRIMARY KEY IDENTITY(1,1),
    Code              NVARCHAR(20) NOT NULL,
    Name              NVARCHAR(200) NOT NULL,
    Date              DATE   DEFAULT NULL,
    Address           NVARCHAR( MAX) DEFAULT NULL,
    PhoneNumber       NVARCHAR(10) DEFAULT NULL,
    Email             NVARCHAR(150) DEFAULT NULL,
    Gender            INT    DEFAULT null,
    Avatar            NVARCHAR( MAX) DEFAULT NULL,
    AccumulatedPoints FLOAT  DEFAULT NULL,
    DateCreate        DATE   DEFAULT NULL,
    DateUpdate        DATE   DEFAULT NULL,
    PersonCreate      NVARCHAR(200) DEFAULT NULL,
    PersonUpdate      NVARCHAR(200) DEFAULT NULL,
    Id_Rank           BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Rank) REFERENCES RankCustomer (Id),
    Status            INT    DEFAULT NULL
)

CREATE TABLE Bill
(
    Id                   bigint PRIMARY KEY IDENTITY(1,1),
    Code                 NVARCHAR(20) NOT NULL,
    DateCreate           DATE   DEFAULT NULL,
    DateUpdate           DATE   DEFAULT NULL,
    PersonCreate         NVARCHAR(200) DEFAULT NULL,
    PersonUpdate         NVARCHAR(200) DEFAULT NULL,
    Quantity             INT    DEFAULT NULL,
    DateOfPayment        DATE   DEFAULT NULL,
    DateShipping         DATE   DEFAULT NULL,
    DeceivedDate         DATE   DEFAULT NULL,
    NameCustomer         NVARCHAR(250) DEFAULT NULL,
    Address              NVARCHAR( MAX) DEFAULT NULL,
    MoneyReduced         FLOAT  DEFAULT NULL,
    TotalMoney           FLOAT  DEFAULT NULL,
    Status               INT    DEFAULT NULL,
    ConsigneePhoneNumber NVARCHAR(10) DEFAULT NULL,
    DeliveryPhoneNumber  NVARCHAR(10) DEFAULT NULL,
    NameShipper          NVARCHAR( MAX) DEFAULT NULL,
    Discount             NVARCHAR( MAX) DEFAULT NULL,
    Id_Customer          BIGINT DEFAULT NULL,
    Id_Staff             BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer (Id),
    FOREIGN KEY (Id_Staff) REFERENCES Staff (Id)
)
CREATE TABLE BillDetail
(
    Id               bigint PRIMARY KEY IDENTITY(1,1),
    Code             NVARCHAR(20) NOT NULL,
    Quantity         INT    DEFAULT NULL,
    UnitPrice        FLOAT  DEFAULT NULL,
    DiscountMoney    FLOAT  DEFAULT NULL,
    DateCreate       DATE   DEFAULT NULL,
    DateUpdate       DATE   DEFAULT NULL,
    PersonCreate     NVARCHAR(200) DEFAULT NULL,
    PersonUpdate     NVARCHAR(200) DEFAULT NULL,
    Status           INT    DEFAULT NULL,
    CodeImei         NVARCHAR(20) NOT NULL,
    Id_ProductDetail BIGINT DEFAULT NULL,
    Id_Bill          BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail (Id),
    FOREIGN KEY (Id_Bill) REFERENCES Bill (Id)
)

CREATE TABLE MembershipCard
(
    Id               bigint PRIMARY KEY IDENTITY(1,1),
    Code             NVARCHAR(20) NOT NULL,
    Color            NVARCHAR(20) NOT NULL,
    Type             NVARCHAR(100) DEFAULT NULL,
    ReleaseDate      DATE   DEFAULT NULL,
    CancellationDate DATE   DEFAULT NULL,
    DateCreate       DATE   DEFAULT NULL,
    DateUpdate       DATE   DEFAULT NULL,
    PersonCreate     NVARCHAR(200) DEFAULT NULL,
    PersonUpdate     NVARCHAR(200) DEFAULT NULL,
    Status           INT    DEFAULT NULL,
    Id_Customer      BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer (Id)
)
CREATE TABLE Address
(
    Id               bigint PRIMARY KEY IDENTITY(1,1),
    Code             NVARCHAR(20) NOT NULL,
    DescribeSpecific NVARCHAR( MAX) DEFAULT NULL,
    City             NVARCHAR(30) DEFAULT NULL,
    District         NVARCHAR(30) DEFAULT NULL,
    Commune          NVARCHAR(30) DEFAULT NULL,
    Street           NVARCHAR(30) DEFAULT NULL,
    DateCreate       DATE   DEFAULT NULL,
    DateUpdate       DATE   DEFAULT NULL,
    PersonCreate     NVARCHAR(200) DEFAULT NULL,
    PersonUpdate     NVARCHAR(200) DEFAULT NULL,
    Status           INT    DEFAULT NULL,
    Id_Customer      BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer (Id)
)
CREATE TABLE Cart
(
    Id           bigint PRIMARY KEY IDENTITY(1,1),
    Code         NVARCHAR(20) NOT NULL,
    DateCreate   DATE     DEFAULT NULL,
    DateUpdate   DATE     DEFAULT NULL,
    PersonCreate NVARCHAR(200) DEFAULT NULL,
    PersonUpdate NVARCHAR(200) DEFAULT NULL,
    TimeUpdate   DATETIME DEFAULT NULL,
    Status       INT      DEFAULT NULL,
    Note         NVARCHAR( MAX) DEFAULT NULL,
    Id_Customer  BIGINT   DEFAULT NULL,
    FOREIGN KEY (Id_Customer) REFERENCES Customer (Id)
)

CREATE TABLE CartDetailShooping
(
    Id               bigint PRIMARY KEY IDENTITY(1,1),
    Quantity         INT    DEFAULT NULL,
    DateCreate       DATE   DEFAULT NULL,
    DateUpdate       DATE   DEFAULT NULL,
    PersonCreate     NVARCHAR(200) DEFAULT NULL,
    PersonUpdate     NVARCHAR(200) DEFAULT NULL,
    Price            FLOAT  DEFAULT NULL,
    TotalMoney       Float  DEFAULT NULL,
    Status           INT    DEFAULT NULL,
    Id_Cart          BIGINT DEFAULT NULL,
    Id_ProductDetail BIGINT DEFAULT NULL,
    Id_Bill          BIGINT DEFAULT NULL,
    FOREIGN KEY (Id_Cart) REFERENCES Cart (Id),
    FOREIGN KEY (Id_ProductDetail) REFERENCES ProductDetail (Id),
    FOREIGN KEY (Id_Bill) REFERENCES Bill (Id)
)

ALTER TABLE ProductDetail
    ADD images NVARCHAR(MAX)
ALTER TABLE ProductDetail
    ADD Price FLOAT
