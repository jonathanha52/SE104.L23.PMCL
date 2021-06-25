/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.PaymentBLL;
import DTO.PaymentDTO;
import Utils.DBUtils;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    
    double tongTien = 0;
    public void loadChiTietTableDVSD(){
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
                row[3] = Double.toString(dongia);
                row[4] = Double.toString(soluong*dongia);
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
                long songay = time.convert(rs.getDate("startdate").getTime()-rs.getDate("enddate").getTime(), TimeUnit.MILLISECONDS);
                long songuoi = rs.getInt("slot");
                row[1] = songuoi+" người, "+songay+" ngày";
                row[2] = rs.getDate("startdate").toString();
                double dongia = rs.getDouble("priceperslot");
                row[3] = Double.toString(dongia)+"/người/ngày";
                row[4] = Double.toString(songuoi*songay*dongia);
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
                row[4] = "-"+Double.toString(rs.getDouble("depositamount"));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbSoPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTenKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbPhuongThucTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateNgayRoiDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTongTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(ckbDaThanhToan)
                        .addGap(102, 102, 102)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateNgayRoiDi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTongTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(cbbPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(txtMaDatPhong)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbDaThanhToan))
                .addGap(21, 21, 21))
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
                Connection con = new DBUtils().createConn();
                PaymentDTO payment = new PaymentDTO();
                payment.setPaymentID(layPaymentID());
                payment.setBookingID(Integer.parseInt(txtMaDatPhong.getText()));
                payment.setPaymentTypeName(cbbPhuongThucTT.getSelectedItem().toString());
                java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                payment.setPaymentDate(sqlDate);
                payment.setPaymentStatus(true);
                payment.setAmount(Double.parseDouble(txtTongTienTT.getText()));
                PaymentBLL paymentBll = new PaymentBLL();
                int result = paymentBll.insertPayment(payment);
                if(result!=0){
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    this.ckbDaThanhToan.setSelected(true);
                    this.ckbDaThanhToan.setEnabled(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    ArrayList<String>arrTenKH = new ArrayList<String>();
    private void cbbSoPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSoPhongActionPerformed
        // TODO add your handling code here:
        try{
            String strSQL = "select customername, booking.customerid from customer, booking"
                    + " where roomid = "+cbbSoPhong.getSelectedItem()
                    + " and customer.customerid = booking.customerid";
            Connection con = new DBUtils().createConn();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            while(rs.next()){
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
        //đổ ngày và thông tin chi tiết dvsd vào bảng
        try{
            String strSQL = "select booking.customerid, enddate, bookingid from customer, booking"
                    + " where customer.customerid = booking.customerid"
                    + " and customername = '"+cbbTenKH.getSelectedItem()+"'";
            Connection con = new DBUtils().createConn();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(strSQL);
            if(rs.next()){
                java.util.Date utilDate = new java.sql.Date(rs.getDate("enddate").getTime());
                dateNgayRoiDi.setDate(utilDate);
                txtMaDatPhong.setText(rs.getString("bookingid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        loadChiTietTableDVSD();
        txtTongTienTT.setText(Double.toString(tongTien));
    }//GEN-LAST:event_cbbTenKHActionPerformed

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
