package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;

@Entity(name = "role_account")
public class Role_Account {

    @EmbeddedId
    RoleAccountID id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Roles role;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Accounts account;

    public RoleAccountID getId() {
        return id;
    }

    public void setId(RoleAccountID id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }
}
