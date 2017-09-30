package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 17/8/23.
 */
public class UserSkill {

    /**
     * 用户战法Id
     */
    private String userSkillId;

    /**
     * '用户编号'
     */
    private String userId;

    /**
     * '战法编号'
     */
    private String skillId;

    /**
     * '等级'
     */
    private Integer level;

    /**
     * '合成进度'
     */
    private Integer percent;

    /**
     * '装配英雄编号'
     */
    private String useHeroId;

    /**
     * '更新时间'
     */
    private Date updateTime;

    /**
     * '创建时间'
     */
    private Date createTime;

    public String getUserSkillId() {
        return userSkillId;
    }

    public void setUserSkillId(String userSkillId) {
        this.userSkillId = userSkillId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public Integer getLevel() {
        if (level == null)
            level = 0;
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUseHeroId() {
        return useHeroId;
    }

    public void setUseHeroId(String useHeroId) {
        this.useHeroId = useHeroId;
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

    public Integer getPercent() {
        if (percent == null)
            percent = 0;
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
