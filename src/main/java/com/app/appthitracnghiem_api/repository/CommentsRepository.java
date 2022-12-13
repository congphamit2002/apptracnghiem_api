package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    @Query(value = "call getAllCmtByQGrDetailId(:QGrDeId)", nativeQuery = true)
    ArrayList<Map<String, ?>> getAllCmtByQGrDetailId(@Param("QGrDeId") int QGrDeId);
}
