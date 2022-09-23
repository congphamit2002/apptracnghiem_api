package com.app.appthitracnghiem_api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "history_tests")
public class HistoryTests {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "correct_answer")
    private int correctAnswer;

    @Column(name = "incorrect_answer")
    private int incorrectAnswer;

    @Column(name = "time")
    private Date time;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    @ManyToOne
    @JoinColumn(name = "qgroup_detail_id")
    private QuestionGroupsDetail questionGroupsDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public void setIncorrectAnswer(int incorrectAnswer) {
        this.incorrectAnswer = incorrectAnswer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public QuestionGroupsDetail getQuestionGroupsDetail() {
        return questionGroupsDetail;
    }

    public void setQuestionGroupsDetail(QuestionGroupsDetail questionGroupsDetail) {
        this.questionGroupsDetail = questionGroupsDetail;
    }
}
