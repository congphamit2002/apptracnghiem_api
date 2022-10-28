package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.Comments;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.pojo.CommentPojo;
import com.app.appthitracnghiem_api.pojo.QuestionPojo;
import com.app.appthitracnghiem_api.service.CommentServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api/cmts")
public class CommentsController {
    @Autowired
    CommentServiceImp commentServiceImp;

    @GetMapping("/getAllCmts/{id}")
    public ResponseEntity<?> getAllCmtByQGrDeId(@PathVariable("id") int id) {
        try {
            Gson gson = new Gson();
            String data = gson.toJson(commentServiceImp.getAllCmtByQGrDetailId(id));
            System.out.println(data);
            ObjectMapper mapper = new ObjectMapper();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(df);
            CommentPojo[] listData = mapper.readValue(data, CommentPojo[].class);
            return new ResponseEntity<CommentPojo[]>(listData, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return  new ResponseEntity<String>("Get Cmts Error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("insert")
    public ResponseEntity insertCmt(@RequestParam("accountID") int accountID, @RequestParam("qGrDeID") int qGrDeID,
                                    @RequestParam("comment") String comment) {

        try {
            Accounts accounts = new Accounts();
            accounts.setId(accountID);
            QuestionGroupsDetail questionGroupsDetail = new QuestionGroupsDetail();
            questionGroupsDetail.setId(qGrDeID);
            Comments comments = new Comments();
            comments.setAccounts(accounts);
            comments.setQuestionGroupsDetail(questionGroupsDetail);
            comments.setComment(comment);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            Date now = new Date();
            commentServiceImp.insertComment(comments);
            return new ResponseEntity<String>("Success", HttpStatus.OK);

        }catch (Exception e ) {
            return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }
}
