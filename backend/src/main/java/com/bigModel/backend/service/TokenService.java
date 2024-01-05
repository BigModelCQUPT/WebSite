package com.bigModel.backend.service;

public interface TokenService {
    void addMailtoken(String mailtoken);

    void addYoutubetoken(String youtubetoken);

    void addTwittertoken(String twittertoken);

    void addUserEmail(String email);

    String getToken(String columnName);
}
