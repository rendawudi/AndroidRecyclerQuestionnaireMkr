package com.example.rd.recyclerrd.entity;

/**
 * Created by rd on 2017/9/11.
 */

/**
 * Copyright 2017 bejson.com
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2017-09-11 18:50:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Task {

    private String type;
    private List<String> questions =new ArrayList<>();
    private String title;

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
