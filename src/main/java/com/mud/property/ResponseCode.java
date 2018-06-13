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
     * 英雄上阵成功
     */
    HERO_SELECT_SUCCESS("英雄上阵成功！", SYS_SUCCESS.getValue()),
    /**
     * 英雄已上阵
     */
    HERO_SELECTED("英雄已上阵！", 404),
    /**
     * 英雄阵位错误
     */
    HERO_INDEX_ERR("英雄阵位错误！", 405),
    /**
     * 英雄阵位已存在英雄
     */
    HERO_INDEX_EXIST("英雄阵位已存在英雄！", 406),
    /**
     * 英雄未上阵
     */
    HERO_DESELECTED("英雄未上阵！", 407),
    /**
     * 英雄随机失败
     */
    HERO_RANDOM_FAIL("抽取英雄失败！", 408),
    /**
     * 英雄下阵成功
     */
    HERO_DESELECT_SUCCESS("英雄下阵成功！", SYS_SUCCESS.getValue()),


    /**
     * 战法不存在
     */
    SKILL_NOT_EXIST("战法不存在！", 502),
    /**
     * 战法未获取
     */
    SKILL_NOT_GET("战法未获取！", 503),
    /**
     * 战法扩展属性Err
     */
    SKILL_EXTEND_PROP_ERR("战法扩展属性Err！", 504),


    /**
     * 英雄合成成功
     */
    HERO_PIECE_INTO_SUCCESS("英雄合成成功！", SYS_SUCCESS.getValue()),
    /**
     * 英雄碎片不存在
     */
    HERO_PIECE_NOT_EXIST("英雄碎片不存在！", 601),
    /**
     * 不是英雄碎片
     */
    HERO_PIECE_NOT_TRUE("不是英雄碎片！", 602),
    /**
     * 该碎片无法合成
     */
    HERO_PIECE_CANNOT_INTO("该碎片无法合成！", 603),
    /**
     * 碎片不足
     */
    HERO_PIECE_NOT_ENOUGH("碎片不足！", 604),
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
