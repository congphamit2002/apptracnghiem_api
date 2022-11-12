package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;

import java.util.List;
import java.util.Map;

public interface QuestionGroupsDetailServiceImp {

    public List<Map<String, ?>>  getAllQGrDetailByQGrId(int id);

    public QuestionGroupsDetail insertQGrDetail(QuestionGroupsDetail questionGroupsDetail);
    public boolean updateQGrDetail(QuestionGroupsDetail questionGroupsDetail);
    public boolean deleteQGrDetailById(int id);
    public boolean deleteQGrDetailByQGrID(int id);

    public QuestionGroupsDetail getQGrDetailById(int id);

}
