package com.mud.model;

import com.mud.mapper.Skill;
import com.mud.mapper.UserSkill;

/**
 * Created by leeesven on 17/8/23.
 */
public class SkillModel {

    private String skillId;
    private String skillName;
    private String desc;
    private String script;
    private String skillType;
    private String soldierType;
    private Integer atkDist;
    private String atkDestType;
    private String atkDest;
    private String cond;
    private String caution;

    /**
     * 用户战法Id
     */
    private String userSkillId;
    /**
     * '等级'
     */
    private Integer level;
    /**
     * 合成进度
     */
    private Integer percent;

    /**
     * '装配英雄编号'
     */
    private String useHeroId;

    private HeroModel useHeroModel;

    public int canUpExp; // 升级需要的经验

    public SkillModel(Skill skill) {
        this.skillId = skill.getSkillId();
        this.skillName = skill.getSkillName();
        this.desc = skill.getDesc();
        this.script = skill.getScript();
        this.skillType = skill.getSkillType();
        this.soldierType = skill.getSoldierType();
        this.atkDist = skill.getAtkDist();
        this.atkDestType = skill.getAtkDestType();
        this.atkDest = skill.getAtkDest();
        this.cond = skill.getCond();
        this.caution = skill.getCaution();
    }

    public SkillModel(Skill skill, UserSkill userSkill){
        this.skillId = skill.getSkillId();
        this.skillName = skill.getSkillName();
        this.desc = skill.getDesc();
        this.script = skill.getScript();
        this.skillType = skill.getSkillType();
        this.soldierType = skill.getSoldierType();
        this.atkDist = skill.getAtkDist();
        this.atkDestType = skill.getAtkDestType();
        this.atkDest = skill.getAtkDest();
        this.cond = skill.getCond();
        this.caution = skill.getCaution();

        this.userSkillId = userSkill.getUserSkillId();
        this.level= userSkill.getLevel();
        this.percent = userSkill.getPercent();
        this.useHeroId = userSkill.getUseHeroId();
    }

    public String getUserSkillId() {
        return userSkillId;
    }

    public void setUserSkillId(String userSkillId) {
        this.userSkillId = userSkillId;
    }

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

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public String getSoldierType() {
        return soldierType;
    }

    public void setSoldierType(String soldierType) {
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

    public Integer getLevel() {
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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public HeroModel getUseHeroModel() {
        return useHeroModel;
    }

    public void setUseHeroModel(HeroModel useHeroModel) {
        this.useHeroModel = useHeroModel;
    }

    public int getCanUpExp() {
        return canUpExp;
    }

    public void setCanUpExp(int canUpExp) {
        this.canUpExp = canUpExp;
    }
}
