package com.example.comm.controller;

import com.example.comm.dto.QuestionDto;
import com.example.comm.mapper.QuestionMapper;
import com.example.comm.mapper.UserMapper;
import com.example.comm.model.Question;
import com.example.comm.model.User;
import com.example.comm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies=request.getCookies();
        if(cookies !=null && cookies.length!=0)
        {
            for (Cookie cookie : cookies)
            {
                if(cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    User user=userMapper.findByToken(token);
                    if(user != null)
                    {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }

        }
        List <QuestionDto> questionList = questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }

}
