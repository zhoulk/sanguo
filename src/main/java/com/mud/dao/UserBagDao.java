package com.mud.dao;

import com.mud.mapper.UserBag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by leeesven on 2017/9/25.
 */
public interface UserBagDao {

    @Select("SELECT * FROM user_bag WHERE user_id = #{0} AND product_id = #{1}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
    })
    UserBag getUserBagOf(String userId, String productId);

    @Select("SELECT * FROM user_bag WHERE user_id = #{0}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
    })
    List<UserBag> getAllBagsOf(String userId);

    @Select("SELECT * FROM user_bag WHERE user_id = #{0} AND product_id in (SELECT product_id FROM product WHERE type = 1)")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "productId", column = "product_id"),
    })
    List<UserBag> getAllBagsOfHeroPiece(String userId);

    @Insert("INSERT INTO user_bag(user_id, product_id, num) VALUES (#{userId}, #{productId}, #{num})")
    void insertUserBag(UserBag userBag);

    @Update("UPDATE user_bag " +
            "SET " +
            "num = #{num} " +
            "WHERE user_id = #{userId} AND product_id = #{productId}")
    void updateUserBag(UserBag userBag);

}
