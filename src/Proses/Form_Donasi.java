/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;


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
import Master.Form_Donatur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author harip
 */

public class Form_Donasi extends javax.swing.JFrame {
   
   Statement st;
   Connection con;
   ResultSet rs;
   public DefaultTableModel model;


    /**
     * Creates new form Form_Donasi
     */
    public Form_Donasi() {
        initComponents();
//        String[] header ={"Kode Akta","Jenis Akta"};
//        model = new DefaultTableModel(header,0);
//        tabel_donatur.setModel(model);
        String[] head ={""};
        DefaultTableModel model2 = new DefaultTableModel(head,0);
        tabel_donasi.setModel(model2);
//        tampilTabel_donatur();
        tampilTabel_donasi();
        data_donatur();
        auto_donasi();
        Tanggal_sekarang();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable (false);
        
    }
    
    public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    
    
        public void kosongkan_donasi(){
          nama_donasi.setText(null);
          nohp_donasi.setText(null);
          jComboBox.setSelectedItem(" -Jenis Kelamin-");
          alamat_donasi.setText(null);
          tanggall.setDate(null);
          jumlah_donasi.setText(null);
         
      }
        
            private void data_donatur(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root", "");
            String SelectQuery = "SELECT * FROM data_donatur WHERE id_donatur";
            st = con.createStatement();
            rs = st.executeQuery(SelectQuery);
            while(rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getString("id_donatur");
                row[1] = rs.getString("nama");
                row[2] = rs.getString("no_hp");
                row[3] = rs.getString("jenis_kelamin");
                row[4] = rs.getString("email");
                row[5] = rs.getString("alamat");    
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }  
        

    
//    private void tampilTabel_donatur(){
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("ID Donatur");
//        model.addColumn("Nama");
//        model.addColumn("No HP");
//        model.addColumn("Jenis Kelamin");
//        model.addColumn("Email");
//        model.addColumn("Alamat");
//        tabel_donatur.setModel(model);
//        //untuk mengahapus baris setelah input
//        int row = tabel_donatur.getRowCount();
//         for(int a = 0 ; a < row ; a++){
//            model.removeRow(0);
//        }
//        String cari = txt_carii.getText();
//         String sql = "Select * from data_donatur where id_donatur like '%" + txt_carii.getText() + "%'" +
//                        "or nama like '%" + txt_carii.getText() + "%'" +"or no_hp like '%" + txt_carii.getText() + "%'"+"or email like '%" + txt_carii.getText() + "%'";
//
//        try{
//            Connection connect = Koneksi.configDB();//memanggil koneksi
//            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
//            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query
//            
//            while (rslt.next()){
//                //menampung data sementara
//                   
//                 String satu = rslt.getString("id_donatur");
//                 String dua  = rslt.getString("nama");
//                 String tiga = rslt.getString("no_hp");
//                 String empat= rslt.getString("jenis_kelamin");
//                 String lima = rslt.getString("email");
//                 String enam = rslt.getString("alamat");
//                 
//                 
//                  
//                    
//                    
//                //masukan semua data kedalam array
//                String[] data = {satu,dua,tiga,empat,lima,enam};
//                //menambahakan baris sesuai dengan data yang tersimpan diarray
//                model.addRow(data);
//            }
//                //mengeset nilai yang ditampung agar muncul di table
//                tabel_donatur.setModel(model);
//            
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        
//    }
//    
     private void tampilTabel_donasi(){
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("ID Donasi");
        model2.addColumn("Nama");
        model2.addColumn("No HP");
        model2.addColumn("Jenis Kelamin");
        model2.addColumn("Alamat");
        model2.addColumn("Jumlah Donasi");
        model2.addColumn("Status Donasi");
        model2.addColumn("Tanggal Masuk");
        tabel_donasi.setModel(model2);
        //untuk mengahapus baris setelah input
        int row = tabel_donasi.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model2.removeRow(0);
        }
        String sql = "Select * from data_donasi where id_donasi like '%" + txt_cari.getText() + "%'" +
        "or tanggal_donasi like '%" + txt_cari.getText() + "%'" +"or no_hp like '%" + txt_cari.getText() + "%'"+"or nama like '%" + txt_cari.getText() + "%'";
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu  = rslt.getString("id_donasi");
                 String dua   = rslt.getString("nama");
                 String tiga  = rslt.getString("no_hp");
                 String empat = rslt.getString("jenis_kelamin");
                 String lima  = rslt.getString("alamat");
                 String enam = rslt.getString("jumlah_donasi");
                 String tujuh  = rslt.getString("status_donasi");
                 String delapan  = rslt.getString("tanggal_donasi"); 
                 
                 
                  
                    
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima,enam,tujuh,delapan};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model2.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tabel_donasi.setModel(model2);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
     
       private void open_db() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        con =   (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/isi_ulang","root","");
            st =  (Statement)con.createStatement();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Koneksi gagal");
            System.out.println(e.getMessage());
        }
    }  
    
    private void auto_donasi(){
        
//                try {
//            open_db();
//            String sql="select right (id_donasi,2)+1 from data_donasi";
//            ResultSet rs=st.executeQuery(sql);
//            if(rs.next()){
//                rs.last();
//                String no=rs.getString(0);
//                while (no.length()<3){
//                    no="0"+no;
//                    id_donasi.setText("DSI-"+no);
//                    }
//                }
//            else
//            {
//                id_donasi.setText("DSI-"); 
//              
//        }
//        } catch (Exception e) 
//        {
        
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_donasi) as id_donasi FROM data_donasi";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.id_donasi.setText("DSI-0"+(a+1));  
       }else{  
         this.id_donasi.setText("DSI-0"+(a+1));  
       }  
            
        }

     while(rs.next()){  
    
   }  
   rs.close(); }  
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
            PreparedStatement ps = con.prepareStatement("insert into data_donasi(id_donasi,nama,no_hp,jenis_kelamin,alamat,status_donasi,tanggal_donasi,jumlah_donasi) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, id_donasi.getText());
            ps.setString(2, nama_donasi.getText());
            ps.setString(3, nohp_donasi.getText());
            ps.setString(4, (String) jComboBox.getSelectedItem());
            ps.setString(5, alamat_donasi.getText());
            ps.setString(6, (String) txstatus.getSelectedItem());
            ps.setString(7, tanggal);
            ps.setString(8, jumlah_donasi.getText()); 
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }      
    }
    
    private void perbarui(){
        try {
            
            String penanggalan = "dd-MM-yyyy";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tanggall.getDate());
            con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_donasi SET id_donasi =?,nama=?,no_hp=?,jenis_kelamin=?,alamat=?,status_donasi=?,tanggal_donasi=?,jumlah_donasi=? WHERE id_donasi = '"+id_donasi.getText()+"'");
            ps.setString(1, id_donasi.getText());
            ps.setString(2, nama_donasi.getText());
            ps.setString(3, nohp_donasi.getText());
            ps.setString(4, (String) jComboBox.getSelectedItem());
            ps.setString(5, alamat_donasi.getText());
            ps.setString(6, (String) txstatus.getSelectedItem());
            ps.setString(7, tanggal);
            ps.setString(8, jumlah_donasi.getText()); 
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Anda Berhasil Edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
            e.printStackTrace();
        }
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        update_donasi = new javax.swing.JButton();
        reset_donasi = new javax.swing.JButton();
        create_donasi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_donasi = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        nama_donasi = new javax.swing.JTextField();
        nohp_donasi = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat_donasi = new javax.swing.JTextArea();
        tanggal = new javax.swing.JLabel();
        id_donasi = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tanggall = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jumlah_donasi = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        txcetak = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        id_donatur = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txstatus = new javax.swing.JComboBox<>();
        btriwayat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        txt_search = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btsaran = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btmenu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1300, 720));
        setPreferredSize(new java.awt.Dimension(1300, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tabel Donasi");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 210, 20));

        update_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        update_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        update_donasi.setText("Edit");
        update_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(update_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, 110, 40));

        reset_donasi.setBackground(new java.awt.Color(255, 255, 255));
        reset_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reset_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        reset_donasi.setText("Bersihkan");
        reset_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(reset_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, 30));

        create_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        create_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        create_donasi.setText("Simpan");
        create_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(create_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, 110, 40));

        tabel_donasi.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_donasi.setCellSelectionEnabled(true);
        tabel_donasi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabel_donasi.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tabel_donasi.setDoubleBuffered(true);
        tabel_donasi.setDragEnabled(true);
        tabel_donasi.setFillsViewportHeight(true);
        tabel_donasi.setFocusCycleRoot(true);
        tabel_donasi.setFocusTraversalPolicyProvider(true);
        tabel_donasi.setInheritsPopupMenu(true);
        tabel_donasi.setSurrendersFocusOnKeystroke(true);
        tabel_donasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_donasiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_donasi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 510, 800, 160));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel23.setText("Nama Donasi");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 120, -1));
        getContentPane().add(nama_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 180, 30));
        getContentPane().add(nohp_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 150, 30));

        jLabel24.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel24.setText("No HP");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 60, -1));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel1.setText("Jenis Kelamin");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 110, -1));

        jComboBox.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " -Jenis Kelamin-", "    Laki-Laki", "   Perempuan" }));
        getContentPane().add(jComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 150, -1));

        alamat_donasi.setColumns(20);
        alamat_donasi.setLineWrap(true);
        alamat_donasi.setRows(5);
        alamat_donasi.setWrapStyleWord(true);
        alamat_donasi.setFocusCycleRoot(true);
        alamat_donasi.setFocusTraversalPolicyProvider(true);
        jScrollPane2.setViewportView(alamat_donasi);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 180, 100));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText("Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));
        getContentPane().add(id_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 70, 29));

        jLabel16.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel16.setText("ID Donatur");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 100, -1));

        tanggall.setDateFormatString("yyyy-MM-dd");
        tanggall.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggallPropertyChange(evt);
            }
        });
        getContentPane().add(tanggall, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 200, 150, 29));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel6.setText("Status Donasi");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 120, 110, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel8.setText("Nominal Donasi");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 120, -1));

        jumlah_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(jumlah_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 160, 164, 30));

        btnHapus.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 110, 40));

        jLabel28.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel28.setText("Alamat");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, 70, -1));

        jButton8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/arrow.png"))); // NOI18N
        jButton8.setText("LogOut");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 130, 40));

        txcetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/printer.png"))); // NOI18N
        txcetak.setText("Cetak Bukti");
        txcetak.setToolTipText("");
        txcetak.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txcetak.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txcetakActionPerformed(evt);
            }
        });
        getContentPane().add(txcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(883, 290, 140, 40));

        jLabel17.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel17.setText("ID Donasi");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 90, -1));
        getContentPane().add(id_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 60, 30));

        jButton10.setText("Cari");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, -1, 30));

        jLabel15.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Contoh ID : DR-01");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, -1));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 110, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel7.setText("Tanggal Donasi");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 200, 120, -1));

        txstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Donatur", "Umum" }));
        getContentPane().add(txstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, 90, 30));

        btriwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/history.png"))); // NOI18N
        btriwayat.setText("Data Donatur");
        btriwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btriwayatActionPerformed(evt);
            }
        });
        getContentPane().add(btriwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 290, 140, 40));

        jLabel4.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel4.setText("Cari Data Donasi");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 450, -1, -1));

        jLabel5.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel5.setText("Input Data Donasi");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 470, 110, 30));

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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 470, -1, 30));

        jLabel9.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel9.setText("Input Jumlah Donasi");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, -1, -1));

        btsaran.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btsaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        btsaran.setText("Saran");
        btsaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaranActionPerformed(evt);
            }
        });
        getContentPane().add(btsaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 110, 40));

        jButton6.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton6.setText("Ebout");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 110, 40));

        jButton9.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton9.setText(" Data Laporan");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 180, 40));

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
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 180, 40));

        jButton2.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/money-transfer.png"))); // NOI18N
        jButton2.setText("TRANSAKSI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 160, 40));

        jButton4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/data.png"))); // NOI18N
        jButton4.setText("MASTER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 160, 40));

        btmenu.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/home.png"))); // NOI18N
        btmenu.setText("MENU");
        btmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmenuActionPerformed(evt);
            }
        });
        getContentPane().add(btmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 160, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Donasi.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1300, 720));
        jLabel2.setMinimumSize(new java.awt.Dimension(1300, 720));
        jLabel2.setPreferredSize(new java.awt.Dimension(1300, 720));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 720));
        jLabel2.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tanggallPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggallPropertyChange
        // TODO add your handling code here:
 
    }//GEN-LAST:event_tanggallPropertyChange

    private void jumlah_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_donasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah_donasiActionPerformed

    private void update_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_donasiActionPerformed
        // TODO add your handling code here:
          
        perbarui();
        tampilTabel_donasi();
        kosongkan_donasi();
    }//GEN-LAST:event_update_donasiActionPerformed

    private void reset_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_donasiActionPerformed
        // TODO add your handling code here:
       kosongkan_donasi();
       auto_donasi();
    }//GEN-LAST:event_reset_donasiActionPerformed

    private void create_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_donasiActionPerformed
        // TODO add your handling code here:
       simpan();
       kosongkan_donasi();
       tampilTabel_donasi();
       auto_donasi();
    }//GEN-LAST:event_create_donasiActionPerformed

    private void tabel_donasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_donasiMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) tabel_donasi.getModel();
        int baris = tabel_donasi.getSelectedRow();
        
        id_donasi.setText(tabel_donasi.getModel().getValueAt(baris, 0).toString());
        
        nama_donasi.setText(tabel_donasi.getModel().getValueAt(baris, 1).toString());
        
        nohp_donasi.setText(tabel_donasi.getModel().getValueAt(baris, 2).toString());    
        
        jComboBox.setSelectedItem(tabel_donasi.getModel().getValueAt(baris, 3).toString());
        
        alamat_donasi.setText(tabel_donasi.getModel().getValueAt(baris, 4).toString());
        
        jumlah_donasi.setText(tabel_donasi.getModel().getValueAt(baris,5).toString());
          
        txstatus.setSelectedItem(tabel_donasi.getModel().getValueAt(baris, 6).toString());
        
      
        
        Date date = null;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model2.getValueAt(baris,7));
       } catch (ParseException ex) {
           Logger.getLogger(Form_Donasi.class.getName()).log(Level.SEVERE, null, ex);
       }
        tanggall.setDate(date);
           
      
     
        
        
        
    }//GEN-LAST:event_tabel_donasiMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
       int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_donasi WHERE id_donasi='"+id_donasi.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
                  
        tampilTabel_donasi();
       }


    }//GEN-LAST:event_btnHapusActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //          String ObjButtons[] = {"Yes","No"};
        // int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        // if(pilihan == 0){
            new Login.Form_Login().setVisible(true);
            dispose();
            //        System.exit(0);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void txcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txcetakActionPerformed
        // TODO add your handling code here:
         try {
            String reportName = "src/Report/Nota_Donasi.jasper";
            HashMap parameter = new HashMap();
            parameter.put("id_donasi",this.id_donasi.getText());
            JasperPrint jPrint = JasperFillManager.fillReport(reportName, parameter, Koneksi.configDB());
            JasperViewer.viewReport(jPrint, false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_txcetakActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try{
                       
                       rs= st.executeQuery("SELECT * FROM data_donatur WHERE id_donatur LIKE '"+ id_donatur.getText() +"'");
                       
                       
                       if (rs.next () )
                       {
                            id_donatur.setText(rs.getString(1));
                            nama_donasi.setText(rs.getString(2));
                            nohp_donasi.setText(rs.getString(3));
                            jComboBox.setSelectedItem(rs.getString(4));
                            alamat_donasi.setText(rs.getString(6));
                           
                       }else{
                           JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
                       }
                       
                   }catch (HeadlessException | SQLException ex){
                       System.out.println("error : "+ex);
                   }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btriwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btriwayatActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Riwayat_Donatur().setVisible(true);
        dispose();
    }//GEN-LAST:event_btriwayatActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        tampilTabel_donasi();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampilTabel_donasi();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        tampilTabel_donasi();
    }//GEN-LAST:event_txt_cariActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Donasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Donasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Donasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Donasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Donasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_donasi;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btriwayat;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton create_donasi;
    private javax.swing.JTextField id_donasi;
    private javax.swing.JTextField id_donatur;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlah_donasi;
    private javax.swing.JTextField nama_donasi;
    private javax.swing.JTextField nohp_donasi;
    private javax.swing.JButton reset_donasi;
    private javax.swing.JTable tabel_donasi;
    private javax.swing.JLabel tanggal;
    private com.toedter.calendar.JDateChooser tanggall;
    private javax.swing.JButton txcetak;
    private javax.swing.JComboBox<String> txstatus;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    private javax.swing.JButton update_donasi;
    // End of variables declaration//GEN-END:variables
}
