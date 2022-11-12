package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.pojo.QuestionGroupDetailPojo;
import com.app.appthitracnghiem_api.pojo.QuestionPojo;
import com.app.appthitracnghiem_api.service.QuestionGroupsDetailServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/qGrDetail")
public class QuestionGroupsDetailController {

    @Autowired
    QuestionGroupsDetailServiceImp questionGroupsDetailServiceImp;

    @GetMapping("/getQGrDetailByQGrId/{id}")
    public ResponseEntity<?> getQGrDetailByQGrId(@PathVariable("id") int id) {

        try {
            Gson gson = new Gson();
            String data =  gson.toJson(questionGroupsDetailServiceImp.getAllQGrDetailByQGrId(id));

            ObjectMapper mapper = new ObjectMapper();
            QuestionGroupDetailPojo[] listData = mapper.readValue(data, QuestionGroupDetailPojo[].class);
            return new ResponseEntity<QuestionGroupDetailPojo[]>(listData, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>("Question Group Detail is invalid", HttpStatus.BAD_REQUEST);
        }
    }
}
