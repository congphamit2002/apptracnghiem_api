package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.entity.Questions;
import com.app.appthitracnghiem_api.pojo.QuestionPojo;
import com.app.appthitracnghiem_api.service.FileSystemStorageServiceImp;
import com.app.appthitracnghiem_api.service.QuestionServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    QuestionServiceImp questionServiceImp;

    @Autowired
    FileSystemStorageServiceImp fileSystemStorageServiceImp;
    @GetMapping("/questionDetailID/{id}")
    public ResponseEntity<?> getAllQuestionByQuestionGroupsDetailId (@PathVariable("id") int id) {

        try {
            List<Map<String, ?>> list = questionServiceImp.getAllQuestionByQuestionGroupsDetailId(id);
            Gson gson = new Gson();
            String data = gson.toJson(list);
            ObjectMapper mapper = new ObjectMapper();
            QuestionPojo[] listQuestion = mapper.readValue(data, QuestionPojo[].class);
            return new ResponseEntity<QuestionPojo[]>(listQuestion, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>("Question Groups Detail Id is invalid", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/saveFile")
    public ResponseEntity<?> saveFileFullTest(
            @RequestParam("fulltestName") String fulltestName,
            @RequestParam MultipartFile fileExcel, @RequestParam("qgrdId") int QGrDetailID) {

       try {
           fileSystemStorageServiceImp.init(Constant.questionExcel);


           if(!fileExcel.isEmpty()) {
               fileSystemStorageServiceImp.save(fileExcel, Constant.questionExcel);
               questionServiceImp.saveFile(fileExcel, QGrDetailID);
           }

           return new ResponseEntity<String>("OKE", HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<String>("FAIL  ", HttpStatus.OK);
       }
    }
}
