package com.mud.model;

import com.mud.mapper.User;
import com.mud.mapper.UserAuth;

/**
 * Created by leeesven on 17/8/22.
 */
public class UserModel {

    private String userName;
    private String authAccessToken;

    private int skillPoint;

    public UserModel(){}

    public UserModel(User user, UserAuth userAuth) {
        this.userName = user.getUserName();
        this.authAccessToken = userAuth.getAuthAccessToken();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthAccessToken() {
        return authAccessToken;
    }

    public void setAuthAccessToken(String authAccessToken) {
        this.authAccessToken = authAccessToken;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }
}
