package com.mud.dao;

import com.mud.mapper.UserChapter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/23.
 */
public interface UserChapterDao {

    @Select("SELECT * FROM user_chapter WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time"),
    })
    ArrayList<UserChapter> getAllUserChapterByUserId(String userId);

    @Insert("INSERT INTO user_chapter(user_id, chapter_id, status) " +
            "VALUES " +
            "(#{userId}, #{chapterId}, #{status})")
    void insertUserChapter(UserChapter userChapter);
}
