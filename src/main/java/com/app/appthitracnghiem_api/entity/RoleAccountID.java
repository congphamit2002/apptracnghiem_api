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
}
