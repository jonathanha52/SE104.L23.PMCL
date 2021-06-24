CREATE SCHEMA `se104` ;
use se104;

create table RoomInformation (
RoomID int not null, /*tạo roomID theo dạng số phòng 101, 102,.. 201, 202,...*/
RoomTypeName char(50) not null,
RoomSlot int not null,
RoomIsFull boolean not null,
PricePerSlot double not null, /*giá tiền cho mỗi slot, nếu thuê nguyên căn thì giá bằng roomslot*priceperslot, nên để là số chẵn cho dễ tính*/
SlotRemaining int not null,
Primary Key (RoomID)
);

-- create table RoomType (
-- RoomTypeID int not null auto_increment,
-- RoomTypeName varchar(100) not null, /*tạo tên loại phòng: Ở ghép và Nguyên phòng*/
-- Primary Key (RoomTypeID)
-- );

create table Booking (
BookingID int not null,
RoomID int not null,
CustomerID int not null,
StartDate date not null,
EndDate date not null,
Slot int not null,
Primary Key (BookingID)
);
create table Customer (
CustomerID int not null, /*đây là CMND của khách hàng*/
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
DepartmentName varchar(20) not null,
ServiceName varchar(20) not null,
Unit char(7) not null,
UnitPrice double not null,
Primary Key(ServiceID)
);
-- create table ServiceDepartment(
-- DepartmentID int not null auto_increment,
-- DepartmentName char(50) not null,
-- Primary Key (DepartmentID)
-- );
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
PaymentTypeName varchar(20) not null,
PayDate date not null,
PaymentStatus boolean not null,
Primary Key (PaymentID)
);
-- create table PaymentType(
-- PaymentTypeID int not null auto_increment,
-- PaymentTypeName char(10) not null,
-- Primary Key (PaymentTypeID)
-- );

ALTER TABLE Booking
ADD CONSTRAINT FK1_Booking_RoomID FOREIGN KEY (RoomID) REFERENCES RoomInformation(RoomID);
ALTER TABLE Booking
ADD CONSTRAINT FK2_Booking_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);


ALTER TABLE ServiceBill
ADD CONSTRAINT FK1_ServiceBill_RoomID FOREIGN KEY (RoomID) REFERENCES RoomInformation(RoomID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK2_ServiceBill_StaffID FOREIGN KEY (StaffID) REFERENCES Staff(StaffID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK3_ServiceBill_ServiceID FOREIGN KEY (ServiceID) REFERENCES Service(ServiceID);


ALTER TABLE Deposit
ADD CONSTRAINT FK1_Deposit_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);
ALTER TABLE Deposit
ADD CONSTRAINT FK2_Deposit_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);

ALTER TABLE Payment
ADD CONSTRAINT FK1_Payment_ServiceBillID FOREIGN KEY (ServiceBillID) REFERENCES ServiceBill(ServiceBillID);
ALTER TABLE Payment
ADD CONSTRAINT FK2_Payment_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);


/*insert vào các table: 
roomType (2 loại là Ở ghép và Nguyên Phòng), roominfomation,
staff, (thêm khoảng 3 staff)
serviceDepartment (3 loại: Ăn uống, Massage, Giặt ủi), service (mỗi loại khoảng 2-3 lựa chọn),
paymenttype (2 loại là Cash và Card)*/

-- insert into RoomType (RoomTypeID, RoomTypeName) values (Null, 'Ở ghép');
-- insert into RoomType (RoomTypeID, RoomTypeName) values (Null, 'Nguyên phòng');

insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (101, 'Ở ghép', 4, 0, 100000, 0);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (102, 'Nguyên phòng', 3, 0, 100000, 0);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (103, 'Ở ghép', 2, 1, 100000, 2);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (104, 'Ở ghép', 1, 0, 100000, 0);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (105, 'Ở ghép', 4, 0, 100000, 0);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (106, 'Nguyên phòng', 3, 0, 200000, 0);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (107, 'Nguyên phòng', 2, 1, 200000, 1);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (108, 'Nguyên phòng', 1, 1, 200000, 1);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (109, 'Ở ghép', 1, 0, 200000, 0);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (110, 'Nguyên phòng', 1, 0, 200000, 1);

insert into Staff (StaffID, StaffName) values (Null, 'Lê Văn Trí');
insert into Staff (StaffID, StaffName) values (Null, 'Huỳnh Lữ Anh Khoa');
insert into Staff (StaffID, StaffName) values (Null, 'Hà Thúc Anh Khoa');

-- insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Ăn uống');
-- insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Massage');
-- insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Giặt ủi');

insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Ăn uống', 'Thức ăn 1', 'phần', 50000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Ăn uống', 'Thức ăn 2', 'phần', 100000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Ăn uống', 'Thức uống 1', 'phần', 40000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Massage', 'Massage 1 ', 'lần', 200000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Massage', 'Massage 2', 'lần', 300000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Giặt ủi', 'Giặt sấy', 'kg', 20000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Giặt ủi', 'Giặt ủi', 'kg', 30000);

-- insert into PaymentType(PaymentTypeID, PaymentTypeName) values (Null, 'Card');
-- insert into PaymentType(PaymentTypeID, PaymentTypeName) values (Null, 'Cash');


select * from RoomInformation;
select * from Staff;
select * from Service;
