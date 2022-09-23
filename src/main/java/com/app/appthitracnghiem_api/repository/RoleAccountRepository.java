package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Role_Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAccountRepository extends JpaRepository<Role_Account, Integer> {
}
