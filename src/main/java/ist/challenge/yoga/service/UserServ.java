package ist.challenge.yoga.service;

import ist.challenge.yoga.model.UserModel;

import java.util.List;

public interface UserServ {
    List<UserModel> getUser();

    boolean UserInput(UserModel userModel);

    boolean isNameExist(String username);

    boolean isPwdExist(String psswd);

    List<UserModel> getUserPswd(String psswd, String username);

    List<UserModel> getUser(String username);

    List<UserModel> getPswd(String psswd);

    void updateUser(UserModel userModel);

    void updatePsswd(UserModel userModel);
}
