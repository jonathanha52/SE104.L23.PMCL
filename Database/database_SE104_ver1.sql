CREATE SCHEMA `se104` ;
use se104;

create table RoomInformation (
RoomID int not null,
RoomTypeName char(20) not null,
RoomSlot int not null,
RoomIsFull boolean not null,
PricePerSlot double not null,
Primary Key (RoomID)
);


create table Booking (
BookingID int not null,
RoomID int not null,
CustomerID int not null,
StartDate date not null,
EndDate date not null,
Primary Key (BookingID)
);
create table Customer (
CustomerID int not null,
CustomerName char(50) not null,
Primary Key (CustomerID)
);
create table Staff (
StaffID int not null auto_increment,
StaffName char(50) not null,
Primary Key (StaffID)
);
create table ServiceBill (
ServiceBillID int not null,
RoomID int not null,
StaffID int not null,
ServiceID int not null,
ServiceDate date not null,
Quantity int not null,
Primary Key (ServiceBillID)
);
create table Service(
ServiceID int not null auto_increment,
DepartmentID int not null,
ServiceName varchar(20) not null,
Unit char(7) not null,
UnitPrice float not null,
Primary Key(ServiceID)
);
create table ServiceDepartment(
DepartmentID int not null auto_increment,
DepartmentName char(50) not null,
Primary Key (DepartmentID)
);
create table Deposit(
DepositID int not null,
BookingID int not null,
CustomerID int not null,
DepositDate date not null,
DepositAmount double not null,
Explanation varchar(100) not null,
Primary Key (DepositID)
);
create table Payment(
PaymentID int not null,
ServiceBillID int not null,
BookingID int not null,
PaymentTypeID int not null,
PayDate date not null,
PaymentStatus boolean not null,
Primary Key (PaymentID)
);
create table PaymentType(
PaymentTypeID int not null auto_increment,
PaymentTypeName char(10) not null,
Primary Key (PaymentTypeID)
);

ALTER TABLE Booking
ADD CONSTRAINT FK1_Booking_RoomID FOREIGN KEY (RoomID) REFERENCES RoomInformation(RoomID);
ALTER TABLE Booking
ADD CONSTRAINT FK2_Booking_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);

-- ALTER TABLE RoomInformation
-- ADD CONSTRAINT FK_RoomInformation_RoomTypeID FOREIGN KEY (RoomTypeID) REFERENCES RoomType(RoomTypeID);

ALTER TABLE ServiceBill
ADD CONSTRAINT FK1_ServiceBill_RoomID FOREIGN KEY (RoomID) REFERENCES RoomInformation(RoomID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK2_ServiceBill_StaffID FOREIGN KEY (StaffID) REFERENCES Staff(StaffID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK3_ServiceBill_ServiceID FOREIGN KEY (ServiceID) REFERENCES Service(ServiceID);


ALTER TABLE Service
ADD CONSTRAINT FK_Service_DepartmentID FOREIGN KEY (DepartmentID) REFERENCES ServiceDepartment(DepartmentID);

ALTER TABLE Deposit
ADD CONSTRAINT FK1_Deposit_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);
ALTER TABLE Deposit
ADD CONSTRAINT FK2_Deposit_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);

ALTER TABLE Payment
ADD CONSTRAINT FK1_Payment_ServiceBillID FOREIGN KEY (ServiceBillID) REFERENCES ServiceBill(ServiceBillID);
ALTER TABLE Payment
ADD CONSTRAINT FK2_Payment_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);
ALTER TABLE Payment
ADD CONSTRAINT FK3_Payment_PaymentTypeID FOREIGN KEY (PaymentTypeID) REFERENCES PaymentType(PaymentTypeID);



insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (101, 'Ở ghép', 4, 0, 100000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (102, 'Ở ghép', 3, 0, 100000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (103, 'Nguyên phòng', 2, 0, 100000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (104, 'Nguyên phòng', 1, 0, 100000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (105, 'Ở ghép', 4, 0, 100000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (106, 'Nguyên phòng', 3, 0, 200000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (107, 'Nguyên phòng', 2, 1, 200000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (108, 'Ở ghép', 1, 1, 200000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (109, 'Nguyên phòng', 1, 0, 200000);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot) values (110, 'Ở ghép', 1, 0, 200000);

insert into Staff (StaffID, StaffName) values (Null, 'Lê Văn Trí');
insert into Staff (StaffID, StaffName) values (Null, 'Huỳnh Lữ Anh Khoa');
insert into Staff (StaffID, StaffName) values (Null, 'Hà Thúc Anh Khoa');

insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Ăn uống');
insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Massage');
insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Giặt ủi');

insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 1, 'Thức ăn 1', 'phần', 50000);
insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 1, 'Thức ăn 2', 'phần', 100000);
insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 1, 'Thức uống 1', 'phần', 40000);
insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 2, 'Massage 1 ', 'lần', 200000);
insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 2, 'Massage 2', 'lần', 300000);
insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 3, 'Giặt sấy', 'kg', 20000);
insert into Service(ServiceID, DepartmentID, ServiceName, Unit, UnitPrice) values (Null, 3, 'Giặt ủi', 'kg', 30000);

insert into PaymentType(PaymentTypeID, PaymentTypeName) values (Null, 'Card');
insert into PaymentType(PaymentTypeID, PaymentTypeName) values (Null, 'Cash');


select * from RoomType;
select * from RoomInformation;
select * from Staff;
select * from ServiceDepartment;
select * from Service;
select * from PaymentType;
