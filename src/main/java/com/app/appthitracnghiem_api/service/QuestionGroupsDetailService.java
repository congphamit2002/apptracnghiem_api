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
    public List<QuestionGroupsDetail> getQGrDetailByQGrID(int id) {
        return questionGroupsDetailRepository.findAllByQuestionGroupsId(id);
    }

    @Override
    public List<Map<String,?>> getAllQGrDetailByQGrId(int id) {
        return questionGroupsDetailRepository.getAllQGrDetailByQGrId(id);
    }
}
