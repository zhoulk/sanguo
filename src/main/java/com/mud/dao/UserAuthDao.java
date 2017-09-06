package com.mud.dao;

import com.mud.mapper.UserAuth;
import org.apache.ibatis.annotations.*;

/**
 * Created by leeesven on 17/8/21.
 */
public interface UserAuthDao {

    @Insert("INSERT INTO user_auth(user_id, auth_name, auth_id) VALUES (#{userId}, #{authName}, #{authId})")
    void insertUserAuth(UserAuth userAuth);

    @Select("SELECT * FROM user_auth where user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "authName", column = "auth_name"),
            @Result(property = "authId", column = "auth_id"),
            @Result(property = "authAccessToken", column = "auth_access_token"),
            @Result(property = "authExpires", column = "auth_expires"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    UserAuth getUserAuthByUserId(String userId);

    @Update("UPDATE user_auth SET auth_access_token = #{authAccessToken} WHERE user_id = #{userId}")
    void updateUserAuth(UserAuth userAuth);

    @Select("SELECT * FROM user_auth where auth_access_token = #{authAccessToken}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "authName", column = "auth_name"),
            @Result(property = "authId", column = "auth_id"),
            @Result(property = "authAccessToken", column = "auth_access_token"),
            @Result(property = "authExpires", column = "auth_expires"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    UserAuth getUserAuthByAccessToken(String token);
}
