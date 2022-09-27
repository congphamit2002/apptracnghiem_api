package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.Subjects;
import com.app.appthitracnghiem_api.service.QuestionGroupsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questionGroups")
public class QuestionGroupsController {

    @Autowired
    QuestionGroupsServiceImp questionGroupsServiceImp;

    @GetMapping("/getQGBySubjectId/{id}")
    public ResponseEntity<?> getQGBySubjectId(@PathVariable("id") int id) {
        try {
            List<QuestionGroups> list = questionGroupsServiceImp.getAllQuestionGroupsBySubjectId(id);

            return new ResponseEntity<List<QuestionGroups>>(list, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>("Subject Id is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteQGrById(@PathVariable("id") int id) {
        try {
            if(questionGroupsServiceImp.deleteQGrById(id)) {

                return new ResponseEntity<String>("Delete Successfully", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<String>("Subject Id is invalid", HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            return new ResponseEntity<String>("Subject Id is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertQGr (@RequestParam("subjectId") int subjectId,
                                        @RequestParam("nameGroup") String nameGroup) {
        QuestionGroups questionGroups = new QuestionGroups();
        questionGroups.setNameGroup(nameGroup);
        Subjects subjects = new Subjects();
        subjects.setId(subjectId);
        questionGroups.setSubject(subjects);

        if(questionGroupsServiceImp.insertQGr(questionGroups)) {
            return new ResponseEntity<String>("Insert Question Group Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Insert Question Group Failed", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/update")
    public ResponseEntity<?> updateQGr (@RequestParam("id") int id,
                                        @RequestParam("subjectId") int subjectId,
                                        @RequestParam("nameGroup") String nameGroup) {
        QuestionGroups questionGroups = new QuestionGroups();
        questionGroups.setId(id);
        questionGroups.setNameGroup(nameGroup);
        Subjects subjects = new Subjects();
        subjects.setId(subjectId);
        questionGroups.setSubject(subjects);

        if(questionGroupsServiceImp.updateQGr(questionGroups)) {
            return new ResponseEntity<String>("Update Question Group Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Update Question Group Failed", HttpStatus.BAD_REQUEST);

    }
}