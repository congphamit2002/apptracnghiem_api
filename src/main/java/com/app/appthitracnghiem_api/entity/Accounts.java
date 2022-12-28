package com.app.appthitracnghiem_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private int gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "role_id")
    private int roleID;

    @Column(name = "create_at")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //private Date createAt;
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Provinces province;

    @OneToMany(mappedBy = "account")
    private Set<HistoryTests> listHistoryTests;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Role_Account> listRoleAccount;

//     @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//     @JoinTable(name = "role_account",
//             joinColumns = {@JoinColumn(name ="account_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//     private Set<Roles> roles;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

//    public Date getCreateAt() {
//        return createAt;
//    }
//
//    public void setCreateAt(Date createAt) {
//        this.createAt = createAt;
//    }


    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Provinces getProvince() {
        return province;
    }

    public void setProvince(Provinces province) {
        this.province = province;
    }

    public Set<Role_Account> getListRoleAccount() {
        return listRoleAccount;
    }

    public void setListRoleAccount(Set<Role_Account> listRoleAccount) {
        this.listRoleAccount = listRoleAccount;
    }
}
