package com.mud.mapper;

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
    private int level;
    private int exp;
    private int sp;
    private int status;
    private String exSkillId1;
    private String exSkillId2;
    private Date updateTime;
    private Date createTime;

    private Integer intelligence;
    private Integer atkDist;
    private Integer towerAtk;
    private Integer attack;
    private Integer defence;
    private Integer moveSpeed;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public static Integer getSkillPosition1() {
        return skillPosition1;
    }

    public static Integer getSkillPosition2() {
        return skillPosition2;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getAtkDist() {
        return atkDist;
    }

    public void setAtkDist(Integer atkDist) {
        this.atkDist = atkDist;
    }

    public Integer getTowerAtk() {
        return towerAtk;
    }

    public void setTowerAtk(Integer towerAtk) {
        this.towerAtk = towerAtk;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public Integer getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(Integer moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
