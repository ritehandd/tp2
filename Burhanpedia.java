import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Burhanpedia {
    private static int daysToAdd = 1;
    private PenjualRepository repoPenjual = new PenjualRepository();
    private PembeliRepository repoPembeli = new PembeliRepository();
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
                    hariEsok(daysToAdd);
                    break;
                case 4:
                    System.out.println();
                    System.out.println("===========================================");
                    System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                    System.out.println("===========================================");
                    main = false;
                    break;
                default:
                    break;
            }
            
         }


    }
    
    public void register(){

        PenjualRepository repoPenjual = this.repoPenjual;
        Penjual[] penjualList = repoPenjual.getPenjualList();
        PembeliRepository repoPembeli = new PembeliRepository();
        Pembeli[] pembeliList = repoPembeli.getPembeliList();

        Scanner scanner = new Scanner(System.in);
        System.out.println("=========== Registrasi ===========");
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
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

        if (perintah == 1){
            System.out.print("Masukkan nama toko: ");
            String namaToko = scanner.nextLine();
            Penjual akunPenjual = new Penjual(username, password, namaToko);
            System.out.println("Registrasi akun penjual berhasil!");
            repoPenjual.addPenjualList(akunPenjual);
            System.out.println();

        } else if (perintah == 2){
            Pembeli akunPembeli = new Pembeli(username, password);
            System.out.println("Registrasi akun pembeli berhasil!");
            repoPembeli.addPembeliList(akunPembeli);
            System.out.println();
        }
    }

    public void login(){
        PenjualRepository repoPenjual = this.repoPenjual;
        Penjual[] penjualList = repoPenjual.getPenjualList();
        PembeliRepository repoPembeli = new PembeliRepository();
        Pembeli[] pembeliList = repoPembeli.getPembeliList();
        AdminRepository repoAdmin = new AdminRepository();
        Admin[] adminList = repoAdmin.getAdminList();
        Pembeli akunPembeli;
        Penjual akunPenjual;

        boolean aksesPenjual = false;
        boolean aksesPembeli = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("=========== Login ===========");
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        
        int index;
        boolean admin = repoAdmin.login(username, password);
        if (admin == true) {
            System.out.println("Login berhasil. Selamat datang, Admin!");
            System.out.println();
            Admin.adminMenu();
        } else {
            for (int i = 0; i < pembeliList.length - 1; i++) {
                if (pembeliList[i].getUsername().equals(username) && pembeliList[i].getPassword().equals(password)) {
                    System.out.println("Login berhasil! selamat datang " + username);
                    aksesPembeli = true;
                    break;
                }
            }
            if (aksesPembeli) {
                aksesPembeli = false;

            } else {
                for (Penjual penjual : penjualList) {
                    if (penjual.getUsername().equals(username) && penjual.getPassword().equals(password)) {
                        System.out.println("Login berhasil! Selamat datang " + username);
                        aksesPenjual = true;
                        akunPenjual = penjual;
                        if (aksesPenjual) {
                            akunPenjual.penjualMenu();
                            break;
                        }

                    }
                }
            }
        }
    }
    public static void hariEsok(int daysToAdd) {
        System.out.println();
        // mengambil hari esok sesuai jumlah oenambahan hari
        LocalDate tomorrow = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", new Locale("id"));
        System.out.println("Tanggal : " + tomorrow.format(formatter));
        // jika tidak ada barang yg dikirim
        /*if (statusPengiriman == false) {
            // jika ada barang yang dikirim
            } else if (statusPengiriman == true) {
                System.out.println("Hari telah berganti. Barang sudah sampai!");
                statusPengiriman = false;
                }*/
        System.out.println("pok pok pok!");
        System.out.println();
        //return statusPengiriman;

    }

    public PenjualRepository getPenjualRepository(){
        return this.repoPenjual;
    }

    public PembeliRepository getPembeliRepository(){
        return this.repoPembeli;
    }
   
}

