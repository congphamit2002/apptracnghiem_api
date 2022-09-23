package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects, Integer> {
    public Subjects findSubjectById(int id);
}
