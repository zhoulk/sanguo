package com.mud.dao;

import com.mud.mapper.UserBattle;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/23.
 */
public interface UserBattleDao {

    @Select("SELECT * FROM user_battle WHERE user_id = #{0} AND chapter_id = #{1}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "battleId", column = "battle_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time"),
    })
    ArrayList<UserBattle> getAllUserBattleByChapterId(String userId, String chapterId);

    @Select("SELECT * FROM user_battle WHERE user_id = #{0} AND battle_id = #{1}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "battleId", column = "battle_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time"),
    })
    UserBattle getUserBattleByBattleId(String userId, String battleId);

    @Insert("INSERT INTO user_battle(user_id, chapter_id, battle_id, status) " +
            "VALUES " +
            "(#{userId}, #{chapterId}, #{battleId}, #{status})")
    void insertUserBattle(UserBattle userBattle);

    @Update("UPDATE user_battle " +
            "SET " +
            "status = #{status} " +
            "WHERE " +
            "user_id = #{userId} AND battle_id = #{battleId}")
    void updateUserBattle(UserBattle userBattle);
}
