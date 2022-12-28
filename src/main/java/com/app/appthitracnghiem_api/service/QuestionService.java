package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Questions;
import com.app.appthitracnghiem_api.helper.ExcelHelper;
import com.app.appthitracnghiem_api.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService implements QuestionServiceImp{

    @Autowired
    QuestionsRepository questionsRepository;


    @Override
    public ArrayList<Map<String ,?>> getAllQuestionByQuestionGroupsDetailId(int id) {
        return questionsRepository.getAllQuestionByQuestionGroupsDetailId(id);
    }

    @Override
    public void saveFileExcel(int qGrDetailId, MultipartFile questionFileExcel) {
        try {
            if(ExcelHelper.hasExcelFormat(questionFileExcel)) {

                ArrayList<Questions> listData = new ArrayList<Questions>();
                listData = ExcelHelper.excelToQuestions(questionFileExcel.getInputStream(), qGrDetailId);
                questionsRepository.saveAll(listData);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void deleteQuestionByQGrDetailId(int qGrDetailId) {
            questionsRepository.deleteQuestionByQGrDetailId(qGrDetailId);
    }



    @Override
    public boolean updateFileExcel(int qGrDetailId, MultipartFile questionFileExcel) {
        try {
            if(ExcelHelper.hasExcelFormat(questionFileExcel)) {
                deleteQuestionByQGrDetailId(qGrDetailId);
                ArrayList<Questions> listData = ExcelHelper.excelToQuestions(questionFileExcel.getInputStream(), qGrDetailId);
                questionsRepository.saveAll(listData);
            }
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
