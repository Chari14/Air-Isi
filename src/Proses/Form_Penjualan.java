/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import Koneksi.Koneksi;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;






/**
 *
 * @author harip
 */
public class Form_Penjualan extends javax.swing.JFrame {
    Statement st;
    Connection con;
    ResultSet rs;
    String filepath;
    public DefaultTableModel model;

    /**
     * Creates new form Form_Penjualan
     */
    public Form_Penjualan() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        tampilTabel_pembeli();
        auto_penjualan();
        data_karyawan001();
        Tanggal_sekarang();

    }

    public void kosongkan_penjualan() {
        
        nama_penjual.setText(null);
        nohp_penjual.setText(null);
        jenis_galon.setSelectedItem("Naafie");
        alamat_penjual.setText(null);
        tanggal_pesanan.setDate(null);
        id_kurir.setText(null);
        nama_kurir.setText(null);
        txjumlahgalon.setText(null);

        txtotal.setText(null);
        txbayar.setText(null);
        txkembali.setText(null);
      

    }
    
     public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }

    private void data_karyawan001(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root", "");
            String SelectQuery = "SELECT * FROM data_karyawan WHERE id_karyawan";
            st = con.createStatement();
            rs = st.executeQuery(SelectQuery);
            while(rs.next()){
                Object[] row = new Object[5];
                row[0] = rs.getString("id_karyawan");
                row[1] = rs.getString("nama_karyawan");
                row[2] = rs.getString("no_hp");
                row[3] = rs.getString("jabatan");
                row[4] = rs.getString("alamat");
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }  
    
    
    private void auto_penjualan() {
        try {
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_penjualan) as id_penjualan FROM data_penjualan";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Long a = rs.getLong(1); //mengambil nilai tertinggi  
                if (a == 0) {
                    this.id_penjual.setText("IDP-0" + (a + 1));
                } else {
                    this.id_penjual.setText("IDP-0" + (a + 1));
                }

            }

            while (rs.next()) {

            }
            rs.close();
           // st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");
        }

    }

    private void tampilTabel_pembeli() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Penjualan");
        model.addColumn("Nam Pembeli");
        model.addColumn("No HP");
        model.addColumn("Jenis Air Galon");
        model.addColumn("Alamat");
        model.addColumn("Status Pembayaran");
        model.addColumn("Tanggal Pemesanan");
        model.addColumn("ID Kurir");
        model.addColumn("Nama Kurir");
        model.addColumn("Jumlah Galon");
//        model.addColumn("Harga Galon");
        model.addColumn("Total");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");
        table_penjualan.setModel(model);
        //untuk mengahapus baris setelah input
        int row = table_penjualan.getRowCount();
        for (int a = 0; a < row; a++) {
            model.removeRow(0);
        }
        String sql = "Select * from data_penjualan where id_penjualan like '%" + txt_cari.getText() + "%'";

        try {
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(sql);//menjalanakn query

            while (rslt.next()) {
                //menampung data sementara

                String satu = rslt.getString("id_penjualan");
                String dua = rslt.getString("nama_pembeli");
                String tiga = rslt.getString("nohp_pembeli");
                String empat = rslt.getString("jenis_galon");
                String lima = rslt.getString("alamat_pembeli");
                String enam  = rslt.getString("status_pembayaran");
                String tujuh = rslt.getString("tgl_pemesanan");
                String lapan = rslt.getString("id_kurir");
                String sembilan = rslt.getString("nama_kurir");
                String sepuluh = rslt.getString("jumlah_galon");
//                String sebelas = rslt.getString("harga");
                String sebelas = rslt.getString("total");
                String duabelas = rslt.getString("bayar");
                String tigabelas = rslt.getString("kembali");

                //masukan semua data kedalam array
                String[] data = {satu, dua, tiga, empat, lima, enam, tujuh, lapan, sembilan, sepuluh, sebelas, duabelas, tigabelas};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model.addRow(data);
            }
            //mengeset nilai yang ditampung agar muncul di table
            table_penjualan.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

  

//    public void simpan() {
//        try {
//            String penanggalan = "yyyy-MM-dd";
//            SimpleDateFormat fm = new SimpleDateFormat(penanggalan);
//            String tanggal = fm.format(tanggal_pesanan.getDate());
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang", "root", "");
//            PreparedStatement ps = con.prepareStatement("insert into data_penjualan(id_penjualan,nama_pembeli,nohp_pembeli,jenis_galon,alamat_pembeli,status_pembayaran,tgl_pemesanan,id_kurir,nama_kurir,jumlah_galon,harga,total,bayar,kembali) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            ps.setString(1, id_penjual.getText());
//            ps.setString(2, nama_penjual.getText());
//            ps.setString(3, nohp_penjual.getText());
//            ps.setString(4, (String) jenis_galon.getSelectedItem());
//            ps.setString(5, alamat_penjual.getText());
//            ps.setString(6, (String) txstatus.getSelectedItem());
//            ps.setString(7, tanggal);
//            ps.setString(8, id_kurir.getText());
//            ps.setString(9, nama_kurir.getText());
//            ps.setString(10, txjumlahgalon.getText());
//            ps.setString(11, txharga.getText());
//            ps.setString(12, txtotal.getText());
//            ps.setString(13, txbayar.getText());
//            ps.setString(14, txkembali.getText());
//            ps.executeUpdate();
//            ps.close();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
//        }
//        
//   
//    }
    
    
                       private void simpan(){
           
            try{ 
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tanggal_pesanan.getDate()); 
    
         ////   for(int i=0;i<t;i++)    
            {
         //   String id_donasi=tabel_kas.getValueAt(i, 0).toString();
          //  String kas_masuk=tabel_kas.getValueAt(i, 1).toString();
         //   String update_masuk= tabel_kas.getValueAt(i, 2).toString();
//            int subtot= Integer.parseInt(tabeltransaksi.getValueAt(i, 5).toString());
         
            String sql ="insert into data_penjualan values('"+id_penjual.getText()+"','"+nama_penjual.getText()+"','"+nohp_penjual.getText()+"','"+jenis_galon.getSelectedItem()+"','"+alamat_penjual.getText()+"','"+txstatus.getSelectedItem()+"','"+tanggal+"','"+id_kurir.getText()+"','"+nama_kurir.getText()+"','"+txjumlahgalon.getText()+"','"+txharga.getText()+"','"+txtotal.getText()+"','"+txbayar.getText()+"','"+txkembali.getText()+ "')"; 
            
             st.executeUpdate(sql);
             
            }   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Penyimpanan Uang Kas Berhasil");
            
        }
                    }

    
    private void perbarui(){
        try {
            
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tanggal_pesanan.getDate());
            con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_penjualan SET id_penjualan =?,nama_pembeli=?,nohp_pembeli=?,jenis_galon=?,alamat_pembeli=?,status_pembayaran=?,tgl_pemesanan=?,id_kurir=?,nama_kurir=?,jumlah_galon=?,harga=?,total=?,bayar=?,kembali=? WHERE id_penjualan = '"+id_penjual.getText()+"'");
            ps.setString(1, id_penjual.getText());
            ps.setString(2, nama_penjual.getText());
            ps.setString(3, nohp_penjual.getText());
            ps.setString(4, (String) jenis_galon.getSelectedItem());
            ps.setString(5, alamat_penjual.getText());
            ps.setString(6, (String) txstatus.getSelectedItem());
            ps.setString(7, tanggal);
            ps.setString(8, id_kurir.getText());
            ps.setString(9, nama_kurir.getText());
            ps.setString(10, txjumlahgalon.getText());
            ps.setString(11, txharga.getText());
            ps.setString(12, txtotal.getText());
            ps.setString(13, txbayar.getText());
            ps.setString(14, txkembali.getText());
            ps.executeUpdate();
            ps.close();
            
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        id_penjual = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        nama_penjual = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        nohp_penjual = new javax.swing.JTextField();
        jenis_galon = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat_penjual = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tanggal_pesanan = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        id_kurir = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        bthapus = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txkembali = new javax.swing.JLabel();
        txtotal = new javax.swing.JLabel();
        txbayar = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txharga = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_penjualan = new javax.swing.JTable();
        update_donasi = new javax.swing.JButton();
        btcetak = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txstatus = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        nama_kurir = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txjumlahgalon = new javax.swing.JTextField();
        btmasuk = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btsaran = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btmenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 720));
        setPreferredSize(new java.awt.Dimension(1300, 710));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_penjual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(id_penjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, 90, 29));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel13.setText("ID Penjualan");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, 100, -1));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel23.setText("Nama Pembeli");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 120, -1));
        getContentPane().add(nama_penjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 190, 29));

        jLabel24.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel24.setText("No HP");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 60, -1));
        getContentPane().add(nohp_penjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 166, 29));

        jenis_galon.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jenis_galon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Naafie", "AQUA", "Le Minerale", "VIT", "OASIS" }));
        getContentPane().add(jenis_galon, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 130, -1));

        jLabel27.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel27.setText("Alamat");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, 60, -1));

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel2.setText("Status Bayar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 110, -1));

        alamat_penjual.setColumns(20);
        alamat_penjual.setLineWrap(true);
        alamat_penjual.setRows(5);
        alamat_penjual.setWrapStyleWord(true);
        alamat_penjual.setFocusCycleRoot(true);
        alamat_penjual.setFocusTraversalPolicyProvider(true);
        jScrollPane2.setViewportView(alamat_penjual);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 330, 210, 100));

        jLabel11.setFont(new java.awt.Font("Forte", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Input Kurir");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 140, 140, 30));

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel3.setText("Tanggal Pesanan");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 130, -1));

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel4.setText("ID Kurir");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, -1, 20));

        tanggal_pesanan.setDateFormatString("yyyy-MM-dd");
        tanggal_pesanan.setMaxSelectableDate(new java.util.Date(253370743316000L));
        getContentPane().add(tanggal_pesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 290, 170, 30));

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel5.setText("Nama Kurir");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 250, -1, 20));

        id_kurir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(id_kurir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 210, 80, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        jButton6.setText("Bersihkan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, -1, 30));

        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/save.png"))); // NOI18N
        btsimpan.setText("Simpan");
        btsimpan.setEnabled(false);
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 110, 40));

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/delete.png"))); // NOI18N
        bthapus.setText("Hapus");
        bthapus.setEnabled(false);
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        getContentPane().add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 440, 110, 40));

        jLabel8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel8.setText("Harga Galon");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 100, -1));

        jLabel9.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel9.setText("Jumlah Galon");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 110, -1));

        txkembali.setFont(new java.awt.Font("Gill Sans MT", 0, 24)); // NOI18N
        txkembali.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 580, 140, 70));

        txtotal.setFont(new java.awt.Font("Gill Sans MT", 1, 17)); // NOI18N
        txtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 120, 30));

        txbayar.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        txbayar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbayarActionPerformed(evt);
            }
        });
        txbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txbayarKeyPressed(evt);
            }
        });
        getContentPane().add(txbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, 130, 30));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 490, 110, 30));

        jLabel6.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel6.setText("Cari");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 490, 30, 30));

        jButton7.setText("Cari ID");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 210, 80, 30));

        txharga.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        txharga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txharga.setText("6000");
        getContentPane().add(txharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 120, 30));

        jLabel14.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("ID Penjualan");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, -1, -1));

        btnNew.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/add.png"))); // NOI18N
        btnNew.setText("Baru");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 110, 40));

        btncancel.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/file.png"))); // NOI18N
        btncancel.setText("Batal  "); // NOI18N
        btncancel.setActionCommand("Batal \nPesanan");
        btncancel.setEnabled(false);
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        getContentPane().add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 120, 40));

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
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        table_penjualan.setBackground(new java.awt.Color(153, 153, 153));
        table_penjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        table_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_penjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_penjualan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, 700, 150));

        update_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        update_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refresh.png"))); // NOI18N
        update_donasi.setText("Edit");
        update_donasi.setEnabled(false);
        update_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(update_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 440, 110, 40));

        btcetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/printer.png"))); // NOI18N
        btcetak.setText("Cetak Struk");
        btcetak.setEnabled(false);
        btcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcetakActionPerformed(evt);
            }
        });
        getContentPane().add(btcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, -1, 40));

        jLabel15.setFont(new java.awt.Font("Gill Sans MT", 0, 19)); // NOI18N
        jLabel15.setText("Jenis Air Galon");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 120, -1));

        txstatus.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Transfer" }));
        txstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txstatusActionPerformed(evt);
            }
        });
        getContentPane().add(txstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 100, 30));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 110, 40));

        jLabel16.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Tabel Penjualan");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 500, 210, 20));

        nama_kurir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nama_kurir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(nama_kurir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 250, 120, 30));

        jLabel17.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Contoh ID : KKR-01");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 190, -1, 20));
        getContentPane().add(txjumlahgalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 120, 30));

        btmasuk.setBackground(new java.awt.Color(153, 153, 153));
        btmasuk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btmasuk.setText("Hitung Harga Galon");
        btmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmasukActionPerformed(evt);
            }
        });
        getContentPane().add(btmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 150, 30));

        jLabel19.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 204)), javax.swing.BorderFactory.createTitledBorder(null, "Kembalian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24)))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 550, 220, 120));

        jLabel20.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 204)), javax.swing.BorderFactory.createTitledBorder(null, "BAYAR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 150, 60));

        jLabel22.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 204)), javax.swing.BorderFactory.createTitledBorder(null, "TOTAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14)))); // NOI18N
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, 150, 60));

        jLabel12.setFont(new java.awt.Font("Forte", 0, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Penjualan");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 210, 30));

        jLabel21.setFont(new java.awt.Font("Forte", 0, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Input Data penjualan");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 250, 30));

        jLabel25.setFont(new java.awt.Font("Forte", 0, 24)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Input Jumlah Galon");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 250, 30));

        btsaran.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        btsaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        btsaran.setText("Saran");
        btsaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaranActionPerformed(evt);
            }
        });
        getContentPane().add(btsaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 110, 40));

        jButton12.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/report.png"))); // NOI18N
        jButton12.setText("Ebout");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 110, 40));

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

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image umum/Menu Umum Master.jpg"))); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
          simpan();
          tampilTabel_pembeli();
//          auto_penjualan();
//          kosongkan_penjualan();
          
   btcetak.setEnabled(true);
    }//GEN-LAST:event_btsimpanActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        kosongkan_penjualan();
        auto_penjualan();
        btncancel.setEnabled(false);
        btsimpan.setEnabled(false);
        btnNew.setEnabled(true);
        bthapus.setEnabled(false);
        btcetak.setEnabled(false);
        update_donasi.setEnabled(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int total = Integer.parseInt(txtotal.getText());
            int bayar = Integer.parseInt(txbayar.getText());
            if (bayar < total) {
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                txbayar.requestFocus();
            } else {
                int kembali = bayar - total;
//                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                txkembali.requestFocus();
                txkembali.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_txbayarKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
            try{
                       
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

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
        
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete record Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            String sql=("DELETE FROM data_penjualan WHERE id_penjualan='"+id_penjual.getText()+"'"); 
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
        tampilTabel_pembeli();
        kosongkan_penjualan();
       }
    }//GEN-LAST:event_bthapusActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
        try{
                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root", "");
                       rs= st.executeQuery("SELECT * FROM data_karyawan WHERE id_karyawan LIKE '"+ id_kurir.getText() +"'");
                       
                       
                       if (rs.next () )
                       {
                            id_kurir.setText(rs.getString(0));
                            nama_kurir.setText(rs.getString(1));
                           
                       }else{
                           JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
                       }
                       
                   }catch (HeadlessException | SQLException ex){
                       System.out.println("error : "+ex);
                   }
        
    }//GEN-LAST:event_jButton7KeyPressed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        btsimpan.setEnabled(true);
        btncancel.setEnabled(true);
        btnNew.setEnabled(false);
//        btcetak.setEnabled(true);
        bthapus.setEnabled(false);
        update_donasi.setEnabled(false);
        
       
    }//GEN-LAST:event_btnNewActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        btncancel.setEnabled(false);
        btsimpan.setEnabled(false);
        btnNew.setEnabled(true);
        bthapus.setEnabled(false);
        btcetak.setEnabled(false);
        update_donasi.setEnabled(false);
        kosongkan_penjualan();
    }//GEN-LAST:event_btncancelActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //          String ObjButtons[] = {"Yes","No"};
        // int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        // if(pilihan == 0){
            new Login.Form_Login().setVisible(true);
            dispose();
            //        System.exit(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void table_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_penjualanMouseClicked
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel) table_penjualan.getModel();
        int baris = table_penjualan.getSelectedRow();
        
        id_penjual.setText(table_penjualan.getModel().getValueAt(baris, 0).toString());
        
        nama_penjual.setText(table_penjualan.getModel().getValueAt(baris, 1).toString());
        
        nohp_penjual.setText(table_penjualan.getModel().getValueAt(baris, 2).toString());
        
        jenis_galon.setSelectedItem(table_penjualan.getModel().getValueAt(baris, 3).toString());
        
        alamat_penjual.setText(table_penjualan.getModel().getValueAt(baris, 4).toString());
        
        txstatus.setSelectedItem(table_penjualan.getModel().getValueAt(baris, 5).toString());
        
        Date date = null;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse((String)table_penjualan.getValueAt(baris,6));
       } catch (ParseException ex) {
           Logger.getLogger(Form_Penjualan.class.getName()).log(Level.SEVERE, null, ex);
       }
        tanggal_pesanan.setDate(date);
        
        id_kurir.setText(table_penjualan.getModel().getValueAt(baris, 7).toString());
        
        nama_kurir.setText(table_penjualan.getModel().getValueAt(baris, 8).toString());
        
        txjumlahgalon.setText(table_penjualan.getModel().getValueAt(baris, 9).toString());
        
        txtotal.setText(table_penjualan.getModel().getValueAt(baris, 10).toString());
        
        txbayar.setText(table_penjualan.getModel().getValueAt(baris, 11).toString());
        
        txkembali.setText(table_penjualan.getModel().getValueAt(baris, 12).toString());
        
        
        update_donasi.setEnabled(true);
        bthapus.setEnabled(true);
        btsimpan.setEnabled(false);
        btcetak.setEnabled(true);
    }//GEN-LAST:event_table_penjualanMouseClicked

    private void update_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_donasiActionPerformed
        // TODO add your handling code here:

        perbarui();
        tampilTabel_pembeli();
//        kosongkan_penjualan();
  btcetak.setEnabled(true);
  btsimpan.setEnabled(false);
    }//GEN-LAST:event_update_donasiActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txstatusActionPerformed

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

            int jml=Integer.parseInt(txjumlahgalon.getText());
            int harga=Integer.parseInt(txharga.getText());
            {
                //        if(jml>jml){
                    //            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
                    //        }else if (jml<=100000){

                    int totbay=harga*jml;
                    txtotal.setText(Integer.toString(totbay));

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

    }//GEN-LAST:event_btmasukActionPerformed

    private void btcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcetakActionPerformed
        // TODO add your handling code here:
        
          try {
            String reportName = "src/Report/Nota_Penjualan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("id_penjualan",this.txt_cari.getText());
            parameter.put("id_penjualan",this.id_penjual.getText());
            JasperPrint jPrint = JasperFillManager.fillReport(reportName, parameter, Koneksi.configDB());
            JasperViewer.viewReport(jPrint, false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
          
    }//GEN-LAST:event_btcetakActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampilTabel_pembeli();
        btcetak.setEnabled(true);
    }//GEN-LAST:event_txt_cariKeyPressed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txbayarActionPerformed

    private void btsaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaranActionPerformed
        // TODO add your handling code here:
        Form_Ebout_FeedBack.Form_FeedBack frm = new Form_Ebout_FeedBack.Form_FeedBack(this, rootPaneCheckingEnabled);
        frm.setTitle("Saran");
        frm.setVisible(true);
    }//GEN-LAST:event_btsaranActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        new Form_Ebout_FeedBack.Form_Ebout().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_penjual;
    private javax.swing.JButton btcetak;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btmasuk;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton btsimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField id_kurir;
    private javax.swing.JTextField id_penjual;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jenis_galon;
    private javax.swing.JTextField nama_kurir;
    private javax.swing.JTextField nama_penjual;
    private javax.swing.JTextField nohp_penjual;
    private javax.swing.JTable table_penjualan;
    private javax.swing.JLabel tanggal;
    private com.toedter.calendar.JDateChooser tanggal_pesanan;
    private javax.swing.JTextField txbayar;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txjumlahgalon;
    private javax.swing.JLabel txkembali;
    private javax.swing.JComboBox<String> txstatus;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JLabel txtotal;
    private javax.swing.JButton update_donasi;
    // End of variables declaration//GEN-END:variables
}
