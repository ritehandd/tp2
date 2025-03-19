import java.util.UUID;

public class Cart {
    private CartProduct[] keranjang;
    static int numOfProduct = 0;

    public void addToCart(UUID productId){
        // Add product to cart
        CartProduct[] temp = new CartProduct[numOfProduct+1];
        CartProduct[] newCartList = {productId};
        System.arraycopy(keranjang, 0, temp, 0, numOfProduct);
        System.arraycopy(newCartList, 0, temp, numOfProduct, newCartList.length);
        numOfProduct++;
       keranjang = temp;
    }

    public CartProduct[] getCart(){
        return this.keranjang;
    }
}
