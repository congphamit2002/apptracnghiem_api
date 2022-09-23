package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    public Accounts findAccountById(int id);
}
