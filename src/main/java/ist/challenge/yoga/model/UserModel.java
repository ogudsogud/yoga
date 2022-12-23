package ist.challenge.yoga.model;

import com.sun.istack.NotNull;

public class UserModel {
    public int iduser;
    public String username;
    public String psswd;

    public int status;

    public UserModel(int iduser, String username, String psswd, int status) {
        this.iduser = iduser;
        this.username = username;
        this.psswd = psswd;
        this.status = status;
    }

    public UserModel() {

    }


    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
