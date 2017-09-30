package com.mud.service;

import com.mud.dao.*;
import com.mud.helper.NumberHelper;
import com.mud.mapper.*;
import com.mud.model.HeroModel;
import com.mud.model.SkillModel;
import com.mud.model.define.SGMacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by leeesven on 17/9/15.
 */

@Service
public class PlayerService {

    private Logger logger = Logger.getLogger(NpcService.class.getName());

    @Autowired
    UserSelectHeroDao userSelectHeroDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    UserHeroDao userHeroDao;

    @Autowired
    SkillDao skillDao;

    @Autowired
    BattleExtendDao battleExtendDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    HeroExtendDao heroExtendDao;

    @Autowired
    UserBagDao userBagDao;

    /**
     * 获取 指定用户 上阵武将信息
     * @param userId
     * @return
     */
    public ArrayList<HeroModel> getSelectedHerosOfUserId(String userId){
        ArrayList<HeroModel> heros = new ArrayList<>();

        logger.info("getSelectedHerosOfUserId userId = " + userId);

        ArrayList<UserSelectHero> selectHeroes = userSelectHeroDao.getUserSelectedHeros(userId);
        for (UserSelectHero selectHero : selectHeroes) {

            Hero hero = heroDao.getHeroById(selectHero.getHeroId());
            UserHero userHero = userHeroDao.getUserHeroByUserIdHeroId(userId, selectHero.getHeroId());
            HeroModel model = new HeroModel(hero, userHero, selectHero);
            // 添加战法信息
            Skill skill = skillDao.getSkillById(model.getSkillId());
            model.setSkill(new SkillModel(skill));
            if (model.getExSkillId1() != null && model.getExSkillId1().length() > 0){
                Skill exSkill1 = skillDao.getSkillById(model.getExSkillId1());
                model.setExSkill1(new SkillModel(exSkill1));
            }
            if (model.getExSkillId2() != null && model.getExSkillId2().length() > 0){
                Skill exSkill2 = skillDao.getSkillById(model.getExSkillId2());
                model.setExSkill2(new SkillModel(exSkill2));
            }
            heros.add(model);
        }

        return heros;
    }

    /**
     * 计算战役奖励
     * @param userId
     * @param battleId
     * @return
     */
    public List<UserReportDetail> calBattleReward(String userId, List<HeroModel> heros, int battleId){
        List<UserReportDetail> reportDetailList = new ArrayList<>();

        ArrayList<BattleExtend> battleRewards = battleExtendDao.getExtendOfBattle(battleId);
        for (BattleExtend reward : battleRewards) {
            switch(reward.getType()){
                case SGMacro.SG_BATTLE_REWARD_EXP:
                {
                    for (HeroModel hero : heros) {
                        UserReportDetail reportDetail = new UserReportDetail();
                        int exp = Integer.parseInt(reward.getVal());
                        reportDetail.setRound(10);
                        reportDetail.setFromHero(hero.getHeroId());
                        reportDetail.setProp("exp");
                        reportDetail.setVal(reward.getVal());
                        String desc = "奖励 【" + hero.getNickname() + "】经验 " + exp + "点";
                        reportDetail.setDescrib(desc);
                        reportDetailList.add(reportDetail);
                        logger.info(desc);

                        // 计算等级
                        UserHero userHero = userHeroDao.getUserHeroByUserIdHeroId(userId, hero.getHeroId());

                        int currentExp = userHero.getExp() + exp;

                        HeroExtend heroExtend = heroExtendDao.getHeroExtendLevelUp(hero.getHeroId());
                        if (heroExtend != null){
                            String script = heroExtend.getVal();
                            int levelUpExp = 100 * calScript(script, hero);
                            if (currentExp > levelUpExp){
                                currentExp = currentExp - levelUpExp;
                                userHero.setLevel(userHero.getLevel() + 1);
                                desc = "武将 【" + hero.getNickname() + "】升级到 " + userHero.getLevel() + " 级";
                                logger.info(desc);

                                UserReportDetail levelReportDetail = new UserReportDetail();
                                levelReportDetail.setRound(10);
                                levelReportDetail.setFromHero(hero.getHeroId());
                                levelReportDetail.setProp("level");
                                levelReportDetail.setVal(String.valueOf(userHero.getLevel()));
                                levelReportDetail.setDescrib(desc);
                                reportDetailList.add(levelReportDetail);
                            }
                        }

                        userHero.setExp(currentExp);

                        userHeroDao.updateUserHero(userHero);
                    }
                }
                    break;
                case SGMacro.SG_BATTLE_REWARD_PRODUCT:
                {
                    if(Math.random()*100 > reward.getChance()){
                        UserReportDetail reportDetail = new UserReportDetail();
                        String productId = reward.getVal();
                        Product product = productDao.getProductById(productId);
                        reportDetail.setRound(10);
                        reportDetail.setProp("product");
                        reportDetail.setVal(productId);
                        String desc = "获得 【" + product.getDescrib() + "】1 个";
                        reportDetail.setDescrib(desc);
                        reportDetailList.add(reportDetail);
                        logger.info(desc);

                        // 更新背包
                        UserBag userBag = userBagDao.getUserBagOf(userId, productId);
                        if (userBag == null){
                            userBag = new UserBag();
                            userBag.setUserId(userId);
                            userBag.setProductId(productId);
                            userBag.setNum(1);
                            userBagDao.insertUserBag(userBag);
                        }else{
                            userBag.setNum(userBag.getNum() + 1);
                            userBagDao.updateUserBag(userBag);
                        }
                    }
                }
                    break;
                case SGMacro.SG_BATTLE_EXPEND_SP:
                {
                    for (HeroModel hero : heros) {
                        UserReportDetail reportDetail = new UserReportDetail();
                        int expendSp = Integer.parseInt(reward.getVal());
                        reportDetail.setRound(10);
                        reportDetail.setFromHero(hero.getHeroId());
                        reportDetail.setProp("expend_sp");
                        reportDetail.setVal(reward.getVal());
                        String desc = "消耗 【" + hero.getNickname() + "】体力 " + expendSp + "点";
                        reportDetail.setDescrib(desc);
                        reportDetailList.add(reportDetail);
                        logger.info(desc);

                        UserHero userHero = userHeroDao.getUserHeroByUserIdHeroId(userId, hero.getHeroId());
                        int currentSP = userHero.getSp();
                        currentSP = currentSP - expendSp;
                        if (currentSP > 0){
                            userHero.setSp(currentSP);
                            userHeroDao.updateUserHero(userHero);
                        }
                    }
                }
                    break;
                default:
                    break;
            }
        }

        return reportDetailList;
    }

    private int calScript(String script, HeroModel hero){
        List<String> cmds = Arrays.asList(script.split(" "));
        String cmd = cmds.get(0);
        double result = 0;
        if (cmd.equalsIgnoreCase("POW")){
            String xV = cmds.get(1);
            float x = 1;
            if (NumberHelper.isRealNumber(xV)){
                x = Float.parseFloat(xV);
            }

            String yV = cmds.get(2);
            float y = 0;
            if (NumberHelper.isRealNumber(yV)){
                y = Float.parseFloat(yV);
            }else{
                if (yV.equalsIgnoreCase("level")){
                    y = hero.getLevel();
                }
            }
            result = Math.pow(x, y);
        }
        return (int) result;
    }

}
