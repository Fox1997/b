package com.example.comm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;



}
