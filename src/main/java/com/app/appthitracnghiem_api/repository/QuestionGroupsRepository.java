package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface QuestionGroupsRepository extends JpaRepository<QuestionGroups, Integer> {
    public ArrayList<QuestionGroups> findAllBySubjectId(int id);
    public QuestionGroups findQuestionGroupsById(int id);
    @Query(value = "call deleteQuestionGrBySubjectId(:id)", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteQuestionGrBySubjectId(@Param("id") Integer id);
    public QuestionGroups findById(int id);
}
