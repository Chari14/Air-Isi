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
public class Form_Lembaga_Penerima extends javax.swing.JFrame {
   Statement st;
   Connection con;
   ResultSet rs;
   private DefaultTableModel model;
    /**
     * Creates new form Lembaga_Penerima
     */
    public Form_Lembaga_Penerima() {
        initComponents();
        Tanggal_sekarang();
        tampilData_lembagapenerima();
        auto_lembagapenerima();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable (false);
    }
    
    public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    

    
     public void kosongkan_lembagapenerima(){
          penanggung_jawab.setText(null);
          nama_tempat.setText(null);
          nohp_penerima.setText(null);
          jComboBox.setSelectedItem("-Pilih Lembaga-");
          alamat_penerima.setText(null);
          
      }
     
     private void tampilData_lembagapenerima(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pemerima");
        model.addColumn("Penanggung Jawab");
        model.addColumn("Nama Tempat");
        model.addColumn("No HP");
        model.addColumn("Pilih");
        model.addColumn("Alamat Tujuan");
        table_penerima.setModel(model);
        //untuk mengahapus baris setelah input
        int row = table_penerima.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        String sql = "Select * from data_lembagapenerima where id_penerima like '%" + txt_cari.getText() + "%'" +
            "or pilih like '%" + txt_cari.getText() + "%'" + "or nama_tempat like '%" + txt_cari.getText() + "%'";
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu  = rslt.getString("id_penerima");
                 String dua   = rslt.getString("penanggung_jawab");
                 String tiga  = rslt.getString("nama_tempat");
                 String empat = rslt.getString("no_hp");
                 String lima  = rslt.getString("pilih");
                 String enam  = rslt.getString("alamat_tujuan");  
                  
                    
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima,enam};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_penerima.setModel(model);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
                
    }
     
         private void auto_lembagapenerima(){
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_penerima) as id_penerima FROM data_lembagapenerima";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.id_penerima.setText("LBP-0"+(a+1));  
       }else{  
         this.id_penerima.setText("LBP-0"+(a+1));  
       }  
            
        }

     while(rs.next()){  
    
   }  
   rs.close(); st.close();}  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
   }
    
    }

     private boolean perbarui()throws IOException, SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_lembagapenerima SET id_penerima = ?,penanggung_jawab= ?,nama_tempat= ?,no_hp=?,pilih=?,alamat_tujuan=? WHERE id_penerima = '"+id_penerima.getText()+"'");
            ps.setString(1, id_penerima.getText());
            ps.setString(2, penanggung_jawab.getText());
            ps.setString(3, nama_tempat.getText());
            ps.setString(4, nohp_penerima.getText());
            ps.setString(5, (String) jComboBox.getSelectedItem());
            ps.setString(6, alamat_penerima.getText());
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

        jLabel3 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_penerima = new javax.swing.JTable();
        kosongkan = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        nama_tempat = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nohp_penerima = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        alamat_penerima = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        penanggung_jawab = new javax.swing.JTextField();
        tanggal = new javax.swing.JLabel();
        id_penerima = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        txt_search = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btsaran = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btmenu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 720));
        setPreferredSize(new java.awt.Dimension(1300, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Forte", 0, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lembaga Penerima");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 330, 40));

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 120, 40));

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setFocusCycleRoot(true);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 450, 110, 40));

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, 110, 40));

        table_penerima.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        table_penerima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_penerimaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_penerima);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 520, 890, 150));

        kosongkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        kosongkan.setText("KOSONGKAN");
        kosongkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kosongkanActionPerformed(evt);
            }
        });
        getContentPane().add(kosongkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, 140, 30));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel13.setText("ID Penerima");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 100, -1));
        getContentPane().add(nama_tempat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 190, 29));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel23.setText("Penanggung Jawab");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 140, -1));
        getContentPane().add(nohp_penerima, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 166, 29));

        jLabel24.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel24.setText("No HP");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 60, -1));

        jComboBox.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Lembaga-", "      Masjid", "   Pesantren", "     Yayasan", "     Individu" }));
        jComboBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, 150, -1));

        jLabel26.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel26.setText("Pilih");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 40, -1));

        jLabel27.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel27.setText("Alamat Tujuan");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 110, -1));

        alamat_penerima.setColumns(20);
        alamat_penerima.setRows(5);
        jScrollPane4.setViewportView(alamat_penerima);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 240, 120));

        jLabel25.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel25.setText("Nama Tempat");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 110, -1));
        getContentPane().add(penanggung_jawab, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 210, 30));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText(" Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, 30));

        id_penerima.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_penerima.setEnabled(false);
        id_penerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_penerimaActionPerformed(evt);
            }
        });
        getContentPane().add(id_penerima, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 80, 30));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 480, 120, 30));

        txt_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/search (1).png"))); // NOI18N
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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 480, -1, 30));

        jLabel4.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cari Data Donatur");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 450, 130, 30));

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
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 180, 40));

        jButton2.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/money-transfer.png"))); // NOI18N
        jButton2.setText("TRANSAKSI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 160, 40));

        jButton4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/data.png"))); // NOI18N
        jButton4.setText("MASTER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 160, 40));

        btmenu.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/home.png"))); // NOI18N
        btmenu.setText("MENU");
        btmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmenuActionPerformed(evt);
            }
        });
        getContentPane().add(btmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 160, 40));

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Umum Master.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("insert into data_lembagapenerima(id_penerima,penanggung_jawab,nama_tempat,no_hp,pilih,alamat_tujuan) values(?,?,?,?,?,?)");
            ps.setString(1, id_penerima.getText());
            ps.setString(2, penanggung_jawab.getText());
            ps.setString(3, nama_tempat.getText());
            ps.setString(4, nohp_penerima.getText());
            ps.setString(5, (String) jComboBox.getSelectedItem());
            ps.setString(6, alamat_penerima.getText());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Anda Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }
        tampilData_lembagapenerima();
        kosongkan_lembagapenerima();
        auto_lembagapenerima();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
            try {
            // TODO add your handling code here:
            perbarui();
        } catch (IOException ex) {
            Logger.getLogger(Form_Lembaga_Penerima.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Lembaga_Penerima.class.getName()).log(Level.SEVERE, null, ex);
        }
        kosongkan_lembagapenerima();
        tampilData_lembagapenerima();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
//        try{
//            
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
//            Statement st = con.createStatement();
//            PreparedStatement ps = con.prepareStatement("DELETE FROM data_lbpenerima WHERE id_penerima='"+id_penerima.getText()+"'");  
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "DELETED!!");
//        }
//        catch(HeadlessException | SQLException e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//        
int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_lembagapenerima WHERE id_penerima='"+id_penerima.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
            tampilData_lembagapenerima();

       }
       
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void table_penerimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_penerimaMouseClicked
        int baris = table_penerima.getSelectedRow();
        id_penerima.setText(table_penerima.getModel().getValueAt(baris, 0).toString());
        
        penanggung_jawab.setText(table_penerima.getModel().getValueAt(baris, 1).toString());
        
        nama_tempat.setText(table_penerima.getModel().getValueAt(baris, 2).toString());
        
        nohp_penerima.setText(table_penerima.getModel().getValueAt(baris, 3).toString());
        
        jComboBox.setSelectedItem(table_penerima.getModel().getValueAt(baris, 4).toString());
        
        alamat_penerima.setText(table_penerima.getModel().getValueAt(baris, 5).toString());
    }//GEN-LAST:event_table_penerimaMouseClicked

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void kosongkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kosongkanActionPerformed
        // TODO add your handling code here:
        kosongkan_lembagapenerima();
        auto_lembagapenerima();
    }//GEN-LAST:event_kosongkanActionPerformed

    private void id_penerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_penerimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_penerimaActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        tampilData_lembagapenerima();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampilData_lembagapenerima();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        tampilData_lembagapenerima();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
        tampilData_lembagapenerima();
    }//GEN-LAST:event_txt_searchKeyPressed

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
            java.util.logging.Logger.getLogger(Form_Lembaga_Penerima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Lembaga_Penerima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Lembaga_Penerima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Lembaga_Penerima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Lembaga_Penerima().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_penerima;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btsaran;
    private javax.swing.JTextField id_penerima;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton kosongkan;
    private javax.swing.JTextField nama_tempat;
    private javax.swing.JTextField nohp_penerima;
    private javax.swing.JTextField penanggung_jawab;
    private javax.swing.JTable table_penerima;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    // End of variables declaration//GEN-END:variables
}
