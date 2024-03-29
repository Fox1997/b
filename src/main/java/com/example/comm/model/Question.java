package com.example.comm.model;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer linkCount;


}
