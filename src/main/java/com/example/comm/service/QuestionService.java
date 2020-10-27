package com.example.comm.service;

import com.example.comm.dto.PaginationDto;
import com.example.comm.dto.QuestionDto;
import com.example.comm.mapper.QuestionMapper;
import com.example.comm.mapper.UserMapper;
import com.example.comm.model.Question;
import com.example.comm.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;



    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto=new PaginationDto();
        Integer totalCount=questionMapper.count();
        paginationDto.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }
        if(page>paginationDto.getTotalPage()){
            page=paginationDto.getTotalPage();
        }
        Integer offset = size*(page-1);
        List<Question> questions=questionMapper.list(offset,size);
        List <QuestionDto> questionDtoList=new ArrayList<>();
        for (Question question : questions) {
            User user =userMapper.findById(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;

    }
}
