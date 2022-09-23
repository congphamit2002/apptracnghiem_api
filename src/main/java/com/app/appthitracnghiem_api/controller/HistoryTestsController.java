package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.service.HistoryTestsServiceImp;
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
        if (historyTestsServiceImp.getHistoryTestByAccountIDandQGrDetailID(accountID,qgroupDetailID) != null) {
            return new ResponseEntity<>(historyTestsServiceImp.getHistoryTestByAccountIDandQGrDetailID(accountID,qgroupDetailID), HttpStatus.OK);
        }

        return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
    }
}
