package com.mud.controller;

import com.mud.context.UserContext;
import com.mud.dao.*;
import com.mud.mapper.*;
import com.mud.mapper.defines.DBMacro;
import com.mud.mapper.defines.DBStatus;
import com.mud.model.HeroModel;
import com.mud.model.ResponseModel;
import com.mud.model.SkillModel;
import com.mud.property.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by leeesven on 17/8/19.
 */

@RequestMapping("/api/hero")
@RestController
public class HeroController {

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private UserHeroDao userHeroDao;

    @Autowired
    private UserSkillDao userSkillDao;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private SequeneDao sequeneDao;

    /**
     * 获取所有英雄定义
     * @return  {@code ArrayList<Hero>}
     */
    @GetMapping(value = "/all")
    public ResponseModel getAllHero(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(heroDao.getAllHero());
        return responseModel;
    }

    /**
     * 获取指定英雄定义
     * @param heroId    英雄Id
     * @return  {@code Hero}
     */
    @GetMapping(value = "/{heroId}")
    public ResponseModel getHeroById(@PathVariable String heroId){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(heroDao.getHeroById(heroId));
        return responseModel;
    }

    /**
     * 获取登录用户全部英雄
     * @return  {@code ArrayList<HeroModel>}
     */
    @GetMapping(value = "/all/user_hero")
    public ResponseModel getAllUserHero(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllUserHero userId = " + userAuth.getUserId());

        ArrayList<UserHero> userHeroes = userHeroDao.getAllUserHero(userAuth.getUserId());
        if (userHeroes == null || userHeroes.size() == 0){
            //生成 hero 关联
            ArrayList<Hero> allHero = heroDao.getAllHero();
            for (Hero hero : allHero){
                UserHero userHero = new UserHero();

                int seqNo = sequeneDao.nextVal(DBMacro.SEQ_NAME_HERO);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String nowdate = sdf.format(new Date());
                String userHeroId = String.format("%s%04d", nowdate, seqNo);

                userHero.setUserHeroId(userHeroId);
                userHero.setUserId(userAuth.getUserId());
                userHero.setHeroId(hero.getHeroId());
                userHero.setStatus(DBStatus.USER_HERO_GET);
                userHeroDao.insertUserHero(userHero);
            }
        }
        userHeroes = userHeroDao.getAllUserHero(userAuth.getUserId());

        ArrayList<HeroModel> models = new ArrayList<>();
        for (UserHero userHero : userHeroes){
            Hero hero = heroDao.getHeroById(userHero.getHeroId());
            models.add(new HeroModel(hero, userHero));
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(models);

        return responseModel;
    }

    /**
     * 分解执行英雄
     * @param userHeroId 用户英雄Id
     * @return {@code SkillModel}
     */
    @PostMapping(value = "/convert_to_skill")
    public ResponseModel convertToSkill(@RequestParam String userHeroId){

        ResponseModel responseModel = new ResponseModel();

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("convertToSkill userId = " + userAuth.getUserId());

        UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
        if (userHero == null){
            responseModel.setCode(ResponseCode.HERO_NOT_GET);
        }else{
            Hero hero = heroDao.getHeroById(userHero.getHeroId());
            if (hero == null){
                responseModel.setCode(ResponseCode.HERO_NOT_EXIST);
            }else{

                // 获取战法定义
                Skill skill = skillDao.getSkillById(hero.getExSkillId());
                if (skill == null){
                    responseModel.setCode(ResponseCode.SKILL_NOT_EXIST);
                }else{
                    // 生成新的战法
                    int seqNo = sequeneDao.nextVal(DBMacro.SEQ_NAME_SKILL);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String nowdate = sdf.format(new Date());
                    String userSkillId = String.format("%s%04d", nowdate, seqNo);

                    UserSkill userSkill = new UserSkill();
                    userSkill.setUserSkillId(userSkillId);
                    userSkill.setUserId(userAuth.getUserId());
                    userSkill.setSkillId(skill.getSkillId());
                    userSkillDao.insertUserSkill(userSkill);

                    // 消耗掉英雄
                    userHero.setStatus(DBStatus.USER_HERO_CONVERTED_SKILL);
                    userHeroDao.updateUserHero(userHero);

                    // 获取用户战法
                    userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);

                    SkillModel skillModel = new SkillModel(skill, userSkill);
                    responseModel.setData(skillModel);
                }
            }
        }

        return responseModel;
    }

    /**
     * 分配战法到指定英雄
     * @param userHeroId    英雄Id
     * @param userSkillId   战法Id
     * @return  {@code ResponseModel}
     */
    @PostMapping(value = "/add_skill")
    public ResponseModel addSkill(@RequestParam String userHeroId, @RequestParam String userSkillId, @RequestParam Integer position){
        ResponseModel responseModel = new ResponseModel();

        //TODO
        //
        UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
        if (userHero == null){
            responseModel.setCode(ResponseCode.HERO_NOT_GET);
        }else{
            UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);
            if (userSkill == null){
                responseModel.setCode(ResponseCode.SKILL_NOT_GET);
            }else{

                //TODO 确定技能位置
                if (UserHero.skillPosition1 == position){
                    // 如果已经设置技能，将之前的技能恢复到初始状态
                    String exSkillId1 = userHero.getExSkillId1();
                    if (exSkillId1 != null){
                        UserSkill userSkill1 = userSkillDao.getUserSkillByUserSkillId(exSkillId1);
                        userSkill1.setUseHeroId(null);
                        //userSkillDao.update(userHero);
                    }
                    // 设置新技能
                    userHero.setExSkillId1(userSkill.getUserSkillId());
                }else if(UserHero.skillPosition2 == position){
                    String exSkillId2 = userHero.getExSkillId2();
                    if (exSkillId2 != null){
                        UserSkill userSkill2 = userSkillDao.getUserSkillByUserSkillId(exSkillId2);
                        userSkill2.setUseHeroId(null);
                        userHeroDao.updateUserHero(userHero);
                    }
                    // 设置新技能
                    userHero.setExSkillId2(userSkill.getUserSkillId());
                }

                userHeroDao.updateUserHero(userHero);

                // 更新
                userSkill.setUseHeroId(userHero.getUserHeroId());
            }
        }

        return responseModel;
    }

    /**
     * 获取指定英雄
     * @param heroId    英雄Id
     * @return  {@code ResponseModel}
     */
    @PostMapping(value = "/get_hero")
    public ResponseModel getHero(@RequestParam String heroId){
        ResponseModel responseModel = new ResponseModel();

        //TODO

        return responseModel;
    }
}
