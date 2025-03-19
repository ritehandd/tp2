import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Penjual {

    private String username;
    private String password;
    private String namaToko;
    private UUID idPenjual;
    private ProductRepository productRepo = this.getProductRepo();


    public void penjualMenu(){
        boolean penjual = true;
        Scanner scanner = new Scanner(System.in);
        while (penjual) {
            System.out.println("===== MENU PENJUAL =====\r\n"
                                + //
                                "1. Cek Produk\r\n"
                                + //
                                "2. Tambah Produk\r\n"
                                + //
                                "3. Tambah Stok\r\n"
                                + //
                                "4. Ubah Harga Barang\r\n"
                                + //
                                "5. Generate Voucher\r\n"
                                + //
                                "6. Kirim Barang\r\n"
                                + //
                                "7. Lihat Laporan Pendapatan\r\n"
                                + //
                                "8. Kembali ke menu utama");
                        System.out.println();
                        System.out.print("Perintah : ");
                        int perintah = scanner.nextInt();
                        switch (perintah) {
                            case 1:
                                Product[] listProducts = this.productRepo.getProductList();
                                if (listProducts != null) {
                                    for (Product product : listProducts) {
                                        if(product != null) {
                                            System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getStok());
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("============================");
                                    System.out.println("Toko belum memiliki produk");
                                    System.out.println("============================");
                                    System.out.println();
                                }
                                break;
                            case 2:
                                System.out.print("Masukkan nama produk : ");
                                String namaProduk = scanner.next();
                                scanner.nextLine();
                                System.out.print("Masukkan stok produk : ");
                                int stokProduk = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Masukkan harga produk : ");
                                long hargaProduk = scanner.nextLong();
                                scanner.nextLine();
                                Product produk = new Product(namaProduk, stokProduk, hargaProduk);
                                productRepo.addProductList(produk);
                                System.out.println("berhasil menambahkan produk baru!");
                                break;

                            case 3:
                                System.out.print("Masukkan nama barang : ");
                                namaProduk = scanner.next();
                                scanner.nextLine();
                                System.out.print("Masukkan jumlah stok yang ingin ditambah: ");
                                stokProduk = scanner.nextInt();
                                scanner.nextLine();
                                listProducts = this.productRepo.getProductList();
                                for (Product product : listProducts) {
                                    if(product != null) {
                                        if (product.getName().equalsIgnoreCase(namaProduk)) {
                                            product.setStok(product.getStok() + stokProduk);
                                            System.out.println("Stok " + product.getName() + " berhasil ditambah! Stok saat ini: " + product.getStok());
                                            break;
                                        }
                                    }
                                }
                                break;
                            
                            case 4:
                                System.out.print("Masukkan nama barang : ");
                                namaProduk = scanner.next();
                                scanner.nextLine();
                                System.out.print("Masukkan harga barnag yang baru: ");
                                hargaProduk = scanner.nextLong();
                                listProducts = this.productRepo.getProductList();
                                scanner.nextLine();
                                for (Product product : listProducts) {
                                    if(product != null) {
                                        if (product.getName().equalsIgnoreCase(namaProduk)) {
                                            product.setPrice(hargaProduk);
                                            System.out.println("Harga " + product.getName() + " diperbarui: " + product.getPrice());
                                            break;
                                        }
                                    }
                                }
                                break;
                                
                            case 8:
                                penjual = false;
                                break;
                        
                            default:
                                System.out.println("Input tidak valid");;
                        }

        }
    }

    public Penjual(String username, String password, String namaToko){
        this.username = username;
        this.password = password;
        this.namaToko = namaToko;
        this.idPenjual = UUID.randomUUID();
        this.productRepo = new ProductRepository();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNamaToko() {
        return this.namaToko;
    }

    public UUID getIdPenjual() {
        return this.idPenjual;
    }

    public ProductRepository getProductRepo(){
        return this.productRepo;
    }
}
