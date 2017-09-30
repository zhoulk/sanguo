package com.mud.mapper;

/**
 * Created by leeesven on 17/8/20.
 */
public class Battle {

    private Integer battleId;
    private Integer chapterId;
    private String battleTitle;
    private String battleDesc;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
