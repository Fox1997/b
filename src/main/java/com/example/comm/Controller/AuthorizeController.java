package com.example.comm.Controller;

import com.example.comm.Provider.GithubProvider;
import com.example.comm.dto.AccessTokenDto;
import com.example.comm.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${github.client.secret}")
    private String clientsecret;

    @Value("${github.redirect.uri}")
    private String redirecturi;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state)
    {
        AccessTokenDto accessTokenDto =new AccessTokenDto();
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(clientsecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirecturi);
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        return "index";

    }
}
