package com.mud.dao;

import com.mud.mapper.UserHero;
import com.mud.mapper.UserSelectHero;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/9/14.
 */
public interface UserSelectHeroDao {

    @Insert("INSERT INTO user_select_hero(user_hero_id, user_id, hero_id, position) " +
            "VALUES " +
            "(#{userHeroId}, #{userId}, #{heroId}, #{position})")
    void insertUserHero(UserSelectHero userHero);

    @Select("SELECT * FROM user_select_hero WHERE user_id = #{0} AND position = #{1}")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
    })
    UserSelectHero getUserSelectHeroByIndex(String userId, Integer position);

    @Select("SELECT * FROM user_select_hero WHERE user_hero_id = #{0}")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
    })
    UserSelectHero getUserSelectHeroByUserHeroId(String userHeroId);

    @Select("SELECT * FROM user_select_hero WHERE user_id = #{0} AND position != 0")
    @Results({
            @Result(property = "userHeroId", column = "user_hero_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "heroId", column = "hero_id"),
    })
    ArrayList<UserSelectHero> getUserSelectedHeros(String userId);

    @Update("UPDATE user_select_hero " +
            "SET " +
            "user_hero_id = #{userHeroId} ," +
            "hero_id = #{heroId} ," +
            "position = #{position} " +
            "WHERE " +
            "user_hero_id = #{userHeroId}")
    void updateUserSelectHero(UserSelectHero userSelectHero);
}
