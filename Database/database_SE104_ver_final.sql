CREATE SCHEMA `se104` ;
use se104;

create table RoomInformation (
RoomID int not null,
RoomTypeName varchar(20) not null,
RoomSlot int not null,
RoomIsFull boolean not null,
PricePerSlot double not null,
SlotRemaining int not null,
Primary Key (RoomID)
);


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
CustomerID int not null,
CustomerName varchar(50) not null,
Primary Key (CustomerID)
);
create table Staff (
StaffID int not null auto_increment,
StaffName varchar(50) not null,
Primary Key (StaffID)
);
create table ServiceBill (
ServiceBillID int not null,
BookingID int not null,
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
Unit varchar(7) not null,
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
BookingID int not null,
PaymentTypeName varchar(50) not null,
PayDate date not null,
PaymentStatus boolean not null,
Amount double not null,
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

-- ALTER TABLE RoomInformation
-- ADD CONSTRAINT FK_RoomInformation_RoomTypeID FOREIGN KEY (RoomTypeID) REFERENCES RoomType(RoomTypeID);

-- ALTER TABLE ServiceBill
-- ADD CONSTRAINT FK1_ServiceBill_RoomID FOREIGN KEY (RoomID) REFERENCES RoomInformation(RoomID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK1_ServiceBill_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK2_ServiceBill_StaffID FOREIGN KEY (StaffID) REFERENCES Staff(StaffID);
ALTER TABLE ServiceBill
ADD CONSTRAINT FK3_ServiceBill_ServiceID FOREIGN KEY (ServiceID) REFERENCES Service(ServiceID);


-- ALTER TABLE Service
-- ADD CONSTRAINT FK_Service_DepartmentID FOREIGN KEY (DepartmentID) REFERENCES ServiceDepartment(DepartmentID);

ALTER TABLE Deposit
ADD CONSTRAINT FK1_Deposit_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);
ALTER TABLE Deposit
ADD CONSTRAINT FK2_Deposit_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);

-- ALTER TABLE Payment
-- ADD CONSTRAINT FK1_Payment_ServiceBillID FOREIGN KEY (ServiceBillID) REFERENCES ServiceBill(ServiceBillID);
ALTER TABLE Payment
ADD CONSTRAINT FK_Payment_BookingID FOREIGN KEY (BookingID) REFERENCES Booking(BookingID);
-- ALTER TABLE Payment
-- ADD CONSTRAINT FK3_Payment_PaymentTypeID FOREIGN KEY (PaymentTypeID) REFERENCES PaymentType(PaymentTypeID);



insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (101, '??? gh??p', 4, 0, 100000,4);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (102, '??? gh??p', 3, 0, 100000,3);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (103, 'Nguy??n ph??ng', 5, 0, 100000,5);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (104, 'Nguy??n ph??ng', 1, 0, 100000,1);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (105, '??? gh??p', 4, 0, 100000,4);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (106, 'Nguy??n ph??ng', 3, 0, 200000,3);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (107, 'Nguy??n ph??ng', 2, 1, 200000,2);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (108, '??? gh??p', 5, 1, 200000,5);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (109, 'Nguy??n ph??ng', 3, 0, 200000,3);
insert into RoomInformation (RoomID, RoomTypeName, RoomSlot, RoomIsFull, PricePerSlot, SlotRemaining) values (110, '??? gh??p', 2, 0, 200000,2);

insert into Staff (StaffID, StaffName) values (Null, 'L?? V??n Tr??');
insert into Staff (StaffID, StaffName) values (Null, 'Hu???nh L??? Anh Khoa');
insert into Staff (StaffID, StaffName) values (Null, 'H?? Th??c Anh Khoa');

-- insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, '??n u???ng');
-- insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Massage');
-- insert into ServiceDepartment(DepartmentID, DepartmentName) values (Null, 'Gi???t ???i');

insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, '??n u???ng', 'Th???c ??n 1', 'ph???n', 50000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, '??n u???ng', 'Th???c ??n 2', 'ph???n', 100000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, '??n u???ng', 'Th???c u???ng 1', 'ph???n', 40000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Massage', 'Massage 1 ', 'l???n', 200000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Massage', 'Massage 2', 'l???n', 300000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Gi???t ???i', 'Gi???t s???y', 'kg', 20000);
insert into Service(ServiceID, DepartmentName, ServiceName, Unit, UnitPrice) values (Null, 'Gi???t ???i', 'Gi???t ???i', 'kg', 30000);

-- insert into PaymentType(PaymentTypeID, PaymentTypeName) values (Null, 'Ti???n m???t');
-- insert into PaymentType(PaymentTypeID, PaymentTypeName) values (Null, 'Th??? ATM');


select * from RoomType;
select * from RoomInformation;
select * from Staff;
select * from ServiceDepartment;
select * from Service;
select * from PaymentType;
