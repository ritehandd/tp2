public class ProductRepository {
    private String namaToko;
    private Product[] productList = new Product[1];
    int numOfProduct = 0;

    public Product[] getProductList() {
        return this.productList;
    
    }

    public Product getProductById(String id){
        for (Product product : productList){
            if (product.getProductID().equals(id)){
                return product;
            }
        }
        return null;
    }

    public void addProductList(Product produk){
        
        Product[] temp = new Product[numOfProduct+2];
        Product[] newProductList = {produk};
        System.arraycopy(productList, 0, temp, 0, numOfProduct);
        System.arraycopy(newProductList, 0, temp, numOfProduct, newProductList.length);
        numOfProduct++;
        productList = temp;
    }
}
