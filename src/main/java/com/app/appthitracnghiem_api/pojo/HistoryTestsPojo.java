package com.app.appthitracnghiem_api.pojo;

import java.util.Date;

public class HistoryTestsPojo {
    private int correct_answer;
    private int incorrect_answer;
    private int score;
    private Date time;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
