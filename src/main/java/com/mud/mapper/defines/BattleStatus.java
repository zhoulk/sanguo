package com.mud.mapper.defines;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/20.
 */
public enum BattleStatus {
    UN_COMPLETE("未通关", 1),
    UN_ACTIVE("未激活", 2);

    private String desc;
    private int value;
    private BattleStatus(String desc, int value){
        this.desc = desc;
        this.value = value;
    }

    @JsonValue
    public int getValue(){
        return this.value;
    }

    public String getDesc() {
        return desc;
    }
}
