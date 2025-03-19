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

    public Penjual getPenjualById(int id) {
        for (int i = 0; i < numOfPenjual; i++) {
            if (this.penjualList[i].getIdPenjual().equals(id)){
                return this.penjualList[i];
            }
        }
        return null;
    }
}

