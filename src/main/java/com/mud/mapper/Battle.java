package com.mud.mapper;

import com.mud.mapper.defines.BattleStatus;

/**
 * Created by leeesven on 17/8/20.
 */
public class Battle {

    private String battleId;
    private String chapterId;
    private String battleTitle;
    private String battleDesc;
    private BattleStatus status;

    public String getBattleId() {
        return battleId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
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

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }
}
