package com.mud.mapper;

import com.mud.mapper.defines.SkillType;
import com.mud.mapper.defines.SoldierType;

import java.io.Serializable;

/**
 * Created by leeesven on 17/8/20.
 */
public class Skill implements Serializable{

    private String skillId;
    private String skillName;
    private String desc;
    private SkillType skillType;
    private SoldierType soldierType;
    private Integer atkDist;
    private String atkDest;
    private String cond;
    private String caution;

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public SoldierType getSoldierType() {
        return soldierType;
    }

    public void setSoldierType(SoldierType soldierType) {
        this.soldierType = soldierType;
    }

    public Integer getAtkDist() {
        return atkDist;
    }

    public void setAtkDist(Integer atkDist) {
        this.atkDist = atkDist;
    }

    public String getAtkDest() {
        return atkDest;
    }

    public void setAtkDest(String atkDest) {
        this.atkDest = atkDest;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }
}
