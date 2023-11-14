package com.example.pathfindr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int answerQuestion1; // Maps to the user's answer to question 1
    private int answerQuestion2; // Maps to the user's answer to question 2
    private int answerQuestion3; // Maps to the user's answer to question 3
    private int answerQuestion4; // Maps to the user's answer to question 4
    private int answerQuestion5; // Maps to the user's answer to question 5
    private int answerQuestion6; // Maps to the user's answer to question 6
    private int answerQuestion7; // Maps to the user's answer to question 7
    private int answerQuestion8; // Maps to the user's answer to question 8
    private int answerQuestion9; // Maps to the user's answer to question 9

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnswerQuestion1() {
        return answerQuestion1;
    }

    public void setAnswerQuestion1(int answerQuestion1) {
        this.answerQuestion1 = answerQuestion1;
    }

    public int getAnswerQuestion2() {
        return answerQuestion2;
    }

    public void setAnswerQuestion2(int answerQuestion2) {
        this.answerQuestion2 = answerQuestion2;
    }

    public int getAnswerQuestion3() {
        return answerQuestion3;
    }

    public void setAnswerQuestion3(int answerQuestion3) {
        this.answerQuestion3 = answerQuestion3;
    }

    public int getAnswerQuestion4() {
        return answerQuestion4;
    }

    public void setAnswerQuestion4(int answerQuestion4) {
        this.answerQuestion4 = answerQuestion4;
    }

    public int getAnswerQuestion5() {
        return answerQuestion5;
    }

    public void setAnswerQuestion5(int answerQuestion5) {
        this.answerQuestion5 = answerQuestion5;
    }

    public int getAnswerQuestion6() {
        return answerQuestion6;
    }

    public void setAnswerQuestion6(int answerQuestion6) {
        this.answerQuestion6 = answerQuestion6;
    }

    public int getAnswerQuestion7() {
        return answerQuestion7;
    }

    public void setAnswerQuestion7(int answerQuestion7) {
        this.answerQuestion7 = answerQuestion7;
    }

    public int getAnswerQuestion8() {
        return answerQuestion8;
    }

    public void setAnswerQuestion8(int answerQuestion8) {
        this.answerQuestion8 = answerQuestion8;
    }

    public int getAnswerQuestion9() {
        return answerQuestion9;
    }

    public void setAnswerQuestion9(int answerQuestion9) {
        this.answerQuestion9 = answerQuestion9;
    }

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Column(name = "topField")
    private String topField;

    public String getTopField() {
        return topField;
    }

    public void setTopField(String topField) {
        this.topField = topField;
    }

}
