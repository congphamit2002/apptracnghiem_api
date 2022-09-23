package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "provinces")
public class Provinces {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "province_name")
    private String provinceName;

    @OneToMany(mappedBy = "province")
    private Set<Accounts> listAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
