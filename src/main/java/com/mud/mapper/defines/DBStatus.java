package com.mud.mapper.defines;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/22.
 */
public enum DBStatus {
    /**
     * 用户英雄状态 已获取
     */
    USER_HERO_GET("已获取", 1),
    /**
     * 用户英雄状态 未获取
     */
    USER_HERO_NOT_GET("未获取", 2),
    /**
     * 用户英雄状态 未激活
     */
    USER_HERO_NOT_ACTIVE("未激活", 3),
    /**
     * 用户英雄状态 已拆解
     */
    USER_HERO_CONVERTED_SKILL("已拆解", 4),

    /**
     * 用户战役状态   未通关
     */
    USER_BATTLE_UN_COMPLETE("未通关", 1),
    /**
     * 用户战役状态   未激活
     */
    USER_BATTLE_UN_ACTIVE("未激活", 2),
    ;

    private String desc;
    private int value;
    private DBStatus(String desc, int value){
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
