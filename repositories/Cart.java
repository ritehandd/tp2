package repositories;
import java.util.UUID;
import model.CartProduct;

public class Cart {
    private CartProduct[] keranjang = new CartProduct[1];
    static int numOfProduct = 0;

    public void addToCart(UUID productId){
        // Add product to cart    
        CartProduct product = new CartProduct(productId,1);
        CartProduct[] temp = new CartProduct[numOfProduct+2];
        CartProduct[] newCartList = {product};
        System.arraycopy(this.keranjang, 0, temp, 0, numOfProduct);
        System.arraycopy(newCartList, 0, temp, numOfProduct, newCartList.length);
        numOfProduct++;
        this.keranjang = temp;
    }

    public CartProduct[] getCartProduct(){
        return this.keranjang;
    }

    public void removeCart(UUID productID){
        // Remove product from cart
        int index = 0;
        for (int i = 0; i < this.keranjang.length; i++) {
            if (this.keranjang[i] != null){
                if (this.keranjang[i].getProductId().equals(productID)) {
                    index = i;
                }
            }
        }

        CartProduct[] temp = new CartProduct[numOfProduct];
        System.arraycopy(this.keranjang, 0, temp, 0, index+1);
        System.arraycopy(this.keranjang, index + 1, temp, index, numOfProduct - index - 1);
        this.keranjang = temp;
        numOfProduct--;

    }
    public boolean containsProduct(UUID productId) {
        for (CartProduct product : getCartProduct()) {
            if (product != null && product.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    } 
}
