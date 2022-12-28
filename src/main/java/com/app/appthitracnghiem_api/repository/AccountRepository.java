package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    public Accounts findAccountById(int id);
    public Accounts findAccountsByUsername(String username);
    @Query(value = "call getAllAccountRespone()", nativeQuery = true)
    ArrayList<Map<String, ?>> getAllAccountRespone();
    public Boolean existsAccountsByUsername(String username);
    public Boolean existsAccountsByEmail(String email);
    public boolean existsAccountsByPhone(String phone);


}
