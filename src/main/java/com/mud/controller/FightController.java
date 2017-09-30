package com.mud.controller;

import com.mud.context.UserContext;
import com.mud.dao.*;
import com.mud.mapper.*;
import com.mud.model.HeroModel;
import com.mud.model.ResponseModel;
import com.mud.model.define.SGMacro;
import com.mud.service.FightService;
import com.mud.service.NpcService;
import com.mud.service.PlayerService;
import com.mud.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by leeesven on 17/8/21.
 */

@RequestMapping("/api/fight")
@RestController
public class FightController {

    private Logger logger = Logger.getLogger(FightController.class.getName());

    @Autowired
    NpcService battleService;

    @Autowired
    PlayerService playerService;

    @Autowired
    FightService fightService;

    @Autowired
    UserReportDao userReportDao;

    @Autowired
    UserReportDetailDao userReportDetailDao;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    UserBattleDao userBattleDao;

    /**
     * PVE 战役
     * @param battleId  战役Id
     * @return  {@code BattleModel}
     */
    @PostMapping("/pve/battle")
    public ResponseModel fightBattle(@RequestParam Integer battleId){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        logger.info("fightBattle userId = " + userId);

        ResponseModel responseModel = new ResponseModel();
        // 获取战役武将
        ArrayList<HeroModel> battleHeros = battleService.getHerosOfBattleId(battleId);
        // 获取出战武将
        ArrayList<HeroModel> selectHeros = playerService.getSelectedHerosOfUserId(userId);
        // fighting ...
        List<UserReportDetail> reportDetails = fightService.fight(selectHeros, battleHeros);

        // 存储
        String reportId = sequenceService.sequenceOfReport();

        int index = 0;
        int result = SGMacro.SG_REPORT_RESULT_TIE;
        for (UserReportDetail reportDetail : reportDetails) {
            index ++;
            reportDetail.setReportId(reportId);
            reportDetail.setOrderNum(index);
            userReportDetailDao.insertUserReportDetail(reportDetail);

            if (reportDetail.getProp().equalsIgnoreCase("win")){
                result = SGMacro.SG_REPORT_RESULT_WIN;
            }else if (reportDetail.getProp().equalsIgnoreCase("lost")){
                result = SGMacro.SG_REPORT_RESULT_LOST;
            }
        }

        UserBattle userBattle = userBattleDao.getUserBattleByBattleId(userId, battleId);

        UserReport report = new UserReport();
        report.setReportId(reportId);
        report.setChapterId(userBattle.getChapterId());
        report.setUserId(userId);
        report.setBattleId(battleId);
        report.setResult(result);
        userReportDao.insertUserReport(report);

        // 更新战役状态
        if (result == SGMacro.SG_REPORT_RESULT_WIN){
            userBattle.setStatus(SGMacro.SG_BATTLE_USER_BATTLE_COMPLETED);
            userBattleDao.updateUserBattle(userBattle);

            // 计算奖励
            List<UserReportDetail> rewardReportDetails = playerService.calBattleReward(userId, selectHeros, battleId);
            for (UserReportDetail reportDetail : rewardReportDetails) {
                index ++;
                reportDetail.setReportId(reportId);
                reportDetail.setOrderNum(index);
                userReportDetailDao.insertUserReportDetail(reportDetail);
            }
        }

        // 返回战斗记录
        responseModel.setData(userReportDetailDao.getAllUserReportDetail(reportId));

        return responseModel;
    }
}
