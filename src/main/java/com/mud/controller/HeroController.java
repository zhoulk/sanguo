package com.mud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mud.context.UserContext;
import com.mud.dao.*;
import com.mud.mapper.*;
import com.mud.model.HeroModel;
import com.mud.model.ResponseModel;
import com.mud.model.SkillModel;
import com.mud.model.UserModel;
import com.mud.model.define.SGMacro;
import com.mud.property.ResponseCode;
import com.mud.service.CalService;
import com.mud.service.HeroService;
import com.mud.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private SequenceService sequenceService;

    @Autowired
    HeroService heroService;

    @Autowired
    private UserSelectHeroDao userSelectHeroDao;

    @Autowired
    SkillExtendDao skillExtendDao;

    @Autowired
    CalService calService;

    @Autowired
    HeroExtendDao heroExtendDao;

    @Autowired
    UserExtendDao userExtendDao;

    @Autowired
    UserBagDao userBagDao;

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
     * 随机一个武将
     * @param level 随机等级
     */
    @GetMapping(value = "/random/{level}")
    public ResponseModel randomAHero(@PathVariable int level){
        ResponseModel responseModel = new ResponseModel();
        HeroModel randomHero = heroService.randomAHero(level);
        if(randomHero != null){
            // 给用户增加一个武将
            UserAuth userAuth = UserContext.getCurrentUserAuth();
            String userId = userAuth.getUserId();
            boolean res = heroService.addUserHero(userId, randomHero.getHeroId());
            if(!res){
                responseModel.setCode(ResponseCode.HERO_RANDOM_FAIL);
            }else{
                responseModel.setData(randomHero);
            }
        }
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
//        if (userHeroes == null || userHeroes.size() == 0){
//            //生成 hero 关联
//            ArrayList<Hero> allHero = heroDao.getAllHeroOfCanSelect();
//            for (Hero hero : allHero){
//                UserHero userHero = new UserHero();
//
//
//                String userHeroId = sequenceService.sequenceOfHero();
//
//                userHero.setUserHeroId(userHeroId);
//                userHero.setUserId(userAuth.getUserId());
//                userHero.setHeroId(hero.getHeroId());
//                userHero.setLevel(1);
//                userHero.setSp(100);
//                userHero.setStatus(SGMacro.SG_HERO_USER_HERO_GET);
//                userHeroDao.insertUserHero(userHero);
//            }
//        }
//        userHeroes = userHeroDao.getAllUserHero(userAuth.getUserId());

        ArrayList<HeroModel> models = new ArrayList<>();
        if (userHeroes != null){
            for (UserHero userHero : userHeroes){
                Hero hero = heroDao.getHeroById(userHero.getHeroId());
                HeroModel heroModel = new HeroModel(hero, userHero);
                createFullHeroModel(heroModel);

                UserSelectHero userSelectHero = userSelectHeroDao.getUserSelectHeroByUserHeroId(userHero.getUserHeroId());
                if(userSelectHero != null){
                    heroModel.setPosCol(userSelectHero.getPosCol());
                    heroModel.setPosRow(userSelectHero.getPosRow());
                }

                models.add(heroModel);
            }
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(models);

        return responseModel;
    }

    /**
     * 获取对应战法研究英雄
     * @return  {@code ArrayList<HeroModel>}
     */
    @GetMapping(value = "/all/can_into_skill")
    public ResponseModel getAllCanIntoHeros(@RequestParam String userSkillId){
        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllCanIntoHeros userId = " + userAuth.getUserId());

        ArrayList<UserHero> userHeroes = userHeroDao.getAllUserHero(userAuth.getUserId());
        UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);

        ArrayList<HeroModel> models = new ArrayList<>();
        if (userHeroes != null){
            for (UserHero userHero : userHeroes) {
                Hero hero = heroDao.getHeroById(userHero.getHeroId());
                HeroModel heroModel = new HeroModel(hero, userHero);
                int canIntoNum = calService.calIntoPercent(userSkill.getSkillId(), heroModel);
                heroModel.setCanIntoNum(canIntoNum);
                models.add(heroModel);
            }
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(models);
        return responseModel;
    }

    /**
     * 获取英雄战法经验
     * @return  {@code ArrayList<HeroModel>}
     */
    @GetMapping(value = "/all/can_skill_point")
    public ResponseModel getAllCanSkillPointHeros(){
        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllCanSkillPointHeros userId = " + userAuth.getUserId());

        ArrayList<UserHero> userHeroes = userHeroDao.getAllUserHero(userAuth.getUserId());

        ArrayList<HeroModel> models = new ArrayList<>();
        if (userHeroes != null){
            for (UserHero userHero : userHeroes) {
                Hero hero = heroDao.getHeroById(userHero.getHeroId());
                HeroModel heroModel = new HeroModel(hero, userHero);
                int skillPoint = calService.calSkillPoint(heroModel);
                heroModel.setCanSkillPoint(skillPoint);
                models.add(heroModel);
            }
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(models);
        return responseModel;
    }

    /**
     * 分解武将 到 战法
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
                    String userSkillId = sequenceService.sequenceOfSkill();

                    UserSkill userSkill = new UserSkill();
                    userSkill.setUserSkillId(userSkillId);
                    userSkill.setUserId(userAuth.getUserId());
                    userSkill.setSkillId(skill.getSkillId());
                    userSkillDao.insertUserSkill(userSkill);

                    // 增加研究进度
                    int upPercent = calService.calIntoPercent(userSkill.getSkillId(), new HeroModel(hero, userHero));
                    userSkill.setPercent(userSkill.getPercent() + upPercent);

                    if (userSkill.getPercent() >= 100){
                        userSkill.setLevel(userSkill.getLevel() + 1);
                    }
                    userSkillDao.updateUserSkill(userSkill);

                    // 消耗掉英雄
                    userHero.setStatus(SGMacro.SG_HERO_USER_HERO_CONVERTED_SKILL);
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
     * 分解武将 到 战法经验
     * @param userHeroId
     * @return
     */
    @PostMapping(value = "/convert_to_skill_point")
    public ResponseModel convertToSkillPoint(@RequestParam String userHeroId){
        ResponseModel responseModel = new ResponseModel();

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("convertToSkillPoint userId = " + userId);

        UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
        if (userHero == null){
            responseModel.setCode(ResponseCode.HERO_NOT_GET);
        }else {
            Hero hero = heroDao.getHeroById(userHero.getHeroId());
            if (hero == null) {
                responseModel.setCode(ResponseCode.HERO_NOT_EXIST);
            } else {
                HeroExtend heroExtend = heroExtendDao.getHeroExtendConvertSkillPoint(hero.getHeroId());
                int upPoint = Integer.parseInt(heroExtend.getVal());
                int currentPoint;
//                UserExtend userExtend = userExtendDao.getExtendOfUserSkillPoint(userId);
//                if (userExtend == null){
//                    currentPoint = upPoint;
//                    userExtend = new UserExtend();
//                    userExtend.setUserId(userId);
//                    userExtend.setProp("skillPoint");
//                    userExtend.setVal(currentPoint + "");
//                    userExtendDao.insertExtendOfUser(userExtend);
//                }else {
//                    currentPoint = Integer.parseInt(userExtend.getVal());
//                    currentPoint += upPoint;
//                    userExtend.setVal(currentPoint + "");
//                    userExtendDao.updateExtendOfUser(userExtend);
//                }

                // 消耗武将
                userHero.setStatus(SGMacro.SG_HERO_USER_HERO_CONVERTED_SKill_POINT);
                userHeroDao.updateUserHero(userHero);

                // 构造用户结构
                UserModel userModel = new UserModel();
                //userModel.setSkillPoint(currentPoint);
                responseModel.setData(userModel);
            }
        }

        return responseModel;
    }

    /**
     * 武将合成战法
     * @param userHeroIds
     * @param userSkillId
     * @return
     */
    @PostMapping(value = "/convert_into_skill")
    public ResponseModel convertIntoSkill(@RequestParam String userHeroIds, @RequestParam String userSkillId){
        ResponseModel responseModel = new ResponseModel();

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("convertIntoSkill userId = " + userAuth.getUserId());

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> userHeroIdList = null;
        try {
            userHeroIdList = objectMapper.readValue(userHeroIds, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String userHeroId : userHeroIdList) {
            ResponseCode code = convertOneIntoSkill(userHeroId, userSkillId);
            if (code.getValue() != ResponseCode.SYS_SUCCESS.getValue()){
                responseModel.setCode(code);
                break;
            }
        }

        // 获取用户战法
        UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);
        Skill skill = skillDao.getSkillById(userSkill.getSkillId());
        SkillModel skillModel = new SkillModel(skill, userSkill);
        responseModel.setData(skillModel);

        return responseModel;
    }

    private ResponseCode convertOneIntoSkill(String userHeroId, String userSkillId){
        ResponseCode code = ResponseCode.SYS_SUCCESS;
        UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
        if (userHero == null){
            code = ResponseCode.HERO_NOT_GET;
        }else {
            Hero hero = heroDao.getHeroById(userHero.getHeroId());
            if (hero == null){
                code = ResponseCode.HERO_NOT_EXIST;
            }else {
                UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);
                if (userSkill == null){
                    code = ResponseCode.SKILL_NOT_GET;
                }else {
                    Skill skill = skillDao.getSkillById(userSkill.getSkillId());
                    if (skill == null){
                        code = ResponseCode.SKILL_NOT_EXIST;
                    }else{
                        // 增加研究进度
                        List<SkillExtend> skillExtends = skillExtendDao.getExtendOfSkillIntoCmd(skill.getSkillId());
                        int upPercent = 0;
                        for (SkillExtend extend : skillExtends) {
                            upPercent = calService.calValue(extend.getVal(), new HeroModel(hero, userHero));
                            if (upPercent > 0){
                                break;
                            }
                        }
                        userSkill.setPercent(userSkill.getPercent() + upPercent);
                        // 战法研究 不能去提升战法等级
                        if (userSkill.getPercent() >= 100 && userSkill.getLevel() < 1){
                            userSkill.setLevel(userSkill.getLevel() + 1);
                        }
                        userSkillDao.updateUserSkill(userSkill);

                        // 消耗掉英雄
                        userHero.setStatus(SGMacro.SG_HERO_USER_HERO_CONVERTED_SKill_INTO);
                        userHeroDao.updateUserHero(userHero);
                    }
                }
            }
        }
        return code;
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

        UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
        if (userHero == null){
            responseModel.setCode(ResponseCode.HERO_NOT_GET);
        }else{
            UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(userSkillId);
            if (userSkill == null){
                responseModel.setCode(ResponseCode.SKILL_NOT_GET);
            }else{

                // 重置目标英雄技能状态
                if (UserHero.skillPosition1 == position){
                    // 如果已经设置技能，将之前的技能恢复到初始状态
                    String exSkillId1 = userHero.getExSkillId1();
                    if (exSkillId1 != null){
                        UserSkill userSkill1 = userSkillDao.getUserSkillByUserSkillId(exSkillId1);
                        userSkill1.setUseHeroId(null);
                        userSkillDao.updateUserSkill(userSkill1);
                    }
                }else if(UserHero.skillPosition2 == position){
                    String exSkillId2 = userHero.getExSkillId2();
                    if (exSkillId2 != null){
                        UserSkill userSkill2 = userSkillDao.getUserSkillByUserSkillId(exSkillId2);
                        userSkill2.setUseHeroId(null);
                        userSkillDao.updateUserSkill(userSkill2);
                    }
                }
                // 重置目标技能相关英雄状态
                UserHero userHero1 = userHeroDao.getUserHeroByUserHeroId(userSkill.getUseHeroId());
                if (userHero1.getExSkillId1() != null && userHero1.getExSkillId1().equalsIgnoreCase(userSkill.getUserSkillId())){
                    userHero1.setExSkillId1(null);
                }
                if (userHero1.getExSkillId2() != null && userHero1.getExSkillId2().equalsIgnoreCase(userSkill.getUserSkillId())){
                    userHero1.setExSkillId2(null);
                }
                userHeroDao.updateUserHero(userHero1);

                // 更新目标英雄状态
                UserHero userHero2 = userHeroDao.getUserHeroByUserHeroId(userHeroId);
                if (UserHero.skillPosition1 == position){
                    // 设置新技能
                    userHero2.setExSkillId1(userSkill.getUserSkillId());
                }else if(UserHero.skillPosition2 == position){
                    // 设置新技能
                    userHero2.setExSkillId2(userSkill.getUserSkillId());
                }
                userHeroDao.updateUserHero(userHero2);
                // 更新目标技能状态
                UserSkill userSkill1 = userSkillDao.getUserSkillByUserSkillId(userSkillId);
                userSkill1.setUseHeroId(userHero.getUserHeroId());
                userSkillDao.updateUserSkill(userSkill1);

                Hero hero = heroDao.getHeroById(userHero.getHeroId());
                HeroModel heroModel = new HeroModel(hero, userHero2);
                createFullHeroModel(heroModel);
                responseModel.setData(heroModel);
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

    /**
     * 上阵指定英雄
     * @param userHeroId 用户英雄Id
     * @param posRow 阵位
     * @return
     */
    @PostMapping(value = "/select_hero")
    public ResponseModel selectHero(@RequestParam String userHeroId,
                                    @RequestParam Integer posRow,
                                    @RequestParam Integer posCol){
        ResponseModel responseModel = new ResponseModel();

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("selectHero userId = " + userId);

        // 检测英雄是否已经上阵
        UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
        if (userHero.getStatus() == SGMacro.SG_HERO_USER_HERO_SELECTED){
            responseModel.setCode(ResponseCode.HERO_SELECTED);
        }else{
            // 检测阵位是否开启
            if(posCol < 0 || posCol > 4){
                responseModel.setCode(ResponseCode.HERO_INDEX_ERR);
            }else{
                // 检测阵位是否为空
                UserSelectHero userSelectHero;
                // 上阵之前被下阵的英雄会报主键冲突
                userSelectHero = userSelectHeroDao.getUserSelectHeroByUserHeroId(userHeroId);

                if (userSelectHero == null){
                    // 上阵
                    userSelectHero = new UserSelectHero();
                    userSelectHero.setUserHeroId(userHeroId);
                    userSelectHero.setUserId(userId);
                    userSelectHero.setHeroId(userHero.getHeroId());
                    userSelectHero.setPosRow(posRow);
                    userSelectHero.setPosCol(posCol);
                    userSelectHeroDao.insertUserHero(userSelectHero);
                }else{
                    // 更新被替换用户武将状态
                    UserHero repeatUserHero = new UserHero();
                    repeatUserHero.setUserHeroId(userSelectHero.getUserHeroId());
                    repeatUserHero.setStatus(SGMacro.SG_HERO_USER_HERO_GET);
                    userHeroDao.updateUserHero(repeatUserHero);
                    // 更新
                    userSelectHero.setUserHeroId(userHeroId);
                    userSelectHero.setHeroId(userHero.getHeroId());
                    userSelectHero.setUserId(userId);
                    userSelectHero.setPosRow(posRow);
                    userSelectHero.setPosCol(posCol);
                    userSelectHeroDao.updateUserSelectHero(userSelectHero);
                }
                // 更新用户武将状态
                userHero.setStatus(SGMacro.SG_HERO_USER_HERO_SELECTED);
                userHeroDao.updateUserHero(userHero);


                responseModel.setCode(ResponseCode.HERO_SELECT_SUCCESS);
            }
        }

        return responseModel;
    }

    /**
     * 下阵用户英雄
     * @param userHeroId
     * @return
     */
    @PostMapping(value = "/deselect_hero")
    public ResponseModel deselectHero(@RequestParam(required = false) String userHeroId,
                                      @RequestParam(required = false) Integer posRow,
                                      @RequestParam(required = false) Integer posCol){
        ResponseModel responseModel = new ResponseModel();

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("deselectHero userId = " + userId);

        if (userHeroId != null){
            // 检测英雄是否已经上阵
            UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userHeroId);
            if (userHero.getStatus() != SGMacro.SG_HERO_USER_HERO_SELECTED){
                responseModel.setCode(ResponseCode.HERO_DESELECTED);
            }else{
                // 更新被替换用户武将状态
                userHero.setStatus(SGMacro.SG_HERO_USER_HERO_GET);
                userHeroDao.updateUserHero(userHero);
                //下阵
                UserSelectHero userSelectHero = userSelectHeroDao.getUserSelectHeroByUserHeroId(userHeroId);
                userSelectHero.setPosRow(0);
                userSelectHero.setPosCol(0);
                userSelectHeroDao.updateUserSelectHero(userSelectHero);

                responseModel.setCode(ResponseCode.HERO_DESELECT_SUCCESS);
            }
        }else{
            // 检测阵位是否开启
            if(posCol < 0 || posCol > 4){
                responseModel.setCode(ResponseCode.HERO_INDEX_ERR);
            }else{
                UserSelectHero userSelectHero = userSelectHeroDao.getUserSelectHeroByIndex(userId, posRow, posCol);
                if (userSelectHero != null){
                    // 更新被替换用户武将状态
                    UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userSelectHero.getUserHeroId());
                    userHero.setStatus(SGMacro.SG_HERO_USER_HERO_GET);
                    userHeroDao.updateUserHero(userHero);
                    //下阵
                    userSelectHero.setPosRow(0);
                    userSelectHero.setPosCol(0);
                    userSelectHeroDao.updateUserSelectHero(userSelectHero);

                    responseModel.setCode(ResponseCode.HERO_DESELECT_SUCCESS);
                }
            }
        }

        return responseModel;
    }

    /**
     * 获取上阵英雄
     * @return
     */
    @GetMapping(value = "/selected")
    public ResponseModel getSelectedHero(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("getSelectedHero userId = " + userId);

        ResponseModel responseModel = new ResponseModel();
        ArrayList<UserSelectHero> userSelectHeroes = userSelectHeroDao.getUserSelectedHeros(userId);
        ArrayList<HeroModel> heroModels = new ArrayList<>();
        for (UserSelectHero userSelectHero : userSelectHeroes) {
            UserHero userHero = userHeroDao.getUserHeroByUserHeroId(userSelectHero.getUserHeroId());
            Hero hero = heroDao.getHeroById(userSelectHero.getHeroId());
            HeroModel heroModel = new HeroModel(hero, userHero, userSelectHero);
            heroModels.add(heroModel);
        }
        responseModel.setData(heroModels);
        return responseModel;
    }

    /**
     * 合成武将
     * @param productId
     * @return
     */
    @PostMapping(value = "/into_hero")
    public ResponseModel intoHero(@RequestParam String productId){
        UserAuth userAuth = UserContext.getCurrentUserAuth();
        String userId = userAuth.getUserId();
        System.out.println("intoHero userId = " + userId);

        ResponseModel responseModel = new ResponseModel();
        UserBag userBag = userBagDao.getUserBagOf(userId, productId);
        if (userBag == null){
            responseModel.setCode(ResponseCode.HERO_PIECE_NOT_EXIST);
        }else{
            HeroExtend heroExtend = heroExtendDao.getHeroExtendHeroPiece(productId);
            if (heroExtend == null){
                responseModel.setCode(ResponseCode.HERO_PIECE_NOT_TRUE);
            }else {
                HeroExtend intoHeroExtend = heroExtendDao.getHeroExtendIntoNum(heroExtend.getHeroId());
                if (intoHeroExtend == null){
                    responseModel.setCode(ResponseCode.HERO_PIECE_CANNOT_INTO);
                }else{
                    int havNum = userBag.getNum();
                    int intoNum = Integer.MAX_VALUE;
                    if (intoHeroExtend.getVal() != null){
                        intoNum = Integer.parseInt(intoHeroExtend.getVal());
                    }
                    if (havNum >= intoNum){

                        UserHero userHero = new UserHero();
                        userHero.setUserId(userId);
                        userHero.setHeroId(heroExtend.getHeroId());
                        userHero.setUserHeroId(sequenceService.sequenceOfHero());
                        userHero.setStatus(SGMacro.SG_HERO_USER_HERO_GET);
                        userHeroDao.insertUserHero(userHero);

                        userBag.setNum(havNum - intoNum);
                        userBagDao.updateUserBag(userBag);

                        Hero hero = heroDao.getHeroById(heroExtend.getHeroId());
                        HeroModel heroModel = new HeroModel(hero);
                        responseModel.setData(heroModel);
                    }else{
                        responseModel.setCode(ResponseCode.HERO_PIECE_NOT_ENOUGH);
                    }
                }
            }
        }
        return responseModel;
    }

    private void createFullHeroModel(HeroModel heroModel){
        if (heroModel.getSkillId() != null){
            Skill skill = skillDao.getSkillById(heroModel.getSkillId());
            heroModel.setSkill(new SkillModel(skill));
        }
        if (heroModel.getExSkillId1() != null){
            UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(heroModel.getExSkillId1());
            Skill skill = skillDao.getSkillById(userSkill.getSkillId());
            heroModel.setExSkill1(new SkillModel(skill));
        }
        if (heroModel.getExSkillId2() != null){
            UserSkill userSkill = userSkillDao.getUserSkillByUserSkillId(heroModel.getExSkillId2());
            Skill skill = skillDao.getSkillById(userSkill.getSkillId());
            heroModel.setExSkill2(new SkillModel(skill));
        }
    }
}
