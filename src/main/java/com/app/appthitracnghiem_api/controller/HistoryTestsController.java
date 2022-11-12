package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.HistoryTests;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.payload.HistoryTestRequest;
import com.app.appthitracnghiem_api.pojo.HistoryTestsPojo;
import com.app.appthitracnghiem_api.repository.HistoryTestsRepository;
import com.app.appthitracnghiem_api.service.HistoryTestsServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historyTest")
public class HistoryTestsController {

    @Autowired
    HistoryTestsServiceImp historyTestsServiceImp;

    @Autowired
    HistoryTestsRepository historyTestsRepository;

    @GetMapping("/{accountID}/{qgroupDetailID}")
    public ResponseEntity<?> getHistoryTestByAccountIDandQGrDetailID(@PathVariable("accountID") int accountID,
                                                                     @PathVariable("qgroupDetailID") int qgroupDetailID) {

        //cần fix
        Gson gson = new Gson();
        String data = gson.toJson(historyTestsServiceImp.getHistoryTestByAccountIDandQGrDetailID(accountID,qgroupDetailID));

        ObjectMapper mapper = new ObjectMapper();
        try {
            HistoryTestsPojo[] historyTestsPojos = mapper.readValue(data, HistoryTestsPojo[].class);
//            return new ResponseEntity<>(historyTestsPojos, HttpStatus.OK);
            return new ResponseEntity<>(historyTestsRepository.findAll(), HttpStatus.OK);
        }catch (Exception e) {

        }
        return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveHistoryTest(@RequestBody HistoryTestRequest historyTestsRequeset) {
        try {
            HistoryTests historyTests = new HistoryTests();
            Accounts accounts = new Accounts();
            accounts.setId(historyTestsRequeset.getAccountId());
            historyTests.setAccount(accounts);
            historyTests.setCorrectAnswer(historyTestsRequeset.getCorrectAnswer());
            historyTests.setIncorrectAnswer(historyTestsRequeset.getInCorrectAnswer());
            historyTests.setScore(historyTestsRequeset.getScore());
            QuestionGroupsDetail questionGroupsDetail = new QuestionGroupsDetail();
            questionGroupsDetail.setId(historyTestsRequeset.getQgrDetailId());
            historyTests.setQuestionGroupsDetail(questionGroupsDetail);
            if(historyTestsServiceImp.saveHistoryTest(historyTests)) {
                return new ResponseEntity<>("Save history test successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Save history test failed ", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Save history test failed ", HttpStatus.BAD_REQUEST);
        }
    }

}
