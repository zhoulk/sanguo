package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 2017/9/21.
 */
public class UserReport {

    // '战报编号'
    private String reportId;
    // '用户编号'
    private String userId;
    // '章回'
    private String chapterId;
    // '战役'
    private String battleId;
    // '状态 0 平局 1 胜利 2 失败'
    private int result;
    // '更新时间'
    private Date updateTime;
    // '创建时间'
    private Date createTime;

    public String getReportId() {
        return reportId;
    }

    public String getUserId() {
        return userId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public String getBattleId() {
        return battleId;
    }

    public int getResult() {
        return result;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
