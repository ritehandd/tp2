package model;
import java.util.UUID;

public class Product {
    private UUID productID;
    private String name;
    private int stok;
    private long price;
    private String namaToko;

    public Product(String name, int stok, long price) {
        this.productID = UUID.randomUUID();
        this.name = name;
        this.stok = stok;
        this.price = price;
    }

    public UUID getProductID() {
        return this.productID;
    }

    public String getNamaProduk(){
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public String getNamaToko(){
        return this.namaToko;
    }

    public int getStok() {
        return this.stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}

