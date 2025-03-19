import java.util.UUID;

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

    public Pembeli getPembeliByID(String Id){
        for (int i = 0; i < numOfPembeli; i++) {
            if (pembeliList[i].getIdPembeli().equals(Id)) {
                return pembeliList[i];
                }
            }
        return null;
    }
}
