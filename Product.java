import java.util.UUID;

public class Product {
    private UUID productID;
    private String name;
    private int stok;
    private long price;

    public Product(String name, int stok, long price) {
        this.productID = UUID.randomUUID();
        this.name = name;
        this.stok = stok;
        this.price = price;
    }

    public UUID getProductID() {
        return this.productID;
    }

    public String getName() {
        return this.name;
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

