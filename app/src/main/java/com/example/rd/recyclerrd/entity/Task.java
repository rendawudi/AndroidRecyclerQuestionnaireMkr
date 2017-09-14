package com.example.rd.recyclerrd.entity;


import java.util.ArrayList;
import java.util.List;


public class Task {

    private String type;                                    //问题类型
    private List<String> questions =new ArrayList<>();     //选项内容
    private String title;                                   //问题描述

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
