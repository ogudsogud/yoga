package ist.challenge.yoga.controller;


import ist.challenge.yoga.model.ErrCode;
import ist.challenge.yoga.model.UserModel;
import ist.challenge.yoga.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserServ userServ;

    @RequestMapping("/list")
    public ResponseEntity<List<UserModel>> getALlUser(){
        if(userServ.getUser().isEmpty()) {
            return new ResponseEntity(new ErrCode("409", "User masih kosong"), HttpStatus.NOT_FOUND);
        }
        List<UserModel> list = userServ.getUser();
        return new ResponseEntity<List<UserModel>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity<Void> createCluster(@RequestBody UserModel userModel, UriComponentsBuilder uriComponentsBuilder) {
        if (userServ.isNameExist(userModel.getUsername()) == true) {
            userServ.UserInput(userModel);
            return new ResponseEntity(new ErrCode("201", "User berhasil Disimpan"), HttpStatus.CREATED);
        }
        return  new ResponseEntity(new ErrCode("409", "Username sudah terpakai"), HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Void> usrpswd(@Validated @RequestBody UserModel userModel, UriComponentsBuilder uriComponentsBuilder) {
        if (userModel.username.isEmpty() || userModel.psswd.isEmpty()) {
            return new ResponseEntity(new ErrCode("400", "Username atau password kosong"), HttpStatus.NOT_FOUND);
        } else {
            if (userServ.getUserPswd(userModel.getUsername(), userModel.getPsswd()).isEmpty()) {
                return new ResponseEntity(new ErrCode("401", "Login Gagal"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(new ErrCode("201", "Sukses Login"), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel) {
        if (userServ.isNameExist(userModel.getUsername())==true) {
            userServ.updateUser(userModel);
            return new ResponseEntity(new ErrCode("201", "User berhasil diubah"), HttpStatus.OK);
        }
            return new ResponseEntity(new ErrCode("409", "Username sudah terpakai"), HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.PUT)
    public ResponseEntity<UserModel> updatePswd(@RequestBody UserModel userModel) {
        if (userServ.isPwdExist(userModel.getPsswd())==true) {
            userServ.updatePsswd(userModel);
            return new ResponseEntity(new ErrCode("201", "Password berhasil diubah"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrCode("409", "Password tidak boleh sama dengan sebelumnya"), HttpStatus.CONFLICT);
    }

}
