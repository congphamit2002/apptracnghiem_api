package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.HistoryTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<HistoryTests, Integer> {
}
