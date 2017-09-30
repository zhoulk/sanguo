package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 2017/9/22.
 */
public class BattleExtend {

    //'战役'
    private int battleId;
    //'奖励类型  1 经验  2 物品'
    private int type;
    //'奖励值 type:1 经验值  type:2 物品Id'
    private String val;
    //'概率 百分比'
    private int chance;
    //'是否生效  0 失效  1 生效'
    private int enable;
    //'更新时间'
    private Date updateTime;
    //'创建时间'
    private Date createTime;

    public int getBattleId() {
        return battleId;
    }

    public int getType() {
        return type;
    }

    public String getVal() {
        return val;
    }

    public int getChance() {
        return chance;
    }

    public int getEnable() {
        return enable;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
