package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.helper.JwtProvider;
import com.app.appthitracnghiem_api.payload.*;
import com.app.appthitracnghiem_api.service.AccountServiceImp;
import com.app.appthitracnghiem_api.service.RoleAccountServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountServiceImp accountServiceImp;

    @Autowired
    RoleAccountServiceImp roleAccountServiceImp;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/getAllAccount")
    public ResponseEntity<?> getAllAccount(){
        return new ResponseEntity<ArrayList<Map<String, ?>>>(accountServiceImp.getAllAccountRespone(), HttpStatus.OK);
    }

    @GetMapping("/deleteAccount/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") int id){
        try {
            accountServiceImp.deleteAccount(id);
            return new ResponseEntity<String>("Delete Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Delete Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<?> updateAccount(@RequestBody AccountUpRequest accountsUp){
        try {
            Accounts accounts = new Accounts();
            accounts.setId(accountsUp.getId());
            accounts.setFullname(accountsUp.getFullname());
            accounts.setPhone(accountsUp.getPhone());
            accounts.setGender(accountsUp.getGender());
            accounts.setDateOfBirth(accountsUp.getDate_of_birth());
            accounts.setEmail(accountsUp.getEmail());
            Provinces provinces = new Provinces();
            provinces.setId(accountsUp.getProvince_id());
            accounts.setProvince(provinces);
            System.out.println("\t\tProvince ID " + accountsUp.getProvince_id());


            if(accountServiceImp.updateAccount(accounts)) {
                return new ResponseEntity<String>("Update Successfully", HttpStatus.OK);
            } else  {
                return new ResponseEntity<String>("Update Fail", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            return new ResponseEntity<String>("Update Fail", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("update/{id}")
    public ResponseEntity<?> getAccountUpdateByID(@PathVariable("id") int id) {

        //return new ResponseEntity<Account>(accountServiceImp.getAccountByID(id), HttpStatus.OK);
        return new ResponseEntity<AccountUpdateRespone>(accountServiceImp.getAccountUpdateByID(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getAccountByUsername(@PathVariable("username") String username) {
        Accounts accounts = accountServiceImp.getAccountByUsername(username);

        if(accounts != null) {
            System.out.println("Account id" + accounts.getId());
            return new ResponseEntity<Accounts>(accounts, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Username is invalid", HttpStatus.OK);
    }


    @PostMapping("/insertAccount")
    public ResponseEntity<?> insertAccount(@RequestBody Accounts accounts){
        try {
            if(accountServiceImp.insertAccount(accounts)) {
                return new ResponseEntity<String>("Insert Successfully", HttpStatus.OK);
                //return new ResponseEntity<Accounts>(accountServiceImp.getAccountByID(1), HttpStatus.OK);
            } else  {
                return new ResponseEntity<String>("Insert Fail", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<String>("Insert Fail", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            System.out.println("Id " + changePasswordRequest.getId());
            System.out.println("Old pass " + changePasswordRequest.getOldPassword());
            System.out.println("New pass " + changePasswordRequest.getNewPassword());
            if(accountServiceImp.changePassword(changePasswordRequest)) {
                return new ResponseEntity<String>("Change Password Successful", HttpStatus.OK);
            } else  {
                return new ResponseEntity<String>("Change Password Failed", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<String>("Change Password Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
//        logger.info("[IN-REQUEST] " + json);

        //Hàm dùng để kích hoạt đăng nhập bằng tay
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
                        , loginRequest.getPassword()) );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //String jwtToken = jwtProvider.generateToken((User) authentication.getPrincipal());
        String jwtToken = jwtProvider.generateToken(loginRequest.getUsername());
        Accounts accounts = accountServiceImp.getAccountByUsername(loginRequest.getUsername());
        LoginRespone loginRespone = new LoginRespone();
        loginRespone.setUsername(accounts.getUsername());
        loginRespone.setId(accounts.getId());
        loginRespone.setEmail(accounts.getEmail());
        loginRespone.setToken(jwtToken);
        return new ResponseEntity<LoginRespone>(loginRespone, HttpStatus.OK);
    }
}
