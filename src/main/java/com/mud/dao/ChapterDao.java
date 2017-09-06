package com.mud.dao;

import com.mud.mapper.Chapter;
import com.mud.mapper.defines.DBStatus;
import com.mud.mapper.defines.DBStatusTypeHandler;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */
public interface ChapterDao {

    @Select("SELECT * FROM chapter order by chapter_id")
    @Results({
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "chapterTitle", column = "chapter_title"),
            @Result(property = "chapterDesc", column = "chapter_desc"),
            @Result(property = "status", column = "status", javaType = DBStatus.class, typeHandler = DBStatusTypeHandler.class),
    })
    public ArrayList<Chapter> getAllChapter();

    @Select("SELECT * FROM chapter WHERE chapter_id = #{chapterId}")
    @Results({
            @Result(property = "chapterId", column = "chapter_id"),
            @Result(property = "chapterTitle", column = "chapter_title"),
            @Result(property = "chapterDesc", column = "chapter_desc"),
            @Result(property = "status", column = "status", javaType = DBStatus.class, typeHandler = DBStatusTypeHandler.class),
    })
    public Chapter getChapterById(Integer chapterId);
}
