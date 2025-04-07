package repositories;
import model.*;

public class AdminRepository {

    private Admin[] adminList;

    public AdminRepository(){
        adminList = new Admin[3];
        adminList[0] = new Admin("admin","admin");
        adminList[1] = new Admin("root","toor");
        adminList[2] = new Admin("dekdepe","aku_CinTaJaVa");
        

    }

    public boolean login(String uname, String pass) {
        Admin[] adminList = getAdminList();
        for (int i = 0; i < 3 ; i++){
            if(adminList[i].getUname().equals(uname) && adminList[i].getPassword().equals(pass)){
                return true;
            }
            
        } return false;
    }

    public Admin[] getAdminList() {
        return adminList;
    }

    public Admin getAdmin(String Username) {
        Admin[] adminList = this.getAdminList();
        for (int i = 0; i < 3; i++) {
            if (adminList[i].getUname().equals(Username)) {
                return adminList[i];
            }
        }
        return null;
    }

}
