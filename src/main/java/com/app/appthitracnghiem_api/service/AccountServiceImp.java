package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.payload.AccountUpdateRespone;
import com.app.appthitracnghiem_api.payload.ChangePasswordRequest;
import com.app.appthitracnghiem_api.payload.LoginRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AccountServiceImp {
    public ArrayList<Accounts> findAll();
    public ArrayList<Map<String, ?>> getAllAccountRespone();
    public AccountUpdateRespone getAccountUpdateByID(int id);
    public Accounts getAccountByID(int id);
    public boolean insertAccount(Accounts account);
    public void deleteAccount(int id);
    public boolean updateAccount(Accounts account);
    public Accounts getAccountByUsername(String username);
    public boolean changePassword(ChangePasswordRequest changePasswordRequest);
    public boolean login(LoginRequest request);
    public boolean existUserByUsername(String username);
    public boolean existUserByEmail(String email);
    public boolean existUserByPhone(String phone);

}
