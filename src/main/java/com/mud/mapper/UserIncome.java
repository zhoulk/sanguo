package com.mud.mapper;

import java.util.Date;

/**
 * Created by zlk on 2018/6/13.
 */
public class UserIncome {

    private String userId;
    private long gold;
    private long tongBi;
    private Date updateTime;
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
