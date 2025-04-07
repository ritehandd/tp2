package repositories;
import model.*;

public class TransaksiRepository {
    private Transaksi[] transaksiList = new Transaksi[1];
    public static int numOfTransaksi = 0;

    public Transaksi[] getList() {
        return this.transaksiList;
    }

    public void addTransaksi(Transaksi transaksi){
        Transaksi[] temp = new Transaksi[numOfTransaksi+2];
        Transaksi[] newList = {transaksi};
        System.arraycopy(transaksiList, 0, temp, 0, numOfTransaksi);
        System.arraycopy(newList, 0, temp, numOfTransaksi, newList.length);
        numOfTransaksi++;
        transaksiList = temp;
    
    }
}   
