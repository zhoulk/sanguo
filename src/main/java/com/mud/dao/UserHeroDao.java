package com.mud.dao;

import com.mud.mapper.UserHero;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/22.
 */
public interface UserHeroDao {

    @Insert("INSERT INTO user_hero(user_hero_id, user_id, hero_id, level, status, ex_skill_id1, ex_skill_id2) " +
            "VALUES " +
            "(#{userHeroId}, #{userId}, #{heroId}, #{level}, #{status}, #{exSkillId1}, #{exSkillId2})")
    void insertUserHero(UserHero userHero);

    @Select("SELECT * FROM user_hero where user_id = #{userId} AND status IN (1,5)")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "exSkillId1", column = "ex_skill_id1"),
            @Result(property = "exSkillId2", column = "ex_skill_id2"),
    })
    ArrayList<UserHero> getAllUserHero(String userId);

    @Update("UPDATE user_hero " +
            "SET " +
            "status = #{status}, " +
            "level = #{level}, " +
            "exp = #{exp}, " +
            "sp = #{sp}, " +
            "ex_skill_id1 = #{exSkillId1}, " +
            "ex_skill_id2 = #{exSkillId2} " +
            "WHERE " +
            "user_hero_id = #{userHeroId}")
    void updateUserHero(UserHero userHero);

    @Select("SELECT * FROM user_hero WHERE user_hero_id = #{userHeroId}")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "exSkillId1", column = "ex_skill_id1"),
            @Result(property = "exSkillId2", column = "ex_skill_id2"),
    })
    UserHero getUserHeroByUserHeroId(String userHeroId);

    @Select("SELECT * FROM user_hero WHERE user_id = #{0} AND hero_id = #{1}")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "exSkillId1", column = "ex_skill_id1"),
            @Result(property = "exSkillId2", column = "ex_skill_id2"),
    })
    UserHero getUserHeroByUserIdHeroId(String userId, String heroId);
}
