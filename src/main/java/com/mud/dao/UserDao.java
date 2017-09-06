package com.mud.dao;

import com.mud.mapper.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by leeesven on 17/8/20.
 */
public interface UserDao {

    @Insert("INSERT INTO user(user_id,user_name,update_time,create_time) VALUES(#{userId}, #{userName}, #{updateTime}, #{createTime})")
    void insertUser(User user);

    @Select("SELECT * FROM user WHERE user_name = #{name}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time"),
    })
    User getUserByName(String name);

}
