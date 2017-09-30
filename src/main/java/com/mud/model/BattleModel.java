package com.mud.model;

import com.mud.mapper.Battle;
import com.mud.mapper.UserBattle;

/**
 * Created by leeesven on 17/8/23.
 */
public class BattleModel {

    private Integer battleId;
    private Integer chapterId;
    private String battleTitle;
    private String battleDesc;

    // '得分'
    private Integer score;

    // '状态 1 未通关 2 未激活'
    private int status;

    public BattleModel(Battle battle, UserBattle userBattle){
        this.chapterId = battle.getChapterId();
        this.battleId = battle.getBattleId();
        this.battleTitle = battle.getBattleTitle();
        this.battleDesc = battle.getBattleDesc();

        this.score = userBattle.getScore();
        this.status = userBattle.getStatus();
    }

    public Integer getBattleId() {
        return battleId;
    }

    public void setBattleId(Integer battleId) {
        this.battleId = battleId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getBattleTitle() {
        return battleTitle;
    }

    public void setBattleTitle(String battleTitle) {
        this.battleTitle = battleTitle;
    }

    public String getBattleDesc() {
        return battleDesc;
    }

    public void setBattleDesc(String battleDesc) {
        this.battleDesc = battleDesc;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
