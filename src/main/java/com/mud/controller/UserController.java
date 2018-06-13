package com.mud.controller;

import com.mud.dao.*;
import com.mud.mapper.User;
import com.mud.mapper.UserAuth;
import com.mud.mapper.UserExtend;
import com.mud.mapper.UserIncome;
import com.mud.mapper.defines.DBMacro;
import com.mud.helper.EncoderHelper;
import com.mud.model.ResponseModel;
import com.mud.model.UserModel;
import com.mud.property.ResponseCode;
import com.mud.property.SGProps;
import com.mud.service.CacheService;
import com.mud.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by leeesven on 17/8/19.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private String[] props = new String[]{"nickName","exp","lv","power"};
    private String[] vals = new String[]{"小英雄","0","1","0"};

    @Autowired
    private SGProps sgProps;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    UserExtendDao userExtendDao;

    @Autowired
    UserIncomeDao userIncomeDao;

    @Autowired
    ConfigDao configDao;

    /**
     * 用户注册
     * @param name  账号
     * @param password  密码
     * @return  {@code User}
     */
    @PostMapping(value = "/register")
    public ResponseModel api_user_register(@RequestParam String name, @RequestParam String password){

        ResponseModel responseModel = new ResponseModel();

        try {

            User historyUser = userDao.getUserByName(name);
            if (historyUser != null){
                responseModel.setCode(ResponseCode.USER_REGISTER_NAME_EXIST);
            }else{
                String newPassword = name + password + sgProps.getMDSecret();
                String md5Str = EncoderHelper.EcoderByMD5(newPassword);

                String userId = sequenceService.sequenceOfUser();

                User currUser = new User();
                currUser.setUserId(userId);
                currUser.setUserName(name);
                userDao.insertUser(currUser);

                UserAuth userAuth = new UserAuth();
                userAuth.setUserId(userId);
                userAuth.setAuthName(DBMacro.AUTH_NAME_LOCAL);
                userAuth.setAuthId(md5Str);
                userAuthDao.insertUserAuth(userAuth);

                // 完善扩展信息
                for(int i=0; i<props.length; i++){
                    UserExtend userExtend = new UserExtend();
                    userExtend.setUserId(userId);
                    userExtend.setProp(props[i]);
                    userExtend.setVal(vals[i]);
                    userExtendDao.insertExtendOfUser(userExtend);
                }

                //创建账户
                UserIncome userIncome = new UserIncome();
                userIncome.setUserId(userId);
                userIncome.setGold(0);
                userIncome.setTongBi(0);
                userIncomeDao.insertUserIncome(userIncome);

                User resultUser = userDao.getUserByName(name);
                responseModel.setCode(ResponseCode.USER_REGISTER_SUCCESS);
                responseModel.setData(resultUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseModel;
    }

    /**
     * 用户登录
     * @param name  账号
     * @param password  密码
     * @return  {@code UserModel}
     */
    @PostMapping(value = "/login")
    public ResponseModel api_user_login(@RequestParam String name, @RequestParam String password){

        System.out.println("api_user_login name = " + name + " ,password = " + password);

        ResponseModel responseModel = new ResponseModel();

        User historyUser = userDao.getUserByName(name);
        if (historyUser == null){
            responseModel.setCode(ResponseCode.USER_LOGIN_NAME_NOT_EXIST);
        }else {
            UserAuth userAuth = userAuthDao.getUserAuthByUserId(historyUser.getUserId());
            String newPassword = name + password + sgProps.getMDSecret();
            String md5Str = "";
            try {
                md5Str = EncoderHelper.EcoderByMD5(newPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (userAuth.getAuthId().equalsIgnoreCase(md5Str)){

                //生成accessToken
                String accessToken = EncoderHelper.AccessToken();
                userAuth.setAuthAccessToken(accessToken);
                userAuthDao.updateUserAuth(userAuth);

                responseModel.setCode(ResponseCode.USER_LOGIN_SUCCESS);
                UserModel userModel = new UserModel(historyUser, userAuth);

                List<UserExtend> extendList = userExtendDao.getUserExtendList(userAuth.getUserId());
                userModel.setExtendList(extendList);

                UserIncome userIncome = userIncomeDao.selectUserIncome(userAuth.getUserId());
                userModel.setGold(userIncome.getGold());
                userModel.setTongBi(userIncome.getTongBi());

                long exp = CacheService.levelUpExp(userModel.getLv());
                if(exp == 0){
                    CacheService.cacheConfig(configDao.getAllConfig());
                }
                userModel.setLvUpExp(CacheService.levelUpExp(userModel.getLv()));

                responseModel.setData(userModel);
            }else {
                responseModel.setCode(ResponseCode.USER_LOGIN_PASSWORD_ERR);
            }
        }

        return responseModel;
    }

//    @GetMapping(value = "/skill_point")
//    public ResponseModel getSkillPoint(){
//
//        UserAuth userAuth = UserContext.getCurrentUserAuth();
//        String userId = userAuth.getUserId();
//        System.out.println("getSkillPoint userId = " + userId);
//
//        ResponseModel responseModel = new ResponseModel();
//
//        UserExtend userExtend = userExtendDao.getExtendOfUserSkillPoint(userId);
//        int skillPoint = 0;
//        if (userExtend != null){
//            skillPoint = Integer.parseInt(userExtend.getVal());
//        }
//        UserModel userModel = new UserModel();
//        userModel.setSkillPoint(skillPoint);
//        responseModel.setData(userModel);
//
//        return responseModel;
//    }

}
