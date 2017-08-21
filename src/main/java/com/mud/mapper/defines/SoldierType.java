package com.mud.mapper.defines;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/20.
 */
public enum SoldierType {
    G("弓", 1),
    B("步", 2),
    Q("骑", 3),
    G_B("弓，步", 4),
    G_Q("弓，骑", 5),
    B_Q("步，骑", 6),
    G_B_Q("弓，步，骑", 7);

    private String desc;
    private int value;
    private SoldierType(String desc, int value){
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
