package com.mud.dao;

import com.mud.mapper.Skill;
import com.mud.mapper.defines.DBTypeTypeHandler;
import com.mud.mapper.defines.DBType;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */
public interface SkillDao {

    @Select("SELECT * FROM skill where skill_id = #{skillId}")
    @Results({
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "skillName", column = "skill_name"),
            @Result(property = "skillType", column = "skill_type", javaType = DBType.class, typeHandler = DBTypeTypeHandler.class),
            @Result(property = "soldierType", column = "soldier_type", javaType = DBType.class, typeHandler = DBTypeTypeHandler.class),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "atkDest", column = "atk_dest"),
    })
    Skill getSkillById(String skillId);

    @Select("SELECT * FROM skill order by skill_id")
    @Results({
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "skillName", column = "skill_name"),
            @Result(property = "skillType", column = "skill_type", javaType = DBType.class, typeHandler = DBTypeTypeHandler.class),
            @Result(property = "soldierType", column = "soldier_type", javaType = DBType.class, typeHandler = DBTypeTypeHandler.class),
            @Result(property = "atkDist", column = "atk_dist"),
            @Result(property = "atkDest", column = "atk_dest"),
    })
    ArrayList<Skill> getAllSkill();
}
