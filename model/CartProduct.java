package model;
import java.util.UUID;

public class CartProduct {
    private UUID productId;
    private int amount;
    private String name;
    private long price;

    public CartProduct(UUID productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
    public void setPrice(long price){
        this.price = price;
    }

    public long getPrice(){
        return this.price;
    }

}
