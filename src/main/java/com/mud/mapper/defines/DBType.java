package com.mud.mapper.defines;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/20.
 */
public enum DBType {

    /**
     * 士兵类型 弓
     */
    SOLDIER_G("弓", 1),
    /**
     * 士兵类型 步
     */
    SOLDIER_B("步", 2),
    /**
     * 士兵类型 骑
     */
    SOLDIER_Q("骑", 3),
    /**
     * 士兵类型 弓，步
     */
    SOLDIER_G_B("弓，步", 4),
    /**
     * 士兵类型 弓，骑
     */
    SOLDIER_G_Q("弓，骑", 5),
    /**
     * 士兵类型 步，骑
     */
    SOLDIER_B_Q("步，骑", 6),
    /**
     * 士兵类型 弓，步，骑
     */
    SOLDIER_G_B_Q("弓，步，骑", 7),

    /**
     * 战法类型 指挥
     */
    SKILL_DIRECTOR("指挥", 1),
    /**
     * 战法类型 主动
     */
    SKILL_ACTIVE("主动", 2),
    /**
     * 战法类型 被动
     */
    SKILL_PASSIVE("被动", 3)
    ;

    private String desc;
    private int value;
    private DBType(String desc, int value){
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
