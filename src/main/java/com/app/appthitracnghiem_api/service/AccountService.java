package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.payload.ChangePasswordRequest;
import com.app.appthitracnghiem_api.payload.LoginRequest;
import com.app.appthitracnghiem_api.repository.AccountRepository;
import com.app.appthitracnghiem_api.repository.RoleAccountRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountServiceImp{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleAccountRepository roleAccountRepository;

    @Override
    public List<Accounts> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean insertAccount(Accounts account) {

        try {
            boolean flag = false;
            String password = account.getPassword();
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            account.setPassword(hash);
            account.setRoleID(1);
            Accounts test = accountRepository.save(account);

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
    public Accounts getAccountByID(int id) {
        return accountRepository.findAccountById(id);
    }


}
