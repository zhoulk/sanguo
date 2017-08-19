package com.mud.dao;

import com.mud.entity.Hero;
import org.apache.ibatis.annotations.*;

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
            @Result(property = "exSkillId", column = "ex_skill_id")
    })
    Hero getHeroById(String heroId);
}