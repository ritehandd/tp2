package repositories;
import model.*;

public class SoldProductRepository {
    private SoldProduct[] produkTerjual = new SoldProduct[1];
    private long pendapatan;
    static int numOfProduct=0;

    public SoldProduct[] getProductList(){
        return this.produkTerjual;
    }

    public void addSoldProduct(SoldProduct product){

        SoldProduct[] temp = new SoldProduct[numOfProduct+2];
        SoldProduct[] newSoldList = {product};
        System.arraycopy(produkTerjual, 0, temp, 0, numOfProduct);
        System.arraycopy(newSoldList, 0, temp, numOfProduct, newSoldList.length);
        numOfProduct++;
        produkTerjual = temp;
    }

    public void addRevenue(long pendapatan){
        this.pendapatan += pendapatan;
    }

    public long getRevenue(){
        return this.pendapatan;
    }
}
