package com.mud.model;

import com.mud.mapper.Chapter;
import com.mud.mapper.UserChapter;
import com.mud.mapper.defines.DBStatus;

/**
 * Created by leeesven on 17/8/23.
 */
public class ChapterModel {

    // '章回'
    private Integer chapterId;

    private String chapterTitle;
    private String chapterDesc;

    // '得分'
    private Integer score;

    // '状态 1 未通关 2 未激活'
    private DBStatus status;

    public ChapterModel(Chapter chapter, UserChapter userChapter){
        this.chapterId = chapter.getChapterId();
        this.chapterTitle = chapter.getChapterTitle();
        this.chapterDesc = chapter.getChapterDesc();

        this.score = userChapter.getScore();
        this.status = userChapter.getStatus();
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getChapterDesc() {
        return chapterDesc;
    }

    public void setChapterDesc(String chapterDesc) {
        this.chapterDesc = chapterDesc;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public DBStatus getStatus() {
        return status;
    }

    public void setStatus(DBStatus status) {
        this.status = status;
    }
}