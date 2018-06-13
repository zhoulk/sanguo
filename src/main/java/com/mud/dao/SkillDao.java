package com.mud.dao;

import com.mud.mapper.Skill;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */
public interface SkillDao {

    @Select("SELECT * FROM skill where skill_name = #{skillId}")
    @Results({
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "skillName", column = "skill_name"),
            @Result(property = "skillType", column = "skill_type"),
            @Result(property = "soldierType", column = "soldier_type"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "atkDest", column = "atk_dest"),
            @Result(property = "atkDestType", column = "atk_dest_type"),
    })
    Skill getSkillById(String skillId);

    @Select("SELECT * FROM skill order by skill_id")
    @Results({
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "skillName", column = "skill_name"),
            @Result(property = "skillType", column = "skill_type"),
            @Result(property = "soldierType", column = "soldier_type"),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "atkDest", column = "atk_dest"),
            @Result(property = "atkDestType", column = "atk_dest_type"),
    })
    ArrayList<Skill> getAllSkill();
}
