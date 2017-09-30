package com.mud.controller;

import com.mud.context.UserContext;
import com.mud.dao.BattleDao;
import com.mud.dao.ChapterDao;
import com.mud.dao.UserBattleDao;
import com.mud.dao.UserChapterDao;
import com.mud.mapper.*;
import com.mud.model.BattleModel;
import com.mud.model.ChapterModel;
import com.mud.model.ResponseModel;
import com.mud.model.define.SGMacro;
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
    private ChapterDao chapterDao;

    @Autowired
    private BattleDao battleDao;

    @Autowired
    private UserChapterDao userChapterDao;

    @Autowired
    private UserBattleDao userBattleDao;

    /**
     * 获取所有章节
     * @return  {@code ArrayList<Chapter>}
     */
    @GetMapping(value = "/chapter/all")
    public ResponseModel getAllChapter(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(chapterDao.getAllChapter());
        return responseModel;
    }

    /**
     * 获取指定章节信息
     * @param chapterId 章节Id
     * @return  {@code Chapter}
     */
    @GetMapping(value = "/chapter/{chapterId}")
    public ResponseModel getChapterById(@PathVariable Integer chapterId){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(chapterDao.getChapterById(chapterId));
        return responseModel;
    }

    /**
     * 获取指定章节全部战役
     * @param chapterId 章节Id
     * @return  {@code ArrayList<Battle>}
     */
    @GetMapping(value = "/all")
    public ResponseModel getAllBattleByChapterId(@RequestParam Integer chapterId){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(battleDao.getAllBattleByChapterId(chapterId));
        return responseModel;
    }

    /**
     * 获取指定战役信息
     * @param battleId  战役Id
     * @return  {@code Battle}
     */
    @GetMapping(value = "/{battleId}")
    public ResponseModel getBattleById(@PathVariable Integer battleId){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(battleDao.getBattleById(battleId));
        return responseModel;
    }

    /**
     * 获取登录用户的全部章节
     * @return  {@code ArrayList<ChapterModel>}
     */
    @GetMapping(value = "/chapter/all/user_chapter")
    public ResponseModel getAllUserChapter(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllUserChaper userId = " + userAuth.getUserId());

        ArrayList<UserChapter> userChapters = userChapterDao.getAllUserChapterByUserId(userAuth.getUserId());
        if (userChapters == null || userChapters.size() == 0){
            //生成 chapter 关联
            ArrayList<Chapter> allChapter = chapterDao.getAllChapter();
            for (Chapter chapter : allChapter){
                UserChapter userChapter = new UserChapter();
                userChapter.setUserId(userAuth.getUserId());
                userChapter.setChapterId(chapter.getChapterId());
                userChapter.setStatus(SGMacro.SG_BATTLE_USER_BATTLE_UN_COMPLETE);
                userChapterDao.insertUserChapter(userChapter);
            }
        }
        userChapters = userChapterDao.getAllUserChapterByUserId(userAuth.getUserId());

        ArrayList<ChapterModel> models = new ArrayList<>();
        for (UserChapter userChapter : userChapters){
            Chapter chapter = chapterDao.getChapterById(userChapter.getChapterId());
            models.add(new ChapterModel(chapter, userChapter));
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(models);

        return responseModel;
    }

    /**
     * 获取登录用户指定章节的战役
     * @param chapterId     章节Id
     * @return  {@code ArrayList<UserBattle>}
     */
    @GetMapping(value = "/all/user_battle")
    public ResponseModel getAllUserBattleByChapterId(@RequestParam Integer chapterId){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllUserChaper userId = " + userAuth.getUserId());

        ArrayList<UserBattle> userBattles = userBattleDao.getAllUserBattleByChapterId(userAuth.getUserId(), chapterId);
        if (userBattles == null || userBattles.size() == 0){
            //生成 battle 关联
            ArrayList<Battle> allBattle = battleDao.getAllBattleByChapterId(chapterId);
            for (Battle battle : allBattle){
                UserBattle userBattle = new UserBattle();
                userBattle.setUserId(userAuth.getUserId());
                userBattle.setChapterId(battle.getChapterId());
                userBattle.setBattleId(battle.getBattleId());
                userBattle.setStatus(SGMacro.SG_BATTLE_USER_BATTLE_UN_COMPLETE);
                userBattleDao.insertUserBattle(userBattle);
            }
        }
        userBattles = userBattleDao.getAllUserBattleByChapterId(userAuth.getUserId(), chapterId);

        ArrayList<BattleModel> models = new ArrayList<>();

        for (UserBattle userBattle : userBattles){
            Battle battle = battleDao.getBattleById(userBattle.getBattleId());
            models.add(new BattleModel(battle, userBattle));
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(models);

        return responseModel;
    }
}
