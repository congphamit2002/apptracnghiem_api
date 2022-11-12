package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.repository.QuestionGroupsDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionGroupsDetailService implements QuestionGroupsDetailServiceImp{

    @Autowired
    QuestionGroupsDetailRepository questionGroupsDetailRepository;

    @Override
    public List<Map<String,?>> getAllQGrDetailByQGrId(int id) {
        return questionGroupsDetailRepository.getAllQGrDetailByQGrId(id);
    }

    @Override
    public QuestionGroupsDetail insertQGrDetail(QuestionGroupsDetail questionGroupsDetail) {
        try {
            QuestionGroupsDetail temp = questionGroupsDetailRepository.save(questionGroupsDetail);
            return temp;
        }catch (Exception e ) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public boolean updateQGrDetail(QuestionGroupsDetail questionGroupsDetail) {
        QuestionGroupsDetail update = questionGroupsDetailRepository.findQuestionGroupsDetailById(questionGroupsDetail.getId());
        update.setNameGrDetail(questionGroupsDetail.getNameGrDetail());
        update.setNumberQuestions(questionGroupsDetail.getNumberQuestions());
        update.setTime(questionGroupsDetail.getTime());
        update.setDescription(questionGroupsDetail.getDescription());
        if(!questionGroupsDetail.getLinkExcel().equals(""))
            update.setLinkExcel(questionGroupsDetail.getLinkExcel());
        if(questionGroupsDetailRepository.save(update) != null)
            return  true;
        return false;
    }

    @Override
    public boolean deleteQGrDetailById(int id) {
        try {
            questionGroupsDetailRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteQGrDetailByQGrID(int id) {
        return false;
    }

    @Override
    public QuestionGroupsDetail getQGrDetailById(int id) {
        try {
            return questionGroupsDetailRepository.findQuestionGroupsDetailById(id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
