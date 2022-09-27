package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroups;

import java.util.List;

public interface QuestionGroupsServiceImp {
    public List<QuestionGroups> getAllQuestionGroupsBySubjectId(int id);
    public boolean deleteQGrById(int id);
    public boolean insertQGr(QuestionGroups questionGroups);
    public boolean updateQGr(QuestionGroups questionGroups);
}
