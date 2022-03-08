/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;


import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author harip
 */
public class Form_Admin extends javax.swing.JFrame {
    private DefaultTableModel DftTblModel_user;
    private String SQL;

    /**
     * Creates new form Form_Admin
     */
    public Form_Admin(){
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable (false);
        TampilData();
        kosongkan_admin();
        Tanggal_sekarang();
       
    }
    
    
    public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    
    public void kosongkan_admin(){
          txtiduser.setText(null);
          txtusername.setText(null);
          txtpassword.setText(null);
          txtnamauser.setText(null);
          cblevel.setSelectedItem("Admin");
          
      }
    
     public void TampilData() {
        DftTblModel_user = new DefaultTableModel();
        DftTblModel_user.addColumn("ID ADMIN");
        DftTblModel_user.addColumn("USERNAME");
        DftTblModel_user.addColumn("PASSWORD");
        DftTblModel_user.addColumn("NAMA USER");
        DftTblModel_user.addColumn("LEVEL");
        tbuser.setModel(DftTblModel_user);
        Connection conn = null;
        try {
            conn = Koneksi.configDB();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement stmt = conn.createStatement();
            String cari = txt_cari.getText();
            SQL = ("SELECT * FROM data_admin WHERE id_admin  LIKE '%"+cari+"%'");
            ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_user.addRow(new Object[]{
                    res.getString("id_admin"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("nama_user"),
                    res.getString("level")
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     
      private void kodeOtomatis() {
        try {
            int akhir = 0;
            Connection conn = Koneksi.configDB();
            String query = "SELECT MAX(RIGHT(id_admin,1)) AS jml FROM data_admin";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                akhir = Integer.parseInt(rs.getObject("jml").toString());
            }
            if (akhir == 0) {
                txtiduser.setText("ADN-");
            } else {
                String no = String.valueOf(akhir + 1);
                int noLong = no.length();
                for (int a = 0; a < 3 - noLong; a++) {
                    no = "0" + no;
                }
                txtiduser.setText("ADN-" + no);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      
      private void Update(){
          try {
            Connection conn = Koneksi.configDB();
            PreparedStatement stmt = conn.prepareStatement("update data_admin set username=?, password=?, nama_user=?, level=? where id_admin=?");
            stmt.setString(1, txtusername.getText());
            stmt.setString(2, txtpassword.getText());
            stmt.setString(3, txtnamauser.getText());
            stmt.setString(4, cblevel.getSelectedItem().toString());
            stmt.setString(5, txtiduser.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            TampilData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        btnupdate.setEnabled(false);
        btnsimpan.setEnabled(false);
        btnhapus.setEnabled(false);
        btbaru.setEnabled(true);
      }
      

      
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btnhapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbuser = new javax.swing.JTable();
        tanggal = new javax.swing.JLabel();
        btnsimpan = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        reset_donasi = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btexit = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        cblevel = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        btbaru = new javax.swing.JButton();
        txtiduser = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        txtnamauser = new javax.swing.JTextField();
        btnupdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_search = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btbatal = new javax.swing.JButton();
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

        jLabel3.setFont(new java.awt.Font("Forte", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Input data Admin");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 220, 30));

        btnhapus.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete-friend.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setEnabled(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 400, 110, 40));

        tbuser.setBackground(new java.awt.Color(204, 204, 204));
        tbuser.setForeground(new java.awt.Color(51, 51, 51));
        tbuser.setModel(new javax.swing.table.DefaultTableModel(
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
        tbuser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbuser.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tbuser.setSurrendersFocusOnKeystroke(true);
        tbuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbuserMouseClicked(evt);
            }
        });
        tbuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbuserKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbuser);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 810, 170));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText(" Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 130, 30));

        btnsimpan.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setEnabled(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 110, 40));

        jLabel14.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel14.setText("Level");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 50, -1));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel23.setText("Password");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 90, -1));

        reset_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reset_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        reset_donasi.setText("Segarkan");
        reset_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(reset_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 120, 30));

        jButton8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/arrow.png"))); // NOI18N
        jButton8.setText("LogOut");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 630, 130, 40));

        btexit.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        btexit.setText("Exit");
        btexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btexitActionPerformed(evt);
            }
        });
        getContentPane().add(btexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 110, 40));
        getContentPane().add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 140, 30));

        jLabel15.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel15.setText("Username");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 80, -1));

        cblevel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cblevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Kurir", "Kepala Toko" }));
        getContentPane().add(cblevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 120, 30));

        jLabel16.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel16.setText("Nama User");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 90, -1));

        btbaru.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btbaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/add.png"))); // NOI18N
        btbaru.setText("Baru");
        btbaru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btbaruMouseClicked(evt);
            }
        });
        btbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbaruActionPerformed(evt);
            }
        });
        getContentPane().add(btbaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 300, 110, 40));

        txtiduser.setBackground(new java.awt.Color(0, 174, 255));
        txtiduser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtiduser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtiduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, 150, 60));
        getContentPane().add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 150, 30));
        getContentPane().add(txtnamauser, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 150, 30));

        btnupdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        btnupdate.setText("Edit");
        btnupdate.setEnabled(false);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 400, 100, 40));

        jLabel1.setBackground(new java.awt.Color(0, 188, 203));
        jLabel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.MatteBorder(null), javax.swing.BorderFactory.createTitledBorder(null, "ID Admin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Forte", 0, 18)))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, 190, 120));

        jPanel2.setBackground(new java.awt.Color(0, 174, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 80, 30));

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
        jPanel2.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 850, 240));

        jLabel4.setFont(new java.awt.Font("Forte", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Admin");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 330, 30));

        jLabel5.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cari Data Admin");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 420, 120, 30));

        btbatal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/cancel.png"))); // NOI18N
        btbatal.setText("Batal");
        btbatal.setEnabled(false);
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });
        getContentPane().add(btbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 300, 100, 40));

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
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 720));
        jLabel2.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        try {
            conn = Koneksi.configDB();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("delete from data_admin where id_admin ='" + txtiduser.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                TampilData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
        btbaru.setEnabled(true);
        btnsimpan.setEnabled(false);
        btnhapus.setEnabled(false);
        btnupdate.setEnabled(false);
        btnupdate.setEnabled(false);
    }//GEN-LAST:event_btnhapusActionPerformed

    private void reset_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_donasiActionPerformed
        // TODO add your handling code here:
          kosongkan_admin();
    }//GEN-LAST:event_reset_donasiActionPerformed

    private void tbuserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbuserKeyPressed
        // TODO add your handling code here:
  
    }//GEN-LAST:event_tbuserKeyPressed

    private void tbuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbuserMouseClicked
        // TODO add your handling code here:
        int baris = tbuser.getSelectedRow();
        txtiduser.setText(DftTblModel_user.getValueAt(baris, 0).toString());
        txtusername.setText(DftTblModel_user.getValueAt(baris, 1).toString());
        txtpassword.setText(DftTblModel_user.getValueAt(baris, 2).toString());
        txtnamauser.setText(DftTblModel_user.getValueAt(baris, 3).toString());
        cblevel.setSelectedItem(DftTblModel_user.getValueAt(baris, 4).toString());

        btbaru.setEnabled(true);
        btnhapus.setEnabled(true);
        btnupdate.setEnabled(true);
        btnsimpan.setEnabled(false);
          
    }//GEN-LAST:event_tbuserMouseClicked

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement stmt = conn.prepareStatement("insert into data_admin(id_admin, username, password, nama_user, level) values(?,?,?,?,?)");
            stmt.setString(1, txtiduser.getText());
            stmt.setString(2, txtusername.getText());
            stmt.setString(3, txtpassword.getText());
            stmt.setString(4, txtnamauser.getText());
            stmt.setString(5, cblevel.getSelectedItem().toString());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            TampilData();
            kodeOtomatis();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        btbaru.setEnabled(true);
        btnsimpan.setEnabled(true);
        btnhapus.setEnabled(false);
        btnupdate.setEnabled(false);
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btexitActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_btexitActionPerformed

    private void btbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbaruActionPerformed
        // TODO add your handling code here:
        
        try {
            int akhir = 0;
            Connection conn = Koneksi.configDB();
            String query = "SELECT MAX(RIGHT(id_admin,1)) AS jml FROM data_admin";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                akhir = Integer.parseInt(rs.getObject("jml").toString());
            }
            if (akhir == 0) {
                txtiduser.setText("ADN-");
            } else {
                String no = String.valueOf(akhir + 1);
                int noLong = no.length();
                for (int a = 0; a < 3 - noLong; a++) {
                    no = "0" + no;
                }
                txtiduser.setText("ADN-" + no);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        kodeOtomatis();
        txtusername.requestFocus();
        btnsimpan.setEnabled(true);
        btnupdate.setEnabled(false);
        btnhapus.setEnabled(false);
        btnupdate.setEnabled(false);
        btbaru.setEnabled(false);
        btbatal.setEnabled(true);
//       
    }//GEN-LAST:event_btbaruActionPerformed

    private void btbaruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbaruMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btbaruMouseClicked

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
        //        tampilTabel_admin();

    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        //        tampilTabel_admin();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
                TampilData();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        //        tampilTabel_admin();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
       btbaru.setEnabled(true);
       btnsimpan.setEnabled(false);
       btnupdate.setEnabled(false);
       btnhapus.setEnabled(false);
       btbatal.setEnabled(false);
       kosongkan_admin();
    }//GEN-LAST:event_btbatalActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbaru;
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btexit;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btsaran;
    private javax.swing.JComboBox<String> cblevel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reset_donasi;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTable tbuser;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    private javax.swing.JTextField txtiduser;
    private javax.swing.JTextField txtnamauser;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
