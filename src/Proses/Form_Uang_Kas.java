/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import Koneksi.Koneksi;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

/**
 *
 * @author harip
 */
public class Form_Uang_Kas extends javax.swing.JFrame {
    Statement st;
    Connection con;
    ResultSet rs;
    public int totbay;
    public DefaultTableModel model;

    /**
     * Creates new form Form_Kas_Donatur
     */
    public Form_Uang_Kas() {
        initComponents();
        Object header[]={"ID Donasi","Jumlah Donasi","Kondisi","Total Donasi"};
        model=new DefaultTableModel(null,header);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable (false);
        auto_kas();
        open_db();
        tampilData_kas();
        Tanggal_sekarang();
        tabel_kas .setAutoCreateRowSorter( true );
        
    }
      private void siapIsi(boolean a){
        id_donasi.setEditable(a);
        jumlah_kas_lama.setEnabled(a);
        update_jumlah.setEnabled(a);
     
    }
    private void bersih(){
        id_donasi.setText("");
        tgl_update.setDate(null);
        kas_masuk.setText("0");
        kas_keluar.setText("0");
        jumlah_kas_lama.setText("0");
        update_jumlah.setText("0");

    }
    
    
public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
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
       
           private void auto_kas(){
        try {
            open_db();
            String sql="select right (id_kas,2)+1 from data_kas";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    id_kas.setText("IKS-"+no);
                    }
                }
            else
            {
                id_kas.setText("IKS-001"); 
              
            }
        } catch (Exception e) 
        {
        }
           }
        
             
               private void tampilData_kas(){
        DefaultTableModel model3 = new DefaultTableModel();
        model3.addColumn("ID Kas");
        model3.addColumn("ID Donasi");
        model3.addColumn("ID Distribusi");
        model3.addColumn("Tanggal Kas Masuk");
        model3.addColumn("Kondisi");
        model3.addColumn("Jumlah Kas");
        tabel_kas.setModel(model3);
        //untuk mengahapus baris setelah input
        int row = tabel_kas.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        String sql = "Select * from data_kas where id_kas like '%" + txt_cari.getText() + "%'";
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu  = rslt.getString("id_kas");
                 String dua  = rslt.getString("id_donasi");
                 String tiga  = rslt.getString("id_distribusi");
                 
                 String empat  = rslt.getString("tgl_update");
                 String lima  = rslt.getString("kondisi");
                 String enam  = rslt.getString("update_jumlah");
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima,enam};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model3.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                 tabel_kas.setModel(model3);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
               }     
               
                    private void simpan(){
           
            try{ 
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tgl_update.getDate()); 
    
  
            {
         
            String sql ="insert into data_kas values('"+id_kas.getText()+"','"+id_donasi.getText()+"','"+id_distribusi.getText()+"','"+jumlah_kas_lama.getText()+"','"+tanggal+"','"+kas_masuk.getText()+"','"+kas_keluar.getText()+"','"+kondisi.getSelectedItem()+"','"+update_jumlah.getText()+  "')";
            
             st.executeUpdate(sql);
             
            }   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Penyimpanan Uang Kas Berhasil");
            
        }
                    }
             
       
//       private void perbarui(){
//        try{ 
//           String tanggalpenyerahan ="yyyy-MM-dd" ;
//            SimpleDateFormat fm = new SimpleDateFormat(tanggalpenyerahan);
//            String tanggal = fm.format(fm.format(tgl_update.getDate())); 
//    
//           
//              
//            {
//          
////            int subtot= Integer.parseInt(tabeltransaksi.getValueAt(i, 5).toString());
//         
//            String sql ="UPDATE data_kas SET('"+id_kas.getText()+"','"+id_donasi+"','"+id_distribusi.getText()+"','"+jumlah_kas_lama.getText()+"','"+tanggal+"','"+kas_masuk.getText()+"','"+kas_keluar.getText()+"','"+kondisi.getSelectedItem()+"','"+update_jumlah.getText()+  "')";
//        
//           st.executeUpdate(sql);
//             
//            }   
//          
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Penyimpanan Uang Kas Berhasil");
//            
//        }
//                    }
       
       
         private void perbarui(){
          try {
            
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tgl_update.getDate());
            con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_kas SET id_kas =?,id_donasi=?,id_distribusi=?,jumlah_kas_lama=?,tgl_update=?,kas_masuk=?,kas_keluar=?,kondisi=?,update_jumlah=? WHERE id_kas = '"+id_kass.getText()+"'");
            ps.setString(1, id_kass.getText());
            ps.setString(2, id_donasi.getText());
            ps.setString(3, id_distribusi.getText());
            ps.setString(4, jumlah_kas_lama.getText());
            ps.setString(5, tanggal);
            ps.setString(6, kas_masuk.getText());
            ps.setString(7, kas_keluar.getText());
            ps.setString(8, (String) kondisi.getSelectedItem());
            ps.setString(9, update_jumlah.getText());
//            ps.setString(10, txjml.getText());
//            ps.setString(11, txpengeluaran.getText());    
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
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

        id_donasi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jumlah_kas_lama = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        update_jumlah = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        kas_keluar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_kas = new javax.swing.JTable();
        id_kas = new javax.swing.JTextField();
        kas_masuk = new javax.swing.JTextField();
        btmasuk = new javax.swing.JButton();
        bttambah = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        tgl_update = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        kondisi = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btkeluar = new javax.swing.JButton();
        caridistribusi = new javax.swing.JButton();
        btedit = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        id_distribusi = new javax.swing.JTextField();
        caridonasi = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        bthapus = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        id_kass = new javax.swing.JTextField();
        btriwayat = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        txt_search = new javax.swing.JButton();
        btsaran = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btmenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 720));
        setPreferredSize(new java.awt.Dimension(1300, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(id_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 80, 29));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel13.setText("Up Date Jumlah");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 120, -1));
        getContentPane().add(jumlah_kas_lama, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, 190, 29));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel23.setText("Jumlah Kas Lama");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 130, -1));
        getContentPane().add(update_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, 140, 30));

        jLabel24.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel24.setText("Tgl Kas Masuk");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 110, -1));

        jLabel14.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel14.setText("ID Donasi");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 80, -1));

        jLabel15.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel15.setText("Kas Masuk");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 80, -1));

        jLabel16.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel16.setText("Kas Keluar");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 90, -1));

        kas_keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kas_keluarKeyPressed(evt);
            }
        });
        getContentPane().add(kas_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 120, 29));

        jLabel17.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel17.setText("Kondisi");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 70, -1));

        tabel_kas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_kas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabel_kas.setDoubleBuffered(true);
        tabel_kas.setDragEnabled(true);
        tabel_kas.setFillsViewportHeight(true);
        tabel_kas.setFocusCycleRoot(true);
        tabel_kas.setFocusTraversalPolicyProvider(true);
        tabel_kas.setInheritsPopupMenu(true);
        tabel_kas.setShowHorizontalLines(false);
        tabel_kas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_kasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_kas);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 510, 790, 170));
        getContentPane().add(id_kas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 90, 29));

        kas_masuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kas_masukKeyPressed(evt);
            }
        });
        getContentPane().add(kas_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 120, 29));

        btmasuk.setBackground(new java.awt.Color(153, 153, 153));
        btmasuk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btmasuk.setText("Hitung Kas Masuk");
        btmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmasukActionPerformed(evt);
            }
        });
        getContentPane().add(btmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 140, 30));

        bttambah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bttambah.setText("Buat\n");
        bttambah.setEnabled(false);
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });
        getContentPane().add(bttambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 410, 90, 30));

        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        btsimpan.setText("Simpan");
        btsimpan.setEnabled(false);
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, 110, 40));
        getContentPane().add(tgl_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 150, 30));

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 1, 17)); // NOI18N
        jLabel3.setText("Tabel Kas");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, 20));

        kondisi.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        kondisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masuk", "Keluar" }));
        kondisi.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        kondisi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(kondisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, 90, -1));

        jLabel11.setFont(new java.awt.Font("Forte", 0, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Uang Kas");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 210, 40));

        btkeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btkeluar.setText("Hitung Kas Keluar");
        btkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 170, 140, 30));

        caridistribusi.setText("Cari");
        caridistribusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caridistribusiActionPerformed(evt);
            }
        });
        getContentPane().add(caridistribusi, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 130, 60, 30));

        btedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        btedit.setText("Edit");
        btedit.setEnabled(false);
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });
        getContentPane().add(btedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 560, 110, 40));

        jLabel20.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel20.setText("ID Distribusi");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 100, -1));

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/add.png"))); // NOI18N
        btnNew.setText("Baru");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, 110, 40));

        btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/cancel.png"))); // NOI18N
        btncancel.setText("Batal");
        btncancel.setEnabled(false);
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        getContentPane().add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 460, 110, 40));

        jLabel21.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel21.setText("ID Kas Lama");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 90, -1));
        getContentPane().add(id_distribusi, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 80, 30));

        caridonasi.setText("Cari");
        caridonasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caridonasiActionPerformed(evt);
            }
        });
        getContentPane().add(caridonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 60, 30));

        jLabel18.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Contoh ID : PDS-01");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel19.setText("Tgl harus sama Dengan ID yang di Input");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 280, -1));

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        bthapus.setText("Hapus");
        bthapus.setEnabled(false);
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        getContentPane().add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 620, 110, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setToolTipText("");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createTitledBorder(null, "Sesuaikan Data Masuk Atau Keluar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 450, 240));

        jButton8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/arrow.png"))); // NOI18N
        jButton8.setText("LogOut");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 130, 40));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText("Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createTitledBorder(null, "Kas Keluar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 380, 130));

        jLabel22.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel22.setText("ID Kas");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 60, -1));

        id_kass.setEnabled(false);
        getContentPane().add(id_kass, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, 70, 30));

        btriwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/history.png"))); // NOI18N
        btriwayat.setText("Riwayat Kas");
        btriwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btriwayatActionPerformed(evt);
            }
        });
        getContentPane().add(btriwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 400, 150, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setToolTipText("");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createTitledBorder(null, "Kas Masuk", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 370, 160));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 110, 40));

        jLabel25.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Contoh ID : DSI-01");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, -1, -1));

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

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Umum Master.jpg"))); // NOI18N
        jLabel1.setMaximumSize(null);
        jLabel1.setName(""); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));
        jLabel1.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        if(bttambah.getText().equalsIgnoreCase("Tambah")){
            bttambah.setText("Refresh");
            siapIsi(true);
            bersih();

            // txt_transaksi.setEnabled(false);
            btsimpan.setEnabled(true);
            bthapus.setEnabled(false);
            btmasuk.setEnabled(true);
            btkeluar.setEnabled(true);
//            btedit.setEnabled(true);

        }else{
            bersih();
            siapIsi(true);
            bttambah.setText("Tambah");
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            
        }
    }//GEN-LAST:event_bttambahActionPerformed

    private void kas_keluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kas_keluarKeyPressed
        // TODO add your handling code here:
//            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int total = Integer.parseInt(jumlah_kas_lama.getText());
//            int bayar = Integer.parseInt(kas_masuk.getText());
//            if (bayar < total) {
//                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
//                kas_keluar.requestFocus();
//            } else {
//                int kembali = total - bayar;
//                update_jumlah.setText(Integer.toString(kembali));
//            }
//        }


        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        int jml=Integer.parseInt(jumlah_kas_lama.getText());
        int total=Integer.parseInt(kas_keluar.getText());
        if (jml<total) {
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                kas_keluar.requestFocus();
            } else {
                int totbay=jml-total;
                update_jumlah.setText(Integer.toString(totbay));
                 
        }
            }
    }//GEN-LAST:event_kas_keluarKeyPressed

    private void btkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btkeluarActionPerformed
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
               

        int jml=Integer.parseInt(jumlah_kas_lama.getText());
        int total=Integer.parseInt(kas_keluar.getText());
        if (jml<total) {
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                kas_keluar.requestFocus();
            } else {
                int totbay=jml-total;
                update_jumlah.setText(Integer.toString(totbay));
                 
//              btkeluar.setEnabled(false);
            }
        
        
//        if(jml>jml){
//            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
//        }else if (jml<=100000){



//            int totbay=jml-total;
//                update_jumlah.setText(Integer.toString(totbay));
//
//                ambildata_keluar();

                
                
                
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
        btsimpan.setEnabled(true);
        bthapus.setEnabled(false);
            
    }//GEN-LAST:event_btkeluarActionPerformed

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
               

        int jml=Integer.parseInt(kas_masuk.getText());
        int total=Integer.parseInt(jumlah_kas_lama.getText());
        {
//        if(jml>jml){
//            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
//        }else if (jml<=100000){



            int totbay=total+jml;
                update_jumlah.setText(Integer.toString(totbay));

             

//             btmasuk.setEnabled(false);
                
                
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
        btsimpan.setEnabled(true);
        bthapus.setEnabled(false);
    }//GEN-LAST:event_btmasukActionPerformed

    private void kas_masukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kas_masukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kas_masukKeyPressed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        simpan();
        tampilData_kas();
        auto_kas();
    }//GEN-LAST:event_btsimpanActionPerformed

    private void caridistribusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caridistribusiActionPerformed
        // TODO add your handling code here:
        try{

            rs= st.executeQuery("SELECT * FROM data_pendistribusian WHERE id_distribusi LIKE '"+ id_distribusi.getText() +"'");

            if (rs.next () )
            {
                id_distribusi.setText(rs.getString(1));
                kas_keluar.setText(rs.getString(13));
//                nama_tempat.setText(rs.getString(3));
//                nohp_penerima.setText(rs.getString(4));
//                jComboBox.setSelectedItem(rs.getString(5));
//                alamat_penerima.setText(rs.getString(6));
//                nama_tempat.setText(rs.getString(3));
              
            }else{
                JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
            }

        }catch (HeadlessException | SQLException ex){
            System.out.println("error : "+ex);
        }
    
    }//GEN-LAST:event_caridistribusiActionPerformed

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
        // TODO add your handling code here:
        perbarui();
        tampilData_kas();
        auto_kas();
    }//GEN-LAST:event_bteditActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
//        btsimpan.setEnabled(true);
        btnNew.setEnabled(false);
        btncancel.setEnabled(true);
        bttambah.setEnabled(true);
        btmasuk.setEnabled(true);
        
    }//GEN-LAST:event_btnNewActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        btncancel.setEnabled(false);
        btsimpan.setEnabled(false);
        btnNew.setEnabled(true);
        bttambah.setEnabled(false);
        btmasuk.setEnabled(false);
        btedit.setEnabled(false);
        bthapus.setEnabled(false);
        bersih();
    }//GEN-LAST:event_btncancelActionPerformed

    private void caridonasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caridonasiActionPerformed
        // TODO add your handling code here:
                try{
                    
       

            rs= st.executeQuery("SELECT * FROM data_donasi WHERE id_donasi LIKE '"+ id_donasi.getText() +"'");

            if (rs.next () )
            {
                id_donasi.setText(rs.getString(1));
                
                kas_masuk.setText(rs.getString(8));
//                nama_tempat.setText(rs.getString(3));
//                nohp_penerima.setText(rs.getString(4));
//                jComboBox.setSelectedItem(rs.getString(5));
//                alamat_penerima.setText(rs.getString(6));
//                nama_tempat.setText(rs.getString(3));
              
            }else{
                JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
            }

        }catch (HeadlessException | SQLException ex){
            System.out.println("error : "+ex);
        }
    
    }//GEN-LAST:event_caridonasiActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
         int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_kas WHERE id_kas='"+id_kass.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
                  
        tampilData_kas();
        auto_kas();
       }

    }//GEN-LAST:event_bthapusActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //          String ObjButtons[] = {"Yes","No"};
        // int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        // if(pilihan == 0){
            new Login.Form_Login().setVisible(true);
            dispose();
            //        System.exit(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btriwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btriwayatActionPerformed
        // TODO add your handling code here:
        new Proses.Form_Riwayat_Kas().setVisible(true);
        dispose();
    }//GEN-LAST:event_btriwayatActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabel_kasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_kasMouseClicked
        // TODO add your handling code here:
        int baris = tabel_kas.getSelectedRow();
        id_kass.setText(tabel_kas.getModel().getValueAt(baris, 0).toString());
//        id_donasi.setText(tabel_kas.getModel().getValueAt(baris, 1).toString());
//        id_distribusi.setText(tabel_kas.getModel().getValueAt(baris, 2).toString());

       Date date = null;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabel_kas.getValueAt(baris, 3));
       } catch (ParseException ex) {
           Logger.getLogger(Form_Stok_Galon.class.getName()).log(Level.SEVERE, null, ex);
       }
        tgl_update.setDate(date);
        
        jumlah_kas_lama.setText(tabel_kas.getModel().getValueAt(baris, 5).toString());
        kondisi.setSelectedItem(tabel_kas.getModel().getValueAt(baris, 4).toString());
        //        kas_keluar.setText(tabel_distribusi.getModel().getValueAt(baris, 3).toString());
        
        bttambah.setEnabled(true);
        bthapus.setEnabled(true);
        btedit.setEnabled(true);
        btsimpan.setEnabled(false);
        
    }//GEN-LAST:event_tabel_kasMouseClicked

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        tampilData_kas();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampilData_kas();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        tampilData_kas();
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
            java.util.logging.Logger.getLogger(Form_Uang_Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Uang_Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Uang_Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Uang_Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Uang_Kas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedit;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btkeluar;
    private javax.swing.JButton btmasuk;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btriwayat;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.JButton caridistribusi;
    private javax.swing.JButton caridonasi;
    private javax.swing.JTextField id_distribusi;
    private javax.swing.JTextField id_donasi;
    private javax.swing.JTextField id_kas;
    private javax.swing.JTextField id_kass;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jumlah_kas_lama;
    private javax.swing.JTextField kas_keluar;
    private javax.swing.JTextField kas_masuk;
    private javax.swing.JComboBox<String> kondisi;
    private javax.swing.JTable tabel_kas;
    private javax.swing.JLabel tanggal;
    private com.toedter.calendar.JDateChooser tgl_update;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    private javax.swing.JTextField update_jumlah;
    // End of variables declaration//GEN-END:variables
}
