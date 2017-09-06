package com.mud.dao;

import com.mud.mapper.UserHero;
import com.mud.mapper.defines.DBStatus;
import com.mud.mapper.defines.DBStatusTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/22.
 */
public interface UserHeroDao {

    @Insert("INSERT INTO user_hero(user_hero_id, user_id, hero_id, level, status, ex_skill_id1, ex_skill_id2) " +
            "VALUES " +
            "(#{userHeroId}, #{userId}, #{heroId}, #{level}, #{status.value}, #{exSkillId1}, #{exSkillId2})")
    void insertUserHero(UserHero userHero);

    @Select("SELECT * FROM user_hero where user_id = #{userId}")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "status", column = "status", javaType = DBStatus.class, typeHandler = DBStatusTypeHandler.class),
            @Result(property = "exSkillId1", column = "ex_skill_id1"),
            @Result(property = "exSkillId2", column = "ex_skill_id2"),
    })
    ArrayList<UserHero> getAllUserHero(String userId);

    @Update("UPDATE user_hero " +
            "SET " +
            "status = #{status.value} " +
            "WHERE " +
            "user_hero_id = #{userHeroId}")
    void updateUserHero(UserHero userHero);

    @Select("SELECT * FROM user_hero WHERE user_hero_id = #{userHeroId}")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "status", column = "status", javaType = DBStatus.class, typeHandler = DBStatusTypeHandler.class),
            @Result(property = "exSkillId1", column = "ex_skill_id1"),
            @Result(property = "exSkillId2", column = "ex_skill_id2"),
    })
    UserHero getUserHeroByUserHeroId(String userHeroId);
}
