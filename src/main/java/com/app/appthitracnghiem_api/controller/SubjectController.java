package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.entity.Subjects;
import com.app.appthitracnghiem_api.service.SubjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectServiceImp subjectServiceImp;

    @GetMapping("/getAllSubject")
    public ResponseEntity<?> getAllSubject() {
        return new ResponseEntity<List<Subjects>>(subjectServiceImp.getAllSubject(), HttpStatus.OK);
    }

    @PostMapping("/insertSubject")
    public ResponseEntity<?> insertSubject(@RequestParam("subjectName") String subjectName,
                                           @RequestParam("image") String image) {

        Subjects subject = new Subjects();

        subject.setSubjectName(subjectName);
        subject.setImage(image);

        if(subjectServiceImp.insertSubject(subject)) {
            return new ResponseEntity<String>("Insert Subject Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Insert Subject Fail", HttpStatus.OK);

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") int id) {
        subjectServiceImp.deleteSubjectById(id);
        return new ResponseEntity<String>("Delete    Subject Successfully", HttpStatus.OK);
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
                                           @RequestParam("image") String image) {

        Subjects subject = new Subjects();

        subject.setId(id);
        subject.setSubjectName(subjectName);
        subject.setImage(image);

        if(subjectServiceImp.updateSubject(subject)) {
            return new ResponseEntity<String>("Update Subject Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Update Subject Fail", HttpStatus.OK);

    }
}
