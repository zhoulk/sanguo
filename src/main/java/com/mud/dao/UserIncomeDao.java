package com.mud.dao;

import com.mud.mapper.UserIncome;
import org.apache.ibatis.annotations.*;

/**
 * Created by zlk on 2018/6/13.
 */
public interface UserIncomeDao {

    @Insert("INSERT INTO user_income(user_id, gold, tong_bi) VALUES (#{userId}, #{gold}, #{tongBi})")
    public void insertUserIncome(UserIncome userIncome);

    @Update("UPDATE user_income set gold = #{gold}, tong_bi = #{tongBi} WHERE user_id = #{userId}")
    public void updateUserInCome(UserIncome userIncome);

    @Select("SELECT * from user_income WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "tongBi", column = "tong_bi")
    })
    public UserIncome selectUserIncome(String userId);
}
