package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.payload.AccountRespone;
import com.app.appthitracnghiem_api.payload.AccountUpdateRespone;
import com.app.appthitracnghiem_api.payload.ChangePasswordRequest;
import com.app.appthitracnghiem_api.payload.LoginRequest;
import com.app.appthitracnghiem_api.repository.AccountRepository;
import com.app.appthitracnghiem_api.repository.RoleAccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AccountService implements AccountServiceImp{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleAccountRepository roleAccountRepository;

    @Override
    public ArrayList<Accounts> findAll() {
        return (ArrayList<Accounts>) accountRepository.findAll();
    }

    @Override
    public ArrayList<Map<String, ?>> getAllAccountRespone() {
        return accountRepository.getAllAccountRespone();
    }

    @Override
    public boolean insertAccount(Accounts account) {

        try {
            boolean flag = false;
            Accounts test = new Accounts();
            try {
                String password = account.getPassword();
                String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
                account.setPassword(hash);
                account.setRoleID(1);
                 test = accountRepository.save(account);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            if(test != null) {
//                Role_Account roleAccount = new Role_Account();
//                Roles roleSet = new Roles();
//                roleSet.setId(test.getRoleID());
//                Accounts accountSet = new Accounts();
//                accountSet.setId(test.getId());
//                roleAccount.setRole(roleSet);
//                roleAccount.setAccount(accountSet);
//                if(roleAccountRepository.save(roleAccount) != null) {
//                    flag = true;
//                }
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
            if(!accountUpdate.getFullname().equals("")) {
                accountUpdate.setFullname(account.getFullname());
            }
            if(!accountUpdate.getEmail().equals("")) {
                accountUpdate.setEmail(account.getEmail());
            }
            if(accountUpdate.getGender() == 0 || accountUpdate.getGender() == 1) {
                accountUpdate.setGender(account.getGender());
            }
            if(!accountUpdate.getPhone().equals("")) {
                accountUpdate.setPhone(account.getPhone());

            }
            if(accountUpdate.getProvince() != null) {
                accountUpdate.setProvince(account.getProvince());
            }
            if(!accountUpdate.getDateOfBirth().equals("")) {
                accountUpdate.setDateOfBirth(account.getDateOfBirth());
            }
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
    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        try {
            boolean flag = false;
            Accounts accounts = accountRepository.findAccountById(changePasswordRequest.getId());
            String passwordAccount = accounts.getPassword();
            String oldPassChange = changePasswordRequest.getOldPassword();

            if(BCrypt.checkpw(oldPassChange, passwordAccount)) {
                String newPasswordUpdate = changePasswordRequest.getNewPassword();
                String newHash = BCrypt.hashpw(newPasswordUpdate, BCrypt.gensalt(12));
                accounts.setPassword(newHash);
                accountRepository.save(accounts);
                flag = true;
            }
            return flag;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }



    }

    @Override
    public boolean login(LoginRequest request) {
        if(getAccountByUsername(request.getUsername()) != null) {
            Accounts accounts = getAccountByUsername(request.getUsername());
            if(BCrypt.checkpw(request.getPassword(), accounts.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public AccountUpdateRespone getAccountUpdateByID(int id) {
        Accounts accounts =  accountRepository.findAccountById(id);
        AccountUpdateRespone accountRespone = new AccountUpdateRespone();
        accountRespone.setId(accounts.getId());
        accountRespone.setEmail(accounts.getEmail());
        accountRespone.setGender(accounts.getGender());
        accountRespone.setPhone(accounts.getPhone());
        accountRespone.setFullname(accounts.getFullname());
        accountRespone.setProvince_id(accounts.getProvince().getId());
        accountRespone.setDate_of_birth(accounts.getDateOfBirth());
        accountRespone.setRole_id(accounts.getRoleID());
        accountRespone.setUsername(accounts.getUsername());
        return accountRespone;
    }

    @Override
    public Accounts getAccountByID(int id) {
        return accountRepository.findAccountById(id);
    }



}
