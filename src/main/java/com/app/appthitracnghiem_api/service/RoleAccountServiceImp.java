package com.app.appthitracnghiem_api.service;

import java.util.List;
import java.util.Map;

public interface RoleAccountServiceImp {

    public boolean insertRoleAccount(int accountID, int roleID);
    public List<Map<String, ?>> getAllRolesByUsername(String username);
}
