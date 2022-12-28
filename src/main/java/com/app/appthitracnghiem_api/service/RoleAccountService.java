package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.Roles;
import com.app.appthitracnghiem_api.entity.Role_Account;
import com.app.appthitracnghiem_api.repository.RoleAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleAccountService implements RoleAccountServiceImp{

    @Autowired
    RoleAccountRepository roleAccountRepository;
    @Override
    public boolean insertRoleAccount(int accountID, int roleID) {
        Role_Account roleAccount = new Role_Account();
        Accounts account = new Accounts();
        account.setId(accountID);
        Roles role = new Roles();
        role.setId(roleID);
        roleAccount.setAccount(account);
        roleAccount.setRole(role);

        Role_Account test = roleAccountRepository.save(roleAccount);
        if(test != null)
            return  true;
        return false;
    }

    @Override
    public List<Map<String, ?>> getAllRolesByUsername(String username) {
        return roleAccountRepository.getAllRolesByUsername(username);
    }
}
