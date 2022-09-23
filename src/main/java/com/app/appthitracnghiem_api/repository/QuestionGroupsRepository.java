package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionGroupsRepository extends JpaRepository<QuestionGroups, Integer> {
}
