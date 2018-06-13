package com.mud.dao;

import com.mud.mapper.Hero;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/19.
 */
public interface HeroDao {

    @Select("SELECT * FROM hero where hero_id = #{heroId}")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "towerAtk", column = "tower_atk"),
            @Result(property = "moveSpeed", column = "speed"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "exSkillId", column = "ex_skill_id"),
    })
    Hero getHeroById(String heroId);

    @Select("SELECT * FROM hero order by hero_id")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "towerAtk", column = "tower_atk"),
            @Result(property = "moveSpeed", column = "speed"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "exSkillId", column = "ex_skill_id"),
    })
    ArrayList<Hero> getAllHero();

    @Select("SELECT * FROM hero WHERE can_select = 1 order by hero_id")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "towerAtk", column = "tower_atk"),
            @Result(property = "moveSpeed", column = "speed"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "exSkillId", column = "ex_skill_id"),
    })
    ArrayList<Hero> getAllHeroOfCanSelect();

    @Select("SELECT * FROM hero WHERE star = #{0} order by hero_id")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "towerAtk", column = "tower_atk"),
            @Result(property = "moveSpeed", column = "speed"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "exSkillId", column = "ex_skill_id"),
    })
    ArrayList<Hero> getAllHeroOfStar(int star);
}