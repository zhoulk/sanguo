package com.mud.dao;

import com.mud.mapper.UserBattle;
import com.mud.mapper.defines.DBStatus;
import com.mud.mapper.defines.DBStatusTypeHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
            @Result(property = "status", column = "status", javaType = DBStatus.class, typeHandler = DBStatusTypeHandler.class),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time"),
    })
    ArrayList<UserBattle> getAllUserBattleByChapterId(String userId, Integer chapterId);

    @Insert("INSERT INTO user_battle(user_id, chapter_id, battle_id, status) " +
            "VALUES " +
            "(#{userId}, #{chapterId}, #{battleId}, #{status.value})")
    void insertUserBattle(UserBattle userBattle);
}
