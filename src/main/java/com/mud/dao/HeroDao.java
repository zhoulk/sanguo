package com.mud.dao;

import com.mud.mapper.Hero;
import com.mud.mapper.defines.SoldierType;
import com.mud.mapper.defines.SoldierTypeTypeHandler;
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
            @Result(property = "moveSpeed", column = "move_speed"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "exSkillId", column = "ex_skill_id"),
            @Result(property = "type", column = "type", javaType = SoldierType.class, typeHandler = SoldierTypeTypeHandler.class)
    })
    Hero getHeroById(String heroId);

    @Select("SELECT * FROM hero order by hero_id")
    @Results({
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "towerAtk", column = "tower_atk"),
            @Result(property = "moveSpeed", column = "move_speed"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "exSkillId", column = "ex_skill_id"),
            @Result(property = "type", column = "type", javaType = SoldierType.class, typeHandler = SoldierTypeTypeHandler.class)
    })
    ArrayList<Hero> getAllHero();
}