package com.mud.controller;

import com.mud.dao.BattleDao;
import com.mud.dao.ChapterDao;
import com.mud.mapper.Battle;
import com.mud.mapper.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by leeesven on 17/8/20.
 */

@RequestMapping("/api/battle")
@RestController
public class BattleController {

    @Autowired
    ChapterDao chapterDao;

    @Autowired
    BattleDao battleDao;

    @GetMapping(value = "/chapter/all")
    public ArrayList<Chapter> getAllChapter(){
        return chapterDao.getAllChapter();
    }

    @GetMapping(value = "/chapter/{chapterId}")
    public Chapter getChapterById(@PathVariable Integer chapterId){
        return chapterDao.getChapterById(chapterId);
    }

    @GetMapping(value = "/all")
    public ArrayList<Battle> getAllBattleByChapterId(@RequestParam Integer chapterId){
        return battleDao.getAllBattleByChapterId(chapterId);
    }

    @GetMapping(value = "/{battleId}")
    public Battle getBattleById(@PathVariable Integer battleId){
        return battleDao.getBattleById(battleId);
    }
}
