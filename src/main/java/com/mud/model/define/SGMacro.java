package com.mud.model.define;

/**
 * Created by leeesven on 17/9/19.
 */
public class SGMacro {

    /************************
     * 系统开关
     ************************/
    public static final int SG_SYS_ENABLR = 1;
    public static final int SG_SYS_DISABLE = 0;


    /************************
     * 战法
     ************************/

    /**
     * 战法类型 指挥
     */
    public static final String SG_SKILL_TYPE_DIRECTOR = "指挥";
    /**
     * 战法类型 主动
     */
    public static final String SG_SKILL_TYPE_ACTIVE = "主动";
    /**
     * 战法类型 被动
     */
    public static final String SG_SKILL_TYPE_PASSIVE = "被动";



    /**
     * 士兵类型 弓
     */
    public static final String SG_SKILL_SOLDIER_TYPE_G = "弓";
    /**
     * 士兵类型 步
     */
    public static final String SG_SKILL_SOLDIER_TYPE_B = "步";
    /**
     * 士兵类型 骑
     */
    public static final String SG_SKILL_SOLDIER_TYPE_Q = "骑";
    /**
     * 士兵类型 弓，步
     */
    public static final String SG_SKILL_SOLDIER_TYPE_G_B = "弓步";
    /**
     * 士兵类型 弓，骑
     */
    public static final String SG_SKILL_SOLDIER_TYPE_G_Q = "弓骑";
    /**
     * 士兵类型 步，骑
     */
    public static final String SG_SKILL_SOLDIER_TYPE_B_Q = "步骑";
    /**
     * 士兵类型 弓，步，骑
     */
    public static final String SG_SKILL_SOLDIER_TYPE_G_B_Q = "弓步骑";


    /**
     * 攻击目标类型 自己
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_SELF = 1;
    /**
     * 攻击目标类型 我军群体(有效距离内1个目标)
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_FRIEND_1 = 2;
    /**
     * 攻击目标类型 我军群体(有效距离内2个目标)
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_FRIEND_2 = 3;
    /**
     * 攻击目标类型 我军群体(有效距离内3个目标)
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_FRIEND_3 = 4;
    /**
     * 攻击目标类型 敌军群体(有效距离内1个目标)
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_ENETMY_1 = 5;
    /**
     * 攻击目标类型 敌军群体(有效距离内2个目标)
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_ENETMY_2 = 6;
    /**
     * 攻击目标类型 敌军群体(有效距离内3个目标)
     */
    public static final int SG_SKILL_ATK_DEST_TYPE_ENETMY_3 = 7;


    /*********************
     * 英雄
     ********************/

    /**
     * 用户英雄状态 已获取
     */
    public static final int SG_HERO_USER_HERO_GET = 1;
    /**
     * 用户英雄状态 未获取
     */
    public static final int SG_HERO_USER_HERO_NOT_GET = 2;
    /**
     * 用户英雄状态 未激活
     */
    public static final int SG_HERO_USER_HERO_NOT_ACTIVE = 3;
    /**
     * 用户英雄状态 已拆解 转化成 技能
     */
    public static final int SG_HERO_USER_HERO_CONVERTED_SKILL = 4;
    /**
     * 用户英雄状态 已上阵
     */
    public static final int SG_HERO_USER_HERO_SELECTED = 5;
    /**
     * 用户英雄状态 已拆解 转化成 战法经验
     */
    public static final int SG_HERO_USER_HERO_CONVERTED_SKill_POINT = 6;
    /**
     * 用户英雄状态 已拆解 转化成 战法研究
     */
    public static final int SG_HERO_USER_HERO_CONVERTED_SKill_INTO = 7;


    /*********************
     * 战役
     ********************/

    /**
     * 用户战役状态   未通关
     */
    public static final int SG_BATTLE_USER_BATTLE_UN_COMPLETE = 1;
    /**
     * 用户战役状态   未激活
     */
    public static final int SG_BATTLE_USER_BATTLE_UN_ACTIVE = 2;
    /**
     * 用户战役状态   已通关
     */
    public static final int SG_BATTLE_USER_BATTLE_COMPLETED = 3;


    /*********************
     * 战报
     ********************/
    /**
     * 战报结果  胜利
     */
    public static final int SG_REPORT_RESULT_WIN = 1;
    /**
     * 战报结果  失败
     */
    public static final int SG_REPORT_RESULT_LOST = 2;
    /**
     * 战报结果  平局
     */
    public static final int SG_REPORT_RESULT_TIE = 3;


    /*********************
     * 战役扩展类型
     ********************/
    /**
     * 战役奖励  经验
     */
    public static final int SG_BATTLE_REWARD_EXP = 1;
    /**
     * 战役奖励  物品
     */
    public static final int SG_BATTLE_REWARD_PRODUCT = 2;
    /**
     * 战役消耗  体力
     */
    public static final int SG_BATTLE_EXPEND_SP = 101;

}
