package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 17/8/20.
 */
public class UserAuth {

    private String userId;
    private String authName;
    private String authId;
    private String authAccessToken;
    private Integer authExpires;
    private Date updateTime;
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthAccessToken() {
        return authAccessToken;
    }

    public void setAuthAccessToken(String authAccessToken) {
        this.authAccessToken = authAccessToken;
    }

    public Integer getAuthExpires() {
        return authExpires;
    }

    public void setAuthExpires(Integer authExpires) {
        this.authExpires = authExpires;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
