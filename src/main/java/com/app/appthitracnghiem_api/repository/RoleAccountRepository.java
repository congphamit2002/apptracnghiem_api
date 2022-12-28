package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Role_Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleAccountRepository extends JpaRepository<Role_Account, Integer> {

    @Query(value = "call getAllRolesByUsername(:username)",nativeQuery = true)
    public List<Map<String, ?>> getAllRolesByUsername(@Param("username") String username);

}
