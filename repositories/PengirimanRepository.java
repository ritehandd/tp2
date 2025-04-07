package repositories;
import java.util.UUID;
import model.*;

public class PengirimanRepository {
    private Pengiriman[] pengiriman = new Pengiriman[1];
    public static int numOfPengiriman = 0;

    public void addPengiriman(Pengiriman pengiriman) {
        Pengiriman[] temp = new Pengiriman[numOfPengiriman+2];
        Pengiriman[] newPengirimanList = {pengiriman};
        System.arraycopy(this.pengiriman, 0, temp, 0, numOfPengiriman);
        System.arraycopy(newPengirimanList, 0, temp, numOfPengiriman, newPengirimanList.length);
        numOfPengiriman++;
        this.pengiriman = temp;
    }

     public void removePengiriman(UUID ID){
        // Remove product from cart
        int index = 0;
        for (int i = 0; i < this.pengiriman.length; i++) {
            if (this.pengiriman[i] != null){
                if (this.pengiriman[i].getId().equals(ID)) {
                    index = i;
                }
            }
        }
        
        Pengiriman[] temp = new Pengiriman[numOfPengiriman];
        System.arraycopy(this.pengiriman, 0, temp, 0, index+1);
        System.arraycopy(this.pengiriman, index + 1, temp, index, numOfPengiriman - index - 1);
        this.pengiriman = temp;
        numOfPengiriman--;

    }

    public Pengiriman[] getPengirimanList(){
        return this.pengiriman;
    }

}
