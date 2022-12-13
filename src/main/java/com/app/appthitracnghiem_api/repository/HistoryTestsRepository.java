package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.HistoryTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface HistoryTestsRepository extends JpaRepository<HistoryTests, Integer> {
    //@Procedure("GetPhimWithCategory")
    @Query(value = "call findHistoryTestByAccountIDandQGrDetailID(:accountID)", nativeQuery = true)
    ArrayList<Map<String, ?>> getHistoryTestByAccountIDandQGrDetailID(@Param("accountID") Integer accountID);
}
