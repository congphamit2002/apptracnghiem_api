package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.entity.Questions;
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
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {

    @Query(value = "CALL getAllQuestionByQuestionGroupsDetailId(:id);", nativeQuery = true)
    ArrayList<Map<String, ?>> getAllQuestionByQuestionGroupsDetailId(@Param("id") Integer id);

    @Query(value = "call deleteQuestionByQGrDetailId(:id)", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteQuestionByQGrDetailId(@Param("id") Integer id);
    public Questions findQuestionsById(int id);
}
