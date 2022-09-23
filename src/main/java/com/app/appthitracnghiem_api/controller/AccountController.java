package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.service.AccountServiceImp;
import com.app.appthitracnghiem_api.service.RoleAccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountServiceImp accountServiceImp;

    @Autowired
    RoleAccountServiceImp roleAccountServiceImp;

    @GetMapping("/getAllAccount")
    public ResponseEntity<?> getAllAccount(){
        return new ResponseEntity<List<Accounts>>(accountServiceImp.findAll(), HttpStatus.OK);
    }

    @PostMapping("/insertAccount")
    public ResponseEntity<?> insertAccount(@RequestParam("username") String username, @RequestParam("password") String password
                                           , @RequestParam("fullname") String fullname, @RequestParam("phone") String phone,
                                           @RequestParam("dateOfBirth")String dateOfBitrh, @RequestParam("email") String email,
                                           @RequestParam("gender") int gender,
                                           @RequestParam("provinceID") int provinceID,
                                           @RequestParam("roleID") int roleID){
        try {
            Accounts account = new Accounts();
            account.setUsername(username);
            account.setPassword(password);
            account.setFullname(fullname);
            account.setPhone(phone);
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(dateOfBitrh);
            System.out.println(date.toString());
            account.setDateOfBirth(date);
            account.setEmail(email);
            Provinces province = new Provinces();
            province.setId(provinceID);
            account.setProvince(province);
            account.setGender(gender);
            account.setRoleID(roleID);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dateCreate = new Date();
            account.setCreateAt(dateCreate);

            if(accountServiceImp.insertAccount(account)) {
                return new ResponseEntity<String>("Insert Successfully", HttpStatus.OK);
            } else  {
                return new ResponseEntity<String>("Insert Fail", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            return new ResponseEntity<String>("Insert Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/deleteAccount/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") int id){
        try {
            accountServiceImp.deleteAccount(id);
            return new ResponseEntity<String>("Delete Successfully", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>("Delete Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<?> updateAccount(@RequestParam("id") int id,
                                           @RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("fullname") String fullname,
                                           @RequestParam("phone") String phone,
                                           @RequestParam("dateOfBirth")String dateOfBitrh,
                                           @RequestParam("email") String email,
                                           @RequestParam("gender") int gender,
                                           @RequestParam("provinceID") int provinceID,
                                           @RequestParam("roleID") int roleID){
        try {
            Accounts account = new Accounts();
            account.setId(id);
            account.setUsername(username);
            account.setPassword(password);
            account.setFullname(fullname);
            account.setPhone(phone);
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(dateOfBitrh);
            System.out.println(date.toString());
            account.setDateOfBirth(date);
            account.setEmail(email);
            Provinces province = new Provinces();
            province.setId(provinceID);
            account.setProvince(province);
            account.setGender(gender);
            account.setRoleID(roleID);

            if(accountServiceImp.updateAccount(account)) {
                return new ResponseEntity<String>("Update Successfully", HttpStatus.OK);
            } else  {
                return new ResponseEntity<String>("Update Fail", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            return new ResponseEntity<String>("Update Fail", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountByID(@PathVariable("id") int id) {

        //return new ResponseEntity<Account>(accountServiceImp.getAccountByID(id), HttpStatus.OK);
        return new ResponseEntity<Accounts>(accountServiceImp.getAccountByID(id), HttpStatus.OK);
    }

}
