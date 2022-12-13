package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface QuestionGroupsDetailServiceImp {

    public ArrayList<Map<String, ?>> getAllQGrDetailByQGrId(int id);

    public QuestionGroupsDetail insertQGrDetail(QuestionGroupsDetail questionGroupsDetail);
    public boolean updateQGrDetail(QuestionGroupsDetail questionGroupsDetail);
    public void deleteQGrDetailById(int id);
    public void deleteQGrDetailByQGrID(int id);

    public ArrayList<QuestionGroupsDetail> getAllByQGrId(int id);

    public QuestionGroupsDetail getQGrDetailById(int id);
    public ArrayList<Map<String, ?>> getAllPreviewImageByQGeDId(int id);

}
