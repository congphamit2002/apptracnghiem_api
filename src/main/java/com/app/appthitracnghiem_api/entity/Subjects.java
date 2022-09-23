package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "subjects")
public class Subjects {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name_subject")
    private String subjectName;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "subject")
    private Set<QuestionGroups> listQuestionGroups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
