package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;
import src.Burhanpedia;

public class Transaksi {
    private String id;
    private LocalDate timeStamp = Burhanpedia.hariIni;
    private String voucherID;
    private Pengiriman[] statusPengiriman = new Pengiriman[1];
    static int numOfPengiriman;
    private TransaksiProduct[] produkDibeli = new TransaksiProduct[1];
    static int numOfProduct;

    public void addTransaksi(TransaksiProduct transaksi){
        TransaksiProduct[] temp = new TransaksiProduct[numOfProduct+2];
        TransaksiProduct[] newList = {transaksi};
        System.arraycopy(this.produkDibeli, 0, temp, 0, numOfProduct);
        System.arraycopy(newList, 0, temp, numOfProduct, newList.length);
        numOfProduct++;
        this.produkDibeli = temp;
    
    }

    public TransaksiProduct[] getProductList(){
        return this.produkDibeli;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucherID(){
        return this.voucherID;
    }

    public void addPengiriman(Pengiriman pengiriman) {
        Pengiriman[] temp = new Pengiriman[numOfPengiriman+2];
        Pengiriman[] newPengirimanList = {pengiriman};
        System.arraycopy(this.statusPengiriman, 0, temp, 0, numOfPengiriman);
        System.arraycopy(newPengirimanList, 0, temp, numOfPengiriman, newPengirimanList.length);
        numOfPengiriman++;
        this.statusPengiriman = temp;
    }
    
    public Pengiriman[] getStatusPengiriman(){
        return this.statusPengiriman;
    }

    public void setTimeStamp(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", new Locale("id"));
        String formattedDate = date.format(formatter);
        this.timeStamp = date;
    }
    
    public long calculateTotal(){
        long total = 0;
        for(TransaksiProduct product : produkDibeli){
            total += product.getPrice() * product.getAmount() * 1.03;
        }
        return total;
    }
    
    public void setId(int urutan){
        String formatted = this.timeStamp.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.id = "TRX" + formatted + String.format("%03d", urutan);
    }

    public LocalDate getDate(){
        return this.timeStamp;
    }

    public String getId(){
        return this.id;
    }

    public void setPengiriman(){
        for (TransaksiProduct product : this.produkDibeli){
            if (product != null){
                Pengiriman pengiriman = new Pengiriman();
                UUID idBarang = product.getProductId();
                pengiriman.setId(idBarang);
            }
        }
    }
}