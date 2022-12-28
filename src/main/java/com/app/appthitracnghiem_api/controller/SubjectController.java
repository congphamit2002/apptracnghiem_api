package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.entity.Subjects;
import com.app.appthitracnghiem_api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/getAllSubject")
    public ResponseEntity<?> getAllSubject() {
        ArrayList<Subjects> listResult = subjectServiceImp.getAllSubject();
        for(Subjects subjects : listResult) {
            subjects.setImage("/api/file/subjectImage/" +subjects.getImage());
        }
        return new ResponseEntity<ArrayList<Subjects>>(listResult, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/insertSubject")
    public ResponseEntity<?> insertSubject(@RequestParam("subjectName") String subjectName,
                                           @RequestParam("image")MultipartFile image) {

        try {
            if(subjectName.contains(",")) {
                String[] list = subjectName.split(",");
                subjectName = list[0];
            }
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

    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
    @PostMapping("/updateSubject")
    public ResponseEntity<?> updateSubject(@RequestParam("id") int id,
                                           @RequestParam("subjectName") String subjectName,
                                           @RequestParam("image") MultipartFile image) {

        try {
            if(subjectName.contains(",")) {
                String[] list = subjectName.split(",");
                subjectName = list[0];
            }
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
