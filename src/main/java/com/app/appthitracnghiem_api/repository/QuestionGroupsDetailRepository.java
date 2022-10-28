package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuestionGroupsDetailRepository extends JpaRepository<QuestionGroupsDetail, Integer> {
    public List<QuestionGroupsDetail> findAllByQuestionGroupsId(int id);

    @Query(value = "call getAllQGrDetailByQGrId(:QGrId)", nativeQuery = true)
    List<Map<String, ?>> getAllQGrDetailByQGrId(@Param("QGrId") Integer QGrId);
}
