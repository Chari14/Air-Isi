/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
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
import java.awt.event.KeyEvent;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author harip
 */
public class Form_Pendistribusian extends javax.swing.JFrame {
   Statement st;
   Connection con;
   ResultSet rs;
   public DefaultTableModel model;
    /**
     * Creates new form Penyalurann
     */
    public Form_Pendistribusian() {
        initComponents();
//        String[] header ={"ID Pemerima","Penanggung Jawab","Nama Tempat","No HP","Pilih","Alamat Tujuan"};
//        model = new DefaultTableModel(header,0);
//        String[] head ={"Kode Akta","Jenis Akta"};
        DefaultTableModel model = (DefaultTableModel) tabel_distibusi.getModel();
        tabel_distibusi.setModel(model);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable (false);
        tampilData_lembagapenerima();
        tampildata_distribuksi();
        auto_pendistribusian();
        data_kurir();
        Tanggal_sekarang();
        
    }
    
     public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    
    private void data_kurir(){
        try {

            con = Koneksi.configDB();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql = "SELECT * FROM data_karyawan_kurir order by id_kurir";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("id_karyawan");
                row[1] = rs.getString("id_kurir");
                row[2] = rs.getString("nama_kurir");
                row[3] = rs.getString("no_hp");
                row[4] = rs.getString("bagian");
//                row[5] = rs.getString("alamat");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    
        
          }

    
    public void kosongkan_pendistribusian(){
          penanggung_jawab.setText(null);
          nama_tempat.setText(null);
          nohp_penerima.setText(null);
          jComboBox.setSelectedItem("-Pilih Lembaga-");
          alamat_penerima.setText(null);
          tanggall.setDate(null);
          id_kurir.setText(null);
          nama_kurir.setText(null);
          id_stok.setText(null);
          jumlah_galon.setText(null);
          txjml.setText(null);
          txpengeluaran.setText(null);
          
          
      }
    
     
     private void tampildata_distribuksi(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Distribusi");
        model.addColumn("Penanggung Jawab");
        model.addColumn("Nama Tempat");
        model.addColumn("No HP");
        model.addColumn("Pilih");
        model.addColumn("Alamat Tujuan");
        model.addColumn("Tanggal Penyaluran");
        model.addColumn("ID Kurir");
        model.addColumn("Nama Kurir");
        model.addColumn("ID Stok");
        model.addColumn("Stok Galon");
        model.addColumn("jumlah");
        model.addColumn("Biaya Pendistribusi");
        tabel_distibusi.setModel(model);
        //untuk mengahapus baris setelah input
        int row = tabel_distibusi.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        String sql = "Select * from data_pendistribusian where id_distribusi like '%" + txt_cari.getText() + "%'";
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu  = rslt.getString("id_distribusi");
                 String dua   = rslt.getString("penanggung_jawab");
                 String tiga  = rslt.getString("nama_tempat");
                 String empat = rslt.getString("no_hp");
                 String lima  = rslt.getString("pilih");
                 String enam  = rslt.getString("alamat");  
                 String tujuh  = rslt.getString("tanggal_penyaluran"); 
                 String lapan  = rslt.getString("id_kurir"); 
                 String sembilan  = rslt.getString("nama_kurir"); 
                 String sepuluh   = rslt.getString("id_stok");
                 String sebelas   = rslt.getString("stok_galon");
                 String duabelas  = rslt.getString("jml");
                 String tigabelas = rslt.getString("biaya_pendistribusi"); 
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima,enam,tujuh,lapan,sembilan,sepuluh,sebelas,duabelas,tigabelas};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tabel_distibusi.setModel(model);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
                
    }
     
    
     
      private void auto_pendistribusian(){
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_distribusi) as id_distribusi FROM data_pendistribusian";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.id_distribusi.setText("PDS-0"+(a+1));  
       }else{  
         this.id_distribusi.setText("PDS-0"+(a+1));  
       }  
            
        }

     while(rs.next()){  
    
   }  
   rs.close(); 
//   st.close();
       }  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
   }
    
    }
      
      
      public void simpan(){
        try{
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tanggall.getDate());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("insert into data_pendistribusian(id_distribusi,penanggung_jawab,nama_tempat,no_hp,pilih,alamat,tanggal_penyaluran,id_kurir,nama_kurir,id_stok,stok_galon,jml,biaya_pendistribusi) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, id_distribusi.getText());
            ps.setString(2, penanggung_jawab.getText());
            ps.setString(3, nama_tempat.getText());
            ps.setString(4, nohp_penerima.getText());
            ps.setString(5, (String) jComboBox.getSelectedItem());
            ps.setString(6, alamat_penerima.getText());
            ps.setString(7, tanggal);
            ps.setString(8, id_kurir.getText());
            ps.setString(9, nama_kurir.getText());
            ps.setString(10, id_stok.getText());
            ps.setString(11, jumlah_galon.getText());
            ps.setString(12, txjml.getText());
            ps.setString(13, txpengeluaran.getText());
            ps.executeUpdate();
            ps.close();
         JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }  
//        tampilata_distribuksi();
    }
      
      private void perbarui(){
        try {
            
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tanggall.getDate());
            con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_pendistribusian SET id_distribusi =?,penanggung_jawab=?,nama_tempat=?,no_hp=?,pilih=?,alamat=?,tanggal_penyaluran=?,id_kurir=?,nama_kurir=?,id_stok=?,stok_galon=?,jml=?,biaya_pendistribusi=? WHERE id_distribusi = '"+id_distribusi.getText()+"'");
            ps.setString(1, id_distribusi.getText());
            ps.setString(2, penanggung_jawab.getText());
            ps.setString(3, nama_tempat.getText());
            ps.setString(4, nohp_penerima.getText());
            ps.setString(5, (String) jComboBox.getSelectedItem());
            ps.setString(6, alamat_penerima.getText());
            ps.setString(7, tanggal);
            ps.setString(8, id_kurir.getText());
            ps.setString(9, nama_kurir.getText());
            ps.setString(10, id_stok.getText());
            ps.setString(11, jumlah_galon.getText());
            ps.setString(12, txjml.getText());
            ps.setString(13, txpengeluaran.getText());    
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
            e.printStackTrace();
        }
        
    }
      
           private void tampilData_lembagapenerima(){
          try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root", "");
            String SelectQuery = "SELECT * FROM data_lembagapenerima WHERE id_penerima";
            st = con.createStatement();
            rs = st.executeQuery(SelectQuery);
            while(rs.next()){
                Object[] row = new Object[6];
                row[0] = rs.getString("id_penerima");
                row[1] = rs.getString("penanggung_jawab");
                row[2] = rs.getString("nama_tempat");
                row[3] = rs.getString("no_hp");
                row[4] = rs.getString("pilih");
                row[5] = rs.getString("alamat_tujuan");
                
 
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
                
    }
      
           
//            private void tampilData_kas(){
//          try{
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root", "");
//            String SelectQuery = "SELECT * FROM data_kas WHERE id_kas";
//            st = con.createStatement();
//            rs = st.executeQuery(SelectQuery);
//            while(rs.next()){
//                Object[] row = new Object[7];
//                row[0] = rs.getString("id_kas");
//                row[1] = rs.getString("id_donasi");
//                row[2] = rs.getString("jumlah_kas_lama");
//                row[3] = rs.getString("tgl_update");
//                row[4] = rs.getString("kas_masuk");
//                row[5] = rs.getString("kas_keluar");
//                row[6] = rs.getString("kondisi");
//                row[7] = rs.getString("update_jumlah");
//            }
//        }catch(SQLException ex){
//            JOptionPane.showMessageDialog(null, ex);
//        }
//          
//            }
//        private void perbarui1(){
//          try{
//          
//           String sql = "UPDATE data_kas SET update_jumlah='"+txt_total.getText()+ "'WHERE id_kas = '"+id_kas.getText()+"'";
//             st.executeUpdate(sql);
//            JOptionPane.showMessageDialog(null,"Stok data berhasil","kaset atm",JOptionPane.INFORMATION_MESSAGE);
//        } 
//        catch(Exception e){
//        }
//       }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        id_distribusi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        penanggung_jawab = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nohp_penerima = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        create_donasi = new javax.swing.JButton();
        delete_donasi = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        nama_tempat = new javax.swing.JTextField();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        tanggall = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        alamat_penerima = new javax.swing.JTextArea();
        txcari = new javax.swing.JButton();
        jumlah_galon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txpengeluaran = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        id_stok = new javax.swing.JTextField();
        txjml = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        id_kurir = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        id_lembaga = new javax.swing.JTextField();
        kosongkan1 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txharga = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        btmasuk = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        bt_riwayat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_distibusi = new javax.swing.JTable();
        nama_kurir = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        txt_search = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btsaran = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btmenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1300, 710));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 60, -1));
        getContentPane().add(id_distribusi, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 80, 30));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel13.setText("Nama Penang Jawab");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 150, -1));
        getContentPane().add(penanggung_jawab, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 190, 30));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel23.setText("Pilih");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 50, -1));
        getContentPane().add(nohp_penerima, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 130, 30));

        jLabel27.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel27.setText("Tanggal Penyaluran");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 140, -1));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText("Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, 30));

        create_donasi.setBackground(new java.awt.Color(255, 255, 255));
        create_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        create_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        create_donasi.setText("Simpan");
        create_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(create_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 110, 40));

        delete_donasi.setBackground(new java.awt.Color(255, 255, 255));
        delete_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        delete_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        delete_donasi.setText("Hapus");
        delete_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(delete_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 500, 110, 40));

        jLabel25.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel25.setText("No HP");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 50, -1));

        jLabel26.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel26.setText("Alamat ");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 60, -1));

        jLabel28.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel28.setText("Nama Tempat");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 100, -1));
        getContentPane().add(nama_tempat, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 160, 30));

        jComboBox.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Lembaga-", "      Masjid", "   Pesantren", "     Yayasan", "     Individu" }));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 140, -1));

        jLabel29.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel29.setText("ID Lembaga");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 90, -1));

        tanggall.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(tanggall, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 160, 30));

        alamat_penerima.setColumns(20);
        alamat_penerima.setLineWrap(true);
        alamat_penerima.setRows(5);
        alamat_penerima.setWrapStyleWord(true);
        alamat_penerima.setFocusCycleRoot(true);
        alamat_penerima.setFocusTraversalPolicyProvider(true);
        jScrollPane5.setViewportView(alamat_penerima);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 200, 100));

        txcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/search (1).png"))); // NOI18N
        txcari.setText("Cari");
        txcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txcariActionPerformed(evt);
            }
        });
        getContentPane().add(txcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 260, 80, 30));
        getContentPane().add(jumlah_galon, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, 110, 30));

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel5.setText("ID Stok");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 60, 20));

        txpengeluaran.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        txpengeluaran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpengeluaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpengeluaranKeyPressed(evt);
            }
        });
        getContentPane().add(txpengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 470, 150, 60));

        jLabel6.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel6.setText("Jumlah ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 60, 20));
        getContentPane().add(id_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 70, 30));
        getContentPane().add(txjml, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 380, 100, 30));

        jLabel7.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel7.setText("Harga Galon");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 90, 20));
        getContentPane().add(id_kurir, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 80, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/search (1).png"))); // NOI18N
        jButton7.setText("Cari");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 120, 80, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel9.setText("ID Kurir");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 120, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel10.setText("Nama Kurir");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, -1, -1));
        getContentPane().add(id_lembaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 120, 30));

        kosongkan1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kosongkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        kosongkan1.setText("Bersihkan");
        kosongkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kosongkan1ActionPerformed(evt);
            }
        });
        getContentPane().add(kosongkan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 130, 30));

        jLabel30.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel30.setText("ID Distribusi");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 100, -1));

        jLabel14.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Contoh ID : STG-01");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 240, 130, 20));

        jLabel15.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Contoh ID : LBP-01");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        jButton6.setText("Cari");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, -1, 30));

        jLabel16.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Contoh ID : KKR-01");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, -1, 20));

        txharga.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        txharga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txharga.setText("9000");
        txharga.setEnabled(false);
        getContentPane().add(txharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 300, 70, 30));

        jLabel11.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel11.setText("Total Galon");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 340, 90, 20));

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        jButton10.setText("Edit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, 110, 40));

        btmasuk.setBackground(new java.awt.Color(153, 153, 153));
        btmasuk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btmasuk.setText(" Biaya Pengiriman");
        btmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmasukActionPerformed(evt);
            }
        });
        getContentPane().add(btmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 340, 140, 30));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 110, 40));

        jLabel19.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 204)), javax.swing.BorderFactory.createTitledBorder(null, "Biaya Pengeluaran ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 440, 190, 100));

        jLabel12.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel12.setText("Ambil Data Stok  Galon");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, -1, -1));

        jLabel17.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel17.setText("Input Data Lembaga");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel18.setText("Input Data Kurir");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, -1, -1));

        bt_riwayat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bt_riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/history.png"))); // NOI18N
        bt_riwayat.setText("Riwayat Pendistribusian");
        bt_riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_riwayatActionPerformed(evt);
            }
        });
        getContentPane().add(bt_riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 430, 210, 40));

        tabel_distibusi.setBackground(new java.awt.Color(153, 153, 153));
        tabel_distibusi.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_distibusi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_distibusiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_distibusi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 930, 110));

        nama_kurir.setEditable(false);
        nama_kurir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nama_kurir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(nama_kurir, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 120, 30));

        jButton8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/arrow.png"))); // NOI18N
        jButton8.setText("LogOut");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 130, 40));

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cariKeyPressed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 120, 30));

        txt_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/search (1).png"))); // NOI18N
        txt_search.setText("Cari");
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, 80, 30));

        jLabel20.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel20.setText("Ambil Data Stok  Galon");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, -1, -1));

        btsaran.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btsaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        btsaran.setText("Saran");
        btsaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaranActionPerformed(evt);
            }
        });
        getContentPane().add(btsaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 110, 40));

        jButton15.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton15.setText("Ebout");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 110, 40));

        jButton9.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton9.setText(" Data Laporan");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 180, 40));

        jButton5.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/cash-flow.png"))); // NOI18N
        jButton5.setText("STOK & KAS");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setDoubleBuffered(true);
        jButton5.setFocusCycleRoot(true);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 180, 40));

        jButton2.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/money-transfer.png"))); // NOI18N
        jButton2.setText("TRANSAKSI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 160, 40));

        jButton4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/data.png"))); // NOI18N
        jButton4.setText("MASTER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 160, 40));

        btmenu.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/home.png"))); // NOI18N
        btmenu.setText("MENU");
        btmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmenuActionPerformed(evt);
            }
        });
        getContentPane().add(btmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 160, 40));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Pendistribusian lembaga.jpg"))); // NOI18N
        jLabel1.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void create_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_donasiActionPerformed
        // TODO add your handling code here:
       simpan();
       tampildata_distribuksi();
       kosongkan_pendistribusian();
       auto_pendistribusian();
       
  
    }//GEN-LAST:event_create_donasiActionPerformed

    private void delete_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_donasiActionPerformed
        // TODO add your handling code here:
//        try{
//            
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
//            Statement st = con.createStatement();
//            PreparedStatement ps = con.prepareStatement("DELETE FROM data_pendistribusian WHERE id_distribusi='"+id_distribusi.getText()+"'");  
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "DELETED!!");
//        }
//        catch(HeadlessException | SQLException e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//        
//        tampilData_distribuksi();
//       kosongkan_pendistribusian();
//        
     int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete record Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            String sql=("DELETE FROM data_pendistribusian WHERE id_distribusi='"+id_distribusi.getText()+"'");
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
        tampildata_distribuksi();
        
       }
    }//GEN-LAST:event_delete_donasiActionPerformed

    private void txcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txcariActionPerformed
        // TODO add your handling code here:
        try{

            rs= st.executeQuery("SELECT * FROM data_stok WHERE id_stok LIKE '"+ id_stok.getText() +"'");

            if (rs.next () )
            {
                id_stok.setText(rs.getString(2));
                jumlah_galon.setText(rs.getString(8));
//                nama_tempat.setText(rs.getString(3));
              
            }else{
                JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
            }

        }catch (HeadlessException | SQLException ex){
            System.out.println("error : "+ex);
        }
        
    }//GEN-LAST:event_txcariActionPerformed

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try{
                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
                       rs= st.executeQuery("SELECT * FROM data_karyawan_kurir WHERE id_kurir LIKE '"+ id_kurir.getText() +"'");
                       
                       
                       if (rs.next () )
                       {
                            id_kurir.setText(rs.getString(2));
                            nama_kurir.setText(rs.getString(3));
                           
                       }else{
                           JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
                       }
                       
                   }catch (HeadlessException | SQLException ex){
                       System.out.println("error : "+ex);
                   }
    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void kosongkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kosongkan1ActionPerformed
        // TODO add your handling code here:
        kosongkan_pendistribusian();
         auto_pendistribusian();
       
    }//GEN-LAST:event_kosongkan1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{

            rs= st.executeQuery("SELECT * FROM data_lembagapenerima WHERE id_penerima LIKE '"+ id_lembaga.getText() +"'");

            if (rs.next () )
            {
                id_lembaga.setText(rs.getString(1));
                penanggung_jawab.setText(rs.getString(2));
                nama_tempat.setText(rs.getString(3));
                nohp_penerima.setText(rs.getString(4));
                jComboBox.setSelectedItem(rs.getString(5));
                alamat_penerima.setText(rs.getString(6));
//                nama_tempat.setText(rs.getString(3));
              
            }else{
                JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
            }

        }catch (HeadlessException | SQLException ex){
            System.out.println("error : "+ex);
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txpengeluaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpengeluaranKeyPressed

    }//GEN-LAST:event_txpengeluaranKeyPressed

    private void btmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmasukActionPerformed
        // TODO add your handling code here:
        //        int jml=Integer.parseInt(kas_masuk.getText());
        //        int total=Integer.parseInt(update_jumlah.getText());
        //
        //        if
        //           (jml<total){
            //           JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
            //           kas_masuk.requestFocus();
            //        }else {
            //                int totbay=total+jml;
            //                update_jumlah.setText(Integer.toString(totbay));
            //
            //                ambildata();

            int jml=Integer.parseInt(txjml.getText());
            int total=Integer.parseInt(txharga.getText());
            {
                //        if(jml>jml){
                    //            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
                    //        }else if (jml<=100000){

                    int totbay=total*jml;
                    txpengeluaran.setText(Integer.toString(totbay));

//                    btmasuk.setEnabled(false);

                    //                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        //            int total = Integer.parseInt(kas_keluar.getText());
                        //            int bayar = Integer.parseInt(kas_masuk.getText());
                        //            if (bayar < total) {
                            //                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                            //                kas_keluar.requestFocus();
                            //            } else {
                            //                int kembali = bayar - total;
                            //                update_jumlah.setText(Integer.toString(kembali));
                            //            }
                        //                kas_masuk.setText("");

                        //                tlp.setText("");
                        //                txt_tgl.setText("");
                        //                jumlah.setText("");
                        // stockTF.setText("");

                    }

    }//GEN-LAST:event_btmasukActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        perbarui();
        tampildata_distribuksi();
        kosongkan_pendistribusian();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bt_riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_riwayatActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Riwayat_Pendistribusian().setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_riwayatActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //          String ObjButtons[] = {"Yes","No"};
        // int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        // if(pilihan == 0){
            new Login.Form_Login().setVisible(true);
            dispose();
            //        System.exit(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tabel_distibusiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_distibusiMouseClicked
        // TODO add your handling code here:
                DefaultTableModel model = (DefaultTableModel) tabel_distibusi.getModel();
        int baris = tabel_distibusi.getSelectedRow();
        
        id_distribusi.setText(tabel_distibusi.getModel().getValueAt(baris, 0).toString());
        
        penanggung_jawab.setText(tabel_distibusi.getModel().getValueAt(baris, 1).toString());
        
        nama_tempat.setText(tabel_distibusi.getModel().getValueAt(baris, 2).toString());
        
        nohp_penerima.setText(tabel_distibusi.getModel().getValueAt(baris, 3).toString());
        
        jComboBox.setSelectedItem(tabel_distibusi.getModel().getValueAt(baris, 4).toString());
        
        alamat_penerima.setText(tabel_distibusi.getModel().getValueAt(baris, 5).toString());
        
        Date date = null;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabel_distibusi.getValueAt(baris,6));
       } catch (ParseException ex) {
           Logger.getLogger(Form_Pendistribusian.class.getName()).log(Level.SEVERE, null, ex);
       }
        tanggall.setDate(date);
        
        id_kurir.setText(tabel_distibusi.getModel().getValueAt(baris, 7).toString());
        
        nama_kurir.setText(tabel_distibusi.getModel().getValueAt(baris, 8).toString());
        
        id_stok.setText(tabel_distibusi.getModel().getValueAt(baris, 9).toString());
        
        jumlah_galon.setText(tabel_distibusi.getModel().getValueAt(baris, 10).toString());
        
        txjml.setText(tabel_distibusi.getModel().getValueAt(baris,11).toString());
        
//         txharga.setText(tabel_distibusi.getModel().getValueAt(baris,12).toString());
        
        txpengeluaran.setText(tabel_distibusi.getModel().getValueAt(baris, 12).toString());
    }//GEN-LAST:event_tabel_distibusiMouseClicked

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        tampildata_distribuksi();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampildata_distribuksi();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        tampildata_distribuksi();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKeyPressed

    private void btsaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaranActionPerformed
        // TODO add your handling code here:
        Form_Ebout_FeedBack.Form_FeedBack frm = new Form_Ebout_FeedBack.Form_FeedBack(this, rootPaneCheckingEnabled);
        frm.setTitle("Saran");
        frm.setVisible(true);
    }//GEN-LAST:event_btsaranActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        new Form_Ebout_FeedBack.Form_Ebout().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        new Output.Form_Tampilan_Laporan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Stok_Kas().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Transaksi().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new Master.Form_Master().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Pendistribusian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Pendistribusian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Pendistribusian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Pendistribusian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Pendistribusian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_penerima;
    private javax.swing.JButton bt_riwayat;
    private javax.swing.JButton btmasuk;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton create_donasi;
    private javax.swing.JButton delete_donasi;
    private javax.swing.JTextField id_distribusi;
    private javax.swing.JTextField id_kurir;
    private javax.swing.JTextField id_lembaga;
    private javax.swing.JTextField id_stok;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton15;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jumlah_galon;
    private javax.swing.JButton kosongkan1;
    private javax.swing.JTextField nama_kurir;
    private javax.swing.JTextField nama_tempat;
    private javax.swing.JTextField nohp_penerima;
    private javax.swing.JTextField penanggung_jawab;
    private javax.swing.JTable tabel_distibusi;
    private javax.swing.JLabel tanggal;
    private com.toedter.calendar.JDateChooser tanggall;
    private javax.swing.JButton txcari;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txjml;
    private javax.swing.JTextField txpengeluaran;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    // End of variables declaration//GEN-END:variables
}
