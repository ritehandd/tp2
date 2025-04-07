package repositories;
import java.util.UUID;
import model.*;

public class PenjualRepository {
    private Penjual[] penjualList = new Penjual[1];
    static int numOfPenjual = 0;

    public void addPenjualList(Penjual penjual) {
        Penjual[] temp = new Penjual[numOfPenjual+2];
        Penjual[] newPenjualList = {penjual};
        System.arraycopy(penjualList, 0, temp, 0, numOfPenjual);
        System.arraycopy(newPenjualList, 0, temp, numOfPenjual, newPenjualList.length);
        numOfPenjual++;
        penjualList = temp;

    }

    public Penjual[] getPenjualList(){
        return penjualList;
    }   

    public Penjual getPenjualById(UUID id) {
        for (int i = 0; i < numOfPenjual; i++) {
            if (this.penjualList[i].getIdPenjual().equals(id)){
                return this.penjualList[i];
            }
        }
        return null;
    }

    public Penjual getPenjualByUsername(String username) {
        for (int i = 0; i < numOfPenjual; i++) {
            if (this.penjualList[i].getUsername().equals(username)){
                return this.penjualList[i];
            }
        }
        return null;
    }

    public boolean login(String username ,  String password){
        for (int i = 0; i < penjualList.length - 1; i++) {
            if (penjualList[i].getUsername().equals(username) && penjualList[i].getPassword().equals(password)) {
                System.out.println("Login berhasil! selamat datang " + username);
                return true;
            }
        }
        return false;
    }

    public Penjual getPenjualByID(UUID id){
        for (Penjual penjual : penjualList) {
            if (penjual != null && penjual.getIdPenjual().equals(id)) {
                return penjual;
            }
        } 
        return null;  
    }
}

