package repositories;
import java.util.UUID;
import model.*;

public class PembeliRepository {
    private Pembeli[] pembeliList = new Pembeli[1];
    static int numOfPembeli = 0;

    public void addPembeliList(Pembeli pembeli) {
        Pembeli[] temp = new Pembeli[numOfPembeli+2];
        Pembeli[] newPembeliList = {pembeli};
        System.arraycopy(pembeliList, 0, temp, 0, numOfPembeli);
        System.arraycopy(newPembeliList, 0, temp, numOfPembeli, newPembeliList.length);
        numOfPembeli++;
        pembeliList = temp;

    }

    public Pembeli[] getPembeliList(){
        return pembeliList;
    }

    public Pembeli getPembeliByID(UUID Id){
        for (int i = 0; i < numOfPembeli; i++) {
            if (pembeliList[i].getIdPembeli().equals(Id)) {
                return pembeliList[i];
                }
            }
        return null;
    }

    public boolean login(String username, String password){
        
       if (pembeliList.length <= 1){
            return false;
       }
        
        for (int i = 0; i < pembeliList.length; i++) {
            if (pembeliList[i] != null){
                if (pembeliList[i].getUsername().equals(username) && pembeliList[i].getPassword().equals(password)) {
                    System.out.println("Login berhasil! selamat datang " + username);
                    return true;
                }
            }
        }
        return false;
    }

    public Pembeli getPembeliByUsername(String uname){
        for (Pembeli pembeli : pembeliList){
            if (pembeli != null && pembeli.getUsername().equals(uname)){
                return pembeli;
            }
        }
        return null;
    }
}
