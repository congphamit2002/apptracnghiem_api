package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;

import java.util.List;
import java.util.Map;

public interface QuestionGroupsDetailServiceImp {
    public List<QuestionGroupsDetail> getQGrDetailByQGrID(int id);

    public List<Map<String, ?>>  getAllQGrDetailByQGrId(int id);
}
