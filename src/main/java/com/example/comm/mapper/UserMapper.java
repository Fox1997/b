package com.example.comm.mapper;

import com.example.comm.dto.QuestionDto;
import com.example.comm.model.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token= #{token}")
    User findByToken(@Param("token") String token) ;

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);



}

