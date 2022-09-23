package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "roles")
public class Roles {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<Role_Account> listRoleAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
