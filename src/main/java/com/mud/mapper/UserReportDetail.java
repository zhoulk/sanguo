package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 2017/9/21.
 */
public class UserReportDetail {

    // '战报编号'
    private String reportId;
    // '回合数'
    private int round;
    // '编号'
    private int orderNum;
    // '触发武将'
    private String fromHero;
    // '战法'
    private String skill;
    // '影响武将'
    private String toHero;
    // '影响属性'
    private String prop;
    // '影响数值'
    private String val;
    // '描述'
    private String describ;
    // '更新时间'
    private Date updateTime;
    // '创建时间'
    private Date createTime;

    public String getReportId() {
        return reportId;
    }

    public int getRound() {
        return round;
    }

    public int getOrder() {
        return orderNum;
    }

    public String getFromHero() {
        return fromHero;
    }

    public String getSkill() {
        return skill;
    }

    public String getToHero() {
        return toHero;
    }

    public String getProp() {
        return prop;
    }

    public String getVal() {
        return val;
    }

    public String getDescrib() {
        return describ;
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

    public void setRound(int round) {
        this.round = round;
    }

    public void setOrderNum(int order) {
        this.orderNum = order;
    }

    public void setFromHero(String from) {
        this.fromHero = from;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setToHero(String to) {
        this.toHero = to;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setVal(String value) {
        this.val = value;
    }

    public void setDescrib(String desc) {
        this.describ = desc;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
