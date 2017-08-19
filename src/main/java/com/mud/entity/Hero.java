package com.mud.entity;

/**
 * Created by leeesven on 17/8/19.
 */
public class Hero {

    private String heroId;
    private String nickname;
    private String desc;
    private Integer star;
    private Integer cost;
    private Integer type;
    private Integer intelligence;
    private Integer atkDist;
    private Integer towerAtk;
    private Integer attack;
    private Integer defence;
    private Integer moveSpeed;
    private String skillId;
    private String exSkillId;



    public String getHeroId() {
        return heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getExSkillId() {
        return exSkillId;
    }

    public void setExSkillId(String exSkillId) {
        this.exSkillId = exSkillId;
    }
}
