package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.payload.QuestionGrRespone;

import java.util.ArrayList;
import java.util.List;

public interface QuestionGroupsServiceImp {
    public ArrayList<QuestionGrRespone> getAllQuestionGroupsResponeBySubjectId(int id);
    public ArrayList<QuestionGroups> getAllQuestionGroupsBySubjectId(int id);
    public QuestionGroups getQGrById(int id);
    public void deleteQGrById(int id);
    public void deleteQGrBySubjectId(int id);
    public boolean insertQGr(QuestionGroups questionGroups);
    public boolean updateQGr(QuestionGroups questionGroups);
}
