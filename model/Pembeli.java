package model;
import java.util.UUID;
import repositories.*;

public class Pembeli{

    private String username;
    private String password;
    private UUID idPembeli;
    private long balance;
    private Cart keranjang = new Cart();
    private TransaksiRepository riwayatTransaksi = this.getRiwayatTransaksi();


    public Pembeli(String username, String password){
        this.username = username;
        this.password = password;
        this.idPembeli = UUID.randomUUID();
        this.keranjang = new Cart();
        this.riwayatTransaksi = new TransaksiRepository();
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
        
       
            // menambahkan saldo
            long saldoAkhir = this.getBalance() + balance;
            this.balance = saldoAkhir;
        

    }

    public Cart getCart() {
        return this.keranjang;
    }

    public TransaksiRepository getRiwayatTransaksi(){
        return this.riwayatTransaksi;
    }

}
