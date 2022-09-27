package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.pojo.HistoryTestsPojo;
import com.app.appthitracnghiem_api.service.HistoryTestsServiceImp;
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
@RequestMapping("/api/historyTest")
public class HistoryTestsController {

    @Autowired
    HistoryTestsServiceImp historyTestsServiceImp;

    @GetMapping("/{accountID}/{qgroupDetailID}")
    public ResponseEntity<?> getHistoryTestByAccountIDandQGrDetailID(@PathVariable("accountID") int accountID,
                                                                     @PathVariable("qgroupDetailID") int qgroupDetailID) {

        Gson gson = new Gson();
        String data = gson.toJson(historyTestsServiceImp.getHistoryTestByAccountIDandQGrDetailID(accountID,qgroupDetailID));

        ObjectMapper mapper = new ObjectMapper();
        try {
            HistoryTestsPojo[] historyTestsPojos = mapper.readValue(data, HistoryTestsPojo[].class);
            return new ResponseEntity<>(historyTestsPojos, HttpStatus.OK);
        }catch (Exception e) {

        }
        return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
    }
}
