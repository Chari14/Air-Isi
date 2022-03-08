/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import java.awt.HeadlessException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.Koneksi;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author harip
 */
public class Form_Karyawan extends javax.swing.JFrame {
 String s;   
   private Koneksi db = new Koneksi();
   Statement st;
   Connection con;
   ResultSet rs;
   String filepath;
   private DefaultTableModel model;
   
    /**
     * Creates new form kar
     */
   
    public Form_Karyawan() {
        initComponents();
        String[] header ={"ID Karyawan","Nama","No HP","Bagian","Alamat"};
        model = new DefaultTableModel(header,0);
        table_karyawan.setModel(model);
        String[] head ={"ID Kurir","Nama","No HP","Bagian"};
        DefaultTableModel model2 = new DefaultTableModel(head,0);
        table_kurir.setModel(model2);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(false);
        setResizable (false);
        data_karyawan();
        data_kurir();
        auto_karyawan();
        auto_kurir();
        Tanggal_sekarang();
        tampilkan_data();
        tampilkan_kurir();

//        tampilData_karyawan();
    }
   
    
    public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    
    
    public void kosongkan_karyawan(){
          
          nama_karyawan.setText(null);
          no_hp.setText(null);
          jComboBox.setSelectedItem("  -Bagian-");
          alamat_karyawan.setText(null);
          
      }
    
    
    public void kosongkan_kurir(){
          
          id_karyawann.setText(null);
          nama_kurir.setText(null);
          txnohp.setText(null);
          txbagian.setSelectedItem("  -Bagian-");
          
          
      }
    
    private void data_karyawan(){
    Statement st;
    ResultSet rs;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String SelectQuery = "SELECT * FROM data_karyawan WHERE id_karyawan ";
        
            st = con.createStatement();
            rs = st.executeQuery(SelectQuery);
            while(rs.next()){
                DefaultTableModel model = (DefaultTableModel) table_karyawan.getModel();
                Object[] row = new Object[5];
                row[0] = rs.getString("id_karyawan");
                row[1] = rs.getString("nama_karyawan");
                row[2] = rs.getString("no_hp");
                row[3] = rs.getString("bagian");
                row[4] = rs.getString("alamat");
                model.addRow(row);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void data_kurir(){
    Statement st;
    ResultSet rs;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String SelectQuery = "SELECT * FROM data_karyawan_kurir WHERE id_karyawan ";
        
            st = con.createStatement();
            rs = st.executeQuery(SelectQuery);
            while(rs.next()){
                DefaultTableModel model2 = (DefaultTableModel) table_kurir.getModel();
                Object[] row = new Object[5];
                row[0] = rs.getString("id_karyawan");
                row[1] = rs.getString("id_kurir");
                row[2] = rs.getString("nama_kurir");
                row[3] = rs.getString("no_hp");
                row[4] = rs.getString("bagian");
                model2.addRow(row);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void tampilkan_data(){
        Koneksi db= new Koneksi();
        int jumlahrow =  table_karyawan.getRowCount();
        for(int n=0;n<jumlahrow;n++){
            model.removeRow(0);
        }    
        try{       
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String SelectQuery = "SELECT * FROM data_karyawan order by id_karyawan ";
            st = con.createStatement();
            rs = st.executeQuery(SelectQuery);
            int no =1;
            while (rs.next () ){
                String []row= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
                model.addRow(row);
                no++;
            }
                table_karyawan.setModel(model);
                              
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private void tampilkan_kurir(){
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("ID Karyawan");
        model2.addColumn("ID Kurir");
        model2.addColumn("Nama");
        model2.addColumn("No HP");
        model2.addColumn("Bagian");
        table_kurir.setModel(model2);
        //untuk mengahapus baris setelah input
        int row = table_kurir.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model2.removeRow(0);
        }
        String sql = "Select * from data_karyawan_kurir ";
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu  = rslt.getString("id_karyawan");
                 String dua  = rslt.getString("id_kurir");
                 String tiga   = rslt.getString("nama_kurir");
                 String empat = rslt.getString("no_hp");
                 String lima = rslt.getString("bagian");

                 
                  
                    
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model2.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_kurir.setModel(model2);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
      
    
    private void auto_karyawan(){
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_karyawan) as id_karyawan FROM data_karyawan";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.id_karyawan.setText("KRY-0"+(a+1));  
       }else{  
         this.id_karyawan.setText("KRY-0"+(a+1));  
       }  
            
        }

     while(rs.next()){  
    
   }  
   rs.close(); }  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
   }
    
    }
    
    private void auto_kurir(){
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_karyawan) as id_karyawan FROM data_karyawan_kurir";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.id_kurir.setText("KKR-0"+(a+1));  
       }else{  
         this.id_kurir.setText("KKR-0"+(a+1));  
       }  
            
        }

     while(rs.next()){  
    
   }  
   rs.close(); }  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
   }
    
    }
    
    
       private boolean perbarui()throws IOException, SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_karyawan SET id_karyawan = ?,nama_karyawan= ?,no_hp=?,bagian=?,alamat=? WHERE id_karyawan = '"+id_karyawan.getText()+"'");
            ps.setString(1, id_karyawan.getText());
            ps.setString(2, nama_karyawan.getText());
            ps.setString(3, no_hp.getText());
            ps.setString(4, (String) jComboBox.getSelectedItem());
            ps.setString(5, alamat_karyawan.getText());
            ps.executeUpdate();
            ps.close();
        return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
            e.printStackTrace();
        }
        return false;
    }

       private boolean perbarui_kurir()throws IOException, SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_karyawan_kurir SET id_karyawan= ?,id_kurir= ?,nama_kurir= ?,no_hp= ?,bagian=? WHERE id_karyawan = '"+id_karyawann.getText()+"'");
            ps.setString(1, id_karyawann.getText());
            ps.setString(2, id_kurir.getText());
            ps.setString(3, nama_kurir.getText());
            ps.setString(4, txnohp.getText());
            ps.setString(5, (String) txbagian.getSelectedItem());
            ps.executeUpdate();
            ps.close();
        return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
            e.printStackTrace();
        }
        return false;
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jTextField8 = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnkosongkann = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nama_karyawan = new javax.swing.JTextField();
        no_hp = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txbagian = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        alamat_karyawan = new javax.swing.JTextArea();
        tanggal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_karyawan = new javax.swing.JTable();
        id_karyawan = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txnohp = new javax.swing.JTextField();
        id_kurir = new javax.swing.JTextField();
        nama_kurir = new javax.swing.JTextField();
        jComboBox = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_kurir = new javax.swing.JTable();
        id_karyawan1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        id_karyawann = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnkosongkan1 = new javax.swing.JButton();
        btsaran = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        btmenu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "ENTRY KARYAWAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("PASSWORD");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel16.setText("USERNAME");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel17.setText("NAMA");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 208, -1, -1));

        jLabel18.setText("NO HP");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 268, -1, -1));

        jLabel19.setText("ID Karyawan");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 70, -1));

        jLabel20.setText("ALAMAT");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 335, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 360, 204, -1));
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 117, 29));
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 155, 27));
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 228, 166, 29));
        jPanel2.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 147, 29));
        jPanel2.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 288, 166, 29));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 720));
        setPreferredSize(new java.awt.Dimension(1300, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setFocusCycleRoot(true);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 110, 30));

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 110, 30));

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 120, 30));

        btnkosongkann.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        btnkosongkann.setText("Bersihkan");
        btnkosongkann.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkosongkannActionPerformed(evt);
            }
        });
        getContentPane().add(btnkosongkann, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 180, 120, 30));

        jLabel3.setFont(new java.awt.Font("Forte", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Data Kurir");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 70, 110, 50));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel13.setText("ID Kurir");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 180, 80, -1));
        getContentPane().add(nama_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 180, 30));
        getContentPane().add(no_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 166, 30));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel23.setText("Nama");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 220, 60, -1));

        jLabel24.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel24.setText("NO HP");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 70, -1));

        txbagian.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        txbagian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  -Bagian-", "     Kurir" }));
        getContentPane().add(txbagian, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, 120, 30));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setText("Bagian");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 300, 60, -1));

        alamat_karyawan.setColumns(20);
        alamat_karyawan.setRows(5);
        jScrollPane4.setViewportView(alamat_karyawan);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 240, 120));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText(" Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        table_karyawan.setBackground(new java.awt.Color(153, 153, 153));
        table_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        table_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_karyawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_karyawan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 410, 200));

        id_karyawan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karyawan.setEnabled(false);
        id_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_karyawanActionPerformed(evt);
            }
        });
        getContentPane().add(id_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 90, 30));

        jButton10.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/arrow.png"))); // NOI18N
        jButton10.setText("LogOut");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 630, 130, 40));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 110, 40));

        jLabel27.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel27.setText("Alamat");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 60, -1));

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel4.setText("Bagian");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 60, -1));

        jLabel25.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel25.setText("NO HP");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 60, -1));

        jLabel28.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel28.setText("Nama Anda");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 90, -1));

        jLabel14.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel14.setText("ID Karyawan");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 100, -1));
        getContentPane().add(txnohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 150, 30));
        getContentPane().add(id_kurir, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 180, 100, 30));
        getContentPane().add(nama_kurir, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 220, 190, 30));

        jComboBox.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  -Bagian-", "    Admin", "  ISI Galon", "     Kurir" }));
        getContentPane().add(jComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 120, 30));

        table_kurir.setBackground(new java.awt.Color(153, 153, 153));
        table_kurir.setModel(new javax.swing.table.DefaultTableModel(
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
        table_kurir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_kurirMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_kurir);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, -1, 200));

        id_karyawan1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karyawan1.setEnabled(false);
        id_karyawan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_karyawan1ActionPerformed(evt);
            }
        });
        getContentPane().add(id_karyawan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 90, 30));

        jLabel21.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel21.setText("ID Karyawan");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 100, -1));
        getContentPane().add(id_karyawann, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 140, 70, 30));

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 140, -1, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        jButton4.setText("Simpan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 120, 30));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        jButton5.setText("Edit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 360, 100, 30));

        hapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 360, -1, 30));

        jLabel22.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Contoh ID : KRY-01");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Forte", 1, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Karyawan\n\n");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 210, 40));

        jLabel6.setFont(new java.awt.Font("Forte", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Data Karyawan & Bagian");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 220, 40));

        btnkosongkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        btnkosongkan1.setText("Bersihkan");
        btnkosongkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkosongkan1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnkosongkan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 120, 30));

        btsaran.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btsaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        btsaran.setText("Saran");
        btsaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaranActionPerformed(evt);
            }
        });
        getContentPane().add(btsaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 110, 40));

        jButton6.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton6.setText("Ebout");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 110, 40));

        jButton9.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton9.setText(" Data Laporan");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 180, 40));

        jButton7.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/cash-flow.png"))); // NOI18N
        jButton7.setText("STOK & KAS");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.setDoubleBuffered(true);
        jButton7.setFocusCycleRoot(true);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 180, 40));

        jButton8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/money-transfer.png"))); // NOI18N
        jButton8.setText("TRANSAKSI");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 160, 40));

        jButton13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/data.png"))); // NOI18N
        jButton13.setText("MASTER");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 160, 40));

        btmenu.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/home.png"))); // NOI18N
        btmenu.setText("MENU");
        btmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmenuActionPerformed(evt);
            }
        });
        getContentPane().add(btmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 160, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Umum Master.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnkosongkannActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkosongkannActionPerformed
        // TODO add your handling code here:
        kosongkan_kurir();
        auto_kurir();
    }//GEN-LAST:event_btnkosongkannActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            perbarui();
        } catch (IOException ex) {
            Logger.getLogger(Form_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        kosongkan_karyawan();
        tampilkan_data();
        auto_karyawan();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_karyawan WHERE id_karyawan='"+id_karyawan.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
            kosongkan_karyawan();
            tampilkan_data();
            auto_karyawan();

       }

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("insert into data_karyawan(id_karyawan,nama_karyawan,no_hp,bagian,alamat) values(?,?,?,?,?)");
            ps.setString(1, id_karyawan.getText());
            ps.setString(2, nama_karyawan.getText());
            ps.setString(3, no_hp.getText());
            ps.setString(4, (String) jComboBox.getSelectedItem());
            ps.setString(5, alamat_karyawan.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }
         tampilkan_data();
         kosongkan_karyawan();
         auto_karyawan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void table_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_karyawanMouseClicked
        // TODO add your handling code here:
       
           
            DefaultTableModel model = (DefaultTableModel) table_karyawan.getModel();
            int baris = table_karyawan.rowAtPoint(evt.getPoint());
           
            String id=table_karyawan.getValueAt(baris,0).toString();
            id_karyawan.setText(id);

            String nama=table_karyawan.getValueAt(baris,1).toString();
            nama_karyawan.setText(nama);

            String no =table_karyawan.getValueAt(baris,2).toString();
            no_hp.setText(no);

            String bagian =table_karyawan.getValueAt(baris,3).toString();
            jComboBox.setSelectedItem(bagian);
            
            String alamat =table_karyawan.getValueAt(baris,4).toString();
            alamat_karyawan.setText(alamat);
        
    
    }//GEN-LAST:event_table_karyawanMouseClicked

    private void id_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_karyawanActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        //          String ObjButtons[] = {"Yes","No"};
        // int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        // if(pilihan == 0){
            new Login.Form_Login().setVisible(true);
            dispose();
            //        System.exit(0);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void id_karyawan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_karyawan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_karyawan1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try{
                       
                       rs= st.executeQuery("SELECT * FROM data_karyawan WHERE id_karyawan LIKE '"+ id_karyawann.getText() +"'");
                       
                       
                       if (rs.next () )
                       {
                            id_karyawann.setText(rs.getString(1));
                            nama_kurir.setText(rs.getString(2));
                            txnohp.setText(rs.getString(3));
                            txbagian.setSelectedItem(rs.getString(4));
                           
                       }else{
                           JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
                       }
                       
                   }catch (HeadlessException | SQLException ex){
                       System.out.println("error : "+ex);
                   }
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("insert into data_karyawan_kurir(id_karyawan,id_kurir,nama_kurir,no_hp,bagian) values(?,?,?,?,?)");
            ps.setString(1, id_karyawann.getText());
            ps.setString(2, id_kurir.getText());
            ps.setString(3, nama_kurir.getText());
            ps.setString(4, txnohp.getText());
            ps.setString(5, (String) txbagian.getSelectedItem());
//            ps.setString(5, alamat_karyawan.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }
         tampilkan_kurir();
         kosongkan_kurir();
         auto_kurir();
//         auto_karyawan();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_karyawan_kurir WHERE id_kurir='"+id_kurir.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
            kosongkan_kurir();
            tampilkan_kurir();
            auto_kurir();

       }

    }//GEN-LAST:event_hapusActionPerformed

    private void btnkosongkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkosongkan1ActionPerformed
        // TODO add your handling code here:
        kosongkan_karyawan();
    }//GEN-LAST:event_btnkosongkan1ActionPerformed

    private void table_kurirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_kurirMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) table_kurir.getModel();
            int baris = table_kurir.rowAtPoint(evt.getPoint());
           
            String id_k=table_kurir.getValueAt(baris,0).toString();
            id_karyawann.setText(id_k);
            
            String id=table_kurir.getValueAt(baris,1).toString();
            id_kurir.setText(id);

            String nama=table_kurir.getValueAt(baris,2).toString();
            nama_kurir.setText(nama);

            String no =table_kurir.getValueAt(baris,3).toString();
            txnohp.setText(no);
            
            String bagian =table_kurir.getValueAt(baris,4).toString();
            txbagian.setSelectedItem(bagian);
    }//GEN-LAST:event_table_kurirMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            perbarui_kurir();
        } catch (IOException ex) {
            Logger.getLogger(Form_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Karyawan.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        tampilkan_kurir();
        kosongkan_kurir();
        auto_kurir();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btsaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaranActionPerformed
        // TODO add your handling code here:
        Form_Ebout_FeedBack.Form_FeedBack frm = new Form_Ebout_FeedBack.Form_FeedBack(this, rootPaneCheckingEnabled);
        frm.setTitle("Saran");
        frm.setVisible(true);
    }//GEN-LAST:event_btsaranActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new Form_Ebout_FeedBack.Form_Ebout().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        new Output.Form_Tampilan_Laporan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Stok_Kas().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Transaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        new Master.Form_Master().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmenuActionPerformed
        // TODO add your handling code here:
        new Master.Form_Menu_Utama().setVisible(true);
        dispose();
    }//GEN-LAST:event_btmenuActionPerformed
    
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
            java.util.logging.Logger.getLogger(Form_Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_karyawan;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnkosongkan1;
    private javax.swing.JButton btnkosongkann;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField id_karyawan;
    private javax.swing.JTextField id_karyawan1;
    private javax.swing.JTextField id_karyawann;
    private javax.swing.JTextField id_kurir;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField nama_karyawan;
    private javax.swing.JTextField nama_kurir;
    private javax.swing.JTextField no_hp;
    private javax.swing.JTable table_karyawan;
    private javax.swing.JTable table_kurir;
    private javax.swing.JLabel tanggal;
    private javax.swing.JComboBox<String> txbagian;
    private javax.swing.JTextField txnohp;
    // End of variables declaration//GEN-END:variables
}
