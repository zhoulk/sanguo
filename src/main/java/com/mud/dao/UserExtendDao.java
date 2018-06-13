package com.mud.dao;

import com.mud.mapper.UserExtend;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeesven on 2017/9/26.
 */
public interface UserExtendDao {

    @Select("SELECT * FROM user_extend WHERE user_id = #{0}")
    @Results({
            @Result(property = "userId", column = "user_id")
    })
    public List<UserExtend> getUserExtendList(String userId);

    @Insert("INSERT INTO user_extend(user_id, prop, val) VALUES (#{userId}, #{prop}, #{val})")
    public void insertExtendOfUser(UserExtend userExtend);

    @Update("UPDATE user_extend " +
            "SET " +
            "val = #{val} " +
            "WHERE user_id = #{userId} AND prop = #{prop}")
    public void updateExtendOfUser(UserExtend userExtend);
}
