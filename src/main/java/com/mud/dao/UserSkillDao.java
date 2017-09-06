package com.mud.dao;

import com.mud.mapper.UserSkill;
import com.mud.mapper.defines.DBStatus;
import com.mud.mapper.defines.DBStatusTypeHandler;
import org.apache.ibatis.annotations.*;

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
            @Result(property = "heroId", column = "hero_id"),
            @Result(property = "useHeroId", column = "use_hero_id"),
    })
    UserSkill getUserSkillByUserSkillId(String userSkillId);

    @Update("")
    void updateUserSkill(UserSkill userSkill);
}
