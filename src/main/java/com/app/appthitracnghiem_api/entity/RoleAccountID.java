package com.app.appthitracnghiem_api.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoleAccountID implements Serializable {

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "account_id")
    private int accountId;

    public RoleAccountID(int roleId, int accountId) {
        this.roleId = roleId;
        this.accountId = accountId;
    }

    public RoleAccountID() {

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
