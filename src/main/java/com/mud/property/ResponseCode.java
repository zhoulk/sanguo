package com.mud.property;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/21.
 */
public enum ResponseCode {

    /**
     * Http请求成功
     */
    SYS_SUCCESS("", 200),

    /**
     * 用户已存在
     */
    USER_REGISTER_NAME_EXIST("用户已存在！", 301),
    /**
     * 注册成功
     */
    USER_REGISTER_SUCCESS("注册成功", SYS_SUCCESS.getValue()),
    /**
     * 登陆成功
     */
    USER_LOGIN_SUCCESS("登陆成功", SYS_SUCCESS.getValue()),
    /**
     * 用户名不存在
     */
    USER_LOGIN_NAME_NOT_EXIST("用户名不存在！", 302),
    /**
     * 密码错误
     */
    USER_LOGIN_PASSWORD_ERR("密码错误！", 303),
    /**
     * 英雄不存在
     */
    HERO_NOT_EXIST("英雄不存在!", 402),

    /**
     * 英雄未获取
     */
    HERO_NOT_GET("英雄未获取!", 403),

    /**
     * 战法不存在
     */
    SKILL_NOT_EXIST("战法不存在！", 502),

    /**
     * 战法未获取
     */
    SKILL_NOT_GET("战法未获取！", 503),

    ;

    private String desc;
    private int value;
    private ResponseCode(String desc, int value){
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
