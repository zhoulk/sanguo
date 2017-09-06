package com.mud.model;

import com.mud.mapper.Hero;
import com.mud.mapper.UserHero;
import com.mud.mapper.defines.DBStatus;
import com.mud.mapper.defines.DBType;

/**
 * Created by leeesven on 17/8/22.
 */
public class HeroModel {

    private String heroId;
    private String nickname;
    private String desc;
    private Integer star;
    private Integer cost;
    private DBType type;
    private Integer intelligence;
    private Integer atkDist;
    private Integer towerAtk;
    private Integer attack;
    private Integer defence;
    private Integer moveSpeed;
    private String skillId;
    private String exSkillId;

    private Integer level;
    private DBStatus status;
    private String exSkillId1;
    private String exSkillId2;

    public HeroModel(Hero hero, UserHero userHero) {
        this.heroId = hero.getHeroId();
        this.nickname = hero.getNickname();
        this.desc = hero.getDesc();
        this.star = hero.getStar();
        this.cost = hero.getCost();
        this.type = hero.getType();
        this.intelligence = hero.getIntelligence();
        this.atkDist = hero.getAtkDist();
        this.towerAtk = hero.getTowerAtk();
        this.attack = hero.getAttack();
        this.defence = hero.getDefence();
        this.skillId = hero.getSkillId();
        this.exSkillId = hero.getExSkillId();

        this.level = userHero.getLevel();
        this.status = userHero.getStatus();
        this.exSkillId1 = userHero.getExSkillId1();
        this.exSkillId2 = userHero.getExSkillId2();
    }

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

    public DBType getType() {
        return type;
    }

    public void setType(DBType type) {
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
}
