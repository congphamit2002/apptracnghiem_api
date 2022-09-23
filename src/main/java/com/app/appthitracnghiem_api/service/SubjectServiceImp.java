package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Subjects;

import java.util.List;

public interface SubjectServiceImp {
    public List<Subjects> getAllSubject();
    public boolean insertSubject(Subjects subject);
    public void deleteSubjectById(int id);
    public boolean updateSubject(Subjects subject);
    public Subjects getSubjectsById(int id);
}
