package com.mud.mapper.defines;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/20.
 */
public enum SkillType {
    DIRECTOR("指挥", 1), //指挥
    ACTIVE("主动", 2), //主动
    PASSIVE("被动", 3); //被动

    private String desc;
    private int value;
    private SkillType(String desc, int value){
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
