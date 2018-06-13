package com.mud.model;

import com.mud.mapper.Hero;
import com.mud.mapper.UserHero;
import com.mud.mapper.UserSelectHero;

/**
 * Created by leeesven on 17/8/22.
 */
public class HeroModel {

    private String heroId;
    private String nickname;
    private String desc;
    private Integer star;
    private Integer cost;
    private int type;
    private Integer intelligence;
    private Integer atkDist;
    private Integer towerAtk;
    private Integer attack;
    private Integer defence;
    private Integer moveSpeed;
    private String skillId;
    private String exSkillId;
    private int atkNumOneRound; //每回合攻击次数

    private int level;
    private int status;
    private String exSkillId1;
    private String exSkillId2;
    private int soldierNum; //士兵数量
    private int exp;

    private String userHeroId;
    private Integer posRow;
    private Integer posCol;

    private SkillModel skill;
    private SkillModel exSkill1;
    private SkillModel exSkill2;

    private int canIntoNum; // 可以给指定战法增加多少进度 (实时计算)
    private int canSkillPoint; // 可以拆解多少战法经验

    private boolean isFriend; // 是否是友军
    private int exAtkNumOneRound; //每回合攻击次数
    private int exAttack; //附加攻击
    private int exSpeed; //附加速度
    private int exDefence; //附加防御
    private int exSoldierNum; //附加士兵

    public HeroModel(Hero hero) {
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
        this.moveSpeed = hero.getMoveSpeed();
    }

    public HeroModel(Hero hero, UserHero userHero) {
        this.heroId = hero.getHeroId();
        this.nickname = hero.getNickname();
        this.desc = hero.getDesc();
        this.star = hero.getStar();
        this.cost = hero.getCost();
        this.type = hero.getType();

        this.skillId = hero.getSkillId();
        this.exSkillId = hero.getExSkillId();
        this.moveSpeed = hero.getMoveSpeed();

        this.level = userHero.getLevel();
        this.status = userHero.getStatus();
        this.exSkillId1 = userHero.getExSkillId1();
        this.exSkillId2 = userHero.getExSkillId2();
        this.exp = userHero.getExp();

        this.userHeroId = userHero.getUserHeroId();

        this.intelligence = userHero.getIntelligence();
        this.atkDist = userHero.getAtkDist();
        this.towerAtk = userHero.getTowerAtk();
        this.attack = userHero.getAttack();
        this.defence = userHero.getDefence();
    }

    public HeroModel(Hero hero, UserHero userHero, UserSelectHero userSelectHero) {
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
        this.moveSpeed = hero.getMoveSpeed();

        this.level = userHero.getLevel();
        this.status = userHero.getStatus();
        this.exSkillId1 = userHero.getExSkillId1();
        this.exSkillId2 = userHero.getExSkillId2();
        this.exp = userHero.getExp();

        this.userHeroId = userHero.getUserHeroId();

        this.posRow = userSelectHero.getPosRow();
        this.posCol = userSelectHero.getPosCol();
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
        return attack + exAttack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefence() {
        return defence + exDefence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public Integer getMoveSpeed() {
        return moveSpeed + exSpeed;
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

    public String getUserHeroId() {
        return userHeroId;
    }

    public void setUserHeroId(String userHeroId) {
        this.userHeroId = userHeroId;
    }

    public SkillModel getSkill() {
        return skill;
    }

    public SkillModel getExSkill1() {
        return exSkill1;
    }

    public SkillModel getExSkill2() {
        return exSkill2;
    }

    public void setSkill(SkillModel skill) {
        this.skill = skill;
    }

    public void setExSkill1(SkillModel exSkill1) {
        this.exSkill1 = exSkill1;
    }

    public void setExSkill2(SkillModel exSkill2) {
        this.exSkill2 = exSkill2;
    }

    public int getExAtkNumOneRound() {
        return exAtkNumOneRound;
    }

    public void setExAtkNumOneRound(int exAtkNumOneRound) {
        this.exAtkNumOneRound = exAtkNumOneRound;
    }

    public void setExAttack(int exAttack) {
        this.exAttack = exAttack;
    }

    public int getExAttack() {
        return exAttack;
    }

    public int getAtkNumOneRound() {
        return 1 + exAtkNumOneRound;
    }

    public void setAtkNumOneRound(int atkNumOneRound) {
        this.atkNumOneRound = atkNumOneRound;
    }

    public int getExSpeed() {
        return exSpeed;
    }

    public int getExDefence() {
        return exDefence;
    }

    public void setExSpeed(int exSpeed) {
        this.exSpeed = exSpeed;
    }

    public void setExDefence(int exDefence) {
        this.exDefence = exDefence;
    }

    public int getSoldierNum() {
        return level * 300 + exSoldierNum;
    }

    public int getExSoldierNum() {
        return exSoldierNum;
    }

    public void setExSoldierNum(int exSoldierNum) {
        this.exSoldierNum = exSoldierNum;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCanIntoNum() {
        return canIntoNum;
    }

    public void setCanIntoNum(int canIntoNum) {
        this.canIntoNum = canIntoNum;
    }

    public int getCanSkillPoint() {
        return canSkillPoint;
    }

    public void setCanSkillPoint(int canSkillPoint) {
        this.canSkillPoint = canSkillPoint;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSoldierNum(int soldierNum) {
        this.soldierNum = soldierNum;
    }

    public Integer getPosRow() {
        return posRow;
    }

    public void setPosRow(Integer posRow) {
        this.posRow = posRow;
    }

    public Integer getPosCol() {
        return posCol;
    }

    public void setPosCol(Integer posCol) {
        this.posCol = posCol;
    }
}
