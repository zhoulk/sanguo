package com.mud.mapper;

/**
 * Created by leeesven on 17/8/20.
 */
public class Chapter {

    private String chapterId;
    private String chapterTitle;
    private String chapterDesc;
    private int status;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
