package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "comments")
public class Comments {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts accounts;

    @ManyToOne
    @JoinColumn(name = "qgroup_detail_id")
    private QuestionGroupsDetail questionGroupsDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public QuestionGroupsDetail getQuestionGroupsDetail() {
        return questionGroupsDetail;
    }

    public void setQuestionGroupsDetail(QuestionGroupsDetail questionGroupsDetail) {
        this.questionGroupsDetail = questionGroupsDetail;
    }
}
