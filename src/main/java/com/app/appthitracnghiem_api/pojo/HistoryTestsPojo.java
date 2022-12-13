package com.app.appthitracnghiem_api.pojo;

import java.util.Date;

public class HistoryTestsPojo {
    private int correct_answer;
    private int incorrect_answer;
    private double score;
    private String name_gr_detail;

    public String getNameQuestionGrDe() {
        return name_gr_detail;
    }

    public void setNameQuestionGrDe(String nameQuestionGrDe) {
        this.name_gr_detail = nameQuestionGrDe;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getIncorrect_answer() {
        return incorrect_answer;
    }

    public void setIncorrect_answer(int incorrect_answer) {
        this.incorrect_answer = incorrect_answer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
