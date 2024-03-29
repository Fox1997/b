package com.example.comm.Provider;

import com.alibaba.fastjson.JSON;
import com.example.comm.dto.AccessTokenDto;
import com.example.comm.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println("========================string=====================");
            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];
            System.out.println("========================token=====================");
            System.out.println(token);
            return  token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    public GithubUser getUser(String accessToken)
    {
        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://api.github.com/user?accessToken="+ accessToken)
//                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            System.out.println("========================githubUser=====================");
            System.out.println(githubUser);
            return  githubUser;
        }catch (IOException e){
        }
        return  null;
    }

}
