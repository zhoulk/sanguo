package com.mud.mapper;

import java.io.Serializable;

/**
 * Created by leeesven on 17/8/20.
 */
public class Skill implements Serializable{

    private String skillId;
    private String skillName;
    private String desc;
    private String script;
    private int skillType;
    private int soldierType;
    private Integer atkDist;
    private String atkDest;
    private String atkDestType;
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

    public int getSkillType() {
        return skillType;
    }

    public void setSkillType(int skillType) {
        this.skillType = skillType;
    }

    public int getSoldierType() {
        return soldierType;
    }

    public void setSoldierType(int soldierType) {
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

    public String getAtkDestType() {
        return atkDestType;
    }

    public void setAtkDestType(String atkDestType) {
        this.atkDestType = atkDestType;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
