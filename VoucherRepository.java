import java.util.Random;

public class VoucherRepository {
    Voucher[] voucherList = new Voucher[1];
    int numOfVoucher = 0;

    public void generateVoucher() {
        String voucher = "";
        Random random = new Random();
        String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String convertedVoucher = "";
        // melakukan 10 kali loop untuk mengambil sembarang karakter alfabet
        for (int i = 0; i < 10; i++) {
            int alfaIndex = random.nextInt(26);
            int count = ((alfaIndex + 10) * i) % 10; // melakukan konversi code 93
            convertedVoucher += count + ""; // menambahkan digit code 93
            voucher += alfabet.charAt(alfaIndex) + "";/* menambahkan karakter voucher */
        }
        System.out.println("Voucher berhasil dibuat: " + convertedVoucher);
        Voucher newVoucher = new Voucher(convertedVoucher, false);
        addVoucherList(newVoucher);
    }

    public void addVoucherList(Voucher newVoucher){
        Voucher[] temp = new Voucher[numOfVoucher+2]                   ;
        Voucher[] newVoucherList = {newVoucher};
        System.arraycopy(voucherList, 0, temp, 0, numOfVoucher);
        System.arraycopy(newVoucherList, 0, temp, numOfVoucher, newVoucherList.length);
        numOfVoucher++;
        voucherList = temp;
    }

    public Voucher getVoucherById(String id){
        return voucherList[0];
    }

    public Voucher[] getVoucherList(){
        return voucherList;
    }
}
