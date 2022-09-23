package com.app.appthitracnghiem_api.entity;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "question_groups")
public class QuestionGroups {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name_group")
    private String nameGroup;

    @OneToMany(mappedBy = "questionGroups")
    private Set<QuestionGroupsDetail> listQuestionGroupsDetails;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subjects subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
