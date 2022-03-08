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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harip
 */

public class Form_Donatur extends javax.swing.JFrame {
   Statement st;
   Connection con;
   ResultSet rs;
   private DefaultTableModel model;
    
    /**
     * Creates new form Donatur
     */
    public Form_Donatur() {
        initComponents();
        Tanggal_sekarang();
        tampilData_donatur();
        auto_donatur();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable (false);
    }
    
    public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    

    public void kosongkan_donatur(){
          nama_donatur.setText(null);
          nohp_donatur.setText(null);
          jComboBox.getSelectedItem();
          email_donatur.setText(null);
          jComboBox.setSelectedItem(" -Jenis Kelamin-");
          alamat_donatur.setText(null);
          
      }
    
    private void tampilData_donatur(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Donatur");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Email");
        model.addColumn("Alamat");
        table_donatur.setModel(model);
        //untuk mengahapus baris setelah input
        int row = table_donatur.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        String cari = txt_cari.getText();
        String query = ("SELECT * FROM data_donatur WHERE id_donatur  LIKE '%"+cari+"%'");
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu = rslt.getString("id_donatur");
                 String dua  = rslt.getString("nama");
                 String tiga = rslt.getString("no_hp");
                 String empat= rslt.getString("jenis_kelamin");
                 String lima = rslt.getString("email");
                 String enam = rslt.getString("alamat");  
                  
                    
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima,enam};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_donatur.setModel(model);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
                
    }
    
    private void auto_donatur(){
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_donatur) as id_donatur FROM data_donatur";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.id_donatur.setText("DR-0"+(a+1));  
       }else{  
         this.id_donatur.setText("DR-0"+(a+1));  
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
            PreparedStatement ps = con.prepareStatement("UPDATE data_donatur SET id_donatur = ?,nama= ?,no_hp=?,jenis_kelamin=?,email=?,alamat=? WHERE id_donatur = '"+id_donatur.getText()+"'");
            ps.setString(1, id_donatur.getText());
            ps.setString(2, nama_donatur.getText());
            ps.setString(3, nohp_donatur.getText());
            ps.setString(4, (String) jComboBox.getSelectedItem());
            ps.setString(5, email_donatur.getText());
            ps.setString(6, alamat_donatur.getText());
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
        create_donatur = new javax.swing.JButton();
        delete_donatur = new javax.swing.JButton();
        update = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
        reset_donatur = new javax.swing.JButton();
        nama_donatur = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        nohp_donatur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        email_donatur = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat_donatur = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id_donatur = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_donatur = new javax.swing.JTable();
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

        jLabel3.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cari Data Donatur");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 430, 130, 30));

        create_donatur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        create_donatur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        create_donatur.setText("Simpan");
        create_donatur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_donaturActionPerformed(evt);
            }
        });
        getContentPane().add(create_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 120, 40));

        delete_donatur.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        delete_donatur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        delete_donatur.setText("Hapus");
        delete_donatur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_donaturActionPerformed(evt);
            }
        });
        getContentPane().add(delete_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 440, 110, 40));

        update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        update.setText("Edit");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 110, 40));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText(" Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        reset_donatur.setBackground(new java.awt.Color(255, 255, 255));
        reset_donatur.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reset_donatur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        reset_donatur.setText("Segarkan");
        reset_donatur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_donaturActionPerformed(evt);
            }
        });
        getContentPane().add(reset_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 130, -1));
        getContentPane().add(nama_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 240, 29));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel23.setText("Nama Donatur");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 130, 120, -1));

        jLabel24.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel24.setText("No HP");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 60, -1));
        getContentPane().add(nohp_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 180, 29));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setText("Jenis Kelamin");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 100, -1));

        jComboBox.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " -Jenis Kelamin-", "    Laki-Laki", "   Perempuan" }));
        getContentPane().add(jComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 150, 30));
        getContentPane().add(email_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 259, 190, 30));

        jLabel26.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel26.setText("Email");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, -1, -1));

        alamat_donatur.setColumns(20);
        alamat_donatur.setRows(5);
        jScrollPane2.setViewportView(alamat_donatur);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 300, 240, 120));

        jLabel27.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel27.setText("Alamat");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, 60, -1));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel13.setText("ID Donatur");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 90, -1));

        id_donatur.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_donatur.setEnabled(false);
        id_donatur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_donaturActionPerformed(evt);
            }
        });
        getContentPane().add(id_donatur, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 120, 30));

        table_donatur.setModel(new javax.swing.table.DefaultTableModel(
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
        table_donatur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_donaturMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_donatur);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 910, 160));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 460, 110, 30));

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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 460, -1, 30));

        jLabel4.setFont(new java.awt.Font("Forte", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Donatur");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 210, 30));

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Umum Master.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void create_donaturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_donaturActionPerformed
        // TODO add your handling code here:
         try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("insert into data_donatur(id_donatur,nama,no_hp,jenis_kelamin,email,alamat) values(?,?,?,?,?,?)");
            ps.setString(1, id_donatur.getText());
            ps.setString(2, nama_donatur.getText());
            ps.setString(3, nohp_donatur.getText());
            ps.setString(4, (String) jComboBox.getSelectedItem());
            ps.setString(5, email_donatur.getText());
            ps.setString(6, alamat_donatur.getText());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }
         tampilData_donatur();
//            kosongkan_donatur();
            auto_donatur();
    }//GEN-LAST:event_create_donaturActionPerformed

    private void delete_donaturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_donaturActionPerformed
        // TODO add your handling code here:

int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_donatur WHERE id_donatur='"+id_donatur.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
            tampilData_donatur();
            kosongkan_donatur();
            auto_donatur();
            

       }
    }//GEN-LAST:event_delete_donaturActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            perbarui();
        } catch (IOException ex) {
            Logger.getLogger(Form_Donatur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Donatur.class.getName()).log(Level.SEVERE, null, ex);
        }
        kosongkan_donatur();
        tampilData_donatur();
    }//GEN-LAST:event_updateActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampilData_donatur();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        tampilData_donatur();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
        tampilData_donatur();
        kosongkan_donatur();
    }//GEN-LAST:event_txt_searchKeyPressed

    private void reset_donaturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_donaturActionPerformed
        // TODO add your handling code here:
        kosongkan_donatur();
        auto_donatur();
    }//GEN-LAST:event_reset_donaturActionPerformed

    private void id_donaturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_donaturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_donaturActionPerformed

    private void table_donaturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_donaturMouseClicked
        // TODO add your handling code here:
         int baris = table_donatur.getSelectedRow();
        id_donatur.setText(table_donatur.getModel().getValueAt(baris, 0).toString());
        nama_donatur.setText(table_donatur.getModel().getValueAt(baris, 1).toString());
        nohp_donatur.setText(table_donatur.getModel().getValueAt(baris, 2).toString());
        jComboBox.setSelectedItem(table_donatur.getModel().getValueAt(baris, 3).toString());
        email_donatur.setText(table_donatur.getModel().getValueAt(baris, 4).toString());
        alamat_donatur.setText(table_donatur.getModel().getValueAt(baris, 5).toString());
    }//GEN-LAST:event_table_donaturMouseClicked

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
            java.util.logging.Logger.getLogger(Form_Donatur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Donatur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Donatur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Donatur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Donatur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_donatur;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton create_donatur;
    private javax.swing.JButton delete_donatur;
    private javax.swing.JTextField email_donatur;
    private javax.swing.JTextField id_donatur;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nama_donatur;
    private javax.swing.JTextField nohp_donatur;
    private javax.swing.JButton reset_donatur;
    private javax.swing.JTable table_donatur;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
