package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;

import java.util.List;

public interface AccountServiceImp {
    public List<Accounts> findAll();
    public Accounts getAccountByID(int id);
    public boolean insertAccount(Accounts account);
    public void deleteAccount(int id);
    public boolean updateAccount(Accounts account);
}
