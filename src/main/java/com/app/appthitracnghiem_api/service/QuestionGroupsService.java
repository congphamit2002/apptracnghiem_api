package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.Subjects;
import com.app.appthitracnghiem_api.payload.QuestionGrRespone;
import com.app.appthitracnghiem_api.repository.QuestionGroupsRepository;
import com.app.appthitracnghiem_api.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionGroupsService implements QuestionGroupsServiceImp {

    @Autowired
    QuestionGroupsRepository questionGroupsRepository;

    @Override
    public ArrayList<QuestionGrRespone> getAllQuestionGroupsResponeBySubjectId(int id) {
        ArrayList<QuestionGroups> listData =  questionGroupsRepository.findAllBySubjectId(id);
        ArrayList<QuestionGrRespone> listResult = new ArrayList<>();
        for (QuestionGroups item : listData) {
            QuestionGrRespone questionGrRespone = new QuestionGrRespone();
            questionGrRespone.setId(item.getId());
            questionGrRespone.setNameGroup(item.getNameGroup());
            questionGrRespone.setSubjectName(item.getSubject().getSubjectName());
            listResult.add(questionGrRespone);
        }
        return  listResult;
    }

    @Override
    public ArrayList<QuestionGroups> getAllQuestionGroupsBySubjectId(int id) {
        return questionGroupsRepository.findAllBySubjectId(id);
    }

    @Override
    public QuestionGroups getQGrById(int id) {
        return questionGroupsRepository.findById(id);
    }


    @Override
    public void deleteQGrById(int id) {
            questionGroupsRepository.deleteById(id);
    }

    @Override
    public void deleteQGrBySubjectId(int id) {
            questionGroupsRepository.deleteQuestionGrBySubjectId(id);
    }


    @Override
    public boolean insertQGr(QuestionGroups questionGroups) {
        try {
            boolean flag = false;
            if(questionGroupsRepository.save(questionGroups) != null) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateQGr(QuestionGroups questionGroups) {
        try {
            boolean flag = false;
            QuestionGroups questionGroupsUpdate = questionGroupsRepository.findQuestionGroupsById(questionGroups.getId());
            questionGroupsUpdate.setSubject(questionGroupsUpdate.getSubject());
            questionGroupsUpdate.setNameGroup(questionGroups.getNameGroup());

            if(questionGroupsRepository.save(questionGroupsUpdate) != null) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }
}
