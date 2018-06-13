package com.mud.service;

import com.mud.dao.BattleDao;
import com.mud.dao.HeroDao;
import com.mud.dao.SkillDao;
import com.mud.mapper.Battle;
import com.mud.mapper.Hero;
import com.mud.mapper.Skill;
import com.mud.model.HeroModel;
import com.mud.model.SkillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by leeesven on 17/9/15.
 */

@Service
public class NpcService {

    private Logger logger = Logger.getLogger(NpcService.class.getName());

    @Autowired
    BattleDao battleDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    SkillDao skillDao;

    /**
     * 获取指定战役的 NPC 列表
     * @param battleId
     * @return
     */
    public ArrayList<HeroModel> getHerosOfBattleId(String battleId){
        ArrayList<HeroModel> heros = new ArrayList<>();
        logger.info("获取战役NPC列表 start ");
        // 难度系数计算
        // 章节 * 10 + 战役
        logger.info("1.难度系数计算 ");
        Battle battle =  battleDao.getBattleById(battleId);
        int degree = 100;
        logger.info("难度系数计算 = " + degree);

        // 确定武将数量 (1-3)
        // ceil(难度系数 / 30) + random(0-100)<30 ? 1 : 0
        logger.info("2.确定武将数量 ");
        int heroCount = (int)Math.ceil(degree / 30.0f);
        heroCount += Math.random() < 0.3 ? 1 : 0;
        logger.info("武将数量 = " + heroCount);

        // 确定武将星级
        // 副将星级 = ceil(难度系数 / 100) + random(0-100)<30 ? 1 : 0
        // 中将星级 = ceil(难度系数 / 100) + random(0-100)<50 ? 1 : 0
        // 主将星级 = ceil(难度系数 / 100) + random(0-100)<80 ? 1 : 0
        logger.info("3.确定武将星级 ");
        int heroStar1 = 1;
        int heroStar2 = 1;
        int heroStar3 = 1;

        if (heroCount > 2){
            heroStar3 = (int)Math.ceil(degree / 100.0f);
            heroStar3 += Math.random() < 0.8 ? 1 : 0;
            logger.info("主将星级 = " + heroStar3);
        }
        if (heroCount > 1){
            heroStar2 = (int)Math.ceil(degree / 100.0f);
            heroStar2 += Math.random() < 0.5 ? 1 : 0;
            logger.info("中将星级 = " + heroStar2);
        }
        if (heroCount > 0){
            heroStar1 = (int)Math.ceil(degree / 100.0f);
            heroStar1 += Math.random() < 0.3 ? 1 : 0;
            logger.info("副将星级 = " + heroStar1);
        }

        // 随机一个指定星级的武将
        logger.info("4.随机一个指定星级的武将 ");
        String heroId1 = "";
        String heroId2 = "";
        String heroId3 = "";
        if (heroCount > 2){
            ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroStar3);
            int index = (int) Math.floor(Math.random() * starHeros.size());
            if (starHeros!= null && starHeros.size() > 0 &&
                    index >= 0 && index < starHeros.size()){
                heroId3 = starHeros.get(index).getHeroId();
            }
            logger.info("主将Id = " + heroId3 + "( index = " + index + ")");
        }
        if (heroCount > 1){
            ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroStar2);
            int index = (int) Math.floor(Math.random() * starHeros.size());
            if (starHeros!= null && starHeros.size() > 0 &&
                    index >= 0 && index < starHeros.size()){
                heroId2 = starHeros.get(index).getHeroId();
            }
            logger.info("中将Id = " + heroId2 + "( index = " + index + ")");
        }
        if (heroCount > 0){
            ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroStar1);
            int index = (int) Math.floor(Math.random() * starHeros.size());
            if (starHeros!= null && starHeros.size() > 0 &&
                    index >= 0 && index < starHeros.size()){
                heroId1 = starHeros.get(index).getHeroId();
            }
            logger.info("副将Id = " + heroId1 + "( index = " + index + ")");
        }


        // 确定武将等级
        // 副将等级 = floor(难度系数 / 50) * 5 + random(0-100)<30 ? 5 : 0
        // 中将等级 = floor(难度系数 / 50) * 5 + random(0-100)<50 ? 5 : 0
        // 主将等级 = floor(难度系数 / 50) * 5 + random(0-100)<80 ? 5 : 0
        logger.info("5.确定武将等级 ");
        int heroLevel1 = 1;
        int heroLevel2 = 1;
        int heroLevel3 = 1;
        if (heroCount > 2) {
            heroLevel3 = (int)Math.floor(degree / 50.0f) * 5;
            heroLevel3 += Math.random() < 0.8 ? 5 : 0;
            if (heroLevel3 == 0){
                heroLevel3 = 1;
            }
            logger.info("主将等级 = " + heroLevel3);
        }
        if (heroCount > 1) {
            heroLevel2 = (int)Math.floor(degree / 50.0f) * 5;
            heroLevel2 += Math.random() < 0.5 ? 5 : 0;
            if (heroLevel2 == 0){
                heroLevel2 = 1;
            }
            logger.info("中将等级 = " + heroLevel2);
        }
        if (heroCount > 0) {
            heroLevel1 = (int)Math.floor(degree / 50.0f) * 5;
            heroLevel1 += Math.random() < 0.3 ? 5 : 0;
            if (heroLevel1 == 0){
                heroLevel1 = 1;
            }
            logger.info("副将等级 = " + heroLevel1);
        }

        // 确定武将战法数量
        // 副将战法数量 = ceil(难度系数 / 200) + random(0-100)<30 ? 1 : 0
        // 中将战法数量 = ceil(难度系数 / 200) + random(0-100)<50 ? 1 : 0
        // 主将战法数量 = ceil(难度系数 / 200) + random(0-100)<80 ? 1 : 0
        logger.info("6.确定武将战法数量 ");
        int heroSkillCount1 = 0;
        int heroSkillCount2 = 0;
        int heroSkillCount3 = 0;
        if (heroCount > 2){
            heroSkillCount3 = (int)Math.ceil(degree / 200.0f);
            heroSkillCount3 += Math.random() < 0.8 ? 1 : 0;
            logger.info("主将战法数量 = " + heroSkillCount3);
        }
        if (heroCount > 1){
            heroSkillCount2 = (int)Math.ceil(degree / 200.0f);
            heroSkillCount2 += Math.random() < 0.5 ? 1 : 0;
            logger.info("中将战法数量 = " + heroSkillCount2);
        }
        if (heroCount > 0){
            heroSkillCount1 = (int)Math.ceil(degree / 200.0f);
            heroSkillCount1 += Math.random() < 0.3 ? 1 : 0;
            logger.info("副将战法数量 = " + heroSkillCount1);
        }

        // 确定武将战法星级
        // 副将战法星级 = ceil(难度系数 / 100) + random(0-100)<30 ? 1 : 0
        // 中将战法星级 = ceil(难度系数 / 100) + random(0-100)<50 ? 1 : 0
        // 主将战法星级 = ceil(难度系数 / 100) + random(0-100)<80 ? 1 : 0
        logger.info("7.确定武将战法星级 ");
        int heroSkillStar3 = 1;
        int heroSkillStar2 = 1;
        int heroSkillStar1 = 1;
        if (heroCount > 2){
            heroSkillStar3 = (int)Math.ceil(degree / 100.0f);
            heroSkillStar3 += Math.random() < 0.8 ? 1 : 0;
            logger.info("主将战法星级 = " + heroSkillStar3);
        }
        if (heroCount > 1){
            heroSkillStar2 = (int)Math.ceil(degree / 100.0f);
            heroSkillStar2 += Math.random() < 0.5 ? 1 : 0;
            logger.info("中将战法星级 = " + heroSkillStar2);
        }
        if (heroCount > 0){
            heroSkillStar1 = (int)Math.ceil(degree / 100.0f);
            heroSkillStar1 += Math.random() < 0.3 ? 1 : 0;
            logger.info("副将战法星级 = " + heroSkillStar1);
        }

        // 随机武将战法
        logger.info("8.随机武将战法 ");
        String heroSkillId11 = "";
        String heroSkillId12 = "";
        String heroSkillId21 = "";
        String heroSkillId22 = "";
        String heroSkillId31 = "";
        String heroSkillId32 = "";
        if (heroCount > 2){
            if (heroSkillCount3 > 1){
                ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroSkillStar3);
                int index = (int) Math.floor(Math.random() * starHeros.size());
                if (starHeros!= null && starHeros.size() > 0 &&
                        index >= 0 && index < starHeros.size()){
                    if(starHeros.get(index).getExSkillId() != null &&
                            starHeros.get(index).getExSkillId().length() > 0){
                        heroSkillId32 = starHeros.get(index).getExSkillId();
                    }else{
                        heroSkillId32 = starHeros.get(index).getSkillId();
                    }
                }
                logger.info("主将战法2 = " + heroSkillId32);
            }
            if (heroSkillCount3 > 0){
                ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroSkillStar3);
                int index = (int) Math.floor(Math.random() * starHeros.size());
                if (starHeros!= null && starHeros.size() > 0 &&
                        index >= 0 && index < starHeros.size()){
                    heroSkillId31 = starHeros.get(index).getSkillId();
                }
                logger.info("主将战法1 = " + heroSkillId31);
            }
        }
        if (heroCount > 1){
            if (heroSkillCount2 > 1){
                ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroSkillStar2);
                int index = (int) Math.floor(Math.random() * starHeros.size());
                if (starHeros!= null && starHeros.size() > 0 &&
                        index >= 0 && index < starHeros.size()){
                    if(starHeros.get(index).getExSkillId() != null &&
                            starHeros.get(index).getExSkillId().length() > 0){
                        heroSkillId22 = starHeros.get(index).getExSkillId();
                    }else{
                        heroSkillId22 = starHeros.get(index).getSkillId();
                    }
                }
                logger.info("中将战法2 = " + heroSkillId22);
            }
            if (heroSkillCount2 > 0){
                ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroSkillStar2);
                int index = (int) Math.floor(Math.random() * starHeros.size());
                if (starHeros!= null && starHeros.size() > 0 &&
                        index >= 0 && index < starHeros.size()){
                    heroSkillId21 = starHeros.get(index).getSkillId();
                }
                logger.info("中将战法1 = " + heroSkillId21);
            }
        }
        if (heroCount > 0){
            if (heroSkillCount1 > 1){
                ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroSkillStar1);
                int index = (int) Math.floor(Math.random() * starHeros.size());
                if (starHeros!= null && starHeros.size() > 0 &&
                        index >= 0 && index < starHeros.size()){
                    if(starHeros.get(index).getExSkillId() != null &&
                            starHeros.get(index).getExSkillId().length() > 0){
                        heroSkillId12 = starHeros.get(index).getExSkillId();
                    }else{
                        heroSkillId12 = starHeros.get(index).getSkillId();
                    }
                }
                logger.info("副将战法2 = " + heroSkillId12);
            }
            if (heroSkillCount1 > 0){
                ArrayList<Hero> starHeros = heroDao.getAllHeroOfStar(heroSkillStar1);
                int index = (int) Math.floor(Math.random() * starHeros.size());
                if (starHeros!= null && starHeros.size() > 0 &&
                        index >= 0 && index < starHeros.size()){
                    heroSkillId11 = starHeros.get(index).getSkillId();
                }
                logger.info("副将战法1 = " + heroSkillId11);
            }
        }

        // 确定武将战法等级
        // 副将战法等级 = ceil(难度系数 / 100) + random(0-100)<30 ? 1 : 0
        // 中将战法等级 = ceil(难度系数 / 100) + random(0-100)<50 ? 1 : 0
        // 主将战法等级 = ceil(难度系数 / 100) + random(0-100)<80 ? 1 : 0
        logger.info("9.确定武将战法等级 ");
        int heroSkillLevel3 = 1;
        int heroSkillLevel2 = 1;
        int heroSkillLevel1 = 1;
        if (heroCount > 2){
            heroSkillLevel3 = (int)Math.ceil(degree / 100.0f);
            heroSkillLevel3 += Math.random() < 0.8 ? 1 : 0;
            logger.info("主将战法等级 = " + heroSkillLevel3);
        }
        if (heroCount > 1){
            heroSkillLevel2 = (int)Math.ceil(degree / 100.0f);
            heroSkillLevel2 += Math.random() < 0.5 ? 1 : 0;
            logger.info("中将战法等级 = " + heroSkillLevel2);
        }
        if (heroCount > 0){
            heroSkillLevel1 = (int)Math.ceil(degree / 100.0f);
            heroSkillLevel1 += Math.random() < 0.3 ? 1 : 0;
            logger.info("副将战法等级 = " + heroSkillLevel1);
        }

        // 组装武将
        logger.info("10.组装武将 ");
        if (heroCount > 2){
            Hero hero = heroDao.getHeroById(heroId3);
            HeroModel model = new HeroModel(hero);

            // 设置英雄等级
            model.setLevel(heroLevel3);

            // 设置英雄技能
            {
                Skill skill = skillDao.getSkillById(hero.getSkillId());
                SkillModel skillModel = new SkillModel(skill);
                // 设置英雄技能等级
                skillModel.setLevel(heroSkillLevel3);
                model.setSkill(skillModel);
            }
            if (heroSkillId31 != null && heroSkillId31.length() > 0){
                model.setExSkillId1(heroSkillId31);
                Skill exSkill1 = skillDao.getSkillById(heroSkillId31);
                SkillModel exSkillModel1 = new SkillModel(exSkill1);
                // 设置英雄技能等级
                exSkillModel1.setLevel(heroSkillLevel3);
                model.setExSkill1(exSkillModel1);
            }
            if (heroSkillId32 != null && heroSkillId32.length() > 0){
                model.setExSkillId2(heroSkillId32);
                Skill exSkill2 = skillDao.getSkillById(heroSkillId32);
                SkillModel exSkillModel2 = new SkillModel(exSkill2);
                // 设置英雄技能等级
                exSkillModel2.setLevel(heroSkillLevel3);
                model.setExSkill2(exSkillModel2);
            }

            // 阵位
            model.setPosCol(3);

            heros.add(model);
        }
        if (heroCount > 1){
            Hero hero = heroDao.getHeroById(heroId2);
            HeroModel model = new HeroModel(hero);

            // 设置英雄等级
            model.setLevel(heroLevel2);

            // 设置英雄技能
            {
                Skill skill = skillDao.getSkillById(hero.getSkillId());
                SkillModel skillModel = new SkillModel(skill);
                // 设置英雄技能等级
                skillModel.setLevel(heroSkillLevel2);
                model.setSkill(skillModel);
            }
            if (heroSkillId21 != null && heroSkillId21.length() > 0){
                model.setExSkillId1(heroSkillId21);
                Skill exSkill1 = skillDao.getSkillById(heroSkillId21);
                SkillModel exSkillModel1 = new SkillModel(exSkill1);
                // 设置英雄技能等级
                exSkillModel1.setLevel(heroSkillLevel2);
                model.setExSkill1(exSkillModel1);
            }
            if (heroSkillId22 != null && heroSkillId22.length() > 0){
                model.setExSkillId2(heroSkillId22);
                Skill exSkill2 = skillDao.getSkillById(heroSkillId22);
                SkillModel exSkillModel2 = new SkillModel(exSkill2);
                // 设置英雄技能等级
                exSkillModel2.setLevel(heroSkillLevel2);
                model.setExSkill2(exSkillModel2);
            }

            // 阵位
            model.setPosCol(2);

            heros.add(model);
        }
        if (heroCount > 0){
            Hero hero = heroDao.getHeroById(heroId1);
            HeroModel model = new HeroModel(hero);

            // 设置英雄等级
            model.setLevel(heroLevel1);

            // 设置英雄技能
            {
                Skill skill = skillDao.getSkillById(hero.getSkillId());
                SkillModel skillModel = new SkillModel(skill);
                // 设置英雄技能等级
                skillModel.setLevel(heroSkillLevel1);
                model.setSkill(skillModel);
            }
            if (heroSkillId11 != null && heroSkillId11.length() > 0){
                model.setExSkillId1(heroSkillId11);
                Skill exSkill1 = skillDao.getSkillById(heroSkillId11);
                SkillModel exSkillModel1 = new SkillModel(exSkill1);
                // 设置英雄技能等级
                exSkillModel1.setLevel(heroSkillLevel1);
                model.setExSkill1(exSkillModel1);
            }
            if (heroSkillId12 != null && heroSkillId12.length() > 0){
                model.setExSkillId2(heroSkillId12);
                Skill exSkill2 = skillDao.getSkillById(heroSkillId12);
                SkillModel exSkillModel2 = new SkillModel(exSkill2);
                // 设置英雄技能等级
                exSkillModel2.setLevel(heroSkillLevel1);
                model.setExSkill2(exSkillModel2);
            }

            // 阵位
            model.setPosCol(1);

            heros.add(model);
        }

        logger.info("获取战役NPC列表 end ");
        return heros;
    }

}
