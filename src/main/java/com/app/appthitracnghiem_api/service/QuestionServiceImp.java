package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface QuestionServiceImp{
    //public List<Questions> getAllQuestionByQuestionGroupsDetailId(int id);
    public ArrayList<Map<String, ?>> getAllQuestionByQuestionGroupsDetailId(int id);

    public void saveFileExcel( int qGrDetailId, MultipartFile questionFileExcel);
    public boolean updateFileExcel(int qGrDetailId, MultipartFile questionFileExcel);
    public void deleteQuestionByQGrDetailId(int qGrDetailId);

}
