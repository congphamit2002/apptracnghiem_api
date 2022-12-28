package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.pojo.QuestionGroupDetailPojo;
import com.app.appthitracnghiem_api.pojo.QuestionPojo;
import com.app.appthitracnghiem_api.repository.QuestionGroupsDetailRepository;
import com.app.appthitracnghiem_api.service.QuestionGroupsDetailServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/qGrDetail")
public class QuestionGroupsDetailController {

    @Autowired
    QuestionGroupsDetailServiceImp questionGroupsDetailServiceImp;

    @Autowired
    QuestionGroupsDetailRepository  questionGroupsDetailRepository;
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/getQGrDetailByQGrId/{id}")
    public ResponseEntity<?> getQGrDetailByQGrId(@PathVariable("id") int id) {

        try {
            Gson gson = new Gson();
            String data =  gson.toJson(questionGroupsDetailServiceImp.getAllQGrDetailByQGrId(id));

            ObjectMapper mapper = new ObjectMapper();
            QuestionGroupDetailPojo[] listData = mapper.readValue(data, QuestionGroupDetailPojo[].class);
            ArrayList<QuestionGroupDetailPojo> listResult = new ArrayList<>();
            for(QuestionGroupDetailPojo item : listData) {
                listResult.add(item);
            }
            return new ResponseEntity<ArrayList<QuestionGroupDetailPojo>>(listResult, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>("Question Group Detail is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        QuestionGroups questionGroups = new QuestionGroups();
        questionGroups.setId(id);
        questionGroupsDetailRepository.deleteQuestionGrDeByQGrId(id);
        return new ResponseEntity<>("OKe", HttpStatus.OK);
    }
}
