package com.mud.controller;

import com.mud.dao.SequeneDao;
import com.mud.dao.UserAuthDao;
import com.mud.dao.UserDao;
import com.mud.mapper.User;
import com.mud.mapper.UserAuth;
import com.mud.mapper.defines.Macro;
import com.mud.helper.EncoderHelper;
import com.mud.model.ResponseModel;
import com.mud.property.ResponseCode;
import com.mud.property.SGProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leeesven on 17/8/19.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    SGProps sgProps;

    @Autowired
    UserDao userDao;

    @Autowired
    UserAuthDao userAuthDao;

    @Autowired
    SequeneDao sequeneDao;

    /**
     * 用户注册
     * @param name  账号
     * @param password  密码
     * @return
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

                int seqNo = sequeneDao.nextVal(Macro.SEQ_NAME_USER);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String nowdate = sdf.format(new Date());
                String userId = String.format("%s%04d", nowdate, seqNo);

                User currUser = new User();
                currUser.setUserId(userId);
                currUser.setUserName(name);
                userDao.insertUser(currUser);

                UserAuth userAuth = new UserAuth();
                userAuth.setUserId(userId);
                userAuth.setAuthName(Macro.AUTH_NAME_LOCAL);
                userAuth.setAuthId(md5Str);
                userAuthDao.insertUserAuth(userAuth);

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
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseModel api_user_login(@RequestParam String name, @RequestParam String password){

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
                responseModel.setCode(ResponseCode.USER_LOGIN_SUCCESS);
                responseModel.setData(historyUser);
            }else {
                responseModel.setCode(ResponseCode.USER_LOGIN_PASSWORD_ERR);
            }
        }

        return responseModel;
    }


}
