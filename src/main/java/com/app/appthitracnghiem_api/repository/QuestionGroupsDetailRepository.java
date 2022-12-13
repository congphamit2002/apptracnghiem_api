package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface QuestionGroupsDetailRepository extends JpaRepository<QuestionGroupsDetail, Integer> {
    public ArrayList<QuestionGroupsDetail> findAllByQuestionGroupsId(int id);

    @Query(value = "call getAllQGrDetailByQGrId(:QGrId)", nativeQuery = true)
    ArrayList<Map<String, ?>> getAllQGrDetailByQGrId(@Param("QGrId") Integer QGrId);

    public QuestionGroupsDetail findQuestionGroupsDetailById(int id);

    public void deleteById(int id);
    @Query(value = "call deleteQuestionGrDeByQGrId(:id)", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteQuestionGrDeByQGrId(@Param("id") Integer id);

    @Query(value = "call getAllPreviewImageByQGeDId(:id)", nativeQuery = true)
    List<Map<String, ?>> getAllPreviewImageByQGeDId(@Param("id") int id);
}
