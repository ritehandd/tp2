import java.util.Scanner;
import java.util.UUID;

public class Pembeli{

    private String username;
    private String password;
    private UUID idPembeli;
    private long balance;
    private Cart keranjang = new Cart();

     public void menuPembeli(){

        Scanner scanner = new Scanner(System.in);
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
                    "4. Beli Barang\r\n"
                    + //
                    "5. Generate Voucher\r\n"
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
                    long saldo = this.getBalance();
                    String saldoAkhirString = String.format("Saldo saat ini: %.2f", saldo);
                    System.out.println("==============================");
                    System.out.println(saldoAkhirString);
                    System.out.println("==============================");
                    System.out.println();
                    break;
                case 2:
                    saldo = this.getBalance();
                    System.out.print("Masukkan jumlah saldo yang ingin ditambahkan : ");
                    long topUp = scanner.nextLong();
                    scanner.nextLine();
                    this.setBalance(topUp);
                    break;
                
                case 3:
                    
                    break;

                case 8:
                    pembeli = false;
                    break;

                default:
                System.out.println("Perintah tidak ditemukan");
            }
        }
    }

    public Pembeli(String username, String password){
        this.username = username;
        this.password = password;
        this.idPembeli = UUID.randomUUID();
        this.keranjang = new Cart();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UUID getIdPembeli() {
        return this.idPembeli;
    }
    public long getBalance() {
        return this.balance;
    }
    public void setBalance(long balance) {
        // melempar error jika saldo tambahan lebih kecil dari 1
        if (balance <= 0) {
            throw new IllegalArgumentException("Nominal tidak valid!");
        } else {
            // menambahkan saldo
            long saldoAkhir = this.getBalance() + balance;
            String saldoAkhirFormat = String.format("Saldo berhasil ditambah! Saldo saat ini: %.2f", saldoAkhir);
            System.out.println(saldoAkhirFormat);
            System.out.println();
            this.balance = saldoAkhir;
        }

    }

    public void cekDaftarBarang(){
        Burhanpedia main = Burhanpedia();
        PenjualRepository repoPenjual = main.getPenjualRepository();
    }
}
