package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.entity.Subjects;
import com.app.appthitracnghiem_api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectServiceImp subjectServiceImp;

    @Autowired
    QuestionServiceImp questionServiceImp;

    @Autowired
    QuestionGroupsDetailServiceImp questionGroupsDetailServiceImp;

    @Autowired
    QuestionGroupsServiceImp questionGroupsServiceImp;

    @Autowired
    FileSystemStorageServiceImp fileSystemStorageServiceImp;

    @GetMapping("/getAllSubject")
    public ResponseEntity<?> getAllSubject() {
        return new ResponseEntity<ArrayList<Subjects>>(subjectServiceImp.getAllSubject(), HttpStatus.OK);
    }

    @PostMapping("/insertSubject")
    public ResponseEntity<?> insertSubject(@RequestParam("subjectName") String subjectName,
                                           @RequestParam("image")MultipartFile image) {

        try {
            fileSystemStorageServiceImp.init(Constant.subjectImage);
            System.out.println("\t\tSubject name " + subjectName);
            Subjects subject = new Subjects();
            subject.setSubjectName(subjectName);
            subject.setImage(image.getOriginalFilename());
            subjectServiceImp.insertSubject(subject);

            if(!image.isEmpty()) {
                fileSystemStorageServiceImp.save(image, Constant.subjectImage);
            }

            return new ResponseEntity<String>("Insert Subject Successfully", HttpStatus.OK);

        }catch (Exception e ) {
            return new ResponseEntity<String>("Insert Subject Fail", HttpStatus.OK);

        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") int id) {
       try {
           ArrayList<QuestionGroups> questionGroups = questionGroupsServiceImp.getAllQuestionGroupsBySubjectId(id);
           for(QuestionGroups itemGr : questionGroups) {
               ArrayList<QuestionGroupsDetail> questionGroupsDetails = questionGroupsDetailServiceImp.getAllByQGrId(itemGr.getId());
               for(QuestionGroupsDetail item : questionGroupsDetails) {
                   questionServiceImp.deleteQuestionByQGrDetailId(item.getId());
               }
               questionGroupsDetailServiceImp.deleteQGrDetailByQGrID(itemGr.getId());
           }
           questionGroupsServiceImp.deleteQGrBySubjectId(id);
           subjectServiceImp.deleteSubjectById(id);

           return new ResponseEntity<String>("Delete    Subject Successfully", HttpStatus.OK);

       }catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<String>("Delete    Subject Failed", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/getSubject/{id}")
    public ResponseEntity<?> getSubjectByID(@PathVariable("id") int id) {
        Subjects subject = subjectServiceImp.getSubjectsById(id);

        if(subject != null) {
            return new ResponseEntity<Subjects>(subject, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Subject ID is invalid", HttpStatus.OK);
    }

    @PostMapping("/updateSubject")
    public ResponseEntity<?> updateSubject(@RequestParam("id") int id,
                                           @RequestParam("subjectName") String subjectName,
                                           @RequestParam("image") MultipartFile image) {

        try {
            Subjects subject = new Subjects();
            subject.setId(id);
            subject.setSubjectName(subjectName);
            if(!image.isEmpty())
                subject.setImage(image.getOriginalFilename());
            subjectServiceImp.updateSubject(subject);
            if(!image.isEmpty()) {
                fileSystemStorageServiceImp.save(image, Constant.subjectImage);
            }
            return new ResponseEntity<String>("Update Subject Successfully", HttpStatus.OK);
        }catch (Exception  e) {
            return new ResponseEntity<String>("Update Subject Fail", HttpStatus.OK);

        }



    }
}
