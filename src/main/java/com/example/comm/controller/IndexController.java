package com.example.comm.controller;

import com.example.comm.mapper.UserMapper;
import com.example.comm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index(HttpServletRequest request){

        return "index";

    }

}
