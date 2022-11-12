package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.payload.QuestionGrRespone;

import java.util.List;

public interface QuestionGroupsServiceImp {
    public List<QuestionGrRespone> getAllQuestionGroupsBySubjectId(int id);
    public boolean deleteQGrById(int id);
    public boolean insertQGr(QuestionGroups questionGroups);
    public boolean updateQGr(QuestionGroups questionGroups);
}
