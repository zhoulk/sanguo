package com.mud.mapper;

import com.mud.mapper.defines.DBStatus;

import java.util.Date;

/**
 * Created by leeesven on 17/8/22.
 */
public class UserHero {

    public static final Integer skillPosition1 = 1;
    public static final Integer skillPosition2 = 2;

    private String userHeroId;
    private String userId;
    private String heroId;
    private Integer level;
    private DBStatus status;
    private String exSkillId1;
    private String exSkillId2;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public DBStatus getStatus() {
        return status;
    }

    public void setStatus(DBStatus status) {
        this.status = status;
    }

    public String getExSkillId1() {
        return exSkillId1;
    }

    public void setExSkillId1(String exSkillId1) {
        this.exSkillId1 = exSkillId1;
    }

    public String getExSkillId2() {
        return exSkillId2;
    }

    public void setExSkillId2(String exSkillId2) {
        this.exSkillId2 = exSkillId2;
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
