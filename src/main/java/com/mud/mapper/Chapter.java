package com.mud.mapper;

import com.mud.mapper.defines.DBStatus;

/**
 * Created by leeesven on 17/8/20.
 */
public class Chapter {

    private Integer chapterId;
    private String chapterTitle;
    private String chapterDesc;
    private DBStatus status;

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

    public DBStatus getStatus() {
        return status;
    }

    public void setStatus(DBStatus status) {
        this.status = status;
    }
}
