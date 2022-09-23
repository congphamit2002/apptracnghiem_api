package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "question_groups_detail")
public class QuestionGroupsDetail {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name_gr_detail")
    private String nameGrDetail;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "questionGroupsDetail")
    private Set<Comments> listComments;

    @OneToMany(mappedBy = "questionGroupsDetail")
    private Set<Questions> listQuestions;
    @ManyToOne
    @JoinColumn(name = "question_group_id")
    private QuestionGroups questionGroups;

    @OneToMany(mappedBy = "questionGroupsDetail")
    private Set<HistoryTests> listHistoryTests;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameGrDetail() {
        return nameGrDetail;
    }

    public void setNameGrDetail(String nameGrDetail) {
        this.nameGrDetail = nameGrDetail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionGroups getQuestionGroups() {
        return questionGroups;
    }

    public void setQuestionGroups(QuestionGroups questionGroups) {
        this.questionGroups = questionGroups;
    }
}
