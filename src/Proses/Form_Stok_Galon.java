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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harip
 */
public class Form_Stok_Galon extends javax.swing.JFrame {
   Statement st;
   Connection con;
   ResultSet rs;
   public DefaultTableModel model;
    /**
     * Creates new form Form_Stok_Galon
     */
    public Form_Stok_Galon() {
        initComponents();
        tampilTabel_stok();
        open_db();
        auto_stok_galon_masuk();
        auto_stok_galon();
        Tanggal_sekarang();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
    }

    
    public void Tanggal_sekarang(){
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat ("yyyy-MM-dd") ;
        tanggal.setText(kal.format(sekarang));
    }
    
    public void kosongkan_stok_masuk(){
          idstok_lama.setText(null);
//          id_stok.setText(null);
          tgl_masuk.setDate(null);
          jumlah_galon.setText("0");
//          harga_galon.setText(null);
            stok_lama.setText("0");
            update_galon.setText("0");
          
      }
    
     private void tampilTabel_stok(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Stok Masuk");
        model.addColumn("ID Stok Galon");
        model.addColumn("Tgl Stok Masuk");
        model.addColumn("Jumlah Galon Masuk");
        model.addColumn("Harga Galon");
        model.addColumn("ID Stok Lama");
        model.addColumn("Tambah Galon");  
        model.addColumn("Stok Galon ");
        tabel_stok.setModel(model);
        //untuk mengahapus baris setelah input
        int row = tabel_stok.getRowCount();
         for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        String cari = txt_cari.getText();
        String query = ("SELECT * FROM data_stok WHERE id_stok  LIKE '%"+cari+"%'" );
        
        try{
            Connection connect = Koneksi.configDB();//memanggil koneksi
            java.sql.Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                 String satu = rslt.getString("id_stok_masuk");
                 String dua = rslt.getString("id_stok");
                 String tiga  = rslt.getString("tgl_stok_masuk");
                 String empat = rslt.getString("jumlah_galon_masuk");
                 String lima = rslt.getString("harga_galon");
                 String enam = rslt.getString("id_stok_lama");
                 String tujuh = rslt.getString("jml");
                 String delapan = rslt.getString("stok_galon");
                 
                  
                    
                    
                //masukan semua data kedalam array
                String[] data = {satu,dua,tiga,empat,lima,enam,tujuh,delapan};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                model.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                tabel_stok.setModel(model);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
     
     
     private void auto_stok_galon_masuk(){
       try{
            con = Koneksi.configDB();
            String sql = "SELECT COUNT(id_stok) as id_stok FROM data_stok";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.idstok_masuk.setText("STK-0"+(a+1));  
       }else{  
         this.idstok_masuk.setText("STK-0"+(a+1));  
       }  
            
        }

     while(rs.next()){  
    
   }  
   rs.close(); }  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan");  
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
       
           private void auto_stok_galon(){
        try {
            open_db();
            String sql="select right (id_stok,2)+1 from data_stok";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<2){
                    no="0"+no;
                    id_stok.setText("STG-"+no);
                    }
                }
            else
            {
                id_stok.setText("STG-01"); 
              
            }
        } catch (Exception e) 
        {
        }
           }
     
     public void simpan(){
        try{
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tgl_masuk.getDate());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("insert into data_stok(id_stok_masuk,id_stok,tgl_stok_masuk,jumlah_galon_masuk,harga_galon,id_stok_lama,jml,stok_galon) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, idstok_masuk.getText());
            ps.setString(2, id_stok.getText());
            ps.setString(3, tanggal);
            ps.setString(4, jumlah_galon.getText());
            ps.setString(5, harga_galon.getText());
            ps.setString(6, idstok_lama.getText());
            ps.setString(7, stok_lama.getText());
            ps.setString(8, update_galon.getText());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Data Tidak Lengkap! Pastikan Semua Data Pada Form Sudah Terisi Dan/Atau Masukan Ulang Berkas Pengajuan");
        }      
     }

//            private void simpan(){
//            try{ 
//            String penanggalan = "yyyy-MM-dd";
//            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
//            String tanggal = fm.format(tgl_masuk.getDate()); 
//    
//         ////   for(int i=0;i<t;i++)    
//         {
//         //   String id_donasi=tabel_kas.getValueAt(i, 0).toString();
//          //  String kas_masuk=tabel_kas.getValueAt(i, 1).toString();
//         //   String update_masuk= tabel_kas.getValueAt(i, 2).toString();
////            int subtot= Integer.parseInt(tabeltransaksi.getValueAt(i, 5).toString());
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
//            String sql ="insert into data_stok values('"+idstok_masuk.getText()+"','"+id_stok.getText()+"','"+tanggal+"','"+jumlah_galon.getText()+"','"+harga_galon.getText()+"','"+idstok_lama.getText()+"','"+stok_lama.getText()+"','"+update_galon.getText()+ "')"; 
//            
//             st.executeUpdate(sql);
//             
//            }   
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Penyimpanan Uang Kas Berhasil");
//            
//        }
//                    }

    
    
    private void perbarui(){
        try {
            
            String penanggalan = "yyyy-MM-dd";
            SimpleDateFormat fm= new SimpleDateFormat(penanggalan);
            String tanggal = fm.format(tgl_masuk.getDate());
            con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            PreparedStatement ps = con.prepareStatement("UPDATE data_stok SET id_stok_masuk=?,id_stok =?,tgl_stok_masuk=?,jumlah_galon_masuk=?,harga_galon=?,id_stok_lama=?,jml=?,stok_galon=? WHERE id_stok_masuk = '"+idstok_masuk.getText()+"'");
            ps.setString(1, idstok_masuk.getText());
            ps.setString(2, id_stok.getText());
            ps.setString(3, tanggal);
            ps.setString(4, jumlah_galon.getText());
            ps.setString(5, harga_galon.getText());
            ps.setString(6, idstok_lama.getText());
            ps.setString(7, stok_lama.getText());
            ps.setString(8, update_galon.getText());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_stok = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        harga_galon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id_stok = new javax.swing.JTextField();
        tgl_masuk = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        reset_donasi = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jumlah_galon = new javax.swing.JTextField();
        update_galon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        stok_lama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        btmasuk = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        idstok_lama = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        idstok_masuk = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        caridonasi = new javax.swing.JButton();
        txt_search = new javax.swing.JButton();
        btbaru = new javax.swing.JButton();
        btbatal = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
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
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_stok.setBackground(new java.awt.Color(153, 153, 153));
        tabel_stok.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_stokMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_stok);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 850, 220));

        jLabel23.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel23.setText("Tanggal Masuk");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 110, -1));

        harga_galon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        harga_galon.setText(" 8000");
        harga_galon.setDoubleBuffered(true);
        harga_galon.setDragEnabled(true);
        harga_galon.setFocusCycleRoot(true);
        harga_galon.setName(""); // NOI18N
        harga_galon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harga_galonActionPerformed(evt);
            }
        });
        getContentPane().add(harga_galon, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 70, 30));

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel3.setText("Stok Lama");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, 100, 20));

        jLabel13.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel13.setText("ID Stok lama");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, 100, -1));
        getContentPane().add(id_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 90, 30));
        getContentPane().add(tgl_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 200, 30));

        jLabel11.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Setok Galon Lama di Perbaharui");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 80, 270, 40));

        reset_donasi.setBackground(new java.awt.Color(255, 255, 255));
        reset_donasi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reset_donasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/refreshh.png"))); // NOI18N
        reset_donasi.setText("Bersihkan");
        reset_donasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_donasiActionPerformed(evt);
            }
        });
        getContentPane().add(reset_donasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 120, 30));

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/calendar.png"))); // NOI18N
        tanggal.setText("Tanggal");
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

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
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 380, 120, 30));

        btnsimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setEnabled(false);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 90, 30));

        btnedit.setBackground(new java.awt.Color(255, 255, 255));
        btnedit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnedit.setText("Edit");
        btnedit.setEnabled(false);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        getContentPane().add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, 90, 30));

        btnhapus.setBackground(new java.awt.Color(255, 255, 255));
        btnhapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setEnabled(false);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 370, 90, 30));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel1.setText("Update Galon");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 110, -1));

        jumlah_galon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_galonActionPerformed(evt);
            }
        });
        getContentPane().add(jumlah_galon, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 110, 30));
        getContentPane().add(update_galon, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 270, 110, 30));

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel4.setText("Harga Galon");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, -1, -1));
        getContentPane().add(stok_lama, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, 90, 30));

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel5.setText("Jumlah Galon Masuk");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 150, 20));

        jButton8.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/arrow.png"))); // NOI18N
        jButton8.setText("LogOut");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, 130, 40));

        btmasuk.setBackground(new java.awt.Color(153, 153, 153));
        btmasuk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btmasuk.setText("Hitung Galon Masuk");
        btmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmasukActionPerformed(evt);
            }
        });
        getContentPane().add(btmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 230, 160, 30));

        jButton3.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/exit.png"))); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 110, 40));

        jLabel14.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel14.setText("ID Stok Masuk");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 110, -1));

        idstok_lama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idstok_lamaActionPerformed(evt);
            }
        });
        idstok_lama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idstok_lamaKeyPressed(evt);
            }
        });
        getContentPane().add(idstok_lama, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 190, 80, 30));

        jLabel15.setFont(new java.awt.Font("Gill Sans MT", 0, 17)); // NOI18N
        jLabel15.setText("ID Stok Galon");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 110, -1));
        getContentPane().add(idstok_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 90, 30));

        jLabel12.setFont(new java.awt.Font("Forte", 0, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Setok Galon");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 210, 40));

        jLabel16.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Setok Galon Masuk");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 210, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("ID Stok Galon Masuk");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("ID Stok Galon Lama");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 170, -1, -1));

        caridonasi.setText("Cari");
        caridonasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caridonasiActionPerformed(evt);
            }
        });
        getContentPane().add(caridonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 190, 60, 30));

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
        getContentPane().add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 380, -1, 30));

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
        getContentPane().add(btbaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 40));

        btbatal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Image 32pixel/cancel.png"))); // NOI18N
        btbatal.setText("Batal");
        btbatal.setEnabled(false);
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });
        getContentPane().add(btbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 100, 40));

        jLabel8.setFont(new java.awt.Font("Forte", 0, 14)); // NOI18N
        jLabel8.setText("Cari Data Stok Galon");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 360, -1, -1));

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

    private void reset_donasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_donasiActionPerformed
        // TODO add your handling code here:
        kosongkan_stok_masuk();
        auto_stok_galon_masuk();
        auto_stok_galon();
        
    }//GEN-LAST:event_reset_donasiActionPerformed

    private void tabel_stokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_stokMouseClicked
        // TODO add your handling code here:
         int baris = tabel_stok.getSelectedRow();
        idstok_masuk.setText(tabel_stok.getModel().getValueAt(baris, 0).toString());
        id_stok.setText(tabel_stok.getModel().getValueAt(baris, 1).toString());
        Date date = null;
       try {
           date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabel_stok.getValueAt(baris,2));
       } catch (ParseException ex) {
           Logger.getLogger(Form_Stok_Galon.class.getName()).log(Level.SEVERE, null, ex);
       }
        tgl_masuk.setDate(date);
        jumlah_galon.setText(tabel_stok.getModel().getValueAt(baris, 3).toString());
        harga_galon.setText(tabel_stok.getModel().getValueAt(baris, 4).toString());
        idstok_lama.setText(tabel_stok.getModel().getValueAt(baris, 5).toString());
        
//        stok_lama.setText(tabel_stok.getModel().getValueAt(baris, 7).toString());

//        idstok_lama.setText(tabel_stok.getModel().getValueAt(baris, 1).toString());
//        update_galon.setText(tabel_stok.getModel().getValueAt(baris, 4).toString());


        btbatal.setEnabled(true);
        btbaru.setEnabled(false);
        btnhapus.setEnabled(true);
        btnedit.setEnabled(true);
        btnsimpan.setEnabled(false);
 
    }//GEN-LAST:event_tabel_stokMouseClicked

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:

       simpan();
       auto_stok_galon();
       tampilTabel_stok();
        btbaru.setEnabled(true);
        btnsimpan.setEnabled(true);
        btnhapus.setEnabled(false);
        btnedit.setEnabled(false);
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
       perbarui();
       kosongkan_stok_masuk();
       tampilTabel_stok();
       auto_stok_galon_masuk();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        
        int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete Data ini???", "Confirmation",JOptionPane.YES_NO_OPTION);
       if (ok==0)
       {
        try
         {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
            String sql="DELETE FROM data_stok WHERE id_stok_masuk='"+idstok_masuk.getText()+"'";
            PreparedStatement st=con.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Data Sukses");
         }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Delete Data Gagal");
        }
                  
        tampilTabel_stok();
        auto_stok_galon_masuk();
        auto_stok_galon();
        
       }
        
//        try{
//            
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/isi_ulang","root","");
//            Statement st = con.createStatement();
//            PreparedStatement ps = con.prepareStatement("DELETE FROM data_stok WHERE id_stok_masuk='"+idstok_masuk.getText()+"'");  
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "DELETED!!");
//        }
//        catch(HeadlessException | SQLException e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//        
//        tampilTabel_stok();
//       
//        

    }//GEN-LAST:event_btnhapusActionPerformed

    private void jumlah_galonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_galonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah_galonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //          String ObjButtons[] = {"Yes","No"};
        // int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        // if(pilihan == 0){
            new Login.Form_Login().setVisible(true);
            dispose();
            //        System.exit(0);
    }//GEN-LAST:event_jButton8ActionPerformed

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

            int jml=Integer.parseInt(jumlah_galon.getText());
            int total=Integer.parseInt(stok_lama.getText());
            {
                //        if(jml>jml){
                    //            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
                    //        }else if (jml<=100000){

                    int totbay=total+jml;
                    update_galon.setText(Integer.toString(totbay));

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
        btnsimpan.setEnabled(true);
        btnhapus.setEnabled(false);
    }//GEN-LAST:event_btmasukActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Yes","No"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda Yakin Ingin keluar dari Aplikasi ?","Message",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void harga_galonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harga_galonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_harga_galonActionPerformed

    private void caridonasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caridonasiActionPerformed
        // TODO add your handling code here:
        try{

            rs= st.executeQuery("SELECT * FROM data_stok WHERE id_stok_masuk LIKE '"+ idstok_lama.getText() +"'");

            if (rs.next () )
            {
                idstok_lama.setText(rs.getString(1));

                stok_lama.setText(rs.getString(8));
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

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        tampilTabel_stok();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:

        tampilTabel_stok();
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
        // TODO add your handling code here:
        tampilTabel_stok();
    }//GEN-LAST:event_txt_cariKeyPressed

    private void btbaruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbaruMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btbaruMouseClicked

    private void btbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbaruActionPerformed
        // TODO add your handling code here:
//        kodeOtomatis();
//        txtusername.requestFocus();
        btnsimpan.setEnabled(true);
        btnedit.setEnabled(false);
        btnhapus.setEnabled(false);
        btbaru.setEnabled(false);
        btbatal.setEnabled(true);
        //
    }//GEN-LAST:event_btbaruActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
        btbaru.setEnabled(true);
        btnsimpan.setEnabled(false);
        btnedit.setEnabled(false);
        btnhapus.setEnabled(false);
        btbatal.setEnabled(false);
        kosongkan_stok_masuk();
        
    }//GEN-LAST:event_btbatalActionPerformed

    private void idstok_lamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idstok_lamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_idstok_lamaKeyPressed

    private void idstok_lamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idstok_lamaActionPerformed
        // TODO add your handling code here:
//                try{
//                       
//                       rs= st.executeQuery("SELECT * FROM data_stok WHERE id_stok_masuk LIKE '"+ idstok_lama.getText() +"'");
//                       
//                       
//                       if (rs.next () )
//                       {
//                            idstok_lama.setText(rs.getString(1));
//                            stok_lama.setText(rs.getString(8));
//                           
//                       }else{
//                           JOptionPane.showMessageDialog(null, "Data yang di cari Tidak Ada");
//                       }
//                       
//                   }catch (HeadlessException | SQLException ex){
//                       System.out.println("error : "+ex);
//                   }
//    
    }//GEN-LAST:event_idstok_lamaActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Stok_Galon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Stok_Galon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Stok_Galon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Stok_Galon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Stok_Galon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbaru;
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btmasuk;
    private javax.swing.JButton btmenu;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btsaran;
    private javax.swing.JButton caridonasi;
    private javax.swing.JTextField harga_galon;
    private javax.swing.JTextField id_stok;
    private javax.swing.JTextField idstok_lama;
    private javax.swing.JTextField idstok_masuk;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah_galon;
    private javax.swing.JButton reset_donasi;
    private javax.swing.JTextField stok_lama;
    private javax.swing.JTable tabel_stok;
    private javax.swing.JLabel tanggal;
    private com.toedter.calendar.JDateChooser tgl_masuk;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JButton txt_search;
    private javax.swing.JTextField update_galon;
    // End of variables declaration//GEN-END:variables
}
