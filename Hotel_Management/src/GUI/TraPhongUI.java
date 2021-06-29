/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import DTO.*;
import Utils.DBUtils;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.math.*;
import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author huynh
 */
public class TraPhongUI extends javax.swing.JFrame {

    /**
     * Creates new form TraPhongUI
     */
    public TraPhongUI() {
        initComponents();
        loadPhuongThucTT();
        loadSoPhong();
        loadTableDVSD();
    }
    
    ArrayList<String> arrPhuongThucTT = new ArrayList<String>();
    public void loadPhuongThucTT(){
        arrPhuongThucTT.add("Tiền mặt");
        arrPhuongThucTT.add("Thẻ ATM");
        cbbPhuongThucTT.setModel(new DefaultComboBoxModel<String>(arrPhuongThucTT.toArray(new String[0])));
    }
    
    ArrayList<String> arrSoPhong = new ArrayList<String>();
    public void loadSoPhong(){
        Connection con = new DBUtils().createConn();
        String strSQL = "select roomid from booking";
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                if(!arrSoPhong.contains(Integer.toString(rs.getInt("roomid"))))
                    arrSoPhong.add(Integer.toString(rs.getInt("roomid")));
            }
            cbbSoPhong.setModel(new DefaultComboBoxModel<String>(arrSoPhong.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    DefaultTableModel tblModelDVSD = new DefaultTableModel();
    public void loadTableDVSD(){
        String tieuDe[] = {"Tên dịch vụ", "Số lượng", "Ngày đặt", "Đơn giá", "Thành tiền"};
        tblModelDVSD.setColumnIdentifiers(tieuDe);
        tblDVSD.setModel(tblModelDVSD);
        setVisible(true);
    }
    
    public double tongTien;
    public void loadChiTietTableDVSD(){
        tblModelDVSD.setRowCount(0);
        tongTien = 0;
        try{
            String strSQL = "select servicebill.serviceid, quantity, servicedate, servicename, unit, unitprice"
                    + " from servicebill, service where bookingid = "+txtMaDatPhong.getText()
                    + " and service.serviceid = servicebill.serviceid";
            Connection con = new DBUtils().createConn();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                String row[] = new String[5];
                row[0] = rs.getString("servicename");
                int soluong = rs.getInt("quantity");
                row[1] = Integer.toString(soluong)+" "+rs.getString("unit");
                row[2] = rs.getDate("servicedate").toString();
                double dongia = rs.getDouble("unitprice");
                row[3] = dinhDangTienTe(dongia);
                row[4] = dinhDangTienTe(soluong*dongia);
                tongTien += soluong*dongia;
                tblModelDVSD.addRow(row);
            }
            
            
            strSQL = "select slot, startdate, enddate, priceperslot from booking, roominformation"
                    + " where bookingid = "+txtMaDatPhong.getText()
                    + " and roominformation.roomid = "+cbbSoPhong.getSelectedItem();
            
            rs = stat.executeQuery(strSQL);
            if(rs.next()){
                String row[] = new String[5];
                row[0] = "Tiền phòng";
                TimeUnit time =TimeUnit.DAYS;
                long songay = time.convert(rs.getDate("enddate").getTime()-rs.getDate("startdate").getTime(), TimeUnit.MILLISECONDS);
                long songuoi = rs.getInt("slot");
                row[1] = songuoi+" người, "+songay+" ngày";
                row[2] = rs.getDate("startdate").toString();
                double dongia = rs.getDouble("priceperslot");
                row[3] = dinhDangTienTe(dongia)+"/người/ngày";
                row[4] = dinhDangTienTe(songuoi*songay*dongia);
                tongTien += songuoi*songay*dongia;
                tblModelDVSD.addRow(row);
            }
            
            strSQL = "select * from deposit where bookingid = "+txtMaDatPhong.getText();
            rs = stat.executeQuery(strSQL);
            if(rs.next()){
                String row[] = new String[5];
                row[0] = "Tiền đặt cọc";
                row[1] = "1";
                row[2] = rs.getDate("depositdate").toString();
                row[3] = "x";
                row[4] = "-"+dinhDangTienTe(rs.getDouble("depositamount"));
                tongTien -= rs.getDouble("depositamount");
                tblModelDVSD.addRow(row);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int layPaymentID(){
        int paymentID = 0;
        Connection con = new DBUtils().createConn();
        
        String strSQL = "select paymentID from payment order by paymentID desc";
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            if(rs.next()==false){
                paymentID = 1;
            }
            else{
                int paymentIDHienTai = rs.getInt("paymentID");
                paymentID = paymentIDHienTai+1;
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return paymentID;
    }
    
    public String dinhDangTienTe(double soTien){
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        String str = en.format(soTien);
        return str;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbSoPhong = new javax.swing.JComboBox<>();
        cbbPhuongThucTT = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateNgayRoiDi = new com.toedter.calendar.JDateChooser();
        txtTongTienTT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDVSD = new javax.swing.JTable();
        btnXacNhan = new javax.swing.JButton();
        ckbDaThanhToan = new javax.swing.JCheckBox();
        cbbTenKH = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMaDatPhong = new javax.swing.JTextField();
        btnThoat = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Trả phòng");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Số phòng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Phương thức thanh toán");

        cbbSoPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbSoPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSoPhongActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ngày rời đi");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tổng tiền thanh toán");

        dateNgayRoiDi.setEnabled(false);

        txtTongTienTT.setEditable(false);

        tblDVSD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDVSD);

        btnXacNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXacNhan.setText("Xác nhận thanh toán");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        ckbDaThanhToan.setText("Đã thanh toán");
        ckbDaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbDaThanhToanActionPerformed(evt);
            }
        });

        cbbTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenKHActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mã đặt phòng");

        txtMaDatPhong.setEditable(false);

        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnInHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addGap(84, 84, 84)
                        .addComponent(cbbSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)
                        .addGap(75, 75, 75)
                        .addComponent(dateNgayRoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43)
                        .addComponent(cbbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addGap(39, 39, 39)
                        .addComponent(txtTongTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel7)
                        .addGap(57, 57, 57)
                        .addComponent(txtMaDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cbbPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(ckbDaThanhToan)
                        .addGap(36, 36, 36)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnInHoaDon)
                        .addGap(46, 46, 46)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cbbSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel5))
                    .addComponent(dateNgayRoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4))
                    .addComponent(cbbPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(ckbDaThanhToan))
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        if(cbbTenKH.getSelectedItem().equals("")||txtTongTienTT.getText().equals("")||
                txtMaDatPhong.getText().equals("")||cbbTenKH.getSelectedItem().equals("")||
                cbbPhuongThucTT.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try{
                PaymentDTO payment = new PaymentDTO();
                payment.setPaymentID(layPaymentID());
                payment.setBookingID(Integer.parseInt(txtMaDatPhong.getText()));
                payment.setPaymentTypeName(cbbPhuongThucTT.getSelectedItem().toString());
                java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                payment.setPaymentDate(sqlDate);
                payment.setPaymentStatus(true);
                payment.setAmount(tongTien);
                PaymentBLL paymentBll = new PaymentBLL();
                int ret = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán?", "Thanh toán", JOptionPane.YES_NO_OPTION);
                if(ret == JOptionPane.YES_OPTION){

//                    ServiceBillDTO serviceBill = new ServiceBillDTO();
//                    serviceBill.setBookingID(Integer.parseInt(txtMaDatPhong.getText()));
//                    ServiceBillBLL serviceBillBll = new ServiceBillBLL();
//                    serviceBillBll.deleteServiceBill(serviceBill);
//                    
//                    
//                    DepositDTO deposit = new DepositDTO();
//                    deposit.setBookingID(Integer.parseInt(txtMaDatPhong.getText()));
//                    DepositBLL depositBll = new DepositBLL();
//                    depositBll.deleteDeposit(deposit);
                    String strSQL = "select * from booking where bookingid = "+txtMaDatPhong.getText();
                    Connection con = new DBUtils().createConn();
                    Statement stat = con.createStatement();
                    ResultSet rs = stat.executeQuery(strSQL);
                    BookingDTO booking = new BookingDTO();
                    if(rs.next()){
                        booking.setBookingID(Integer.parseInt(txtMaDatPhong.getText()));
                        booking.setSlot(rs.getInt("slot"));
                        booking.setRoomID(rs.getInt("roomid"));
                    }
                    BookingBLL bookingBll = new BookingBLL();
                    bookingBll.saveBooking(booking);
                    int result = paymentBll.insertPayment(payment);
                    if(result!=0){
                        JOptionPane.showMessageDialog(null, "Thanh toán thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        this.ckbDaThanhToan.setSelected(true);
                        this.ckbDaThanhToan.setEnabled(false);
                        this.btnXacNhan.setEnabled(false);
                    }
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    ArrayList<String>arrTenKH = new ArrayList<String>();
    ArrayList<String>arrBookingidDaThanhToan = new ArrayList<String>();
    private void cbbSoPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSoPhongActionPerformed
        // TODO add your handling code here:
        arrTenKH.removeAll(arrTenKH);
        try{
            String strSQL = "select * from payment";
            Connection con = new DBUtils().createConn();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                arrBookingidDaThanhToan.add(String.valueOf(rs.getInt("bookingid")));
            }
            
            strSQL = "select customername, booking.customerid, bookingid from customer, booking"
                    + " where roomid = "+cbbSoPhong.getSelectedItem()
                    + " and customer.customerid = booking.customerid";
            rs = stat.executeQuery(strSQL);
            while(rs.next()){
                if(!arrBookingidDaThanhToan.contains(rs.getInt("bookingid")))
                    arrTenKH.add(rs.getString("customername"));
            }
            cbbTenKH.setModel(new DefaultComboBoxModel<String>(arrTenKH.toArray(new String[0])));
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbSoPhongActionPerformed

    private void ckbDaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbDaThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbDaThanhToanActionPerformed

    private void cbbTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenKHActionPerformed
        // TODO add your handling code here:
        this.ckbDaThanhToan.setSelected(false);
        this.ckbDaThanhToan.setEnabled(true);
        this.btnXacNhan.setEnabled(true);
        //đổ ngày và thông tin chi tiết dvsd vào bảng
        try{
            String strSQL = "select booking.customerid, enddate, bookingid from customer, booking"
                    + " where customer.customerid = booking.customerid"
                    + " and customername = '"+cbbTenKH.getSelectedItem()+"'";
            Connection con = new DBUtils().createConn();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
                String strSQL1 = "select * from payment where bookingid ="+rs.getInt("bookingid");
                Connection con1 = new DBUtils().createConn();
                Statement stat1 = con1.createStatement();
                ResultSet rs1 = stat1.executeQuery(strSQL1);
                if(rs1.next()){
                    if(rs1.getBoolean("paymentstatus")){
                        this.ckbDaThanhToan.setSelected(true);
                        this.ckbDaThanhToan.setEnabled(false);
                        this.btnXacNhan.setEnabled(false);
                    }
                }
                java.util.Date utilDate = new java.sql.Date(rs.getDate("enddate").getTime());
                dateNgayRoiDi.setDate(utilDate);
                txtMaDatPhong.setText(rs.getString("bookingid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        loadChiTietTableDVSD();
        txtTongTienTT.setText(String.valueOf(dinhDangTienTe(tongTien)));
    }//GEN-LAST:event_cbbTenKHActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
        if(txtMaDatPhong.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thanh toán trước khi in hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else{
            Document document = new Document(PageSize.A4);
            String filename = "HoaDonMaDatPhong"+txtMaDatPhong.getText();
            try{
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\UIT\\CongNghePhanMem\\Final\\Hotel_Management\\src\\HoaDon\\"+filename+".pdf"));
                document.open();
                File filefont = new File("E:\\UIT\\CongNghePhanMem\\Final\\Hotel_Management\\src\\fonts\\vuArial.ttf");
                BaseFont bf = BaseFont.createFont(filefont.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(bf, 13);
                Paragraph prgTieuDe = new Paragraph("HÓA ĐƠN DỊCH VỤ", font);
                prgTieuDe.setAlignment(Element.ALIGN_CENTER);
                prgTieuDe.setSpacingBefore(10);
                prgTieuDe.setSpacingAfter(10);
                document.add(prgTieuDe);
                
                Connection con = new DBUtils().createConn();
                String strSQL = "select * from deposit where bookingid = "+txtMaDatPhong.getText();
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery(strSQL);
                
                List listTTHD = new List(List.UNORDERED);
                listTTHD.add(new ListItem("Số hóa đơn thanh toán: "+txtMaDatPhong.getText(), font));
                listTTHD.add(new ListItem("Tên khách: "+cbbTenKH.getSelectedItem(), font));
                listTTHD.add(new ListItem("Số phòng: "+cbbSoPhong.getSelectedItem(), font));
                java.util.Date utilDate = new java.util.Date(dateNgayRoiDi.getDate().getTime());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                String[] arrNgay = sqlDate.toString().split("-");
                String nam = arrNgay[0];
                String thang = arrNgay[1];
                String ngay = arrNgay[2];
                listTTHD.add(new ListItem("Ngày: "+ngay+"/"+thang+"/"+nam, font));
                if(rs.next())
                    listTTHD.add(new ListItem("Số tiền đã tạm ứng: "+rs.getString("depositamount"), font));
                else
                    listTTHD.add(new ListItem("Số tiền đã tạm ứng: 0", font));
                listTTHD.add(new ListItem("Số tiền phải trả: "+txtTongTienTT.getText(), font));
                document.add(listTTHD);
                
                
                
                //Them noi dung vao table
                Paragraph prgDichVu = new Paragraph("Chi tiết dịch vụ sử dụng:", font);
                prgDichVu.setSpacingBefore(10);
                prgDichVu.setSpacingAfter(10);
                document.add(prgDichVu);
                PdfPTable tableDV = new PdfPTable(5);
                tableDV.setWidthPercentage(80);
                tableDV.setSpacingBefore(10);
                tableDV.setSpacingAfter(10);
                float[] tableDV_columnWidths = {130, 100, 120, 150, 130};
                tableDV.setWidths(tableDV_columnWidths);
                
                PdfPCell cellTenDV = new PdfPCell(new Paragraph("Tên dịch vụ", font));
                cellTenDV.setBorderColor(BaseColor.BLACK);
                cellTenDV.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellTenDV.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellTenDV.setMinimumHeight(30);
                tableDV.addCell(cellTenDV);
                
                PdfPCell cellSL = new PdfPCell(new Paragraph("Số lượng", font));
                cellSL.setBorderColor(BaseColor.BLACK);
                cellSL.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellSL.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellSL.setMinimumHeight(30);
                tableDV.addCell(cellSL);
                
                PdfPCell cellNgay = new PdfPCell(new Paragraph("Ngày", font));
                cellNgay.setBorderColor(BaseColor.BLACK);
                cellNgay.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellNgay.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellNgay.setMinimumHeight(30);
                tableDV.addCell(cellNgay);
                
                PdfPCell cellDG = new PdfPCell(new Paragraph("Đơn giá", font));
                cellDG.setBorderColor(BaseColor.BLACK);
                cellDG.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellDG.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellDG.setMinimumHeight(30);
                tableDV.addCell(cellDG);
                
                PdfPCell cellTT = new PdfPCell(new Paragraph("Thành tiền", font));
                cellTT.setBorderColor(BaseColor.BLACK);
                cellTT.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellTT.setMinimumHeight(30);
                tableDV.addCell(cellTT);
                
                
                con = new DBUtils().createConn();
                strSQL = "select servicebill.serviceid, quantity, servicedate, servicename, unit, unitprice"
                        + " from servicebill, service where bookingid = "+txtMaDatPhong.getText()
                        + " and service.serviceid = servicebill.serviceid";
                stat = con.createStatement();
                rs = stat.executeQuery(strSQL);
                while(rs.next()){
                    cellTenDV = new PdfPCell(new Paragraph(rs.getString("servicename"), font));
                    cellTenDV.setPaddingLeft(10);
                    cellTenDV.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTenDV.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellTenDV.setMinimumHeight(20);
                    tableDV.addCell(cellTenDV);
                    
                    int soLuong = rs.getInt("quantity");
                    cellSL = new PdfPCell(new Paragraph(String.valueOf(soLuong), font));
                    cellSL.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellSL.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellSL);
                    
                    cellNgay = new PdfPCell(new Paragraph(rs.getDate("servicedate").toString(), font));
                    cellNgay.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellNgay.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellNgay);
                    
                    double donGia = rs.getDouble("unitprice");
                    cellDG = new PdfPCell(new Paragraph(dinhDangTienTe(donGia), font));
                    cellDG.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellDG.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellDG);
                    
                    double thanhTien = soLuong*donGia;
                    cellTT = new PdfPCell(new Paragraph(String.valueOf(dinhDangTienTe(thanhTien)), font));
                    cellTT.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellTT);
                }
                
                strSQL = "select slot, startdate, enddate, priceperslot from booking, roominformation"
                        + " where bookingid = "+txtMaDatPhong.getText()
                        + " and roominformation.roomid = "+cbbSoPhong.getSelectedItem();
                rs = stat.executeQuery(strSQL);
                while(rs.next()){
                    cellTenDV = new PdfPCell(new Paragraph("Tiền phòng", font));
                    cellTenDV.setPaddingLeft(10);
                    cellTenDV.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTenDV.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellTenDV.setMinimumHeight(20);
                    tableDV.addCell(cellTenDV);

                    TimeUnit time = TimeUnit.DAYS;
                    long soNgay = time.convert(rs.getDate("enddate").getTime()-rs.getDate("startdate").getTime(), TimeUnit.MILLISECONDS);
                    long soNguoi = rs.getInt("slot");
                    cellSL = new PdfPCell(new Paragraph(String.valueOf(soNguoi)+"người, "+String.valueOf(soNgay)+"ngày", font));
                    cellSL.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellSL.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellSL);

                    cellNgay = new PdfPCell(new Paragraph(rs.getDate("startdate").toString(), font));
                    cellNgay.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellNgay.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellNgay);

                    double donGia = rs.getDouble("priceperslot");
                    cellDG = new PdfPCell(new Paragraph(dinhDangTienTe(donGia)+"/người/ngày", font));
                    cellDG.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellDG.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellDG);
                        
                    double thanhTien = soNgay*soNguoi*donGia;
                    cellTT = new PdfPCell(new Paragraph(dinhDangTienTe(thanhTien), font));
                    cellTT.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableDV.addCell(cellTT);
                }
                document.add(tableDV);
                document.close();
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
            try{
                File file = new File("E:\\UIT\\CongNghePhanMem\\Final\\Hotel_Management\\src\\HoaDon\\"+filename+".pdf");
                if(!Desktop.isDesktopSupported()){
                    System.out.println("Not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if(file.exists()){
                    desktop.open(file);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

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
            java.util.logging.Logger.getLogger(TraPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TraPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TraPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TraPhongUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TraPhongUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbbPhuongThucTT;
    private javax.swing.JComboBox<String> cbbSoPhong;
    private javax.swing.JComboBox<String> cbbTenKH;
    private javax.swing.JCheckBox ckbDaThanhToan;
    private com.toedter.calendar.JDateChooser dateNgayRoiDi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDVSD;
    private javax.swing.JTextField txtMaDatPhong;
    private javax.swing.JTextField txtTongTienTT;
    // End of variables declaration//GEN-END:variables
}
