package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
}
