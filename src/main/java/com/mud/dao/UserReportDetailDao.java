package com.mud.dao;

import com.mud.mapper.UserHero;
import com.mud.mapper.UserReportDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeesven on 2017/9/21.
 */
public interface UserReportDetailDao {

    @Insert("INSERT INTO user_report_detail(report_id, round, order_num, from_hero, skill, to_hero, prop, val, describ) " +
            "VALUES " +
            "(#{reportId}, #{round}, #{orderNum}, #{fromHero}, #{skill}, #{toHero}, #{prop}, #{val}, #{describ})")
    void insertUserReportDetail(UserReportDetail reportDetail);

    @Select("SELECT * FROM user_report_detail WHERE report_id = #{0}")
    @Results({
            @Result(property = "reportId", column = "report_id"),
            @Result(property = "orderNum", column = "order_num"),
            @Result(property = "fromHero", column = "from_hero"),
            @Result(property = "toHero", column = "to_hero"),
    })
    ArrayList<UserReportDetail> getAllUserReportDetail(String reportId);

}
