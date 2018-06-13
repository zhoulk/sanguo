package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 17/8/22.
 */
public class UserSelectHero {

    private String userHeroId;
    private String userId;
    private String heroId;
    private Integer posRow;
    private Integer posCol;
    private Date updateTime;
    private Date createTime;

    public String getUserHeroId() {
        return userHeroId;
    }

    public void setUserHeroId(String userHeroId) {
        this.userHeroId = userHeroId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
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

    public Integer getPosRow() {
        return posRow;
    }

    public void setPosRow(Integer posRow) {
        this.posRow = posRow;
    }

    public Integer getPosCol() {
        return posCol;
    }

    public void setPosCol(Integer posCol) {
        this.posCol = posCol;
    }
}
