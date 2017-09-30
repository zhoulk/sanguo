package com.mud.dao;

import com.mud.mapper.SkillExtend;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 2017/9/26.
 */
public interface SkillExtendDao {

    @Select("SELECT * FROM skill_extend WHERE skill_id = #{0} AND prop like 'intoCmd%' ORDER BY prop")
    @Results({
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    public ArrayList<SkillExtend> getExtendOfSkillIntoCmd(String skillId);

    @Select("SELECT * FROM skill_extend WHERE skill_id = #{0} AND prop = 'levelUp'")
    @Results({
            @Result(property = "skillId", column = "skill_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    public SkillExtend getExtendOfSkillLevelUp(String skillId);
}
