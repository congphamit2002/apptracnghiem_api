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
    public List<QuestionGrRespone> getAllQuestionGroupsBySubjectId(int id) {
        List<QuestionGroups> listData =  questionGroupsRepository.findAllBySubjectId(id);
        List<QuestionGrRespone> listResult = new ArrayList<>();
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
    public boolean deleteQGrById(int id) {
        try {
            questionGroupsRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
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
