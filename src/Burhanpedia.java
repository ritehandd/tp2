package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import model.*;
import repositories.*;

public class Burhanpedia {
    public static int daysToAdd = 1;
    public static LocalDate hariIni = LocalDate.now();
    private PenjualRepository repoPenjual = new PenjualRepository();
    private PembeliRepository repoPembeli = new PembeliRepository();
    private AdminRepository repoAdmin = new AdminRepository();
    private VoucherRepository repoVoucher = new VoucherRepository();

    public static void main(String[] args) {
        Burhanpedia app = new Burhanpedia();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============================================================");
        System.out.println("\n  ____             _                 _____         _ _       \r\n"
                + " |  _ \\           | |               |  __ \\       | (_)      \r\n"
                + " | |_) |_   _ _ __| |__   __ _ _ __ | |__) |__  __| |_  __ _ \r\n"
                + " |  _ <| | | | '__| '_ \\ / _` | '_ \\|  ___/ _ \\/ _` | |/ _` |\r\n"
                + " | |_) | |_| | |  | | | | (_| | | | | |  |  __/ (_| | | (_| |\r\n"
                + " |____/ \\__,_|_|  |_| |_|\\__,_|_| |_|_|   \\___|\\__,_|_|\\__,_|\r\n"
                + "                                                             \r\n"
                + "                                                             ");
        System.out.println("=============================================================");
        System.out.println("============== Selamat datang di Burhanpedia! ===============");
        System.out.println("=============================================================");
        System.out.println();
        boolean main = true;
        while (main) {
            System.out.println("Pilih menu :");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Hari Selanjutnya");
            System.out.println("4. Keluar");
            System.out.println();
            System.out.print("Perintah : ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    app.login();
                    break;
                case 2:
                    app.register();

                    break;
                case 3:
                    app.hariEsok(Burhanpedia.daysToAdd);
                    Burhanpedia.daysToAdd++;

                    break;
                case 4:
                    System.out.println();
                    System.out.println("===========================================");
                    System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                    System.out.println("===========================================");
                    main = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }

        }

    }

    public void register() {

        PenjualRepository repoPenjual = this.repoPenjual;
        Penjual[] penjualList = repoPenjual.getPenjualList();
        PembeliRepository repoPembeli = this.repoPembeli;
        Pembeli[] pembeliList = repoPembeli.getPembeliList();
        boolean register = true;
        while (register) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("=========== Registrasi ===========");
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            for (Penjual penjual : penjualList) {
                if (penjual != null) {
                    if (penjual.getUsername().equals(username)) {
                        System.out.println("Username sudah ada!");
                        System.out.println();
                        return;
                    }
                }
            }
            for (Pembeli pembeli : pembeliList) {
                if (pembeli != null) {
                    if (pembeli.getUsername().equals(username)) {
                        System.out.println("Username sudah ada!");
                        System.out.println();
                        return;
                    }
                }
            }
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();
            System.out.println();

            System.out.println("Pilih Role:");
            System.out.println("1. Penjual");
            System.out.println("2. Pembeli");
            System.out.println();
            System.out.print("Perintah: ");
            int perintah = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (perintah == 1) {
                boolean namaTokoInsert = true;
                String namaToko = "";
                while (namaTokoInsert) {
                    System.out.print("Masukkan nama toko: ");
                    namaToko = scanner.nextLine();
                    for (Penjual penjual : penjualList) {
                        if (penjual != null) {
                            ProductRepository repoProduk = penjual.getProductRepo();
                            if (repoProduk.getNamaToko().equalsIgnoreCase(namaToko)) {
                                System.out.println("Nama toko sudah ada!");
                                System.out.println();
                                break;
                            }
                        } else {
                            namaTokoInsert = false;
                        }
                    }
                }

                Penjual akunPenjual = new Penjual(username, password, namaToko);
                System.out.println("Registrasi akun penjual berhasil!");
                repoPenjual.addPenjualList(akunPenjual);
                System.out.println();
                register = false;

            } else if (perintah == 2) {
                Pembeli akunPembeli = new Pembeli(username, password);
                System.out.println("Registrasi akun pembeli berhasil!");
                repoPembeli.addPembeliList(akunPembeli);
                System.out.println();
                register = false;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

    }

    public void login() {
        PenjualRepository repoPenjual = this.repoPenjual;
        Penjual[] penjualList = repoPenjual.getPenjualList();
        PembeliRepository repoPembeli = this.repoPembeli;
        Pembeli[] pembeliList = repoPembeli.getPembeliList();
        AdminRepository repoAdmin = this.repoAdmin;
        Admin[] adminList = repoAdmin.getAdminList();
        Pembeli akunPembeli;
        Penjual akunPenjual;
        String username;
        String password;

        boolean aksesPenjual = false;
        boolean aksesPembeli = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("=========== Login ===========");
        System.out.print("Masukkan username: ");
        username = scanner.nextLine();
        if (repoPembeli.getPembeliByUsername(username) == null && repoPenjual.getPenjualByUsername(username) == null
                && repoAdmin
                        .getAdmin(username) == null) {
            System.out.println("Akun tidak ditemukan!");
            System.out.println();
            return;
        }
        System.out.print("Masukkan password: ");
        password = scanner.nextLine();
        if (repoPembeli.getPembeliByUsername(username) != null) {
            if (!repoPembeli.getPembeliByUsername(username).getPassword().equals(password)) {
                System.out.println("Password salah!");
                System.out.println();
                return;
            }
        } else if (repoPenjual.getPenjualByUsername(username) != null) {
            if (!repoPenjual.getPenjualByUsername(username).getPassword().equals(password)) {
                System.out.println("Password salah!");
                System.out.println();
                return;
            }
        } else if (repoAdmin.getAdmin(username) != null) {
            if (!repoAdmin.getAdmin(username).getPassword().equals(password)) {
                System.out.println("Password salah!");
                System.out.println();
                return;
            }
        }

        boolean admin = repoAdmin.login(username, password);
        if (admin == true) {
            System.out.println("Login berhasil. Selamat datang, Admin!");
            System.out.println();
            VoucherRepository repoVoucher = this.getVoucherRepository();
            while (admin) {
                System.out.println("========== MENU ADMIN =========");
                System.out.println("1. Generate Voucher");
                System.out.println("2. Lihat Voucher");
                System.out.println("3. Kembali ke menu utama");
                System.out.println();
                System.out.print("Perintah : ");
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        repoVoucher.generateVoucher();
                        System.out.println();
                        System.out.println(repoVoucher.getVoucherList().length - 1);
                        break;
                    case 2:
                        Voucher[] voucherList = repoVoucher.getVoucherList();
                        if (voucherList.length == 1) {
                            System.out.println("==============================");
                            System.out.println("Belum ada voucher yang dibuat!");
                            System.out.println("==============================");
                        } else {
                            System.out.println("=========================");
                            for (int i = 0; i < voucherList.length - 1; i++) {
                                Voucher voucher = voucherList[i];
                                System.out.print(voucher.getId());
                                if (voucher.getStatus() == false) {
                                    System.out.print(" [Belum digunakan]");
                                } else {
                                    System.out.print(" [Sudah digunakan]");
                                }
                                System.out.println();
                            }
                            System.out.println("=========================");
                            System.out.println();
                        }
                        break;
                    case 3:
                        admin = false;
                        System.out.println();
                        break;
                    default:
                        break;
                }
            }
        } else {
            aksesPembeli = repoPembeli.login(username, password);
            aksesPenjual = repoPenjual.login(username, password);
            System.out.println();

            if (aksesPembeli) {
                akunPembeli = repoPembeli.getPembeliByUsername(username);
                Cart keranjang = akunPembeli.getCart();
                CartProduct[] listKeranjang = keranjang.getCartProduct();
                TransaksiRepository repoTransaksi = akunPembeli.getRiwayatTransaksi();
                boolean pembeli = true;
                while (pembeli) {
                    System.out.println("===== MENU PEMBELI =====\r\n"
                            + //
                            "1. Cek Saldo\r\n"
                            + //
                            "2. Top Up Saldo\r\n"
                            + //
                            "3. Cek Daftar Barang\r\n"
                            + //
                            "4. Tambah Barang ke Keranjang\r\n"
                            + //
                            "5. Checkout Keranjang\r\n"
                            + //
                            "6. Lacak Barang\r\n"
                            + //
                            "7. Lihat Laporan Pengeluaran\r\n"
                            + //
                            "8. Kembali ke menu utama");
                    System.out.println();
                    System.out.print("Perintah : ");
                    int perintah2 = scanner.nextInt();
                    System.out.println();
                    switch (perintah2) {
                        case 1:
                            long saldo = akunPembeli.getBalance();
                            String saldoAkhirString = String.format("Saldo saat ini: %.2f", saldo * 1.00);
                            System.out.println("==============================");
                            System.out.println(saldoAkhirString);
                            System.out.println("==============================");
                            System.out.println();
                            break;
                        case 2:
                            saldo = akunPembeli.getBalance();
                            boolean inputSaldo = true;
                            Long topUp = (long) 0;
                            while (inputSaldo) {
                                try {
                                    System.out.print("Masukkan jumlah saldo yang ingin ditambahkan : ");
                                    topUp = scanner.nextLong();
                                    scanner.nextLine();
                                    if (topUp == 0) {
                                        System.out.println("Saldo tidak boleh bernilai nol");
                                    } else if (topUp < 0) {
                                        System.out.println("Saldo tidak boleh bernilai negatif");
                                    } else {
                                        inputSaldo = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Saldo bukan angka");
                                    scanner.next();
                                }
                            }
                            akunPembeli.setBalance(topUp);
                            String saldoAkhirFormat = String.format("Saldo berhasil ditambah! Saldo saat ini: %.2f",
                                    akunPembeli.getBalance() * 1.00);
                            System.out.println(saldoAkhirFormat);
                            System.out.println();
                            break;

                        case 3:

                            System.out.println("==============================");
                            for (Penjual penjual : repoPenjual.getPenjualList()) {
                                if (penjual != null) {
                                    ProductRepository repoProduct = penjual.getProductRepo();
                                    Product[] listProduct = repoProduct.getProductList();
                                    if (listProduct.length > 1) {
                                        System.out.println(repoProduct.getNamaToko());
                                        for (Product produk : listProduct) {
                                            if (produk != null) {
                                                System.out.printf("%-10s %10.2f %5d%n", produk.getNamaProduk(),
                                                        produk.getPrice() * 1.00, produk.getStok());
                                            }
                                        }
                                        System.out.println("--------------------------------------");
                                    } else {
                                        System.out.println("Belum ada barang yang dijual!");
                                        break;
                                    }
                                } else {
                                    System.out.println("Belum ada barang yang dijual!");
                                }
                            }
                            System.out.println("==============================");
                            break;

                        case 4:
                            Penjual[] listPenjual = repoPenjual.getPenjualList();
                            int jumlahProduk = 0;
                            for (Penjual cekPenjual : listPenjual) {
                                boolean lanjut = true;
                                if (cekPenjual != null) {
                                    ProductRepository repoProduk = cekPenjual.getProductRepo();
                                    if (repoProduk.getProductList().length == 1) {
                                        System.out.println("Belum ada barang yang dijual");
                                    } else {
                                        boolean addCart = true;
                                        System.out.print("Masukkan toko penjual barang yang ingin dibeli : ");
                                        String namaToko = scanner.next();
                                        scanner.nextLine();
                                        System.out.print("Masukkan nama barang yang ingin dibeli : ");
                                        String namaProduk = scanner.next();
                                        scanner.nextLine();
                                        while (addCart) {
                                            System.out.print("Masukkan jumlah barang yang ingin dibeli : ");
                                            jumlahProduk = scanner.nextInt();
                                            scanner.nextLine();
                                            if (jumlahProduk <= 0) {
                                                System.out.println("Jumlah produk tidak valid");
                                            } else if (jumlahProduk > cekPenjual.getProductRepo()
                                                    .getProductByName(namaProduk).getStok()) {
                                                System.out.println("Jumlah produk melebihi batas");
                                                addCart = false;
                                                lanjut = false;
                                            } else {
                                                addCart = false;
                                            }
                                        }
                                        if (lanjut == true) {

                                            for (Penjual penjual : repoPenjual.getPenjualList()) {
                                                if (penjual != null) {
                                                    if (penjual.getProductRepo().getNamaToko().equals(namaToko)) {
                                                        if (penjual.getProductRepo().getProductList().length > 1) {
                                                            for (Product produk : penjual.getProductRepo()
                                                                    .getProductList()) {
                                                                if (produk != null) {
                                                                    if (produk.getNamaProduk().equals(namaProduk)) {
                                                                        if (produk.getStok() >= jumlahProduk) {
                                                                            UUID idProduk = produk.getProductID();
                                                                            for (CartProduct cekProduk : keranjang
                                                                                    .getCartProduct()) {
                                                                                if (cekProduk != null) {
                                                                                    if (cekProduk.getProductId()
                                                                                            .equals(idProduk)) {
                                                                                        cekProduk.setAmount(
                                                                                                cekProduk.getAmount()
                                                                                                        + jumlahProduk);
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (!keranjang.containsProduct(idProduk)) {
                                                                                keranjang.addToCart(idProduk);
                                                                                listKeranjang = keranjang
                                                                                        .getCartProduct();
                                                                                for (CartProduct cartProduct : listKeranjang) {
                                                                                    if (cartProduct != null) {
                                                                                        if (cartProduct.getProductId()
                                                                                                .equals(idProduk)) {
                                                                                            cartProduct.setAmount(
                                                                                                    jumlahProduk);
                                                                                            cartProduct.setName(
                                                                                                    namaProduk);
                                                                                            cartProduct.setPrice(
                                                                                                    produk.getPrice());

                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("=============================");
                                    System.out.println("Belum ada barang yang dijual!");
                                    System.out.println("=============================");
                                    System.out.println();
                                    break;
                                }
                            }
                            break;

                        case 5:
                            Transaksi transaksi = new Transaksi();
                            long hargaTotal = 0;
                            Transaksi[] transaksiList = repoTransaksi.getList();
                            long totalTransaksi = 0;
                            CartProduct[] listProducts = keranjang.getCartProduct();
                            if (listProducts != null && listProducts.length > 1) {
                                for (CartProduct product : listProducts) {
                                    if (product != null) {
                                        hargaTotal = product.getPrice() * product.getAmount();
                                        totalTransaksi += hargaTotal;
                                        System.out.println("====================================");
                                        System.out.printf("%-10s %10.2f %5d (%5.2f)\n", product.getName(),
                                                product.getPrice() * 1.00, product.getAmount(), hargaTotal * 1.00);
                                    }
                                }
                                System.out.println("------------------------------------");
                                System.out.printf("%-10s %10.2f\n", "Subtotal", totalTransaksi * 1.00);
                                System.out.println("====================================");

                                System.out.print("Apakah anda yakin dengan produknya? (Y/N): ");
                                String jawaban = scanner.next();
                                scanner.nextLine();
                                if (jawaban.equalsIgnoreCase("Y")) {

                                    for (CartProduct cartProduct : listKeranjang) {
                                        if (cartProduct != null) {
                                            for (Penjual penjual : repoPenjual.getPenjualList()) {
                                                if (penjual != null) {
                                                    for (Product produk : penjual.getProductRepo().getProductList()) {
                                                        if (produk != null) {
                                                            if (produk.getNamaProduk().equals(cartProduct.getName())) {
                                                                if (produk.getStok() < cartProduct.getAmount()) {
                                                                    System.out.println(
                                                                            "Jumlah produk " + cartProduct.getName()
                                                                                    + "  melebihi batas stok!");
                                                                    break;
                                                                } else if (produk.getStok() >= cartProduct
                                                                        .getAmount()) {
                                                                    boolean cekVoucher = true;
                                                                    System.out.println(
                                                                            "Masukkan kode voucher.\nJika tidak ada, ketik 'skip'");
                                                                    while (cekVoucher) {
                                                                        System.out.println(
                                                                                "====================================");
                                                                        System.out.print("Kode: ");
                                                                        String kodeVoucher = scanner.nextLine();
                                                                        if (kodeVoucher.equalsIgnoreCase("skip")) {
                                                                            hargaTotal = (long) (hargaTotal * 1.03);
                                                                            // akunPembeli.setBalance(- hargaTotal -
                                                                            // hargaPajak);
                                                                        } else {
                                                                            for (Voucher voucher : repoVoucher
                                                                                    .getVoucherList()) {
                                                                                if (voucher != null) {
                                                                                    if (voucher.getId()
                                                                                            .equals(kodeVoucher)) {
                                                                                        if (voucher
                                                                                                .getStatus() == false) {
                                                                                            int diskon = voucher
                                                                                                    .hitungDiskon(
                                                                                                            voucher.getId(),
                                                                                                            0);
                                                                                            if (diskon > 99) {
                                                                                                diskon = voucher
                                                                                                        .hitungDiskon(
                                                                                                                diskon + "",
                                                                                                                0);
                                                                                            }
                                                                                            voucher.setStatus();
                                                                                            String idVoucher = voucher
                                                                                                    .getId();
                                                                                            transaksi.setVoucherID(
                                                                                                    idVoucher);
                                                                                            hargaTotal = (long) ((hargaTotal
                                                                                                    * (1 - diskon
                                                                                                            / 100.00)
                                                                                                    * 1.03));
                                                                                            // akunPembeli.setBalance(-
                                                                                            // hargaTotal);
                                                                                            System.out.println(
                                                                                                    "Voucher diterapkan! Total harga setelah diskon: "
                                                                                                            + hargaTotal
                                                                                                                    * 1.00);
                                                                                            cekVoucher = false;
                                                                                            break;
                                                                                        } else {
                                                                                            System.out.println(
                                                                                                    "Voucher tidak valid!");
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println(
                                                                                                "Voucher tidak valid!");
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    if (akunPembeli.getBalance() >= hargaTotal) {
                                                                        akunPembeli.setBalance(-hargaTotal);
                                                                        TransaksiProduct produkTransaksi = new TransaksiProduct(
                                                                                produk.getProductID(), 1);
                                                                        produkTransaksi
                                                                                .setAmount(cartProduct.getAmount());
                                                                        produkTransaksi.setPrice(produk.getPrice());
                                                                        produkTransaksi.setName(produk.getName());
                                                                        produkTransaksi
                                                                                .setProductId(produk.getProductID());
                                                                        transaksi.addTransaksi(produkTransaksi);
                                                                        repoTransaksi.addTransaksi(transaksi);
                                                                        transaksi.setId(repoTransaksi.numOfTransaksi);
                                                                        produk.setStok(produk.getStok()
                                                                                - cartProduct.getAmount());
                                                                        for (Penjual tokoPenjual : penjualList) {
                                                                            if (tokoPenjual != null) {
                                                                                for (Product Soldproduk : tokoPenjual
                                                                                        .getProductRepo()
                                                                                        .getProductList()) {
                                                                                    if (Soldproduk != null) {
                                                                                        if (Soldproduk.getNamaProduk()
                                                                                                .equals(cartProduct
                                                                                                        .getName())) {
                                                                                            SoldProduct soldProduct = new SoldProduct();
                                                                                            soldProduct.setAmount(
                                                                                                    cartProduct
                                                                                                            .getAmount());
                                                                                            soldProduct.setProductName(
                                                                                                    cartProduct
                                                                                                            .getName());
                                                                                            SoldProductRepository barangTerjual = tokoPenjual
                                                                                                    .getLaporan();
                                                                                            barangTerjual
                                                                                                    .addSoldProduct(
                                                                                                            soldProduct);
                                                                                            Long revenue = (long) (hargaTotal
                                                                                                    * (100.00
                                                                                                            / 103.00));
                                                                                            barangTerjual.addRevenue(
                                                                                                    revenue);
                                                                                            keranjang.removeCart(
                                                                                                    cartProduct
                                                                                                            .getProductId());
                                                                                            Pengiriman pengiriman = new Pengiriman();
                                                                                            pengiriman.setIdPenjual(
                                                                                                    penjual.getIdPenjual());
                                                                                            pengiriman.setTimeStamp(
                                                                                                    Burhanpedia.hariIni);
                                                                                            PengirimanRepository repoPengiriman = penjual
                                                                                                    .getRepoPengiriman();
                                                                                            repoPengiriman
                                                                                                    .addPengiriman(
                                                                                                            pengiriman);
                                                                                            pengiriman.setIdTransaksi(
                                                                                                    repoPengiriman.numOfPengiriman);
                                                                                            transaksi.addPengiriman(
                                                                                                    pengiriman);
                                                                                        }
                                                                                    }
                                                                                }

                                                                            }
                                                                        }
                                                                        System.out.println("Transaksi Berhasil!");

                                                                    } else {
                                                                        System.out.println(
                                                                                "Pembelian gagal, Saldo tidak cukup!");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                        // break;
                                    }

                                } else if (jawaban.equalsIgnoreCase("N")) {
                                    break;

                                }

                            } else {
                                System.out.println("============================");
                                System.out.println("Keranjang masih kosong!");
                                System.out.println("============================");
                                System.out.println();
                            }

                            break;
                        case 6:

                            for (Transaksi cekTransaksi : repoTransaksi.getList()) {
                                if (cekTransaksi != null) {
                                    for (Pengiriman cekPengiriman : cekTransaksi.getStatusPengiriman()) {
                                        if (cekPengiriman != null) {
                                            System.out.println("============================");
                                            System.out.printf("%-15s %-10s\n", "ID Transaksi",
                                                    cekPengiriman.getIdTransaksi());
                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                                    "EEEE, d MMMM yyyy",
                                                    new Locale("id"));
                                            System.out.printf("%-15s %-10s\n", "Tanggal",
                                                    cekPengiriman.getTimeStamp().format(formatter));

                                            UUID idPengirim = cekPengiriman.getIdPenjual();
                                            ProductRepository cekProductRepository = repoPenjual
                                                    .getPenjualByID(idPengirim).getProductRepo();
                                            for (TransaksiProduct cekProductTransaksi : cekTransaksi.getProductList()) {
                                                if (cekProductTransaksi != null) {
                                                    UUID idProduk = cekProductTransaksi.getProductId();
                                                    for (Product cekProduk : cekProductRepository.getProductList()) {
                                                        if (cekProduk.getProductID().equals(idProduk)) {
                                                            String cekNamaToko = cekProductRepository.getNamaToko();
                                                            System.out.printf("%-15s %-10s\n", "Nama Toko: ",
                                                                    cekNamaToko);
                                                            break;
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                            if (cekPengiriman.getIsArrived() == false
                                                    && cekPengiriman.getIsSent() == false) {
                                                System.out.printf("%-15s %-10s\n", "Status", "Dikemas");
                                            } else if (cekPengiriman.getIsSent() == true) {
                                                System.out.printf("%-15s %-10s\n", "Status", "Dikirim");
                                            } else if (cekPengiriman.getIsArrived() == true) {
                                                System.out.printf("%-15s %-10s\n", "Status", "Sampai");
                                            }
                                            break;
                                        }
                                    }
                                    System.out.println("----------------------------");
                                }
                            }
                            break;

                        case 7:

                            Transaksi[] listTransaksi = repoTransaksi.getList();
                            int diskon = 0;
                            if (listTransaksi.length > 1) {
                                int totalKeseluruhan = 0;
                                System.out.println("======= LAPORAN PENGELUARAN =======");
                                for (Transaksi transaksiPembeli : listTransaksi) {
                                    int totalTransaksiItem = 0;
                                    String voucherID = transaksiPembeli != null ? transaksiPembeli.getVoucherID() : "";
                                    Voucher voucher = repoVoucher.getVoucherById(voucherID);
                                    if (transaksiPembeli != null) {
                                        System.out.printf("%-15s %-10s\n", "ID Transaksi: ", transaksiPembeli.getId());
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy",
                                                new Locale("id"));
                                        String formatted = Burhanpedia.hariIni.format(formatter);
                                        System.out.printf("%-15s %-10s\n", "Tanggal", formatted);
                                        System.out.println("-----------------------------------");
                                        for (TransaksiProduct produk : transaksiPembeli.getProductList()) {
                                            if (produk != null) {
                                                hargaTotal = produk.getPrice() * produk.getAmount();
                                                totalTransaksiItem += hargaTotal;
                                                System.out.printf("%-15s %-10.2f %5d (%5.2f)\n", produk.getName(),
                                                        produk.getPrice() * 1.00, produk.getAmount(),
                                                        hargaTotal * 1.00);
                                            }
                                        }
                                        diskon = voucher != null ? voucher.hitungDiskon(voucher.getId(), 0) : 0;
                                        System.out.println("-----------------------------------");
                                        System.out.printf("%-15s %-10.2f\n", "Subtotal", totalTransaksiItem * 1.00);

                                        System.out.printf("%-15s %-10.2f\n", "Diskon",
                                                totalTransaksiItem * (diskon / 100.00) * 1.00);
                                        System.out.printf("%-15s %-10.2f\n", "Pajak (3%)",
                                                totalTransaksiItem * (1 - (diskon / 100.00)) * 0.03);
                                        System.out.println("-----------------------------------");

                                        System.out.printf("%-15s %-10.2f\n", "Total",
                                                totalTransaksiItem * (1 - (diskon / 100.00)) * 1.03);
                                        System.out.println("===================================");
                                        System.out.println();
                                    }
                                    totalKeseluruhan += totalTransaksiItem * (1 - (diskon / 100.00)) * 1.03;
                                }
                                System.out.printf("%-15s %-10.2f\n", "Total Keseluruhan", totalKeseluruhan * 1.00);
                            } else {
                                System.out.println("===================================");
                                System.out.println("Laporan pengeluaran masih kosong!");
                                System.out.println("===================================");
                            }

                            break;
                        case 8:
                            pembeli = false;
                            break;

                        default:
                            System.out.println("Perintah tidak ditemukan");
                    }
                }
                aksesPembeli = false;

            } else if (aksesPenjual) {
                akunPenjual = repoPenjual.getPenjualByUsername(username);
                ProductRepository productRepo = akunPenjual.getProductRepo();
                boolean penjual = true;
                while (penjual) {
                    System.out.println("===== MENU PENJUAL =====\r\n"
                            + // 0305202540
                            "1. Cek Produk\r\n"
                            + //
                            "2. Tambah Produk\r\n"
                            + //
                            "3. Tambah Stok\r\n"
                            + //
                            "4. Ubah Harga Barang\r\n"
                            + //
                            "5. Kirim Barang\r\n"
                            + //
                            "6. Lihat Laporan Pendapatan\r\n"
                            + //
                            "7. Kembali ke menu utama");
                    System.out.println();
                    System.out.print("Perintah : ");
                    int perintah = scanner.nextInt();
                    switch (perintah) {
                        case 1:
                            Product[] listProducts = productRepo.getProductList();
                            if (listProducts != null && listProducts.length > 1) {
                                for (Product product : listProducts) {
                                    if (product != null) {
                                        System.out.println(product.getName() + " - " + product.getPrice() + " - "
                                                + product.getStok());
                                    } else {
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("============================");
                                System.out.println("Toko belum memiliki produk");
                                System.out.println("============================");
                                System.out.println();
                            }
                            break;
                        case 2:
                            boolean inputNama = true;
                            boolean inputStok = true;
                            boolean inputHarga = true;
                            int stokProduk = 0;
                            long hargaProduk = 0;
                            String namaProduk = "";
                            while (inputNama) {
                                System.out.print("Masukkan nama produk : ");
                                namaProduk = scanner.next();
                                scanner.nextLine();
                                for (Product cekProduk : akunPenjual.getProductRepo().getProductList()) {
                                    if (cekProduk != null && namaProduk.equalsIgnoreCase(cekProduk.getName())) {
                                        System.out.println("Nama Produk sudah ada!");
                                        break;
                                    } else {
                                        inputNama = false;
                                    }
                                }
                            }
                            while (inputStok) {
                                try {

                                    System.out.print("Masukkan stok produk : ");
                                    stokProduk = scanner.nextInt();
                                    scanner.nextLine();
                                    if (stokProduk == 0) {
                                        System.out.println("Stok tidak boleh bernilai nol!");
                                    } else if (stokProduk < 0) {
                                        System.out.println("Stok tidak boleh bernilai negatif!");
                                    } else {
                                        inputStok = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Stok harus berupa angka!");
                                    scanner.next();
                                }

                            }
                            while (inputHarga) {
                                try {

                                    System.out.print("Masukkan harga produk : ");
                                    hargaProduk = scanner.nextLong();
                                    scanner.nextLine();
                                    if (hargaProduk == 0) {
                                        System.out.println("Harga tidak boleh bernilai nol!");
                                    } else if (hargaProduk < 0) {
                                        System.out.println("Harga tidak boleh bernilai negatif!");
                                    } else {
                                        inputHarga = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Harga harus berupa angka!");
                                    scanner.next();
                                }

                            }
                            Product produk = new Product(namaProduk, stokProduk, hargaProduk);
                            productRepo.addProductList(produk);
                            System.out.println("berhasil menambahkan produk baru!");
                            break;

                        case 3:
                            boolean inputNama2 = true;
                            boolean inputStok2 = true;
                            String namaProduk2 = "";
                            int stokProduk2 = 0;
                            if (akunPenjual.getProductRepo().getProductList().length == 1) {
                                System.out.println("============================");
                                System.out.println("Toko belum memiliki produk");
                                System.out.println("============================");
                                System.out.println();
                            } else {
                                while (inputNama2) {
                                    boolean productFound = false;
                                    System.out.print("Masukkan nama barang: ");
                                    namaProduk2 = scanner.next();
                                    scanner.nextLine();
                                    for (Product cekProduk : akunPenjual.getProductRepo().getProductList()) {
                                        if (cekProduk != null && namaProduk2.equalsIgnoreCase(cekProduk.getName())) {
                                            inputNama2 = false;
                                            productFound = true;
                                            break;
                                        }
                                    }
                                    if (!productFound) {
                                        System.out.println("Produk tidak ditemukan!");
                                    }
                                }
                                while (inputStok2) {
                                    try {

                                        System.out.print("Masukkan stok produk : ");
                                        stokProduk2 = scanner.nextInt();
                                        scanner.nextLine();
                                        if (stokProduk2 == 0) {
                                            System.out.println("Jumlah stok produk tidak bertambah!");
                                        } else if (stokProduk2 < 0) {
                                            System.out.println("Stok tidak boleh bernilai negatif!");
                                        } else {
                                            inputStok2 = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Stok harus berupa angka!");
                                        scanner.next();
                                    }

                                }
                                listProducts = productRepo.getProductList();
                                for (Product product : listProducts) {
                                    if (product != null) {
                                        if (product.getName().equalsIgnoreCase(namaProduk2)) {
                                            product.setStok(product.getStok() + stokProduk2);
                                            System.out.println("Stok " + product.getName()
                                                    + " berhasil ditambah! Stok saat ini: " + product.getStok());
                                            break;
                                        }
                                    }
                                }
                            }
                            break;

                        case 4:
                            boolean inputNama3 = true;
                            boolean inputHarga2 = true;
                            String namaProduk3 = "";
                            long hargaProduk2 = 0;
                            if (akunPenjual.getProductRepo().getProductList().length == 1) {
                                System.out.println("============================");
                                System.out.println("Toko belum memiliki produk");
                                System.out.println("============================");
                                System.out.println();
                            } else {
                                while (inputNama3) {
                                    boolean productFound = false;
                                    System.out.print("Masukkan nama barang: ");
                                    namaProduk3 = scanner.next();
                                    scanner.nextLine();
                                    for (Product cekProduk : akunPenjual.getProductRepo().getProductList()) {
                                        if (cekProduk != null && namaProduk3.equalsIgnoreCase(cekProduk.getName())) {
                                            inputNama3 = false;
                                            productFound = true;
                                            break;
                                        }
                                    }
                                    if (!productFound) {
                                        System.out.println("Produk tidak ditemukan!");
                                    }
                                }
                                while (inputHarga2) {
                                    try {

                                        System.out.print("Masukkan harga produk : ");
                                        hargaProduk2 = scanner.nextLong();
                                        scanner.nextLine();
                                        if (hargaProduk2 == 0) {
                                            System.out.println("harga tidak boleh bernilai nol!");
                                        } else if (hargaProduk2 < 0) {
                                            System.out.println("harga tidak boleh bernilai negatif!");
                                        } else {
                                            inputHarga2 = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Stok harus berupa angka!");
                                        scanner.next();
                                    }

                                }
                                listProducts = productRepo.getProductList();
                                for (Product product : listProducts) {
                                    if (product != null) {
                                        if (product.getName().equalsIgnoreCase(namaProduk3)) {
                                            product.setPrice(hargaProduk2);
                                            System.out.println(
                                                    "Harga " + product.getName() + " diperbarui: "
                                                            + product.getPrice() * 1.00);
                                            break;
                                        }
                                    }
                                }
                            }
                            break;

                        case 5:
                            PengirimanRepository repoPengiriman = akunPenjual.getRepoPengiriman();
                            if (repoPengiriman.getPengirimanList().length == 1) {
                                System.out.println("==================================");
                                System.out.println("Tidak ada barang yang dapat dikirim");
                                System.out.println("==================================");
                                System.out.println();
                            } else {
                                for (Pengiriman pengiriman : repoPengiriman.getPengirimanList()) {
                                    if (pengiriman != null) {

                                        System.out.println("==================================");
                                        System.out.printf("%-15s %-10s\n", "ID Transaksi", pengiriman.getIdTransaksi());
                                        LocalDate date = pengiriman.getTimeStamp();
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy",
                                                new Locale("id"));
                                        String formattedDate = date.format(formatter);
                                        System.out.printf("%-15s %-10s\n", "Tanggal", formattedDate);
                                        pengiriman.send();
                                        System.out.printf("%-15s %-10s\n", "Status", "Dikirim");
                                    }
                                }
                            }
                            break;

                        case 6:
                            SoldProductRepository repoTerjual = akunPenjual.getLaporan();
                            SoldProduct[] listTerjual = repoTerjual.getProductList();
                            if (listTerjual.length == 1) {
                                System.out.println("================================");
                                System.out.println("Laporan pendapatan masih kosong!");
                                System.out.println("================================");
                                System.out.println();
                            } else {
                                System.out.println("======= LAPORAN PENDAPATAN =======");
                                for (SoldProduct soldProduct : listTerjual) {
                                    if (soldProduct != null) {
                                        System.out.printf("%-10s %2d\n", soldProduct.getProductName(),
                                                soldProduct.getAmount());
                                    }
                                }
                                System.out.println("==================================");
                                System.out.println("Total pendapatan: " + repoTerjual.getRevenue());
                                System.out.println();
                            }
                            break;

                        case 7:
                            penjual = false;
                            break;

                        default:
                            System.out.println("Input tidak valid");
                            ;
                    }

                }
            }
        }
    }

    public void hariEsok(int daysToAdd) {
        System.out.println();
        // mengambil hari esok sesuai jumlah oenambahan hari
        LocalDate tomorrow = LocalDate.now().plusDays(daysToAdd);
        Burhanpedia.hariIni = tomorrow;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", new Locale("id"));
        System.out.println("Tanggal : " + tomorrow.format(formatter));
        for (Penjual penjual : repoPenjual.getPenjualList()) {
            if (penjual != null) {
                PengirimanRepository repoPengiriman = penjual.getRepoPengiriman();
                for (Pengiriman pengiriman : repoPengiriman.getPengirimanList()) {
                    if (pengiriman != null) {
                        pengiriman.nextDay();
                        repoPengiriman.removePengiriman(pengiriman.getId());
                    }
                }
            }
        }

        System.out.println("pok pok pok!");
        System.out.println();
    }

    public PenjualRepository getPenjualRepository() {
        return this.repoPenjual;
    }

    public PembeliRepository getPembeliRepository() {
        return this.repoPembeli;
    }

    public VoucherRepository getVoucherRepository() {
        return this.repoVoucher;
    }

}
