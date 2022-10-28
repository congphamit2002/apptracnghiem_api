package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.payload.ChangePasswordRequest;
import com.app.appthitracnghiem_api.payload.LoginRequest;

import java.util.List;

public interface AccountServiceImp {
    public List<Accounts> findAll();
    public Accounts getAccountByID(int id);
    public boolean insertAccount(Accounts account);
    public void deleteAccount(int id);
    public boolean updateAccount(Accounts account);
    public Accounts getAccountByUsername(String username);
    public boolean changePassword(ChangePasswordRequest changePasswordRequest);
    public boolean login(LoginRequest request);
}
