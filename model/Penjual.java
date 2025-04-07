package model;
import java.util.UUID;
import repositories.*;

public class Penjual {

    private String username;
    private String password;
    private UUID idPenjual;
    private ProductRepository productRepo;
    private SoldProductRepository laporanTerjual;
    private PengirimanRepository repoPengiriman;

    public Penjual(String username, String password, String namaToko){
        this.username = username;
        this.password = password;
        this.idPenjual = UUID.randomUUID();
        this.productRepo = new ProductRepository(namaToko);
        this.laporanTerjual = new SoldProductRepository();
        this.repoPengiriman = new PengirimanRepository();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UUID getIdPenjual() {
        return this.idPenjual;
    }

    public ProductRepository getProductRepo(){
        return this.productRepo;
    }

    public SoldProductRepository getLaporan(){
        return this.laporanTerjual;
    }

    public PengirimanRepository getRepoPengiriman(){
        return this.repoPengiriman;
    }
}
