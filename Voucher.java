public class Voucher {
    private String id;
    private boolean isUsed;

    public Voucher(String id, boolean isUsed){
        this.id = id;
        this.isUsed = isUsed;
    }

    public int hitungDiskon(String voucher, int total) {
        String firstEl = voucher.charAt(0) + ""; // mengambil figit pertama voucher
        String secondEl = voucher.charAt(voucher.length() - 1) + "";// mengambil digit terakhri voucher
        // konversi string menjadi integer
        int firstElInt = Integer.parseInt(firstEl);
        int secondElInt = Integer.parseInt(secondEl);
        // melakukan perkalian
        int diskon = (firstElInt * secondElInt);
        total += diskon; // menambahkan hasil ke total diskon
        if (voucher.length() == 2) { // base case ketika sudah diakhir panjang voucher adalah 2
            return total;
        }

        else {
            // melakukan rekursi perhitungan diskon
            return hitungDiskon(voucher.substring(1, voucher.length() - 1), total);
        }
    }

    public String getId(){
        return id;
    }

    public boolean getStatus(){
        return isUsed;
    }
}
