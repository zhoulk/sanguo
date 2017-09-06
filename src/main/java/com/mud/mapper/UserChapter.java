package com.mud.mapper;

import com.mud.mapper.defines.DBStatus;

import java.util.Date;

/**
 * Created by leeesven on 17/8/23.
 */
public class UserChapter {

    // '用户编号'
    private String userId;

    // '章回'
    private Integer chapterId;

    // '得分'
    private Integer score;

    // '状态 1 未通关 2 未激活'
    private DBStatus status;

    // '更新时间'
    private Date updateTime;

    // '创建时间'
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public DBStatus getStatus() {
        return status;
    }

    public void setStatus(DBStatus status) {
        this.status = status;
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
