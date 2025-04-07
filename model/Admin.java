package model;

public class Admin {
    private String uname;
    private String password;


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