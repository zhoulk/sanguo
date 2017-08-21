package com.mud.property;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by leeesven on 17/8/21.
 */
public enum ResponseCode {

    SYS_SUCCESS("", 200),

    USER_REGISTER_NAME_EXIST("用户已存在！", 301),
    USER_REGISTER_SUCCESS("注册成功", SYS_SUCCESS.getValue()),
    USER_LOGIN_SUCCESS("登陆成功", SYS_SUCCESS.getValue()),
    USER_LOGIN_NAME_NOT_EXIST("用户名不存在！", 302),
    USER_LOGIN_PASSWORD_ERR("密码错误！", 303),
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
