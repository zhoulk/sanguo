package com.mud.dao;

import com.mud.mapper.UserSkill;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by leeesven on 17/8/23.
 */
public interface UserSkillDao {

    @Insert("INSERT INTO user_skill(user_skill_id, user_id, skill_id)" +
            "VALUES " +
            "(#{userSkillId}, #{userId}, #{skillId})")
    void insertUserSkill(UserSkill userSkill);

    @Select("SELECT * FROM user_skill WHERE user_skill_id = #{userSkillId}")
    @Results({
            @Result(property = "userSkillId", column = "user_skill_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "useHeroId", column = "use_hero_id"),
    })
    UserSkill getUserSkillByUserSkillId(String userSkillId);

    @Select("SELECT * FROM user_skill WHERE level > 0 AND user_id = #{userId}")
    @Results({
            @Result(property = "userSkillId", column = "user_skill_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "useHeroId", column = "use_hero_id"),
    })
    List<UserSkill> getAllUserSkills(String userId);

    @Select("SELECT * FROM user_skill WHERE level = 0 AND user_id = #{userId}")
    @Results({
            @Result(property = "userSkillId", column = "user_skill_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "useHeroId", column = "use_hero_id"),
    })
    List<UserSkill> getAllIntoUserSkills(String userId);

    @Update("UPDATE user_skill " +
            "SET " +
            "level = #{level}, " +
            "percent = #{percent}, " +
            "use_hero_id = #{useHeroId} " +
            "WHERE " +
            "user_skill_id = #{userSkillId}")
    void updateUserSkill(UserSkill userSkill);
}
