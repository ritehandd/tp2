import java.nio.channels.Pipe.SourceChannel;
import java.util.Random;
import java.util.Scanner;

public class Admin {
    private String uname;
    private String password;

    public static void adminMenu() {

        VoucherRepository repoVoucher = new VoucherRepository();
        Scanner scanner = new Scanner(System.in);
        boolean admin = true;
        while (admin){
            System.out.println("========== MENU ADMIN =========");
            System.out.println("1. Generate Voucher");
            System.out.println("2. Lihat Voucher");
            System.out.println("3. Kembali ke menu utama");
            System.out.println();
            System.out.print("Perintah : ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    repoVoucher.generateVoucher();
                    System.out.println();
                    System.out.println(repoVoucher.getVoucherList().length - 1);
                    break;
                case 2:
                    Voucher[] voucherList = repoVoucher.getVoucherList();
                    System.out.println("=========================");
                    for(int i = 0; i < voucherList.length - 1; i++){
                        Voucher voucher = voucherList[i];
                        System.out.print(voucher.getId());
                        if(voucher.getStatus() == false){
                            System.out.print(" [Belum digunakan]");
                        }
                        else {
                            System.out.print(" [Sudah digunakan]");
                        }
                        System.out.println();
                    }
                    System.out.println("=========================");
                    System.out.println();
                    break;
                case 3:
                    admin = false;
                    System.out.println();
                    break;
                default:
                    break;
            }
        }
        
    }

    public Admin(String Uname, String Pword) {
        this.uname = Uname;
        this.password = Pword;
    }
    
    public String getUname() {
        return this.uname;
    }
    
    
    public String getPassword() {
        return this.password;
    }
    

}