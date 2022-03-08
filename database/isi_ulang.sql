-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Feb 2022 pada 03.08
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `isi_ulang`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_admin`
--

CREATE TABLE `data_admin` (
  `id_admin` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_admin`
--

INSERT INTO `data_admin` (`id_admin`, `username`, `password`, `nama_user`, `level`) VALUES
('ADN-001', 'Sutris', 'Sutris', 'Sutris', 'Kepala Toko'),
('ADN-002', 'Naafie', 'Naafie', 'Naafie', 'Admin'),
('ADN-003', 'Admin', 'Admin', 'Admin', 'Kepala Toko');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_donasi`
--

CREATE TABLE `data_donasi` (
  `id_donasi` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `status_donasi` varchar(30) NOT NULL,
  `tanggal_donasi` varchar(30) NOT NULL,
  `jumlah_donasi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_donasi`
--

INSERT INTO `data_donasi` (`id_donasi`, `nama`, `no_hp`, `jenis_kelamin`, `alamat`, `status_donasi`, `tanggal_donasi`, `jumlah_donasi`) VALUES
('DSI-01', 'Hari P', '089503410152', '    Laki-Laki', 'Bekasi', 'Donatur', '2022-02-01', '144000'),
('DSI-02', 'Dika', '089134356877', '    Laki-Laki', 'Cipayung', 'Donatur', '2022-02-02', '1244444'),
('DSI-03', 'Rini', '098872232366', '   Perempuan', 'Banten', 'Donatur', '2022-02-02', '1566666'),
('DSI-04', 'Rahma', '082134657784', '   Perempuan', 'Cibubur', 'Donatur', '2022-02-03', '122222'),
('DSI-05', 'Endaryono', '0812998767', '    Laki-Laki', 'Matraman', 'Umum', '2022-02-21', '300000'),
('DSI-06', 'Rizal', '0896776573', '    Laki-Laki', 'Kranggan Permai', 'Umum', '2022-02-22', '80000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_donatur`
--

CREATE TABLE `data_donatur` (
  `id_donatur` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(25) NOT NULL,
  `email` varchar(30) NOT NULL,
  `alamat` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_donatur`
--

INSERT INTO `data_donatur` (`id_donatur`, `nama`, `no_hp`, `jenis_kelamin`, `email`, `alamat`) VALUES
('DR-01', 'Hari P', '089503410152', '    Laki-Laki', 'hariprasetiyoo13@gmail.com', 'Bekasi'),
('DR-02', 'Dika', '089134356877', '    Laki-Laki', 'dika13@gmail.com', 'Cipayung'),
('DR-03', 'Rini', '098872232366', '   Perempuan', 'rini22@gmail.com', 'Banten'),
('DR-04', 'Rahma', '082134657784', '   Perempuan', 'rahma32@gmail.com', 'Cibubur'),
('DR-05', 'Rika', '089503420345', '   Perempuan', 'rika13@gmail.com', 'Depok'),
('DR-06', 'Farhan', '082156787788', '    Laki-Laki', 'farhan44@gmail.com', 'Keranggan'),
('DR-07', 'Devi', '082144576875', '   Perempuan', 'devi22@gmail.com', 'Tangerang'),
('DR-08', 'Harsi', '085546477656', '   Perempuan', 'harsi@gmail.com', 'Jonggol'),
('DR-09', 'Chintya', '082234657713', '   Perempuan', 'chintya@gmail.com', 'Kota Bekasi'),
('DR-010', 'Fajar', '082267889888', '    Laki-Laki', 'fajar22@fmail.com', 'Ujung Aspal Pondok Gede');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_karyawan`
--

CREATE TABLE `data_karyawan` (
  `id_karyawan` varchar(30) NOT NULL,
  `nama_karyawan` varchar(50) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `bagian` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_karyawan`
--

INSERT INTO `data_karyawan` (`id_karyawan`, `nama_karyawan`, `no_hp`, `bagian`, `alamat`) VALUES
('KRY-01', 'Aldi', '0897384343', '  ISI Galon', 'JL.Merpati V No.13'),
('KRY-02', 'Rio Hermawan', '0822456678', '    Admin', 'JL.Merdeka VII No.3'),
('KRY-03', 'Riyan', '0895344456', '     Kurir', 'JL.Melari IV No.I'),
('KRY-04', 'FIrman', '0812556787', '     Kurir', 'JL.Cempaka VIII No.10');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_karyawan_kurir`
--

CREATE TABLE `data_karyawan_kurir` (
  `id_karyawan` varchar(50) NOT NULL,
  `id_kurir` varchar(50) NOT NULL,
  `nama_kurir` varchar(100) NOT NULL,
  `no_hp` varchar(50) NOT NULL,
  `bagian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_karyawan_kurir`
--

INSERT INTO `data_karyawan_kurir` (`id_karyawan`, `id_kurir`, `nama_kurir`, `no_hp`, `bagian`) VALUES
('KRY-03', 'KKR-01', 'Riyan', '0895344456', '     Kurir'),
('KRY-04', 'KKR-02', 'FIrman', '0812556787', '     Kurir');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_kas`
--

CREATE TABLE `data_kas` (
  `id_kas` varchar(30) NOT NULL,
  `id_donasi` varchar(50) NOT NULL,
  `id_distribusi` varchar(30) NOT NULL,
  `jumlah_kas_lama` varchar(50) NOT NULL,
  `tgl_update` varchar(50) NOT NULL,
  `kas_masuk` varchar(50) NOT NULL,
  `kas_keluar` varchar(50) NOT NULL,
  `kondisi` varchar(30) NOT NULL,
  `update_jumlah` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_kas`
--

INSERT INTO `data_kas` (`id_kas`, `id_donasi`, `id_distribusi`, `jumlah_kas_lama`, `tgl_update`, `kas_masuk`, `kas_keluar`, `kondisi`, `update_jumlah`) VALUES
('IKS-001', 'DSI-01', '', '0', '2022-02-01', '144000', '0', 'Masuk', '144000'),
('IKS-002', 'DSI-02', '', '144000', '2022-02-02', '1244444', '0', 'Masuk', '1388444'),
('IKS-003', 'DSI-03', '', '1388444', '2022-02-02', '1244444', '0', 'Masuk', '2632888'),
('IKS-004', 'DSI-04', '', '2632888', '2022-02-02', '1244444', '0', 'Masuk', '3877332'),
('IKS-005', '', 'PDS-04', '3877332', '2022-02-21', '0', '360000', 'Keluar', '3517332'),
('IKS-006', 'DSI-05', '', '3517332', '2022-02-21', '300000', '', 'Masuk', '3817332');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_lembagapenerima`
--

CREATE TABLE `data_lembagapenerima` (
  `id_penerima` varchar(30) NOT NULL,
  `penanggung_jawab` varchar(50) NOT NULL,
  `nama_tempat` varchar(50) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `pilih` varchar(30) NOT NULL,
  `alamat_tujuan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_lembagapenerima`
--

INSERT INTO `data_lembagapenerima` (`id_penerima`, `penanggung_jawab`, `nama_tempat`, `no_hp`, `pilih`, `alamat_tujuan`) VALUES
('LBP-01', 'Rahman', 'AL-Barokah', '08213244567', '      Masjid', 'Kota Bekasi'),
('LBP-02', 'Riri', 'AL-Furqon', '08213445346', '   Pesantren', 'Jonggol'),
('LBP-03', 'Sulaiman', 'Nurul Huda Kaffah', '08213466578', '     Yayasan', 'Kota Bekasi , Keranggan'),
('LBP-04', 'Ipul', 'Nurul Huda', '08124343887', '      Masjid', 'Kota Bekasi , Keranggan'),
('LBP-05', 'Geni', 'AL-Jihad', '08214465577', '      Masjid', 'Bogor'),
('LBP-06', 'Rahman', 'Yayasan AL Furqon', '08323244225', '     Yayasan', 'Depok'),
('LBP-07', 'Chintya', 'Rumah', '0834447654', '     Individu', 'Jatisampurna'),
('LBP-08', 'Fahmi', 'AL-IMAM', '08984456787', '      Masjid', 'Pondok Gede'),
('LBP-09', 'Gatot', 'AL -Hidayah', '084458768', '      Masjid', 'JL. Duren No.12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pendistribusian`
--

CREATE TABLE `data_pendistribusian` (
  `id_distribusi` varchar(30) NOT NULL,
  `penanggung_jawab` varchar(50) NOT NULL,
  `nama_tempat` varchar(50) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `pilih` varchar(20) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `tanggal_penyaluran` varchar(30) NOT NULL,
  `id_kurir` varchar(50) NOT NULL,
  `nama_kurir` varchar(50) NOT NULL,
  `id_stok` varchar(30) NOT NULL,
  `stok_galon` varchar(40) NOT NULL,
  `jml` varchar(30) NOT NULL,
  `biaya_pendistribusi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_pendistribusian`
--

INSERT INTO `data_pendistribusian` (`id_distribusi`, `penanggung_jawab`, `nama_tempat`, `no_hp`, `pilih`, `alamat`, `tanggal_penyaluran`, `id_kurir`, `nama_kurir`, `id_stok`, `stok_galon`, `jml`, `biaya_pendistribusi`) VALUES
('PDS-01', 'Rahman', 'AL-Barokah', '08213244567', '      Masjid', 'Kota Bekasi', '2022-02-04', 'KKR-02', 'FIrman', 'STG-02', '45', '50', '450000'),
('PDS-02', 'Riri', 'AL-Furqon', '08213445346', '   Pesantren', 'Jonggol', '2022-02-15', 'KKR-01', 'Riyan', 'STG-03', '300', '40', '360000'),
('PDS-03', 'Sulaiman', 'Nurul Huda Kaffah', '08213466578', '     Yayasan', 'Kota Bekasi , Keranggan', '2022-02-16', 'KKR-01', 'Riyan', 'STG-02', '95', '20', '180000'),
('PDS-04', 'Gatot', 'AL -Hidayah', '084458768', '      Masjid', 'JL. Duren No.12', '2022-02-21', 'KKR-01', 'Riyan', 'STG-03', '183', '40', '360000');

--
-- Trigger `data_pendistribusian`
--
DELIMITER $$
CREATE TRIGGER `barang_keluar` AFTER INSERT ON `data_pendistribusian` FOR EACH ROW BEGIN

UPDATE data_stok SET stok_galon = stok_galon - NEW.jml

WHERE id_stok = NEW.id_stok;

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `barang_update` AFTER UPDATE ON `data_pendistribusian` FOR EACH ROW BEGIN

UPDATE data_stok SET stok_galon = stok_galon - NEW.jml

WHERE id_stok = NEW.id_stok;

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_penjualan`
--

CREATE TABLE `data_penjualan` (
  `id_penjualan` varchar(30) NOT NULL,
  `nama_pembeli` varchar(30) NOT NULL,
  `nohp_pembeli` varchar(30) NOT NULL,
  `jenis_galon` varchar(30) NOT NULL,
  `alamat_pembeli` varchar(50) NOT NULL,
  `status_pembayaran` varchar(20) NOT NULL,
  `tgl_pemesanan` varchar(20) NOT NULL,
  `id_kurir` varchar(20) NOT NULL,
  `nama_kurir` varchar(50) NOT NULL,
  `jumlah_galon` varchar(30) NOT NULL,
  `harga` varchar(10) NOT NULL,
  `total` varchar(20) NOT NULL,
  `bayar` varchar(30) NOT NULL,
  `kembali` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_penjualan`
--

INSERT INTO `data_penjualan` (`id_penjualan`, `nama_pembeli`, `nohp_pembeli`, `jenis_galon`, `alamat_pembeli`, `status_pembayaran`, `tgl_pemesanan`, `id_kurir`, `nama_kurir`, `jumlah_galon`, `harga`, `total`, `bayar`, `kembali`) VALUES
('IDP-01', 'Hari Prasetiyo', '089503410152', 'Naafie', 'JLMelati VI No.13', 'Cash', '2022-01-01', 'KRY-01', 'RIzal', '3', '6000', '18000', '20000', '2000'),
('IDP-02', 'Fahmi', '08224566766', 'AQUA', 'JL.Mawar III No.1', 'Cash', '2022-01-01', '', '', '4', '6000', '24000', '24000', '0'),
('IDP-03', 'Rina', '0812336454', 'Le Minerale', 'JL.Anggrek X No.11', 'Cash', '2022-01-01', '', '', '3', '6000', '18000', '20000', '2000'),
('IDP-04', 'Fahmi', '08950344565', 'Le Minerale', 'JL.Randu No.13', 'Transfer', '2022-01-01', 'KKR-02', 'FIrman', '50', '6000', '300000', '350000', '50000'),
('IDP-05', 'Winda', '0823887653', 'AQUA', 'Jl. Asem No.12', 'Cash', '2022-01-02', '', '', '5', '6000', '30000', '50000', '20000'),
('IDP-06', 'Caka', '085655776', 'AQUA', 'Kunciran', 'Cash', '2022-02-21', '', '', '4', '6000', '24000', '25000', '1000'),
('IDP-07', 'Endar', '0856768878', 'VIT', 'Matraman', 'Cash', '2022-02-21', 'KKR-02', 'FIrman', '3', '6000', '18000', '20000', '2000'),
('IDP-08', 'Faisan', '098765242', 'VIT', 'Jl.Mawar 4 N0. 12', 'Transfer', '2022-02-22', '', '', '4', '6000', '24000', '25000', '1000'),
('IDP-09', 'Iyas', '084778764445', 'Naafie', 'Jl. Melawai', 'Transfer', '2022-02-22', 'KKR-01', 'Riyan', '2', '6000', '12000', '15000', '3000'),
('IDP-010', 'Dayat', '0895564746', 'OASIS', 'JL. Merdeka No.23', 'Cash', '2022-02-22', '', '', '2', '6000', '12000', '15000', '3000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_stok`
--

CREATE TABLE `data_stok` (
  `id_stok_masuk` varchar(30) NOT NULL,
  `id_stok` varchar(30) NOT NULL,
  `tgl_stok_masuk` varchar(30) NOT NULL,
  `jumlah_galon_masuk` varchar(30) NOT NULL,
  `harga_galon` varchar(30) NOT NULL,
  `id_stok_lama` varchar(50) NOT NULL,
  `jml` varchar(30) NOT NULL,
  `stok_galon` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_stok`
--

INSERT INTO `data_stok` (`id_stok_masuk`, `id_stok`, `tgl_stok_masuk`, `jumlah_galon_masuk`, `harga_galon`, `id_stok_lama`, `jml`, `stok_galon`) VALUES
('STK-01', 'STG-01', '2022-02-23', '200', ' 8000', '', '0', '200'),
('STK-02', 'STG-02', '2022-02-14', '50', ' 8000', 'STK-01', '200', '250'),
('STK-03', 'STG-03', '2022-02-21', '13', ' 8000', 'STK-02', '250', '263');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
