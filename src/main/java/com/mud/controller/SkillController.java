package com.mud.controller;

import com.mud.context.UserContext;
import com.mud.dao.*;
import com.mud.mapper.*;
import com.mud.model.HeroModel;
import com.mud.model.ResponseModel;
import com.mud.model.SkillModel;
import com.mud.property.ResponseCode;
import com.mud.service.CalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeesven on 17/8/20.
 */

@RequestMapping("/api/skill")
@RestController
public class SkillController {

    @Autowired
    private SkillDao skillDao;

    @Autowired
    UserSkillDao userSkillDao;

    @Autowired
    UserHeroDao userHeroDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    SkillExtendDao skillExtendDao;

    @Autowired
    CalService calService;

    @Autowired
    UserExtendDao userExtendDao;

    /**
     * 获取战法列表
     * @return  {@code ArrayList<Skill>}
     */
    @GetMapping(value = "/all")
    public ResponseModel getAllSkill(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(skillDao.getAllSkill());
        return responseModel;
    }

    /**
     * 获取指定战法信息
     * @param skillId   战法Id
     * @return {@code Skill}
     */
    @GetMapping(value = "/{skillId}")
    public ResponseModel getSkillById(@PathVariable String skillId){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(skillDao.getSkillById(skillId));
        return responseModel;
    }

    /**
     * 获取拥有的战法
     * @return
     */
    @GetMapping(value = "/user_skills")
    public ResponseModel getUserSkills(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("getUserSkills userId = " + userId);

        ResponseModel responseModel = new ResponseModel();

        List<SkillModel> skillModels = new ArrayList<>();
        List<UserSkill> userSkills = userSkillDao.getAllUserSkills(userId);
        for (UserSkill userSkill : userSkills) {
            Skill skill = skillDao.getSkillById(userSkill.getSkillId());
            SkillModel model = new SkillModel(skill, userSkill);
            createFullSkillModel(model);
            skillModels.add(model);
        }
        responseModel.setData(skillModels);

        return responseModel;
    }

    /**
     * 获取需要升级的战法
     * @return
     */
    @GetMapping(value = "/user_up_skills")
    public ResponseModel getUserUpSkills(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("getUserSkills userId = " + userId);

        ResponseModel responseModel = new ResponseModel();

        List<SkillModel> skillModels = new ArrayList<>();
        List<UserSkill> userSkills = userSkillDao.getAllUserSkills(userId);
        for (UserSkill userSkill : userSkills) {
            Skill skill = skillDao.getSkillById(userSkill.getSkillId());
            SkillModel model = new SkillModel(skill, userSkill);
            SkillExtend skillExtend = skillExtendDao.getExtendOfSkillLevelUp(skill.getSkillId());
            int upExp = 0;
            if (skillExtend != null){
                upExp = calService.calValue( skillExtend.getVal(), model);
            }
            model.setCanUpExp(upExp);
            skillModels.add(model);
        }
        responseModel.setData(skillModels);

        return responseModel;
    }

    /**
     * 获取待研究的战法
     * @return
     */
    @GetMapping(value = "/user_into_skills")
    public ResponseModel getUserIntoSkills(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("getUserIntoSkills userId = " + userId);

        ResponseModel responseModel = new ResponseModel();

        List<SkillModel> skillModels = new ArrayList<>();
        List<UserSkill> userSkills = userSkillDao.getAllIntoUserSkills(userId);
        for (UserSkill userSkill : userSkills) {
            Skill skill = skillDao.getSkillById(userSkill.getSkillId());
            SkillModel model = new SkillModel(skill, userSkill);
            createFullSkillModel(model);
            skillModels.add(model);
        }
        responseModel.setData(skillModels);

        return responseModel;
    }

    /**
     * 战法升级
     * @param userSkillId   战法Id
     * @return  {@code ResponseModel}
     */
    @PostMapping(value = "/level_up")
    public ResponseModel levelUp(@RequestParam String userSkillId){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("levelUp userId = " + userId);

        ResponseModel responseModel = new ResponseModel();

        UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);
        if (userSkill == null){
            responseModel.setCode(ResponseCode.SKILL_NOT_GET);
        }else{
            Skill skill = skillDao.getSkillById(userSkill.getSkillId());
            if (skill == null){
                responseModel.setCode(ResponseCode.SKILL_NOT_EXIST);
            }else {
                SkillExtend skillExtend = skillExtendDao.getExtendOfSkillLevelUp(userSkill.getSkillId());
                if (skillExtend == null){
                    responseModel.setCode(ResponseCode.SKILL_EXTEND_PROP_ERR);
                }else{
                    String script = skillExtend.getVal();
                    int upExp = calService.calValue(script, new SkillModel(skill, userSkill));

                    UserExtend userExtend = userExtendDao.getExtendOfUserSkillPoint(userId);
                    int skillPoint = 0;
                    if (userExtend != null){
                        skillPoint = Integer.parseInt(userExtend.getVal());
                    }

                    if (skillPoint >= upExp){
                        skillPoint -= upExp;

                        // 升一级
                        userSkill.setLevel(userSkill.getLevel() + 1);
                        userSkillDao.updateUserSkill(userSkill);

                        // 扣除经验
                        userExtend.setVal(skillPoint+"");
                        userExtendDao.updateExtendOfUser(userExtend);
                    }

                    // 组织回复
                    SkillModel skillModel = new SkillModel(skill, userSkill);
                    responseModel.setData(skillModel);
                }
            }
        }

        return responseModel;
    }

    private void createFullSkillModel(SkillModel skillModel){
        if (skillModel.getUseHeroId() != null){
            UserHero userHero = userHeroDao.getUserHeroByUserHeroId(skillModel.getUseHeroId());
            Hero hero = heroDao.getHeroById(userHero.getHeroId());
            skillModel.setUseHeroModel(new HeroModel(hero, userHero));
        }
    }
}
