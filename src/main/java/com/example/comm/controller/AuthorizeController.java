package com.example.comm.controller;

import com.example.comm.Provider.GithubProvider;
import com.example.comm.dto.AccessTokenDto;
import com.example.comm.dto.GithubUser;
import com.example.comm.mapper.UserMapper;
import com.example.comm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
@PropertySource({"classpath:application.properties"})
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired(required = false)
    private UserMapper userMapper;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response)
    {
        AccessTokenDto accessTokenDto =new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println("========================UserName=====================");
        System.out.println(githubUser.getName());
        if (githubUser != null && githubUser.getId() != null)
        {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            user.setAvatarUrl(githubUser.getAvatar_url());



            userMapper.insert(user);
//          insert database==session
//          request.getSession().setAttribute("user",githubUser);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";

        }else{
            return "redirect:/";

        }

    }
}
