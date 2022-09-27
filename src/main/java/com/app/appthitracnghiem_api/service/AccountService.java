package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountServiceImp{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Accounts> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean insertAccount(Accounts account) {

        try {
            boolean flag = false;

            Accounts test = accountRepository.save(account);

            if(test != null) {
                flag = true;
            }

            return  flag;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void deleteAccount(int id) {
        try {
            accountRepository.deleteById(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateAccount(Accounts account) {

        try {
            boolean flag = false;
            Accounts accountUpdate  = getAccountByID(account.getId());

            accountUpdate.setUsername(account.getUsername());
            accountUpdate.setPassword(account.getPassword());
            accountUpdate.setFullname(account.getFullname());
            accountUpdate.setEmail(account.getEmail());
            accountUpdate.setGender(account.getGender());
            accountUpdate.setPhone(account.getPhone());
            accountUpdate.setProvince(account.getProvince());
            accountUpdate.setDateOfBirth(account.getDateOfBirth());
            accountUpdate.setRoleID(account.getRoleID());
            Accounts test = accountRepository.save(accountUpdate);
            if(test != null){
                flag = true;
            }

            return  flag;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Accounts getAccountByUsername(String username) {
        return accountRepository.findAccountsByUsername(username);
    }

    @Override
    public Accounts getAccountByID(int id) {
        return accountRepository.findAccountById(id);
    }

}
