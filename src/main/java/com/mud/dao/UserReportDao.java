package com.mud.dao;

import com.mud.mapper.UserReport;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by leeesven on 2017/9/21.
 */
public interface UserReportDao {

    @Insert("INSERT INTO user_report(report_id, user_id, chapter_id, battle_id, result) " +
            "VALUES " +
            "(#{reportId}, #{userId}, #{chapterId}, #{battleId}, #{result})")
    void insertUserReport(UserReport report);
}
