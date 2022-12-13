package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Subjects;
import com.app.appthitracnghiem_api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService implements SubjectServiceImp {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public ArrayList<Subjects> getAllSubject() {
        return (ArrayList<Subjects>) subjectRepository.findAll();
    }

    @Override
    public boolean insertSubject(Subjects subject) {
        try {
            boolean flag = false;
            Subjects temp = subjectRepository.save(subject);

            if(temp != null) {
                flag = true;
            }
            return flag;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public void deleteSubjectById(int id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public boolean updateSubject(Subjects subject) {
        try {
            boolean flag = false;
            Subjects subjectUpdate = subjectRepository.findSubjectById(subject.getId());

            subjectUpdate.setSubjectName(subject.getSubjectName());
            if(subject.getImage() != null)
                subjectUpdate.setImage(subject.getImage());

            System.out.println("\t\tID " + subject.getId());
            System.out.println("\t\tSubject name " + subject.getSubjectName());
            System.out.println("\t\tImage " + subject.getImage());
            Subjects test = subjectRepository.save(subjectUpdate);
            if(test != null){
                flag = true;
            }

            return  flag;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Subjects getSubjectsById(int id) {
        return subjectRepository.findSubjectById(id);
    }
}
