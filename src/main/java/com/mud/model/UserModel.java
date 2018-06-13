package com.mud.model;

import com.mud.mapper.User;
import com.mud.mapper.UserAuth;
import com.mud.mapper.UserExtend;

import java.util.List;

/**
 * Created by leeesven on 17/8/22.
 */
public class UserModel {

    private String userName;
    private String authAccessToken;

    private int skillPoint;
    private List<UserExtend> extendList;

    private long gold;
    private long tongBi;

    private long lvUpExp;

    public int getLv(){
        if(extendList != null){
            for (UserExtend u : extendList) {
                if(u.getProp().equalsIgnoreCase("lv")){
                    return Integer.parseInt(u.getVal());
                }
            }
        }
        return 0;
    }

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

    public List<UserExtend> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<UserExtend> extendList) {
        this.extendList = extendList;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getTongBi() {
        return tongBi;
    }

    public void setTongBi(long tongBi) {
        this.tongBi = tongBi;
    }

    public long getLvUpExp() {
        return lvUpExp;
    }

    public void setLvUpExp(long lvUpExp) {
        this.lvUpExp = lvUpExp;
    }
}

