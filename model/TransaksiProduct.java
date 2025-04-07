package model;
import java.util.UUID;

public class TransaksiProduct {
    private UUID productId;
    private int amount;
    private String name;
    private long price;
    
    public TransaksiProduct(UUID productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
    
    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getPrice() {
        return this.price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
}

