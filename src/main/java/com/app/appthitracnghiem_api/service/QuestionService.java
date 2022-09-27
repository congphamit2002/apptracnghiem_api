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
    public List<Map<String , ?>> getAllQuestionByQuestionGroupsDetailId(int id) {
        return questionsRepository.getAllQuestionByQuestionGroupsDetailId(id);
    }

    @Override
    public void saveFile(MultipartFile file, int QGrDetailId) {
        try {
            if(ExcelHelper.hasExcelFormat(file)) {

                List<Questions> listData = new ArrayList<Questions>();
                listData = ExcelHelper.excelToReadQuestion(file.getInputStream(), QGrDetailId);
                questionsRepository.saveAll(listData);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
